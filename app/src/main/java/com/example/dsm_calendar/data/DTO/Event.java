package com.example.dsm_calendar.data.DTO;

public class Event{
	private int eventId;
	private String endDate;
	private String eventPoster;
	private String eventDetail;
	private String startDate;
	private boolean eventStatus;

	public boolean isEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(boolean eventStatus) {
		this.eventStatus = eventStatus;
	}

	public void setEventId(int eventId){
		this.eventId = eventId;
	}

	public int getEventId(){
		return eventId;
	}

	public void setEndDate(String endDate){
		this.endDate = endDate;
	}

	public String getEndDate(){
		return endDate;
	}

	public void setEventPoster(String eventPoster){
		this.eventPoster = eventPoster;
	}

	public String getEventPoster(){
		return eventPoster;
	}

	public void setEventDetail(String eventDetail){
		this.eventDetail = eventDetail;
	}

	public String getEventDetail(){
		return eventDetail;
	}

	public void setStartDate(String startDate){
		this.startDate = startDate;
	}

	public String getStartDate(){
		return startDate;
	}

	@Override
 	public String toString(){
		return 
			"Event{" + 
			"eventId = '" + eventId + '\'' + 
			",endDate = '" + endDate + '\'' + 
			",eventPoster = '" + eventPoster + '\'' + 
			",eventDetail = '" + eventDetail + '\'' + 
			",startDate = '" + startDate + '\'' + 
			"}";
		}
}
