package com.adviewpro.proto;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.adviewpro.MainActivity;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.kuaiyou.loader.AdViewNativeManager;
import com.kuaiyou.loader.loaderInterface.AdViewNativeListener;

import java.util.HashMap;
import java.util.List;

public class AdNativeView extends LinearLayout implements AdViewNativeListener {
    private Context mContext;
    public static  AdViewNativeManager adViewNative;
    private String mPosId = "";
    private HashMap<String, Object> nativeAd;

    public AdNativeView(Context context) {
        this(context, null);
    }

    public AdNativeView(Context context, @Nullable AttributeSet attrs) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
    }

    /**
     * 获取原生广告
     */
    public void getNative() {
        if (TextUtils.isEmpty(mPosId)) {
            Toast.makeText(mContext, "广告位Id为空，请重试", Toast.LENGTH_SHORT).show();
            return;
        }
        adViewNative = new AdViewNativeManager(MainActivity.activity, MainActivity.APPID, mPosId, this);
        adViewNative.requestAd();
    }

    /**
     * 设置广告位Id
     * @param posId 广告位Id
     */
    public void setPosId(String posId) {
        mPosId = posId;
    }

    /**
     * 获取数据回调
     * @param writableMap 数据
     */
    private void sendData(WritableMap writableMap) {
        ReactContext reactContext = (ReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                getId(),//native和js两个视图会依据getId()而关联在一起
                "onGetDataSuccess",//事件名称
                writableMap
        );
    }

    @Override
    public void onNativeAdReceived(List nativeAdList) {
        if (null != nativeAdList && !nativeAdList.isEmpty()) {
            nativeAd = (HashMap) nativeAdList.get(0);
            if (nativeAd.get("nativeView") == null && nativeAd.get("videoUrl") == null) {
                WritableMap map = new WritableNativeMap();
                WritableArray array = new WritableNativeArray();
                //获取imageList
                List list = (List) nativeAd.get("imageList");
                if (list != null && !list.isEmpty()) {
                    for (int i = 0; i < list.size(); i++) {
                        array.pushString((String) list.get(i));
                    }
                }
                map.putString("adId", (String) nativeAd.get("adId"));
                map.putString("title", (String) nativeAd.get("title"));
                map.putString("description", (String) nativeAd.get("description"));
                map.putString("adIcon", (String) nativeAd.get("adIcon"));
                map.putString("adImage", (String) nativeAd.get("adImage"));
                map.putString("adFlagLogo", (String) nativeAd.get("adFlagLogo"));
                map.putString("adFlagIcon", (String) nativeAd.get("adFlagIcon"));
                map.putString("sec_description", (String) nativeAd.get("sec_description"));
                map.putInt("imageWidth", (int) nativeAd.get("imageWidth"));
                map.putInt("imageHeight", (int) nativeAd.get("imageHeight"));
                map.putInt("iconWidth", (int) nativeAd.get("iconWidth"));
                map.putInt("iconHeight", (int) nativeAd.get("iconHeight"));
                map.putArray("imageList", array);
                sendData(map);
            }
        }

    }

    @Override
    public void onNativeAdReceiveFailed(String s) {

    }

    @Override
    public void onDownloadStatusChange(int i) {

    }

    @Override
    public void onNativeAdClosed(View view) {

    }

}
