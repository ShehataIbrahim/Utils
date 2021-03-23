/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams.gironil.persistance.controller;

import com.streams.gironil.Configs;
import com.streams.gironil.persistance.JdbcController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shehata.Ibrahim
 */
public class ErrorsJdbcController {
    public static String getErrorReason(String code,String[] params) throws SQLException {
        Connection conn = JdbcController.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet set = stmt.executeQuery(Configs.SQL_ERROR_SELECT_BY_CODE.replace(Configs.TOKEN_ERROR_CODE, code));
        if (set.next()) {
            String error= set.getString(1);
            if(params!=null)
            {
                for(int i=0;i<params.length;i++)
                {
                    error=error.replace("{"+(i+1)+"}", params[i]);
                }
            }
            set.close();
            return error;
        }
        set.close();
        return "Unknown Error Occured";
    }
    public static void main(String[] args)
    {
        try {
            String[] str=new String[2];
            str[0]="Hello.text";
            str[1]="Helloxsd";
            System.out.println(ErrorsJdbcController.getErrorReason("GN0001", str));
        } catch (SQLException ex) {
            Logger.getLogger(ErrorsJdbcController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
