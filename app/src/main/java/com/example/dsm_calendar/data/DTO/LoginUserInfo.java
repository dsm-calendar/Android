package com.example.dsm_calendar.data.DTO;

public class LoginUserInfo {
    private int loginUserId;
    private String id;
    private int classOf;
    private int iconIndex;
    private int myCalendarId;

    public LoginUserInfo(int loginUserId, String id, int classOf, int iconIndex, int myCalendarId){
        this.loginUserId = loginUserId;
        this.id = id;
        this.classOf = classOf;
        this.iconIndex = iconIndex;
        this.myCalendarId = myCalendarId;
    }

    public void setLoginUserId(int loginUserId) {
        this.loginUserId = loginUserId;
    }

    public int getLoginUserId() {
        return loginUserId;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setClassOf(int classOf){
        this.classOf = classOf;
    }

    public int getClassOf(){
        return classOf;
    }

    public void setIconIndex(int iconIndex){
        this.iconIndex = iconIndex;
    }

    public int getIconIndex(){
        return iconIndex;
    }

    public void setMyCalendarId(int myCalendarId){
        this.myCalendarId = myCalendarId;
    }

    public int getMyCalendarId(){
        return myCalendarId;
    }

    @Override
    public String toString() {
        return
                "LoginUserInfo{" +
                        "loginUserId = '" + loginUserId + '\'' +
                        ",id = '" + id + '\'' +
                        ",classOf = '" + classOf + '\'' +
                        ",iconIndex = '" + iconIndex + '\'' +
                        ",myCalendarId = '" + myCalendarId + '\'' +
                        "}";
    }
}
