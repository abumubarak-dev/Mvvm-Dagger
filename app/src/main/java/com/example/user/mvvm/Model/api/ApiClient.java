package com.example.user.mvvm.Model.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;
    private static ApiClient INSTANCE;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public ApiClient () {
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ApiClient getINSTANCE() {
        if (INSTANCE==null){
            INSTANCE=new ApiClient();
        }
        return INSTANCE;
    }

    public  ApiEndPoint getApiEndPoint(){
        return retrofit.create(ApiEndPoint.class);
    }

}
