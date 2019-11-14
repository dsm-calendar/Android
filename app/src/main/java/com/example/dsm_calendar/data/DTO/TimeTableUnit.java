package com.example.dsm_calendar.data.DTO;

public class TimeTableUnit {
	private String subject;
	private int index;

	public TimeTableUnit(String subject, int index) {
		this.subject = subject;
		this.index = index;
	}

	public void setSubject(String subject){
		this.subject = subject;
	}

	public String getSubject(){
		return subject;
	}

	public void setIndex(int index){
		this.index = index;
	}

	public int getIndex(){
		return index;
	}

	@Override
 	public String toString(){
		return 
			"TimeTableUnit{" +
			"subject = '" + subject + '\'' + 
			",index = '" + index + '\'' + 
			"}";
		}
}
