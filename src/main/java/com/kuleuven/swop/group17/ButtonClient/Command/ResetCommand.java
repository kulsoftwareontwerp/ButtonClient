package com.kuleuven.swop.group17.ButtonClient.Command;

import com.kuleuven.swop.group17.ButtonClient.applicationLayer.GameController;
import com.kuleuven.swop.group17.ButtonClient.types.ExecutionSnapshot;

public class ResetCommand implements GameWorldCommand{
	private GameController gameController;
	private ExecutionSnapshot snapshot;



	/**
	 * A ResetCommand makes a command that holds a reset action of the gameWorld.
	 * @throws NullPointerException when {@link GameController} object is null.
	 * @param gameController
	 * @param snapshot
	 */
	public ResetCommand(GameController gameController) {
		super();
		if(gameController == null)
			throw new NullPointerException("GameController may not be null.");
		this.gameController = gameController;
		this.snapshot = null;
	}

	@Override
	public void execute() {
		snapshot = gameController.resetGameWorld();
	}

	@Override
	public void undo() {
		gameController.restoreExecutionSnapshot(snapshot);

	}

}
