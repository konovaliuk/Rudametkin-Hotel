package com.rudametkin.hotelsystem.database.daofactory;

import com.rudametkin.hotelsystem.entitys.Bill;

import java.util.List;

public interface IBillDAO {
    void save(Bill bill) throws DAOException;
    void update(Bill bill) throws DAOException;
    List<Bill> findByRegisterId(int registerId) throws DAOException;
}
