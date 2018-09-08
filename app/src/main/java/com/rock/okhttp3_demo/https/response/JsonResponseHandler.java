package com.rock.okhttp3_demo.https.response;




import com.rock.okhttp3_demo.https.CrashHandler;
import com.rock.okhttp3_demo.https.LogUtils;
import com.rock.okhttp3_demo.https.MyOkHttp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * json类型的回调接口
 * Created by tsy on 16/8/15.
 */
public abstract class JsonResponseHandler implements IResponseHandler {

    @Override
    public final void onSuccess(final Response response) {
        ResponseBody responseBody = response.body();
        String responseBodyStr = "";

        try {
            responseBodyStr = responseBody.string();
        } catch (final IOException e) {
            e.printStackTrace();
            LogUtils.e("onResponse fail read response body");

            MyOkHttp.mHandler.post(new Runnable() {
                @Override
                public void run() {
//                    onFailure(response.code(), "fail read response body");
                    CrashHandler.outputTextFile("错误信息:" + e.toString());
                }
            });
            return;
        } finally {
            responseBody.close();
        }

        final String finalResponseBodyStr = responseBodyStr;

        try {
            final Object result = new JSONTokener(finalResponseBodyStr).nextValue();
            if(result instanceof JSONObject) {
                MyOkHttp.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onSuccess(response.code(), (JSONObject) result);
                    }
                });
            } else if(result instanceof JSONArray) {
                MyOkHttp.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onSuccess(response.code(), (JSONArray) result);
                    }
                });
            } else {
                LogUtils.e("onResponse fail parse jsonobject, body=" + finalResponseBodyStr);
                MyOkHttp.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onFailure(response.code(), "fail parse jsonobject, body=" + finalResponseBodyStr);
                        onFailure(response.code(), "fail parse jsonobject, body=" + finalResponseBodyStr);
                    }
                });
            }
        } catch (final JSONException e) {
            e.printStackTrace();
            LogUtils.e("onResponse fail parse jsonobject, body=" + finalResponseBodyStr);
            MyOkHttp.mHandler.post(new Runnable() {
                @Override
                public void run() {
//                    onFailure(response.code(), "fail parse jsonobject, body=" + finalResponseBodyStr);
                    CrashHandler.outputTextFile("错误信息:" + e.toString());
                }
            });
        }
    }
    public void onSuccess(int statusCode, JSONObject response) {
        LogUtils.w("onSuccess(int statusCode, JSONObject response) was not overriden, but callback was received");
    }

    public void onSuccess(int statusCode, JSONArray response) {
        LogUtils.w("onSuccess(int statusCode, JSONArray response) was not overriden, but callback was received");
    }

    public void jsonOnProgress(long currentBytes, long totalBytes) {

    }
        @Override
    public void onProgress(final long currentBytes, final long totalBytes) {
        MyOkHttp.mHandler.post(new Runnable() {
            @Override
            public void run() {
                jsonOnProgress(currentBytes, totalBytes);
            }
        });
    }

}