package com.example.dsm_calendar.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CalendarRetrofit {
    private static CalendarRetrofit instance = new CalendarRetrofit();
    public static CalendarRetrofit getInstance() {
        return instance;
    }

    private CalendarRetrofit() {

    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.156.145.132:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    RetrofitService retrofitService = retrofit.create(RetrofitService.class);

    public RetrofitService getService(){
        return retrofitService;
    }
}
