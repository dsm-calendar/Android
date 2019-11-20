package com.example.dsm_calendar.data;

import android.content.Context;

import com.example.dsm_calendar.contract.SchoolScheduleContract;
import com.example.dsm_calendar.data.DTO.Schedule;
import com.example.dsm_calendar.data.DTO.SchoolSchedule;
import com.example.dsm_calendar.data.Singleton.CalendarRetrofit;
import com.example.dsm_calendar.data.Singleton.UserPreference;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchoolScheduleRepository implements SchoolScheduleContract.Repository {

    private Context context;
    private int token;

    public interface GetSchedulesListener{
        void onSuccess(ArrayList<Schedule> list);
        void onFail(String message);
    }

    public interface DeleteScheduleListener{
        void onSuccess();
        void onFail(String message);
    }

    public SchoolScheduleRepository(Context context) {
        this.context = context;
        this.token = UserPreference.getInstance(this.context).getUserID();
    }

    @Override
    public void getSchedules(GetSchedulesListener listener) {
        Call<SchoolSchedule> call = CalendarRetrofit.getInstance().getService().getSchoolCalendar(token);
        call.enqueue(new Callback<SchoolSchedule>() {
            @Override
            public void onResponse(Call<SchoolSchedule> call, Response<SchoolSchedule> response) {
                if (response.code() == 200){
                    ArrayList<Schedule> schedules = response.body().getSchedules();
                    if (response.body() != null){
                        for (int i = 0; i < schedules.size(); ++i){
                            schedules.get(i).setExpended(false);
                            schedules.get(i).setCalendarDay(schedules.get(i).getStartDate(), schedules.get(i).getEndDate());
                        }
                        if (UserPreference.getInstance(context).getIsAdmin()){
                            UserPreference.getInstance(context).putSchoolCalendarId("schoolCalendarId", response.body().getSchoolCalendarId());
                        }
                    }
                    listener.onSuccess(schedules);
                } else if(response.code() == 500){
                    listener.onFail("server error");
                } else {
                    listener.onFail(response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<SchoolSchedule> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void deleteSchedule(DeleteScheduleListener listener) {
        listener.onSuccess();
    }
}
