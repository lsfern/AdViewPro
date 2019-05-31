package com.adviewpro.react.simpleview.video;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.adviewpro.MainActivity;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.kuaiyou.loader.AdViewVideoManager;
import com.kuaiyou.loader.loaderInterface.AdViewVideoListener;

public class AdVideoView extends LinearLayout implements AdViewVideoListener {
    private Context mContext;
    private AdViewVideoManager adViewVideoManager = null;
    private String mPosId = "";

    public AdVideoView(Context context) {
        this(context, null);
    }

    public AdVideoView(Context context, @Nullable AttributeSet attrs) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
    }

    public void getVideo() {
        adViewVideoManager = new AdViewVideoManager(MainActivity.activity, MainActivity.APPID, mPosId, this, false);
        adViewVideoManager.setVideoOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        adViewVideoManager.setVideoBackgroungColor("#000000");
    }

    /**
     * 设置广告位Id
     * @param posId 广告位Id
     */
    public void setPosId(String posId) {
        mPosId = posId;
    }

    @Override
    public void onReceivedVideo(String s) {
//        sendVideoLoadSuccessEvent();
    }

    @Override
    public void onFailedReceivedVideo(String s) {

    }

    @Override
    public void onVideoReady() {
        if (adViewVideoManager.isReady()) {
            adViewVideoManager.playVideo(MainActivity.activity);
        }
    }

    @Override
    public void onVideoStartPlayed() {
    }

    @Override
    public void onVideoFinished() {
//        sendVideoFinishedEvent();
    }

    @Override
    public void onVideoClosed() {
//        sendVideoCloseEvent();
    }

    @Override
    public void onPlayedError(String s) {

    }

    private void sendVideoLoadSuccessEvent() {
        ReactContext reactContext = (ReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                getId(),
                "onVideoLoadSuccess",
                null
        );
    }

    private void sendVideoCloseEvent() {
        ReactContext reactContext = (ReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                getId(),
                "onVideoClose",
                null
        );
    }

    private void sendVideoFinishedEvent() {
        ReactContext reactContext = (ReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                getId(),
                "onVideoFinished",
                null
        );
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
        reLayout();
    }

    public void reLayout() {
        if (getWidth() > 0 && getHeight() > 0) {
            int w = MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.EXACTLY);
            int h = MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.EXACTLY);
            measure(w, h);
            layout(getPaddingLeft() + getLeft(), getPaddingTop() + getTop(), getWidth() + getPaddingLeft() + getLeft(), getHeight() + getPaddingTop() + getTop());
        }
    }
}
