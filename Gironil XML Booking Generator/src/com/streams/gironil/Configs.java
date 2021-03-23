/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams.gironil;

import com.streams.gironil.persistance.controller.ParametersJdbcController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Shehata.Ibrahim
 */
public class Configs {

    public static final String CONFIG_FILE_NAME = "config.properties";
    public static final String INPUT_FOLDER_KEY = "XMLInputPath";
    public static final String PROCESSED_FOLDER_KEY = "XMLValidatedPath";
    public static final String ERROR_FOLDER_KEY = "XMLFileErrorPath";
    public static final String OUTPUT_FOLDER_KEY = "XMLOutputPath";
    public static final String SCHEMAS_FOLDER_KEY = "XMLSchemasPath";
    public static final String INPUT_FILE_EXTENSION = ".xml";
    public static final String DEFAULT_ENCODING = "Windows-1256";
    public static final String DB_CONFIG_FILE_NAME = "dbconfig.properties";
    public static final String DB_DRIVER_KEY = "javax.persistence.jdbc.driver";
    public static final String DB_URL_KEY = "javax.persistence.jdbc.url";
    public static final String DB_USERNAME_KEY = "javax.persistence.jdbc.user";
    public static final String DB_PASSWORD_KEY = "javax.persistence.jdbc.password";
    public static final String DB_HOST_KEY = "host";
    public static final String DB_DB_NAME_KEY = "dbName";
    public static final String DBMS_SUPPORTED_DBMS_FILE = "dbms.properties";
    public static final String PERSISTANCE_UNIT_NAME = "Gironil_XML_Booking_GeneratorPU";
    public static final String XSDBOOKING_KEY="XSDLocationBOO";
    public static final String XSDPACS_KEY="XSDLocationPAC";
    public static final String XSDACCEPT_GIRO_KEY="XSDLocationAcceptGiro";
    public static final String FILE_SEPERATOR_CHAR_KEY="SyncFileEscapeCharacter";
    public static final String BATCH_HEADER_NODE="GrpHdr";
    public static final String BATCH_TRANSACTION_NODE="PmtInf";
    
    public static final String SQL_PARAMETERS_UPDATE="UPDATE PARAMETER SET VALUE_CHAR=? WHERE NAME=?";
    public static final String SQL_PARAMETERS_SELECT="SELECT NAME,VALUE_CHAR FROM PARAMETER";
    public static final String SQL_ERROR_SELECT_BY_CODE="SELECT DESCRIPTION FROM ERROR_REASONS WHERE CODE='{code}'";
    public static final String TOKEN_ERROR_CODE="{code}";
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
    public static Properties loadConfigurations() throws SQLException {
       return ParametersJdbcController.getConfigurations();
    }
    

    public static void saveConfigurations(String inputFolder, String processedFolder, String outputFolder,String errorFolder, String schemasFolder) throws SQLException {
        Properties con = new Properties();
    
        con.setProperty(INPUT_FOLDER_KEY, inputFolder);
        con.setProperty(PROCESSED_FOLDER_KEY, processedFolder);
        con.setProperty(OUTPUT_FOLDER_KEY, outputFolder);
        con.setProperty(SCHEMAS_FOLDER_KEY, schemasFolder);
        ParametersJdbcController.saveConfigurations(con);
      // con.store(new FileOutputStream(outputFile), "");
        

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
