package com.kuleuven.swop.group17.ButtonClient.guiLayer;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;
import com.kuleuven.swop.group17.GameWorldApi.Action;

@RunWith(MockitoJUnitRunner.class)
public class testActionFactory {
	
	@Mock
	private Action action;
	@InjectMocks
	private ActionFactory actionFactory;
	
	@Test
	public void testCreateActionButton() {
		ActionButton ab = actionFactory.createActionButton(action, 0, 0);
		assertTrue((ab != null)&&(ab instanceof ActionButton));
	}

}
