package com.nectarmicrosystems.libgdx.twoliesonefact.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.nectarmicrosystems.libgdx.twoliesonefact.handlers.AssetsHandler;
import com.nectarmicrosystems.libgdx.twoliesonefact.handlers.GameSettings;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by oluwatobi on 12/26/2015.
 */
public class GameSession {

    private GameState gameState;
    private AnswerState answerState;
    private int score;
    private int highestScore;
    private int questionCount;
    private ArrayList<Statement> statements;
    private ArrayList<Statement> answers;
    private String[] selectedTrueValue;
    private int timer;
    private String currentTrueValue;
    private Thread timerThread;
    private Bonus timeBonus;
    private Bonus eraseOneBonus;

    public GameSession(){
        this.gameState = GameState.Ready;
        this.answerState = AnswerState.NotAnswered;
        this.score = 0;
        highestScore = GameSettings.getHighscore();
        this.questionCount = 0;
        statements = new ArrayList<Statement>();
        answers = new ArrayList<Statement>();
        timer = 20;
        timerThread = new Thread(timerCount);
        selectedTrueValue = new String[3];
        timeBonus = new Bonus(20, TwoLiesOneFact.GAME_HEIGHT - 85, AssetsHandler.timeBonusUnusedTexture.getWidth(), AssetsHandler.timeBonusUnusedTexture.getHeight(), AssetsHandler.timeBonusUsedTextureRegion, AssetsHandler.timeBonusUnusedTextureRegion);
        eraseOneBonus = new Bonus(100, TwoLiesOneFact.GAME_HEIGHT - 85, AssetsHandler.eraseOneBonusUnusedTexture.getWidth(), AssetsHandler.eraseOneBonusUnusedTexture.getHeight(), AssetsHandler.eraseOneBonusUsedTextureRegion, AssetsHandler.eraseOneBonusUnusedTextureRegion);
        loadStatements();
    }

    Runnable timerCount = new Runnable() {
        public void run() {
            try {
                while(timer != 0) {
                    Thread.sleep(1000);
                    timer--;
                    if(timer <= 5 && timer > 0){
                       AssetsHandler.playTick();
                    }
                    if(timer == 0) {
                        AssetsHandler.playWrongSound();
                        setGameState(GameState.Ended);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    public void play(){
        updateScore();
        updateQuestionCount();
        updateHighscore();
    }

    public void pause(){
        timerThread.interrupt();
    }

    public void resume(){
        timerThread = new Thread(timerCount);
        timerThread.start();
    }

    public void end(){
        if(timerThread.isAlive())
            timerThread.interrupt();
    }

    public void executeTimeBonus(){
        //add five seconds
        this.timer += 5;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getScore() {
        return score;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void updateScore(){
        this.score += 5;
    }

    public void updateQuestionCount(){
        this.questionCount += 1;
    }

    Runnable loadThread = new Runnable(){
        public void run() {
            FileHandle file = Gdx.files.internal("statements.txt");
            BufferedReader reader = new BufferedReader(file.reader());
            String line = null;
            int index = 0;

            //load statements
            try {
                line = reader.readLine();
                while( line != null ) {
                    if(line.charAt(0) == '+')
                        statements.add(new Statement(line.substring(1), true, index));
                    else  if(line.charAt(0) == '-')
                        statements.add(new Statement(line.substring(1), false, index));

                    line = reader.readLine();
                    index++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            file = Gdx.files.internal("answers.txt");
            reader = new BufferedReader(file.reader());
            index = 0;

            //load answers
            try {
                line = reader.readLine();
                while( line != null ) {
                    answers.add(new Statement(line, true, index));
                    line = reader.readLine();
                    index++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private void loadStatements(){
        Thread load = new Thread(loadThread);
        load.start();
    }

    public Statement[] generateQuestion(){
        if(timerThread.isAlive())
            timerThread.interrupt();

        this.timer = 20;
        answerState = AnswerState.NotAnswered;
        Statement[] res = new Statement[3];
        res[0] = res[1] = res[2] = null;
        Random r = new Random();

        int x = r.nextInt(3);
        res[x] = pickRandomTrueStatement(x);

        for(int i = 0; i < 3; i++)
            if(res[i] == null)
                res[i] = pickRandomFalseStatement(i);

        timerThread = new Thread(timerCount);
        timerThread.start();

        return  res;
    }

    private Statement pickRandomTrueStatement(int i){
        Statement res;
        Random r = new Random();
        int x = r.nextInt(statements.size());

        res = statements.get(x);
        if(res.isTrue()) {
            currentTrueValue = statements.get(x).getStatement();
            selectedTrueValue[i] = answers.get(x).getStatement();
            statements.remove(x);
            answers.remove(x);
            return res;
        }
        else
            return pickRandomTrueStatement(i);
    }

    private Statement pickRandomFalseStatement(int i){
        Statement res;
        Random r = new Random();
        int x = r.nextInt(statements.size());

        res = statements.get(x);
        if(!res.isTrue()) {
            selectedTrueValue[i] = answers.get(x).getStatement();
            statements.remove(x);
            answers.remove(x);
            return res;
        }
        else
            return pickRandomFalseStatement(i);
    }

    public int getTimer() {
        return timer;
    }

    public AnswerState getAnswerState() {
        return answerState;
    }

    public void setAnswerState(AnswerState answerState) {
        this.answerState = answerState;
    }

    public Thread getTimerThread() {
        return timerThread;
    }

    public Bonus[] getBonuses(){
        Bonus[] bonuses = new Bonus[2];

        bonuses[0] = timeBonus;
        bonuses[1] = eraseOneBonus;

        return bonuses;
    }

    public String getCurrentTrueValue() {
        return currentTrueValue;
    }

    public String[] getSelectedTrueValue() {
        return selectedTrueValue;
    }

    public int getHighestScore(){
        return highestScore;
    }

    public void updateHighscore(){
        if(score > highestScore) {
            highestScore = score;
            GameSettings.setHighscore(highestScore);
            GameSettings.save();
        }
    }
}
