package com.example.dsm_calendar.data.DTO;

import java.util.ArrayList;

public class SchoolSchedule {
    private ArrayList<Schedule> scheduleList;
    private int scheduleId;

    public SchoolSchedule(ArrayList<Schedule> schedules, int scheduleId) {
        this.scheduleList = schedules;
        this.scheduleId = scheduleId;
    }

    public ArrayList<Schedule> getSchedules() {
        return scheduleList;
    }

    public int getScheduleId() {
        return scheduleId;
    }
}
