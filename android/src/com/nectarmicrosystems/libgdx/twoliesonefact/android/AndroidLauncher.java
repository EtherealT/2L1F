package com.nectarmicrosystems.libgdx.twoliesonefact.android;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.admob.AdsController;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.nectarmicrosystems.libgdx.twoliesonefact.main.TwoLiesOneFact;

public class AndroidLauncher extends AndroidApplication implements AdsController {
	private Tracker mTracker;
	private static final String BANNER_AD_UNIT_ID = "ca-app-pub-6513366131729803/2239843178";

	AdView bannerAd;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		View gameView = initializeForView(new TwoLiesOneFact(this), config);
		setupAds();

		//copied code for setting up views to support ads (http://www.norakomi.com/tutorial_admob_part2_banner_ads1.php)
		RelativeLayout layout = new RelativeLayout(this);
		layout.addView(gameView, ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);

		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		layout.addView(bannerAd, params);

		setContentView(layout);
		//

		//google analytics
		AnalyticsApplication application = (AnalyticsApplication) getApplication();
		mTracker = application.getGoogleAnalyticsTracker();
	}

	@Override
	protected void onResume() {
		super.onResume();
		String name = "main menu";

		// [START screen_view_hit]
		Log.i("sending analytics", "Setting screen name: " + name);
		mTracker.setScreenName("Image~" + name);
		mTracker.send(new HitBuilders.ScreenViewBuilder().build());
		// [END screen_view_hit]
	}

	public void setupAds() {
		bannerAd = new AdView(this);
		bannerAd.setVisibility(View.INVISIBLE);
		bannerAd.setBackgroundColor(0xffff9966);
		bannerAd.setAdUnitId(BANNER_AD_UNIT_ID);
		bannerAd.setAdSize(AdSize.SMART_BANNER);
	}

	@Override
	public void showBannerAd() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				bannerAd.setVisibility(View.VISIBLE);
				AdRequest.Builder builder = new AdRequest.Builder();
				AdRequest ad = builder.build();
				bannerAd.loadAd(ad);
			}
		});
	}

	@Override
	public void hideBannerAd() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				bannerAd.setVisibility(View.INVISIBLE);
			}
		});
	}
}
