package com.nectarmicrosystems.libgdx.twoliesonefact.main;

import com.admob.AdsController;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.nectarmicrosystems.libgdx.twoliesonefact.handlers.AssetsHandler;
import com.nectarmicrosystems.libgdx.twoliesonefact.handlers.GameSettings;
import com.nectarmicrosystems.libgdx.twoliesonefact.screens.SplashScreen;

import java.net.InetAddress;

public class TwoLiesOneFact extends Game{
	public static float SCREEN_WIDTH;
	public static float SCREEN_HEIGHT;
	public static float GAME_WIDTH;
	public static float GAME_HEIGHT;
	public static float SCALE;
	public static Game GAME;
	public static GameSettings GAME_SETTINGS;

	public static AdsController adsController;

	public TwoLiesOneFact(AdsController adsController){
		this.adsController = adsController;
	}

	@Override
	public void create () {
		SCREEN_WIDTH = Gdx.graphics.getWidth();
		SCREEN_HEIGHT = Gdx.graphics.getHeight();
		GAME_WIDTH = 800;
		GAME_HEIGHT = SCREEN_HEIGHT / (SCREEN_WIDTH / GAME_WIDTH);
		SCALE = SCREEN_WIDTH / GAME_WIDTH;
		GAME = this;
		GAME_SETTINGS = new GameSettings();

		AssetsHandler.load();
		this.setScreen(new SplashScreen());
	}

	@Override
	public void pause() {
		super.pause();
		GAME_SETTINGS.save();
	}

	@Override
	public void resume() {
		super.resume();
		GAME_SETTINGS.load();
	}

	public static boolean internetAvailable() {
		try {
			InetAddress ipAddr = InetAddress.getByName("google.com");

			if (ipAddr.equals("")) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			return false;
		}

	}
}
