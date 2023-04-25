package com.rudametkin.hotelsystem.DTO;

import com.rudametkin.hotelsystem.Entitys.Room;

import java.sql.Timestamp;

public class RoomSearchDto {
    private Room.RoomType type;
    private int capacity;
    private int bedsAmount;
    private Timestamp arrivalDate;
    private Timestamp departureDate;

    public RoomSearchDto() {
        type = Room.RoomType.Any;
        capacity = 1;
        bedsAmount = 1;
        arrivalDate = new Timestamp(System.currentTimeMillis());

        long days = 1, hoursInDay = 24, minsInHour = 60, secsInMin = 60, millisInSec = 1000;
        long dayInMs = days * hoursInDay * minsInHour * secsInMin * millisInSec;
        departureDate = new Timestamp(System.currentTimeMillis() + dayInMs);;
    }

    public RoomSearchDto setType(Room.RoomType type) {
        this.type = type;
        return this;
    }

    public RoomSearchDto setBedsAmount(int amount) {
        this.bedsAmount = amount;
        return this;
    }

    public RoomSearchDto setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public RoomSearchDto setDepartureDate(Timestamp date) {
        this.departureDate = date;
        return this;
    }

    public RoomSearchDto setArrivalDate(Timestamp date) {
        this.arrivalDate = date;
        return this;
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


    public Timestamp getArrivalDate() {
        return arrivalDate;
    }

    public Timestamp getDepartureDate() {
        return departureDate;
    }
}
