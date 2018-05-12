package com.robot.tuling.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robot.tuling.R;
import com.robot.tuling.adapter.TalkAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseTalkActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    protected ViewPager mViewPager;
    @BindView(R.id.ll_indicator)
    protected LinearLayout mLlIndicator;

    protected TalkAdapter mTalkAdapter;

    protected List<Fragment> mFragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_talk);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        mFragmentList = new ArrayList<>();
    }

}
