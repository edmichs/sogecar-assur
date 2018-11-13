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
import entities.Bpc;
import entities.Exercices;
import entities.Polices;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author EDY TCHOKOUANI
 */
public class BpcJpaController implements Serializable {

    public BpcJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Bpc bpc) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Assures assureId = bpc.getAssureId();
            if (assureId != null) {
                assureId = em.getReference(assureId.getClass(), assureId.getId());
                bpc.setAssureId(assureId);
            }
            Exercices exerciceId = bpc.getExerciceId();
            if (exerciceId != null) {
                exerciceId = em.getReference(exerciceId.getClass(), exerciceId.getId());
                bpc.setExerciceId(exerciceId);
            }
            Polices policeId = bpc.getPoliceId();
            if (policeId != null) {
                policeId = em.getReference(policeId.getClass(), policeId.getId());
                bpc.setPoliceId(policeId);
            }
            em.persist(bpc);
            if (assureId != null) {
                assureId.getBpcList().add(bpc);
                assureId = em.merge(assureId);
            }
            if (exerciceId != null) {
                exerciceId.getBpcList().add(bpc);
                exerciceId = em.merge(exerciceId);
            }
            if (policeId != null) {
                policeId.getBpcList().add(bpc);
                policeId = em.merge(policeId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Bpc bpc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bpc persistentBpc = em.find(Bpc.class, bpc.getId());
            Assures assureIdOld = persistentBpc.getAssureId();
            Assures assureIdNew = bpc.getAssureId();
            Exercices exerciceIdOld = persistentBpc.getExerciceId();
            Exercices exerciceIdNew = bpc.getExerciceId();
            Polices policeIdOld = persistentBpc.getPoliceId();
            Polices policeIdNew = bpc.getPoliceId();
            if (assureIdNew != null) {
                assureIdNew = em.getReference(assureIdNew.getClass(), assureIdNew.getId());
                bpc.setAssureId(assureIdNew);
            }
            if (exerciceIdNew != null) {
                exerciceIdNew = em.getReference(exerciceIdNew.getClass(), exerciceIdNew.getId());
                bpc.setExerciceId(exerciceIdNew);
            }
            if (policeIdNew != null) {
                policeIdNew = em.getReference(policeIdNew.getClass(), policeIdNew.getId());
                bpc.setPoliceId(policeIdNew);
            }
            bpc = em.merge(bpc);
            if (assureIdOld != null && !assureIdOld.equals(assureIdNew)) {
                assureIdOld.getBpcList().remove(bpc);
                assureIdOld = em.merge(assureIdOld);
            }
            if (assureIdNew != null && !assureIdNew.equals(assureIdOld)) {
                assureIdNew.getBpcList().add(bpc);
                assureIdNew = em.merge(assureIdNew);
            }
            if (exerciceIdOld != null && !exerciceIdOld.equals(exerciceIdNew)) {
                exerciceIdOld.getBpcList().remove(bpc);
                exerciceIdOld = em.merge(exerciceIdOld);
            }
            if (exerciceIdNew != null && !exerciceIdNew.equals(exerciceIdOld)) {
                exerciceIdNew.getBpcList().add(bpc);
                exerciceIdNew = em.merge(exerciceIdNew);
            }
            if (policeIdOld != null && !policeIdOld.equals(policeIdNew)) {
                policeIdOld.getBpcList().remove(bpc);
                policeIdOld = em.merge(policeIdOld);
            }
            if (policeIdNew != null && !policeIdNew.equals(policeIdOld)) {
                policeIdNew.getBpcList().add(bpc);
                policeIdNew = em.merge(policeIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bpc.getId();
                if (findBpc(id) == null) {
                    throw new NonexistentEntityException("The bpc with id " + id + " no longer exists.");
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
            Bpc bpc;
            try {
                bpc = em.getReference(Bpc.class, id);
                bpc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bpc with id " + id + " no longer exists.", enfe);
            }
            Assures assureId = bpc.getAssureId();
            if (assureId != null) {
                assureId.getBpcList().remove(bpc);
                assureId = em.merge(assureId);
            }
            Exercices exerciceId = bpc.getExerciceId();
            if (exerciceId != null) {
                exerciceId.getBpcList().remove(bpc);
                exerciceId = em.merge(exerciceId);
            }
            Polices policeId = bpc.getPoliceId();
            if (policeId != null) {
                policeId.getBpcList().remove(bpc);
                policeId = em.merge(policeId);
            }
            em.remove(bpc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Bpc> findBpcEntities() {
        return findBpcEntities(true, -1, -1);
    }

    public List<Bpc> findBpcEntities(int maxResults, int firstResult) {
        return findBpcEntities(false, maxResults, firstResult);
    }

    private List<Bpc> findBpcEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Bpc.class));
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

    public Bpc findBpc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Bpc.class, id);
        } finally {
            em.close();
        }
    }

    public int getBpcCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Bpc> rt = cq.from(Bpc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
