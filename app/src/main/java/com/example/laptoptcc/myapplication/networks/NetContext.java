package com.example.laptoptcc.myapplication.networks;

import android.util.Log;

import com.example.laptoptcc.myapplication.setting.SharePref;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by laptopTCC on 4/18/2017.
 */

public class NetContext {
    public static final NetContext instance = new NetContext();
    private Retrofit retrofit;

    private NetContext(){
        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(new LoggerInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://retrofitserver.herokuapp.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public <T> T create(Class<T> classz) {return retrofit.create(classz);}

    class HeaderInterceptor implements Interceptor{
        private static final String TAG = "loggerInterceptor";
        @Override
        public Response intercept(Chain chain) throws IOException {
            String token = SharePref.instance.getAccessToken();
            //1.get request
            if(token != null){
            Log.d(TAG,String.format("Accesstoken: %s", token));
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization",String.format("JWT %s", token))
                    .build();
                return chain.proceed(request);
            }
            //2.process request
            return chain.proceed(chain.request());

        }
    }

    class LoggerInterceptor implements Interceptor{
        private static final String TAG = "LoggerInterceptor";

        @Override
        public Response intercept(Chain chain) throws IOException {
            //1. Get request
            Request request = chain.request();
            //2. Process request (print out).
            Log.d(TAG,String.format("url: %s",request.toString()));

            RequestBody body = request.body();

            Headers header = request.headers();
            if(body != null){
                Log.d(TAG,String.format("headers: %s", header.toString()));
            }
            //3. Process chain
            Response response = chain.proceed(request);
            //4. Process response
            Log.d(TAG, String.format("response: %s", response.toString()));
            return response;
        }
    }

}
