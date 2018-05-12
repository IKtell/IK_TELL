package com.robot.tuling.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.robot.tuling.R;
import com.robot.tuling.TulingApplication;
import com.robot.tuling.constant.TulingParams;
import com.robot.tuling.control.RetrofitApi;
import com.robot.tuling.entity.LoginEntity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edit_user_no)
    TextInputLayout mEditUserNo;
    @BindView(R.id.edit_password)
    TextInputLayout mEditPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void onLoginClicked() {
//        Intent intent = new Intent(LoginActivity.this, ChooseRoleActivity.class);
//        startActivity(intent);
//        finish();
        TulingParams.studentID = mEditUserNo.getEditText().getText().toString();
        String password = mEditPassword.getEditText().getText().toString();
        if (TextUtils.isEmpty(TulingParams.studentID)) return;
        if (TextUtils.isEmpty(password)) return;
        RetrofitApi retrofitService = TulingApplication.getRetrofit().create(RetrofitApi.class);
        Call<LoginEntity> callback = retrofitService.login(TulingParams.studentID, password);
        callback.enqueue(new Callback<LoginEntity>() {
            @Override
            public void onResponse(Call<LoginEntity> call, Response<LoginEntity> response) {
                    if ("SUCCESS".equalsIgnoreCase(response.body().getStatus())) {
                        Intent intent = new Intent(LoginActivity.this, ChooseRoleActivity.class);
                        startActivity(intent);
                        finish();
                }
            }

            @Override
            public void onFailure(Call<LoginEntity> call, Throwable t) {

            }
        });
    }
}
