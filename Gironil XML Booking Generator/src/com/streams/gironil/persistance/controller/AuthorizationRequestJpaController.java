/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams.gironil.persistance.controller;

import com.streams.gironil.persistance.AuthorizationRequest;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.streams.gironil.persistance.Batch;
import com.streams.gironil.persistance.controller.exceptions.NonexistentEntityException;
import com.streams.gironil.persistance.controller.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Shehata.Ibrahim
 */
public class AuthorizationRequestJpaController implements Serializable {

    public AuthorizationRequestJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AuthorizationRequest authorizationRequest) throws PreexistingEntityException, Exception {
        if (authorizationRequest.getBatchCollection() == null) {
            authorizationRequest.setBatchCollection(new ArrayList<Batch>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Batch> attachedBatchCollection = new ArrayList<Batch>();
            for (Batch batchCollectionBatchToAttach : authorizationRequest.getBatchCollection()) {
                batchCollectionBatchToAttach = em.getReference(batchCollectionBatchToAttach.getClass(), batchCollectionBatchToAttach.getBatchKey());
                attachedBatchCollection.add(batchCollectionBatchToAttach);
            }
            authorizationRequest.setBatchCollection(attachedBatchCollection);
            em.persist(authorizationRequest);
            for (Batch batchCollectionBatch : authorizationRequest.getBatchCollection()) {
                AuthorizationRequest oldAuthorizationKeyOfBatchCollectionBatch = batchCollectionBatch.getAuthorizationKey();
                batchCollectionBatch.setAuthorizationKey(authorizationRequest);
                batchCollectionBatch = em.merge(batchCollectionBatch);
                if (oldAuthorizationKeyOfBatchCollectionBatch != null) {
                    oldAuthorizationKeyOfBatchCollectionBatch.getBatchCollection().remove(batchCollectionBatch);
                    oldAuthorizationKeyOfBatchCollectionBatch = em.merge(oldAuthorizationKeyOfBatchCollectionBatch);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAuthorizationRequest(authorizationRequest.getAuthorizationKey()) != null) {
                throw new PreexistingEntityException("AuthorizationRequest " + authorizationRequest + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AuthorizationRequest authorizationRequest) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AuthorizationRequest persistentAuthorizationRequest = em.find(AuthorizationRequest.class, authorizationRequest.getAuthorizationKey());
            Collection<Batch> batchCollectionOld = persistentAuthorizationRequest.getBatchCollection();
            Collection<Batch> batchCollectionNew = authorizationRequest.getBatchCollection();
            Collection<Batch> attachedBatchCollectionNew = new ArrayList<Batch>();
            for (Batch batchCollectionNewBatchToAttach : batchCollectionNew) {
                batchCollectionNewBatchToAttach = em.getReference(batchCollectionNewBatchToAttach.getClass(), batchCollectionNewBatchToAttach.getBatchKey());
                attachedBatchCollectionNew.add(batchCollectionNewBatchToAttach);
            }
            batchCollectionNew = attachedBatchCollectionNew;
            authorizationRequest.setBatchCollection(batchCollectionNew);
            authorizationRequest = em.merge(authorizationRequest);
            for (Batch batchCollectionOldBatch : batchCollectionOld) {
                if (!batchCollectionNew.contains(batchCollectionOldBatch)) {
                    batchCollectionOldBatch.setAuthorizationKey(null);
                    batchCollectionOldBatch = em.merge(batchCollectionOldBatch);
                }
            }
            for (Batch batchCollectionNewBatch : batchCollectionNew) {
                if (!batchCollectionOld.contains(batchCollectionNewBatch)) {
                    AuthorizationRequest oldAuthorizationKeyOfBatchCollectionNewBatch = batchCollectionNewBatch.getAuthorizationKey();
                    batchCollectionNewBatch.setAuthorizationKey(authorizationRequest);
                    batchCollectionNewBatch = em.merge(batchCollectionNewBatch);
                    if (oldAuthorizationKeyOfBatchCollectionNewBatch != null && !oldAuthorizationKeyOfBatchCollectionNewBatch.equals(authorizationRequest)) {
                        oldAuthorizationKeyOfBatchCollectionNewBatch.getBatchCollection().remove(batchCollectionNewBatch);
                        oldAuthorizationKeyOfBatchCollectionNewBatch = em.merge(oldAuthorizationKeyOfBatchCollectionNewBatch);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = authorizationRequest.getAuthorizationKey();
                if (findAuthorizationRequest(id) == null) {
                    throw new NonexistentEntityException("The authorizationRequest with id " + id + " no longer exists.");
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
            AuthorizationRequest authorizationRequest;
            try {
                authorizationRequest = em.getReference(AuthorizationRequest.class, id);
                authorizationRequest.getAuthorizationKey();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The authorizationRequest with id " + id + " no longer exists.", enfe);
            }
            Collection<Batch> batchCollection = authorizationRequest.getBatchCollection();
            for (Batch batchCollectionBatch : batchCollection) {
                batchCollectionBatch.setAuthorizationKey(null);
                batchCollectionBatch = em.merge(batchCollectionBatch);
            }
            em.remove(authorizationRequest);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AuthorizationRequest> findAuthorizationRequestEntities() {
        return findAuthorizationRequestEntities(true, -1, -1);
    }

    public List<AuthorizationRequest> findAuthorizationRequestEntities(int maxResults, int firstResult) {
        return findAuthorizationRequestEntities(false, maxResults, firstResult);
    }

    private List<AuthorizationRequest> findAuthorizationRequestEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AuthorizationRequest.class));
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

    public AuthorizationRequest findAuthorizationRequest(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AuthorizationRequest.class, id);
        } finally {
            em.close();
        }
    }

    public int getAuthorizationRequestCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AuthorizationRequest> rt = cq.from(AuthorizationRequest.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
