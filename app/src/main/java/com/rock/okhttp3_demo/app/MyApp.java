package com.rock.okhttp3_demo.app;

import android.app.Application;

import com.rock.okhttp3_demo.https.MyOkHttp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Rock on 2018/4/28.
 */

public class MyApp extends Application {

    private MyOkHttp mMyOkHttp;
    private static MyApp instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //自定义OkHttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
//                .addInterceptor(logging)
                .build();
        mMyOkHttp = new MyOkHttp(okHttpClient);
    }

    public MyOkHttp getmMyOkHttp() {
        return mMyOkHttp;
    }

    public static MyApp getInstance() {
        if (instance == null) {
            instance = new MyApp();
        }
        return instance;
    }
}
