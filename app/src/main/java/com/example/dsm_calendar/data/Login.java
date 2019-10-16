package com.example.dsm_calendar.data;

public class Login {
	private String id;
	private String pw;

	public Login(String id, String pw) {
		this.id = id;
		this.pw = pw;
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
			"id = '" + id + '\'' +
			",pw = '" + pw + '\'' +
			"}";
		}
}
