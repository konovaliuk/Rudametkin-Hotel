package com.rudametkin.hotelsystem.Entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bills")
public class Bill {

    public static class BillBuilder {
        private final int registerId;
        private float roomCharge = 0;
        private float roomServiceCharge = 0;
        private float barCharge = 0;
        private float restaurantCharge = 0;
        private float lateDepartureCharge = 0;
        private float otherCharge = 0;
        private boolean isPaid = false;

        public BillBuilder(int registerId) {
            this.registerId = registerId;
        }

        public BillBuilder addRoomCharge(float charge) {
            this.roomCharge = charge;
            return this;
        }

        public BillBuilder addRoomServiceCharge(float charge) {
            this.roomServiceCharge = charge;
            return this;
        }

        public BillBuilder addBarCharge(float charge) {
            this.barCharge = charge;
            return this;
        }

        public BillBuilder addRestaurantCharge(float charge) {
            this.restaurantCharge = charge;
            return this;
        }

        public BillBuilder addLateDepartureCharge(float charge) {
            this.lateDepartureCharge = charge;
            return this;
        }

        public BillBuilder addOtherCharge(float charge) {
            this.otherCharge = charge;
            return this;
        }

        public BillBuilder addPaid(boolean isPaid) {
            this.isPaid = isPaid;
            return this;
        }

        public Bill build() {
            return new Bill(registerId, roomCharge, roomServiceCharge, barCharge, restaurantCharge,
                    lateDepartureCharge, otherCharge, isPaid);
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "register_id")
    private int registerId;
    @Column(name = "room_charge")
    private float roomCharge;
    @Column(name = "room_service")
    private float roomServiceCharge;
    @Column(name = "bar_charge")
    private float barCharge;
    @Column(name = "restaurant_charge")
    private float restaurantCharge;
    @Column(name = "late_departure_charge")
    private float lateDepartureCharge;
    @Column(name = "other_charge")
    private float otherCharge;
    @Column(name = "is_paid")
    private boolean isPaid;

    public Bill(int registerId, float roomCharge, float roomServiceCharge, float barCharge, float restaurantCharge,
                float lateDepartureCharge, float otherCharge, boolean isPaid) {
        this.registerId = registerId;
        this.roomCharge = roomCharge;
        this.roomServiceCharge = roomServiceCharge;
        this.barCharge = barCharge;
        this.restaurantCharge = restaurantCharge;
        this.lateDepartureCharge = lateDepartureCharge;
        this.otherCharge = otherCharge;
        this.isPaid = isPaid;
    }
}
