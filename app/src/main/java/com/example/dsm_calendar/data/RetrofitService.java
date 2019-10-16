package com.example.dsm_calendar.data;

import com.example.dsm_calendar.data.DTO.Login;
import com.example.dsm_calendar.data.DTO.LoginUserInfo;
import com.example.dsm_calendar.data.DTO.Student;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitService {
    @POST("join")
    Call<Void> signUp(@Body Student student);

    @POST("loginUser")
    Call<LoginUserInfo> login(@Body Login login);
}