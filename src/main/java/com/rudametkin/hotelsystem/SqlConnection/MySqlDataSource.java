package com.rudametkin.hotelsystem.SqlConnection;

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

import com.rudametkin.hotelsystem.MySqlDAOFactory.MySqlUserDAO;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;


public class MySqlDataSource {
    private static DataSource instance = null;

    private static void initDataSource() throws SQLException {
        PoolProperties poolProps = new PoolProperties();
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(Paths.get("").toAbsolutePath().getParent().toString() +
                    "/webapps/classichotel/resources/properties/hoteldatabase.properties"));
        } catch (IOException e) {
            throw new SQLException("Can't get access to DB properties");
        }

        poolProps.setUrl(props.getProperty("jdbc.db.url"));
        poolProps.setUsername(props.getProperty("db.user.name"));
        poolProps.setPassword(props.getProperty("db.user.password"));
        poolProps.setDriverClassName(props.getProperty("jdbc.driver.class.name"));

        instance = new DataSource();
        instance.setPoolProperties(poolProps);
    }
    private MySqlDataSource() {}
    public static DataSource getInstance() throws SQLException
    {
        if(instance == null)
            initDataSource();
        return instance;
    }
}
