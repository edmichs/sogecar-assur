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
import entities.Assures;
import entities.BonusMalus;
import entities.Exercices;
import entities.Polices;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author EDY TCHOKOUANI
 */
public class BonusMalusJpaController implements Serializable {

    public BonusMalusJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BonusMalus bonusMalus) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Assures assureId = bonusMalus.getAssureId();
            if (assureId != null) {
                assureId = em.getReference(assureId.getClass(), assureId.getId());
                bonusMalus.setAssureId(assureId);
            }
            Exercices exerciceId = bonusMalus.getExerciceId();
            if (exerciceId != null) {
                exerciceId = em.getReference(exerciceId.getClass(), exerciceId.getId());
                bonusMalus.setExerciceId(exerciceId);
            }
            Polices policeId = bonusMalus.getPoliceId();
            if (policeId != null) {
                policeId = em.getReference(policeId.getClass(), policeId.getId());
                bonusMalus.setPoliceId(policeId);
            }
            em.persist(bonusMalus);
            if (assureId != null) {
                assureId.getBonusMalusList().add(bonusMalus);
                assureId = em.merge(assureId);
            }
            if (exerciceId != null) {
                exerciceId.getBonusMalusList().add(bonusMalus);
                exerciceId = em.merge(exerciceId);
            }
            if (policeId != null) {
                policeId.getBonusMalusList().add(bonusMalus);
                policeId = em.merge(policeId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BonusMalus bonusMalus) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BonusMalus persistentBonusMalus = em.find(BonusMalus.class, bonusMalus.getId());
            Assures assureIdOld = persistentBonusMalus.getAssureId();
            Assures assureIdNew = bonusMalus.getAssureId();
            Exercices exerciceIdOld = persistentBonusMalus.getExerciceId();
            Exercices exerciceIdNew = bonusMalus.getExerciceId();
            Polices policeIdOld = persistentBonusMalus.getPoliceId();
            Polices policeIdNew = bonusMalus.getPoliceId();
            if (assureIdNew != null) {
                assureIdNew = em.getReference(assureIdNew.getClass(), assureIdNew.getId());
                bonusMalus.setAssureId(assureIdNew);
            }
            if (exerciceIdNew != null) {
                exerciceIdNew = em.getReference(exerciceIdNew.getClass(), exerciceIdNew.getId());
                bonusMalus.setExerciceId(exerciceIdNew);
            }
            if (policeIdNew != null) {
                policeIdNew = em.getReference(policeIdNew.getClass(), policeIdNew.getId());
                bonusMalus.setPoliceId(policeIdNew);
            }
            bonusMalus = em.merge(bonusMalus);
            if (assureIdOld != null && !assureIdOld.equals(assureIdNew)) {
                assureIdOld.getBonusMalusList().remove(bonusMalus);
                assureIdOld = em.merge(assureIdOld);
            }
            if (assureIdNew != null && !assureIdNew.equals(assureIdOld)) {
                assureIdNew.getBonusMalusList().add(bonusMalus);
                assureIdNew = em.merge(assureIdNew);
            }
            if (exerciceIdOld != null && !exerciceIdOld.equals(exerciceIdNew)) {
                exerciceIdOld.getBonusMalusList().remove(bonusMalus);
                exerciceIdOld = em.merge(exerciceIdOld);
            }
            if (exerciceIdNew != null && !exerciceIdNew.equals(exerciceIdOld)) {
                exerciceIdNew.getBonusMalusList().add(bonusMalus);
                exerciceIdNew = em.merge(exerciceIdNew);
            }
            if (policeIdOld != null && !policeIdOld.equals(policeIdNew)) {
                policeIdOld.getBonusMalusList().remove(bonusMalus);
                policeIdOld = em.merge(policeIdOld);
            }
            if (policeIdNew != null && !policeIdNew.equals(policeIdOld)) {
                policeIdNew.getBonusMalusList().add(bonusMalus);
                policeIdNew = em.merge(policeIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bonusMalus.getId();
                if (findBonusMalus(id) == null) {
                    throw new NonexistentEntityException("The bonusMalus with id " + id + " no longer exists.");
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
            BonusMalus bonusMalus;
            try {
                bonusMalus = em.getReference(BonusMalus.class, id);
                bonusMalus.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bonusMalus with id " + id + " no longer exists.", enfe);
            }
            Assures assureId = bonusMalus.getAssureId();
            if (assureId != null) {
                assureId.getBonusMalusList().remove(bonusMalus);
                assureId = em.merge(assureId);
            }
            Exercices exerciceId = bonusMalus.getExerciceId();
            if (exerciceId != null) {
                exerciceId.getBonusMalusList().remove(bonusMalus);
                exerciceId = em.merge(exerciceId);
            }
            Polices policeId = bonusMalus.getPoliceId();
            if (policeId != null) {
                policeId.getBonusMalusList().remove(bonusMalus);
                policeId = em.merge(policeId);
            }
            em.remove(bonusMalus);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BonusMalus> findBonusMalusEntities() {
        return findBonusMalusEntities(true, -1, -1);
    }

    public List<BonusMalus> findBonusMalusEntities(int maxResults, int firstResult) {
        return findBonusMalusEntities(false, maxResults, firstResult);
    }

    private List<BonusMalus> findBonusMalusEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BonusMalus.class));
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

    public BonusMalus findBonusMalus(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BonusMalus.class, id);
        } finally {
            em.close();
        }
    }

    public int getBonusMalusCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BonusMalus> rt = cq.from(BonusMalus.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
