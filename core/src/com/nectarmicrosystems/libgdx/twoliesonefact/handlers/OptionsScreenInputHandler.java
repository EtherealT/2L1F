package com.nectarmicrosystems.libgdx.twoliesonefact.handlers;

import com.badlogic.gdx.Input;
import com.nectarmicrosystems.libgdx.twoliesonefact.main.TwoLiesOneFact;
import com.nectarmicrosystems.libgdx.twoliesonefact.screens.MenuScreen;
import com.nectarmicrosystems.libgdx.twoliesonefact.screens.OptionsScreen;

/**
 * Created by oluwatobi on 2/15/2016.
 */
public class OptionsScreenInputHandler extends InputHandler {

    private OptionsScreen screen;

    public OptionsScreenInputHandler(OptionsScreen screen) {
        this.screen = screen;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.BACK) {
            AssetsHandler.playSound();
            TwoLiesOneFact.GAME.setScreen(new MenuScreen());
        }

        return super.keyUp(keycode);
    }
}
