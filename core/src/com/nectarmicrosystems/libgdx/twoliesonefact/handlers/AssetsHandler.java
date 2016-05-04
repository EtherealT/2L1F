package com.nectarmicrosystems.libgdx.twoliesonefact.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by oluwatobi on 12/26/2015.
 */
public abstract class AssetsHandler {

    public static Sound clickSound;
    public static Sound correctSound;
    public static Sound wrongSound;
    public static Sound tickSound;
    public static Texture splashTexture;
    public static Texture backgroundTexture;
    public static Texture gameBackgroundTexture;
    public static Texture playButtonUnpressedTexture;
    public static Texture questionTexture;
    public static TextureRegion questionTextureRegion;
    public static TextureRegion playButtonUnpressedTextureRegion;
    public static Texture menuLogoTexture;
    public static Texture playButtonPressedTexture;
    public static TextureRegion playButtonPressedTextureRegion;
    public static Texture touchToBeginTexture;
    public static Texture pauseTexture;
    public static TextureRegion pauseTextureRegion;
    public static Texture timeTexture;
    public static Texture correctTexture;
    public static Texture wrongTexture;
    public static Texture resumeUnpressedTexture;
    public static TextureRegion resumeUnpressedTextureRegion;
    public static Texture resumePressedTexture;
    public static Texture playAgainUnpressedTexture;
    public static TextureRegion playAgainUnpressedTextureRegion;
    public static Texture playAgainPressedTexture;
    public static TextureRegion playAgainPressedTextureRegion;
    public static TextureRegion resumePressedTextureRegion;
    public static Texture mainMenuUnpressedTexture;
    public static TextureRegion mainMenuUnpressedTextureRegion;
    public static Texture mainMenuPressedTexture;
    public static TextureRegion mainMenuPressedTextureRegion;
    public static Texture timeBonusUnusedTexture;
    public static TextureRegion timeBonusUnusedTextureRegion;
    public static Texture timeBonusUsedTexture;
    public static TextureRegion timeBonusUsedTextureRegion;
    public static Texture eraseOneBonusUnusedTexture;
    public static TextureRegion eraseOneBonusUnusedTextureRegion;
    public static Texture eraseOneBonusUsedTexture;
    public static TextureRegion eraseOneBonusUsedTextureRegion;
    public static Texture lineTexture;
    public static Texture soundOnButton;
    public static TextureRegion soundOnButtonTextureRegion;
    public static Texture soundOffButton;
    public static TextureRegion soundOffButtonTextureRegion;
    public static Texture menuButtonUnpressedTexture;
    public static Texture menuButtonPressedTexture;
    public static TextureRegion menuButtonUnpressedTextureRegion;
    public static TextureRegion menuButtonPressedTextureRegion;
    public static Texture statisticsUnpressedTexture;
    public static TextureRegion statisticsUnpressedTextureRegion;
    public static Texture statisticsPressedTexture;
    public static TextureRegion statisticsPressedTextureRegion;

