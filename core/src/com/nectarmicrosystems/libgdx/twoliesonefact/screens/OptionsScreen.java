package com.nectarmicrosystems.libgdx.twoliesonefact.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.nectarmicrosystems.libgdx.twoliesonefact.handlers.AssetsHandler;
import com.nectarmicrosystems.libgdx.twoliesonefact.handlers.MenuScreenInputHandler;
import com.nectarmicrosystems.libgdx.twoliesonefact.handlers.OptionsScreenInputHandler;
import com.nectarmicrosystems.libgdx.twoliesonefact.main.TwoLiesOneFact;
import com.nectarmicrosystems.libgdx.twoliesonefact.renderers.OptionsScreenRenderer;
import com.nectarmicrosystems.libgdx.twoliesonefact.uiElements.SimpleButton;

/**
 * Created by oluwatobi on 2/15/2016.
 */
public class OptionsScreen implements Screen {

    private OptionsScreenRenderer renderer;
    private SimpleButton statisticsButton;

    public OptionsScreen(){
        renderer = new OptionsScreenRenderer(this);
        statisticsButton = new SimpleButton(100, TwoLiesOneFact.GAME_HEIGHT - (AssetsHandler.statisticsUnpressedTexture.getHeight() + 100), AssetsHandler.statisticsUnpressedTexture.getWidth(), AssetsHandler.statisticsUnpressedTexture.getHeight(), AssetsHandler.statisticsUnpressedTextureRegion, AssetsHandler.statisticsPressedTextureRegion);
        Gdx.input.setInputProcessor(new OptionsScreenInputHandler(this));
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
        SimpleButton[] buttons = new SimpleButton[1];

        buttons[0] = statisticsButton;

        return buttons;
    }
}
