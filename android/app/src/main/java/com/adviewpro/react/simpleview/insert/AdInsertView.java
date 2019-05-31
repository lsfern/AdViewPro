package com.adviewpro.react.simpleview.insert;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.adviewpro.MainActivity;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.kuaiyou.loader.AdViewInstlManager;
import com.kuaiyou.loader.loaderInterface.AdViewInstlListener;

public class AdInsertView extends LinearLayout implements AdViewInstlListener {
    private Context mContext;
    private AdViewInstlManager adInstlBIDView = null;

    public AdInsertView(Context context) {
        this(context, null);
    }

    public AdInsertView(Context context, @Nullable AttributeSet attrs) {
        super(context);
        init(context);
    }


    private void init(Context context) {
        this.mContext = context;
    }

    public void getInsert() {
        adInstlBIDView = new AdViewInstlManager(MainActivity.activity, MainActivity.APPID, true);//有关闭按钮：true，无关闭按钮：false
		adInstlBIDView.setDisplayMode(AdViewInstlManager.DISPLAYMODE_DIALOG);
        adInstlBIDView.setOnAdViewListener(this);
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
    public void onAdFailedReceived(String s) {
    }

    @Override
    public void onAdClosed() {
        sendEvent();
    }

    @Override
    public void onAdReady() {
        adInstlBIDView.showInstl(MainActivity.activity);
////        if (adInstlBIDView.getDialogView() != null) {
////			new AlertDialog.Builder(MainActivity.activity).setView(adInstlBIDView.getDialogView()).setPositiveButton("去看看", new DialogInterface.OnClickListener() {
////				@Override
////				public void onClick(DialogInterface dialog, int which) {
////					adInstlBIDView.reportClick();
////					adInstlBIDView.closeInstl();
////				}
////			}).setNegativeButton("退出应用", (dialog, which) -> adInstlBIDView.closeInstl()).show();
////			adInstlBIDView.reportImpression();
////		}
    }
    private void sendEvent(){
        ReactContext reactContext = (ReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                getId(),
                "onInsertClose",
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
