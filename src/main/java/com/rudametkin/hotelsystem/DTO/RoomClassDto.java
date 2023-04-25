package com.rudametkin.hotelsystem.DTO;


import com.rudametkin.hotelsystem.Entitys.Room;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

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
