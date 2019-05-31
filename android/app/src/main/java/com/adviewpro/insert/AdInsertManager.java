package com.adviewpro.insert;

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
        int level = PermissionUtil.getInstance().checkNeedPermission(mContext);
        if (level == 0) {
            insertView.getInsert();
        } else {
            Toast.makeText(mContext, "permission not allow", Toast.LENGTH_SHORT).show();
        }
    }
    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                "onInsertClose", MapBuilder.of("registrationName", "onInsertClose"));
    }
}
