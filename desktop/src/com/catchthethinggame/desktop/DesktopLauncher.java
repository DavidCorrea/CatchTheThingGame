package com.catchthethinggame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.catchthethinggame.CatchTheThingGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		new LwjglApplication(new CatchTheThingGame(), "Catch the thing", 800, 600);
	}
}
