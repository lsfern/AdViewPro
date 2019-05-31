package com.adviewpro;

import android.app.Application;

import com.adviewpro.banner.AdBannerReactPackage;
import com.adviewpro.insert.AdInsertReactPackage;
import com.adviewpro.proto.AdNativeReactPackage;
import com.adviewpro.spread.AdSpreadReactPackage;
import com.adviewpro.video.AdVideoReactPackage;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.kuaiyou.loader.InitSDKManager;

import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    new AdBannerReactPackage(),
                    new AdSpreadReactPackage(),
                    new AdInsertReactPackage(),
                    new AdVideoReactPackage(),
                    new AdNativeReactPackage()
            );
        }

        @Override
        protected String getJSMainModuleName() {
            return "index";
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, /* native exopackage */ false);
    }
}
