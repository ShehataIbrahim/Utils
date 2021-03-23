/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams.gironil.persistance.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.streams.gironil.persistance.AuthorizationRequest;
import com.streams.gironil.persistance.Batch;
import com.streams.gironil.persistance.Controller;
import com.streams.gironil.persistance.controller.exceptions.NonexistentEntityException;
import com.streams.gironil.persistance.controller.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Shehata.Ibrahim
 */
public class BatchJpaController extends Controller {

    private BatchJpaController() {
        super();
    }
    private BatchJpaController controller = null;

    @Override
    public BatchJpaController getInstance() {
        if (controller == null) {
            controller = new BatchJpaController();
        }
        return controller;

    }

    public void create(Batch batch) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AuthorizationRequest authorizationKey = batch.getAuthorizationKey();
            if (authorizationKey != null) {
                authorizationKey = em.getReference(authorizationKey.getClass(), authorizationKey.getAuthorizationKey());
                batch.setAuthorizationKey(authorizationKey);
            }
            em.persist(batch);
            if (authorizationKey != null) {
                authorizationKey.getBatchCollection().add(batch);
                authorizationKey = em.merge(authorizationKey);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBatch(batch.getBatchKey()) != null) {
                throw new PreexistingEntityException("Batch " + batch + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Batch batch) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Batch persistentBatch = em.find(Batch.class, batch.getBatchKey());
            AuthorizationRequest authorizationKeyOld = persistentBatch.getAuthorizationKey();
            AuthorizationRequest authorizationKeyNew = batch.getAuthorizationKey();
            if (authorizationKeyNew != null) {
                authorizationKeyNew = em.getReference(authorizationKeyNew.getClass(), authorizationKeyNew.getAuthorizationKey());
                batch.setAuthorizationKey(authorizationKeyNew);
            }
            batch = em.merge(batch);
            if (authorizationKeyOld != null && !authorizationKeyOld.equals(authorizationKeyNew)) {
                authorizationKeyOld.getBatchCollection().remove(batch);
                authorizationKeyOld = em.merge(authorizationKeyOld);
            }
            if (authorizationKeyNew != null && !authorizationKeyNew.equals(authorizationKeyOld)) {
                authorizationKeyNew.getBatchCollection().add(batch);
                authorizationKeyNew = em.merge(authorizationKeyNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = batch.getBatchKey();
                if (findBatch(id) == null) {
                    throw new NonexistentEntityException("The batch with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Batch batch;
            try {
                batch = em.getReference(Batch.class, id);
                batch.getBatchKey();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The batch with id " + id + " no longer exists.", enfe);
            }
            AuthorizationRequest authorizationKey = batch.getAuthorizationKey();
            if (authorizationKey != null) {
                authorizationKey.getBatchCollection().remove(batch);
                authorizationKey = em.merge(authorizationKey);
            }
            em.remove(batch);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Batch> findBatchEntities() {
        return findBatchEntities(true, -1, -1);
    }

    public List<Batch> findBatchEntities(int maxResults, int firstResult) {
        return findBatchEntities(false, maxResults, firstResult);
    }

    private List<Batch> findBatchEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Batch.class));
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

    public Batch findBatch(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Batch.class, id);
        } finally {
            em.close();
        }
    }

    public int getBatchCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Batch> rt = cq.from(Batch.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
