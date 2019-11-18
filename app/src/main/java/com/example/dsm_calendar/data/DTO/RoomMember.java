package com.example.dsm_calendar.data.DTO;

public class RoomMember{
	private int memberRight;
	private String userId;
	private int roomId;
	private int memberId;

	public void setMemberRight(int memberRight){
		this.memberRight = memberRight;
	}

	public int getMemberRight(){
		return memberRight;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setRoomId(int roomId){
		this.roomId = roomId;
	}

	public int getRoomId(){
		return roomId;
	}

	public void setMemberId(int memberId){
		this.memberId = memberId;
	}

	public int getMemberId(){
		return memberId;
	}

	@Override
 	public String toString(){
		return 
			"RoomMember{" + 
			"memberRight = '" + memberRight + '\'' + 
			",userId = '" + userId + '\'' + 
			",roomId = '" + roomId + '\'' + 
			",memberId = '" + memberId + '\'' + 
			"}";
		}
}
