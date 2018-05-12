package com.robot.tuling.control;

import com.robot.tuling.entity.LoginEntity;
import com.robot.tuling.entity.MessageEntity;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by sunfusheng on 2016/3/20.
 */
public interface RetrofitApi {

    // 请求图灵API接口，获得问答信息
    @GET("api")
    Call<MessageEntity> getTuringInfo(@Query("key") String key, @Query("info") String info);

    // 请求图灵API接口，获得问答信息
    @GET("api")
    Observable<MessageEntity> getTuringInfoByRxJava(@Query("key") String key, @Query("info") String info);

    // 登录接口
    @POST("login/submit")
    Call<LoginEntity> login(@Query("studentID") String id, @Query("password") String password);

    // 学生头像
//    @GET("static/pics/{studentID}.jpg")
//    Call<ResponseBody> getAvatar(@Field("studentID") String studentID);
    //http://45.77.191.48:9090/static/pics/2016210002.jpg
    @GET("static/pics/{studentID}.jpg")
    Call<ResponseBody> getAvatar(@Path("studentID") String id);
}
