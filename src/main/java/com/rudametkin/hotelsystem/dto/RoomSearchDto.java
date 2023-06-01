package com.rudametkin.hotelsystem.dto;

import com.rudametkin.hotelsystem.entitys.Room;
import com.rudametkin.hotelsystem.services.UtilService;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.sql.Timestamp;

public class RoomSearchDto {
    private Room.RoomType type;
    private int capacity;
    private int bedsAmount;
    private Timestamp arrivalDate;
    private Timestamp departureDate;
    private Integer page;


    public RoomSearchDto() {
        type = Room.RoomType.Any;
        capacity = 1;
        bedsAmount = 1;
        arrivalDate = new Timestamp(System.currentTimeMillis());
        page = 1;

        long days = 1, hoursInDay = 24, minsInHour = 60, secsInMin = 60, millisInSec = 1000;
        long dayInMs = days * hoursInDay * minsInHour * secsInMin * millisInSec;
        departureDate = new Timestamp(System.currentTimeMillis() + dayInMs);;
    }

    public RoomSearchDto(RoomSearchRawDto roomSearchRawDto) {
        type = roomSearchRawDto.getType();
        capacity = roomSearchRawDto.getCapacity();
        bedsAmount = roomSearchRawDto.getBedsAmount();
        page = roomSearchRawDto.getPage();

        System.out.println(roomSearchRawDto.getArrivalDate());
        System.out.println(roomSearchRawDto.getDepartureDate());

        UtilService service = new UtilService();
        arrivalDate = service.parseDateTime(roomSearchRawDto.getArrivalDate());
        departureDate = service.parseDateTime(roomSearchRawDto.getDepartureDate());

        System.out.println(arrivalDate);
        System.out.println(departureDate);
    }

    public RoomSearchDto setType(Room.RoomType type) {
        this.type = type;
        return this;
    }

    public RoomSearchDto setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public RoomSearchDto setType(String type) {
        this.type = Room.RoomType.valueOf(type);
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

    public String toString() {
        return String.format("RoomSearch { type: %s, capacity: %d, bedsAmount: %d }", type.toString(), capacity, bedsAmount) + " " + arrivalDate.toString() + " " + departureDate.toString();
    }
}
