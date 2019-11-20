package com.example.dsm_calendar.data;

import android.content.Context;

import com.example.dsm_calendar.contract.MainContract;
import com.example.dsm_calendar.data.DTO.Student;
import com.example.dsm_calendar.data.Singleton.CalendarRetrofit;
import com.example.dsm_calendar.data.Singleton.UserPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository implements MainContract.Repository {

    private Context context;
    private int token;

    public interface GetUserInfoListener{
        void onSuccess(String id, int classOf, int iconIndex);
        void onFail(String message);
    }

    public interface ChangeProfileListener{
        void onSuccess();
        void onFail(String message);
    }

    public interface LogoutListener{
        void onSuccess();
        void onFail(String message);
    }

    public MainRepository(Context context){
        this.context = context;
        this.token = UserPreference.getInstance(this.context).getUserID();
    }

    @Override
    public void getUserInfo(GetUserInfoListener listener) {
        UserPreference preferences = UserPreference.getInstance(context);
        String id = preferences.getID();
        int classOf = preferences.getClassOf();
        int iconIndex = preferences.getIconIndex();

        if(id.equals("")){
            listener.onFail("loading fail");
        } else {
            listener.onSuccess(id, classOf, iconIndex);
        }
    }

    @Override
    public void changeProfile(Student student, ChangeProfileListener listener) {
        Call<Void> call = CalendarRetrofit.getInstance().getService().changeIcon(token, student);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200){
                    listener.onSuccess();
                    UserPreference.getInstance(context).putIconIndex("iconIndex", student.getIconIndex());
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

    @Override
    public void logout(LogoutListener listener) {
        Call<Void> call = CalendarRetrofit.getInstance().getService().logout(token);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200){
                    UserPreference preference = UserPreference.getInstance(context);
                    preference.clear();
                    listener.onSuccess();
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
