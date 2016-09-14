package com.catchthethinggame.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Catcher extends Sprite {

    private final Rectangle catchZone;
    private Vector2 speed;
    private float speedMagnitude;
    private ShapeRenderer shapeRenderer;

    public Catcher() {
        super(new Texture("catcher.png"));
        this.setSize(180, 80);
        this.setPosition(330, 30);
        this.speed = new Vector2(0, 0);
        this.speedMagnitude = 600.0f;
        this.catchZone = new Rectangle(this.getX() + 40, this.getY(), 100, 20);
        this.shapeRenderer = new ShapeRenderer();
    }

    public void render(SpriteBatch spriteBatch) {
        this.draw(spriteBatch);
        spriteBatch.end();
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        this.shapeRenderer.setColor(Color.YELLOW);
        this.shapeRenderer.rect(this.getX() + 40, this.getY(), 100, 20);
        this.shapeRenderer.end();
        spriteBatch.begin();
    }

    public void update() {
        float speedX;

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            speedX = speedMagnitude;
        } else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            speedX = -speedMagnitude;
        } else {
            speedX = 0;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
            speedX = speedX * 2;

        this.speed.set(speedX, 0);

        float delta = Gdx.graphics.getDeltaTime();
        float newXPosition = this.getX() + speed.x * delta;
        float newYPosition = this.getY() + speed.y * delta;
        this.setPosition(newXPosition, newYPosition);

        this.catchZone.setX(newXPosition + 25);
    }

    public boolean canCatch(Rectangle boundingRectangle) {
        return boundingRectangle.overlaps(this.catchZone);
    }
}
