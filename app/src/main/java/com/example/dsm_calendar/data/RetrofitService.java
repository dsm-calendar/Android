package com.example.dsm_calendar.data;

import com.example.dsm_calendar.data.DTO.Event;
import com.example.dsm_calendar.data.DTO.Login;
import com.example.dsm_calendar.data.DTO.MainResponse;
import com.example.dsm_calendar.data.DTO.Message;
import com.example.dsm_calendar.data.DTO.Notice;
import com.example.dsm_calendar.data.DTO.Room;
import com.example.dsm_calendar.data.DTO.RoomInfo;
import com.example.dsm_calendar.data.DTO.RoomMember;
import com.example.dsm_calendar.data.DTO.Schedule;
import com.example.dsm_calendar.data.DTO.SchoolSchedule;
import com.example.dsm_calendar.data.DTO.Student;
import com.example.dsm_calendar.data.DTO.TimeTableUnit;
import com.example.dsm_calendar.data.DTO.User;

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
    @POST("join")
    Call<Void> signUp(@Body Student student);

    @POST("loginUser")
    Call<Student> login(@Body Login login);

    @GET("logout")
    Call<Void> logout(@Header("loginUserId") int token);

    @GET("main")
    Call<MainResponse> loadMainPage(@Header("loginUserId") int token);

    @POST("schedule/{calendarId}")
    Call<Void> addSchedule(@Path("calendarId") int calendarId, @Header("loginUserId") int token, @Body Schedule schedule);

    @GET("myCalendar")
    Call<ArrayList<Schedule>> getCalendar(@Header("loginUserId") int token);

    @DELETE("schedule/{scheduleId}")
    Call<Void> deleteCalendar(@Header("loginUserId") int token, @Path("scheduleId") int scheduleId);

    @GET("schoolCalendar")
    Call<SchoolSchedule> getSchoolCalendar(@Header("loginUserId") int token);

    @POST("notice/")
    Call<ArrayList<Notice>> getNoticeList();

    @POST("notice/addNotice")
    Call<Void> addNotice(@Body Notice notice);

    @DELETE("notice/delete/{noticeId}")
    Call<Void> deleteNotice(@Path("noticeId") int noticeId);

    @POST("timeTable")
    Call<ArrayList<TimeTableUnit>> updateTimetable(@Header("loginUserId") int token, @Body ArrayList<TimeTableUnit> timeTableUnits);

    @GET("timeTable")
    Call<ArrayList<TimeTableUnit>> getTimeTable(@Header("loginUserId") int token);

    @GET("message")
    Call<ArrayList<Message>> getMessage(@Header("loginUserId") int token);

    @POST("message/{messageId}")
    Call<Void> decideMessage(@Header("loginUserId") int token, @Path("messageId") int messageId, @Body boolean messageStatus);

    @DELETE("message/{messageId}")
    Call<Void> deleteMessage(@Header("loginUserId") int token, @Path("messageId") int messageId);

    @GET("myPage")
    Call<Void> getIcon(@Header("loginUserId") int token);

    @PUT("myPage")
    Call<Void> changeIcon(@Header("loginUserId") int token, @Body Student student);

    @GET("room")
    Call<ArrayList<Room>> getRoomList(@Header("loginUserId") int token);

    @POST("room")
    Call<ArrayList<Room>> createRoom(@Header("loginUserId") int token, @Body Room room);

    @GET("room/{roomId}")
    Call<ArrayList<Schedule>> getRoomSchedule(@Header("loginUserId") int token, @Path("roomId") int roomId);

    @GET("room/roomMember/{roomId}")
    Call<ArrayList<RoomMember>> getRoomMember(@Header("loginUserId") int token, @Path("roomId") int roomId);

    @POST("room/{roomId}")
    Call<Void> inviteMember(@Header("loginUserId") int token, @Path("roomId") int roomId, @Body User user);

    @PUT("room/{roomId}")
    Call<ArrayList<RoomMember>> updateMemberAuth(@Header("loginUserId") int token, @Path("roomId") int roomId, @Body RoomMember roomMember);

    @DELETE("room/roomMember/{roomId}/{roomMemberUserId}")
    Call<Void> deleteRoomMember(@Header("loginUserId") int token, @Path("roomId") int roomId, @Path("roomMemberUserId") int memberId);

    @DELETE("room/{roomId}")
    Call<Void> deleteRoom(@Header("loginUserId") int token, @Path("roomId") int roomId);

    @GET("event")
    Call<ArrayList<Event>> getEventList(@Header("loginUserId") int token);

    @GET("event/{eventId}")
    Call<Event> getEventDetail(@Header("loginUserId") int token, @Path("eventId") int eventId);

    @PUT("event/{eventId}")
    Call<Void> manageEvent(@Header("loginUserId") int token, @Path("eventId") int eventId, @Body Event event);
}
