package com.altamob.android.sdkdemo;

import android.app.Application;

import com.altamob.sdk.AltamobAdSDK;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by LiZhiYang on 2016/9/24 0024.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
//        init sdk,debug or not,default debugenable is false
        AltamobAdSDK.getInstance().init(this);
    }
}
