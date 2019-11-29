package com.example.dsm_calendar.data.DTO;

public class Notice{
	private String noticeContent;
	private String endDate;
	private int noticeId;
	private String noticeTitle;
	private String startDate;
	private boolean expanded;

	public Notice(String noticeTitle, String noticeContent, String endDate, String startDate) {
		this.noticeContent = noticeContent;
		this.endDate = endDate;
		this.noticeTitle = noticeTitle;
		this.startDate = startDate;
	}

	public void setNoticeContent(String noticeContent){
		this.noticeContent = noticeContent;
	}

	public String getNoticeContent(){
		return noticeContent;
	}

	public void setEndDate(String endDate){
		this.endDate = endDate;
	}

	public String getEndDate(){
		return endDate;
	}

	public void setNoticeId(int noticeId){
		this.noticeId = noticeId;
	}

	public int getNoticeId(){
		return noticeId;
	}

	public void setNoticeTitle(String noticeTitle){
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeTitle(){
		return noticeTitle;
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
			"Notice{" + 
			"noticeContent = '" + noticeContent + '\'' + 
			",endDate = '" + endDate + '\'' + 
			",noticeId = '" + noticeId + '\'' + 
			",noticeTitle = '" + noticeTitle + '\'' + 
			",startDate = '" + startDate + '\'' + 
			"}";
		}
}
