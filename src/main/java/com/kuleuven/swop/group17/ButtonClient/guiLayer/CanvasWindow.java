package com.kuleuven.swop.group17.ButtonClient.guiLayer;



import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import java.util.Timer;
import com.kuleuven.swop.group17.ButtonClient.applicationLayer.DomainController;
import com.kuleuven.swop.group17.GameWorldApi.Action;
import com.kuleuven.swop.group17.ButtonClient.types.*;



public class CanvasWindow extends CanvasResource implements Constants{
	private static final int MASKEDKEY_DURATION = 500;


	private ActionPanelArea actionPanelArea;
	
	private DomainController domainController;
	private ActionFactory actionFactory;
	private MaskedKeyBag maskedKeyBag;
	private Timer maskedKeyTimer = null;
	
	private ArrayList<Action> supportedActions;


	// methods of CanvasResource that need to be overridden:

	public CanvasWindow(String title, DomainController dc) {
		super(title);
		this.domainController = dc;

		super.height = 600;
		super.width = WIDTH;
		

		setActionFactory(new ActionFactory());
		this.actionPanelArea = new ActionPanelArea(getActionFactory());
		supportedActions = new ArrayList<Action>(domainController.getSupportedActionsGameWorld());
		maskedKeyBag = new MaskedKeyBag(false, false);

	}
	
	private CanvasWindow(String title,DomainController domainController, ActionFactory actionFactory, MaskedKeyBag bag,ArrayList<Action> suppoprtedActions,ActionPanelArea actionPanelArea) {
		super(title);
		this.actionPanelArea = actionPanelArea;
		this.domainController = domainController;
		this.supportedActions = suppoprtedActions;
		this.maskedKeyBag = bag;
	}

	@Override
	protected void paint(Graphics g) {

		Graphics actionGraphics = g.create(PROGRAM_START_X, ORIGIN, PROGRAM_END_X, super.height);
		Graphics gameAreaGraphics = g.create(GAME_START_X, ORIGIN, WIDTH - GAME_START_X, super.height);

		// Partition CanvasWindow in different sections

		actionPanelArea.paint(actionGraphics, supportedActions);

		domainController.paint(gameAreaGraphics);
		actionGraphics.setColor(Color.black);

	}



	@Override
	protected void handleMouseEvent(int id, int x, int y, int clickCount) {

		if(MouseEvent.MOUSE_CLICKED == id) {
			executeCurrentAction(ActionPanelArea.getActionFromCoordinate(x,y));
		}
	}
	
	
	@Override
	protected void handleKeyEvent(int id,int keyCode, char keyChar) {
		if (id == KeyEvent.KEY_PRESSED) {

			if (keyCode == KeyEvent.VK_CONTROL) {
				if (maskedKeyTimer != null) {
					maskedKeyTimer.cancel();
					maskedKeyBag.setShift(false);
				}
				maskedKeyTimer = new Timer();
				maskedKeyTimer.schedule(new MaskedKeyPressed(maskedKeyBag, false), MASKEDKEY_DURATION);
				maskedKeyBag.setCtrl(true);
			}
			if (keyCode == KeyEvent.VK_SHIFT) {
				if (maskedKeyTimer != null) {
					maskedKeyTimer.cancel();
				}
				maskedKeyTimer = new Timer();
				maskedKeyTimer.schedule(new MaskedKeyPressed(maskedKeyBag, true), MASKEDKEY_DURATION);
				maskedKeyBag.setShift(true);
			}
			if (keyCode == KeyEvent.VK_ESCAPE) {
				domainController.resetGame();
			}
			if (keyCode == KeyEvent.VK_Z) {
					if (maskedKeyBag.getCtrl() && !maskedKeyBag.getShift()) {
						undo();
					}
					if (maskedKeyBag.getCtrl() && maskedKeyBag.getShift()) {
						redo();
					}

			}
			
			if(keyCode == KeyEvent.VK_U) {
				System.out.println("U PRESSED");
				undo();
			}
			if(keyCode == KeyEvent.VK_R) {
				System.out.println("R PRESSED");				
				redo();
			}
		}
		repaint();
	}
	
	private void redo() {
		domainController.redo();
		
	}

	private void undo() {
		domainController.undo();
		
	}

	protected void executeCurrentAction(Action action) {
		domainController.executeAction(action);
		repaint();
	}

	


	public ActionFactory getActionFactory() {
		return actionFactory;
	}

	public void setActionFactory(ActionFactory actionFactory) {
		this.actionFactory = actionFactory;
	}

}