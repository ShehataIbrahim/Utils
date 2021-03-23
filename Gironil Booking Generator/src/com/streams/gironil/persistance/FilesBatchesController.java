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
public class FilesBatchesController implements Serializable {

    public FilesBatchesController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FilesBatches filesBatches) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(filesBatches);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFilesBatches(filesBatches.getBatchId()) != null) {
                throw new PreexistingEntityException("FilesBatches " + filesBatches + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FilesBatches filesBatches) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            filesBatches = em.merge(filesBatches);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = filesBatches.getBatchId();
                if (findFilesBatches(id) == null) {
                    throw new NonexistentEntityException("The filesBatches with id " + id + " no longer exists.");
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
            FilesBatches filesBatches;
            try {
                filesBatches = em.getReference(FilesBatches.class, id);
                filesBatches.getBatchId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The filesBatches with id " + id + " no longer exists.", enfe);
            }
            em.remove(filesBatches);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FilesBatches> findFilesBatchesEntities() {
        return findFilesBatchesEntities(true, -1, -1);
    }

    public List<FilesBatches> findFilesBatchesEntities(int maxResults, int firstResult) {
        return findFilesBatchesEntities(false, maxResults, firstResult);
    }

    private List<FilesBatches> findFilesBatchesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FilesBatches.class));
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

    public FilesBatches findFilesBatches(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FilesBatches.class, id);
        } finally {
            em.close();
        }
    }

    public int getFilesBatchesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FilesBatches> rt = cq.from(FilesBatches.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
