package com.kuleuven.swop.group17.ButtonClient.guiLayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.awt.Graphics;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.kuleuven.swop.group17.GameWorldApi.GameWorld;
import java.util.Timer;
import com.kuleuven.swop.group17.ButtonClient.applicationLayer.DomainController;
import com.kuleuven.swop.group17.GameWorldApi.Action;
import com.kuleuven.swop.group17.ButtonClient.types.*;


@RunWith(MockitoJUnitRunner.class)
public class testCanvasWindow {
	@Mock
	private Action action;
	@Mock
	private Graphics graphic;
	@Mock(name="maskedKeyTimer")
	private Timer maskedKeyTimer;
	@Mock(name="maskedKeyBag")
	private MaskedKeyBag maskedKeyBag;
	@Mock(name="suppoprtedActions")
	private ArrayList<Action> suppoprtedActions;
	@Mock(name="actionPanelArea")
	private ActionPanelArea actionPanelArea;
	@Mock(name="domainController")
	private DomainController domainController;
	@Spy
	@InjectMocks
	private CanvasWindow canvasWindow;


	@Before
	public void setup() {

	}

	@After
	public void tearup() {

	}

	@Test
	public void testConstructor(){
		CanvasWindow cw = new CanvasWindow("test", domainController);
		try {
			Field domainController = CanvasWindow.class.getDeclaredField("domainController");
			domainController.setAccessible(true);
			assertTrue("actionFactory was not resetted", domainController.get(cw) != null);

			Field actionFactory = CanvasWindow.class.getDeclaredField("actionFactory");
			actionFactory.setAccessible(true);
			assertTrue("actionFactory was not resetted", actionFactory.get(cw) != null);

			Field actionPanelArea = CanvasWindow.class.getDeclaredField("actionPanelArea");
			actionPanelArea.setAccessible(true);
			assertTrue("actionFactory was not resetted", actionPanelArea.get(cw) != null);


			Field supportedActions = CanvasWindow.class.getDeclaredField("supportedActions");
			supportedActions.setAccessible(true);
			assertTrue("actionFactory was not resetted", supportedActions.get(cw) != null);

			Field maskedKeyBag = CanvasWindow.class.getDeclaredField("maskedKeyBag");
			maskedKeyBag.setAccessible(true);
			assertTrue("actionFactory was not resetted", maskedKeyBag.get(cw) != null);



		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			fail("One or more of the required fields were not declared.");
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testPaint() {
		Graphics actionGraphic = Mockito.mock(Graphics.class);
		when(graphic.create(0,0,150,600)).thenReturn(actionGraphic);
		canvasWindow.paint(graphic);
		verify(graphic,times(2)).create(any(Integer.class),any(Integer.class),any(Integer.class),any(Integer.class));
		verify(actionPanelArea).paint(any(Graphics.class), (any(ArrayList.class)));
	}

	@Test
	public void handleKeyEventControlTimerNull() {
		canvasWindow.handleKeyEvent(401, 17, 'a' );
		verify(maskedKeyBag).setCtrl(true);
		try {

			Field maskedKeyTimer = CanvasWindow.class.getDeclaredField("maskedKeyTimer");
			maskedKeyTimer.setAccessible(true);
			assertTrue("actionFactory was not resetted", maskedKeyTimer.get(canvasWindow) != null);


		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			fail("One or more of the required fields were not declared.");
		}
	}


	@Test
	public void handleKeyEventShiftTimerNull() {
		canvasWindow.handleKeyEvent(401, 16, 'a' );
		verify(maskedKeyBag).setShift(true);
		try {

			Field maskedKeyTimer = CanvasWindow.class.getDeclaredField("maskedKeyTimer");
			maskedKeyTimer.setAccessible(true);
			assertTrue("actionFactory was not resetted", maskedKeyTimer.get(canvasWindow) != null);


		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			fail("One or more of the required fields were not declared.");
		}
	}


	@Test
	public void handleKeyEventEscapeTimerNull() {
		canvasWindow.handleKeyEvent(401, 27, 'a' );
		verify(domainController).resetGame();
	}

	@Test
	public void handleKeyEventZUndoTimerNull() {

		when(maskedKeyBag.getShift()).thenReturn(false);
		when(maskedKeyBag.getCtrl()).thenReturn(true);

		canvasWindow.handleKeyEvent(401, 90, 'a' );


		verify(maskedKeyBag,times(2)).getCtrl();
		verify(maskedKeyBag,times(2)).getShift();
		verify(domainController).undo();
	}
	@Test
	public void handleKeyEventZRedoTimerNull() {
		when(maskedKeyBag.getShift()).thenReturn(true);
		when(maskedKeyBag.getCtrl()).thenReturn(true);

		canvasWindow.handleKeyEvent(401, 90, 'a' );

		verify(maskedKeyBag,times(2)).getCtrl();
		verify(maskedKeyBag,times(2)).getShift();
		verify(domainController).redo();
	}

	@Test
	public void handleKeyEventUTimerNull() {
		canvasWindow.handleKeyEvent(401, 85, 'a' );
		verify(domainController).undo();
	}

	@Test
	public void handleKeyEventRTimerNull() {
		canvasWindow.handleKeyEvent(401, 82, 'a' );
		verify(domainController).redo();
	}

	@Test
	public void testExecuteCurrentAction() {
		canvasWindow.executeCurrentAction(action);
		verify(domainController).executeAction(action);
	}

	//	@Test //STATIC METHOD
	//	public void testHandleMouseEvent() {
	//		ActionPanelArea ap = spy(new ActionPanelArea(mock(ActionFactory.class)));
	//		HashSet<ActionButton> actions = new HashSet<ActionButton>();
	//		ActionButton ab = new ActionButton(action, 0, 0);
	//		actions.add(ab);
	//		when(ActionPanelArea.getActionInActionPanel()).thenReturn(actions);
	//		canvasWindow.handleMouseEvent(500, 0, 0,1);
	//		verify(canvasWindow).executeCurrentAction(action);
	//	}

	@Test
	public void testHandleKeyEventNegativeCONTROL() {
		try {

			Field maskedKeyTimer = CanvasWindow.class.getDeclaredField("maskedKeyTimer");
			maskedKeyTimer.setAccessible(true);
			maskedKeyTimer.set(canvasWindow, mock(Timer.class));

		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			fail("One or more of the required fields were not declared.");
		}
		canvasWindow.handleKeyEvent(401, 17, 'a');
		verify(maskedKeyBag).setShift(false);

	}
	
	@Test
	public void testHandleKeyEventNegativeSHIFT() {
		try {
			Field maskedKeyTimerF = CanvasWindow.class.getDeclaredField("maskedKeyTimer");
			maskedKeyTimerF.setAccessible(true);
			maskedKeyTimerF.set(canvasWindow, maskedKeyTimer);

		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			fail("One or more of the required fields were not declared.");
		}
		canvasWindow.handleKeyEvent(401, 16, 'a');
		verify(maskedKeyTimer).cancel();

	}
	
	@Test
	public void testHandleKeyEventNegativeZ() {
		when(maskedKeyBag.getShift()).thenReturn(false);
		when(maskedKeyBag.getCtrl()).thenReturn(true);
		try {

			Field maskedKeyTimer = CanvasWindow.class.getDeclaredField("maskedKeyTimer");
			maskedKeyTimer.setAccessible(true);
			maskedKeyTimer.set(canvasWindow, mock(Timer.class));

		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			fail("One or more of the required fields were not declared.");
		}
		canvasWindow.handleKeyEvent(401, 90, 'z');
		verify(maskedKeyBag).setShift(false);

	}
	@Test
	public void testHandleKeyEventNegativeZ2() {
		when(maskedKeyBag.getShift()).thenReturn(true);
		when(maskedKeyBag.getCtrl()).thenReturn(true);
		try {
			Field maskedKeyTimerF = CanvasWindow.class.getDeclaredField("maskedKeyTimer");
			maskedKeyTimerF.setAccessible(true);
			maskedKeyTimerF.set(canvasWindow, maskedKeyTimer);

		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			fail("One or more of the required fields were not declared.");
		}
		canvasWindow.handleKeyEvent(401, 90, 'z');
		verify(maskedKeyTimer).cancel();

	}

}
