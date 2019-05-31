package com.adviewpro.react.simpleview.proto;

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

/**
 * 原生广告管理
 */
public class AdNativeManager extends SimpleViewManager<AdNativeView> {
    private Context mContext;

    @Override
    public String getName() {
        return "RCTAdNativeView";
    }

    @Override
    protected AdNativeView createViewInstance(ThemedReactContext reactContext) {
        this.mContext = reactContext;
        return new AdNativeView(mContext);
    }

    @ReactProp(name = "isGetNative")
    public void getNative(AdNativeView adNativeView, boolean isGetNative) {
        if (!isGetNative) {//可以重置当前的广告实例
            return;
        }
        AndPermission.with(mContext).runtime().permission(MainActivity.permissions)
                .onGranted(allow -> {
                    adNativeView.getNative();
                })
                .onDenied(denied -> {
                    Toast.makeText(mContext, "权限被拒绝", Toast.LENGTH_SHORT).show();
                }).start();
    }

    @ReactProp(name = "posId")
    public void setPosId(AdNativeView adNativeView, String posId) {
        adNativeView.setPosId(posId);
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                "onGetDataSuccess", MapBuilder.of("registrationName", "onGetDataSuccess"));
    }
}
