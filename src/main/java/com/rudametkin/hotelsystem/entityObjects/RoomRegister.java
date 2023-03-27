package com.rudametkin.hotelsystem.entityObjects;

import java.sql.Timestamp;

public class RoomRegister {
    public enum RoomStatus {
        Occupied("Occupied"), Booked("Booked"), Prepearing("Prepearing"), NotAvailable("Not available");
        private String value;

        RoomStatus(String value){
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    private int id = -1;
    private int roomNumber;
    private RoomStatus status;
    private Timestamp startDateTime;
    private Timestamp endDateTime;
    private int clientId;
    private void initValues(int id, int roomNumber, RoomStatus status, Timestamp startDateTime,
                            Timestamp endDateTime, int clientId) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.status = status;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.clientId = clientId;
    }
    public RoomRegister() {}
    public RoomRegister(int id, int roomNumber, RoomStatus status, Timestamp startDateTime,
                        Timestamp endDateTime, int clientId) {
        initValues(id, roomNumber, status, startDateTime, endDateTime, clientId);
    }
    public RoomRegister(int roomNumber, RoomStatus status, Timestamp startDateTime,
                        Timestamp endDateTime, int clientId)
    {
        initValues(-1, roomNumber, status, startDateTime, endDateTime, clientId);
    }
    public int getId() {
        return id;
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public RoomStatus getStatus() {
        return status;
    }
    public Timestamp getStartDateTime() {
        return startDateTime;
    }
    public Timestamp getEndDateTime() {
        return endDateTime;
    }
    public int getClientId() {
        return clientId;
    }
    public void setRoomNumber(int newRoomNumber) {
        roomNumber = newRoomNumber;
    }
    public void setStatus(RoomStatus newStatus) {
        status = newStatus;
    }
    public void setStartDateTime(Timestamp newDateTime) {
        startDateTime = newDateTime;
    }
    public void setEndDateTime(Timestamp newDateTime) {
        endDateTime = newDateTime;
    }
    public void setClientId(int newClientId) {
        clientId = newClientId;
    }
    public String toString() {
        return Integer.toString(id) + " | " + Integer.toString(roomNumber) + " | " + status.toString() + " | " + startDateTime.toString() + " | " + endDateTime.toString() + " | " + Integer.toString(clientId);
    }
}
