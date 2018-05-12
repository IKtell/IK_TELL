package com.robot.tuling.ui;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.robot.tuling.R;
import com.robot.tuling.adapter.CommentAdapter;
import com.robot.tuling.beans.MessageInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;

/**
 * Created by ZDL on 2018/5/12.
 */

public class CardActivity extends AppCompatActivity {

    @BindView(R.id.comment)
    RecyclerView recyclerView;

    MessageInfo messageInfo;
    JSONObject jsonObject;
    JSONArray comments;

    CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        messageInfo = (MessageInfo) getIntent().getSerializableExtra("msgInfo");
    }

    @Override
    protected void onStart() {

        super.onStart();
        ((TextView) findViewById(R.id.message_data)).setText(messageInfo.getData());
        ((TextView) findViewById(R.id.like_num)).setText(messageInfo.getLikeNum());
        ((TextView) findViewById(R.id.comment_num)).setText(messageInfo.getCommentNum());



    }


    //===以下未测试

    class GetCommentTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            StringBuilder input = new StringBuilder();

            try {
                URL url = new URL("http://45.77.191.48:9292/card/comment/history?token=123&uuid=" + messageInfo.getUuid() + "&index=0");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                InputStream is = conn.getInputStream();
                int c;
                while((c = is.read()) != -1) {
                    input.append((char) c);
                }
                conn.disconnect();
                jsonObject = new JSONObject(input.toString());

                comments = jsonObject.getJSONArray("comments");

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                adapter = new CommentAdapter(comments);
                recyclerView.setAdapter(adapter);
                if (comments.length() == 0) Toast.makeText(CardActivity.this, "暂无评论", Toast.LENGTH_SHORT).show();

            }
        }
    }
}

