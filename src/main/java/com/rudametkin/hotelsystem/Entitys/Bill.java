package com.rudametkin.hotelsystem.Entity;

public class Bill {
    private int id = -1;
    private int registerId;
    private int clientId;
    private float roomCharge = 0;
    private float roomServiceCharge = 0;
    private float barCharge = 0;
    private float restaurantCharge = 0;
    private float lateDepartureCharge = 0;
    private float otherCharge = 0;
    private boolean isPaid;

    public Bill() {}
    public Bill(int id, int register_id, int client_id)
    {
        this.id = id;
        this.clientId = client_id;
        this.registerId = register_id;
        this.isPaid = false;
    }

    public Bill(int register_id, int client_id)
    {
        this.clientId = client_id;
        this.registerId = register_id;
        this.isPaid = false;
    }

    public Bill(int id, int register_id, int client_id, float roomCharge, float roomServiceCharge, float barCharge, float restaurantCharge,
                float lateDepartureCharge, float otherCharge, boolean isPaid)
    {
        this.id = id;
        this.clientId = client_id;
        this.registerId = register_id;
        this.roomCharge = roomCharge;
        this.roomServiceCharge = roomServiceCharge;
        this.barCharge = barCharge;
        this.restaurantCharge = restaurantCharge;
        this.lateDepartureCharge = lateDepartureCharge;
        this.otherCharge = otherCharge;
        this.isPaid = isPaid;
    }

    public int getId() { return id; }
    public int getRegisterId() { return registerId; }
    public int getClientId() { return clientId; }
    public float getRoomCharge() { return roomCharge; }
    public float getRoomServiceCharge() { return roomServiceCharge; }
    public float getBarCharge() { return barCharge; }
    public float getRestaurantCharge() { return restaurantCharge; }
    public float getLateDepartureCharge() { return lateDepartureCharge; }
    public float getOtherCharge() { return otherCharge; }
    public float getTotalCharge() {
        return roomCharge + roomServiceCharge + barCharge + restaurantCharge + lateDepartureCharge + otherCharge;
    }
    public boolean getIsPaid() { return isPaid; }

    public void setRegisterId(int newRegId) { registerId = newRegId; }
    public void setClientId(int newClientId) { clientId = newClientId; }
    public void setRoomCharge(float newRoomCharge) { roomCharge = newRoomCharge; }
    public void setRoomServiceCharge(float newRoomServiceCharge) { roomServiceCharge = newRoomServiceCharge; }
    public void setBarCharge(float newBarCharge) { barCharge = newBarCharge; }
    public void setRestaurantCharge(float newRestaurantCharge) { restaurantCharge = newRestaurantCharge; }
    public void setLateDepartureCharge(float newLateDepCharge) { lateDepartureCharge = newLateDepCharge; }
    public void setOtherCharge(float newOtherCharge) { otherCharge = newOtherCharge; }
    public void setIsPaid(boolean newIsPaid) { isPaid = newIsPaid; }
}
