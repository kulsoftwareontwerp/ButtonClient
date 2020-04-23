package com.kuleuven.swop.group17.ButtonClient;

import com.kuleuven.swop.group17.ButtonClient.applicationLayer.DomainController;
import com.kuleuven.swop.group17.ButtonClient.guiLayer.CanvasWindow;
import com.kuleuven.swop.group17.GameWorldApi.GameWorld;
import com.kuleuven.swop.group17.GameWorldApi.GameWorldType;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println(args[0]);
		GameWorld gameWorld = GameWorldType.createInstance(args[0]);
		
		DomainController dc = new DomainController(gameWorld);

		// Test push voor de UI-branch
		java.awt.EventQueue.invokeLater(() -> {
			new CanvasWindow("ButtonClient", dc).show();
		});

	}

}
