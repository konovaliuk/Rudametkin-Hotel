package com.rudametkin.hotelsystem.database.jpadao;

import com.rudametkin.hotelsystem.database.daofactory.DAOException;
import com.rudametkin.hotelsystem.database.daofactory.IBillDAO;
import com.rudametkin.hotelsystem.database.jpadao.repository.BillRepository;
import com.rudametkin.hotelsystem.database.jpadao.repository.RoleRepository;
import com.rudametkin.hotelsystem.entitys.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JPABillDAO implements IBillDAO {

    private final BillRepository billRepository;

    public JPABillDAO(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public void save(Bill bill) throws DAOException {
        billRepository.save(bill);
    }

    @Override
    public void update(Bill bill) throws DAOException {
        billRepository.save(bill);
    }

    @Override
    public List<Bill> findByRegisterId(int registerId) throws DAOException {
        return billRepository.findAllByRoomRegisterId(registerId);
    }
}
