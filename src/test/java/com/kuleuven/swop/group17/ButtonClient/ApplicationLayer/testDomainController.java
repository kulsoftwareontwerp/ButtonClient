package com.kuleuven.swop.group17.ButtonClient.ApplicationLayer;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.awt.Graphics;
import java.lang.reflect.Field;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;

import com.kuleuven.swop.group17.ButtonClient.applicationLayer.DomainController;
import com.kuleuven.swop.group17.ButtonClient.applicationLayer.GameController;
import com.kuleuven.swop.group17.GameWorldApi.Action;
import com.kuleuven.swop.group17.GameWorldApi.GameWorld;
import com.kuleuven.swop.group17.GameWorldApi.GameWorldSnapshot;

@RunWith(MockitoJUnitRunner.class)
public class testDomainController {

	
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@InjectMocks
	private DomainController domainController;

	@Mock
	private Action action;
	@Mock
	private Graphics graphic;
	@Mock
	private GameWorldSnapshot gameWolrdSnapshot;
	
	@Mock(name = "gameController")
	private GameController gameController;
	@Mock(name = "gameWorld")
	private GameWorld gameWorld;
	
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	
	
	@Test
	public void testConstructorGameWorldNull() {
		String excMessage = "gameWorld may not be null.";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);
		
		DomainController dc = new DomainController(null);
		
		verify(dc,times(0)).setGameController(any(GameController.class));
		
	}
	
	@Test
	public void testConstructorGameWorld() {
		when(gameWorld.saveState()).thenReturn(gameWolrdSnapshot);
		DomainController dc = new DomainController(gameWorld);

		try {
			Field f = DomainController.class.getDeclaredField("gameController");
			f.setAccessible(true);
			assertTrue("gameController was not initialised", f.get(dc) != null);
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			fail("One or more of the required fields were not declared.");
		}
	}
	
	@Test
	public void testSetGameControllerNull() {
		String excMessage = "GameController may not be null.";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);
		domainController.setGameController(null);
	}
	
	@Test
	public void testExecuteActionNull() {
		String excMessage = "action may not be null.";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);
		domainController.executeAction(null);
	}
	
	@Test
	public void testExecuteAction() {
		domainController.executeAction(action);
		verify(gameController).handleAction(action);
	}
	
	@Test
	public void testPaintGameWorldGraphicsNull() {
		String excMessage = "gameWorldGraphics may not be null.";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);
		domainController.paint(null);
	}
	
	@Test
	public void testPaint() {
		domainController.paint(graphic);
		verify(gameController).paint(graphic);
	}
	
	@Test
	public void testUndo() {
		domainController.undo();
		verify(gameController).undo();
	}
	
	@Test
	public void testRedo() {
		domainController.redo();
		verify(gameController).redo();
	}
	
	@Test
	public void testSupportedActions() {
		domainController.getSupportedActionsGameWorld();
		verify(gameController).getSupportedActionsGameWorld();
		
	}
	
	@Test
	public void testReset() {
		domainController.resetGame();
		verify(gameController).handleReset();
		
	}
	
	
	
}
