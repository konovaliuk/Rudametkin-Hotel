package com.rudametkin.hotelsystem.Database.DAOFactory;

import com.rudametkin.hotelsystem.Entitys.Bill;

import java.util.ArrayList;

public interface IBillDAO {
    void save(Bill bill) throws DAOException;
    void update(Bill bill) throws DAOException;
    ArrayList<Bill> findByRegisterId(int clientId) throws DAOException;
}
