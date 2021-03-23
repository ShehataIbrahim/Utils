/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams.gironil.persistance;

import com.streams.gironil.Configs;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Shehata.Ibrahim
 */
public class DBController {
    private static EntityManagerFactory emf;
    public static EntityManagerFactory getEntityManagerFactory() {
        if(emf!=null)
            return emf;
       try {
            Properties props=new Properties();
            props.load(new FileInputStream("dbconfig.properties"));
            HashMap<String,String> externalProps=new HashMap<String, String>();
            for(Object key:props.keySet())
                externalProps.put(key.toString(), props.getProperty(key.toString()));
           emf=Persistence.createEntityManagerFactory(Configs.PERSISTANCE_UNIT_NAME, externalProps);
            return emf;
        } catch (IOException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static void reloadDBConfigurations()
    {
        emf=null;
        JdbcController.closeConnection();
    }
}
