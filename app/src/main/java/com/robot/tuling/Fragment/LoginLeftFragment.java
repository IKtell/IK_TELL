package com.robot.tuling.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robot.tuling.R;

import butterknife.ButterKnife;

public class LoginLeftFragment extends Fragment {

    protected View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login_left, null);
        ButterKnife.bind(this, mView);
        return mView;
    }
}
