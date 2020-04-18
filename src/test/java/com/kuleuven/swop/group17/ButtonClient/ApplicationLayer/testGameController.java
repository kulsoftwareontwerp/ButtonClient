package com.kuleuven.swop.group17.ButtonClient.ApplicationLayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.awt.Graphics;
import java.io.IOException;
import java.lang.reflect.Field;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.kuleuven.swop.group17.ButtonClient.Command.ActionCommandHandler;
import com.kuleuven.swop.group17.ButtonClient.Command.GameWorldCommand;
import com.kuleuven.swop.group17.ButtonClient.Command.ResetCommand;
import com.kuleuven.swop.group17.ButtonClient.applicationLayer.DomainController;
import com.kuleuven.swop.group17.ButtonClient.applicationLayer.GameController;
import com.kuleuven.swop.group17.ButtonClient.types.ExecutionSnapshot;
import com.kuleuven.swop.group17.GameWorldApi.Action;
import com.kuleuven.swop.group17.GameWorldApi.GameWorld;
import com.kuleuven.swop.group17.GameWorldApi.GameWorldSnapshot;

@RunWith(MockitoJUnitRunner.class)
public class testGameController {
		
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	

	@Mock
	private Action action;
	@Mock
	private Graphics graphic;
	@Mock
	private GameWorldCommand gameWorldCommand;
	
	@Mock
	private ExecutionSnapshot executionSnapshot;

	@Mock(name = "gameWorld")
	private GameWorld gameWorld;
	
	@Mock(name = "initialGameWorldSnapshot")
	private GameWorldSnapshot initialGameWorldSnapshot;
	
	@Mock(name = "actionCommandHandler")
	private ActionCommandHandler actionCommandHandler;
	
	@InjectMocks
	private GameController gameController;
	
	@Before
	public void setUp() throws Exception {
		when(gameWorld.saveState()).thenReturn(initialGameWorldSnapshot);
		
	}
	

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	
	
	@Test
	public void testConstructorGameWorldNull() {
		String excMessage = "GameWorld may not be null.";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);
		
		GameController gc = new GameController(null);
		
	}
	
	@Test
	public void testConstructorGameWorld() {
		when(gameWorld.saveState()).thenReturn(initialGameWorldSnapshot);
		GameController gc = new GameController(gameWorld);
		
		try {
			Field f = GameController.class.getDeclaredField("gameWorld");
			f.setAccessible(true);
			assertTrue("gameController was not initialised", f.get(gc) != null);
			
			Field f2 = GameController.class.getDeclaredField("initialGameWorldSnapshot");
			f2.setAccessible(true);
			assertTrue("initialGameWorldSnapshot was not initialised", f2.get(gc) != null);
			
			Field f3 = GameController.class.getDeclaredField("actionCommandHandler");
			f3.setAccessible(true);
			assertTrue("actionCommandHandler was not initialised", f3.get(gc) != null);
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			fail("One or more of the required fields were not declared.");
		}
	}
	
	@Test
	public void testInitiateGameControllerGameWorldNull() {
		String excMessage = "GameWorld may not be null.";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);
		gameController.initiateGameController(null , initialGameWorldSnapshot ,actionCommandHandler);
	}
	
	@Test
	public void testInitiateGameControllerGameWorldSnapshotNull() {
		String excMessage = "gameWorldSnapshot may not be null.";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);
		
		gameController.initiateGameController(gameWorld , null ,actionCommandHandler);
	}
	
	@Test
	public void testInitiateGameControlleractionCommandHandlerNull() {
		String excMessage = "actionCommandHandler may not be null.";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);
		
		gameController.initiateGameController(gameWorld , initialGameWorldSnapshot ,null);
	}
	
	@Test
	public void testSetGameWorldSnapshotNull() {
		String excMessage = "gameWorldSnapshot may not be null.";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);
		
		gameController.setGameWorldSnapShot(null);
	}
	
	
	@Test
	public void testPerformActionNull() {
		String excMessage = "Action may not be null.";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);
		
		gameController.performAction(null);
		
		
	}
	
	@Test
	public void testPerformAction() {
		ExecutionSnapshot esh= gameController.performAction(action);
		assertTrue(esh != null);
		verify(gameWorld).performAction(action);
	}
	
	@Test
	public void testPaintNull() {
		String excMessage = "graphics may not be null.";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);
		
		gameController.paint(null);
		
	}
	
	@Test
	public void testPaint() {
		gameController.paint(graphic);
		verify(gameWorld).paint(graphic);
	}
	
	@Test
	public void testResetGameWorld() {
		assertTrue(null != gameController.resetGameWorld());
		verify(gameWorld).restoreState(initialGameWorldSnapshot);
		
	}
	
	@Test
	public void testRestoreSnapShotNull(){
		String excMessage = "snapshot may not be null.";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);
		
		gameController.restoreExecutionSnapshot(null);
	}
	
	@Test
	public void testRestoreSnapShot(){
		gameController.restoreExecutionSnapshot(executionSnapshot);
		verify(gameWorld).restoreState(executionSnapshot.getGameSnapshot());
	}
	
	
	@Ignore
	public void testSupportedActions() {
		Set<Action> supportedActions = new HashSet<Action>();
		supportedActions.add(action);
		when(gameWorld.getType().supportedActions()).thenReturn(supportedActions);
		
		
		gameController.getSupportedActionsGameWorld();
		verify(gameWorld).getType().supportedActions();
	}
	
	@Test
	public void testHandleActionNull() {
		String excMessage = "Action may not be null.";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);
		
		gameController.handleAction(null);
	}
	
	
	@Test
	public void testHandleAction() {
		gameController.handleAction(action);
		verify(actionCommandHandler).handle(any(GameWorldCommand.class));
	}
	
	
	@Test
	public void testUndo() {
		gameController.undo();
		verify(actionCommandHandler).undo();
	}
	
	@Test
	public void testRedo() {
		gameController.redo();
		verify(actionCommandHandler).redo();
	}
	

	@Test
	public void testHandleReset() {
		gameController.handleReset();
		verify(actionCommandHandler).handle(any(ResetCommand.class));
	}
	
	
	
}

