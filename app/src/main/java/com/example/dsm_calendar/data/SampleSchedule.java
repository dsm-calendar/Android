package com.example.dsm_calendar.data;

import com.prolificinteractive.materialcalendarview.CalendarDay;

public class SampleSchedule {

    String title;
    String date;
    String content;
    Boolean expended = false;
    CalendarDay day;

    public SampleSchedule(String title, String date, String content, CalendarDay day){
        this.title = title;
        this.date = date;
        this.content = content;
        this.day = day;
    }

    public CalendarDay getDay() {
        return day;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public Boolean getExpended() {
        return expended;
    }

    public void setDay(CalendarDay day) {
        this.day = day;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setExpended(Boolean expended) {
        this.expended = expended;
    }
}
