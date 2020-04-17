package com.kuleuven.swop.group17.ButtonClient.guiLayer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import com.kuleuven.swop.group17.GameWorldApi.Action;



public class ActionPanelArea implements Constants{

	private static HashSet<ActionButton> actionsInPanel;
	private ActionFactory actionFactory;
	
	public ActionPanelArea(ActionFactory actionFactory) {
		setactionFactory(actionFactory);
	}
	
	private void setactionFactory(ActionFactory actionFactory) {
		this.actionFactory = actionFactory;
	}
	
	public ActionButton createAction(Action action, int x , int y) {
		return actionFactory.createAction(action, x, y);
	}

	public static Action getActionFromCoordinate(int x, int y) {
		try {
			HashSet<ActionButton> actions  = getActionInActionPanel();
			for(ActionButton a: actions){
				HashSet<Pair<Integer, Integer>> coordinates = a.getCoordinatesAction();
				for(Pair<Integer, Integer> coordinate : coordinates) {
					if(coordinate.equals(new Pair<Integer, Integer>(x, y)))
						return a.getAction();
				}
			}
//			ActionButton a =  actions.stream()
//					.filter(e -> e.getCoordinatesAction().contains(new Pair<Integer, Integer>(x, y))).findFirst().get();
//			return a.getAction();
		}catch (NoSuchElementException e) {
			System.out.println("NULL");
			return null;
		}
		return null;
	}

	private static HashSet<ActionButton> getActionInActionPanel() {
		return actionsInPanel;
	}

	public void paint(Graphics g, ArrayList<Action> actions) {
		HashSet<ActionButton> set = new HashSet<ActionButton>();
		int tempHeight = 30;
		int tempWidth = 0;// initial starting position in paletteArea to draw Strings
		if(true) {
			
			for (var type : actions) {
					set.add(actionFactory.createAction(type ,0, tempHeight));
					tempHeight += 60;
				}
			set.forEach(e-> e.draw(g));
			}
		
		actionsInPanel = set; 
	}
}
