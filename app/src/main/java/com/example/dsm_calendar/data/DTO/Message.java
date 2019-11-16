package com.example.dsm_calendar.data.DTO;

public class Message{
	private int eventId;
	private boolean haveDialog;
	private int messageId;
	private int roomId;
	private String sendDateNow;
	private String toUserId;
	private int type;

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public boolean isHaveDialog() {
		return haveDialog;
	}

	public void setHaveDialog(boolean haveDialog) {
		this.haveDialog = haveDialog;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getSendDateNow() {
		return sendDateNow;
	}

	public void setSendDateNow(String sendDateNow) {
		this.sendDateNow = sendDateNow;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
