package com.catchthethinggame.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    public static Texture BALL = new Texture("ball.png");
    public static Texture CATCHER = new Texture("catcherSheet.png");
    public static Texture CATCHER_SHADOW = new Texture("catcherShadow.png");
    public static Texture BACKGROUND = new Texture("background.jpg");
    public static Texture BACKGROUND_DETAIL = new Texture("background-detail.png");

    public static Sound CATCH_SOUND = Gdx.audio.newSound(Gdx.files.internal("catch.wav"));
    // public static Music BACKGROUND_MUSIC = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.wav"));

}
