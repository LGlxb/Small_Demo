package small.com.small_demo.utils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ConnectionPool;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {
    //提供一个本地工具类
    public static OkHttpUtils okHttpUtils;
    private final Handler handler;
    private final OkHttpClient httpClient;

    //提供公有方法供外部类使用
    public static OkHttpUtils getOkHttpUtils() {
        //DCL模式 懒汉式
        if (okHttpUtils == null) {
            synchronized (OkHttpUtils.class) {//线程锁
                if (okHttpUtils == null) {
                    return okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }

    //私有化构造函数
    private OkHttpUtils() {
        //主线程handler
        handler = new Handler(Looper.getMainLooper());
        httpClient = new OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS))
                //App拦截器
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        HttpUrl url = request.url();
                        String s = url.url().toString();
                        //---------请求之前-----
                        Log.d("Interceptor", "app interceptor:begin");
                        Response response = chain.proceed(request);
                        //请求之后
                        Log.d("Interceptor", "app interceptor:end");
                        return response;
                    }
                })
                //网络拦截器
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        //---------请求之前-----
                        Log.d("Interceptor", "network interceptor:begin");
                        Response response = chain.proceed(request);
                        //---------请求之后-----
                        Log.d("Interceptor", "network interceptor:end");
                        return response;
                    }
                })
                .build();
    }

    //异步get请求
    public void doGet(String url, final IOKHttpUtilsCallBack iokHttpUtilsCallBack) {
        Request request = new Request.Builder().url(url).build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (iokHttpUtilsCallBack == null) {
                    iokHttpUtilsCallBack.onFailure(e.getMessage());
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    final String json = response.body().string();
                    if (iokHttpUtilsCallBack != null) {
                        //切换到主线程
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                iokHttpUtilsCallBack.onResponse(json);
                            }
                        });
                    }

                } else {
                    if (iokHttpUtilsCallBack != null) {
                        //切换到主线程
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                iokHttpUtilsCallBack.onFailure("网络异常");
                            }
                        });
                    }
                }
            }
        });
    }

    //异步post
    public void doPost(String url, Map<String, String> map, final IOKHttpUtilsCallBack
            iokHttpUtilsCallBack) {
        FormBody.Builder builder = new FormBody.Builder();
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            builder.add(entry.getKey(), entry.getValue());
//            Log.d("OkHttpUtils", entry.getKey() + "++++logkey");
//            Log.d("OkHttpUtils", entry.getValue() + "+++++logvalue");
//        }
        String phone = map.get("phone");
        String pwd = map.get("pwd");
        builder.add(phone, pwd);
        Log.d("OkHttpUtils", phone.toString() + "++++postkey");
        Log.d("OkHttpUtils", pwd.toString() + "++++postkey");
        FormBody formBody = builder.build();
        Log.d("OkHttpUtils", "formBody:" + formBody + "++++formbody");
        Request request = new Request.Builder().post(formBody).url(url).build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {

                if (iokHttpUtilsCallBack != null) {
                    //切换到主线程
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("ok1111", e.getMessage());
                            iokHttpUtilsCallBack.onFailure(e.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                String json = response.body().string();
//                Log.d("OkHttpUtils", json.toString() + "json+++++");
//                if (iokHttpUtilsCallBack != null) {
//                    //切换到主线程
//                    iokHttpUtilsCallBack.onResponse(json);
//                }
                if (response != null && response.isSuccessful()) {
                    final String json = response.body().string();
                    if (iokHttpUtilsCallBack != null) {
                        //切换到主线程
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                iokHttpUtilsCallBack.onResponse(json);
                            }
                        });

                    }
                } else {
                    if (iokHttpUtilsCallBack != null) {
                        //切换到主线程
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                iokHttpUtilsCallBack.onFailure("网络异常");
                            }
                        });
                    }
                }
            }
        });
    }

    //接口回调
    public interface IOKHttpUtilsCallBack {
        void onFailure(String error);

        void onResponse(String json);
    }
}
