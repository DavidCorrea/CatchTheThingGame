package com.catchthethinggame.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.catchthethinggame.helpers.Score;

import java.util.Random;

public class Ball extends Sprite {

    private boolean catched;
    private Vector2 speed;
    private Vector2 acceleration;

    public Ball(float spawnerXCoordinate, float spawnerYCoordinate) {
        super(new Texture("ball.png"));
        this.setSize(50, 50);
        this.setPosition(spawnerXCoordinate, spawnerYCoordinate);
        this.catched = false;
        this.speed = new Vector2(new Random().nextInt(800), -new Random().nextInt(800));
        this.acceleration = new Vector2(0, -1000);
    }

    public void update(Catcher catcher, Score score) {
        if(isCatchedBy(catcher)) {
            this.catched = true;
            score.increase();
        }

        float delta = Gdx.graphics.getDeltaTime();

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
            delta = delta / 4;

        float newXPosition = this.getX() + speed.x * delta;
        float newYPosition = this.getY() + speed.y * delta;
        this.setPosition(newXPosition, newYPosition);

        float newXAcceleration = this.acceleration.x * delta;
        float newYAcceleration = this.acceleration.y * delta;
        this.speed.add(newXAcceleration, newYAcceleration);
    }

    public void render(SpriteBatch spriteBatch) {
        if(!catched) {
            this.setOrigin(25, 25);
            this.rotate(1);
            this.draw(spriteBatch);
        }
    }

    private boolean isCatchedBy(Catcher catcher) {
        return catcher.canCatch(this.getBoundingRectangle());
    }
}
