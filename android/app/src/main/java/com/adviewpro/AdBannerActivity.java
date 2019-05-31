package com.adviewpro;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kuaiyou.loader.AdViewBannerManager;
import com.kuaiyou.loader.loaderInterface.AdViewBannerListener;

/**
 * 横幅广告
 * @author YYZ
 *
 */
public class AdBannerActivity extends Activity implements AdViewBannerListener,
        OnClickListener {
	private AdViewBannerManager adViewBIDView = null;
	private LinearLayout layout = null;

	private Button nextAd = null;
	private LinearLayout codeAd = null;
	private LinearLayout mixAd = null;
	private Button banner_back = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_banner);
		layout = (LinearLayout) findViewById(R.id.banner_layout);
		nextAd = (Button) findViewById(R.id.banner_change);
		codeAd = (LinearLayout) findViewById(R.id.banner_code);
		mixAd = (LinearLayout) findViewById(R.id.banner_mix);
		banner_back = (Button) findViewById(R.id.banner_back);
		nextAd.setOnClickListener(this);
		banner_back.setOnClickListener(this);
		codeAd.setOnClickListener(this);
		mixAd.setOnClickListener(this);
		adViewBIDView = new AdViewBannerManager(AdBannerActivity.this,
				MainActivity.APPID, AdViewBannerManager.BANNER_AUTO_FILL, true);
//		adViewBIDView.logMode=false;
		adViewBIDView.setShowCloseBtn(true);
		adViewBIDView.setRefreshTime(15);
		adViewBIDView.setOpenAnim(true);
		adViewBIDView.setOnAdViewListener(this);
		if (null != layout)
			layout.addView(adViewBIDView.getAdViewLayout());
	}

	@Override
	public void onClick(View v) {
		if (null != adViewBIDView) {
			ViewGroup rootView = (ViewGroup) findViewById(android.R.id.content);
			for (int i = 0; i < rootView.getChildCount(); i++) {
				if (rootView.getChildAt(i) == adViewBIDView.getAdViewLayout()) {
					rootView.removeView(adViewBIDView.getAdViewLayout());
				}
			}
		}
		if (null == layout)
			return;
		layout.removeAllViews();
		adViewBIDView = new AdViewBannerManager(AdBannerActivity.this,
				MainActivity.APPID, AdViewBannerManager.BANNER_SMART, true);
		adViewBIDView.setShowCloseBtn(true);
		adViewBIDView.setRefreshTime(15);
		adViewBIDView.setOpenAnim(true);
		adViewBIDView.setOnAdViewListener(this);
		switch (v.getId()) {
		case R.id.banner_mix:
			mixAd.setBackgroundResource(R.drawable.banner_selected);
			codeAd.setBackgroundResource(R.drawable.banner_normal);
			layout.addView(adViewBIDView.getAdViewLayout());
			break;

		case R.id.banner_code:
			Button button = (Button) findViewById(R.id.banner_back);
			LinearLayout titleLayout = (LinearLayout) findViewById(R.id.title_layout);
			codeAd.setBackgroundResource(R.drawable.banner_selected);
			mixAd.setBackgroundResource(R.drawable.banner_normal);
			LayoutParams layoutParams = new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			layoutParams.topMargin = titleLayout.getHeight()
					+ button.getHeight();
			layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
			this.addContentView(adViewBIDView.getAdViewLayout(), layoutParams);
			break;
		case R.id.banner_change:
			layout.addView(adViewBIDView.getAdViewLayout());
			break;
		case R.id.banner_back:
			this.finish();
			break;
		}

	}


	@Override
	public void onAdClicked() {
		Log.i("AdViewBID", "onAdClicked");
	}

	@Override
	public void onAdClosed() {
		Log.i("AdViewBID", "onAdClosedAd");
		if (null != adViewBIDView) {
			ViewGroup rootView = (ViewGroup) findViewById(android.R.id.content);
			for (int i = 0; i < rootView.getChildCount(); i++) {
				if (rootView.getChildAt(i) == adViewBIDView.getAdViewLayout()) {
					rootView.removeView(adViewBIDView.getAdViewLayout());
				}
			}
		}
		if (null == layout)
			return;
		layout.removeAllViews();
	}

	@Override
	public void onAdDisplayed() {
		Log.i("AdViewBID", "onAdDisplayed");
	}

	@Override
	public void onAdFailedReceived(String arg1) {
		Log.i("AdViewBID", "onAdRecieveFailed");
	}

	@Override
	public void onAdReceived() {
		Toast.makeText(this, "onReceived", Toast.LENGTH_SHORT).show();
		Log.i("AdViewBID", "onAdRecieved");
	}

}
