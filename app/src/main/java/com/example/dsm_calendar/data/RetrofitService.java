package com.example.dsm_calendar.data;

import com.example.dsm_calendar.data.DTO.Login;
import com.example.dsm_calendar.data.DTO.LoginUserInfo;
import com.example.dsm_calendar.data.DTO.Message;
import com.example.dsm_calendar.data.DTO.Notice;
import com.example.dsm_calendar.data.DTO.Schedule;
import com.example.dsm_calendar.data.DTO.Student;
import com.example.dsm_calendar.data.DTO.TimeTableUnit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitService {
    @POST("auth/resister")
    Call<Void> signUp(@Body Student student);

    @POST("auth/login")
    Call<LoginUserInfo> login(@Body Login login);

    @POST("calendar/addSchedule/{scheduleId}")
    Call<Void> addSchedule(@Path("scheduleId") int scheduleId, @Body Schedule schedule);

    @GET("calendar/{scheduleId}")
    Call<ArrayList<Schedule>> getCalendar(@Path("scheduleId") int scheduleId);

    @DELETE("calendar/deleteSchedule/{scheduleId}")
    Call<Void> deleteCalendar(@Path("scheduleId") int scheduleId);

    @POST("notice/")
    Call<ArrayList<Notice>> getNoticeList();

    @POST("notice/addNotice")
    Call<Void> addNotice(@Body Notice notice);

    @DELETE("notice/delete/{noticeId}")
    Call<Void> deleteNotice(@Path("noticeId") int noticeId);

    @PUT("timetable/updateTimetable")
    Call<Void> updateTimetable(@Body TimeTableUnit timeTableUnit);

    @GET("timetable/")
    Call<ArrayList<TimeTableUnit>> getTimeTalbe();

    @GET("message")
    Call<ArrayList<Message>> getMessage();

    @DELETE("message/{messageId}")
    Call<Void> deleteMessage(@Path("messageId") int messageId);
}
