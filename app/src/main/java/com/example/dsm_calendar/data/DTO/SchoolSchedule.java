package com.example.dsm_calendar.data.DTO;

import java.util.ArrayList;

public class SchoolSchedule {
    private ArrayList<Schedule> scheduleList;
    private int schoolCalendarId;

    public SchoolSchedule(ArrayList<Schedule> schedules, int schoolCalendarId) {
        this.scheduleList = schedules;
        this.schoolCalendarId = schoolCalendarId;
    }

    public ArrayList<Schedule> getSchedules() {
        return scheduleList;
    }

    public int getSchoolCalendarId() {
        return schoolCalendarId;
    }
}
