package com.rudametkin.hotelsystem.dto;

import com.rudametkin.hotelsystem.entitys.Bill;
import com.rudametkin.hotelsystem.entitys.RoomRegister;

import java.text.SimpleDateFormat;

public class OrderDto {
    private final Bill bill;
    private final RoomRegister roomRegister;

    public OrderDto(Bill bill, RoomRegister roomRegister) {
        this.bill = bill;
        this.roomRegister = roomRegister;
    }

    public String toString() {
        if(bill.getRoomCharge() != 0) {
            String arrive = new SimpleDateFormat("dd.MM.yyyy").format(roomRegister.getStartDateTime());
            String depart = new SimpleDateFormat("dd.MM.yyyy").format(roomRegister.getEndDateTime());
            return "Apartments | Room #" + roomRegister.getRoomNumber() + " | " + arrive + "-" + depart;
        } else
            return "Service | Room #" + roomRegister.getRoomNumber();
    }

    public int getRoomRegisterId() {
        return roomRegister.getId();
    }
}
