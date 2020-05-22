package com.kuleuven.swop.group17.ButtonClient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
@RunWith(MockitoJUnitRunner.class)
public class testMain {
	@Test
	public void testMain() {
		String[] args = new String[2];
		args[0] = "com.kuleuven.swop.group17.CoolGameWorld.applicationLayer.CoolGameWorld";
		App.main(args);
	}
}
