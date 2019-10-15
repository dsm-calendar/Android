package com.example.dsm_calendar.data;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {
    @FormUrlEncoded
    @POST("join")
    Call<Void> signUp(@Field("classOf") int std_no, @Field("iconIndex") int index, @Field("id") String id, @Field("pw") String password);
}
