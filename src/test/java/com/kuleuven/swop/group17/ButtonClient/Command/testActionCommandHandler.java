package com.kuleuven.swop.group17.ButtonClient.Command;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.awt.Graphics;
import java.lang.reflect.Field;
import java.util.Stack;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class testActionCommandHandler {


		@Mock
		private GameWorldCommand command;
		@Mock(name = "executedGameWorldCommands")
		private Stack<Command> executedGameWorldCommands;
		@Mock(name = "undoneGameWorldCommands")
		private Stack<Command> undoneGameWorldCommands;
		@InjectMocks
		private ActionCommandHandler commandHandler;

		@Rule
		public ExpectedException exceptionRule = ExpectedException.none();


		@Before
		public void setUp() throws Exception {
			when(executedGameWorldCommands.pop()).thenReturn(command);
			when(undoneGameWorldCommands.pop()).thenReturn(command);
		}

		/**
		 * @throws java.lang.Exception
		 */
		@After
		public void tearDown() throws Exception {
		}

		@Test
		public void testActionCommandConstructorCommandHandler() {
			ActionCommandHandler command = new ActionCommandHandler();
			try {
				Field f = ActionCommandHandler.class.getDeclaredField("executedGameWorldCommands");
				f.setAccessible(true);
				assertTrue("executedGameWorldCommands was not resetted", f.get(command) != null);
				
				Field f1 = ActionCommandHandler.class.getDeclaredField("undoneGameWorldCommands");
				f1.setAccessible(true);
				assertTrue("undoneGameWorldCommands was not resetted", f1.get(command) != null);
				
			} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
				fail("One or more of the required fields were not declared.");
			}

		}

		@Test
		public void testHandleNull() {
			String excMessage = "GameWorldCommand may not be null.";
			exceptionRule.expect(NullPointerException.class);
			exceptionRule.expectMessage(excMessage);
			
			commandHandler.handle(null);
		}
		
		@Test
		public void testHandle() {
			commandHandler.handle(command);
			verify(command).execute();
			//verify(executedGameWorldCommands).push(command);
		}

		@Test
		public void testUndoSizeZero() {
			verify(executedGameWorldCommands,times(0)).pop();
			verifyNoInteractions(executedGameWorldCommands.pop());
			verifyNoInteractions(undoneGameWorldCommands);
		}
		
		@Test
		public void testUndo() {//TODO
			when(executedGameWorldCommands.size()).thenReturn(1);
			commandHandler.undo();
//			verify(command).undo();
//			verify(undoneGameWorldCommands).push(command);
		}
		
		@Test
		public void testRedoSizeZero() {
			verifyNoInteractions(undoneGameWorldCommands.pop());
			verifyNoInteractions(executedGameWorldCommands);

		}
		
		@Test
		public void testRedo() {//TODO
			when(undoneGameWorldCommands.size()).thenReturn(1);
			commandHandler.redo();
//			verify(command).execute();
//			verify(executedGameWorldCommands).push(command);

		}

}