    public static void load(){
        clickSound = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));
        correctSound = Gdx.audio.newSound(Gdx.files.internal("correct.mp3"));
        wrongSound = Gdx.audio.newSound(Gdx.files.internal("wrong.mp3"));
        tickSound = Gdx.audio.newSound(Gdx.files.internal("tick.mp3"));

        splashTexture = new Texture(Gdx.files.internal("splash.png"));
        splashTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        backgroundTexture = new Texture(Gdx.files.internal("menu_bg.png"));
        backgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        gameBackgroundTexture = new Texture(Gdx.files.internal("game_bg.png"));
        gameBackgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        menuLogoTexture = new Texture(Gdx.files.internal("menu_logo.png"));
        menuLogoTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        playButtonUnpressedTexture = new Texture(Gdx.files.internal("play_button_unpressed.png"));
        playButtonUnpressedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        playButtonUnpressedTextureRegion = new TextureRegion(playButtonUnpressedTexture);

        playButtonPressedTexture = new Texture(Gdx.files.internal("play_button_pressed.png"));
        playButtonPressedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        playButtonPressedTextureRegion = new TextureRegion(playButtonPressedTexture);

        touchToBeginTexture = new Texture(Gdx.files.internal("ttb.png"));
        touchToBeginTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        questionTexture = new Texture(Gdx.files.internal("question.png"));
        questionTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        questionTextureRegion = new TextureRegion(questionTexture);

        pauseTexture = new Texture(Gdx.files.internal("pause.png"));
        pauseTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        pauseTextureRegion = new TextureRegion(pauseTexture);

        timeTexture = new Texture(Gdx.files.internal("time.png"));
        timeTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        lineTexture = new Texture(Gdx.files.internal("line.png"));
        lineTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        
        correctTexture = new Texture(Gdx.files.internal("correct.png"));
        correctTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        wrongTexture = new Texture(Gdx.files.internal("wrong.png"));
        wrongTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        resumeUnpressedTexture = new Texture(Gdx.files.internal("resume_unpressed.png"));
        resumeUnpressedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        resumeUnpressedTextureRegion = new TextureRegion(resumeUnpressedTexture);

        resumePressedTexture = new Texture(Gdx.files.internal("resume_pressed.png"));
        resumePressedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        resumePressedTextureRegion = new TextureRegion(resumePressedTexture);

        mainMenuUnpressedTexture = new Texture(Gdx.files.internal("main_menu_unpressed.png"));
        mainMenuUnpressedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        mainMenuUnpressedTextureRegion = new TextureRegion(mainMenuUnpressedTexture);

        mainMenuPressedTexture = new Texture(Gdx.files.internal("main_menu_pressed.png"));
        mainMenuPressedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        mainMenuPressedTextureRegion = new TextureRegion(mainMenuPressedTexture);

        timeBonusUnusedTexture = new Texture(Gdx.files.internal("plus_five_unused.png"));
        timeBonusUnusedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        timeBonusUnusedTextureRegion = new TextureRegion(timeBonusUnusedTexture);

        timeBonusUsedTexture = new Texture(Gdx.files.internal("plus_five_used.png"));
        timeBonusUsedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        timeBonusUsedTextureRegion = new TextureRegion(timeBonusUsedTexture);

        eraseOneBonusUnusedTexture = new Texture(Gdx.files.internal("erase_one_unused.png"));
        eraseOneBonusUnusedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        eraseOneBonusUnusedTextureRegion = new TextureRegion(eraseOneBonusUnusedTexture);

        eraseOneBonusUsedTexture = new Texture(Gdx.files.internal("erase_one_used.png"));
        eraseOneBonusUsedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        eraseOneBonusUsedTextureRegion = new TextureRegion(eraseOneBonusUsedTexture);

        playAgainUnpressedTexture = new Texture("play_again_unpressed.png");
        playAgainUnpressedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        playAgainUnpressedTextureRegion = new TextureRegion(playAgainUnpressedTexture);

        playAgainPressedTexture = new Texture("play_again_pressed.png");
        playAgainPressedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        playAgainPressedTextureRegion = new TextureRegion(playAgainPressedTexture);

        soundOnButton = new Texture(Gdx.files.internal("sound.png"));
        soundOnButton.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        soundOnButtonTextureRegion = new TextureRegion(soundOnButton);

        soundOffButton = new Texture(Gdx.files.internal("no_sound.png"));
        soundOffButton.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        soundOffButtonTextureRegion = new TextureRegion(soundOffButton);

        menuButtonPressedTexture = new Texture(Gdx.files.internal("menu_button_pressed.png"));
        menuButtonPressedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        menuButtonPressedTextureRegion = new TextureRegion(menuButtonPressedTexture);

        menuButtonUnpressedTexture = new Texture(Gdx.files.internal("menu_button_unpressed.png"));
        menuButtonUnpressedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        menuButtonUnpressedTextureRegion = new TextureRegion(menuButtonUnpressedTexture);

        statisticsPressedTexture = new Texture(Gdx.files.internal("stats_pressed.png"));
        statisticsPressedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        statisticsPressedTextureRegion = new TextureRegion(statisticsPressedTexture);

        statisticsUnpressedTexture = new Texture(Gdx.files.internal("stats_unpressed.png"));
        statisticsUnpressedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        statisticsUnpressedTextureRegion = new TextureRegion(statisticsUnpressedTexture);
    }

    public static void dispose(){
        clickSound.dispose();
        correctSound.dispose();
        wrongSound.dispose();
        tickSound.dispose();
        splashTexture.dispose();
        backgroundTexture.dispose();
        playButtonPressedTexture.dispose();
        playButtonUnpressedTexture.dispose();
        menuLogoTexture.dispose();
        touchToBeginTexture.dispose();
        gameBackgroundTexture.dispose();
        questionTexture.dispose();
        pauseTexture.dispose();
        timeTexture.dispose();
        correctTexture.dispose();
        wrongTexture.dispose();
        resumeUnpressedTexture.dispose();
        resumePressedTexture.dispose();
        mainMenuPressedTexture.dispose();
        mainMenuUnpressedTexture.dispose();
        timeBonusUnusedTexture.dispose();
        timeBonusUsedTexture.dispose();
        eraseOneBonusUnusedTexture.dispose();
        eraseOneBonusUsedTexture.dispose();
        lineTexture.dispose();
        playAgainUnpressedTexture.dispose();
        playButtonPressedTexture.dispose();
        soundOnButton.dispose();
        soundOffButton.dispose();
        menuButtonPressedTexture.dispose();
        menuButtonUnpressedTexture.dispose();
        statisticsPressedTexture.dispose();
        statisticsUnpressedTexture.dispose();
    }

    public static void playSound(){
        if(GameSettings.soundEnabled())
            clickSound.play(1f);
    }

    public static void playTick(){
        if(GameSettings.soundEnabled())
            tickSound.play(1f);
    }

    public static void playCorrectSound(){
        if(GameSettings.soundEnabled())
            correctSound.play(1f);
    }

    public static void playWrongSound(){
        if(GameSettings.soundEnabled())
            wrongSound.play(1f);
    }
}
