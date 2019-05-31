package com.adviewpro.react.simpleview.banner;

import android.content.Context;
import android.widget.Toast;

import com.adviewpro.MainActivity;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.yanzhenjie.permission.AndPermission;

import java.util.Map;

import javax.annotation.Nullable;

public class AdBannerManager extends SimpleViewManager<AdBannerView>  {
    private Context mContext;
    @Override
    public String getName() {
        return "RCTAdBannerView";
    }

    @Override
    protected AdBannerView createViewInstance(ThemedReactContext reactContext) {
        this.mContext = reactContext;
        return new AdBannerView(reactContext);
    }

    @ReactProp(name = "isGetBanner")
    public void getBanner(AdBannerView bannerView, boolean isGetBanner) {
        if (!isGetBanner) {
            return;
        }
        AndPermission.with(mContext).runtime().permission(MainActivity.permissions)
                .onGranted(allow -> {
                   bannerView.getBanner();
                })
                .onDenied(denied -> {
                    Toast.makeText(mContext, "权限被拒绝", Toast.LENGTH_SHORT).show();
                }).start();
    }
    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                "onBannerClose", MapBuilder.of("registrationName", "onBannerClose"));
    }
}
