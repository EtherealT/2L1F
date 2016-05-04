package com.nectarmicrosystems.libgdx.twoliesonefact.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nectarmicrosystems.libgdx.twoliesonefact.handlers.AssetsHandler;
import com.nectarmicrosystems.libgdx.twoliesonefact.main.TwoLiesOneFact;
import com.nectarmicrosystems.libgdx.twoliesonefact.screens.OptionsScreen;
import com.nectarmicrosystems.libgdx.twoliesonefact.uiElements.SimpleButton;

/**
 * Created by oluwatobi on 2/15/2016.
 */
public class OptionsScreenRenderer implements Renderer {

    private SpriteBatch batcher;
    OrthographicCamera camera;
    private OptionsScreen screen;
    private Sprite bgSprite;

    public OptionsScreenRenderer(OptionsScreen screen) {
        this.screen = screen;
        batcher = new SpriteBatch();
        bgSprite = new Sprite(AssetsHandler.backgroundTexture);
        camera = new OrthographicCamera();

        camera.setToOrtho(false, TwoLiesOneFact.GAME_WIDTH, TwoLiesOneFact.GAME_HEIGHT);
        batcher.setProjectionMatrix(camera.combined);

        bgSprite.setSize(TwoLiesOneFact.GAME_WIDTH, TwoLiesOneFact.GAME_HEIGHT);
        bgSprite.setPosition(0, 0);
    }

    @Override
    public void render(float delta) {
        camera.update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batcher.setProjectionMatrix(camera.combined);
        batcher.begin();
        bgSprite.draw(batcher);
        batcher.enableBlending();
        renderButtons();
        batcher.disableBlending();
        batcher.end();
    }

    private void renderButtons(){
        SimpleButton[] buttons = screen.getButtons();

        for(int i = 0; i < buttons.length; i++)
            buttons[i].draw(batcher);
    }

    @Override
    public void dispose() {

    }
}
