package com.example.limaoi.gameone.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.limaoi.gameone.R;
import com.example.limaoi.gameone.bean.Circle;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by limaoi on 2017/10/28.
 * E-mail：autismlm20@vip.qq.com
 */

public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.ViewHolder> {

    private ArrayList<Circle> mData;

    public CircleAdapter(ArrayList<Circle> Data) {
        this.mData = Data;
    }


    public void updateData(ArrayList<Circle> Data) {
        this.mData = Data;
        notifyDataSetChanged();
    }

    @Override
    public CircleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_cicle_item, parent, false);
        CircleAdapter.ViewHolder holder = new CircleAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CircleAdapter.ViewHolder holder, int position) {
        Circle circle = mData.get(position);
        holder.tv_nickname.setText(circle.getNickname());
        holder.tv_dynamic.setText(circle.getDynamic());
        holder.mDynamicPicture.setImagesData(circle.getDynamicPictureUrl());
        holder.tv_time.setText(circle.getChangeTime());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nickname;
        TextView tv_dynamic;
        TextView tv_time;
        private NineGridImageView<String> mDynamicPicture;

        private NineGridImageViewAdapter<String> mAdapter = new NineGridImageViewAdapter<String>() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, String s) {
                Glide.with(context).load(s).placeholder(R.drawable.ic_account).into(imageView);
            }

            @Override
            protected ImageView generateImageView(Context context) {
                return super.generateImageView(context);
            }

            protected void onItemImageClick(Context context, ImageView imageView, int index, List<String> list) {
                Toast.makeText(context, "image position is " + index, Toast.LENGTH_SHORT).show();
            }

            protected boolean onItemImageLongClick(Context context, ImageView imageView, int index, List<String> list) {
                Toast.makeText(context, "image long click position is " + index, Toast.LENGTH_SHORT).show();
                return true;
            }
        };


        public ViewHolder(View view) {
            super(view);
            tv_nickname = (TextView) view.findViewById(R.id.tv_nickname);
            tv_dynamic = (TextView) view.findViewById(R.id.tv_dynamic);
            tv_time = (TextView) view.findViewById(R.id.tv_time);
            mDynamicPicture = (NineGridImageView<String>) view.findViewById(R.id.ngv_dynamicPicture);
            mDynamicPicture.setAdapter(mAdapter);
        }
    }
}
