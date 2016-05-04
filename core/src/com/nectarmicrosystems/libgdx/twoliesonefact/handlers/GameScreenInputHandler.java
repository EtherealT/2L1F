package com.nectarmicrosystems.libgdx.twoliesonefact.handlers;

import com.badlogic.gdx.Input;
import com.nectarmicrosystems.libgdx.twoliesonefact.main.Bonus;
import com.nectarmicrosystems.libgdx.twoliesonefact.main.GameState;
import com.nectarmicrosystems.libgdx.twoliesonefact.main.TwoLiesOneFact;
import com.nectarmicrosystems.libgdx.twoliesonefact.screens.GameScreen;
import com.nectarmicrosystems.libgdx.twoliesonefact.screens.MenuScreen;
import com.nectarmicrosystems.libgdx.twoliesonefact.uiElements.SimpleButton;

/**
 * Created by oluwatobi on 12/26/2015.
 */
public class GameScreenInputHandler extends InputHandler {

    private GameScreen screen;

    public GameScreenInputHandler(GameScreen screen){
        this.screen = screen;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.BACK && screen.getGameSession().getGameState() == GameState.Ready) {
            AssetsHandler.playSound();
            TwoLiesOneFact.GAME.setScreen(new MenuScreen());
            return true;
        }

        if(keycode == Input.Keys.BACK && screen.getGameSession().getGameState() == GameState.Running) {
            AssetsHandler.playSound();
            screen.getGameSession().pause();
            screen.getGameSession().setGameState(GameState.Paused);
            return true;
        }

        if(keycode == Input.Keys.BACK && screen.getGameSession().getGameState() == GameState.Paused){
            AssetsHandler.playSound();
            screen.getGameSession().resume();
            screen.getGameSession().setGameState(GameState.Running);
            return true;
        }

        if(keycode == Input.Keys.BACK && screen.getGameSession().getGameState() == GameState.Ended){
            AssetsHandler.playSound();
            TwoLiesOneFact.GAME.setScreen(new MenuScreen());
            return true;
        }

        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = (int)(screenX / TwoLiesOneFact.SCALE);
        screenY = (int)(screenY / TwoLiesOneFact.SCALE);
        screenY = (int)(TwoLiesOneFact.GAME_HEIGHT - screenY);
        SimpleButton[] buttons = screen.getButtons();
        Bonus[] bonuses = screen.getGameSession().getBonuses();

        if(screen.getGameSession().getGameState() == GameState.Running){
            if(buttons[0].isClicked(screenX, screenY) && buttons[0].isClickable()){
                buttons[0].isTouchDown(screenX, screenY);
                return true;
            }

            if(buttons[1].isClicked(screenX, screenY) && buttons[1].isClickable()){
                buttons[1].isTouchDown(screenX, screenY);
                return true;
            }

            if(buttons[2].isClicked(screenX, screenY) && buttons[2].isClickable()){
                buttons[2].isTouchDown(screenX, screenY);
                return true;
            }

            if(buttons[3].isClicked(screenX, screenY) && buttons[3].isClickable()){
                buttons[3].isTouchDown(screenX, screenY);
                return true;
            }

            if(bonuses[0].isClicked(screenX, screenY)){
                bonuses[0].isTouchDown(screenX, screenY);
                return true;
            }

            if(bonuses[1].isClicked(screenX, screenY)){
                bonuses[1].isTouchDown(screenX, screenY);
                return true;
            }
        }

        if(screen.getGameSession().getGameState() == GameState.Paused){
            if(buttons[4].isClicked(screenX, screenY) && buttons[4].isClickable()){
                buttons[4].isTouchDown(screenX, screenY);
                return true;
            }

            if(buttons[5].isClicked(screenX, screenY) && buttons[5].isClickable()){
                buttons[5].isTouchDown(screenX, screenY);
                return true;
            }
        }

        if(screen.getGameSession().getGameState() == GameState.Ended){
            if(buttons[6].isClicked(screenX, screenY) && buttons[6].isClickable()){
                buttons[6].isTouchDown(screenX, screenY);
                return true;
            }
        }

        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = (int)(screenX / TwoLiesOneFact.SCALE);
        screenY = (int)(screenY / TwoLiesOneFact.SCALE);
        screenY = (int)(TwoLiesOneFact.GAME_HEIGHT - screenY);
        SimpleButton[] buttons = screen.getButtons();
        Bonus[] bonuses = screen.getGameSession().getBonuses();
        
        if(screen.getGameSession().getGameState() == GameState.Ready){
            AssetsHandler.playSound();
            screen.newQuestion();
            screen.getGameSession().setGameState(GameState.Running);
            return true;
        }

        if(screen.getGameSession().getGameState() == GameState.Running){
            if(buttons[0].isClicked(screenX, screenY) && buttons[0].isClickable()){
                screen.play(0);
                buttons[0].isTouchUp(screenX, screenY);
                return true;
            }

            if(buttons[1].isClicked(screenX, screenY) && buttons[1].isClickable()){
                screen.play(1);
                buttons[1].isTouchUp(screenX, screenY);
                return true;
            }

            if(buttons[2].isClicked(screenX, screenY) && buttons[2].isClickable()){
                screen.play(2);
                buttons[2].isTouchUp(screenX, screenY);
                return true;
            }

            if(buttons[3].isClicked(screenX, screenY) && buttons[3].isClickable()){
                AssetsHandler.playSound();
                buttons[3].isTouchUp(screenX, screenY);
                screen.getGameSession().pause();
                screen.getGameSession().setGameState(GameState.Paused);
                return true;
            }

            if(bonuses[0].isClicked(screenX, screenY)){
                if(!screen.getGameSession().getBonuses()[0].isUsed()){
                    AssetsHandler.playSound();
                    screen.executeTimeBonus();
                    screen.getGameSession().getBonuses()[0].setUsed(true);
                }
                bonuses[0].isTouchUp(screenX, screenY);
                return true;
            }

            if(bonuses[1].isClicked(screenX, screenY)){
                if(!screen.getGameSession().getBonuses()[1].isUsed()){
                    AssetsHandler.playSound();
                    screen.executeEraseOneBonus();
                    screen.getGameSession().getBonuses()[1].setUsed(true);
                }
                bonuses[1].isTouchUp(screenX, screenY);
                return true;
            }
        }

        if(screen.getGameSession().getGameState() == GameState.Paused){
            if(buttons[4].isClicked(screenX, screenY) && buttons[0].isClickable()){
                AssetsHandler.playSound();
                buttons[4].isTouchUp(screenX, screenY);
                screen.getGameSession().resume();
                screen.getGameSession().setGameState(GameState.Running);
                return true;
            }

            if(buttons[5].isClicked(screenX, screenY) && buttons[5].isClickable()){
                AssetsHandler.playSound();
                buttons[5].isTouchUp(screenX, screenY);
                screen.getGameSession().end();
                TwoLiesOneFact.GAME.setScreen(new MenuScreen());
                return true;
            }
        }

        if(screen.getGameSession().getGameState() == GameState.Ended){
            if(buttons[6].isClicked(screenX, screenY) && buttons[6].isClickable()){
                AssetsHandler.playSound();
                buttons[6].isTouchUp(screenX, screenY);
                screen.getGameSession().end();
                TwoLiesOneFact.GAME.setScreen(new GameScreen());
                return true;
            }
        }

        return super.touchUp(screenX, screenY, pointer, button);
    }
}
