package com.catchthethinggame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.catchthethinggame.models.BallSpawner;
import com.catchthethinggame.models.Catcher;

public class GameScreen extends ScreenAdapter {

    private SpriteBatch spriteBatch;
    private Catcher catcher;
    private BallSpawner ballSpawner;
    private OrthographicCamera camera;

    public GameScreen(SpriteBatch spriteBatch) {
        Gdx.gl.glClearColor(0, 0, 0, 1);

        this.spriteBatch = spriteBatch;
        this.initializeCamera();

        this.catcher = new Catcher();
        this.ballSpawner = new BallSpawner();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.catcher.update();

        this.spriteBatch.begin();
        this.catcher.render(this.spriteBatch);
        this.spriteBatch.end();
    }

    @Override
    public void dispose() {
        this.spriteBatch.dispose();
    }

    private void initializeCamera() {
        this.camera = new OrthographicCamera(800, 600);
    }
}
