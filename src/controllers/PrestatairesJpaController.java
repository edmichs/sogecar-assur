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
import entities.Prestataires;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author EDY TCHOKOUANI
 */
public class PrestatairesJpaController implements Serializable {

    public PrestatairesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Prestataires prestataires) {
        if (prestataires.getBaremePrestationsList() == null) {
            prestataires.setBaremePrestationsList(new ArrayList<BaremePrestations>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<BaremePrestations> attachedBaremePrestationsList = new ArrayList<BaremePrestations>();
            for (BaremePrestations baremePrestationsListBaremePrestationsToAttach : prestataires.getBaremePrestationsList()) {
                baremePrestationsListBaremePrestationsToAttach = em.getReference(baremePrestationsListBaremePrestationsToAttach.getClass(), baremePrestationsListBaremePrestationsToAttach.getId());
                attachedBaremePrestationsList.add(baremePrestationsListBaremePrestationsToAttach);
            }
            prestataires.setBaremePrestationsList(attachedBaremePrestationsList);
            em.persist(prestataires);
            for (BaremePrestations baremePrestationsListBaremePrestations : prestataires.getBaremePrestationsList()) {
                Prestataires oldPrestataireIdOfBaremePrestationsListBaremePrestations = baremePrestationsListBaremePrestations.getPrestataireId();
                baremePrestationsListBaremePrestations.setPrestataireId(prestataires);
                baremePrestationsListBaremePrestations = em.merge(baremePrestationsListBaremePrestations);
                if (oldPrestataireIdOfBaremePrestationsListBaremePrestations != null) {
                    oldPrestataireIdOfBaremePrestationsListBaremePrestations.getBaremePrestationsList().remove(baremePrestationsListBaremePrestations);
                    oldPrestataireIdOfBaremePrestationsListBaremePrestations = em.merge(oldPrestataireIdOfBaremePrestationsListBaremePrestations);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Prestataires prestataires) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prestataires persistentPrestataires = em.find(Prestataires.class, prestataires.getId());
            List<BaremePrestations> baremePrestationsListOld = persistentPrestataires.getBaremePrestationsList();
            List<BaremePrestations> baremePrestationsListNew = prestataires.getBaremePrestationsList();
            List<BaremePrestations> attachedBaremePrestationsListNew = new ArrayList<BaremePrestations>();
            for (BaremePrestations baremePrestationsListNewBaremePrestationsToAttach : baremePrestationsListNew) {
                baremePrestationsListNewBaremePrestationsToAttach = em.getReference(baremePrestationsListNewBaremePrestationsToAttach.getClass(), baremePrestationsListNewBaremePrestationsToAttach.getId());
                attachedBaremePrestationsListNew.add(baremePrestationsListNewBaremePrestationsToAttach);
            }
            baremePrestationsListNew = attachedBaremePrestationsListNew;
            prestataires.setBaremePrestationsList(baremePrestationsListNew);
            prestataires = em.merge(prestataires);
            for (BaremePrestations baremePrestationsListOldBaremePrestations : baremePrestationsListOld) {
                if (!baremePrestationsListNew.contains(baremePrestationsListOldBaremePrestations)) {
                    baremePrestationsListOldBaremePrestations.setPrestataireId(null);
                    baremePrestationsListOldBaremePrestations = em.merge(baremePrestationsListOldBaremePrestations);
                }
            }
            for (BaremePrestations baremePrestationsListNewBaremePrestations : baremePrestationsListNew) {
                if (!baremePrestationsListOld.contains(baremePrestationsListNewBaremePrestations)) {
                    Prestataires oldPrestataireIdOfBaremePrestationsListNewBaremePrestations = baremePrestationsListNewBaremePrestations.getPrestataireId();
                    baremePrestationsListNewBaremePrestations.setPrestataireId(prestataires);
                    baremePrestationsListNewBaremePrestations = em.merge(baremePrestationsListNewBaremePrestations);
                    if (oldPrestataireIdOfBaremePrestationsListNewBaremePrestations != null && !oldPrestataireIdOfBaremePrestationsListNewBaremePrestations.equals(prestataires)) {
                        oldPrestataireIdOfBaremePrestationsListNewBaremePrestations.getBaremePrestationsList().remove(baremePrestationsListNewBaremePrestations);
                        oldPrestataireIdOfBaremePrestationsListNewBaremePrestations = em.merge(oldPrestataireIdOfBaremePrestationsListNewBaremePrestations);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = prestataires.getId();
                if (findPrestataires(id) == null) {
                    throw new NonexistentEntityException("The prestataires with id " + id + " no longer exists.");
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
            Prestataires prestataires;
            try {
                prestataires = em.getReference(Prestataires.class, id);
                prestataires.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prestataires with id " + id + " no longer exists.", enfe);
            }
            List<BaremePrestations> baremePrestationsList = prestataires.getBaremePrestationsList();
            for (BaremePrestations baremePrestationsListBaremePrestations : baremePrestationsList) {
                baremePrestationsListBaremePrestations.setPrestataireId(null);
                baremePrestationsListBaremePrestations = em.merge(baremePrestationsListBaremePrestations);
            }
            em.remove(prestataires);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Prestataires> findPrestatairesEntities() {
        return findPrestatairesEntities(true, -1, -1);
    }

    public List<Prestataires> findPrestatairesEntities(int maxResults, int firstResult) {
        return findPrestatairesEntities(false, maxResults, firstResult);
    }

    private List<Prestataires> findPrestatairesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Prestataires.class));
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

    public Prestataires findPrestataires(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Prestataires.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrestatairesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Prestataires> rt = cq.from(Prestataires.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
