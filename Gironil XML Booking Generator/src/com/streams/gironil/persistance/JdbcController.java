/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams.gironil.persistance;

import com.streams.gironil.Configs;
import com.streams.gironil.DBConfigurations;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shehata.Ibrahim
 */
public class JdbcController {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            DBConfigurations dbConfigs = Configs.loadDBConfigurations();
            if (dbConfigs != null) {
                try {
                    Class.forName(dbConfigs.getDriver());
                    connection = DriverManager.getConnection(dbConfigs.getUrl(), dbConfigs.getUserName(), dbConfigs.getPassword());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    connection = null;
                    return connection;
                }

            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
     
            if (connection != null) {
                connection.close();
            }
            connection = null;
        } catch (SQLException ex) {
            Logger.getLogger(JdbcController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
