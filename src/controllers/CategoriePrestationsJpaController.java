/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.BaremePrestations;
import entities.CategoriePrestations;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author EDY TCHOKOUANI
 */
public class CategoriePrestationsJpaController implements Serializable {

    public CategoriePrestationsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CategoriePrestations categoriePrestations) {
        if (categoriePrestations.getBaremePrestationsList() == null) {
            categoriePrestations.setBaremePrestationsList(new ArrayList<BaremePrestations>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<BaremePrestations> attachedBaremePrestationsList = new ArrayList<BaremePrestations>();
            for (BaremePrestations baremePrestationsListBaremePrestationsToAttach : categoriePrestations.getBaremePrestationsList()) {
                baremePrestationsListBaremePrestationsToAttach = em.getReference(baremePrestationsListBaremePrestationsToAttach.getClass(), baremePrestationsListBaremePrestationsToAttach.getId());
                attachedBaremePrestationsList.add(baremePrestationsListBaremePrestationsToAttach);
            }
            categoriePrestations.setBaremePrestationsList(attachedBaremePrestationsList);
            em.persist(categoriePrestations);
            for (BaremePrestations baremePrestationsListBaremePrestations : categoriePrestations.getBaremePrestationsList()) {
                CategoriePrestations oldCategoriePrestationIdOfBaremePrestationsListBaremePrestations = baremePrestationsListBaremePrestations.getCategoriePrestationId();
                baremePrestationsListBaremePrestations.setCategoriePrestationId(categoriePrestations);
                baremePrestationsListBaremePrestations = em.merge(baremePrestationsListBaremePrestations);
                if (oldCategoriePrestationIdOfBaremePrestationsListBaremePrestations != null) {
                    oldCategoriePrestationIdOfBaremePrestationsListBaremePrestations.getBaremePrestationsList().remove(baremePrestationsListBaremePrestations);
                    oldCategoriePrestationIdOfBaremePrestationsListBaremePrestations = em.merge(oldCategoriePrestationIdOfBaremePrestationsListBaremePrestations);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CategoriePrestations categoriePrestations) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriePrestations persistentCategoriePrestations = em.find(CategoriePrestations.class, categoriePrestations.getId());
            List<BaremePrestations> baremePrestationsListOld = persistentCategoriePrestations.getBaremePrestationsList();
            List<BaremePrestations> baremePrestationsListNew = categoriePrestations.getBaremePrestationsList();
            List<BaremePrestations> attachedBaremePrestationsListNew = new ArrayList<BaremePrestations>();
            for (BaremePrestations baremePrestationsListNewBaremePrestationsToAttach : baremePrestationsListNew) {
                baremePrestationsListNewBaremePrestationsToAttach = em.getReference(baremePrestationsListNewBaremePrestationsToAttach.getClass(), baremePrestationsListNewBaremePrestationsToAttach.getId());
                attachedBaremePrestationsListNew.add(baremePrestationsListNewBaremePrestationsToAttach);
            }
            baremePrestationsListNew = attachedBaremePrestationsListNew;
            categoriePrestations.setBaremePrestationsList(baremePrestationsListNew);
            categoriePrestations = em.merge(categoriePrestations);
            for (BaremePrestations baremePrestationsListOldBaremePrestations : baremePrestationsListOld) {
                if (!baremePrestationsListNew.contains(baremePrestationsListOldBaremePrestations)) {
                    baremePrestationsListOldBaremePrestations.setCategoriePrestationId(null);
                    baremePrestationsListOldBaremePrestations = em.merge(baremePrestationsListOldBaremePrestations);
                }
            }
            for (BaremePrestations baremePrestationsListNewBaremePrestations : baremePrestationsListNew) {
                if (!baremePrestationsListOld.contains(baremePrestationsListNewBaremePrestations)) {
                    CategoriePrestations oldCategoriePrestationIdOfBaremePrestationsListNewBaremePrestations = baremePrestationsListNewBaremePrestations.getCategoriePrestationId();
                    baremePrestationsListNewBaremePrestations.setCategoriePrestationId(categoriePrestations);
                    baremePrestationsListNewBaremePrestations = em.merge(baremePrestationsListNewBaremePrestations);
                    if (oldCategoriePrestationIdOfBaremePrestationsListNewBaremePrestations != null && !oldCategoriePrestationIdOfBaremePrestationsListNewBaremePrestations.equals(categoriePrestations)) {
                        oldCategoriePrestationIdOfBaremePrestationsListNewBaremePrestations.getBaremePrestationsList().remove(baremePrestationsListNewBaremePrestations);
                        oldCategoriePrestationIdOfBaremePrestationsListNewBaremePrestations = em.merge(oldCategoriePrestationIdOfBaremePrestationsListNewBaremePrestations);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = categoriePrestations.getId();
                if (findCategoriePrestations(id) == null) {
                    throw new NonexistentEntityException("The categoriePrestations with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriePrestations categoriePrestations;
            try {
                categoriePrestations = em.getReference(CategoriePrestations.class, id);
                categoriePrestations.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoriePrestations with id " + id + " no longer exists.", enfe);
            }
            List<BaremePrestations> baremePrestationsList = categoriePrestations.getBaremePrestationsList();
            for (BaremePrestations baremePrestationsListBaremePrestations : baremePrestationsList) {
                baremePrestationsListBaremePrestations.setCategoriePrestationId(null);
                baremePrestationsListBaremePrestations = em.merge(baremePrestationsListBaremePrestations);
            }
            em.remove(categoriePrestations);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CategoriePrestations> findCategoriePrestationsEntities() {
        return findCategoriePrestationsEntities(true, -1, -1);
    }

    public List<CategoriePrestations> findCategoriePrestationsEntities(int maxResults, int firstResult) {
        return findCategoriePrestationsEntities(false, maxResults, firstResult);
    }

    private List<CategoriePrestations> findCategoriePrestationsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CategoriePrestations.class));
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

    public CategoriePrestations findCategoriePrestations(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CategoriePrestations.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriePrestationsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CategoriePrestations> rt = cq.from(CategoriePrestations.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
