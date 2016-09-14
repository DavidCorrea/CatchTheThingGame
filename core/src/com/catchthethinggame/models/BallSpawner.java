package com.catchthethinggame.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.List;
import java.util.Random;

public class BallSpawner extends Sprite {

    private double cooldown;
    private double timer;
    private List<Ball> balls;

    public BallSpawner(List<Ball> balls) {
        this.generateCooldown();
        this.setPosition(0, 550);
        this.resetTimer();
        this.balls = balls;
    }

    public void update() {
        if(this.timer >= this.cooldown) {
            this.spawnBall();
            this.resetTimer();
            this.generateCooldown();
        } else {
            this.updateTimer();
        }
    }

    private void spawnBall() {
        this.balls.add(new Ball(this.getX(), this.getY()));
    }

    private void generateCooldown() {
        this.cooldown = (new Random().nextFloat() * 0.5) + 0.2;
    }

    private void resetTimer() {
        this.timer = 0.0;
    }

    private void updateTimer() {
        this.timer += Gdx.graphics.getDeltaTime();
    }

}
