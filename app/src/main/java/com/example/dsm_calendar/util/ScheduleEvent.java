package com.example.dsm_calendar.util;

public class ScheduleEvent {
    public enum EVENT{
        SCHEDULE_ADD, JUST_FINISHED
    }

    private EVENT status;

    public ScheduleEvent(EVENT status){
        this.status = status;
    }

    public EVENT getStatus() {
        return status;
    }

    public void setStatus(EVENT status) {
        this.status = status;
    }
}
