package com.adviewpro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.react.ReactActivity;
import com.kuaiyou.loader.InitSDKManager;

public class MainActivity extends ReactActivity {
    public static Activity activity;
    public static String APPID = "SDK20161629040641z7snyxkrbndasty";

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "AdViewPro";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = MainActivity.this;
        InitSDKManager.getInstance().init(activity, APPID, null);
        InitSDKManager.setDownloadNotificationEnable(false);
//        startActivity(new Intent(this, AdBannerActivity.class));
    }
}
