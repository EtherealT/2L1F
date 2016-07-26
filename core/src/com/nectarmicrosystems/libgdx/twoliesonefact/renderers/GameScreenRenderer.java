package com.nectarmicrosystems.libgdx.twoliesonefact.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.nectarmicrosystems.libgdx.twoliesonefact.handlers.AssetsHandler;
import com.nectarmicrosystems.libgdx.twoliesonefact.main.*;
import com.nectarmicrosystems.libgdx.twoliesonefact.screens.GameScreen;
import com.nectarmicrosystems.libgdx.twoliesonefact.uiElements.SimpleButton;

/**
 * Created by oluwatobi on 12/26/2015.
 */
public class GameScreenRenderer implements Renderer {

    private SpriteBatch batcher;
    private OrthographicCamera camera;
    private Sprite bgSprite;
    private Sprite endBgSprite;
    private Sprite questionSprite;
    private Sprite lineSprite;
    private Sprite lineSprite2;
    private Sprite timeSprite;
    private Sprite correctSprite;
    private Sprite wrongSprite;
    private Sprite touchToBegin;
    private GameScreen screen;

    private BitmapFont scoreFont;
    private BitmapFont scoreValueFont;
    private BitmapFont timerValueFont;
    private BitmapFont statementFont;
    private BitmapFont finalScoreFont;
    private BitmapFont highestScoreFont;

    public GameScreenRenderer(GameScreen screen){
        this.screen = screen;
        batcher = new SpriteBatch();
        bgSprite = new Sprite(AssetsHandler.gameBackgroundTexture);
        endBgSprite = new Sprite(AssetsHandler.backgroundTexture);
        questionSprite = new Sprite(AssetsHandler.questionTexture);
        lineSprite = new Sprite(AssetsHandler.lineTexture);
        lineSprite2 = new Sprite(AssetsHandler.lineTexture);
        touchToBegin = new Sprite(AssetsHandler.touchToBeginTexture);
        timeSprite = new Sprite(AssetsHandler.timeTexture);
        correctSprite = new Sprite(AssetsHandler.correctTexture);
        wrongSprite = new Sprite(AssetsHandler.wrongTexture);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, TwoLiesOneFact.GAME_WIDTH, TwoLiesOneFact.GAME_HEIGHT);
        batcher.setProjectionMatrix(camera.combined);

        bgSprite.setSize(TwoLiesOneFact.GAME_WIDTH, TwoLiesOneFact.GAME_HEIGHT);
        bgSprite.setPosition(0, 0);
        endBgSprite.setSize(TwoLiesOneFact.GAME_WIDTH, TwoLiesOneFact.GAME_HEIGHT);
        endBgSprite.setPosition(0, 0);
        questionSprite.setPosition(TwoLiesOneFact.GAME_WIDTH/2 - AssetsHandler.questionTexture.getWidth()/2, TwoLiesOneFact.GAME_HEIGHT - (AssetsHandler.questionTexture.getHeight() + 100));
        lineSprite.setPosition(TwoLiesOneFact.GAME_WIDTH/2 - AssetsHandler.questionTexture.getWidth()/2, TwoLiesOneFact.GAME_HEIGHT - (TwoLiesOneFact.GAME_HEIGHT/3));
        lineSprite2.setPosition(TwoLiesOneFact.GAME_WIDTH/2 - AssetsHandler.questionTexture.getWidth()/2, TwoLiesOneFact.GAME_HEIGHT - (2 * (TwoLiesOneFact.GAME_HEIGHT/3)));
        touchToBegin.setPosition(TwoLiesOneFact.GAME_WIDTH/2 - AssetsHandler.touchToBeginTexture.getWidth()/2, TwoLiesOneFact.GAME_HEIGHT/2 - AssetsHandler.touchToBeginTexture.getHeight()/2);
        timeSprite.setPosition(TwoLiesOneFact.GAME_WIDTH/2 - AssetsHandler.timeTexture.getWidth()/2, 130);
        correctSprite.setPosition(TwoLiesOneFact.GAME_WIDTH/2 - AssetsHandler.correctTexture.getWidth()/2, 130);
        wrongSprite.setPosition(TwoLiesOneFact.GAME_WIDTH/2 - AssetsHandler.wrongTexture.getWidth()/2, 130);
        
