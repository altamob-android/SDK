package com.altamob.android.sdkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LiZhiYang on 2016/9/27 0027.
 */

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_natived_ad)
    Button btnNativedAd;
    @BindView(R.id.btn_data_flow_ad)
    Button btnDataFlowAd;
    @BindView(R.id.btn_ad_wall)
    Button btnAdWall;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_natived_ad, R.id.btn_data_flow_ad, R.id.btn_ad_wall})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_natived_ad:
                startActivity(new Intent(MainActivity.this, NativedAdActivity.class));
                break;
            case R.id.btn_data_flow_ad:
                startActivity(new Intent(MainActivity.this, MessageFlowAdActivity.class));
                break;
            case R.id.btn_ad_wall:
                startActivity(new Intent(MainActivity.this, AppWallActivity.class));
                break;
        }
    }
}
