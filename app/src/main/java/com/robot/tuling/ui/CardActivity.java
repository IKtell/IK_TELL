package com.robot.tuling.ui;


import android.os.Bundle;
import android.os.Handler;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.robot.tuling.R;
import com.robot.tuling.adapter.CommentAdapter;
import com.robot.tuling.beans.CommonInfo;
import com.robot.tuling.beans.MessageInfo;
import com.robot.tuling.util.TimeUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ZDL on 2018/5/12.
 */

public class CardActivity extends AppCompatActivity {

    @BindView(R.id.comment_rv)
    RecyclerView recyclerView;
    MessageInfo messageInfo;
    CommentAdapter commentAdapter;
    boolean isInit = false;
    String newtime;
    @BindView(R.id.message_data)
    TextView messageData;
    @BindView(R.id.iv_send_msg)
    ImageView ivSendMsg;
    @BindView(R.id.et_msg)
    EditText etMsg;
    @BindView(R.id.like_num)
    TextView like_num;
    @BindView(R.id.comment_num)
    TextView comment_num;

    private static final String TAG = "CardActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        ButterKnife.bind(this);
//        ivSendMsg = (ImageView) findViewById(R.id.iv_send_msg);
//        etMsg = (EditText) findViewById(R.id.et_msg);
        messageInfo = (MessageInfo) getIntent().getSerializableExtra("msgInfo");
//        recyclerView = (RecyclerView)findViewById(R.id.comment_rv);
//        messageData = (TextView) findViewById(R.id.message_data);
        initRecyclerView();

    }

    @Override
    protected void onStart() {

        super.onStart();
        messageData.setText(messageInfo.getData());
        like_num.setText(messageInfo.getLikeNum());
        comment_num.setText(messageInfo.getCommentNum());



    }

    private void initRecyclerView() {
        List<CommonInfo> commonInfos = new ArrayList<>();
        commentAdapter = new CommentAdapter(commonInfos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(commentAdapter);
        getData(null);

    }

    private void getData(String date) {
        Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuilder input = new StringBuilder();
                try {
                    URL url;
                    if (date != null) {
                        url = new URL("http://45.77.191.48:9292/card/comment/history?token=123&uuid=" + messageInfo.getUuid() + "&time=" + date.replaceAll(" ", "_"));
                    } else {
                        String time = TimeUtil.convertDate(System.currentTimeMillis());

                        url = new URL("http://45.77.191.48:9292/card/comment/history?token=123&uuid=" + messageInfo.getUuid());
                        //url = new URL("http://45.77.191.48:9292/card/comment/history?token=123&uuid=" + messageInfo.getUuid() + "&time=" + time.replaceAll(" ", "_"));
                    }
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String s;
                    while((s = br.readLine()) != null) {
                        input.append(s);
                    }
                    conn.disconnect();
                    JSONObject jsonObject = new JSONObject(input.toString());
                    JSONArray comments = jsonObject.getJSONArray("comments");


                    List<CommonInfo> commonInfos = new ArrayList<>();
                    for (int i = 0; i < comments.length(); i++) {
                        CommonInfo info = new CommonInfo();
                        info.time = ((JSONObject) comments.get(i)).getString("time");
                        info.data = ((JSONObject) comments.get(i)).getString("data");
                        info.isOwn = ((JSONObject) comments.get(i)).getBoolean("isown");
                        commonInfos.add(info);
                    }
                    commentAdapter.addData(commonInfos);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }).start();
    }


    @OnClick(R.id.iv_send_msg)
    public void onIvSendMsgClicked() {
        CommonInfo commonInfo = new CommonInfo();
        commonInfo.isOwn = true;
        commonInfo.data = etMsg.getText().toString();
        etMsg.setText("");
        commonInfo.time = TimeUtil.convertDate(System.currentTimeMillis());
        asyncUpload(commonInfo.data);
        commentAdapter.addData(commonInfo);
    }

    private void asyncUpload(String data) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                addFormDataPart("token", "123")
//                        .addFormDataPart("uuid", uuid)
//                        .addFormDataPart("data", data)
                uploadImage("http://45.77.191.48:9292/card/comment/new?token=123&uuid=" + messageInfo.getUuid() +
                        "&data=" + data, data, messageInfo.getUuid());
                e.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: Rxjava complete!");
                    }
                });
    }

    public static void uploadImage(String url, String data, String uuid) {


        try {

            RequestBody req = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("token", "123")
                    .addFormDataPart("uuid", uuid)
                    .addFormDataPart("data", data).build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(req)
                    .build();

            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();

            Log.d("response", "uploadImage:"+response.body().string());
        } catch (UnknownHostException | UnsupportedEncodingException e) {
            Log.e(TAG, "Error: " + e.getLocalizedMessage());
        } catch (Exception e) {
            Log.e(TAG, "Other Error: " + e.getLocalizedMessage());
        }
    }

}



