package com.adviewpro.react.simpleview.video;

import android.content.Context;
import android.widget.Toast;

import com.adviewpro.MainActivity;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.yanzhenjie.permission.AndPermission;

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
        AndPermission.with(mContext).runtime().permission(MainActivity.permissions)
                .onGranted(allow -> {
                    adVideoView.getVideo();
                })
                .onDenied(denied -> {
                    Toast.makeText(mContext, "权限被拒绝", Toast.LENGTH_SHORT).show();
                }).start();
    }
    @ReactProp(name = "posId")
    public void setPosId(AdVideoView adVideoView, String posId) {
        adVideoView.setPosId(posId);
    }
//    @Nullable
//    @Override
//    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
//        return MapBuilder.of(
//                "onVideoClose", MapBuilder.of("registrationName", "onVideoClose"));
//    }
}
