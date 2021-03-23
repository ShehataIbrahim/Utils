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
    private static EntityManagerFactory getEntityManagerFactory() {
        if(emf!=null)
            return emf;
       try {
            Properties props=new Properties();
            props.load(new FileInputStream(Configs.DB_CONFIG_FILE_NAME));
            HashMap<String,String> externalProps=new HashMap<String, String>();
            for(Object key:props.keySet())
                externalProps.put(key.toString(), props.getProperty(key.toString()));
           emf=Persistence.createEntityManagerFactory("Gironil_Booking_GeneratorPU", externalProps);
            return emf;
        } catch (IOException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    public static void reloadDBConfigurations()
    {
        emf=null;
        companyController=new CompanyInfoController(getEntityManagerFactory());
        batchController=new BatchesController(getEntityManagerFactory());
        transactionController=new TransactionsController(getEntityManagerFactory());
        filesBatchesController=new FilesBatchesController(getEntityManagerFactory());
        JdbcController.closeConnection();
    }
    private static  CompanyInfoController companyController=new CompanyInfoController(getEntityManagerFactory());
    private static  BatchesController batchController=new BatchesController(getEntityManagerFactory());
    private static  TransactionsController transactionController=new TransactionsController(getEntityManagerFactory());
    private static  FilesBatchesController filesBatchesController=new FilesBatchesController(getEntityManagerFactory());

    public static CompanyInfoController getCompanyController() {
        return companyController;
    }

    public static BatchesController getBatchController() {
        return batchController;
    }

    public static TransactionsController getTransactionController() {
        return transactionController;
    }
   public static void main(String[] args)
   {
       Transactions b=getTransactionController().findTransactions("T2015070103423762975841344");
       System.out.println(b.getBatchId());
       System.out.println(b.getCompanyCode());
       System.out.println(b.getCustomerName());
   }

    public static FilesBatchesController getFilesBatchesController() {
        return filesBatchesController;
    }
   
}
