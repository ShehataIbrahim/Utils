/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams.gironil.persistance;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Shehata.Ibrahim
 */
public abstract class Controller {
protected Controller()
{
    
}

    public EntityManager getEntityManager() {
        return DBController.getEntityManagerFactory().createEntityManager();
    }

    public void startTransaction() {
        EntityManager em = null;
        em = getEntityManager();
        em.getTransaction().begin();
    }

    public void commitTransation() {
        EntityManager em = null;
        em = getEntityManager();
        em.getTransaction().commit();
    }
    public abstract Controller getInstance();
}
