package com.kuleuven.swop.group17.ButtonClient.guiLayer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import com.kuleuven.swop.group17.GameWorldApi.Action;
import com.kuleuven.swop.group17.GameWorldApi.GameWorld;



public class ActionPanelArea implements Constants{

	private static HashSet<ActionButton> actionsInPanel;
	private ActionFactory actionFactory;
	
	public ActionPanelArea(ActionFactory actionFactory) {
		setactionFactory(actionFactory);
	}
	
	@SuppressWarnings({ "unused", "static-access" })
	private ActionPanelArea(ActionFactory actionFactory, HashSet<ActionButton> actionsInPanel) {
		super();
		this.actionFactory = actionFactory;
	}
	
	private void setactionFactory(ActionFactory actionFactory) {
		this.actionFactory = actionFactory;
	}
	
	public ActionButton createActionButton(Action action, int x , int y) {
		return actionFactory.createActionButton(action, x, y);
	}

	/**
	 * Executes a Action that exists in the list of SupportedActions given by the Game we are playing.
	 * The Action is determined by the coordinates where the player has clicked. 
	 * Supposedly on a {@link ActionButton} object that has a Set of Coordinates.
	 * @param x
	 * @param y
	 * @return Action given by a {@link ActionButton} selected by the coordinates if it exists.
	 */
	public static Action getActionFromCoordinate(int x, int y) {
		try {
			HashSet<ActionButton> actions  = getActionInActionPanel();
			ActionButton a =  actions.stream()
					.filter(e -> e.getCoordinatesAction().contains(new Pair<Integer, Integer>(x, y))).findFirst().get();
			return a.getAction();
		}catch (NoSuchElementException e) {
			System.out.println("NULL");
			return null;
		}
	}

	public static HashSet<ActionButton> getActionInActionPanel() {
		return actionsInPanel;
	}

	/**
	 * Paints the {@link ActionPanelArea} given a {@link Graphics} object that determines the boundaries of the panel 
	 * and the List of Supported Actions that need to be drawn in the {@link ActionPanelArea}
	 * @param g Graphics object of the class {@link Graphics}.
	 * @param actions {@link ArrayList} witch as object type class {@link Action}.
	 */
	public void paint(Graphics g, ArrayList<Action> actions) {
		HashSet<ActionButton> set = new HashSet<ActionButton>();
		int tempHeight = 30;
		int tempWidth = 0;// initial starting position in paletteArea to draw Strings
		if(true) {
			
			for (var type : actions) {
					set.add(actionFactory.createActionButton(type ,0, tempHeight));
					tempHeight += 60;
				}
			set.forEach(e-> e.draw(g));
			}
		
		actionsInPanel = set; 
	}
}
