/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams.gironil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Shehata.Ibrahim
 */
public class Configs {

    public static final String CONFIG_FILE_NAME = "config.properties";
    public static final String INPUT_FOLDER_KEY = "inputFolder";
    public static final String PROCESSED_FOLDER_KEY = "processedFolder";
    public static final String OUTPUT_FOLDER_KEY = "outputFolder";
    public static final String INPUT_FILE_EXTENSION = ".csv";
    public static final String DEFAULT_ENCODING = "Windows-1256";
    public static final String DB_CONFIG_FILE_NAME = "dbconfig.properties";
    public static final String DB_DRIVER_KEY = "javax.persistence.jdbc.driver";
    public static final String DB_URL_KEY = "javax.persistence.jdbc.url";
    public static final String DB_USERNAME_KEY = "javax.persistence.jdbc.user";
    public static final String DB_PASSWORD_KEY = "javax.persistence.jdbc.password";
    public static final String DB_HOST_KEY = "host";
    public static final String DB_DB_NAME_KEY = "dbName";
    public static final String DBMS_SUPPORTED_DBMS_FILE="dbms.properties";

    public static Properties loadConfigurations(String fileName) {
        File f = new File(fileName);
        if (!f.exists()) {
            return null;
        } else {
            try {
                Properties con = new Properties();
                con.load(new FileInputStream(f));
                return con;
            } catch (IOException ex) {
                return null;
            }
        }
    }

   public  static void saveConfigurations(String inputFolder, String processedFolder, String outputFolder) throws IOException {
        File outputFile = new File(CONFIG_FILE_NAME);
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }
        Properties con = new Properties();
        con.load(new FileInputStream(CONFIG_FILE_NAME));
        con.setProperty(INPUT_FOLDER_KEY, inputFolder);
        con.setProperty(PROCESSED_FOLDER_KEY, processedFolder);
        con.setProperty(OUTPUT_FOLDER_KEY, outputFolder);
        con.store(new FileOutputStream(outputFile), "");

    }

    public static DBConfigurations loadDBConfigurations() {
        File f = new File(Configs.DB_CONFIG_FILE_NAME);
        if (!f.exists()) {
            return null;
        }
        try {
            Properties con = new Properties();
            con.load(new FileInputStream(f));
            DBConfigurations configs = new DBConfigurations();
            configs.setDriver(con.getProperty(DB_DRIVER_KEY));
            configs.setDbName(con.getProperty(DB_DB_NAME_KEY));
            configs.setHost(con.getProperty(DB_HOST_KEY));
            configs.setPassword(con.getProperty(DB_PASSWORD_KEY));
            configs.setUrl(con.getProperty(DB_URL_KEY));
            configs.setUserName(con.getProperty(DB_USERNAME_KEY));
            return configs;
        } catch (IOException ex) {
            return null;
        }
    }

    static void saveConfigurations(DBConfigurations newConfigs) throws IOException {
        File outputFile = new File(DB_CONFIG_FILE_NAME);
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }
        Properties con = new Properties();
        con.load(new FileInputStream(DB_CONFIG_FILE_NAME));
        con.setProperty(DB_DB_NAME_KEY, newConfigs.getDbName());
        con.setProperty(DB_DRIVER_KEY, newConfigs.getDriver());
        con.setProperty(DB_HOST_KEY, newConfigs.getHost());
        con.setProperty(DB_PASSWORD_KEY, newConfigs.getPassword());
        con.setProperty(DB_URL_KEY, newConfigs.getUrl());
        con.setProperty(DB_USERNAME_KEY, newConfigs.getUserName());
        con.store(new FileOutputStream(outputFile), "");
    }
}
