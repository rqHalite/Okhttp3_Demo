package com.rock.okhttp3_demo.net;

import android.app.Activity;

import com.rock.okhttp3_demo.https.MyOkHttp;
import com.rock.okhttp3_demo.https.response.IResponseHandler;

/**
 * Created by Rock on 2018/4/28.
 */

public class NetRequest {
    public static  String URL = "http://api.tianapi.com/social/?key=71e58b5b2f930eaf1f937407acde08fe&num=20";


    public static void getBackOrderMoney(Activity activity, MyOkHttp myOkHttp, IResponseHandler iResponseHandler) {
        myOkHttp.post()
                .url(URL)
//                .addParam("userId", userId)
//                .addParam("money", money)
//                .addParam("voucher_id", couponId)
                .tag(activity)
                .enqueue(iResponseHandler);
    }
}
