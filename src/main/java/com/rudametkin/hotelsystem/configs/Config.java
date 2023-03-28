package com.rudametkin.hotelsystem.configs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {
    private String boundedFile;
    private String directoryPath;
    private Properties properties;
    private final String projectDirectoryPath;

    public Config() {
        boundedFile = null;
        directoryPath = null;
        properties = null;
        projectDirectoryPath = Paths.get("").toAbsolutePath().getParent().toString() + "/webapps/classichotel/";
    }

    public Config(String propertysDirPath, String propertyFile) throws Exception {
        directoryPath = propertysDirPath;
        boundedFile = propertyFile;
        projectDirectoryPath = Paths.get("").toAbsolutePath().getParent().toString() + "/webapps/HotelSystem/target/HotelSystem-1.0-SNAPSHOT/";
        properties = new Properties();
        try {
            properties.load(new FileInputStream(projectDirectoryPath + directoryPath + boundedFile));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            properties = null;
            throw new Exception(e.getMessage());
        }
    }

    public void boundFile(String file) throws Exception {
        if(file.equals(boundedFile) == false) {
            try {
                properties.load(new FileInputStream(projectDirectoryPath + directoryPath + boundedFile));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                properties = null;
                throw new Exception(e.getMessage());
            }
        }
    }

    public void setDirectoryPath(String dirPath) {
        directoryPath = dirPath;
    }

    public String getProperty(String name) {
        if(properties == null) {
            System.out.println("No bounded file to Config");
            return null;
        } else {
            return properties.getProperty(name);
        }
    }
}
