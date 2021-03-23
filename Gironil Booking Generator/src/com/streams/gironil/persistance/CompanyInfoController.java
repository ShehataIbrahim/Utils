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
public class CompanyInfoController implements Serializable {

    public CompanyInfoController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CompanyInfo companyInfo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(companyInfo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCompanyInfo(companyInfo.getCode()) != null) {
                throw new PreexistingEntityException("CompanyInfo " + companyInfo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CompanyInfo companyInfo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            companyInfo = em.merge(companyInfo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = companyInfo.getCode();
                if (findCompanyInfo(id) == null) {
                    throw new NonexistentEntityException("The companyInfo with id " + id + " no longer exists.");
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
            CompanyInfo companyInfo;
            try {
                companyInfo = em.getReference(CompanyInfo.class, id);
                companyInfo.getCode();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The companyInfo with id " + id + " no longer exists.", enfe);
            }
            em.remove(companyInfo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CompanyInfo> findCompanyInfoEntities() {
        return findCompanyInfoEntities(true, -1, -1);
    }

    public List<CompanyInfo> findCompanyInfoEntities(int maxResults, int firstResult) {
        return findCompanyInfoEntities(false, maxResults, firstResult);
    }

    private List<CompanyInfo> findCompanyInfoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CompanyInfo.class));
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

    public CompanyInfo findCompanyInfo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CompanyInfo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanyInfoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CompanyInfo> rt = cq.from(CompanyInfo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
