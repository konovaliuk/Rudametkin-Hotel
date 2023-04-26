package com.rudametkin.hotelsystem.Database.DAOFactory;

import com.rudametkin.hotelsystem.Entitys.Bill;

import java.util.ArrayList;
import java.util.List;

public interface IBillDAO {
    void save(Bill bill) throws DAOException;
    void update(Bill bill) throws DAOException;
    List<Bill> findByRegisterId(int registerId) throws DAOException;
}
