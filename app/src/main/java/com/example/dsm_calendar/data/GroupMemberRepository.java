package com.example.dsm_calendar.data;

import android.content.Context;
import android.util.Log;

import com.example.dsm_calendar.contract.GroupMemberContract;
import com.example.dsm_calendar.data.DTO.RoomInfo;
import com.example.dsm_calendar.data.DTO.RoomMember;
import com.example.dsm_calendar.data.DTO.User;
import com.example.dsm_calendar.data.Singleton.CalendarRetrofit;
import com.example.dsm_calendar.data.Singleton.UserPreference;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupMemberRepository implements GroupMemberContract.Repository {

    private Context context;
    private int token;

    public GroupMemberRepository(Context context) {
        this.context = context;
        this.token = UserPreference.getInstance(this.context).getUserID();
    }

    public interface GetMemberListListener{
        void onSuccess(ArrayList<RoomMember> members);
        void onFail(String message);
    }

    public interface InviteMemberListener{
        void onSuccess();
        void onFail(String message);
    }

    public interface ChangeMemberAuthListener{
        void onSuccess(ArrayList<RoomMember> members);
        void onFail(String message);
    }

    public interface KickMemberListener{
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void getMemberList(int roomId, GetMemberListListener listener) {
        Call<ArrayList<RoomMember>> call = CalendarRetrofit.getInstance().getService().getRoomMember(token, roomId);
        call.enqueue(new Callback<ArrayList<RoomMember>>() {
            @Override
            public void onResponse(Call<ArrayList<RoomMember>> call, Response<ArrayList<RoomMember>> response) {
                if (response.code() == 200){
                    listener.onSuccess(response.body());
                } else if (response.code() == 500){
                    listener.onFail("server error");
                } else{
                    listener.onFail(response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RoomMember>> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void inviteMember(int roomId, String userId, InviteMemberListener listener) {
        User user = new User();
        user.setId(userId);
        Call<Void> call = CalendarRetrofit.getInstance().getService().inviteMember(token, roomId, user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200){
                    listener.onSuccess();
                } else if (response.code() == 500){
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

    @Override
    public void changeMemberAuth(int roomId, int memberId, int authCode, ChangeMemberAuthListener listener) {
        RoomMember member = new RoomMember();
        member.setMemberRight(authCode);
        member.setMemberId(memberId);
        Call<ArrayList<RoomMember>> call = CalendarRetrofit.getInstance().getService().updateMemberAuth(token, roomId, member);
        call.enqueue(new Callback<ArrayList<RoomMember>>() {
            @Override
            public void onResponse(Call<ArrayList<RoomMember>> call, Response<ArrayList<RoomMember>> response) {
                if (response.code() == 200){
                    listener.onSuccess(response.body());
                } else if (response.code() == 500){
                    listener.onFail("server error");
                } else {
                    listener.onFail(response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RoomMember>> call, Throwable t) {
                listener.onFail(t.getMessage());
                Log.d("mydebug", t.getMessage());
            }
        });
    }

    @Override
    public void kickMember(int roomId, int memberId, KickMemberListener listener) {
        Call<Void> call = CalendarRetrofit.getInstance().getService().deleteRoomMember(token, roomId, memberId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200){
                    listener.onSuccess();
                } else if (response.code() == 500){
                    listener.onFail("server error");
                } else{
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
