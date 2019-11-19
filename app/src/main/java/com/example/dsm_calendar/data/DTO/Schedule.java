package com.example.dsm_calendar.data.DTO;

import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Schedule implements Comparable<Schedule> {

    private String scheduleTitle;
    private String startDate;
    private String endDate;
    private String scheduleContent;
    private int calendarId;
    private int scheduleId;
    private Boolean expended = false;
    private CalendarDay startDay;
    private CalendarDay endDay;

    public Schedule(String scheduleTitle, String startDateString, String endDateString, String scheduleContent) {
        this.scheduleTitle = scheduleTitle;
        this.startDate = startDateString;
        this.endDate = endDateString;
        this.scheduleContent = scheduleContent;
    }

    public void setCalendarDay(String startDateString, String endDateString){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date startDate = format.parse(startDateString);
            startDay = CalendarDay.from(startDate);

            Date endDate = format.parse(endDateString);
            endDay = CalendarDay.from(endDate);
        } catch (ParseException e) {
            // TODO Handle exception
        }
    }

    public int getCalendarId() {
        return calendarId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public String getEndDate() {
        return endDate;
    }

    public CalendarDay getStartDay() {
        return startDay;
    }

    public CalendarDay getEndDay() {
        return endDay;
    }

    public String getScheduleTitle() {
        return scheduleTitle;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getScheduleContent() {
        return scheduleContent;
    }

    public Boolean getExpended() {
        return expended;
    }

    public void setExpended(Boolean expended) {
        this.expended = expended;
    }

    public boolean contain(CalendarDay day) {
        return day.isInRange(startDay, endDay);
    }

    @Override
    public int compareTo(Schedule o) {
        return startDay.isBefore(o.startDay) ? -1 : startDay.isAfter(o.startDay) ? 1 : 0;
    }
}
