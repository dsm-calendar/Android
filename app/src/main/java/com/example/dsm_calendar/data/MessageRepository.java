package com.example.dsm_calendar.data;

import android.content.Context;

import com.example.dsm_calendar.contract.MessageContract;
import com.example.dsm_calendar.data.DTO.Message;
import com.example.dsm_calendar.data.Singleton.CalendarRetrofit;
import com.example.dsm_calendar.data.Singleton.UserPreference;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageRepository implements MessageContract.Repository {

    private Context context;
    private int token;

    public MessageRepository(Context context){
        this.context = context;
        this.token = UserPreference.getInstance(this.context).getUserID();
    }

    public interface GetMessageListListener{
        void onSuccess(ArrayList<Message> messageList);
        void onFail(String message);
    }

    public interface AcceptInviteListener{
        void onSuccess();
        void onFail(String message);
    }

    public interface RejectInviteListener{
        void onSuccess();
        void onFail(String message);
    }

    public interface DeleteMessageListener{
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void getMessageList(GetMessageListListener listener) {
        Call<ArrayList<Message>> call = CalendarRetrofit.getInstance().getService().getMessage(token);
        call.enqueue(new Callback<ArrayList<Message>>() {
            @Override
            public void onResponse(Call<ArrayList<Message>> call, Response<ArrayList<Message>> response) {
                if (response.code() == 200){
                    listener.onSuccess(response.body());
                } else if (response.code() == 500){
                    listener.onFail("server error");
                } else {
                    listener.onFail("code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Message>> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void acceptInvite(int messageId, AcceptInviteListener listener) {
        Call<Void> call = CalendarRetrofit.getInstance().getService().decideMessage(token, messageId, true);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200){
                    listener.onSuccess();
                } else if (response.code() == 500){
                    listener.onFail("server error");
                } else {
                    listener.onFail("code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void rejectInvite(int messageId, RejectInviteListener listener) {
        Call<Void> call = CalendarRetrofit.getInstance().getService().decideMessage(token, messageId, false);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200){
                    listener.onSuccess();
                } else if (response.code() == 500){
                    listener.onFail("server error");
                } else {
                    listener.onFail("code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void deleteMessage(int messageId, DeleteMessageListener listener) {
        int token = UserPreference.getInstance(context).getUserID();
        Call<Void> call = CalendarRetrofit.getInstance().getService().deleteMessage(token, messageId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200){
                    listener.onSuccess();
                } else if (response.code() == 500){
                    listener.onFail("server error");
                } else {
                    listener.onFail("code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }
}
