package com.kuleuven.swop.group17.ButtonClient.guiLayer;

import com.kuleuven.swop.group17.GameWorldApi.Action;

public class ActionFactory {

	public ActionFactory() {
		
	}
	
	public ActionButton createAction(Action action, int x , int y) {
		return new ActionButton(action, x, y);
	}

}
