package com.catchthethinggame.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.catchthethinggame.helpers.Assets;
import com.catchthethinggame.helpers.Events;

public class Catcher extends Sprite {

    private final Rectangle catchZone;
    private Vector2 speed;
    private float speedMagnitude;

    public Catcher() {
        super(Assets.CATCHER);
        this.setSize(180, 80);
        this.setPosition(330, 30);
        this.speed = new Vector2(0, 0);
        this.speedMagnitude = 600.0f;
        this.catchZone = new Rectangle(this.getX() + 40, this.getY(), 100, 20);
    }

    public void render(SpriteBatch spriteBatch) {
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
}
