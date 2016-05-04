package com.nectarmicrosystems.libgdx.twoliesonefact.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.nectarmicrosystems.libgdx.twoliesonefact.handlers.AssetsHandler;
import com.nectarmicrosystems.libgdx.twoliesonefact.handlers.GameScreenInputHandler;
import com.nectarmicrosystems.libgdx.twoliesonefact.main.*;
import com.nectarmicrosystems.libgdx.twoliesonefact.renderers.GameScreenRenderer;
import com.nectarmicrosystems.libgdx.twoliesonefact.uiElements.SimpleButton;

import java.util.Random;

/**
 * Created by oluwatobi on 12/26/2015.
 */
public class GameScreen implements Screen{
    private GameSession gameSession;
    private GameScreenRenderer renderer;
    private SimpleButton statement1;
    private SimpleButton statement2;
    private SimpleButton statement3;
    private SimpleButton pause;
    private SimpleButton resume;
    private SimpleButton mainMenu;
    private SimpleButton playAgain;
    private Statement[] currentStatements;
    Thread correctStateThread;
    Thread wrongStateThread;
    private int played;

    public GameScreen(){
        statement1 = new SimpleButton(TwoLiesOneFact.GAME_WIDTH/2 - AssetsHandler.questionTexture.getWidth()/2, (TwoLiesOneFact.GAME_HEIGHT/2 + 350) - AssetsHandler.questionTexture.getHeight()/2, AssetsHandler.questionTexture.getWidth(), AssetsHandler.questionTexture.getHeight(), AssetsHandler.questionTextureRegion, AssetsHandler.questionTextureRegion);
        statement2 = new SimpleButton(TwoLiesOneFact.GAME_WIDTH/2 - AssetsHandler.questionTexture.getWidth()/2, (TwoLiesOneFact.GAME_HEIGHT/2 + 30) - AssetsHandler.questionTexture.getHeight()/2, AssetsHandler.questionTexture.getWidth(), AssetsHandler.questionTexture.getHeight(), AssetsHandler.questionTextureRegion, AssetsHandler.questionTextureRegion);
        statement3 = new SimpleButton(TwoLiesOneFact.GAME_WIDTH/2 - AssetsHandler.questionTexture.getWidth()/2, (TwoLiesOneFact.GAME_HEIGHT/2 - 290) - AssetsHandler.questionTexture.getHeight()/2, AssetsHandler.questionTexture.getWidth(), AssetsHandler.questionTexture.getHeight(), AssetsHandler.questionTextureRegion, AssetsHandler.questionTextureRegion);
        pause = new SimpleButton(TwoLiesOneFact.GAME_WIDTH - AssetsHandler.pauseTexture.getWidth() - 15, 15, AssetsHandler.pauseTexture.getWidth(), AssetsHandler.pauseTexture.getHeight(), AssetsHandler.pauseTextureRegion, AssetsHandler.pauseTextureRegion);
        resume = new SimpleButton(TwoLiesOneFact.GAME_WIDTH/2 - AssetsHandler.resumeUnpressedTexture.getWidth()/2, TwoLiesOneFact.GAME_HEIGHT/2 - AssetsHandler.resumeUnpressedTexture.getHeight()/2, AssetsHandler.resumeUnpressedTexture.getWidth(), AssetsHandler.resumeUnpressedTexture.getHeight(), AssetsHandler.resumeUnpressedTextureRegion, AssetsHandler.resumePressedTextureRegion);
        mainMenu = new SimpleButton(TwoLiesOneFact.GAME_WIDTH/2 - AssetsHandler.mainMenuUnpressedTexture.getWidth()/2, TwoLiesOneFact.GAME_HEIGHT/2 - AssetsHandler.mainMenuUnpressedTexture.getHeight()/2 - 120, AssetsHandler.mainMenuUnpressedTexture.getWidth(), AssetsHandler.mainMenuUnpressedTexture.getHeight(), AssetsHandler.mainMenuUnpressedTextureRegion, AssetsHandler.mainMenuPressedTextureRegion);
        playAgain = new SimpleButton(TwoLiesOneFact.GAME_WIDTH/2 - AssetsHandler.playAgainUnpressedTexture.getWidth()/2, ((TwoLiesOneFact.GAME_HEIGHT/6) - (AssetsHandler.playAgainUnpressedTexture.getHeight()/2)), AssetsHandler.playAgainPressedTexture.getWidth(), AssetsHandler.playAgainPressedTexture.getHeight(), AssetsHandler.playAgainUnpressedTextureRegion, AssetsHandler.playAgainPressedTextureRegion);
        this.gameSession = new GameSession();
        this.renderer = new GameScreenRenderer(this);
        Gdx.input.setInputProcessor(new GameScreenInputHandler(this));
        Gdx.input.setCatchBackKey(true);
    }

    public void newQuestion(){
        currentStatements = gameSession.generateQuestion();
        statement1.setClickable(true);
        statement2.setClickable(true);
        statement3.setClickable(true);
    }

    public void play(int selected){
        statement1.setClickable(false);
        statement2.setClickable(false);
        statement3.setClickable(false);

        played = selected;
        if(gameSession.getTimerThread().isAlive())
            gameSession.getTimerThread().interrupt();

        if(currentStatements[selected].isTrue()){
            AssetsHandler.playCorrectSound();
            gameSession.setAnswerState(AnswerState.Correct);
            correctStateThread = new Thread(correctState);
            correctStateThread.start();
        }else{
            AssetsHandler.playWrongSound();
            gameSession.setAnswerState(AnswerState.Wrong);
            wrongStateThread = new Thread(wrongState);
            wrongStateThread.start();
        }
    }

    Runnable correctState = new Runnable() {
        public void run() {
            statement1.setClickable(false);
            statement2.setClickable(false);
            statement3.setClickable(false);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            gameSession.play();
            newQuestion();
        }
    };

    Runnable wrongState = new Runnable() {
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            gameSession.setGameState(GameState.Ended);
        }
    };

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
        if(gameSession.getGameState() == GameState.Running){
            gameSession.pause();
            gameSession.setGameState(GameState.Paused);
        }
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

    public GameSession getGameSession() {
        return gameSession;
    }

    public SimpleButton[] getButtons(){
        SimpleButton[] buttons = new SimpleButton[7];

        buttons[0] = statement1;
        buttons[1] = statement2;
        buttons[2] = statement3;
        buttons[3] = pause;
        buttons[4] = resume;
        buttons[5] = mainMenu;
        buttons[6] = playAgain;

        return buttons;
    }

    public Statement[] getCurrentStatements() {
        return currentStatements;
    }

    public void executeTimeBonus(){
        gameSession.executeTimeBonus();
    }

    public void executeEraseOneBonus(){
        Random r = new Random();
        int x = r.nextInt(3);

        if(!currentStatements[x].isTrue()) {
            currentStatements[x].setStatement("");
            if(x == 0)
                statement1.setClickable(false);
            else if(x == 1)
                statement2.setClickable(false);
            else if(x == 2)
                statement3.setClickable(false);
        }
        else
            executeEraseOneBonus();

        return;
    }

    public int getPlayed() {
        return played;
    }
}
