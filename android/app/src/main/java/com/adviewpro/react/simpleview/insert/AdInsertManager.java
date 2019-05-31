package com.adviewpro.react.simpleview.insert;

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
 * 插屏广告管理
 */
public class AdInsertManager extends SimpleViewManager<AdInsertView> {
    private Context mContext;

    @Override
    public String getName() {
        return "RCTAdInsertView";
    }

    @Override
    protected AdInsertView createViewInstance(ThemedReactContext reactContext) {
        this.mContext = reactContext;
        return new AdInsertView(mContext);
    }

    @ReactProp(name = "isGetInsert")
    public void getInsert(AdInsertView insertView, boolean isGetInsert) {
        if (!isGetInsert) {
            return;
        }
        AndPermission.with(mContext).runtime().permission(MainActivity.permissions)
                .onGranted(allow -> {
                    insertView.getInsert();
                })
                .onDenied(denied -> {
                    Toast.makeText(mContext, "权限被拒绝", Toast.LENGTH_SHORT).show();
                }).start();
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                "onInsertClose", MapBuilder.of("registrationName", "onInsertClose"));
    }
}
