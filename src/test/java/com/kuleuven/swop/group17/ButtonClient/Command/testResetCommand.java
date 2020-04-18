package com.kuleuven.swop.group17.ButtonClient.Command;

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
import com.kuleuven.swop.group17.ButtonClient.types.ExecutionSnapshot;
import com.kuleuven.swop.group17.GameWorldApi.Action;

@RunWith(MockitoJUnitRunner.class)
public class testResetCommand {

	@Mock(name = "gameController")
	private GameController gameController;
	@Mock(name = "snapshot")
	private ExecutionSnapshot snapshot;
	@InjectMocks
	private ResetCommand command;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		when(gameController.resetGameWorld()).thenReturn(snapshot);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testActionCommandConstructorGameControllerNull() {
		String excMessage = "GameController may not be null.";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);

		Command command = new ResetCommand(null);

	}

	@Test
	public void testExecute() {

		command.execute();
		verify(gameController).resetGameWorld();
		try {
			Field f = ResetCommand.class.getDeclaredField("snapshot");
			f.setAccessible(true);
			assertTrue("snapshot was not resetted", f.get(command) != null);
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			fail("One or more of the required fields were not declared.");
		}
	}

	@Test
	public void testUndo() {
		command.execute();
		command.undo();
		verify(gameController).restoreExecutionSnapshot(snapshot);

	}

}
