package com.example.dsm_calendar.data;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SampleSchedule {

    String title;
    String startDate;
    String endDate;
    String content;
    Boolean expended = false;
    CalendarDay startDay;
    CalendarDay endDay;

    public SampleSchedule(String title, String startDateString, String endDateString, String content) {
        this.title = title;
        this.startDate = startDateString;
        this.endDate = endDateString;
        this.content = content;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date startDate = format.parse(startDateString);
            startDay = CalendarDay.from(startDate);

            Date endDate = format.parse(endDateString);
            endDay = CalendarDay.from(endDate);
        }
        catch (ParseException e) {
            // TODO Handle exception
        }
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

    public CalendarDay getEndDay() {
        return endDay;
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
