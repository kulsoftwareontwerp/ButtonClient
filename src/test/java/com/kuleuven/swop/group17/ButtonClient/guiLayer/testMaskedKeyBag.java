package com.kuleuven.swop.group17.ButtonClient.guiLayer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class testMaskedKeyBag {
	
	private MaskedKeyBag mkb;
	
	@Before
	public void setUp() throws Exception {
		 mkb = new MaskedKeyBag(true,true);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testMaskedKeyBagPositive() {
		MaskedKeyBag mkb = new MaskedKeyBag(true,true);
		try {
			Field shift = MaskedKeyBag.class.getDeclaredField("shift");
			shift.setAccessible(true);
			assertTrue("executedGameWorldCommands was not resetted", shift.get(mkb) != null);
			
			Field ctrl = MaskedKeyBag.class.getDeclaredField("ctrl");
			ctrl.setAccessible(true);
			assertTrue("undoneGameWorldCommands was not resetted", ctrl.get(mkb) != null);
			
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			fail("One or more of the required fields were not declared.");
		}
	}
	
	@Test
	public void testGetShift() {
		assertTrue(mkb.getShift());
	}
	
	@Test
	public void testGetCtrl() {
		assertTrue(mkb.getCtrl());
	}
	
	@Test
	public void testSetCtrl() {
		try {
			mkb.setCtrl(false);
			Field ctrl = MaskedKeyBag.class.getDeclaredField("ctrl");
			ctrl.setAccessible(true);
			assertTrue("undoneGameWorldCommands was not resetted", ctrl.get(mkb) == Boolean.FALSE);
			
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			fail("One or more of the required fields were not declared.");
		}
	}
	
	@Test
	public void testSetShift() {
		try {
			mkb.setShift(false);
			Field shift = MaskedKeyBag.class.getDeclaredField("shift");
			shift.setAccessible(true);
			assertTrue("executedGameWorldCommands was not resetted", shift.get(mkb) == Boolean.FALSE);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
