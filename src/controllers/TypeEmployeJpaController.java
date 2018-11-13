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
import entities.TypeEmploye;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author EDY TCHOKOUANI
 */
public class TypeEmployeJpaController implements Serializable {

    public TypeEmployeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TypeEmploye typeEmploye) {
        if (typeEmploye.getBaremePrestationsList() == null) {
            typeEmploye.setBaremePrestationsList(new ArrayList<BaremePrestations>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<BaremePrestations> attachedBaremePrestationsList = new ArrayList<BaremePrestations>();
            for (BaremePrestations baremePrestationsListBaremePrestationsToAttach : typeEmploye.getBaremePrestationsList()) {
                baremePrestationsListBaremePrestationsToAttach = em.getReference(baremePrestationsListBaremePrestationsToAttach.getClass(), baremePrestationsListBaremePrestationsToAttach.getId());
                attachedBaremePrestationsList.add(baremePrestationsListBaremePrestationsToAttach);
            }
            typeEmploye.setBaremePrestationsList(attachedBaremePrestationsList);
            em.persist(typeEmploye);
            for (BaremePrestations baremePrestationsListBaremePrestations : typeEmploye.getBaremePrestationsList()) {
                TypeEmploye oldTypeEmployeIdOfBaremePrestationsListBaremePrestations = baremePrestationsListBaremePrestations.getTypeEmployeId();
                baremePrestationsListBaremePrestations.setTypeEmployeId(typeEmploye);
                baremePrestationsListBaremePrestations = em.merge(baremePrestationsListBaremePrestations);
                if (oldTypeEmployeIdOfBaremePrestationsListBaremePrestations != null) {
                    oldTypeEmployeIdOfBaremePrestationsListBaremePrestations.getBaremePrestationsList().remove(baremePrestationsListBaremePrestations);
                    oldTypeEmployeIdOfBaremePrestationsListBaremePrestations = em.merge(oldTypeEmployeIdOfBaremePrestationsListBaremePrestations);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TypeEmploye typeEmploye) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TypeEmploye persistentTypeEmploye = em.find(TypeEmploye.class, typeEmploye.getId());
            List<BaremePrestations> baremePrestationsListOld = persistentTypeEmploye.getBaremePrestationsList();
            List<BaremePrestations> baremePrestationsListNew = typeEmploye.getBaremePrestationsList();
            List<BaremePrestations> attachedBaremePrestationsListNew = new ArrayList<BaremePrestations>();
            for (BaremePrestations baremePrestationsListNewBaremePrestationsToAttach : baremePrestationsListNew) {
                baremePrestationsListNewBaremePrestationsToAttach = em.getReference(baremePrestationsListNewBaremePrestationsToAttach.getClass(), baremePrestationsListNewBaremePrestationsToAttach.getId());
                attachedBaremePrestationsListNew.add(baremePrestationsListNewBaremePrestationsToAttach);
            }
            baremePrestationsListNew = attachedBaremePrestationsListNew;
            typeEmploye.setBaremePrestationsList(baremePrestationsListNew);
            typeEmploye = em.merge(typeEmploye);
            for (BaremePrestations baremePrestationsListOldBaremePrestations : baremePrestationsListOld) {
                if (!baremePrestationsListNew.contains(baremePrestationsListOldBaremePrestations)) {
                    baremePrestationsListOldBaremePrestations.setTypeEmployeId(null);
                    baremePrestationsListOldBaremePrestations = em.merge(baremePrestationsListOldBaremePrestations);
                }
            }
            for (BaremePrestations baremePrestationsListNewBaremePrestations : baremePrestationsListNew) {
                if (!baremePrestationsListOld.contains(baremePrestationsListNewBaremePrestations)) {
                    TypeEmploye oldTypeEmployeIdOfBaremePrestationsListNewBaremePrestations = baremePrestationsListNewBaremePrestations.getTypeEmployeId();
                    baremePrestationsListNewBaremePrestations.setTypeEmployeId(typeEmploye);
                    baremePrestationsListNewBaremePrestations = em.merge(baremePrestationsListNewBaremePrestations);
                    if (oldTypeEmployeIdOfBaremePrestationsListNewBaremePrestations != null && !oldTypeEmployeIdOfBaremePrestationsListNewBaremePrestations.equals(typeEmploye)) {
                        oldTypeEmployeIdOfBaremePrestationsListNewBaremePrestations.getBaremePrestationsList().remove(baremePrestationsListNewBaremePrestations);
                        oldTypeEmployeIdOfBaremePrestationsListNewBaremePrestations = em.merge(oldTypeEmployeIdOfBaremePrestationsListNewBaremePrestations);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = typeEmploye.getId();
                if (findTypeEmploye(id) == null) {
                    throw new NonexistentEntityException("The typeEmploye with id " + id + " no longer exists.");
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
            TypeEmploye typeEmploye;
            try {
                typeEmploye = em.getReference(TypeEmploye.class, id);
                typeEmploye.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The typeEmploye with id " + id + " no longer exists.", enfe);
            }
            List<BaremePrestations> baremePrestationsList = typeEmploye.getBaremePrestationsList();
            for (BaremePrestations baremePrestationsListBaremePrestations : baremePrestationsList) {
                baremePrestationsListBaremePrestations.setTypeEmployeId(null);
                baremePrestationsListBaremePrestations = em.merge(baremePrestationsListBaremePrestations);
            }
            em.remove(typeEmploye);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TypeEmploye> findTypeEmployeEntities() {
        return findTypeEmployeEntities(true, -1, -1);
    }

    public List<TypeEmploye> findTypeEmployeEntities(int maxResults, int firstResult) {
        return findTypeEmployeEntities(false, maxResults, firstResult);
    }

    private List<TypeEmploye> findTypeEmployeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TypeEmploye.class));
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

    public TypeEmploye findTypeEmploye(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TypeEmploye.class, id);
        } finally {
            em.close();
        }
    }

    public int getTypeEmployeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TypeEmploye> rt = cq.from(TypeEmploye.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
