package com.adviewpro.react.simpleview.proto;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.kuaiyou.loader.AdViewNativeManager;


public class AdNativeModule extends ReactContextBaseJavaModule {
    private Context mContext;

    public AdNativeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.mContext = reactContext;
    }

    public String getName() {
        return "AdNativeModule";
    }

    /**
     * 物料广告点击上报
     *
     * @param adId - 物料广告Id
     */
    @ReactMethod
    public void report(String adId) {
        if (mContext != null) {
            if (AdNativeView.adViewNative != null) {
                AdViewNativeManager adViewNative = AdNativeView.adViewNative;
                if (!TextUtils.isEmpty(adId)) {
                    adViewNative.reportClick(adId);
                }
            }
        }
    }

    /**
     * 置空当前AdViewNativeManager实例，避免内存泄漏
     */
    @ReactMethod
    public void resetAdViewNative() {
        if (mContext != null) {
            AdNativeView.adViewNative = null;
            Log.i("reset", "success");
        }
    }

}
