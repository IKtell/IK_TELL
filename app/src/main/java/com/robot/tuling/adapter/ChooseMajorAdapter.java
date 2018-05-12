package com.robot.tuling.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robot.tuling.R;
import com.robot.tuling.ui.SkillPointsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseMajorAdapter extends RecyclerView.Adapter<ChooseMajorAdapter.ChooseMajorViewHolder> {

    private Context mContext;
    private SkillPointsActivity mActivity;
    private List<String> mMajors;

    public ChooseMajorAdapter(Context context, SkillPointsActivity activity, List<String> majors) {
        mContext = context;
        mActivity = activity;
        mMajors = majors;
    }

    @Override
    public ChooseMajorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_skill_point, parent, false);
        return new ChooseMajorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChooseMajorViewHolder holder, int position) {
        holder.textSkill.setText(mMajors.get(position));
        holder.textSkill.setOnClickListener(v -> {
            mActivity.closeList(position);
        });
    }

    @Override
    public int getItemCount() {
        return mMajors == null ? 0 : mMajors.size();
    }

    class ChooseMajorViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.text_skill)
        TextView textSkill;

        ChooseMajorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
