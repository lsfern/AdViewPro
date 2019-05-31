package com.adviewpro.react.simpleview.banner;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.adviewpro.MainActivity;
import com.adviewpro.R;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.kuaiyou.loader.AdViewBannerManager;
import com.kuaiyou.loader.loaderInterface.AdViewBannerListener;

public class AdBannerView extends LinearLayout implements AdViewBannerListener {
    private Context mContext;
    private AdViewBannerManager adViewBIDView = null;
    private LinearLayout layout;
    private View view;

    public AdBannerView(Context context) {
        this(context, null);
    }

    public AdBannerView(Context context, @Nullable AttributeSet attrs) {
        super(context);
        init(context);
    }


    private void init(Context context) {
        this.mContext = context;
        if (null == view) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_main, null, true);
        }

        addView(view);
        LinearLayout.LayoutParams params =
                new android.widget.LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        params.width = 200;
        params.height = 200;
        view.setLayoutParams(params);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int top = 0;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            view.layout(l + top, t, top + l + 60, t + 60);
            top = top + 70;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * 获取bannerView
     */
    public void getBanner() {
        adViewBIDView = new AdViewBannerManager(MainActivity.activity,
                MainActivity.APPID, AdViewBannerManager.BANNER_AUTO_FILL, true);
        adViewBIDView.setShowCloseBtn(true);
        adViewBIDView.setRefreshTime(100000);
        adViewBIDView.setOpenAnim(true);
        adViewBIDView.setOnAdViewListener(this);
        addView(adViewBIDView.getAdViewLayout());
        LinearLayout.LayoutParams params =
                new android.widget.LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        params.width = 500;
        params.height = 300;
        view.setLayoutParams(params);
    }

    @Override
    public void onAdClicked() {

    }

    @Override
    public void onAdDisplayed() {

    }

    @Override
    public void onAdReceived() {

    }

    @Override
    public void onAdFailedReceived(String error) {
    }

    @Override
    public void onAdClosed() {
        sendEvent();
    }

    private void sendEvent() {
        ReactContext reactContext = (ReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                getId(),//native和js两个视图会依据getId()而关联在一起
                "onBannerClose",//事件名称
                null
        );
    }
}
