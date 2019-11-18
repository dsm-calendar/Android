package com.example.dsm_calendar.data.DTO;

import java.util.ArrayList;

public class RoomInfo {
    private Room room;
    private ArrayList<RoomMember> roomMembers;
    private ArrayList<Schedule> roomSchedule;

    public RoomInfo(Room room, ArrayList<RoomMember> members, ArrayList<Schedule> roomSchedule) {
        this.room = room;
        this.roomMembers = members;
        this.roomSchedule = roomSchedule;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ArrayList<RoomMember> getRoomMembers() {
        return roomMembers;
    }

    public void setRoomMembers(ArrayList<RoomMember> members) {
        this.roomMembers = members;
    }

    public ArrayList<Schedule> getRoomSchedule() {
        return roomSchedule;
    }

    public void setRoomSchedule(ArrayList<Schedule> roomSchedule) {
        this.roomSchedule = roomSchedule;
    }
}
