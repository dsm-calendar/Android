package com.example.dsm_calendar.data;

import android.content.Context;

import com.example.dsm_calendar.contract.GroupFragmentContract;
import com.example.dsm_calendar.data.DTO.Room;
import com.example.dsm_calendar.data.Singleton.CalendarRetrofit;
import com.example.dsm_calendar.data.Singleton.UserPreference;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupFragmentRepository implements GroupFragmentContract.Repository {

    private Context context;
    private int token;

    public interface GetGroupListListener{
        void onSuccess(ArrayList<Room> rooms);
        void onFail(String message);
    }

    public interface AddGroupListener{
        void onSuccess(ArrayList<Room> rooms);
        void onFail(String message);
    }

    public interface DeleteGroupListener{
        void onSuccess();
        void onFail(String message);
    }

    public GroupFragmentRepository(Context context) {
        this.context = context;
        this.token = UserPreference.getInstance(this.context).getUserID();
    }

    @Override
    public void getGroupList(GetGroupListListener listener) {
        Call<ArrayList<Room>> call = CalendarRetrofit.getInstance().getService().getRoomList(token);
        call.enqueue(new Callback<ArrayList<Room>>() {
            @Override
            public void onResponse(Call<ArrayList<Room>> call, Response<ArrayList<Room>> response) {
                if (response.code() == 200){
                    listener.onSuccess(response.body());
                } else if (response.code() == 500){
                    listener.onFail("server error");
                } else {
                    listener.onFail(response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Room>> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void addGroup(Room room, AddGroupListener listener) {
        Call<ArrayList<Room>> call = CalendarRetrofit.getInstance().getService().createRoom(token, room);
        call.enqueue(new Callback<ArrayList<Room>>() {
            @Override
            public void onResponse(Call<ArrayList<Room>> call, Response<ArrayList<Room>> response) {
                if (response.code() == 200){
                    listener.onSuccess(response.body());
                } else if (response.code() == 500){
                    listener.onFail("server error");
                } else {
                    listener.onFail(response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Room>> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void deleteGroup(int roomId, DeleteGroupListener listener) {
        Call<Void> call = CalendarRetrofit.getInstance().getService().deleteRoom(token, roomId);
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
