package com.catchthethinggame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.catchthethinggame.CatchTheThingGame;
import com.catchthethinggame.desktop.config.Settings;

public class DesktopLauncher {
	public static void main (String[] arg) {
		new LwjglApplication(new CatchTheThingGame(), "Catch the thing", Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
	}
}
