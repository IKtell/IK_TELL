package com.robot.tuling.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robot.tuling.R;
import com.robot.tuling.adapter.ChooseMajorAdapter;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SkillPointsActivity extends AppCompatActivity {

    @BindView(R.id.recycler_choose_major)
    RecyclerView mRecyclerChooseMajor;
    @BindView(R.id.text_major)
    TextView mTextMajor;
    @BindView(R.id.card_view)
    CardView mCardView;
    @BindView(R.id.text_choose_major)
    TextView mTextChooseMajor;

    private List<String> majors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_points);
        ButterKnife.bind(this);
        initMajorChooser();
    }

    private void initMajorChooser() {
        mRecyclerChooseMajor.setLayoutManager(new LinearLayoutManager(this));
        majors = Arrays.asList(getResources().getStringArray(R.array.majors));
        mRecyclerChooseMajor.setAdapter(new ChooseMajorAdapter(this, this, majors));
        mRecyclerChooseMajor.hasFixedSize();
    }

    public void closeList(int major) {
        mRecyclerChooseMajor.setVisibility(View.GONE);
        ViewGroup.LayoutParams params = mCardView.getLayoutParams();
        params.height = 296;
        mCardView.setLayoutParams(params);
        mTextMajor.setText(majors.get(major));
        mTextMajor.setVisibility(View.VISIBLE);
        mTextChooseMajor.setVisibility(View.GONE);

    }

    @OnClick(R.id.text_major)
    public void onTextMajorClicked() {
        ViewGroup.LayoutParams params = mCardView.getLayoutParams();
        params.height = 512;
        mCardView.setLayoutParams(params);
        mTextMajor.setVisibility(View.GONE);
        mTextChooseMajor.setVisibility(View.VISIBLE);
        mRecyclerChooseMajor.setVisibility(View.VISIBLE);
    }
}
