package com.rudametkin.hotelsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import com.rudametkin.hotelsystem.Entitys.RoomRegister.RoomStatus;

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