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
import retrofit2.http.Header;
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

    @POST("timetable")
    Call<ArrayList<TimeTableUnit>> updateTimetable(@Header("Authorization") int token, @Body ArrayList<TimeTableUnit> timeTableUnits);

    @GET("timetable")
    Call<ArrayList<TimeTableUnit>> getTimeTable(@Header("Authorization") int token);

    @GET("message")
    Call<ArrayList<Message>> getMessage(@Header("Authorization") int token);

    @POST("message/{messageId}")
    Call<Void> decideMessage(@Header("Authorization") int token, @Path("messageId") int messageId, @Body boolean messageStatus);

    @DELETE("message/{messageId}")
    Call<Void> deleteMessage(@Header("Authorization") int token, @Path("messageId") int messageId);

    @GET("myPage")
    Call<Void> getIcon(@Header("Authorization") int token);

    @PUT("myPage")
    Call<Integer> changeIcon(@Header("Authorization") int token, int iconIndex);
}
