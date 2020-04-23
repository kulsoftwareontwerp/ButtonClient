package com.kuleuven.swop.group17.ButtonClient.guiLayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.awt.Graphics;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.kuleuven.swop.group17.GameWorldApi.Action;

@RunWith(MockitoJUnitRunner.class)
public class testActionPanelArea {
	
	@Mock
	private Graphics graphic;
	@Mock
	private Pair<Integer,Integer> coordinate;
	@Spy
	private HashSet<Pair<Integer,Integer>> coordinates;
	@Mock
	private ActionButton actionButton;
	@Mock
	private Action action;
	@Mock(name="actionFactory")
	private ActionFactory actionFactory;
	@Spy
	private HashSet<ActionButton> actionsInPanel;
	@Spy
	@InjectMocks
	private ActionPanelArea	actionPanelArea;
	
	@Before
	public void setup() {
		when(actionFactory.createActionButton(action, 0, 30)).thenReturn(actionButton);
		
		when(actionButton.getCoordinatesAction()).thenReturn(coordinates);
		
		actionsInPanel.add(actionButton);		
		coordinates.add(coordinate);
	}
	@Test
	public void testActionPanelAreaConstructor() {
		ActionPanelArea apa = new ActionPanelArea(actionFactory);
		try {
			Field actionFactory = ActionPanelArea.class.getDeclaredField("actionFactory");
			actionFactory.setAccessible(true);
			assertTrue("actionFactory was not resetted", actionFactory.get(apa) != null);
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			fail("One or more of the required fields were not declared.");
		}
	}
	
	@Test
	public void testCreateActionButton() {
		actionPanelArea.createActionButton(action, 0, 0);
		verify(actionFactory).createActionButton(action, 0, 0);
	}
	
	@Test
	public void testPaint() {
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(action);
		actionPanelArea.paint(graphic, actions);
		
		verify(actionFactory).createActionButton(action, 0, 30);
		
		try {
			Field actionsInPanel = ActionPanelArea.class.getDeclaredField("actionsInPanel");
			actionsInPanel.setAccessible(true);
			assertTrue("actionFactory was not resetted", actionsInPanel.get(actionPanelArea) != null);
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			fail("One or more of the required fields were not declared.");
		}
		
	}
	//TODO getActionsFromCoortinates
	@SuppressWarnings("static-access")
	@Test
	public void testGetActionFromCoordinateNull() {
		Action action = mock(Action.class);
		ActionButton ab = mock(ActionButton.class);
		ab.createCoordinatePairs(0, 0);
		ab.setAction(action);
		actionsInPanel.add(ab);
		
		actionPanelArea.getActionFromCoordinate(0, 0);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testGetActionFromCoordinate() {
		ActionButton ab = new ActionButton(action, 0, 0);
		ab.setCoordinatesAction(coordinates);
		actionsInPanel.add(ab);
		
		actionPanelArea.getActionFromCoordinate(0, 0);
	}
}
