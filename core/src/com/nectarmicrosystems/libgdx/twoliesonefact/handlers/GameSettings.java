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

    private static boolean soundOn = true;
    private static int highscore = 0;
    private static Preferences preferences;

    private static Preferences getPrefs() {
        if(preferences == null)
            preferences = Gdx.app.getPreferences(PREFS_NAME);

        return preferences;
    }

    public static void load(){
        soundOn = getPrefs().getBoolean(SOUND_SETTINGS_LABEL, true);
        highscore = getPrefs().getInteger(HIGHSCORE_LABEL, 0);
    }

    public static void save(){
        getPrefs().putBoolean(SOUND_SETTINGS_LABEL, soundOn);
        getPrefs().putInteger(HIGHSCORE_LABEL, highscore);
        getPrefs().flush();
    }

    public static void setSound(boolean val){
        soundOn = val;
    }

    public static void setHighscore(int val){
        highscore = val;
    }

    public static int getHighscore(){
        return highscore;
    }

    public static boolean soundEnabled(){
        return soundOn;
    }

}
