package com.example.dsm_calendar.data;

import android.content.Context;

import com.example.dsm_calendar.contract.TimeTableContract;
import com.example.dsm_calendar.data.DTO.TimeTableUnit;
import com.example.dsm_calendar.data.Singleton.CalendarRetrofit;
import com.example.dsm_calendar.data.Singleton.UserPreference;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimeTableRepository implements TimeTableContract.Repository {

    private Context context;

    public TimeTableRepository(Context context){
        this.context = context;
    }

    public interface GetTimeTableListener{
        void onSuccess(ArrayList<TimeTableUnit> tableUnits);
        void onFail(String message);
    }

    public interface EditSaveListener{
        void onSuccess(ArrayList<TimeTableUnit> timeTableUnits);
        void onFail(String message);
    }

    @Override
    public void getTimeTable(GetTimeTableListener listener) {
        int token = UserPreference.getInstance(context).getUserID();

        Call<ArrayList<TimeTableUnit>> call =  CalendarRetrofit.getInstance().getService().getTimeTable(token);
        call.enqueue(new Callback<ArrayList<TimeTableUnit>>() {
            @Override
            public void onResponse(Call<ArrayList<TimeTableUnit>> call, Response<ArrayList<TimeTableUnit>> response) {
                if (response.code() == 200){
                    listener.onSuccess(response.body());
                } else if(response.code() == 500){
                    listener.onFail("server error");
                } else {
                    listener.onFail("code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TimeTableUnit>> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void editSave(ArrayList<TimeTableUnit> timeTableUnits, EditSaveListener listener) {
        int token = UserPreference.getInstance(context).getUserID();

        Call<ArrayList<TimeTableUnit>> call = CalendarRetrofit.getInstance().getService().updateTimetable(token, timeTableUnits);
        call.enqueue(new Callback<ArrayList<TimeTableUnit>>() {
            @Override
            public void onResponse(Call<ArrayList<TimeTableUnit>> call, Response<ArrayList<TimeTableUnit>> response) {
                if (response.code() == 200){
                    listener.onSuccess(response.body());
                } else if (response.code() == 500){
                    listener.onFail("server error");
                } else {
                    listener.onFail("code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TimeTableUnit>> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }
}
