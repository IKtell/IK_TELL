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

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
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

    @BindView(R.id.comment)
    RecyclerView recyclerView;
    MessageInfo messageInfo;
    CommentAdapter commentAdapter;
    boolean isInit = false;
    String newtime;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.message_data)
    TextView messageData;
    @BindView(R.id.divider)
    View divider;
    @BindView(R.id.comment_num)
    TextView commentNum;
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
    private static final String TAG = "CardActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        ButterKnife.bind(this);
        messageInfo = (MessageInfo) getIntent().getSerializableExtra("msgInfo");
    }

    @Override
    protected void onStart() {

        super.onStart();
        ((TextView) findViewById(R.id.message_data)).setText(messageInfo.getData());
        ((TextView) findViewById(R.id.like_num)).setText(messageInfo.getLikeNum());
        ((TextView) findViewById(R.id.comment_num)).setText(messageInfo.getCommentNum());


    }

    private void initRecyclerView() {
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
                        url = new URL("http://45.77.191.48:9292/card/comment/history?token=123&uuid=" + messageInfo.getUuid() + "&index=" + date);
                    } else {
                        String time = TimeUtil.convertDate(System.currentTimeMillis());
                        url = new URL("http://45.77.191.48:9292/card/comment/history?token=123&uuid=" + messageInfo.getUuid() + "&index=" + time);
                    }
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    int c;
                    while ((c = is.read()) != -1) {
                        input.append((char) c);
                    }
                    conn.disconnect();
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<CommonInfo>>() {
                    }.getType();
                    List<CommonInfo> commonInfos = gson.fromJson(input.toString(), listType);
                    newtime = commonInfos.get(commonInfos.size() - 1).data;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (date == null) {
                                commentAdapter = new CommentAdapter(commonInfos);
                                isInit = true;
                            } else {
                                commentAdapter.addData(commonInfos);
                            }
                        }
                    });

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
        commonInfo.time = TimeUtil.convertDate(System.currentTimeMillis());
        asyncUpload(etMsg.getText().toString());
        commentAdapter.addData(commonInfo);
    }

    private void asyncUpload(String data) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                uploadImage("http://45.77.191.48:9292/card/comment/new", data, messageInfo.getUuid());
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



