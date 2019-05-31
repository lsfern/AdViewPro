package com.adviewpro;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;

import com.facebook.react.ReactActivity;
import com.kuaiyou.loader.InitSDKManager;

public class MainActivity extends ReactActivity {
    public static Activity activity;
    public static String APPID = "SDK20161629040641z7snyxkrbndasty";
    public static String [] permissions=new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.READ_PHONE_STATE};
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
