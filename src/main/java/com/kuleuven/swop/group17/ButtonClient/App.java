package com.kuleuven.swop.group17.ButtonClient;


import java.security.DomainCombiner;

import com.kuleuven.swop.group17.ButtonClient.applicationLayer.DomainController;
import com.kuleuven.swop.group17.ButtonClient.guiLayer.CanvasWindow;
import com.kuleuven.swop.group17.GameWorldApi.GameWorld;
import com.kuleuven.swop.group17.GameWorldApi.GameWorldType;



/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) {	
//		GameWorld gameWorld = GameWorldType.createInstance("com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld");
		GameWorld gameWorld = GameWorldType.createInstance("com.kuleuven.swop.group17.CoolGameWorld.applicationLayer.CoolGameWorld");
		
		
		DomainController dc = new DomainController(gameWorld);		

		//Test push voor de UI-branch
		java.awt.EventQueue.invokeLater(() -> {
	         new CanvasWindow("ButtonClient", dc).show();
	  });

		
		
	}

}
