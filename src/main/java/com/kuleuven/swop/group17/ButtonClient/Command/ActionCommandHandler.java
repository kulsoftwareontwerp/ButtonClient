package com.kuleuven.swop.group17.ButtonClient.Command;

import java.util.Stack;

public class ActionCommandHandler {
	private Stack<Command> executedGameWorldCommands;
	private Stack<Command> undoneGameWorldCommands;

	public ActionCommandHandler() {
		executedGameWorldCommands=new Stack<Command>();
		undoneGameWorldCommands=new Stack<Command>();
	}

	public void handle(GameWorldCommand command) {
		command.execute();
		executedGameWorldCommands.push(command);
	}

	public void undo() {
		if(executedGameWorldCommands.size()!=0) {
			Command c = executedGameWorldCommands.pop();
			c.undo();
			undoneGameWorldCommands.push(c);
		}
	}

	public void redo() {
		if(undoneGameWorldCommands.size()!=0) {
			Command c = undoneGameWorldCommands.pop();
			c.execute();
			executedGameWorldCommands.push(c);
		}
	}
}
