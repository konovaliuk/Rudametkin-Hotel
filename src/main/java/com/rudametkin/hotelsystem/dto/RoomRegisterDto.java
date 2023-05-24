package com.rudametkin.hotelsystem.dto;

import com.rudametkin.hotelsystem.entitys.RoomRegister.RoomStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRegisterDto {
    private int roomNumber;
    private RoomStatus status;
    private Timestamp startDateTime;
    private Timestamp endDateTime;
    private int clientId;
}