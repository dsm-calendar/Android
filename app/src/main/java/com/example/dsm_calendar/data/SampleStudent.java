package com.example.dsm_calendar.data;

public class SampleStudent {

    String name;
    String std_no;
    int profile;

    public SampleStudent(String name, String std_no, int profile){
        this.name = name;
        this.std_no = std_no;
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStd_no() {
        return std_no;
    }

    public void setStd_no(String std_no) {
        this.std_no = std_no;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }
}
