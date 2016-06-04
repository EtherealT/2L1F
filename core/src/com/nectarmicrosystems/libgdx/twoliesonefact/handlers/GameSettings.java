package com.nectarmicrosystems.libgdx.twoliesonefact.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by oluwatobi on 2/15/2016.
 */
public class GameSettings {
    private static final String PREFS_NAME = "com.nectarmicrosystems.libgdx.twoliesonefact.Settings";
    private static final String SOUND_SETTINGS_LABEL = "soundOn";
    private static final String HIGHSCORE_LABEL = "highscore";

    private boolean soundOn = true;
    private int highscore = 0;
    private Preferences preferences;

    public GameSettings(){
        this.load();
    }

    private Preferences getPrefs() {
        if(preferences == null)
            preferences = Gdx.app.getPreferences(PREFS_NAME);

        return preferences;
    }

    public void load(){
        soundOn = getPrefs().getBoolean(SOUND_SETTINGS_LABEL, true);
        highscore = getPrefs().getInteger(HIGHSCORE_LABEL, 0);
    }

    public void save(){
        getPrefs().putBoolean(SOUND_SETTINGS_LABEL, soundOn);
        getPrefs().putInteger(HIGHSCORE_LABEL, highscore);
        getPrefs().flush();
    }

    public void setSound(boolean val){
        soundOn = val;
    }

    public void setHighscore(int val){
        highscore = val;
    }

    public int getHighscore(){
        return highscore;
    }

    public boolean soundEnabled(){
        return soundOn;
    }

}
