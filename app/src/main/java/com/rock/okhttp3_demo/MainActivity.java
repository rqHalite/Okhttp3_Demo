package com.rock.okhttp3_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rock.okhttp3_demo.app.MyApp;
import com.rock.okhttp3_demo.https.MyOkHttp;
import com.rock.okhttp3_demo.https.response.IResponseHandler;
import com.rock.okhttp3_demo.https.response.JsonResponseHandler;
import com.rock.okhttp3_demo.net.NetRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyOkHttp myOkHttp;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.request).setOnClickListener(this);
        txt = (TextView) findViewById(R.id.request_text);
        myOkHttp = MyApp.getInstance().getmMyOkHttp();
    }

    @Override
    public void onClick(View view) {
        NetRequest.getBackOrderMoney(this, myOkHttp, new JsonResponseHandler() {

            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                super.onSuccess(statusCode, response);
                String code = response.optString("code");
                txt.setText(response.toString());
                if ("200".equals(code)){
                    JSONArray jsonArray = response.optJSONArray("newslist");
                }

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }
}
