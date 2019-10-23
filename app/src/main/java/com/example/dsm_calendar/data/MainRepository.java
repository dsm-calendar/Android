package com.example.dsm_calendar.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.dsm_calendar.contract.MainContract;

public class MainRepository implements MainContract.Repository {

    private Context context;

    public interface GetUserInfoListener{
        void onSuccess(String id, int classOf, int iconIndex);
        void onFail();
    }

    public MainRepository(Context context){
        this.context = context;
    }

    @Override
    public void getUserInfo(GetUserInfoListener listener) {
        SharedPreferences userInfo = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String id = userInfo.getString("ID", "");
        int classOf = userInfo.getInt("classOf", 0);
        int iconIndex = userInfo.getInt("iconIndex", 0);

        if(id.equals("")){
            listener.onFail();
        } else {
            listener.onSuccess(id, classOf, iconIndex);
        }
    }
}
