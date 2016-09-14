package com.catchthethinggame.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Catcher extends Sprite {

    private Vector2 speed;
    private float speedMagnitude;

    public Catcher() {
        super(new Texture("catcher.png"));
        this.setSize(110, 110);
        this.setPosition(330, 30);
        this.speed = new Vector2(0, 0);
        this.speedMagnitude = 600.0f;
    }

    public void render(SpriteBatch spriteBatch) {
        this.draw(spriteBatch);
    }

    public void update() {
        float delta = Gdx.graphics.getDeltaTime();
        float speedX;

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            speedX = speedMagnitude;
        } else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            speedX = -speedMagnitude;
        } else {
            speedX = 0;
        }

        this.speed.set(speedX, 0);

        float newXPosition = this.getX() + speed.x * delta;
        float newYPosition = this.getY() + speed.y * delta;
        this.setPosition(newXPosition, newYPosition);
    }
}
