package com.example.dsm_calendar.data;

public class Login {
	private String pw;
	private String id;

	public Login(String pw, String id) {
		this.pw = pw;
		this.id = id;
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

	@Override
 	public String toString(){
		return 
			"Login{" +
			"pw = '" + pw + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}
