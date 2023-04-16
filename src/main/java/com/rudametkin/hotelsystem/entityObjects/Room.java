package com.rudametkin.hotelsystem.entityObjects;

public class Room {
    public enum RoomType {
        Economy("Economy"), Standard("Standard"), Deluxe("Deluxe"), Suite("Suite"), Presidential("Presidential"), Any("Any");

        private String value;

        RoomType(String value){
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    private int number;
    private RoomType type;
    private int roomsAmount;
    private int singleBedsAmount;
    private int doubleBedsAmount;
    private boolean hasMiniBar;
    private boolean hasTV;
    private boolean hasDryer;
    private float price;
    private void initValues(int number, RoomType type, int roomsAmount, int singleBedsAmount, int doubleBedsAmount,
                       boolean hasMiniBar, boolean hasTV, boolean hasDryer, float price) {
        this.number = number;
        this.type = type;
        this.roomsAmount = roomsAmount;
        this.singleBedsAmount = singleBedsAmount;
        this.doubleBedsAmount = doubleBedsAmount;
        this.hasMiniBar = hasMiniBar;
        this.hasTV = hasTV;
        this.hasDryer = hasDryer;
        this.price = price;
    }
    public Room(int number, RoomType type, int roomsAmount, int singleBedsAmount, int doubleBedsAmount,
                     boolean hasMiniBar, boolean hasTV, boolean hasDryer, float price)
    {
        initValues(number, type, roomsAmount, singleBedsAmount, doubleBedsAmount, hasMiniBar, hasTV, hasDryer, price);
    }

    public Room(RoomType type, int roomsAmount, int singleBedsAmount, int doubleBedsAmount,
                     boolean hasMiniBar, boolean hasTV, boolean hasDryer, float price)
    {
        initValues(-1, type, roomsAmount, singleBedsAmount, doubleBedsAmount, hasMiniBar, hasTV, hasDryer, price);
    }

    public int getNumber() {
        return number;
    }
    public RoomType getType() {
        return type;
    }
    public int getRoomsAmount() {
        return roomsAmount;
    }
    public int getSingleBedsAmount() {
        return singleBedsAmount;
    }
    public int getDoubleBedsAmount() {
        return doubleBedsAmount;
    }
    public boolean getHasMiniBar() {
        return hasMiniBar;
    }
    public boolean getHasTV() {
        return hasTV;
    }
    public boolean getHasDryer() {
        return hasDryer;
    }
    public float getPrice() {
        return price;
    }
    public void setType(RoomType newType) {
        type = newType;
    }
    public void setRoomsAmount(int newRoomsAmount) {
        roomsAmount = newRoomsAmount;
    }
    public void setSingleBedsAmount(int newSingleBedsAmount) {
        singleBedsAmount = newSingleBedsAmount;
    }
    public void setDoubleBedsAmount(int newDoubleBedsAmount) {
        doubleBedsAmount = newDoubleBedsAmount;
    }
    public void setHasMiniBar(boolean hasMiniBar) {
        this.hasMiniBar = hasMiniBar;
    }
    public void setHasTV(boolean hasTV) {
        this.hasTV = hasTV;
    }
    public void setHasDryer(boolean hasDryer) {
        this.hasDryer = hasDryer;
    }
    public void setPrice(float newPrice) {
        price = newPrice;
    }
    public String toString() {
        return Integer.toString(number) + " | " + type.toString() + " | Single beds amount : " + Integer.toString(singleBedsAmount) +
                " | Double beds amount : " + Integer.toString(doubleBedsAmount) + " | Rooms amount: " + Integer.toString(roomsAmount) +
                " | Price: " + Float.toString(price);
    }
}
