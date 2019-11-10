package com.example.dsm_calendar.data.DTO;

public class Message{
	private String needDialogue;
	private String messageText;
	private String messageId;

	public void setNeedDialogue(String needDialogue){
		this.needDialogue = needDialogue;
	}

	public String getNeedDialogue(){
		return needDialogue;
	}

	public void setMessageText(String messageText){
		this.messageText = messageText;
	}

	public String getMessageText(){
		return messageText;
	}

	public void setMessageId(String messageId){
		this.messageId = messageId;
	}

	public String getMessageId(){
		return messageId;
	}

	@Override
 	public String toString(){
		return 
			"Message{" + 
			"needDialogue = '" + needDialogue + '\'' + 
			",messageText = '" + messageText + '\'' + 
			",messageId = '" + messageId + '\'' + 
			"}";
		}
}
