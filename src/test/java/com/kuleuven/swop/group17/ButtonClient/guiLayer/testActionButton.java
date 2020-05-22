package com.kuleuven.swop.group17.ButtonClient.guiLayer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import java.awt.Graphics;
import java.lang.reflect.Field;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import com.kuleuven.swop.group17.GameWorldApi.Action;

@RunWith(MockitoJUnitRunner.class)
public class testActionButton {

	@Mock
	private Graphics graphic;
	@Mock
	private Action action;
	@Mock
	private HashSet<Pair<Integer, Integer>> coordinatesAction;
	@Spy
	@InjectMocks
	private ActionButton actionButton;
	
	
	@Before
	public void setUp() throws Exception {
		when(action.toString()).thenReturn("MOVEFORWARD");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testStandardHeigh() {
		assertTrue(actionButton.getStandardHeight() == 30);
	}
	
	@Test
	public void testGetCoordinateAction() {
		ActionButton ab = new ActionButton(mock(Action.class), 10, 10);
		HashSet<Pair<Integer,Integer>> coordinates = actionButton.createCoordinatePairs(10, 10);
		assertTrue(ab.getCoordinatesAction().equals(coordinates));
	}
	
	@Test
	public void testHashCode() {
		Integer hashCode = actionButton.hashCode();
		assertTrue(hashCode != null);
	}
	
	@Test
	public void testSetCoordinateAction() {
		actionButton.setCoordinatesAction(actionButton.createCoordinatePairs(10, 10));
		try {
			Field coordinatesAction = ActionButton.class.getDeclaredField("coordinatesAction");
			coordinatesAction.setAccessible(true);
			assertTrue("snapshot was not resetted", coordinatesAction.get(actionButton) != null);
			
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			fail("One or more of the required fields were not declared.");
		}
	}
	
	@Test
	public void testActionButtonConstructor() {
		ActionButton ab = new ActionButton(action , 0 , 0);
		try {
			Field id = ActionButton.class.getDeclaredField("id");
			id.setAccessible(true);
			assertTrue("snapshot was not resetted", id.get(ab) != null);
			
			Field action = ActionButton.class.getDeclaredField("action");
			action.setAccessible(true);
			assertTrue("snapshot was not resetted", action.get(ab) != null);
			
			
			Field x = ActionButton.class.getDeclaredField("x_coord");
			x.setAccessible(true);
			assertTrue("snapshot was not resetted", x.get(ab) != null);
			
			
			Field y = ActionButton.class.getDeclaredField("y_coord");
			y.setAccessible(true);
			assertTrue("snapshot was not resetted", y.get(ab) != null);
			
			Field coordinatesAction = ActionButton.class.getDeclaredField("coordinatesAction");
			coordinatesAction.setAccessible(true);
			assertTrue("snapshot was not resetted", coordinatesAction.get(ab) != null);
			
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			fail("One or more of the required fields were not declared.");
		}
	}
	
	@Test
	public void testDraw() {
		actionButton.draw(graphic);
		//verify(graphic,atLeast(4)).drawLine(any(int.class),any(int.class),any(int.class),any(int.class)); TODO
		//verify(graphic).drawString(any(String.class),any(int.class),any(int.class)); TODO
	}
	
	@Test
	public void testCreateCoordinatePairs() {	
		HashSet<Pair<Integer, Integer>> set = actionButton.createCoordinatePairs(0, 0);
		assertFalse(0 == set.size());
	}
	
	@Test
	public void testInitDimension() {
		actionButton.initDimensions();
		try {
			Field height = ActionButton.class.getDeclaredField("height");
			height.setAccessible(true);
			assertTrue("snapshot was not resetted", height.get(actionButton) != null);
			
			Field width = ActionButton.class.getDeclaredField("width");
			width.setAccessible(true);
			assertTrue("snapshot was not resetted", width.get(actionButton) != null);
			
			
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			fail("One or more of the required fields were not declared.");
		}
	}
	
	@Test
	public void testEquals() {
		ActionButton a = new ActionButton(action,0,0);
		assertTrue(a.equals(a));
	}
	@Test
	public void testEqualsFalse() {
		ActionButton a = new ActionButton(action,0,0);
		assertFalse(a.equals("zero"));
	}
	
	@Test
	public void testToString() {
		ActionButton a = new ActionButton(action,0,0);
		assertEquals("ActionButton [id="+a.getId()+", x_coord="+a.getX_coord()+", y_coord="+a.getY_coord() +"]",a.toString() );
	}
	

	
	
	
}
