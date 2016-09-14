package com.catchthethinggame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.catchthethinggame.helpers.Score;
import com.catchthethinggame.models.Ball;
import com.catchthethinggame.models.BallSpawner;
import com.catchthethinggame.models.Catcher;

import java.util.ArrayList;
import java.util.List;

public class GameScreen extends ScreenAdapter {

    private final Texture background;
    private final BitmapFont bitMapFont;
    private SpriteBatch spriteBatch;
    private Catcher catcher;
    private BallSpawner ballSpawner;
    private List<Ball> balls;
    private Score score;
    private OrthographicCamera camera;

    public GameScreen(SpriteBatch spriteBatch) {
        Gdx.gl.glClearColor(0, 0, 0, 1);

        this.spriteBatch = spriteBatch;
        this.initializeCamera();

        this.catcher = new Catcher();
        this.balls = new ArrayList<Ball>();
        this.ballSpawner = new BallSpawner(this.balls);
        this.background = new Texture("background.jpg");
        this.bitMapFont = new BitmapFont();
        this.score = new Score();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.update();

        this.spriteBatch.begin();
        this.spriteBatch.draw(this.background, 0, 0);
        for (Ball ball : this.balls) {
            ball.render(this.spriteBatch);
        }
        this.catcher.render(this.spriteBatch);
        this.bitMapFont.draw(this.spriteBatch, "Current Score: " + this.score.getActual(), 10, 20);
        this.bitMapFont.draw(this.spriteBatch, "Press SPACE to slow things a bit", 580, 20);

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
            this.spriteBatch.setColor(Color.BLACK);
        else
            this.spriteBatch.setColor(Color.WHITE.toFloatBits());

        this.spriteBatch.end();
    }

    @Override
    public void dispose() {
        this.spriteBatch.dispose();
    }

    private void initializeCamera() {
        this.camera = new OrthographicCamera(800, 600);
    }

    private void update() {
        this.ballSpawner.update();
        this.catcher.update();
        for (Ball ball : this.balls) {
            ball.update(this.catcher, this.score);
        }
    }
}
