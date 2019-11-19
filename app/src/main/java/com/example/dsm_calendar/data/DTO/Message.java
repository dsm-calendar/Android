package com.example.dsm_calendar.data.DTO;

public class Message{
	private int eventId;
	private boolean haveDialog;
	private String sendDateNow;
	private int messageId;
	private int type;
	private String toUserId;
	private int roomId;

	public void setEventId(int eventId){
		this.eventId = eventId;
	}

	public int getEventId(){
		return eventId;
	}

	public void setHaveDialog(boolean haveDialog){
		this.haveDialog = haveDialog;
	}

	public boolean isHaveDialog(){
		return haveDialog;
	}

	public void setSendDateNow(String sendDateNow){
		this.sendDateNow = sendDateNow;
	}

	public String getSendDateNow(){
		return sendDateNow;
	}

	public void setMessageId(int messageId){
		this.messageId = messageId;
	}

	public int getMessageId(){
		return messageId;
	}

	public void setType(int type){
		this.type = type;
	}

	public int getType(){
		return type;
	}

	public void setToUserId(String toUserId){
		this.toUserId = toUserId;
	}

	public String getToUserId(){
		return toUserId;
	}

	public void setRoomId(int roomId){
		this.roomId = roomId;
	}

	public int getRoomId(){
		return roomId;
	}

	@Override
 	public String toString(){
		return 
			"Message{" + 
			"eventId = '" + eventId + '\'' + 
			",haveDialog = '" + haveDialog + '\'' + 
			",sendDateNow = '" + sendDateNow + '\'' + 
			",messageId = '" + messageId + '\'' + 
			",type = '" + type + '\'' + 
			",toUserId = '" + toUserId + '\'' + 
			",roomId = '" + roomId + '\'' + 
			"}";
		}
}
