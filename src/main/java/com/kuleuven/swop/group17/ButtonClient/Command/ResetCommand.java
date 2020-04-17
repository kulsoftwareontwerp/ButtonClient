package com.kuleuven.swop.group17.ButtonClient.Command;

import com.kuleuven.swop.group17.ButtonClient.applicationLayer.GameController;
import com.kuleuven.swop.group17.ButtonClient.types.ExecutionSnapshot;

public class ResetCommand implements GameWorldCommand{
	private GameController gameController;
	private ExecutionSnapshot snapshot;



	/**
	 * @param gameController
	 * @param snapshot
	 */
	public ResetCommand(GameController gameController) {
		super();
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