        scoreFont = SplashScreenRenderer.scoreFont;
        scoreValueFont = SplashScreenRenderer.scoreValueFont;
        timerValueFont = SplashScreenRenderer.timerValueFont;
        statementFont = SplashScreenRenderer.statementFont;
        finalScoreFont = SplashScreenRenderer.finalScoreFont;
        highestScoreFont = SplashScreenRenderer.highestScoreFont;
    }
    
    @Override
    public void render(float delta) {
        camera.update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batcher.setProjectionMatrix(camera.combined);
        batcher.begin();

        if(screen.getGameSession().getGameState().equals(GameState.Ready))
            renderReadyState(delta);

        if(screen.getGameSession().getGameState().equals(GameState.Running))
            renderRunningState(delta);

        if(screen.getGameSession().getGameState().equals(GameState.Paused))
            renderPausedState(delta);

        if(screen.getGameSession().getGameState().equals(GameState.Ended))
            renderEndedState(delta);

        batcher.end();
    }

    private void renderReadyState(float delta){
        bgSprite.draw(batcher);
        batcher.enableBlending();
        renderBonuses();
        renderScore();
        touchToBegin.draw(batcher);
        batcher.disableBlending();
    }

    private void renderRunningState(float delta){
        bgSprite.draw(batcher);
        batcher.enableBlending();
        renderBonuses();
        renderScore();
        renderButtons();
        renderQuestion();
        timeSprite.draw(batcher);
        timerValueFont.draw(batcher, String.valueOf(screen.getGameSession().getTimer()), TwoLiesOneFact.GAME_WIDTH/2 - AssetsHandler.timeTexture.getWidth()/2, timeSprite.getY() + 65, AssetsHandler.timeTexture.getWidth(), Align.center, true);

        if(screen.getGameSession().getAnswerState() == AnswerState.Correct)
            renderCorrectAnswer();

        if(screen.getGameSession().getAnswerState() == AnswerState.Wrong)
            renderWrongAnswer();

        batcher.disableBlending();
    }

    private void renderPausedState(float delta){
        bgSprite.draw(batcher);
        batcher.enableBlending();
        renderBonuses();
        renderScore();
        renderButtons();
        batcher.disableBlending();
    }

    private void renderEndedState(float delta){
        String solution;

        if(screen.getGameSession().getAnswerState() == AnswerState.NotAnswered)
            solution = screen.getGameSession().getCurrentTrueValue();
        else{
            solution = screen.getGameSession().getSelectedTrueValue()[screen.getPlayed()];
        }

        endBgSprite.draw(batcher);
        batcher.enableBlending();
        questionSprite.draw(batcher);
        statementFont.draw(batcher, solution, questionSprite.getX() + 50, questionSprite.getY() + AssetsHandler.questionTexture.getHeight()/2 + 50, 500, Align.center, true);
        lineSprite.draw(batcher);
        lineSprite2.draw(batcher);
        scoreFont.draw(batcher, "Score", TwoLiesOneFact.GAME_WIDTH/2 - 50, TwoLiesOneFact.GAME_HEIGHT/2 + 130, 100, Align.center, true);
        finalScoreFont.draw(batcher, String.valueOf(screen.getGameSession().getScore()), TwoLiesOneFact.GAME_WIDTH/2 - 250, TwoLiesOneFact.GAME_HEIGHT/2 + 50, 500, Align.center, true);
        highestScoreFont.draw(batcher, "Highest Score: " + String.valueOf(screen.getGameSession().getHighestScore()), TwoLiesOneFact.GAME_WIDTH/2 - 250, TwoLiesOneFact.GAME_HEIGHT/2 - 100, 500, Align.center, true);
        renderButtons();
        batcher.disableBlending();
    }

    private void renderBonuses(){
        Bonus[] bonuses = screen.getGameSession().getBonuses();

        for(int i = 0; i < bonuses.length; i++){
            bonuses[i].draw(batcher);
        }
    }

    private void renderCorrectAnswer(){
        correctSprite.draw(batcher);
    }

    private void renderWrongAnswer(){
        wrongSprite.draw(batcher);
    }

    private void renderButtons(){
        SimpleButton[] buttons = screen.getButtons();

        if(screen.getGameSession().getGameState() == GameState.Running) {
            for (int i = 0; i < buttons.length - 3; i++)
                buttons[i].draw(batcher);
        }

        if(screen.getGameSession().getGameState() == GameState.Paused) {
            for (int i = 4; i < buttons.length - 1; i++)
                buttons[i].draw(batcher);
        }

        if(screen.getGameSession().getGameState() == GameState.Ended) {
            for (int i = 6; i < buttons.length; i++)
                buttons[i].draw(batcher);
        }
    }

    private void renderScore(){
        scoreFont.draw(batcher, "Score", TwoLiesOneFact.GAME_WIDTH - 100, TwoLiesOneFact.GAME_HEIGHT - 10);
        scoreValueFont.draw(batcher, String.valueOf(screen.getGameSession().getScore()), TwoLiesOneFact.GAME_WIDTH - 150, TwoLiesOneFact.GAME_HEIGHT - 50, 130, Align.right, true);
    }

    private void renderQuestion(){
        Statement[] statements = screen.getCurrentStatements();
        SimpleButton[] buttons = screen.getButtons();
        statementFont.draw(batcher, statements[0].getStatement(), buttons[0].getX() + 50, buttons[0].getY() + AssetsHandler.questionTexture.getHeight()/2 + 50, 500, Align.center, true);
        statementFont.draw(batcher, statements[1].getStatement(), buttons[1].getX() + 50, buttons[1].getY() + AssetsHandler.questionTexture.getHeight()/2 + 50, 500, Align.center, true);
        statementFont.draw(batcher, statements[2].getStatement(), buttons[2].getX() + 50, buttons[2].getY() + AssetsHandler.questionTexture.getHeight()/2 + 50, 500, Align.center, true);
    }

    @Override
    public void dispose() {
        batcher.dispose();
    }
}
