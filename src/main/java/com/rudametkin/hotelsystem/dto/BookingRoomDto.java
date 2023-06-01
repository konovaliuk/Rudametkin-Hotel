package com.rudametkin.hotelsystem.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BookingRoomDto {
    private Integer sbeds;
    private Integer dbeds;
    private Integer rooms;
    private Boolean tv;
    private Boolean dryer;
    private Boolean miniBar;
    private Float price;
    private String type;
    private Timestamp arrivalDate;
    private Timestamp departureDate;

    public BookingRoomDto() {}
    public void setSbeds(Integer sbeds) {
        this.sbeds = sbeds;
    }

    public Integer getSbeds() {
        return sbeds;
    }

    public void setDbeds(Integer dbeds) {
        this.dbeds = dbeds;
    }

    public Integer getDbeds() {
        return dbeds;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getRooms() {
        return rooms;
    }

    public Boolean getTv() {
        return tv;
    }

    public void setTv(Boolean tv) {
        this.tv = tv;
    }

    public Boolean getMiniBar() {
        return miniBar;
    }

    public void setMiniBar(Boolean minibar) {
        this.miniBar = minibar;
    }

    public Boolean getDryer() {
        return dryer;
    }

    public void setDryer(Boolean dryer) {
        this.dryer = dryer;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getArrivalDate() {
        return arrivalDate;
    }

    public Timestamp getDepartureDate() {
        return departureDate;
    }

    public String getArrivalDateFormatted() {
        return new SimpleDateFormat("yyyy-MM-dd").format(arrivalDate);
    }

    public String getDepartureDateFormatted() {
        return new SimpleDateFormat("yyyy-MM-dd").format(departureDate);
    }

    public void setArrivalDate(String arrivalDate) {
        Date arrivalDateUtil = new Date();
        try {
            arrivalDateUtil = new SimpleDateFormat("yyyy-MM-dd").parse(arrivalDate);
        } catch (Exception e) {}

        this.arrivalDate = new Timestamp(arrivalDateUtil.getTime());
    }

    public void setDepartureDate(String departureDate) {
        Date departureDateUtil = new Date();
        try {
            departureDateUtil = new SimpleDateFormat("yyyy-MM-dd").parse(departureDate);
        } catch (Exception e) {}

        this.departureDate = new Timestamp(departureDateUtil.getTime());
    }
}
