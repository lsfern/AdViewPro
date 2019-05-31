package com.adviewpro.video;

import android.content.Context;
import android.widget.Toast;

import com.adviewpro.utils.PermissionUtil;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

/**
 * 视频广告管理
 */
public class AdVideoManager extends SimpleViewManager<AdVideoView> {
    private Context mContext;

    @Override
    public String getName() {
        return "RCTAdVideoView";
    }

    @Override
    protected AdVideoView createViewInstance(ThemedReactContext reactContext) {
        this.mContext = reactContext;
        return new AdVideoView(mContext);
    }

    @ReactProp(name = "isGetVideo")
    public void getVideo(AdVideoView adVideoView, boolean isGetVideo) {
        if (!isGetVideo) {
            return;
        }
        int level = PermissionUtil.getInstance().checkNeedPermission(mContext);
        if (level == 0) {
            adVideoView.getVideo();
        } else {
            Toast.makeText(mContext, "permission not allow", Toast.LENGTH_SHORT).show();
        }
    }

//    @Nullable
//    @Override
//    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
//        return MapBuilder.of(
//                "onVideoClose", MapBuilder.of("registrationName", "onVideoClose"));
//    }
}
