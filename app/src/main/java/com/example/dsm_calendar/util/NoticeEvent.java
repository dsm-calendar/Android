package com.example.dsm_calendar.util;

public class NoticeEvent {
    public enum NOTICE_EVENT {
        NOTICE_ADD, JUST_FINISHED
    }

    private NOTICE_EVENT status;

    public NoticeEvent(NOTICE_EVENT status) {
        this.status = status;
    }

    public NOTICE_EVENT getStatus(){
        return status;
    }

    public void setStatus(NOTICE_EVENT status){
        this.status = status;
    }
}
