package com.kuleuven.swop.group17.ButtonClient.Command;

import com.kuleuven.swop.group17.ButtonClient.applicationLayer.GameController;
import com.kuleuven.swop.group17.ButtonClient.types.ExecutionSnapshot;
import com.kuleuven.swop.group17.GameWorldApi.Action;



public class ActionCommand implements GameWorldCommand{
	private GameController gameController;
	private ExecutionSnapshot snapshot;
	private Action action;

	/**
	 * @param gameController
	 * @throws NullPointerException | When {@link GameController} parameter is null.
	 * 								| When {@link Action} parameter is null.
	 */
	public ActionCommand(GameController gameController,Action action){
		super();
		if(gameController == null)
			throw new NullPointerException("GameController may not be null.");
		if(action == null)
			throw new NullPointerException("Action may not be null.");
		this.gameController = gameController;
		this.action=action;
		
	}

	@Override
	public void execute() {
	snapshot = gameController.performAction(action);
	}

	@Override
	public void undo() {
		if(snapshot!=null) {
			gameController.restoreExecutionSnapshot(snapshot);
			snapshot = null;
		}

	}
}
