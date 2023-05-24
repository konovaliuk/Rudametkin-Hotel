package com.rudametkin.hotelsystem.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roomregisters")
public class RoomRegister {
    public enum RoomStatus {
        Occupied("Occupied"), Booked("Booked"), Prepearing("Prepearing"), NotAvailable("Not available");
        private final String value;

        RoomStatus(String value){
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "room_number")
    private int roomNumber;
    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    @Column(name = "start_date_time")
    private Timestamp startDateTime;
    @Column(name = "end_date_time")
    private Timestamp endDateTime;
    @Column(name = "client_id")
    private int clientId;

    public RoomRegister(int roomNumber, RoomStatus status, Timestamp startDateTime, Timestamp endDateTime, int clientId) {
        this.clientId = clientId;
        this.roomNumber = roomNumber;
        this.status = status;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

}
