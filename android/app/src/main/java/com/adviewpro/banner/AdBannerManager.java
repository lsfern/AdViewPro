package com.adviewpro.banner;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.adviewpro.MainActivity;
import com.adviewpro.utils.PermissionUtil;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.kuaiyou.loader.AdViewBannerManager;
import com.kuaiyou.loader.loaderInterface.AdViewBannerListener;

import java.util.Map;

import javax.annotation.Nullable;

public class AdBannerManager extends SimpleViewManager<LinearLayout>  {
    private Context mContent;
    private AdViewBannerManager adViewBIDView = null;
    @Override
    public String getName() {
        return "RCTAdBannerView";
    }

    @Override
    protected LinearLayout createViewInstance(ThemedReactContext reactContext) {
        this.mContent = reactContext;
        return new LinearLayout(reactContext);
    }

    @ReactProp(name = "isGetBanner")
    public void getBanner(LinearLayout bannerView, boolean isGetBanner) {
        if (!isGetBanner) {
            return;
        }
        adViewBIDView = new AdViewBannerManager(MainActivity.activity,
                MainActivity.APPID, AdViewBannerManager.BANNER_AUTO_FILL, true);
        adViewBIDView.setShowCloseBtn(true);
        adViewBIDView.setRefreshTime(100000);
        adViewBIDView.setOpenAnim(true);
        adViewBIDView.setOnAdViewListener(new AdViewBannerListener() {
            @Override
            public void onAdClicked() {

            }

            @Override
            public void onAdDisplayed() {

            }

            @Override
            public void onAdReceived() {
                Toast.makeText(mContent, "收到了", Toast.LENGTH_SHORT).show();
                bannerView.addView(adViewBIDView.getAdViewLayout(),new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
                LinearLayout.LayoutParams params =
                        new android.widget.LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                params.width = 200;
                params.height = 200;
                bannerView.setOrientation(LinearLayout.HORIZONTAL);
                bannerView.setLayoutParams(params);
                Toast.makeText(mContent, ""+bannerView.getMeasuredHeight(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedReceived(String s) {

            }

            @Override
            public void onAdClosed() {

            }
        });
        Button button = new Button(mContent);
        button.setText("横幅广告");
        bannerView.addView(button);

//        int level = PermissionUtil.getInstance().checkNeedPermission(mContent);
//        if (level == 0) {
//            bannerView.getBanner();
//        } else {
//            Toast.makeText(mContent, "permission not allow", Toast.LENGTH_SHORT).show();
//        }
    }
    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                "onBannerClose", MapBuilder.of("registrationName", "onBannerClose"));
    }
}
