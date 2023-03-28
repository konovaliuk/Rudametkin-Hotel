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
import com.rudametkin.hotelsystem.configs.Config;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;


public class MySqlDataSource {
    private static DataSource instance = null;

    private static void initDataSource() throws SQLException {
        PoolProperties poolProps = new PoolProperties();
        Config config;
        try {
            config = new Config("resources/properties/", "hoteldatabase.properties");
        } catch (Exception e) {
            throw new SQLException("Can't get access to DB properties");
        }

        poolProps.setUrl(config.getProperty("jdbc.db.url"));
        poolProps.setUsername(config.getProperty("db.user.name"));
        poolProps.setPassword(config.getProperty("db.user.password"));
        poolProps.setDriverClassName(config.getProperty("jdbc.driver.class.name"));

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
