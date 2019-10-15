package com.example.dsm_calendar.data;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {
    @FormUrlEncoded
    @POST("join")
    Call<Void> signUp(@Body Student student);

    @FormUrlEncoded
    @POST("loginStudent")
    Call<Void> login(@Body Login login);
}
