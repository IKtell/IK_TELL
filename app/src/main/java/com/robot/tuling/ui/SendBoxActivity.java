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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

    private RecyclerView recyclerView;
    private MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_box);
        ButterKnife.bind(this);

        TAG = "CHECK";
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        messageAdapter = new MessageAdapter(mMessageInfoList);
        recyclerView.setAdapter(messageAdapter);
        new UserReceiveTask("123", 0).execute();
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
                    //===
                    messageAdapter.notifyItemInserted(messageAdapter.getItemCount() - 1);
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


    public class UserReceiveTask extends AsyncTask<Void, Void, Boolean> {

        private final String mToken;
        private int index;

        UserReceiveTask(String token, int index) {
            mToken = token;
            this.index = index;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                StringBuilder input = new StringBuilder();
                URL url = new URL("http://45.77.191.48:9292/card/my?index=" + index + "&token=" + mToken);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                InputStream is = conn.getInputStream();
                int c;
                while((c = is.read()) != -1) {
                    input.append((char) c);
                }
                conn.disconnect();
                JSONArray cards = new JSONObject(input.toString()).getJSONArray("cards");
                mMessageInfoList.clear();
                for (int i = 0; i < cards.length(); i++) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    mMessageInfoList.add(new MessageInfo(((JSONObject) cards.get(i)).get("data") + "", ((JSONObject) cards.get(i)).get("like") + "",
                            ((JSONObject) cards.get(i)).get("comment") + "", ((JSONObject) cards.get(i)).get("uuid") + "",
                            new Date(String.valueOf(format.parse((String) ((JSONObject) cards.get(i)).get("time"))))));
                }
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
            Log.e("......", "sssssssssssssss");
            for (int i = 0; i < messageAdapter.getItemCount(); i++) {
                Log.e("......", "sssssssssssssss" + i);
                messageAdapter.notifyItemInserted(i);
            }
        }

        @Override
        protected void onCancelled() {
            // 省略
        }
    }
}
