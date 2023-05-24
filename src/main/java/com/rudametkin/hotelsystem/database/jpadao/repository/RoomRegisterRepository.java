package com.rudametkin.hotelsystem.database.jpadao.repository;

import com.rudametkin.hotelsystem.entitys.Room;
import com.rudametkin.hotelsystem.entitys.RoomRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface RoomRegisterRepository extends JpaRepository<RoomRegister, Integer> {
    RoomRegister findLastByRoomNumber(int number);
    List<RoomRegister> findAllByClientId(int id);

    @Query("SELECT rr FROM RoomRegister rr WHERE rr.clientId = :id AND " +
            "rr.endDateTime > (:activeTimePoint)")
    List<RoomRegister> findActiveByClientId(@Param("id") int id, @Param("activeTimePoint") Timestamp activeTimePoint);
}
