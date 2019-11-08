package com.example.dsm_calendar.data;

import com.prolificinteractive.materialcalendarview.CalendarDay;

public class SampleSchedule {

    String title;
    String startDate;
    String endDate;
    String content;
    Boolean expended = false;
    CalendarDay startDay;
    CalendarDay endDay;

    public SampleSchedule(String title, String startDate, String endDate, String content, CalendarDay startDay, CalendarDay endDay) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.content = content;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public CalendarDay getStartDay() {
        return startDay;
    }

    public String getTitle() {
        return title;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getContent() {
        return content;
    }

    public Boolean getExpended() {
        return expended;
    }

    public void setStartDay(CalendarDay startDay) {
        this.startDay = startDay;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setExpended(Boolean expended) {
        this.expended = expended;
    }
}
