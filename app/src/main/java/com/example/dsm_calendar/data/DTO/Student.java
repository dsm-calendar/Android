package com.example.dsm_calendar.data.DTO;

public class Student{
	private String pw;
	private String id;
	private int classOf;
	private int iconIndex;
	private int myCalendarId;

	public Student(String pw, String id, int classOf, int iconIndex, int myCalendarId) {
		this.pw = pw;
		this.id = id;
		this.classOf = classOf;
		this.iconIndex = iconIndex;
		this.myCalendarId = myCalendarId;
	}

	public void setPw(String pw){
		this.pw = pw;
	}

	public String getPw(){
		return pw;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setClassOf(int classOf){
		this.classOf = classOf;
	}

	public int getClassOf(){
		return classOf;
	}

	public void setIconIndex(int iconIndex){
		this.iconIndex = iconIndex;
	}

	public int getIconIndex(){
		return iconIndex;
	}

	public void setMyCalendarId(int myCalendarId){
		this.myCalendarId = myCalendarId;
	}

	public int getMyCalendarId(){
		return myCalendarId;
	}

	@Override
 	public String toString(){
		return 
			"Student{" + 
			"pw = '" + pw + '\'' + 
			",id = '" + id + '\'' + 
			",classOf = '" + classOf + '\'' + 
			",iconIndex = '" + iconIndex + '\'' + 
			",myCalendarId = '" + myCalendarId + '\'' + 
			"}";
		}
}
