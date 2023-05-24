package com.rudametkin.hotelsystem.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="rooms")
public class Room {
    public enum RoomType {
        Economy("Economy"), Standard("Standard"), Deluxe("Deluxe"), Suite("Suite"), Presidential("Presidential"), Any("Any");

        private final String value;

        RoomType(String value){
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;
    @Enumerated(EnumType.STRING)
    private RoomType type;

    @Column(name="rooms_amount")
    private int roomsAmount;
    @Column(name="single_beds_amount")
    private int singleBedsAmount;
    @Column(name="double_beds_amount")
    private int doubleBedsAmount;
    @Column(name="mini_bar")
    private boolean miniBar;
    private boolean tv;
    private boolean dryer;
    private float price;
}
