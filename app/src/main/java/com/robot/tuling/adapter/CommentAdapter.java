package com.robot.tuling.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by ZDL on 2018/5/12.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    private JSONArray comments;

    public CommentAdapter(JSONArray comments) {
        this.comments = comments;
        ///这里要加入给comments排序
    }

    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CommentAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return comments.length();
    }

    public JSONArray getComments() {
        return comments;
    }

    public void setComments(JSONArray comments) {
        this.comments = comments;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
