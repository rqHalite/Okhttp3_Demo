package com.rock.okhttp3_demo.https.callback;




import com.rock.okhttp3_demo.https.HttpRequest;
import com.rock.okhttp3_demo.https.LogUtils;
import com.rock.okhttp3_demo.https.MyOkHttp;
import com.rock.okhttp3_demo.https.response.IResponseHandler;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by tsy on 16/9/18.
 */
public class MyCallback implements Callback {

    private IResponseHandler mResponseHandler;

    public MyCallback(IResponseHandler responseHandler) {
        mResponseHandler = responseHandler;
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        LogUtils.e("onFailure", e);

        MyOkHttp.mHandler.post(new Runnable() {
            @Override
            public void run() {
                String error= HttpRequest.getExceptionInfo(e);
                mResponseHandler.onFailure(0, error);
            }
        });
    }

    @Override
    public void onResponse(Call call, final Response response) {
        if(response.isSuccessful()) {
            mResponseHandler.onSuccess(response);
        } else {
            LogUtils.e("onResponse fail status=" + response.code());

            MyOkHttp.mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mResponseHandler.onFailure(response.code(), "fail status=" + response.code());
                }
            });
        }
    }
}
