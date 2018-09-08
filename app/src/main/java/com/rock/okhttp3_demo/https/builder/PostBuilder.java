package com.rock.okhttp3_demo.https.builder;



import com.rock.okhttp3_demo.https.LogUtils;
import com.rock.okhttp3_demo.https.MyOkHttp;
import com.rock.okhttp3_demo.https.callback.MyCallback;
import com.rock.okhttp3_demo.https.response.IResponseHandler;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * post请求
 * post builder
 * Created by tsy on 16/9/18.
 */
public class PostBuilder extends OkHttpRequestBuilderHasParam<PostBuilder> {

    private String mJsonParams = "";

    public PostBuilder(MyOkHttp myOkHttp) {
        super(myOkHttp);
    }

    /**
     * json格式参数
     * @param json
     * @return
     */
    public PostBuilder jsonParams(String json) {
        this.mJsonParams = json;
        return this;
    }

    @Override
    public void enqueue(IResponseHandler responseHandler) {
        try {
            if(mUrl == null || mUrl.length() == 0) {
                throw new IllegalArgumentException("url can not be null !");
            }

            Request.Builder builder = new Request.Builder().url(mUrl);
            appendHeaders(builder, mHeaders);

            if (mTag != null) {
                builder.tag(mTag);
            }

            if(mJsonParams.length() > 0) {      //上传json格式参数
                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), mJsonParams);
                builder.post(body);
            } else {        //普通kv参数
                FormBody.Builder encodingBuilder = new FormBody.Builder();
                appendParams(encodingBuilder, mParams);
                builder.post(encodingBuilder.build());
            }

            Request request = builder.build();

            mMyOkHttp.getOkHttpClient()
                    .newCall(request)
                    .enqueue(new MyCallback(responseHandler));
        } catch (Exception e) {
            LogUtils.e("Post enqueue error:" + e.getMessage());
            responseHandler.onFailure(0, e.getMessage());
        }
    }

    //append params to form builder
    private void appendParams(FormBody.Builder builder, Map<String, String> params) {

        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
    }
}
