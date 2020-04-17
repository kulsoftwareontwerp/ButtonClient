package com.kuleuven.swop.group17.ButtonClient.types;

import com.kuleuven.swop.group17.GameWorldApi.GameWorldSnapshot;

/**
/**
 * ExecutionSnapshot
 * 
 * @version 0.1
 * @author group17
 *
 */
public class ExecutionSnapshot {
	
	private GameWorldSnapshot gameSnapshot;
	
	/**
	 * @param nextBlockToBeExecuted
	 * @param gameSnapshot
	 */
	public ExecutionSnapshot( GameWorldSnapshot gameSnapshot) {
		super();
		this.gameSnapshot = gameSnapshot;
	}
	
	
	public GameWorldSnapshot getGameSnapshot() {
		return gameSnapshot;
	}

}
