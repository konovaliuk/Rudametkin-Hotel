package com.rudametkin.hotelsystem.dto;


import com.rudametkin.hotelsystem.entitys.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomClassDto {
    private Room.RoomType type;
    private int roomsAmount;
    private int singleBedsAmount;
    private int doubleBedsAmount;
    private boolean miniBar;
    private boolean tv;
    private boolean dryer;
    private float price;

    public RoomClassDto(String type, int roomsAmount, int singleBedsAmount, int doubleBedsAmount, boolean miniBar, boolean tv, boolean dryer, float price) {
        this.type = Room.RoomType.valueOf(type);
        this.roomsAmount = roomsAmount;
        this.singleBedsAmount = singleBedsAmount;
        this.doubleBedsAmount = doubleBedsAmount;
        this.miniBar = miniBar;
        this.tv = tv;
        this.dryer = dryer;
        this.price = price;
    }

    public RoomClassDto(BookingRoomDto bookingRoomDto) {
        this.type = Room.RoomType.valueOf(bookingRoomDto.getType());
        this.roomsAmount = bookingRoomDto.getRooms();
        this.singleBedsAmount = bookingRoomDto.getSbeds();
        this.doubleBedsAmount = bookingRoomDto.getDbeds();
        this.miniBar = bookingRoomDto.getMiniBar();
        this.tv = bookingRoomDto.getTv();
        this.dryer = bookingRoomDto.getDryer();
        this.price = bookingRoomDto.getPrice();
    }

    public String toString() {
        return "RoomClass : { " + type + ", Rooms: " + roomsAmount + ", Capacity: " + (singleBedsAmount + 2*doubleBedsAmount) + ", Double beds: " + doubleBedsAmount + ", Single beds: " + singleBedsAmount + ", Minibar: " + miniBar + ", Tv: " + tv + ", Dryer: " + dryer + ", Price: " + price + " } ";
    }
}
