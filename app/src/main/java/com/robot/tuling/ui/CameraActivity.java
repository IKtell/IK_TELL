/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.robot.tuling.ui;

import android.os.Bundle;

import com.robot.tuling.Fragment.Camera2BasicFragment;
import com.robot.tuling.Fragment.LoginLeftFragment;
import com.robot.tuling.adapter.TalkAdapter;
import com.robot.tuling.base.BaseTalkActivity;
import com.robot.tuling.widget.ViewPagerIndicator;

public class CameraActivity extends BaseTalkActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_camera);
//        if (null == savedInstanceState) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, Camera2BasicFragment.newInstance())
//                    .commit();
//        }
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        ViewPagerIndicator viewPagerIndicator = new ViewPagerIndicator(this, mViewPager, mLlIndicator, 2);
        mViewPager.addOnPageChangeListener(viewPagerIndicator);
        LoginLeftFragment loginLeftFragment = new LoginLeftFragment();
        Camera2BasicFragment camera2BasicFragment = new Camera2BasicFragment();

        mFragmentList.add(loginLeftFragment);
        mFragmentList.add(camera2BasicFragment);
        mTalkAdapter = new TalkAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mTalkAdapter);

    }

}
