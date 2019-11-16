package com.example.dsm_calendar.data.DTO;

public class TimeTableUnit {
    private String subject;
    private String teacher;
    private int index;

    public TimeTableUnit(String subject, String teacher, int index) {
        this.subject = subject;
        this.teacher = teacher;
        this.index = index;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return
                "TimeTableUnit{" +
                        "subject = '" + subject + '\'' +
                        "teacher ='" + teacher + '\'' +
                        ",index = '" + index + '\'' +
                        "}";
    }
}
