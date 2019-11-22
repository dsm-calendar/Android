package com.example.dsm_calendar.data;

import android.content.Context;

import com.example.dsm_calendar.contract.GroupMemberContract;
import com.example.dsm_calendar.data.DTO.RoomMember;
import com.example.dsm_calendar.data.DTO.Student;
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
        void onFail();
    }

    public interface InviteMemberListener{
        void onSuccess();
        void onFail(String message);
    }

    public interface ChangeMemberAuthListener{
        void onSuccess();
        void onFail(String message);
    }

    public interface KickMemberListener{
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void getMemberList(GetMemberListListener listener) {
        listener.onSuccess(new ArrayList<RoomMember>());
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
    public void changeMemberAuth(int roomId, int authCode, ChangeMemberAuthListener listener) {
        RoomMember member = new RoomMember();
        member.setMemberRight(authCode);
        Call<ArrayList<RoomMember>> call = CalendarRetrofit.getInstance().getService().updateMemberAuth(token, roomId, member);
        call.enqueue(new Callback<ArrayList<RoomMember>>() {
            @Override
            public void onResponse(Call<ArrayList<RoomMember>> call, Response<ArrayList<RoomMember>> response) {
                if (response.code() == 200){
                    listener.onSuccess();
                } else if (response.code() == 500){
                    listener.onFail("server error");
                } else {
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
    public void kickMember(KickMemberListener listener) {
        listener.onSuccess();
    }
}
