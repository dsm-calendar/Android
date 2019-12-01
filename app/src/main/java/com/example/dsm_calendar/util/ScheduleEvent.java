package com.example.dsm_calendar.util;

public class ScheduleEvent {
    public enum SCHEDULE_EVENT{
        SCHEDULE_ADD, JUST_FINISHED
    }

    private SCHEDULE_EVENT status;

    public ScheduleEvent(SCHEDULE_EVENT status){
        this.status = status;
    }

    public SCHEDULE_EVENT getStatus() {
        return status;
    }

    public void setStatus(SCHEDULE_EVENT status) {
        this.status = status;
    }
}
