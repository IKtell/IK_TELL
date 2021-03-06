package com.robot.tuling.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.robot.tuling.R;
import com.robot.tuling.adapter.MessageAdapter;
import com.robot.tuling.beans.MessageInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    String TAG;

    JSONObject jsonObject;

    private List<MessageInfo> mMessageInfoList = new ArrayList<>();

    private UserSendTask mAuthTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_box);
        ButterKnife.bind(this);

        TAG = "CHECK";
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MessageAdapter messageAdapter = new MessageAdapter(mMessageInfoList);
        recyclerView.setAdapter(messageAdapter);
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
                Log.i("TAG", "doInBackground: " + responseData.toString());
                jsonObject = new JSONObject(responseData);
                Log.i("TAG", "doInBackground: " + jsonObject.toString());
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
            try {
                if (jsonObject.getString("status").equals("success")){
                    try {
                        Log.i(TAG, "onPostExecute: "+data);
                        Log.i(TAG, "onPostExecute: "+jsonObject.getString("uuid"));
                        Log.i(TAG, "onPostExecute: "+jsonObject.getString("time"));
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                            Log.i(TAG, "onPostExecute: "+new Date(String.valueOf(format.parse(jsonObject.getString("time").toString()))));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        try {
                            mMessageInfoList.add(new MessageInfo(data, "0", "0",
                                    jsonObject.getString("uuid"), new Date(String.valueOf(format.parse(jsonObject.getString("time").toString())))));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    etMsg.setText("");
                }
                else{
    //                Toast.makeText(this, "failed", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
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
