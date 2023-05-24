package com.rudametkin.hotelsystem.database.jpadao.repository;

import com.rudametkin.hotelsystem.database.daofactory.DAOException;
import com.rudametkin.hotelsystem.dto.RoomClassDto;
import com.rudametkin.hotelsystem.dto.RoomSearchDto;
import com.rudametkin.hotelsystem.entitys.Room;
import com.rudametkin.hotelsystem.services.RoomsSearchingService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;

import java.sql.Timestamp;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {


    @Query("SELECT DISTINCT NEW com.rudametkin.hotelsystem.dto.RoomClassDto(r.type, r.roomsAmount, r.singleBedsAmount, r.doubleBedsAmount, r.miniBar, r.tv, r.dryer, r.price) " +
            "FROM Room r WHERE " +
            "((:#{#rp.type.name()} <> 'Any' AND r.type = :#{#rp.type}) OR :#{#rp.type.name()} = 'Any') AND " +
            "(2 * r.doubleBedsAmount + r.singleBedsAmount) >= :#{#rp.capacity} AND " +
            "(r.doubleBedsAmount + r.singleBedsAmount) >= :#{#rp.bedsAmount} AND " +
            "(" +
            "   SELECT COUNT(rr) FROM RoomRegister rr " +
            "   WHERE rr.endDateTime > :#{#rp.arrivalDate} AND rr.startDateTime < :#{#rp.departureDate} " +
            "   AND rr.roomNumber = r.number" +
            ") = 0")
    List<RoomClassDto> findFreeRoomClassesByParams(@Param("rp") RoomSearchDto rp, Pageable pageable);

    @Query("SELECT COUNT( DISTINCT (r.type, r.roomsAmount, r.singleBedsAmount, r.doubleBedsAmount, r.miniBar, r.tv, r.dryer, r.price)) " +
            "FROM Room r WHERE " +
            "((:#{#rp.type.name()} <> 'Any' AND r.type = :#{#rp.type}) OR :#{#rp.type.name()} = 'Any') AND " +
            "(2 * r.doubleBedsAmount + r.singleBedsAmount) >= :#{#rp.capacity} AND " +
            "(r.doubleBedsAmount + r.singleBedsAmount) >= :#{#rp.bedsAmount} AND " +
            "(" +
            "   SELECT COUNT(rr) FROM RoomRegister rr " +
            "   WHERE rr.endDateTime > :#{#rp.arrivalDate} AND rr.startDateTime < :#{#rp.departureDate} " +
            "   AND rr.roomNumber = r.number" +
            ") = 0")
    int countFreeRoomClassesByParams(@Param("rp") RoomSearchDto rp);

    @Query("SELECT r FROM Room r WHERE " +
            "r.type = :#{#rc.type} " +
            "AND r.roomsAmount = :#{#rc.roomsAmount} " +
            "AND r.singleBedsAmount = :#{#rc.singleBedsAmount} " +
            "AND r.doubleBedsAmount = :#{#rc.doubleBedsAmount} " +
            "AND r.miniBar = :#{#rc.miniBar} " +
            "AND r.tv = :#{#rc.tv} " +
            "AND r.dryer = :#{#rc.dryer} " +
            "AND r.price = :#{#rc.price} " +
            "AND (" +
            "    SELECT COUNT(rr) FROM RoomRegister rr" +
            "    WHERE (rr.endDateTime > (:startDateTime)) AND (rr.startDateTime < (:endDateTime)) " +
            "    AND rr.roomNumber = r.number" +
            "    ) = 0 ")
    List<Room> findFreeRoomsByRoomClass(@Param("rc") RoomClassDto roomclass,
                                 @Param("startDateTime") Timestamp arrivalTime,
                                 @Param("endDateTime") Timestamp departureTime,
                                 Pageable pageable);
}

