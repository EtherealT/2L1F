package com.nectarmicrosystems.libgdx.twoliesonefact.screens;

import com.badlogic.gdx.Screen;
import com.nectarmicrosystems.libgdx.twoliesonefact.handlers.AssetsHandler;
import com.nectarmicrosystems.libgdx.twoliesonefact.renderers.SplashScreenRenderer;

/**
 * Created by oluwatobi on 12/26/2015.
 */
public class SplashScreen implements Screen {
    private SplashScreenRenderer renderer;

    public SplashScreen(){
        this.renderer = new SplashScreenRenderer(this);
    }

    @Override
    public void show() {
        renderer.show();
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

}
