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
public class BatchesController implements Serializable {

    public BatchesController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Batches batches) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(batches);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBatches(batches.getBatchId()) != null) {
                throw new PreexistingEntityException("Batches " + batches + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
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

    public void insert(Batches batches) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.persist(batches);
        } catch (Exception ex) {
            if (findBatches(batches.getBatchId()) != null) {
                throw new PreexistingEntityException("Batches " + batches + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Batches batches) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            batches = em.merge(batches);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = batches.getBatchId();
                if (findBatches(id) == null) {
                    throw new NonexistentEntityException("The batches with id " + id + " no longer exists.");
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
            Batches batches;
            try {
                batches = em.getReference(Batches.class, id);
                batches.getBatchId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The batches with id " + id + " no longer exists.", enfe);
            }
            em.remove(batches);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Batches> findBatchesEntities() {
        return findBatchesEntities(true, -1, -1);
    }

    public List<Batches> findBatchesEntities(int maxResults, int firstResult) {
        return findBatchesEntities(false, maxResults, firstResult);
    }

    private List<Batches> findBatchesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Batches.class));
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

    public Batches findBatches(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Batches.class, id);
        } finally {
            em.close();
        }
    }

    public int getBatchesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Batches> rt = cq.from(Batches.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
