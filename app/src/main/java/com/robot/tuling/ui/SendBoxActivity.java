package com.robot.tuling.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.robot.tuling.R;
import com.robot.tuling.beans.MessageInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SendBoxActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.message)
    TextView message;
    @BindView(R.id.divider)
    View divider;
    @BindView(R.id.comment_num)
    TextView commentNum;
    @BindView(R.id.comment)
    ImageView comment;
    @BindView(R.id.like_num)
    TextView likeNum;
    @BindView(R.id.like)
    ImageView like;
    @BindView(R.id.iv_send_msg)
    ImageView ivSendMsg;
    @BindView(R.id.iv_send_box)
    ImageView ivSendBox;
    @BindView(R.id.et_msg)
    EditText etMsg;
    @BindView(R.id.ll_msg)
    LinearLayout llMsg;
    @BindView(R.id.rl_msg)
    RelativeLayout rlMsg;

    String responseData;

    String token;

    String data;

    private List<MessageInfo> mMessageInfos = new ArrayList<>();

    private UserSendTask mAuthTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_box);
        ButterKnife.bind(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        
    }

    @OnClick(R.id.iv_send_msg)
    public void onViewClicked() {
        data = etMsg.getText().toString();
        SendExec();
    }

    public class UserSendTask extends AsyncTask<Void, Void, Boolean> {

        private final String mToken;
        private final String mdata;

        UserSendTask(String token, String data) {
            mToken = token;
            mdata = data;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                OkHttpClient client = new OkHttpClient();

                RequestBody requestBody = new FormBody.Builder()
//                        .add("token", mToken)
//                        .add("data", mdata)
                        .build();

                Request request = new Request.Builder()
                        .url("http://45.77.191.48:9292/card/new?token="+mToken+"&data="+data)
                        .post(requestBody)
                        .build();

                Response response = client.newCall(request).execute();
                responseData = response.body().string();

            }
            catch (Exception e) {
                e.printStackTrace();
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            // 处理接收数据
            if (responseData.contains("success")){
                message.setText(data);
                etMsg.setText("");
            }
            else{
//                Toast.makeText(this, "failed", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onCancelled() {
           // 省略
        }
    }
    
    public void SendExec(){
        token = "123";
        mAuthTask = new UserSendTask(token, data);
        mAuthTask.execute((Void) null);
    }
        
}
