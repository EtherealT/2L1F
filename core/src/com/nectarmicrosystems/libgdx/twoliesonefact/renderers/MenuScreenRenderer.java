package com.nectarmicrosystems.libgdx.twoliesonefact.renderers;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nectarmicrosystems.libgdx.twoliesonefact.handlers.AssetsHandler;
import com.nectarmicrosystems.libgdx.twoliesonefact.handlers.GameSettings;
import com.nectarmicrosystems.libgdx.twoliesonefact.main.TwoLiesOneFact;
import com.nectarmicrosystems.libgdx.twoliesonefact.screens.MenuScreen;
import com.nectarmicrosystems.libgdx.twoliesonefact.tweenAccessors.SimpleButtonAccessor;
import com.nectarmicrosystems.libgdx.twoliesonefact.tweenAccessors.SpriteAccessor;
import com.nectarmicrosystems.libgdx.twoliesonefact.uiElements.SimpleButton;

/**
 * Created by oluwatobi on 12/26/2015.
 */
public class MenuScreenRenderer implements Renderer {

    private SpriteBatch batcher;
    private TweenManager manager;
    OrthographicCamera camera;
    private Sprite bgSprite;
    private Sprite menuLogoSprite;
    private MenuScreen screen;

    public MenuScreenRenderer(MenuScreen screen){
        this.screen = screen;
        batcher = new SpriteBatch();
        bgSprite = new Sprite(AssetsHandler.backgroundTexture);
        menuLogoSprite = new Sprite(AssetsHandler.menuLogoTexture);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, TwoLiesOneFact.GAME_WIDTH, TwoLiesOneFact.GAME_HEIGHT);
        batcher.setProjectionMatrix(camera.combined);

        bgSprite.setSize(TwoLiesOneFact.GAME_WIDTH, TwoLiesOneFact.GAME_HEIGHT);
        bgSprite.setPosition(0, 0);
        menuLogoSprite.setPosition(TwoLiesOneFact.GAME_WIDTH/2 - (AssetsHandler.menuLogoTexture.getWidth()/2), TwoLiesOneFact.GAME_HEIGHT + AssetsHandler.menuLogoTexture.getHeight());
        setupLogoTween();
    }
    
    @Override
    public void render(float delta) {
        camera.update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        manager.update(delta);
        batcher.setProjectionMatrix(camera.combined);
        batcher.begin();
        bgSprite.draw(batcher);
        batcher.enableBlending();
        renderButtons();
        menuLogoSprite.draw(batcher);
        batcher.disableBlending();
        batcher.end();
    }

    private void renderButtons(){
        SimpleButton[] buttons = screen.getButtons();

        for(int i = 0; i < 2; i++) {
            //don't render menu button
            if(i == 1)
                continue;

            buttons[i].draw(batcher);
        }

        if(GameSettings.soundEnabled())
            buttons[2].draw(batcher);
        else
            buttons[3].draw(batcher);
    }

    private void setupLogoTween() {
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        manager = new TweenManager();

        TweenCallback cb = new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                setupButtonTweens();
                screen.getButtons()[0].updateBounds(TwoLiesOneFact.GAME_WIDTH/2 - (AssetsHandler.playButtonPressedTexture.getWidth()/2), screen.getButtons()[0].getY(), AssetsHandler.playButtonPressedTexture.getWidth(), AssetsHandler.playButtonPressedTexture.getHeight());
                screen.getButtons()[1].updateBounds(TwoLiesOneFact.GAME_WIDTH/2 - (AssetsHandler.menuButtonPressedTexture.getWidth()/2), screen.getButtons()[1].getY(), AssetsHandler.menuButtonPressedTexture.getWidth(), AssetsHandler.menuButtonPressedTexture.getHeight());
                screen.getButtons()[2].updateBounds(TwoLiesOneFact.GAME_WIDTH/2 - (AssetsHandler.soundOnButton.getWidth()/2), screen.getButtons()[2].getY(), AssetsHandler.soundOnButton.getWidth(), AssetsHandler.soundOnButton.getHeight());
                screen.getButtons()[3].updateBounds(TwoLiesOneFact.GAME_WIDTH/2 - (AssetsHandler.soundOffButton.getWidth()/2), screen.getButtons()[3].getY(), AssetsHandler.soundOffButton.getWidth(), AssetsHandler.soundOffButton.getHeight());
                
                if(TwoLiesOneFact.internetAvailable())
                    TwoLiesOneFact.adsController.showBannerAd();
            }
        };

        Tween.to(menuLogoSprite, SpriteAccessor.POSITION_Y, .5f)
                .target(TwoLiesOneFact.GAME_HEIGHT/2 - (AssetsHandler.menuLogoTexture.getHeight()/2 - 400))
                .ease(TweenEquations.easeInOutQuint)
                .setCallback(cb)
                .setCallbackTriggers(TweenCallback.COMPLETE)
                .start(manager);

    }

    private void setupButtonTweens(){
        Tween.registerAccessor(SimpleButton.class, new SimpleButtonAccessor());

        Tween.to(screen.getButtons()[0], SimpleButtonAccessor.POSITION_X, .5f)
                .target(TwoLiesOneFact.GAME_WIDTH/2 - (AssetsHandler.playButtonPressedTexture.getWidth()/2))
                .ease(TweenEquations.easeInOutQuint)
                .start(manager);

        Tween.to(screen.getButtons()[1], SimpleButtonAccessor.POSITION_X, .5f)
                .target(TwoLiesOneFact.GAME_WIDTH/2 - (AssetsHandler.menuButtonPressedTexture.getWidth()/2))
                .ease(TweenEquations.easeInOutQuint)
                .start(manager);

        Tween.to(screen.getButtons()[2], SimpleButtonAccessor.POSITION_X, .5f)
                .target(TwoLiesOneFact.GAME_WIDTH/2 - (AssetsHandler.soundOnButton.getWidth()/2))
                .ease(TweenEquations.easeInOutQuint)
                .start(manager);

        Tween.to(screen.getButtons()[3], SimpleButtonAccessor.POSITION_X, .5f)
                .target(TwoLiesOneFact.GAME_WIDTH/2 - (AssetsHandler.soundOffButton.getWidth()/2))
                .ease(TweenEquations.easeInOutQuint)
                .start(manager);
    }

    @Override
    public void dispose() {
        batcher.dispose();
    }
}
