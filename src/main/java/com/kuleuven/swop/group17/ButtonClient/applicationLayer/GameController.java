package com.kuleuven.swop.group17.ButtonClient.applicationLayer;

import java.awt.Graphics;
import java.util.Set;

import com.kuleuven.swop.group17.ButtonClient.Command.*;
import com.kuleuven.swop.group17.ButtonClient.types.ExecutionSnapshot;
import com.kuleuven.swop.group17.GameWorldApi.Action;
import com.kuleuven.swop.group17.GameWorldApi.GameWorld;
import com.kuleuven.swop.group17.GameWorldApi.GameWorldSnapshot;

/**
 * A GameController is used to call methods of a given {@link GameWorld} API.
 * @author Quentin Tilman
 *
 */
public class GameController {

	private GameWorld gameWorld;//API
	private GameWorldSnapshot initialGameWorldSnapshot;
	private ActionCommandHandler actionCommandHandler;
	
	/**
	 * Create gameController to use the {@link GameWorld} API.
	 * 
	 * @param gameWorld	The gameWorld the client use.
	 * @throws NullpointerException if gameWorld parameter is null.
	 */
	public GameController(GameWorld gameWorld) {
		if(gameWorld == null)
			throw new NullPointerException("GameWorld may not be null.");
		
		this.gameWorld = gameWorld;
		initialGameWorldSnapshot = gameWorld.saveState();
		actionCommandHandler = new ActionCommandHandler();
	}
	
	/**
	 * Executes action that has been given as parameter.
	 * This method calls the perfomAction method of {@link GameWorld}.
	 * It also creates a {@link GameWorldSnapshot} object which means this action is undoable/redoable.
	 * 
	 * @param action A supportedAction by the GameWorld.
	 * @throws 	| NullPointerException if given action parameter is null.
	 * 			| If GameWolrd is null.
	 * @return	ExecutionSnapshot , a snapshot of the game afterwards the action has been executed.
	 */
	public ExecutionSnapshot performAction(Action action)
	{	
		if(action == null)
			throw new NullPointerException("Action may not be null.");
		try {
			GameWorldSnapshot gameSnapshot = gameWorld.saveState();
			ExecutionSnapshot snapshot = new ExecutionSnapshot(gameSnapshot);
			gameWorld.performAction(action);
			return snapshot;
		}catch(NullPointerException e) {
			throw new NullPointerException("gameWorld cannot be null.");
		}
	}
	
	/**
	 * Gets the initial state of the Game.
	 * 
	 * @return initial GameWorldSnapshot saved when GameWorld is loaded in controller.
	 */
	public GameWorldSnapshot getInitialGameWorldSnapshot() {
		return initialGameWorldSnapshot;
	}
	
	/**
	 * Sets InitialGameWorldSnapshot.
	 * 
	 * @throws NullPointerException if given gameWorldSnapshot parameter is null.
	 * @param gameWorldSnapshot Object of the class {@link GameWorldSnapshot}.
	 */
	public void setGameWorldSnapShot(GameWorldSnapshot gameWorldSnapshot) {
		if(gameWorldSnapshot == null)
			throw new NullPointerException("gameWorldSnapshot may not be null.");
		this.initialGameWorldSnapshot = gameWorldSnapshot;
	}
	
	/**
	 * Calls paint method of the {@link GameWorld} that has been instantiated by given 
	 * a {@link Graphics} object as parameter.
	 * 
	 * @throws NullPointerException if given graphics parameter is null.
	 * @param graphics Graphics object.
	 */
	public void paint(Graphics graphics) {
		if(graphics == null)
			throw new NullPointerException("graphics may not be null.");
		gameWorld.paint(graphics);
	}
	
	/**
	 * Reset the game at its initial state.
	 * A {@link GameWorldSnapshot} of the game before executing the reset will be saved.
	 * A reset action can be undone or redone.
	 * 
	 * @return ExecutionSnapshot of the game before the reset has been executed.
	 */
	public ExecutionSnapshot resetGameWorld() {
		GameWorldSnapshot gameSnapshot = gameWorld.saveState();
		ExecutionSnapshot snapshot = new ExecutionSnapshot(gameSnapshot);
		gameWorld.restoreState(initialGameWorldSnapshot);
		return snapshot;
	}

	/**
	 * Restores the game before an certain action given by a {@link ExecutionSnapshot}.
	 * 
	 * @throws NullPointerException if given snapshot parameter is null.
	 * @param snapshot
	 */
	public void restoreExecutionSnapshot(ExecutionSnapshot snapshot) {
		if(snapshot == null)
			throw new NullPointerException("snapshot may not be null.");
		gameWorld.restoreState(snapshot.getGameSnapshot());
		
	}

	/**
	 * Gets all supported Actions of the {@link GameWorld} that has been selected at the start.
	 * @return Set of Actions that are supported by the called gameWorld.
	 */
	public Set<Action> getSupportedActionsGameWorld() {
		return gameWorld.getType().supportedActions();
	}

	/**
	 * Handles a given action.
	 * This method will create a {@link ActionCommand} for the given action.
	 * The command will be passed on to the {@link ActionCommandHandler} that will handle the command.
	 * 
	 * @throws NullPointerException if given action parameter is null.
	 * @param action
	 */
	public void handleAction(Action action) {
		if(action == null)
			throw new NullPointerException("Action may not be null.");
		ActionCommand command = new ActionCommand(this, action);
		actionCommandHandler.handle(command);
	}

	/**
	 * Uses the actionCommandHandler to retrieve a Snapshot to undo a action.
	 */
	public void undo() {
		actionCommandHandler.undo();
		
	}
	
	/**
	 * Uses the actionCommandHandler to retrieve a Snapshot to redo an action that has been undone.
	 */
	public void redo() {
		actionCommandHandler.redo();
		
	}

	/**
	 * This method resets the gameWorld.
	 * This method will create an {@link ResetCommand} an pass it on to the {@link ActionCommandHandler}.
	 */
	public void handleReset() {
		ResetCommand command = new ResetCommand(this);
		actionCommandHandler.handle(command);
	}
}
