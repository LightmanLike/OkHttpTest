package com.example.okhttptest;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Light on 2017/6/5.
 */

public class HttpTool {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static String txt = "";
    public static Response PostStr(String json, final String post_path) {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(post_path)
                .post(RequestBody.create(JSON, json)).addHeader("token", "helloworld")
                .build();

        Response response = null;
        try {
            response = mOkHttpClient.newCall(request).execute();
            System.out.println("_____response____" + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
    public static String GetStr(final String get_path) {
        final Response[] post = new Response[1];
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request

        final Request request = new Request.Builder()
                .url(get_path)
                .build();
        //new call
        okhttp3.Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                txt = response.body().string();
            }

        });
        return txt;
    }
}
