package com.altamob.android.sdkdemo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.altamob.android.sdkdemo.adapter.MessageFlowAdAdapter;
import com.altamob.sdk.ad.NativeAd;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageFlowAdActivity extends BaseActivity {

    @BindView(R.id.recyle_data_flow)
    RecyclerView recyleDataFlow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_flow_ad);
        ButterKnife.bind(this);
        List<CustomModel> customModelList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CustomModel customModel = new CustomModel();
            if (i == 5) {
                customModel.type = CustomModel.TYPE_AD;
            } else {
                customModel.content = "My Content ,My Content ,My Content" + i;
            }
            customModelList.add(customModel);
        }
        recyleDataFlow.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyleDataFlow.setAdapter(new MessageFlowAdAdapter(this, customModelList, new NativeAd(this, "1662684189370000_1769833153868268")));
    }
}
