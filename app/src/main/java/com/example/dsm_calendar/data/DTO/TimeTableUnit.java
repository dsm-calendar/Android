package com.example.dsm_calendar.data.DTO;

public class TimeTableUnit {
	private String subject;
	private String index;

	public void setSubject(String subject){
		this.subject = subject;
	}

	public String getSubject(){
		return subject;
	}

	public void setIndex(String index){
		this.index = index;
	}

	public String getIndex(){
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
