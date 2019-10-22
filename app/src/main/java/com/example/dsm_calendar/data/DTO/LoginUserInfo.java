package com.example.dsm_calendar.data.DTO;

public class LoginUserInfo {
	private int loginUserId;
	private String userId;

	public void setLoginUserId(int loginUserId){
		this.loginUserId = loginUserId;
	}

	public int getLoginUserId(){
		return loginUserId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"LoginUserInfo{" +
			"loginUserId = '" + loginUserId + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}
