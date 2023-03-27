package MySqlDAOFactory;

import DAOFactory.DAOException;
import DAOFactory.IBillDAO;
import SqlConnection.MySqlDataSource;
import entityObjects.Bill;

import java.sql.*;
import java.util.ArrayList;

public class MySqlBillDAO implements IBillDAO {

    private void handleSQLException(SQLException e) throws DAOException {
        if(e.getMessage().equals("MySqlConnectionError"))
            throw new DAOException("MySqlConnectionError");
        else
            throw new DAOException("ExecuteStatementError|bills");
    }

    private void setStatementBillBasicParameters(PreparedStatement stmt, Bill bill) throws SQLException
    {
        if(bill.getRegisterId() != -1)
            stmt.setInt(1, bill.getRegisterId());
        else
            stmt.setNull(1, java.sql.Types.INTEGER);

        if(bill.getClientId() != -1)
            stmt.setInt(2, bill.getClientId());
        else
            stmt.setNull(2, java.sql.Types.INTEGER);

        stmt.setFloat(3, bill.getRoomCharge());
        stmt.setFloat(4, bill.getRoomServiceCharge());
        stmt.setFloat(5, bill.getBarCharge());
        stmt.setFloat(6, bill.getRestaurantCharge());
        stmt.setFloat(7, bill.getLateDepartureCharge());
        stmt.setFloat(8, bill.getOtherCharge());
        stmt.setBoolean(9, bill.getIsPaid());
    }

    @Override
    public void add(Bill bill) throws DAOException {
        try {
            MySqlDataSource msds = MySqlDataSource.getInstance();
            Connection con = msds.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO bills (register_id, client_id, room_charge, room_service, " +
                            "bar_charge, restaurant_charge, late_departure_charge, other_charge, is_paid) " +
                            "VALUES (?,?,?,?,?,?,?,?,?);");
            setStatementBillBasicParameters(stmt, bill);
            stmt.execute();
            msds.releaseConnection(con);
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    @Override
    public void update(Bill bill) throws DAOException {
        try {
            MySqlDataSource msds = MySqlDataSource.getInstance();
            Connection con = msds.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "UPDATE bills SET register_id = ?, client_id = ?, room_charge = ?, room_service = ?, " +
                            "bar_charge = ?, restaurant_charge = ?, late_departure_charge = ?, other_charge = ?, is_paid = ?) " +
                            "WHERE id = ?;");
            setStatementBillBasicParameters(stmt, bill);
            stmt.setInt(10, bill.getId());
            stmt.execute();
            msds.releaseConnection(con);
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    @Override
    public ArrayList<Bill> findByClientId(int clientId) throws DAOException {
        try {
            MySqlDataSource msds = MySqlDataSource.getInstance();
            Connection con = msds.getConnection();
            CallableStatement stmt = con.prepareCall("SELECT * FROM bills WHERE client_id = ? ;");
            stmt.setInt(1, clientId);
            ResultSet resultSet = stmt.executeQuery();
            ArrayList<Bill> list = new ArrayList<>();
            while(resultSet.next())
            {
                int registerId = -1;
                if(resultSet.getInt(2) == 0)
                    registerId = resultSet.getInt(2);
                list.add(new Bill(resultSet.getInt(1), registerId, resultSet.getInt(3),
                        resultSet.getFloat(4), resultSet.getFloat(5), resultSet.getFloat(6),
                        resultSet.getFloat(7), resultSet.getFloat(8), resultSet.getFloat(9),
                        resultSet.getBoolean(10)));
            }
            msds.releaseConnection(con);
            return list;
        } catch (SQLException e) {
            handleSQLException(e);
            return new ArrayList<>();
        }
    }
    @Override
    public ArrayList<Bill> findByRegisterId(int registerId) throws DAOException {
        try {
            MySqlDataSource msds = MySqlDataSource.getInstance();
            Connection con = msds.getConnection();
            CallableStatement stmt = con.prepareCall("SELECT * FROM bills WHERE register_id = ? ;");
            stmt.setInt(1, registerId);
            ResultSet resultSet = stmt.executeQuery();
            ArrayList<Bill> list = new ArrayList<>();
            while(resultSet.next())
            {
                int clientId = -1;
                if(resultSet.getInt(3) == 0)
                    clientId = resultSet.getInt(3);
                list.add(new Bill(resultSet.getInt(1), resultSet.getInt(2), clientId,
                        resultSet.getFloat(4), resultSet.getFloat(5), resultSet.getFloat(6),
                        resultSet.getFloat(7), resultSet.getFloat(8), resultSet.getFloat(9),
                        resultSet.getBoolean(10)));
            }
            msds.releaseConnection(con);
            return list;
        } catch (SQLException e) {
            handleSQLException(e);
            return new ArrayList<>();
        }
    }
}
