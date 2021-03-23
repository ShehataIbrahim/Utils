/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams.gironil.persistance;

import com.streams.gironil.Configs;
import com.streams.gironil.DBConfigurations;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
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
    static PreparedStatement transactionsStmt;

    public static PreparedStatement getTransactionsStmt() {
        if (transactionsStmt != null) {
            return transactionsStmt;
        }
        try {
            transactionsStmt = (PreparedStatement) getConnection().prepareStatement("INSERT INTO TRANSACTIONS (TRANSACTION_ID, ACCOUNT_NUMBER, AMOUNT, BATCH_ID, BIC_CODE, BRANCH_CODE, COMPANY_CODE, CREATION_DATE, CUSTOMER_NAME, CUSTOMER_REFERENCE, TRANSACTION_SERIAL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        } catch (SQLException ex) {
            Logger.getLogger(JdbcController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transactionsStmt;
    }

    public static void insertTransactions(List<Transactions> transactions) {
        PreparedStatement stmt = getTransactionsStmt();
        try {
            for (Transactions trans : transactions) {
                stmt.setString(1, trans.getTransactionId());
                stmt.setString(2, trans.getAccountNumber());
                stmt.setFloat(3, trans.getAmount());
                stmt.setString(4, trans.getBatchId());
                stmt.setString(5, trans.getBicCode());
                stmt.setString(6, trans.getBranchCode());
                stmt.setString(7, trans.getCompanyCode());
                stmt.setDate(8, new java.sql.Date(trans.getCreationDate().getTime()));
                stmt.setString(9, trans.getCustomerName());
                stmt.setString(10, trans.getCustomerReference());
                stmt.setInt(11, trans.getTransactionSerial());
                stmt.addBatch();
            }
            stmt.executeBatch();
            /*   connection.close();
             connection=null;*/

        } catch (SQLException ex) {
            Logger.getLogger(JdbcController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public static void closeConnection() {
        try {
            transactionsStmt = null;
            if (connection != null) {
                connection.close();
            }
            connection = null;
        } catch (SQLException ex) {
            Logger.getLogger(JdbcController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
