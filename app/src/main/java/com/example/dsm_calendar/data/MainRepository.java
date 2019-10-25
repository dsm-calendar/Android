package com.example.dsm_calendar.data;

import android.content.Context;

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
        UserPreference preferences = UserPreference.getInstance(context);
        String id = preferences.getID();
        int classOf = preferences.getClassOf();
        int iconIndex = preferences.getIconIndex();

        if(id.equals("")){
            listener.onFail();
        } else {
            listener.onSuccess(id, classOf, iconIndex);
        }
    }
}
