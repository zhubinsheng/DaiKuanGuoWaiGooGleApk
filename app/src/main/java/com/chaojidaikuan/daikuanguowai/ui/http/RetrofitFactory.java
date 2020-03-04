package com.chaojidaikuan.daikuanguowai.ui.http;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitFactory {
    //baseUrl
    private Retrofit retrofit;
    public Retrofit getRetrofit(){
        if(retrofit==null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.SECONDS)//设置请求超时
                    .readTimeout(10, TimeUnit.SECONDS)//设置读取超时
                    .addInterceptor(new LogInterceptor())
                    .build();
            //初始化Retrofit
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConstant.ROOT_URL)
                    .addConverterFactory(StringConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getRetrofit(long connectTimeout,long readTimeout){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)//设置请求超时
                .readTimeout(readTimeout, TimeUnit.SECONDS)//设置读取超时
                .addInterceptor(new LogInterceptor())
                .build();
        //初始化Retrofit
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(ApiConstant.ROOT_URL)
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit1;
    }






    /**
     * Log拦截器代码
     */
    public static class LogInterceptor implements Interceptor {
        private String TAG = "okhttp";
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            okhttp3.Response response = chain.proceed(chain.request());
            long t2 = System.nanoTime();
//            LogUtil.e("miehahah:" + String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            okhttp3.MediaType mediaType = response.body().contentType();
            String head = response.headers().toString();
            String content = response.body().string();
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        }
    }

}
