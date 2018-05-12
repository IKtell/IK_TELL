package com.robot.tuling;

import android.app.Application;
import android.graphics.Bitmap;

import java.io.FileOutputStream;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sunfusheng on 2018/5/10.
 */
public class TulingApplication extends Application {

    private static Retrofit sRetrofit;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Retrofit getRetrofit() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl("http://45.77.191.48:9090")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;

    }

}
