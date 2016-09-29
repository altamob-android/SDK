package com.altamob.android.sdkdemo.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.altamob.android.sdkdemo.CustomModel;
import com.altamob.android.sdkdemo.R;
import com.altamob.sdk.AltamobAdListener;
import com.altamob.sdk.ad.CustomNativeAd;
import com.altamob.sdk.model.AD;
import com.altamob.sdk.model.AltamobError;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LiZhiYang on 2016/9/27 0027.
 */

public class MessageFlowAdAdapter extends RecyclerView.Adapter <MessageFlowAdAdapter.BaseViewHolder>{
    private final Context mcontext;
    private final List<CustomModel> modelList;
    private final LayoutInflater mLayoutInflater;
    private final CustomNativeAd nativedAd;


    public MessageFlowAdAdapter(Context context, List<CustomModel> modelList, CustomNativeAd nativeAd) {
        this.mcontext = context;
        this.modelList = modelList;
        mLayoutInflater = LayoutInflater.from(context);
        this.nativedAd = nativeAd;
    }

    @Override
    public int getItemViewType(int position) {
        return modelList.get(position).type;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = -1;
        if (viewType == CustomModel.TYPE_CONTENT) {
            layout = R.layout.list_data_flow_content;
            return new ContentViewHolder(mLayoutInflater.inflate(layout, parent, false));
        } else if (viewType == CustomModel.TYPE_AD) {
            layout = R.layout.list_data_flow_ad;
            return new AdViewHolder(mLayoutInflater.inflate(layout, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, int position) {
        CustomModel customModel = modelList.get(position);

        if (customModel.type == CustomModel.TYPE_CONTENT) {
            ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
            contentViewHolder.tvContent.setText(customModel.content);
        } else if (customModel.type == CustomModel.TYPE_AD) {
            AdViewHolder adViewHolder = (AdViewHolder) holder;
            initAd(adViewHolder);
        }


    }

    private void initAd(final AdViewHolder holder) {
        nativedAd.loadAd(new AltamobAdListener() {
            @Override
            public void onLoaded(List<AD> ads, String placementId) {
                if (ads == null && ads.isEmpty()) {
                    return;
                }
                AD ad = ads.get(0);
                List<View> viewList = new ArrayList<>();
                holder.tvAdTitle.setText(ad.getTitle());
                ImageLoader.getInstance().displayImage(ad.getIcon_url(), holder.ivAdIcon);
                viewList.add(holder.tvAdTitle);
                viewList.add(holder.ivAdIcon);
                viewList.add(holder.btnAdInstall);
                viewList.add(holder.cardAdWall);
                nativedAd.registerViewForInteraction(ad,viewList);
            }

            @Override
            public void onError(AltamobError error, String placementId) {
                Toast.makeText(mcontext, "加载出错:code=" + error.errorCode + "|msg=" + error.error, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onClick(AD ad, String placementId) {
                Toast.makeText(mcontext, "广告点击:" + ad.getTitle(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onShowed(AD ad, String placementId) {
//                Toast.makeText(mcontext, "广告展示:" + ad.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList != null ? modelList.size() : 0;
    }
    public static class BaseViewHolder extends RecyclerView.ViewHolder {

        BaseViewHolder(View view) {
            super(view);
        }
    }
    public static class ContentViewHolder extends BaseViewHolder {
        //content
        @BindView(R.id.tv_content)
        public TextView tvContent;

        ContentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    public static class AdViewHolder extends BaseViewHolder {
        //        ad
        @BindView(R.id.iv_ad_icon)
        public ImageView ivAdIcon;
        @BindView(R.id.tv_ad_title)
        public TextView tvAdTitle;
        @BindView(R.id.btn_ad_install)
        public Button btnAdInstall;
        @BindView(R.id.card_ad_wall)
        public CardView cardAdWall;


        AdViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
