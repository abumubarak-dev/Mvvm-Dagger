package com.example.user.mvvm.Model.api;

import com.example.user.mvvm.Model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndPoint {

    @GET("/posts")
    Call<List<Post>> getPostCall();
}
