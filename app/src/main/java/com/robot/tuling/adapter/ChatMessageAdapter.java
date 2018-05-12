package com.robot.tuling.adapter;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Bitmap;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.library.bubbleview.BubbleTextVew;
import com.robot.tuling.R;
import com.robot.tuling.TulingApplication;
import com.robot.tuling.constant.TulingParams;
import com.robot.tuling.control.NavigateManager;
import com.robot.tuling.control.RetrofitApi;
import com.robot.tuling.entity.LoginEntity;
import com.robot.tuling.entity.MessageEntity;
import com.robot.tuling.util.SpecialViewUtil;
import com.robot.tuling.util.TimeUtil;

import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatMessageAdapter extends BaseListAdapter<MessageEntity> {

    private Context mContext;
    private String mAvatar;

    public static final int TYPE_LEFT = 0;
    public static final int TYPE_RIGHT = 1;

    public ChatMessageAdapter(Context context, List<MessageEntity> list, String avatar) {
        super(context, list);
        mContext = context;
        mAvatar = avatar;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).getType() == TYPE_LEFT) {
            return TYPE_LEFT;
        }
        return TYPE_RIGHT;
    }

    private View createViewByType(int position) {
        if (getItem(position).getType() == TYPE_LEFT) {
            return mInflater.inflate(R.layout.item_conversation_left, null);
        }
        return mInflater.inflate(R.layout.item_conversation_right, null);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = createViewByType(position);
        }

        final MessageEntity entity = getItem(position);

        TextView tvTime = ViewHolder.get(convertView, R.id.tv_time);
        BubbleTextVew btvMessage = ViewHolder.get(convertView, R.id.btv_message);

        ImageView avatar = ViewHolder.get(convertView, R.id.civ_avatar);
        if (getItem(position).getType() == TYPE_LEFT)
            avatar.setImageDrawable(ContextCompat.getDrawable(mContext, getAvatar()));
        else {
            RetrofitApi retrofitService = TulingApplication.getRetrofit().create(RetrofitApi.class);
//            Call<ResponseBody> callback = retrofitService.getAvatar("2016210001");
            Call<ResponseBody> callback = retrofitService.getAvatar(TulingParams.studentID);
            callback.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() != 200) {
                        return;
                    }
                    InputStream inputStream = response.body().byteStream();
                    Bitmap bm = BitmapFactory.decodeStream(inputStream);
                    avatar.setImageBitmap(bm);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }
        if (isDisplayTime(position)) {
            tvTime.setVisibility(View.VISIBLE);
            tvTime.setText(TimeUtil.friendlyTime(mContext, entity.getTime()));
        } else {
            tvTime.setVisibility(View.GONE);
        }

        switch (entity.getCode()) {
            case TulingParams.TulingCode.URL:
                btvMessage.setText(SpecialViewUtil.getSpannableString(entity.getText(), entity.getUrl()));
                break;
            case TulingParams.TulingCode.NEWS:
                btvMessage.setText(SpecialViewUtil.getSpannableString(entity.getText(), "点击查看"));
                break;
            default:
                btvMessage.setText(entity.getText());
                break;
        }

        btvMessage.setOnClickListener(v -> {
            switch (entity.getCode()) {
                case TulingParams.TulingCode.URL:
                    NavigateManager.gotoDetailActivity(mContext, entity.getUrl());
                    break;
                case TulingParams.TulingCode.NEWS:
                    NavigateManager.gotoNewsActivity(mContext, entity);
                    break;
            }
        });

        btvMessage.setOnLongClickListener(v -> {
            copyDeleteDialog(mContext, entity);
            return false;
        });

        return convertView;
    }

    private int getAvatar() {
        switch (mAvatar) {
            default:
            case "girl1":
                return R.drawable.girl1;
            case "girl2":
                return R.drawable.girl2;
            case "girl3":
                return R.drawable.girl3;
            case "boy1":
                return R.drawable.boy1;
            case "boy2":
                return R.drawable.boy2;
            case "boy3":
                return R.drawable.boy3;
        }
    }

    //  一分钟内的请求与回复不显示时间
    public boolean isDisplayTime(int position) {
        if (position > 0) {
            if ((getItem(position).getTime() - getItem(position-1).getTime()) > 60 * 1000) {
                return true;
            } else {
                return false;
            }
        } else if (position == 0) {
            return true;
        } else {
            return false;
        }
    }

    private void copyDeleteDialog(Context context, MessageEntity entity) {
        new MaterialDialog.Builder(context)
                .items("复制该文本", "删除这一条")
                .itemsCallback((dialog, view, which, text) -> {
                    switch (which) {
                        case 0:
                            ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                            cm.setText(entity.getText());
                            Toast.makeText(context, "已复制", Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            getData().remove(entity);
                            notifyDataSetChanged();
                            break;
                    }
                })
                .show();
    }

}
