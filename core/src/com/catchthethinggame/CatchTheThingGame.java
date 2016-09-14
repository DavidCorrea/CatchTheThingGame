package com.catchthethinggame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.catchthethinggame.screens.GameScreen;

public class CatchTheThingGame extends Game {

	private SpriteBatch spriteBatch;

	@Override
	public void create () {
		this.spriteBatch = new SpriteBatch();
		this.setScreen(new GameScreen(this.spriteBatch));
	}
}
