/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams.gironil.persistance;

import com.streams.gironil.persistance.exceptions.NonexistentEntityException;
import com.streams.gironil.persistance.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Shehata.Ibrahim
 */
public class TransactionsController implements Serializable {

    public TransactionsController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Transactions transactions) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(transactions);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTransactions(transactions.getTransactionId()) != null) {
                throw new PreexistingEntityException("Transactions " + transactions + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Transactions transactions) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            transactions = em.merge(transactions);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = transactions.getTransactionId();
                if (findTransactions(id) == null) {
                    throw new NonexistentEntityException("The transactions with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Transactions transactions;
            try {
                transactions = em.getReference(Transactions.class, id);
                transactions.getTransactionId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transactions with id " + id + " no longer exists.", enfe);
            }
            em.remove(transactions);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Transactions> findTransactionsEntities() {
        return findTransactionsEntities(true, -1, -1);
    }

    public List<Transactions> findTransactionsEntities(int maxResults, int firstResult) {
        return findTransactionsEntities(false, maxResults, firstResult);
    }

    private List<Transactions> findTransactionsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Transactions.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Transactions findTransactions(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Transactions.class, id);
        } finally {
            em.close();
        }
    }

    public int getTransactionsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Transactions> rt = cq.from(Transactions.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
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
     public void insert(Transactions transactions) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.persist(transactions);
        } catch (Exception ex) {
            if (findTransactions(transactions.getTransactionId()) != null) {
                throw new PreexistingEntityException("Transactions " + transactions + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
