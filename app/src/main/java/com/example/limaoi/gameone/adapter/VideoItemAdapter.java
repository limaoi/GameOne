package com.example.limaoi.gameone.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.limaoi.gameone.R;
import com.example.limaoi.gameone.bean.Video;

import java.util.ArrayList;

import cn.jzvd.JZVideoPlayerStandard;
import de.hdodenhof.circleimageview.CircleImageView;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Created by limaoi on 2017/12/24.
 * E-mail：autismlm20@vip.qq.com
 */

public class VideoItemAdapter extends RecyclerView.Adapter<VideoItemAdapter.ViewHolder> implements View.OnClickListener {


    private ArrayList<Video> mData;
    private OnItemClickListener mOnItemClickListener = null;

    public VideoItemAdapter(ArrayList<Video> Data) {
        this.mData = Data;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void updateData(ArrayList<Video> Data) {
        this.mData = Data;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        VideoItemAdapter.ViewHolder holder = new VideoItemAdapter.ViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Video video = mData.get(position);
        holder.tv_nickname.setText(video.getNickname());
        holder.jzVideoPlayer.setUp(video.getVideoUrl(), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, video.getTitle());
        Glide.with(getApplicationContext()).load(Uri.parse(video.getVideoPicUrl())).into(holder.jzVideoPlayer.thumbImageView);
        Log.i("tag", "video.getHeadPictureUrl()：" + video.getHeadPictureUrl());
        Glide.with(getApplicationContext()).load(video.getHeadPictureUrl()).into(holder.circleImageView_head_photo);
        holder.btn_like.setText(video.getLikeCount() + "");
        holder.btn_comment.setText(video.getCommentCount() + "");
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        JZVideoPlayerStandard jzVideoPlayer;
        CircleImageView circleImageView_head_photo;
        TextView tv_nickname;
        Button btn_like;
        Button btn_comment;

        public ViewHolder(View itemView) {
            super(itemView);
            jzVideoPlayer = (JZVideoPlayerStandard) itemView.findViewById(R.id.videoplayer);
            circleImageView_head_photo = (CircleImageView) itemView.findViewById(R.id.circleImageView_head_photo);
            tv_nickname = (TextView) itemView.findViewById(R.id.tv_nickname);
            btn_like = (Button) itemView.findViewById(R.id.btn_like);
            btn_comment = (Button) itemView.findViewById(R.id.btn_comment);
            btn_like.setOnClickListener(this);
            btn_comment.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
