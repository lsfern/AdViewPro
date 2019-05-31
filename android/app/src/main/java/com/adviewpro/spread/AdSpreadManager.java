package com.adviewpro.spread;

import android.content.Context;
import android.widget.Toast;

import com.adviewpro.utils.PermissionUtil;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

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
        int level = PermissionUtil.getInstance().checkNeedPermission(mContext);
        if (level == 0) {
            spreadView.getSpread();
        } else {
            Toast.makeText(mContext, "permission not allow", Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                "onSpreadClose", MapBuilder.of("registrationName", "onSpreadClose"));
    }
}
