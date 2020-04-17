package com.kuleuven.swop.group17.ButtonClient.Command;

import java.util.Stack;

public class ActionCommandHandler {
	private Stack<Command> executedGameWorldCommands;
	private Stack<Command> undoneGameWorldCommands;

	public ActionCommandHandler() {
		executedGameWorldCommands=new Stack<Command>();
		undoneGameWorldCommands=new Stack<Command>();
	}
	/**
	 * Handles a given command by executing its action and pushing it on the executed {@link GameWorldCommand} stack.
	 * 
	 * @throws NullPointerException when {@link GameWorldCommand} parameter is null.
	 * @param command
	 */
	public void handle(GameWorldCommand command) {
		if(command == null)
			throw new NullPointerException("GameWorldCommand may not be null.");
		command.execute();
		executedGameWorldCommands.push(command);
	}
	
	/**
	 * This method takes the first {@link Command} object from the executed {@link GameWorldCommand} stack and calls the execute method.
	 * This {@link Command} object can either be of the class {@link ActionCommand} or either {@link ResetCommand}.
	 */
	public void undo() {
		if(executedGameWorldCommands.size()!=0) {
			Command c = executedGameWorldCommands.pop();
			c.undo();
			undoneGameWorldCommands.push(c);
		}
	}
	
	/**
	 * This method takes the first {@link Command} object from the undone {@link GameWorldCommand} stack and calls the execute method.
	 * This {@link Command} object can either be of the class {@link ActionCommand} or either {@link ResetCommand}.
	 */
	public void redo() {
		if(undoneGameWorldCommands.size()!=0) {
			Command c = undoneGameWorldCommands.pop();
			c.execute();
			executedGameWorldCommands.push(c);
		}
	}
}
