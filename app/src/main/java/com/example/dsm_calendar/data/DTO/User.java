package com.example.dsm_calendar.data.DTO;

public class User{
	private String pw;
	private boolean admin;
	private String id;
	private int classOf;
	private int iconIndex;
	private int myCalendarId;

	public void setPw(String pw){
		this.pw = pw;
	}

	public String getPw(){
		return pw;
	}

	public void setAdmin(boolean admin){
		this.admin = admin;
	}

	public boolean isAdmin(){
		return admin;
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
			"User{" + 
			"pw = '" + pw + '\'' + 
			",admin = '" + admin + '\'' + 
			",id = '" + id + '\'' + 
			",classOf = '" + classOf + '\'' + 
			",iconIndex = '" + iconIndex + '\'' + 
			",myCalendarId = '" + myCalendarId + '\'' + 
			"}";
		}
}
