package com.example.dsm_calendar.data;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Schedule implements Comparable<Schedule> {

    private String title;
    private String startDate;
    private String endDate;
    private String content;
    private Boolean expended = false;
    private CalendarDay startDay;
    private CalendarDay endDay;

    public Schedule(String title, String startDateString, String endDateString, String content) {
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
