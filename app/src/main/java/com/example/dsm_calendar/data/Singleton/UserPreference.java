package com.example.dsm_calendar.data.Singleton;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.dsm_calendar.data.DTO.Student;

public class UserPreference {
    private static final String PREFERENCE_NAME = "userInfo";
    private static UserPreference preference = null;
    private static Context mContext;
    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;

    public static UserPreference getInstance(Context context) {
        mContext = context;

        if (preference == null){
            preference = new UserPreference();
        }
        if (pref == null){
            pref = mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            editor = pref.edit();
        }
        return preference;
    }



    public void putUserID(String key, int userId){
        editor.putInt(key, userId);
        editor.apply();
    }

    public void putID(String key, String ID){
        editor.putString(key, ID);
        editor.apply();
    }

    public void putClassOf(String key, int classOf){
        editor.putInt(key, classOf);
        editor.apply();
    }

    public void putIconIndex(String key, int iconIndex){
        editor.putInt(key, iconIndex);
        editor.apply();
    }

    public void putMyCalendarID(String key, int myCalendarID){
        editor.putInt(key, myCalendarID);
        editor.apply();
    }

    public int getUserID(){
        return pref.getInt("userID", -1);
    }

    public String getID(){
        return pref.getString("ID", "");
    }

    public int getClassOf(){
        return pref.getInt("classOf", -1);
    }

    public int getIconIndex(){
        return pref.getInt("iconIndex", -1);
    }

    public int getMyCalendarID(){
        return pref.getInt("myCalendarID", -1);
    }

    public void clear(){
        editor.clear();
        editor.commit();
    }
}
