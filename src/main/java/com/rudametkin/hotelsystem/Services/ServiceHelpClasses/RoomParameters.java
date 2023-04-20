package com.rudametkin.hotelsystem.Services.ServiceHelpClasses;

import com.rudametkin.hotelsystem.EntityObjects.Room;

import java.sql.Timestamp;

public class RoomParameters {
    private Room.RoomType type;
    private int capacity;
    private int bedsAmount;
    private Timestamp arrivalDate;
    private Timestamp departureDate;

    public RoomParameters() {
        type = Room.RoomType.Any;
        capacity = 1;
        bedsAmount = 1;
        arrivalDate = new Timestamp(System.currentTimeMillis());

        long days = 1, hoursInDay = 24, minsInHour = 60, secsInMin = 60, millisInSec = 1000;
        long dayInMs = days * hoursInDay * minsInHour * secsInMin * millisInSec;
        departureDate = new Timestamp(System.currentTimeMillis() + dayInMs);;
    }

    public RoomParameters setType(Room.RoomType type) {
        this.type = type;
        return this;
    }

    public RoomParameters setBedsAmount(int amount) {
        this.bedsAmount = amount;
        return this;
    }

    public RoomParameters setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public RoomParameters setDepartureDate(Timestamp date) {
        this.departureDate = date;
        return this;
    }

    public RoomParameters setArrivalDate(Timestamp date) {
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
