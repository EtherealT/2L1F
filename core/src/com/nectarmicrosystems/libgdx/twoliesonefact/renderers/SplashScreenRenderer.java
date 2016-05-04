package com.nectarmicrosystems.libgdx.twoliesonefact.renderers;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.nectarmicrosystems.libgdx.twoliesonefact.handlers.AssetsHandler;
import com.nectarmicrosystems.libgdx.twoliesonefact.main.TwoLiesOneFact;
import com.nectarmicrosystems.libgdx.twoliesonefact.screens.MenuScreen;
import com.nectarmicrosystems.libgdx.twoliesonefact.screens.SplashScreen;
import com.nectarmicrosystems.libgdx.twoliesonefact.tweenAccessors.SpriteAccessor;

/**
 * Created by oluwatobi on 12/26/2015.
 */
public class SplashScreenRenderer implements Renderer {
    private TweenManager manager;
    private SpriteBatch batcher;
    private Sprite sprite;
    OrthographicCamera camera;
    private SplashScreen screen;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    public static BitmapFont scoreFont;
    public static BitmapFont scoreValueFont;
    public static BitmapFont timerValueFont;
    public static BitmapFont statementFont;
    public static BitmapFont finalScoreFont;

    public SplashScreenRenderer(SplashScreen screen){
        this.screen = screen;
    }

    public void show(){
        batcher = new SpriteBatch();
        sprite = new Sprite(AssetsHandler.splashTexture);
        sprite.setColor(1, 1, 1, 1);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, TwoLiesOneFact.GAME_WIDTH, TwoLiesOneFact.GAME_HEIGHT);
        batcher.setProjectionMatrix(camera.combined);
        sprite.setPosition(TwoLiesOneFact.GAME_WIDTH/2 - AssetsHandler.splashTexture.getWidth()/2, TwoLiesOneFact.GAME_HEIGHT/2 - (AssetsHandler.splashTexture.getHeight()/2));
        setupTween();
    }

    Runnable fontGenerators = new Runnable() {
        public void run() {

            //as a temporary fix for free type font generator delay -- load the fonts in a separate thread while splash displays
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    generator = new FreeTypeFontGenerator(Gdx.files.internal("Pink Bunny.ttf"));
                    generator.scaleForPixelHeight((int)Math.ceil(60));
                    parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
                    parameter.size = (int)Math.ceil(60);
                    parameter.minFilter = Texture.TextureFilter.Nearest;
                    parameter.magFilter = Texture.TextureFilter.MipMapLinearNearest;
                    parameter.color = Color.valueOf("#fff4ea");
                    parameter.size = 45;
                    scoreFont = generator.generateFont(parameter);
                    scoreValueFont = generator.generateFont(parameter);
                    parameter.color = Color.BLACK;
                    timerValueFont = generator.generateFont(parameter);
                    parameter.size = 35;
                    statementFont = generator.generateFont(parameter);
                    parameter.color = Color.valueOf("#fff4ea");
                    parameter.size = 100;
                    finalScoreFont = generator.generateFont(parameter);
                }
            });
        }
    };

    private void createFonts(){
        Thread create = new Thread(fontGenerators);
        create.start();
    }

    private void setupTween() {
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        manager = new TweenManager();

        TweenCallback cb = new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                TwoLiesOneFact.GAME.setScreen(new MenuScreen());
            }
        };

        Tween.to(sprite, com.nectarmicrosystems.libgdx.twoliesonefact.tweenAccessors.SpriteAccessor.ALPHA, 2f).target(1)
                .ease(TweenEquations.easeInOutQuad).repeatYoyo(1, .4f)
                .setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
                .start(manager);

        createFonts();
    }

    @Override
    public void render(float delta) {
        manager.update(delta);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batcher.begin();
        sprite.draw(batcher);
        batcher.end();
    }

    @Override
    public void dispose() {
        batcher.dispose();
    }
}
