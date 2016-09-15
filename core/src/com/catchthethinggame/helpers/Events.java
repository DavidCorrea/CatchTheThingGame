package com.catchthethinggame.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Events {

    public static boolean isCatcherMovingRight() {
        return Gdx.input.isKeyPressed(Input.Keys.RIGHT);
    }

    public static boolean isCatcherMovingLeft() {
        return Gdx.input.isKeyPressed(Input.Keys.LEFT);
    }

    public static boolean isSlowMotionActive() {
        return Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
    }

    public static boolean isFastCatcherActive() {
        return Gdx.input.isKeyPressed(Input.Keys.SPACE);
    }
}
