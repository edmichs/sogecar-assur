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
import entities.Exercices;
import entities.Incorporations;
import entities.Polices;
import entities.Succursales;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author EDY TCHOKOUANI
 */
public class IncorporationsJpaController implements Serializable {

    public IncorporationsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Incorporations incorporations) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Exercices exerciceId = incorporations.getExerciceId();
            if (exerciceId != null) {
                exerciceId = em.getReference(exerciceId.getClass(), exerciceId.getId());
                incorporations.setExerciceId(exerciceId);
            }
            Polices policeId = incorporations.getPoliceId();
            if (policeId != null) {
                policeId = em.getReference(policeId.getClass(), policeId.getId());
                incorporations.setPoliceId(policeId);
            }
            Succursales succursaleId = incorporations.getSuccursaleId();
            if (succursaleId != null) {
                succursaleId = em.getReference(succursaleId.getClass(), succursaleId.getId());
                incorporations.setSuccursaleId(succursaleId);
            }
            em.persist(incorporations);
            if (exerciceId != null) {
                exerciceId.getIncorporationsList().add(incorporations);
                exerciceId = em.merge(exerciceId);
            }
            if (policeId != null) {
                policeId.getIncorporationsList().add(incorporations);
                policeId = em.merge(policeId);
            }
            if (succursaleId != null) {
                succursaleId.getIncorporationsList().add(incorporations);
                succursaleId = em.merge(succursaleId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Incorporations incorporations) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Incorporations persistentIncorporations = em.find(Incorporations.class, incorporations.getId());
            Exercices exerciceIdOld = persistentIncorporations.getExerciceId();
            Exercices exerciceIdNew = incorporations.getExerciceId();
            Polices policeIdOld = persistentIncorporations.getPoliceId();
            Polices policeIdNew = incorporations.getPoliceId();
            Succursales succursaleIdOld = persistentIncorporations.getSuccursaleId();
            Succursales succursaleIdNew = incorporations.getSuccursaleId();
            if (exerciceIdNew != null) {
                exerciceIdNew = em.getReference(exerciceIdNew.getClass(), exerciceIdNew.getId());
                incorporations.setExerciceId(exerciceIdNew);
            }
            if (policeIdNew != null) {
                policeIdNew = em.getReference(policeIdNew.getClass(), policeIdNew.getId());
                incorporations.setPoliceId(policeIdNew);
            }
            if (succursaleIdNew != null) {
                succursaleIdNew = em.getReference(succursaleIdNew.getClass(), succursaleIdNew.getId());
                incorporations.setSuccursaleId(succursaleIdNew);
            }
            incorporations = em.merge(incorporations);
            if (exerciceIdOld != null && !exerciceIdOld.equals(exerciceIdNew)) {
                exerciceIdOld.getIncorporationsList().remove(incorporations);
                exerciceIdOld = em.merge(exerciceIdOld);
            }
            if (exerciceIdNew != null && !exerciceIdNew.equals(exerciceIdOld)) {
                exerciceIdNew.getIncorporationsList().add(incorporations);
                exerciceIdNew = em.merge(exerciceIdNew);
            }
            if (policeIdOld != null && !policeIdOld.equals(policeIdNew)) {
                policeIdOld.getIncorporationsList().remove(incorporations);
                policeIdOld = em.merge(policeIdOld);
            }
            if (policeIdNew != null && !policeIdNew.equals(policeIdOld)) {
                policeIdNew.getIncorporationsList().add(incorporations);
                policeIdNew = em.merge(policeIdNew);
            }
            if (succursaleIdOld != null && !succursaleIdOld.equals(succursaleIdNew)) {
                succursaleIdOld.getIncorporationsList().remove(incorporations);
                succursaleIdOld = em.merge(succursaleIdOld);
            }
            if (succursaleIdNew != null && !succursaleIdNew.equals(succursaleIdOld)) {
                succursaleIdNew.getIncorporationsList().add(incorporations);
                succursaleIdNew = em.merge(succursaleIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = incorporations.getId();
                if (findIncorporations(id) == null) {
                    throw new NonexistentEntityException("The incorporations with id " + id + " no longer exists.");
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
            Incorporations incorporations;
            try {
                incorporations = em.getReference(Incorporations.class, id);
                incorporations.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The incorporations with id " + id + " no longer exists.", enfe);
            }
            Exercices exerciceId = incorporations.getExerciceId();
            if (exerciceId != null) {
                exerciceId.getIncorporationsList().remove(incorporations);
                exerciceId = em.merge(exerciceId);
            }
            Polices policeId = incorporations.getPoliceId();
            if (policeId != null) {
                policeId.getIncorporationsList().remove(incorporations);
                policeId = em.merge(policeId);
            }
            Succursales succursaleId = incorporations.getSuccursaleId();
            if (succursaleId != null) {
                succursaleId.getIncorporationsList().remove(incorporations);
                succursaleId = em.merge(succursaleId);
            }
            em.remove(incorporations);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Incorporations> findIncorporationsEntities() {
        return findIncorporationsEntities(true, -1, -1);
    }

    public List<Incorporations> findIncorporationsEntities(int maxResults, int firstResult) {
        return findIncorporationsEntities(false, maxResults, firstResult);
    }

    private List<Incorporations> findIncorporationsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Incorporations.class));
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

    public Incorporations findIncorporations(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Incorporations.class, id);
        } finally {
            em.close();
        }
    }

    public int getIncorporationsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Incorporations> rt = cq.from(Incorporations.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
