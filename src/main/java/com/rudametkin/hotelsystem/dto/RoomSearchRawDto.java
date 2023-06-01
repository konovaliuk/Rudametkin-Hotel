package com.rudametkin.hotelsystem.dto;

import com.rudametkin.hotelsystem.entitys.Room;

import java.sql.Timestamp;

public class RoomSearchRawDto {
    private Room.RoomType type;
    private int capacity;
    private int bedsAmount;
    private String arrivalDate;
    private String departureDate;
    private Integer page;


    public RoomSearchRawDto() {
        page = 1;
    }

    public void setType(Room.RoomType type) {
        this.type = type;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPage() {
        return page;
    }

    public void setType(String type) {
        this.type = Room.RoomType.valueOf(type);
    }

    public void setBedsAmount(int amount) {
        this.bedsAmount = amount;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDepartureDate(String date) {
        this.departureDate = date;
    }

    public void setArrivalDate(String date) {
        this.arrivalDate = date;
    }


    public Room.RoomType getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getBedsAmount() {
        return bedsAmount;
    }


    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String toString() {
        return String.format("RoomSearch { type: %s, capacity: %d, bedsAmount: %d }", type.toString(), capacity, bedsAmount) + " " + arrivalDate.toString() + " " + departureDate.toString();
    }
}
