package com.altamob.android.sdkdemo;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.altamob.android.sdkdemo.adapter.AppWallAdapter;
import com.altamob.sdk.AltamobAdListener;
import com.altamob.sdk.ad.NativeAd;
import com.altamob.sdk.model.AD;
import com.altamob.sdk.model.AltamobError;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppWallActivity extends BaseActivity {

    @BindView(R.id.recyle_ad_wall)
    RecyclerView recyleAdWall;
    private NativeAd nativeAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_wall);
        ButterKnife.bind(this);
        recyleAdWall.setLayoutManager(new GridLayoutManager(this, 2));
        nativeAd = new NativeAd(this, "1662684189370000_1769833153868269");
        showDialog();
        nativeAd.loadAd(new AltamobAdListener() {
            @Override
            public void onLoaded(List<AD> ads, String placementId) {
                hideDialog();
//                    bind data to your view
                Log.e("Test", ads.get(0).getTitle() + "," + ads.get(0).getPackage_name());
                recyleAdWall.setAdapter(new AppWallAdapter(AppWallActivity.this, ads, nativeAd));
            }

            @Override
            public void onError(AltamobError error, String placementId) {
                hideDialog();
                Toast.makeText(AppWallActivity.this, "加载出错:code=" + error.errorCode + "|msg=" + error.error, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onClick(AD ad, String placementId) {
                Toast.makeText(AppWallActivity.this, "广告点击:" + ad.getTitle(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onShowed(AD ad, String placementId) {
//                Toast.makeText(AppWallActivity.this, "广告展示:" + ad.getTitle(), Toast.LENGTH_LONG).show();
            }
        }, 5);

    }
}
