package com.example.dsm_calendar.data.DTO;

public class Notice {

    private String title;
    private String content;
    private Boolean expended = false;

    public Boolean getExpended() {
        return expended;
    }

    public void setExpended(Boolean expended) {
        this.expended = expended;
    }

    public Notice(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
