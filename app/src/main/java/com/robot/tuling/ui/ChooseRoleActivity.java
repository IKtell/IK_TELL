package com.robot.tuling.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.robot.tuling.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseRoleActivity extends AppCompatActivity {

    @BindView(R.id.btn_girl)
    Button mBtnGirl;
    @BindView(R.id.btn_boy)
    Button mBtnBoy;

    private boolean BOY = false;
    private boolean GIRL = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_girl)
    public void onBtnGirlClicked() {
        if (!GIRL) {
            GIRL = true;
            mBtnGirl.setBackground(ContextCompat.getDrawable(this, R.drawable.shape_btn_white));
            BOY = false;
            mBtnBoy.setBackground(null);
        }
    }

    @OnClick(R.id.btn_boy)
    public void onBtnBoyClicked() {
        if (!BOY) {
            BOY = true;
            mBtnBoy.setBackground(ContextCompat.getDrawable(this, R.drawable.shape_btn_white));
            GIRL = false;
            mBtnGirl.setBackground(null);
        }
    }


    @OnClick({R.id.btn_girl1, R.id.text_girl1})
    public void onGirl1Clicked(View view) {
        Intent intent = new Intent(ChooseRoleActivity.this, MainActivity.class);
        intent.putExtra("ROLE", "girl1");
        startActivity(intent);
        finish();
    }


    @OnClick({R.id.btn_girl2, R.id.text_girl2})
    public void onGirl2Clicked(View view) {
        Intent intent = new Intent(ChooseRoleActivity.this, MainActivity.class);
        intent.putExtra("ROLE", "girl2");
        startActivity(intent);
        finish();
    }

    @OnClick({R.id.btn_girl3, R.id.text_girl3})
    public void onGirl3Clicked(View view) {
        Intent intent = new Intent(ChooseRoleActivity.this, MainActivity.class);
        intent.putExtra("ROLE", "girl3");
        startActivity(intent);
        finish();

    }

    @OnClick({R.id.btn_boy1, R.id.text_boy1})
    public void onBoy1Clicked(View view) {
        Intent intent = new Intent(ChooseRoleActivity.this, MainActivity.class);
        intent.putExtra("ROLE", "boy1");
        startActivity(intent);
        finish();

    }


    @OnClick({R.id.btn_boy2, R.id.text_boy2})
    public void onBoy2Clicked(View view) {
        Intent intent = new Intent(ChooseRoleActivity.this, MainActivity.class);
        intent.putExtra("ROLE", "boy2");
        startActivity(intent);
        finish();
    }

    @OnClick({R.id.btn_boy3, R.id.text_boy3})
    public void onBoy3Clicked(View view) {
        Intent intent = new Intent(ChooseRoleActivity.this, MainActivity.class);
        intent.putExtra("ROLE", "boy3");
        startActivity(intent);
        finish();

    }

}
