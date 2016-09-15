package com.catchthethinggame.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.catchthethinggame.helpers.Assets;
import com.catchthethinggame.helpers.Events;

public class Catcher extends Sprite {

    private static final int FRAME_COLS = 4;
    private static final int FRAME_ROWS = 1;

    private final Rectangle catchZone;
    private Vector2 speed;
    private float speedMagnitude;
    private float animationTime;
    private Animation animation;
    private Sound catchSound;

    public Catcher() {
        this.animationTime = 0f;
        this.setSize(180, 80);
        this.setPosition(330, 30);
        this.speed = new Vector2(0, 0);
        this.speedMagnitude = 600.0f;
        this.catchZone = new Rectangle(this.getX() + 40, this.getY(), 100, 20);
        this.animation = new Animation(0.070f, this.prepareAnimation());
        this.catchSound = Assets.CATCH_SOUND;
    }

    public void render(SpriteBatch spriteBatch) {
        this.setRegion(this.animation.getKeyFrame(this.animationTime, true));
        this.draw(spriteBatch);
    }

    public void update() {
        this.changeSpeed();
        this.changePosition();
    }

    public boolean canCatch(Rectangle boundingRectangle) {
        return boundingRectangle.overlaps(this.catchZone);
    }

    private void changeSpeed() {
        float speedX;

        if(Events.isCatcherMovingRight()) {
            speedX = speedMagnitude;
        } else if(Events.isCatcherMovingLeft()) {
            speedX = -speedMagnitude;
        } else {
            speedX = 0;
        }

        if(Events.isFastCatcherActive())
            speedX = speedX * 2;

        this.speed.set(speedX, 0);
    }

    private void changePosition() {
        float delta = Gdx.graphics.getDeltaTime();

        float newXPosition = this.getX() + speed.x * delta;
        float newYPosition = this.getY() + speed.y * delta;
        this.setPosition(newXPosition, newYPosition);
        this.catchZone.setX(newXPosition + 40);
    }

    private TextureRegion[] prepareAnimation() {
        Texture walkSheet;
        TextureRegion[] walkFrames;
        walkSheet = Assets.CATCHER;

        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);

        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        return walkFrames;
    }

    public void catched() {
        this.animationTime += Gdx.graphics.getDeltaTime();
        this.catchSound.play();
    }
}
