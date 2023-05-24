package com.rudametkin.hotelsystem.dto;

import com.rudametkin.hotelsystem.entitys.Room;

public class RoomWithInfo {
    private Room room;
    private int freeRoomsAmount;

    public RoomWithInfo() {}

    public RoomWithInfo(Room room, int freeRoomsAmount) {
        setRoom(room);
        setFreeRoomsAmount(freeRoomsAmount);
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setFreeRoomsAmount(int freeRoomsAmount) {
        this.freeRoomsAmount = freeRoomsAmount;
    }

    public int getFreeRoomsAmount() {
        return freeRoomsAmount;
    }

    public int getNumber() {
        return room.getNumber();
    }
    public Room.RoomType getType() {
        return room.getType();
    }
    public int getRoomsAmount() {
        return room.getRoomsAmount();
    }
    public int getSingleBedsAmount() {
        return room.getSingleBedsAmount();
    }
    public int getDoubleBedsAmount() {
        return room.getDoubleBedsAmount();
    }
    public boolean getHasMiniBar() {
        return room.isMiniBar();
    }
    public boolean getHasTV() {
        return room.isTv();
    }
    public boolean getHasDryer() {
        return room.isDryer();
    }
    public float getPrice() {
        return room.getPrice();
    }

}
