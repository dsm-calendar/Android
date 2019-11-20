package com.example.dsm_calendar.data.DTO;

public class TimeTableUnit {
    private String subject;
    private String teacher;
    private int timeTableIndex;

    public TimeTableUnit(String subject, String teacher, int timeTableIndex) {
        this.subject = subject;
        this.teacher = teacher;
        this.timeTableIndex = timeTableIndex;
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

    public void setTimeTableIndex(int timeTableIndex) {
        this.timeTableIndex = timeTableIndex;
    }

    public int getTimeTableIndex() {
        return timeTableIndex;
    }

    @Override
    public String toString() {
        return
                "TimeTableUnit{" +
                        "subject = '" + subject + '\'' +
                        "teacher ='" + teacher + '\'' +
                        ",timeTableIndex = '" + timeTableIndex + '\'' +
                        "}";
    }
}
