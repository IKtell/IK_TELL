package com.robot.tuling.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robot.tuling.R;
import com.robot.tuling.beans.MessageInfo;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<MessageInfo> mMessageInfoList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView data;

        TextView likeNum;

        TextView commentNum;

        public ViewHolder(View itemView) {
            super(itemView);
            data = (TextView) itemView.findViewById(R.id.message_data);
            likeNum = (TextView) itemView.findViewById(R.id.like_num);
            commentNum = (TextView) itemView.findViewById(R.id.comment_num);
        }
    }

    public MessageAdapter(List<MessageInfo> mMessageInfoList){
        this.mMessageInfoList = mMessageInfoList;

    }

    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MessageAdapter.ViewHolder holder, int position) {
        MessageInfo messageInfo = mMessageInfoList.get(position);
        holder.data.setText(messageInfo.getData());
        holder.commentNum.setText(messageInfo.getCommentNum());
        holder.likeNum.setText(messageInfo.getLikeNum());
    }

    @Override
    public int getItemCount() {
        return mMessageInfoList.size();
    }
}
