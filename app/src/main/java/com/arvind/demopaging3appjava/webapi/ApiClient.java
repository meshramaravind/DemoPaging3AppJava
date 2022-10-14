package com.arvind.demopaging3appjava.webapi;

import static com.arvind.demopaging3appjava.utils.Constants.API_KEY;
import static com.arvind.demopaging3appjava.utils.Constants.BASE_URL;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    // Define APIInterface
    static ApiService apiInterface;
    // create retrofit instance
    public static ApiService getApiService() {
        if (apiInterface == null) {
            // Create OkHttp Client
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            // Add interceptor to add API key as query string parameter to each request
            client.addInterceptor(chain -> {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();
                HttpUrl url = originalHttpUrl.newBuilder()
                        // Add API Key as query string parameter
                        .addQueryParameter("api_key", API_KEY)
                        .build();
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            });
            // Create retrofit instance
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client.build())
                    // Add Gson converter
                    .addConverterFactory(GsonConverterFactory.create())
                    // Add RxJava spport for Retrofit
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
            // Init APIInterface
            apiInterface = retrofit.create(ApiService.class);
        }
        return apiInterface;
    }
}
