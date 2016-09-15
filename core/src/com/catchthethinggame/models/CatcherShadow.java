package com.catchthethinggame.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.catchthethinggame.helpers.Assets;

public class CatcherShadow extends Sprite {

    private Catcher catcher;

    public CatcherShadow(Catcher catcher) {
        super(Assets.CATCHER_SHADOW);
        this.setSize(180, 80);
        this.catcher = catcher;
    }

    public void render(SpriteBatch spriteBatch) {
        this.setPosition(this.catcher.getX(), this.catcher.getY());
        this.draw(spriteBatch);
    }

}
