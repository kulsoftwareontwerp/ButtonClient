package com.kuleuven.swop.group17.ButtonClient.applicationLayer;

import java.awt.Graphics;
import java.util.Set;

import com.kuleuven.swop.group17.GameWorldApi.Action;
import com.kuleuven.swop.group17.GameWorldApi.GameWorld;

/**
 * A DomeinController is used to call methods of a given {@link GameWorld} API.
 * @author Quentin Tilman
 *
 */
public class DomainController {
	
	private GameController gameController;
	
	/**
	 * Creates DomainControllet object.
	 * @throws NullPointerException when gameWorld is null.
	 * @param gameWorld Object of class {@link GameController} .
	 */
	public DomainController(GameWorld gameWorld) {
		if(gameWorld == null)
			throw new NullPointerException("gameWorld may not be null.");
		setGameController(new GameController(gameWorld));
	}
	
	public DomainController(GameWorld gameWorld,GameController gameController) {
		super();
		this.gameController = gameController;
	}
	
	public void setGameController(GameController controller) {
		if(controller == null)
			throw new NullPointerException("GameController may not be null.");
		gameController = controller ;
	}
	
	/**
	 * Executes given action by calling handleAction method of the {@link GameController}.
	 * @throws NullPointerException when action is null.
	 * @param action
	 */
	public void executeAction(Action action) {
		if(action == null)
			throw new NullPointerException("action may not be null.");
		gameController.handleAction(action);
	}
	
	/**
	 * Paints the given GameWorldGraphics.
	 * Calls method paint of the {@link GameController}.
	 * @throws NullPointerException when gameWorldGraphics is null.
	 * @param gameWorldGraphics
	 */
	public void paint(Graphics gameWorldGraphics) {
		if(gameWorldGraphics == null)
			throw new NullPointerException("gameWorldGraphics may not be null.");
		gameController.paint(gameWorldGraphics);
	}
	
	/**
	 * Will undo a certain action.
	 * Calls undo method of the {@link GameController}.
	 */
	public void undo() {
		gameController.undo();
	}
	
	/**
	 * Will redo a certain undone action.
	 * Calls redo method of the {@link GameController}.
	 */
	public void redo() {
		gameController.redo();
	}
	
	/**
	 * Will reset the game to its initial state.
	 * Calls HandleReset method of the {@link GameController}.
	 */
	public void resetGame() {
		gameController.handleReset();
	}
	
	/**
	 * Returns the Supported Actions that the game has.
	 * This returns all actions that a user can use to play the game.
	 * Calls getSuppportedActionsGameWorld method of {@link GameController}.
	 * @return Set of supported actions of the game.
	 */
	public Set<Action> getSupportedActionsGameWorld() {
		return gameController.getSupportedActionsGameWorld();
	}
	
}
