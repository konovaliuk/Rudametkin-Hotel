package com.rudametkin.hotelsystem.database.jpadao.repository;

import com.rudametkin.hotelsystem.entitys.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {

    @Query("SELECT b FROM Bill b WHERE b.registerId = :roomRegisterId")
    List<Bill> findAllByRoomRegisterId(@Param("roomRegisterId") int registerId);
}
