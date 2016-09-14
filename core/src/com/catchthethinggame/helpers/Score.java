package com.catchthethinggame.helpers;

public class Score {

    private int score;

    public Score() {
        this.score = 0;
    }

    public void increase() {
        this.score += 1;
    }

    public int getActual() {
        return this.score;
    }
}
