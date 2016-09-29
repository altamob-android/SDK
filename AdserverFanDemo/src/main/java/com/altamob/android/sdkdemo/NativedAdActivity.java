package com.altamob.android.sdkdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.altamob.sdk.AltamobAdListener;
import com.altamob.sdk.ad.CustomNativeAd;
import com.altamob.sdk.model.AD;
import com.altamob.sdk.model.AltamobError;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NativedAdActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.test)
    Button test;
    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.app_icon)
    ImageView appIcon;
    private CustomNativeAd nativeAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natived_ad);
        ButterKnife.bind(this);
//        init nativedad
        nativeAd = new CustomNativeAd(this, "1662684189370000_1769833153868270", 1);
    }

    @OnClick(R.id.test)
    public void onClick(View view) {
        if (view.getId() == R.id.test) {
            nativeAd.loadAd(new AltamobAdListener() {
                @Override
                public void onLoaded(List<AD> ads, String placementId) {
//                    bind data to your view
                    Log.e("Test", ads.get(0).getTitle() + "," + ads.get(0).getPackage_name());
                    appIcon.setVisibility(View.VISIBLE);
                    title.setText(ads.get(0).getTitle() + " total :" + ads.size());
                    ImageLoader.getInstance().displayImage(ads.get(0).getIcon_url(), appIcon);
                    List<View> list = new ArrayList<>();
                    list.add(appIcon);
                    list.add(title);
//you have to bind your views to this ad,otherwise this ad show is useless and your ads can't click
                    nativeAd.registerViewForInteraction(ads.get(0), list);
//bind to single view
//                    nativeAd.bindToView(ads.get(0), appIcon);
                }

                @Override
                public void onError(AltamobError error, String placementId) {
                    Toast.makeText(NativedAdActivity.this, "加载出错:code=" + error.errorCode + "|msg=" + error.error, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onClick(AD ad, String placementId) {
                    Toast.makeText(NativedAdActivity.this, "广告点击:" + ad.getTitle(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onShowed(AD ad, String placementId) {
                    Toast.makeText(NativedAdActivity.this, "广告展示:" + ad.getTitle(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
