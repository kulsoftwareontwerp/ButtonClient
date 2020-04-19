package com.kuleuven.swop.group17.ButtonClient;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;


import org.junit.runners.Suite.SuiteClasses;
import com.kuleuven.swop.group17.ButtonClient.ApplicationLayer.ApplicationLayer;
import com.kuleuven.swop.group17.ButtonClient.Command.CommandLayer;
import com.kuleuven.swop.group17.ButtonClient.guiLayer.guiLayer;
import com.kuleuven.swop.group17.ButtonClient.types.types;

@RunWith(Suite.class)
@SuiteClasses({ApplicationLayer.class,CommandLayer.class,guiLayer.class})//,types.class
public class AllTests {

}
