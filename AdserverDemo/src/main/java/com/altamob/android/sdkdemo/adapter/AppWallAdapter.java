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

import com.altamob.android.sdkdemo.R;
import com.altamob.sdk.ad.NativeAd;
import com.altamob.sdk.model.AD;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LiZhiYang on 2016/9/27 0027.
 */

public class AppWallAdapter extends RecyclerView.Adapter<AppWallAdapter.AdWallViewHolder> {
    private final Context mcontext;
    private final List<AD> adList;
    private final LayoutInflater mLayoutInflater;
    private final NativeAd nativedAd;

    public AppWallAdapter(Context context, List<AD> adList, NativeAd nativeAd) {
        this.mcontext = context;
        this.adList = adList;
        mLayoutInflater = LayoutInflater.from(context);
        this.nativedAd = nativeAd;
    }


    @Override
    public AdWallViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AdWallViewHolder(mLayoutInflater.inflate(R.layout.list_ad_wall, parent, false));
    }

    @Override
    public void onBindViewHolder(AdWallViewHolder holder, int position) {
        AD ad = adList.get(position);
        List<View> viewList = new ArrayList<>();
        holder.tv_ad_title.setText(ad.getTitle());
        ImageLoader.getInstance().displayImage(ad.getIcon_url(), holder.iv_ad_icon);
        viewList.add(holder.tv_ad_title);
        viewList.add(holder.iv_ad_icon);
        viewList.add(holder.btn_ad_install);
        viewList.add(holder.card_ad_wall);
        nativedAd.bindToViews(ad, viewList);
    }

    @Override
    public int getItemCount() {
        return adList != null ? adList.size() : 0;
    }

    public static class AdWallViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_ad_wall)
        public CardView card_ad_wall;
        @BindView(R.id.iv_ad_icon)
        public ImageView iv_ad_icon;
        @BindView(R.id.tv_ad_title)
        public TextView tv_ad_title;
        @BindView(R.id.btn_ad_install)
        public Button btn_ad_install;

        AdWallViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
