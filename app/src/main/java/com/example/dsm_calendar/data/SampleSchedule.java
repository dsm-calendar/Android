package com.example.dsm_calendar.data;

public class SampleSchedule {

    String title;
    String date;
    String content;
    Boolean expended = false;

    public SampleSchedule(String title, String date, String content){
        this.title = title;
        this.date = date;
        this.content = content;
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
