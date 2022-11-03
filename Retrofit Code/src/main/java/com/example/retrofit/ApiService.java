package com.example.retrofit;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/posts/1/comments")
    Call<List<Comments>> getAllComments();
}
