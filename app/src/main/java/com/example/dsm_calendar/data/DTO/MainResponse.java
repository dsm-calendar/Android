package com.example.dsm_calendar.data.DTO;

import java.util.ArrayList;

public class MainResponse {
    private ArrayList<Event> eventList;
    private ArrayList<Notice> notices;
    private ArrayList<TimeTableUnit> timeTables;
    private ArrayList<Schedule> schedules;

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
    }

    public ArrayList<Event> getEventList() {
        return eventList;
    }

    public void setEventList(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }

    public ArrayList<Notice> getNotices() {
        return notices;
    }

    public void setNotices(ArrayList<Notice> notices) {
        this.notices = notices;
    }

    public ArrayList<TimeTableUnit> getTimeTables() {
        return timeTables;
    }

    public void setTimeTables(ArrayList<TimeTableUnit> timeTables) {
        this.timeTables = timeTables;
    }
}
