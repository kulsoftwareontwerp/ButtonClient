package com.kuleuven.swop.group17.ButtonClient.types;

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
import com.kuleuven.swop.group17.GameWorldApi.GameWorldSnapshot;

@RunWith(MockitoJUnitRunner.class)
public class testExecutionSnapShot {
	
	@Mock
	GameWorldSnapshot gameWorldSnapshort;
	@InjectMocks
	ExecutionSnapshot executionSnapshot;
	
	@Test
	public void testGetGameSnapShot(){
		assertEquals(gameWorldSnapshort,executionSnapshot.getGameSnapshot());
	}

}
