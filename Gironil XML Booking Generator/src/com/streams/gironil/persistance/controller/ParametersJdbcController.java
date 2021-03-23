/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams.gironil.persistance.controller;

import com.streams.MissingConfigurationException;
import com.streams.gironil.Configs;
import com.streams.gironil.persistance.JdbcController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author Shehata.Ibrahim
 */
public class ParametersJdbcController {

    private static Properties props = null;

    public static Properties getConfigurations() throws SQLException {
        if (props == null) {
            props = new Properties();
            selectAllParameters(props);
        }
        return props;
    }
    public static String getConfiguration(String key) throws SQLException,MissingConfigurationException
    {
        Object config= getConfigurations().get(key);
         if(config==null || config.toString().isEmpty())
            throw new MissingConfigurationException("Missing Configuration: "+key);
        return config.toString();
    }
    public static String getSchemaPath(String schemaPathkey) throws SQLException, MissingConfigurationException
    {
        String schemasPath=ParametersJdbcController.getConfiguration(Configs.SCHEMAS_FOLDER_KEY);
        String SchemaFile=ParametersJdbcController.getConfiguration(schemaPathkey);
        String fileSep=ParametersJdbcController.getConfiguration(Configs.FILE_SEPERATOR_CHAR_KEY);
        String schema=schemasPath+fileSep+SchemaFile;
        return schema;
    }

    private static void selectAllParameters(Properties props) throws SQLException {
        Connection conn = JdbcController.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet set = stmt.executeQuery(Configs.SQL_PARAMETERS_SELECT);
        while (set.next()) {
            if (set.getString(1) != null && set.getString(2) != null) {
                props.put(set.getString(1), set.getString(2));
            }
    //        System.out.println(set.getString(1) + "\t" + set.getString(2));
        }
        set.close();
    }
    private static PreparedStatement savingstmt = null;

    public static void saveConfigurations(Properties con) throws SQLException {
        if (savingstmt == null) {
            savingstmt = JdbcController.getConnection().prepareStatement(Configs.SQL_PARAMETERS_UPDATE);
        }
        for (Object key : con.keySet()) {
            savingstmt.setString(1, con.getProperty(key.toString()));
            savingstmt.setString(2, key.toString());
            savingstmt.addBatch();
        }
        savingstmt.executeBatch();
        props = null;
    }
}
