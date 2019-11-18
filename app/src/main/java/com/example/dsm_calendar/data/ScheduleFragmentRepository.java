package com.example.dsm_calendar.data;

import android.content.Context;

import com.example.dsm_calendar.contract.ScheduleFragmentContract;
import com.example.dsm_calendar.data.DTO.Schedule;
import com.example.dsm_calendar.data.Singleton.CalendarRetrofit;
import com.example.dsm_calendar.data.Singleton.UserPreference;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleFragmentRepository implements ScheduleFragmentContract.Repository {

    private Context context;
    private int token;

    public ScheduleFragmentRepository(Context context){
        this.context  = context;
        this.token = UserPreference.getInstance(this.context).getUserID();
    }

    public interface GetScheduleListListener{
        void onSuccess(ArrayList<Schedule> testSchedule);
        void onFail(String message);
    }

    public interface DeleteScheduleListener{
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void getScheduleList(GetScheduleListListener listener) {
//        ArrayList<Schedule> testSchedule = new ArrayList<>();
//
//        testSchedule.add(new Schedule("sample title1", "2019-11-1", "2019-11-1", "sample content1"));
//        testSchedule.add(new Schedule("sample title2", "2019-11-3", "2019-11-10", "sample content2"));
//        testSchedule.add(new Schedule("sample title3", "2019-11-4", "2019-11-5", "sample content3"));
//        testSchedule.add(new Schedule("sample title4", "2019-11-10", "2019-11-10", "sample content4"));
//        //month should be -1 to decorate correctly

        Call<ArrayList<Schedule>> call = CalendarRetrofit.getInstance().getService().getCalendar(token);
        call.enqueue(new Callback<ArrayList<Schedule>>() {
            @Override
            public void onResponse(Call<ArrayList<Schedule>> call, Response<ArrayList<Schedule>> response) {
                if (response.code() == 200){
                    listener.onSuccess(response.body());
                    UserPreference.getInstance(context).putCalendarID("calendarID", response.body().get(0).getCalendarId());
                } else if(response.code() == 500){
                    listener.onFail("server error");
                } else {
                    listener.onFail(response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Schedule>> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void deleteSchedule(int scheduleId, DeleteScheduleListener listener) {
        Call<Void> call = CalendarRetrofit.getInstance().getService().deleteCalendar(token, scheduleId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200){
                    listener.onSuccess();
                } else if(response.code() == 500){
                    listener.onFail("server error");
                } else {
                    listener.onFail(response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }
}
