package com.adviewpro.react.simpleview.spread;

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
 * 开屏广告管理
 */
public class AdSpreadManager extends SimpleViewManager<AdSpreadView> {
    private Context mContext;

    @Override
    public String getName() {
        return "RCTAdSpreadView";
    }

    @Override
    protected AdSpreadView createViewInstance(ThemedReactContext reactContext) {
        this.mContext = reactContext;
        return new AdSpreadView(mContext);
    }

    @ReactProp(name = "isGetSpread")
    public void getSpread(AdSpreadView spreadView, boolean isGetSpread) {
        if (!isGetSpread) {
            return;
        }
        AndPermission.with(mContext).runtime().permission(MainActivity.permissions)
                .onGranted(allow -> {
                    spreadView.getSpread();
                })
                .onDenied(denied -> {
                    Toast.makeText(mContext, "权限被拒绝", Toast.LENGTH_SHORT).show();
                }).start();
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                "onSpreadClose", MapBuilder.of("registrationName", "onSpreadClose"));
    }
}
