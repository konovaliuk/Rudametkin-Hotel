package com.rudametkin.hotelsystem.Database.MySqlDAOFactory;

import com.rudametkin.hotelsystem.Database.DAOFactory.DAOException;
import com.rudametkin.hotelsystem.Database.DAOFactory.IBillDAO;
import com.rudametkin.hotelsystem.Database.SqlConnection.MySqlDataSource;
import com.rudametkin.hotelsystem.Entitys.Bill;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.*;
import java.util.ArrayList;

public class MySqlBillDAO extends MySqlTransactionalDAO implements IBillDAO {

    public MySqlBillDAO() {
        connection = null;
    }

    public MySqlBillDAO(Connection connection) {
        this.connection = connection;
    }

    private void setStatementBillBasicParameters(PreparedStatement stmt, Bill bill) throws SQLException
    {
        stmt.setInt(1, bill.getRegisterId());
        stmt.setFloat(2, bill.getRoomCharge());
        stmt.setFloat(3, bill.getRoomServiceCharge());
        stmt.setFloat(4, bill.getBarCharge());
        stmt.setFloat(5, bill.getRestaurantCharge());
        stmt.setFloat(6, bill.getLateDepartureCharge());
        stmt.setFloat(7, bill.getOtherCharge());
        stmt.setBoolean(8, bill.isPaid());
    }

    @Override
    public void save(Bill bill) throws DAOException {
        executeInTransaction(() -> {
            try(PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO bills (register_id, room_charge, room_service, " +
                            "bar_charge, restaurant_charge, late_departure_charge, other_charge, is_paid) " +
                            "VALUES (?,?,?,?,?,?,?,?);"))
            {
                setStatementBillBasicParameters(stmt, bill);
                stmt.execute();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        });

    }
    @Override
    public void update(Bill bill) throws DAOException {
        executeInTransaction(() -> {
            try(PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE bills SET register_id = ?, room_charge = ?, room_service = ?, " +
                            "bar_charge = ?, restaurant_charge = ?, late_departure_charge = ?, other_charge = ?, is_paid = ?) " +
                            "WHERE id = ?;"))
            {
                setStatementBillBasicParameters(stmt, bill);
                stmt.setInt(9, bill.getId());
                stmt.execute();
            } catch (SQLException e) {
               throw new DAOException(e.getMessage());
            }
        });
    }

    @Override
    public ArrayList<Bill> findByRegisterId(int registerId) throws DAOException {
        return executeInTransaction(() -> {
            ResultSet resultSet = null;
            ArrayList<Bill> list = new ArrayList<>();
            try(CallableStatement stmt = connection.prepareCall("SELECT * FROM bills WHERE register_id = ? ;")) {
                stmt.setInt(1, registerId);
                resultSet = stmt.executeQuery();
                while(resultSet.next())
                    list.add(new Bill(resultSet.getInt(1), resultSet.getInt(2),
                            resultSet.getFloat(3), resultSet.getFloat(4), resultSet.getFloat(5),
                            resultSet.getFloat(6), resultSet.getFloat(7), resultSet.getFloat(8),
                            resultSet.getBoolean(9)));
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            } finally {
                if(resultSet != null)
                    try {
                        resultSet.close();
                    } catch (SQLException ignore) {}
                return list;
            }
        });
    }
}
