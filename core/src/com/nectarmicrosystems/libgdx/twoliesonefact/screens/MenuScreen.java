package com.nectarmicrosystems.libgdx.twoliesonefact.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.nectarmicrosystems.libgdx.twoliesonefact.handlers.AssetsHandler;
import com.nectarmicrosystems.libgdx.twoliesonefact.handlers.MenuScreenInputHandler;
import com.nectarmicrosystems.libgdx.twoliesonefact.main.TwoLiesOneFact;
import com.nectarmicrosystems.libgdx.twoliesonefact.renderers.MenuScreenRenderer;
import com.nectarmicrosystems.libgdx.twoliesonefact.uiElements.SimpleButton;

/**
 * Created by oluwatobi on 12/26/2015.
 */
public class MenuScreen implements Screen{
    private MenuScreenRenderer renderer;
    private SimpleButton playButton;
    private SimpleButton menuButton;
    private SimpleButton soundOnButton;
    private SimpleButton soundOffButton;

    public MenuScreen(){
        renderer = new MenuScreenRenderer(this);
        playButton = new SimpleButton(TwoLiesOneFact.GAME_WIDTH + AssetsHandler.playButtonPressedTexture.getWidth(), (TwoLiesOneFact.GAME_HEIGHT/2 - AssetsHandler.playButtonPressedTexture.getHeight()/2) - 100, AssetsHandler.playButtonPressedTexture.getWidth(), AssetsHandler.playButtonPressedTexture.getHeight(), AssetsHandler.playButtonUnpressedTextureRegion, AssetsHandler.playButtonPressedTextureRegion);
        menuButton = new SimpleButton(TwoLiesOneFact.GAME_WIDTH + AssetsHandler.mainMenuPressedTexture.getWidth(), (TwoLiesOneFact.GAME_HEIGHT/2 - AssetsHandler.menuButtonPressedTexture.getHeight()/2) - 230, AssetsHandler.menuButtonPressedTexture.getWidth(), AssetsHandler.menuButtonPressedTexture.getHeight(), AssetsHandler.menuButtonUnpressedTextureRegion, AssetsHandler.menuButtonPressedTextureRegion);
        soundOnButton = new SimpleButton(TwoLiesOneFact.GAME_WIDTH + AssetsHandler.soundOnButton.getWidth(), (TwoLiesOneFact.GAME_HEIGHT/2 - AssetsHandler.soundOnButton.getHeight()/2) - 380, AssetsHandler.soundOnButton.getWidth(), AssetsHandler.soundOnButton.getHeight(), AssetsHandler.soundOnButtonTextureRegion, AssetsHandler.soundOffButtonTextureRegion);
        soundOffButton = new SimpleButton(TwoLiesOneFact.GAME_WIDTH + AssetsHandler.soundOffButton.getWidth(), (TwoLiesOneFact.GAME_HEIGHT/2 - AssetsHandler.soundOffButton.getHeight()/2) - 380, AssetsHandler.soundOffButton.getWidth(), AssetsHandler.soundOffButton.getHeight(), AssetsHandler.soundOffButtonTextureRegion, AssetsHandler.soundOffButtonTextureRegion);
        playButton.setClickable(true);
        menuButton.setClickable(true);
        soundOnButton.setClickable(true);
        soundOnButton.setClickable(true);
        Gdx.input.setInputProcessor(new MenuScreenInputHandler(this));
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        renderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        renderer.dispose();
        AssetsHandler.dispose();
    }

    public SimpleButton[] getButtons(){
        SimpleButton[] buttons = new SimpleButton[4];

        buttons[0] = playButton;
        buttons[1] = menuButton;
        buttons[2] = soundOnButton;
        buttons[3] = soundOffButton;

        return buttons;
    }
}
