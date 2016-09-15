package com.catchthethinggame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.catchthethinggame.helpers.Assets;
import com.catchthethinggame.helpers.Events;
import com.catchthethinggame.models.Score;
import com.catchthethinggame.models.Ball;
import com.catchthethinggame.models.BallSpawner;
import com.catchthethinggame.models.Catcher;
import com.catchthethinggame.models.CatcherShadow;

import java.util.ArrayList;
import java.util.List;

public class GameScreen extends ScreenAdapter {

    private final Texture background;
    private final BitmapFont bitMapFont;
    private final Texture backgroundDetail;
    private SpriteBatch spriteBatch;
    private CatcherShadow catcherShadow;
    private Catcher catcher;
    private BallSpawner ballSpawner;
    private List<Ball> balls;
    private Score score;
    private float rot;

    public GameScreen(SpriteBatch spriteBatch) {
        Gdx.gl.glClearColor(0, 0, 0, 1);

        this.spriteBatch = spriteBatch;
        this.catcher = new Catcher();
        this.catcherShadow = new CatcherShadow(this.catcher);
        this.balls = new ArrayList<Ball>();
        this.ballSpawner = new BallSpawner(this.balls);
        this.background = Assets.BACKGROUND;
        this.backgroundDetail = Assets.BACKGROUND_DETAIL;
        this.bitMapFont = new BitmapFont();
        this.score = new Score();
        this.rot = 0;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.update();
        this.renderComponents();
    }

    @Override
    public void dispose() {
        this.spriteBatch.dispose();
    }

    private void update() {
        this.ballSpawner.update();
        this.catcher.update();
        this.updateBalls();
    }

    private void renderComponents() {
        this.spriteBatch.begin();
        this.renderBackground();
        this.catcherShadow.render(this.spriteBatch);
        this.renderBalls();
        this.catcher.render(this.spriteBatch);
        this.renderText();
        this.changeColorIfSlowMotion();
        this.spriteBatch.end();
    }

    private void updateBalls(){
        for (Ball ball : this.balls) {
            ball.update(this.catcher, this.score);
        }
    }

    private void renderBackground() {
        this.spriteBatch.draw(this.background, 0, 0, 800, 600);
        this.spriteBatch.draw(new TextureRegion(this.backgroundDetail), 0, 0, 400, 400, 800, 800, 1, 1, rot);
        if(Events.isSlowMotionActive())
            rot += 0.0625;
        else
            rot += 0.25;
    }

    private void renderBalls() {
        for (Ball ball : this.balls) {
            ball.render(this.spriteBatch);
        }
    }

    private void renderText() {
        this.bitMapFont.draw(this.spriteBatch, "Current Score: " + this.score.getActual(), 10, 20);
        this.bitMapFont.draw(this.spriteBatch, "Press SHIFT to slow things a bit", 580, 20);
        this.bitMapFont.draw(this.spriteBatch, "Press SPACE to move faster", 580, 40);
    }

    private void changeColorIfSlowMotion() {
        if(Events.isSlowMotionActive())
            this.spriteBatch.setColor(Color.GRAY.toFloatBits());
        else
            this.spriteBatch.setColor(Color.WHITE.toFloatBits());
    }
}
