package com.adviewpro.spread;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adviewpro.MainActivity;
import com.adviewpro.R;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.kuaiyou.loader.AdViewSpreadManager;
import com.kuaiyou.loader.loaderInterface.AdViewSpreadListener;

public class AdSpreadView extends LinearLayout implements AdViewSpreadListener {
    private Context mContext;
    private int count = -1;
    private AdViewSpreadManager adSpreadBIDView = null;

    public AdSpreadView(Context context) {
        this(context, null);
    }

    public AdSpreadView(Context context, @Nullable AttributeSet attrs) {
        super(context);
        init(context);
    }


    private void init(Context context) {
        this.mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_normal, this);
    }

    public void getSpread() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_normal, this);
        adSpreadBIDView = new AdViewSpreadManager(MainActivity.activity, MainActivity.APPID,
                findViewById(R.id.spread_layout));

        adSpreadBIDView.setLogo(R.drawable.logo);
        adSpreadBIDView.setBackgroundColor(Color.WHITE);
        adSpreadBIDView.setSpreadNotifyType(AdViewSpreadManager.NOTIFY_COUNTER_NUM);

        adSpreadBIDView.setOnAdViewListener(this);
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
    public void onAdSpreadPrepareClosed() {
        sendEvent();
    }

    @Override
    public void onAdClosedByUser() {
        sendEvent();
    }

    @Override
    public void onAdNotifyCustomCallback(final int ruleTime, final int delayTime) {
        final TextView tv1 = new TextView(mContext);
        final Button btn1 = new Button(mContext);
        final RelativeLayout.LayoutParams btnLp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        final RelativeLayout.LayoutParams tvLp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        btn1.setText("Skip");
        tv1.setText(ruleTime + delayTime + "");

        btnLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        tvLp.addRule(RelativeLayout.LEFT_OF, btn1.getId());

        adSpreadBIDView.getParentLayout().postDelayed(() -> {
            btn1.setVisibility(View.VISIBLE);
            btn1.setOnClickListener(v -> adSpreadBIDView.cancelAd());
        }, ruleTime * 1000);
        adSpreadBIDView.getParentLayout().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (ruleTime + delayTime - count >= 1) {
                    tv1.setText(30 - count + "");
                    count++;
                    adSpreadBIDView.getParentLayout().postDelayed(this, 1000);
                }
            }
        }, 1000);
        adSpreadBIDView.getParentLayout().addView(btn1, btnLp);
        adSpreadBIDView.getParentLayout().addView(tv1, tvLp);
        btn1.setVisibility(View.INVISIBLE);
    }
    private void sendEvent(){
        ReactContext reactContext = (ReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                getId(),//native和js两个视图会依据getId()而关联在一起
                "onSpreadClose",//事件名称
                null
        );
    }
}
