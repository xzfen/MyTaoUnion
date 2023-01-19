package com.feng.mytaounion.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.feng.mytaounion.R;
import com.feng.mytaounion.model.domain.HomePagerContent;
import com.feng.mytaounion.utils.LogUtils;
import com.feng.mytaounion.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @项目名称：MyTaoUnion
 * @包名：com.feng.mytaounion.ui.adapter
 * @作者：FENG
 * @类名：HomePageContentAdapter
 * @创建时间：2022/11/913:56
 * @描述：
 **/
public class HomePageContentAdapter extends RecyclerView.Adapter<HomePageContentAdapter.InnerHolder> {

    List<HomePagerContent.DataBean> mData = new ArrayList<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LogUtils.d(this, "onCreateViewHolder...");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_pater_content, parent, false);
        InnerHolder viewHolder = new InnerHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        LogUtils.d(this, "onBindViewHolder..." + position);
        //获取数据
        HomePagerContent.DataBean dataBean = mData.get(position);
        //设置数据
        holder.setData(dataBean);
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemClickListener != null) {
                    mItemClickListener.onItemClick(dataBean);
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<HomePagerContent.DataBean> contents) {
        mData.clear();
        mData.addAll(contents);
        notifyDataSetChanged();

    }

    public void addData(List<HomePagerContent.DataBean> contents) {
        int oldSize = mData.size();
        mData.addAll(contents);
        //更新UI
        notifyItemRangeChanged(oldSize, contents.size());
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.goods_cover)
        public ImageView cover;

        @BindView(R.id.goods_title)
        public TextView title;

        @BindView(R.id.goods_off_price)
        public TextView offPriseTv;

        @BindView(R.id.goods_after_off_prise)
        public TextView finalPriseTv;

        @BindView(R.id.goods_original_prise)
        public TextView originalPriseTv;

        @BindView(R.id.goods_sell_count)
        public TextView sellCountTv;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(HomePagerContent.DataBean dataBean) {
            Context context = itemView.getContext();
            title.setText(dataBean.getTitle());
            ViewGroup.LayoutParams layoutParams = cover.getLayoutParams();
            int width = layoutParams.width;
            int height = layoutParams.height;
            int coverSize = (width>height?width:height)/2;
            //LogUtils.d(this, "picture url--> " + dataBean.getPictUrl());
            String coverPath = UrlUtils.getCoverPath(dataBean.getPictUrl(),coverSize);
            Glide.with(context).load(coverPath).into(cover);
            //LogUtils.d(TAG,"coverPath --- > " + coverPath);
            String finalPrise = dataBean.getZkFinalPrice();
            long couponAmount = dataBean.getCouponAmount();
            // LogUtils.d(this,"final prise -- > " + finalPrise);
            float resultPrise = Float.parseFloat(finalPrise) - couponAmount;
            //LogUtils.d(this,"result prise -- -> " + resultPrise);
            finalPriseTv.setText(String.format("%.2f",resultPrise));
            offPriseTv.setText(String.format(context.getString(R.string.text_goods_off_price),couponAmount));
            originalPriseTv.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            originalPriseTv.setText(String.format(context.getString(R.string.text_goods_original_price),finalPrise));
            sellCountTv.setText(String.format(context.getString(R.string.text_goods_sell_count),dataBean.getVolume()));
        }
    }
}
