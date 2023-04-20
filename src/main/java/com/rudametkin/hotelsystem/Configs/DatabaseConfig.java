package com.rudametkin.hotelsystem.Configs;

import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class DatabaseConfig {
    private final String projectDirectoryPath;
    private String directoryPath;
    private final String boundedFile;
    private Properties properties;

    public DatabaseConfig() {
        projectDirectoryPath = Paths.get("").toAbsolutePath().getParent().toString() + "/webapps/HotelSystem/target/HotelSystem-1.0-SNAPSHOT/";
        directoryPath = "resources/properties/";
        boundedFile = "hoteldatabase.properties";
        properties = new Properties();

        try {
            properties.load(new FileInputStream(projectDirectoryPath + directoryPath + boundedFile));
        } catch(Exception e) {
            properties = null;
        }
    }

    public String getProperty(String name) {
        if(properties == null) {
            return null;
        } else {
            return properties.getProperty(name);
        }
    }
}
