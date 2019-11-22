package com.example.dsm_calendar.data.DTO;

public class Room{
	private int calendarId;
	private String roomTitle;
	private int roomId;
	private int iconIndex;

	public Room(int calendarId, String roomTitle, int roomId, int iconIndex) {
		this.calendarId = calendarId;
		this.roomTitle = roomTitle;
		this.roomId = roomId;
		this.iconIndex = iconIndex;
	}

	public void setCalendarId(int calendarId){
		this.calendarId = calendarId;
	}

	public int getCalendarId(){
		return calendarId;
	}

	public void setRoomTitle(String roomTitle){
		this.roomTitle = roomTitle;
	}

	public String getRoomTitle(){
		return roomTitle;
	}

	public void setRoomId(int roomId){
		this.roomId = roomId;
	}

	public int getRoomId(){
		return roomId;
	}

	public void setIconIndex(int iconIndex){
		this.iconIndex = iconIndex;
	}

	public int getIconIndex(){
		return iconIndex;
	}

	@Override
 	public String toString(){
		return 
			"Room{" + 
			"calendarId = '" + calendarId + '\'' + 
			",roomTitle = '" + roomTitle + '\'' + 
			",roomId = '" + roomId + '\'' + 
			",iconIndex = '" + iconIndex + '\'' + 
			"}";
		}
}
