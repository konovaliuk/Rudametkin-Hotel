package SqlConnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class MySqlDataSource {
    private static MySqlDataSource instance = null;
    private final ArrayList<Connection> connectionPool = new ArrayList<>();
    private final String USERNAME;
    private final String PASSWORD;
    private final String URL;


    private MySqlDataSource (int startPoolSize) throws SQLException
    {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("./src/resources/hoteldatabase.properties"));
            USERNAME = props.getProperty("db.user.name");
            PASSWORD = props.getProperty("db.user.password");
            URL = props.getProperty("jdbc.db.url");
        } catch (IOException e) {
            throw new SQLException("MySqlConnectionError");
        }

        try {
            for(int i = 0; i < startPoolSize; i++)
                connectionPool.add(DriverManager.getConnection(URL, USERNAME, PASSWORD));
        } catch (SQLException e) {
            throw new SQLException("MySqlConnectionError");
        }
    }

    public static MySqlDataSource getInstance() throws SQLException
    {
        if(instance == null)
            instance = new MySqlDataSource(1);
        return instance;
    }

    public int getPoolSize()
    {
        return connectionPool.size();
    }

    public Connection getConnection() throws SQLException
    {
        if(connectionPool.size() == 0)
            try {
                connectionPool.add(DriverManager.getConnection(URL, USERNAME, PASSWORD));
            } catch (SQLException e) {
                 throw new SQLException("MySqlConnectionError");
            }

        Connection con = connectionPool.get(0);
        connectionPool.remove(0);
        return con;
    }
    public void releaseConnection(Connection con)
    {
        connectionPool.add(con);
    }
    public void closePoolConections() throws SQLException
    {
        while(connectionPool.size() > 0)
        {
            connectionPool.get(0).close();
            connectionPool.remove(0);
        }
    }
}
