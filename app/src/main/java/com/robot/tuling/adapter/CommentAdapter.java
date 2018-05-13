package com.robot.tuling.adapter;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.library.bubbleview.BubbleTextVew;
import com.robot.tuling.R;
import com.robot.tuling.beans.CommonInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ZDL on 2018/5/12.
 */

public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CommonInfo> comments;
    private int length = 0;

    public CommentAdapter(List<CommonInfo> comments) {
        this.comments = comments;
    }

    @Override
    public int getItemViewType(int position){
        if (comments.get(position).isOwn){
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0){
            return new MyViewRightHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_conversation_right, parent, false));
        }else {
            return new MyViewLeftHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_conversation_left, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CommonInfo commonInfo = comments.get(position);
        if (holder instanceof MyViewRightHolder){
            MyViewRightHolder rightholder = (MyViewRightHolder)holder;
            rightholder.tvTime.setText(commonInfo.time.toString());
            rightholder.btvMessage.setText(commonInfo.data);
            rightholder.civAvatar.setImageResource(R.drawable.boy1);
        }else {
            MyViewLeftHolder leftholder = (MyViewLeftHolder)holder;
            leftholder.tvTime.setText(commonInfo.time.toString());
            leftholder.btvMessage.setText(commonInfo.data);
            leftholder.civAvatar.setImageResource(R.drawable.girl1);
        }
    }


    @Override
    public int getItemCount() {
        return comments.size();
    }


    class MyViewLeftHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.civ_avatar)
        CircleImageView civAvatar;
        @BindView(R.id.btv_message)
        BubbleTextVew btvMessage;

        public MyViewLeftHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class MyViewRightHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.civ_avatar)
        CircleImageView civAvatar;
        @BindView(R.id.btv_message)
        BubbleTextVew btvMessage;

        public MyViewRightHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void addData(List<CommonInfo> datas) {
        comments.addAll(datas);
        notifyDataSetChanged();
    }

    public void addData(CommonInfo commonInfo){
        comments.add(commonInfo);
        notifyDataSetChanged();
    }
}
