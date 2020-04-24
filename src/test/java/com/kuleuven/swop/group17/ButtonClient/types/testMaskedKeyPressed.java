package com.kuleuven.swop.group17.ButtonClient.types;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import java.lang.reflect.Field;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;

import com.kuleuven.swop.group17.ButtonClient.guiLayer.MaskedKeyBag;


@RunWith(MockitoJUnitRunner.class)
public class testMaskedKeyPressed {
	
	@Mock
	MaskedKeyBag maskedKeyBag;
	@InjectMocks
	MaskedKeyPressed maskedKeyPressed;
	
	@Test
	public void testConstructor() {
		MaskedKeyPressed maskedKeyPressed = new MaskedKeyPressed(maskedKeyBag, true);
		try {
			
			Field bag = MaskedKeyPressed.class.getDeclaredField("bag");
			bag.setAccessible(true);
			assertTrue("snapshot was not resetted", bag.get(maskedKeyPressed) != null);
			
			Field resetBoth = MaskedKeyPressed.class.getDeclaredField("resetBoth");
			resetBoth.setAccessible(true);
			assertTrue("snapshot was not resetted", resetBoth.get(maskedKeyPressed) != null);
			
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			fail("One or more of the required fields were not declared.");
		}
	}
	
	
	@Test
	public void testRunFalse() {
		MaskedKeyPressed mkp = new MaskedKeyPressed(maskedKeyBag,true);
		mkp.run();
		verify(maskedKeyBag).setCtrl(false);
		verify(maskedKeyBag).setShift(false);
	}
	
	@Test
	public void testRunTrue() {
		MaskedKeyPressed mkp = new MaskedKeyPressed(maskedKeyBag,false);
		mkp.run();
		verify(maskedKeyBag).setCtrl(false);
	}
}
