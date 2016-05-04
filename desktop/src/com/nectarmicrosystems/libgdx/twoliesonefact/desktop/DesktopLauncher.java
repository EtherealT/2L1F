package com.nectarmicrosystems.libgdx.twoliesonefact.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nectarmicrosystems.libgdx.twoliesonefact.main.TwoLiesOneFact;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 320;
		config.height = 520;
		config.resizable = false;
		config.title = "2 Lies 1 Fact";
		new LwjglApplication(new TwoLiesOneFact(), config);
	}
}
