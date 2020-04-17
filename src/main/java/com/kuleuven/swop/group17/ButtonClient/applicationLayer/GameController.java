package com.kuleuven.swop.group17.ButtonClient.applicationLayer;

import java.awt.Graphics;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Set;

import com.kuleuven.swop.group17.ButtonClient.Command.*;
import com.kuleuven.swop.group17.ButtonClient.types.ExecutionSnapshot;
import com.kuleuven.swop.group17.GameWorldApi.Action;
import com.kuleuven.swop.group17.GameWorldApi.GameWorld;
import com.kuleuven.swop.group17.GameWorldApi.GameWorldSnapshot;

public class GameController {

	private GameWorld gameWorld;//API
	private GameWorldSnapshot initialGameWorldSnapshot;
	private ActionCommandHandler actionCommandHandler;
	
	public GameController(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
		initialGameWorldSnapshot = gameWorld.saveState();
		this.actionCommandHandler = new ActionCommandHandler();
		
	}
	
	public void handleCommand(GameWorldCommand command) {
		this.actionCommandHandler.handle(command);
	}
	
	public ExecutionSnapshot performAction(Action action)
	{	GameWorldSnapshot gameSnapshot = gameWorld.saveState();
		ExecutionSnapshot snapshot = new ExecutionSnapshot(gameSnapshot);
		gameWorld.performAction(action);
		return snapshot;
	}
	
	public GameWorldSnapshot getInitialGameWorldSnapshot() {
		return initialGameWorldSnapshot;
	}
	
	public void setGameWorldSnapShot(GameWorldSnapshot gameWorldSnapshot) {
		this.initialGameWorldSnapshot = gameWorldSnapshot;
	}
	
	public void paint(Graphics graphics) {
		gameWorld.paint(graphics);
	}
	
	public ExecutionSnapshot resetGameWorld() {
		GameWorldSnapshot gameSnapshot = gameWorld.saveState();
		ExecutionSnapshot snapshot = new ExecutionSnapshot(gameSnapshot);
		gameWorld.restoreState(initialGameWorldSnapshot);
		return snapshot;
	}

	public void restoreExecutionSnapshot(ExecutionSnapshot snapshot) {
		gameWorld.restoreState(snapshot.getGameSnapshot());
		
	}

	public Set<Action> getSupportedActionsGameWorld() {
		return gameWorld.getType().supportedActions();
	}

	public void handle(Action action) {
		ActionCommand command = new ActionCommand(this, action);
		actionCommandHandler.handle(command);
	}

	public void undo() {
		actionCommandHandler.undo();
		
	}

	public void redo() {
		actionCommandHandler.redo();
		
	}
}
