package com.rudametkin.hotelsystem.DTO;


import com.rudametkin.hotelsystem.Entitys.Room;
import lombok.*;

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
}
