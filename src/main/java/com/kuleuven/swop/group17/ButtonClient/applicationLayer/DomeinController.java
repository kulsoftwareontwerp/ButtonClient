package com.kuleuven.swop.group17.ButtonClient.applicationLayer;

import java.awt.Graphics;
import java.util.Set;

import com.kuleuven.swop.group17.GameWorldApi.Action;
import com.kuleuven.swop.group17.GameWorldApi.GameWorld;


public class DomeinController {
	private GameController gameController;
	
	
	public DomeinController(GameWorld gameWorld) {
		gameController = new GameController(gameWorld);
	}
	
	
	public void executeAction(Action action) {
		gameController.handleAction(action);
	}
	
	public void paint(Graphics gameWorldGraphics) {
		gameController.paint(gameWorldGraphics);
	}
	
	public void undo() {
		gameController.undo();
	}
	
	public void redo() {
		gameController.redo();
	}
	
	public void resetGame() {
		gameController.handleReset();
	}
	
	public Set<Action> getSupportedActionsGameWorld() {
		return gameController.getSupportedActionsGameWorld();
	}
	
}
