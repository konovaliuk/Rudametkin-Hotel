package com.rudametkin.hotelsystem.Database.SqlConnection;

import com.rudametkin.hotelsystem.Configs.DatabaseConfig;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;


public class MySqlDataSource {
    private static DataSource instance = getInstance();

    private static void initDataSource() {
        PoolProperties poolProps = new PoolProperties();
        DatabaseConfig config = new DatabaseConfig();

        poolProps.setUrl(config.getProperty("jdbc.db.url"));
        poolProps.setUsername(config.getProperty("db.user.name"));
        poolProps.setPassword(config.getProperty("db.user.password"));
        poolProps.setDriverClassName(config.getProperty("jdbc.driver.class.name"));

        instance = new DataSource();
        instance.setPoolProperties(poolProps);
    }
    private MySqlDataSource() {}
    public static DataSource getInstance() {
        if(instance == null)
            initDataSource();
        return instance;
    }
}
