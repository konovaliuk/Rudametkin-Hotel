package com.rudametkin.hotelsystem.DAOFactory;

import com.rudametkin.hotelsystem.entityObjects.Bill;

import java.util.ArrayList;

public interface IBillDAO {
    void add(Bill bill) throws DAOException;
    void update(Bill bill) throws DAOException;
    ArrayList<Bill> findByClientId(int clientId) throws DAOException;
    ArrayList<Bill> findByRegisterId(int clientId) throws DAOException;
}
