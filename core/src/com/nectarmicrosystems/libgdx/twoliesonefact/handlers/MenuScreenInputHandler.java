package com.nectarmicrosystems.libgdx.twoliesonefact.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.nectarmicrosystems.libgdx.twoliesonefact.main.TwoLiesOneFact;
import com.nectarmicrosystems.libgdx.twoliesonefact.screens.GameScreen;
import com.nectarmicrosystems.libgdx.twoliesonefact.screens.MenuScreen;
import com.nectarmicrosystems.libgdx.twoliesonefact.screens.OptionsScreen;
import com.nectarmicrosystems.libgdx.twoliesonefact.uiElements.SimpleButton;

/**
 * Created by oluwatobi on 12/26/2015.
 */
public class MenuScreenInputHandler extends InputHandler {
    private MenuScreen screen;

    public MenuScreenInputHandler(MenuScreen screen){
        this.screen = screen;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.BACK) {
            AssetsHandler.playSound();
            GameSettings.save();
            Gdx.app.exit();
        }

        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = (int)(screenX / TwoLiesOneFact.SCALE);
        screenY = (int)(screenY / TwoLiesOneFact.SCALE);
        screenY = (int)(TwoLiesOneFact.GAME_HEIGHT - screenY);
        SimpleButton[] buttons = screen.getButtons();

        //play button
        if(buttons[0].isClicked(screenX, screenY) && buttons[0].isClickable()){
            buttons[0].isTouchDown(screenX, screenY);
            return true;
        }

        //menu button
        if(buttons[1].isClicked(screenX, screenY) && buttons[1].isClickable()){
            buttons[1].isTouchDown(screenX, screenY);
            return true;
        }

        //sound on button
        if(buttons[2].isClicked(screenX, screenY) && buttons[2].isClickable()){
            buttons[2].isTouchDown(screenX, screenY);
            return true;
        }

        //sound off button
        if(buttons[3].isClicked(screenX, screenY) && buttons[3].isClickable()){
            buttons[3].isTouchDown(screenX, screenY);
            return true;
        }

        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = (int)(screenX / TwoLiesOneFact.SCALE);
        screenY = (int)(screenY / TwoLiesOneFact.SCALE);
        screenY = (int)(TwoLiesOneFact.GAME_HEIGHT - screenY);
        SimpleButton[] buttons = screen.getButtons();

        //play button
        if(buttons[0].isClicked(screenX, screenY) && buttons[0].isClickable()){
            AssetsHandler.playSound();
            buttons[0].isTouchUp(screenX, screenY);
            TwoLiesOneFact.GAME.setScreen(new GameScreen());
            return true;
        }

        //menu button
        if(buttons[1].isClicked(screenX, screenY) && buttons[1].isClickable()){
            AssetsHandler.playSound();
            buttons[1].isTouchUp(screenX, screenY);
            TwoLiesOneFact.GAME.setScreen(new OptionsScreen());
            return true;
        }

        //sound on button
        if(buttons[2].isClicked(screenX, screenY) && buttons[2].isClickable()){
            buttons[2].isTouchUp(screenX, screenY);
            AssetsHandler.playSound();

            if (GameSettings.soundEnabled())
                GameSettings.setSound(false);
            else
                GameSettings.setSound(true);

            return true;
        }

        //sound off button
        if(buttons[3].isClicked(screenX, screenY) && buttons[3].isClickable()){
            buttons[3].isTouchUp(screenX, screenY);
            AssetsHandler.playSound();
            return true;
        }

        return super.touchUp(screenX, screenY, pointer, button);
    }
}
