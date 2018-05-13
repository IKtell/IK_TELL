package com.robot.tuling.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.robot.tuling.R;
import com.robot.tuling.adapter.ChatMessageAdapter;
import com.robot.tuling.base.BaseActivity;
import com.robot.tuling.constant.TulingParams;
import com.robot.tuling.control.RetrofitApi;
import com.robot.tuling.entity.MessageEntity;
import com.robot.tuling.util.IsNullOrEmpty;
import com.robot.tuling.util.KeyBoardUtil;
import com.robot.tuling.util.TimeUtil;
import com.sunfusheng.FirUpdater;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lv_message)
    ListView lvMessage;
    @BindView(R.id.iv_send_msg)
    ImageView ivSendMsg;
    @BindView(R.id.et_msg)
    EditText etMsg;
    @BindView(R.id.rl_msg)
    RelativeLayout rlMsg;
    @BindView(R.id.iv_send_box)
    ImageView ivSendBox;

    private List<MessageEntity> msgList = new ArrayList<>();
    private ChatMessageAdapter msgAdapter;
    private long exitTime;
    private String responseData;
    private JSONObject jsonObject;
    private TalkTask mAuthTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        new FirUpdater(this, "3c57fb226edf7facf821501e4eba08d2", "5704953c00fc74127000000a").checkVersion();

        initData();
        initView();
        initListener();
    }

    private void initData() {
        if (msgList.size() == 0) {
            MessageEntity entity = new MessageEntity(ChatMessageAdapter.TYPE_LEFT, TimeUtil.getCurrentTimeMillis());
            entity.setText("你好！有什么需要我帮助的吗？");
            msgList.add(entity);
        }
        String role = getIntent().getStringExtra("ROLE");
        msgAdapter = new ChatMessageAdapter(this, msgList, role);
        lvMessage.setAdapter(msgAdapter);
        lvMessage.setSelection(msgAdapter.getCount());
    }

    private void initView() {
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
    }

    private void initListener() {
        ivSendMsg.setOnClickListener(v -> sendMessage());
//        ivSendMsg.setOnClickListener(v -> funcDemo());
        ivSendBox.setOnClickListener(v -> OpenBox());
        lvMessage.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                KeyBoardUtil.hideKeyboard(mActivity);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }

    private void OpenBox() {
        Intent intent = new Intent(MainActivity.this, SendBoxActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.action_skill:
//                NavigateManager.gotoAboutActivity(mContext);
                Intent intent = new Intent(MainActivity.this, SkillPointsActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }

    // 给Turing发送问题
    public void sendMessage() {
        String msg = etMsg.getText().toString().trim();

        if (!IsNullOrEmpty.isEmpty(msg)) {
            MessageEntity entity = new MessageEntity(ChatMessageAdapter.TYPE_RIGHT, TimeUtil.getCurrentTimeMillis(), msg);
            msgList.add(entity);
            msgAdapter.notifyDataSetChanged();
            etMsg.setText("");

            // 仅使用 Retrofit 请求接口
            //requestApiByRetrofit(msg);
            // 使用 Retrofit 和 RxJava 请求接口
            //requestApiByRetrofit_RxJava(msg);
            mAuthTask = new TalkTask(msg);
            mAuthTask.execute((Void) null);
        }
    }

    // 请求图灵API接口，获得问答信息
    private void requestApiByRetrofit(String info) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TulingParams.TULING_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApi api = retrofit.create(RetrofitApi.class);

        Call<MessageEntity> call = api.getTuringInfo(TulingParams.TULING_KEY, info);
        call.enqueue(new Callback<MessageEntity>() {
            @Override
            public void onResponse(Call<MessageEntity> call, Response<MessageEntity> response) {
                handleResponseMessage(response.body());
            }

            @Override
            public void onFailure(Call<MessageEntity> call, Throwable t) {

            }
        });
    }

    // 请求图灵API接口，获得问答信息
    private void requestApiByRetrofit_RxJava(String info) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TulingParams.TULING_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        RetrofitApi api = retrofit.create(RetrofitApi.class);

        api.getTuringInfoByRxJava(TulingParams.TULING_KEY, info)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponseMessage, Throwable::printStackTrace);
    }

    public class TalkTask extends AsyncTask<Void, Void, Boolean> {

        private final String mdata;

        TalkTask(String data) {
            mdata = data;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = new FormBody.Builder()
//                        .add("token", mToken)
//                        .add("data", mdata)
                    .build();

            Request request = new Request.Builder()
                    .url("http://45.77.191.48:9292/chat?data="+mdata)
                    .build();

            okhttp3.Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                responseData = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                jsonObject = new JSONObject(responseData);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            // 处理接收数据
            Log.i("TAG", "doInBackground: " + responseData.toString());
            Log.i("TAG", "doInBackground: " + jsonObject.toString());
        }
    }

    // 处理获得到的问答信息
    private void handleResponseMessage(MessageEntity entity) {
        if (entity == null) return;

        entity.setTime(TimeUtil.getCurrentTimeMillis());
        entity.setType(ChatMessageAdapter.TYPE_LEFT);

        switch (entity.getCode()) {
            case TulingParams.TulingCode.URL:
                entity.setText(entity.getText() + "，点击网址查看：" + entity.getUrl());
                break;
            case TulingParams.TulingCode.NEWS:
                entity.setText(entity.getText() + "，点击查看");
                break;
        }

        msgList.add(entity);
        msgAdapter.notifyDataSetChanged();
    }

    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
