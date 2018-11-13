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
import entities.Decomptes;
import entities.Exercices;
import entities.Polices;
import entities.Succursales;
import entities.Zonegeos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author EDY TCHOKOUANI
 */
public class DecomptesJpaController implements Serializable {

    public DecomptesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Decomptes decomptes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Assures assureId = decomptes.getAssureId();
            if (assureId != null) {
                assureId = em.getReference(assureId.getClass(), assureId.getId());
                decomptes.setAssureId(assureId);
            }
            Exercices exerciceId = decomptes.getExerciceId();
            if (exerciceId != null) {
                exerciceId = em.getReference(exerciceId.getClass(), exerciceId.getId());
                decomptes.setExerciceId(exerciceId);
            }
            Polices policeId = decomptes.getPoliceId();
            if (policeId != null) {
                policeId = em.getReference(policeId.getClass(), policeId.getId());
                decomptes.setPoliceId(policeId);
            }
            Succursales succursaleId = decomptes.getSuccursaleId();
            if (succursaleId != null) {
                succursaleId = em.getReference(succursaleId.getClass(), succursaleId.getId());
                decomptes.setSuccursaleId(succursaleId);
            }
            Zonegeos zonegeoId = decomptes.getZonegeoId();
            if (zonegeoId != null) {
                zonegeoId = em.getReference(zonegeoId.getClass(), zonegeoId.getId());
                decomptes.setZonegeoId(zonegeoId);
            }
            em.persist(decomptes);
            if (assureId != null) {
                assureId.getDecomptesList().add(decomptes);
                assureId = em.merge(assureId);
            }
            if (exerciceId != null) {
                exerciceId.getDecomptesList().add(decomptes);
                exerciceId = em.merge(exerciceId);
            }
            if (policeId != null) {
                policeId.getDecomptesList().add(decomptes);
                policeId = em.merge(policeId);
            }
            if (succursaleId != null) {
                succursaleId.getDecomptesList().add(decomptes);
                succursaleId = em.merge(succursaleId);
            }
            if (zonegeoId != null) {
                zonegeoId.getDecomptesList().add(decomptes);
                zonegeoId = em.merge(zonegeoId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Decomptes decomptes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Decomptes persistentDecomptes = em.find(Decomptes.class, decomptes.getId());
            Assures assureIdOld = persistentDecomptes.getAssureId();
            Assures assureIdNew = decomptes.getAssureId();
            Exercices exerciceIdOld = persistentDecomptes.getExerciceId();
            Exercices exerciceIdNew = decomptes.getExerciceId();
            Polices policeIdOld = persistentDecomptes.getPoliceId();
            Polices policeIdNew = decomptes.getPoliceId();
            Succursales succursaleIdOld = persistentDecomptes.getSuccursaleId();
            Succursales succursaleIdNew = decomptes.getSuccursaleId();
            Zonegeos zonegeoIdOld = persistentDecomptes.getZonegeoId();
            Zonegeos zonegeoIdNew = decomptes.getZonegeoId();
            if (assureIdNew != null) {
                assureIdNew = em.getReference(assureIdNew.getClass(), assureIdNew.getId());
                decomptes.setAssureId(assureIdNew);
            }
            if (exerciceIdNew != null) {
                exerciceIdNew = em.getReference(exerciceIdNew.getClass(), exerciceIdNew.getId());
                decomptes.setExerciceId(exerciceIdNew);
            }
            if (policeIdNew != null) {
                policeIdNew = em.getReference(policeIdNew.getClass(), policeIdNew.getId());
                decomptes.setPoliceId(policeIdNew);
            }
            if (succursaleIdNew != null) {
                succursaleIdNew = em.getReference(succursaleIdNew.getClass(), succursaleIdNew.getId());
                decomptes.setSuccursaleId(succursaleIdNew);
            }
            if (zonegeoIdNew != null) {
                zonegeoIdNew = em.getReference(zonegeoIdNew.getClass(), zonegeoIdNew.getId());
                decomptes.setZonegeoId(zonegeoIdNew);
            }
            decomptes = em.merge(decomptes);
            if (assureIdOld != null && !assureIdOld.equals(assureIdNew)) {
                assureIdOld.getDecomptesList().remove(decomptes);
                assureIdOld = em.merge(assureIdOld);
            }
            if (assureIdNew != null && !assureIdNew.equals(assureIdOld)) {
                assureIdNew.getDecomptesList().add(decomptes);
                assureIdNew = em.merge(assureIdNew);
            }
            if (exerciceIdOld != null && !exerciceIdOld.equals(exerciceIdNew)) {
                exerciceIdOld.getDecomptesList().remove(decomptes);
                exerciceIdOld = em.merge(exerciceIdOld);
            }
            if (exerciceIdNew != null && !exerciceIdNew.equals(exerciceIdOld)) {
                exerciceIdNew.getDecomptesList().add(decomptes);
                exerciceIdNew = em.merge(exerciceIdNew);
            }
            if (policeIdOld != null && !policeIdOld.equals(policeIdNew)) {
                policeIdOld.getDecomptesList().remove(decomptes);
                policeIdOld = em.merge(policeIdOld);
            }
            if (policeIdNew != null && !policeIdNew.equals(policeIdOld)) {
                policeIdNew.getDecomptesList().add(decomptes);
                policeIdNew = em.merge(policeIdNew);
            }
            if (succursaleIdOld != null && !succursaleIdOld.equals(succursaleIdNew)) {
                succursaleIdOld.getDecomptesList().remove(decomptes);
                succursaleIdOld = em.merge(succursaleIdOld);
            }
            if (succursaleIdNew != null && !succursaleIdNew.equals(succursaleIdOld)) {
                succursaleIdNew.getDecomptesList().add(decomptes);
                succursaleIdNew = em.merge(succursaleIdNew);
            }
            if (zonegeoIdOld != null && !zonegeoIdOld.equals(zonegeoIdNew)) {
                zonegeoIdOld.getDecomptesList().remove(decomptes);
                zonegeoIdOld = em.merge(zonegeoIdOld);
            }
            if (zonegeoIdNew != null && !zonegeoIdNew.equals(zonegeoIdOld)) {
                zonegeoIdNew.getDecomptesList().add(decomptes);
                zonegeoIdNew = em.merge(zonegeoIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = decomptes.getId();
                if (findDecomptes(id) == null) {
                    throw new NonexistentEntityException("The decomptes with id " + id + " no longer exists.");
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
            Decomptes decomptes;
            try {
                decomptes = em.getReference(Decomptes.class, id);
                decomptes.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The decomptes with id " + id + " no longer exists.", enfe);
            }
            Assures assureId = decomptes.getAssureId();
            if (assureId != null) {
                assureId.getDecomptesList().remove(decomptes);
                assureId = em.merge(assureId);
            }
            Exercices exerciceId = decomptes.getExerciceId();
            if (exerciceId != null) {
                exerciceId.getDecomptesList().remove(decomptes);
                exerciceId = em.merge(exerciceId);
            }
            Polices policeId = decomptes.getPoliceId();
            if (policeId != null) {
                policeId.getDecomptesList().remove(decomptes);
                policeId = em.merge(policeId);
            }
            Succursales succursaleId = decomptes.getSuccursaleId();
            if (succursaleId != null) {
                succursaleId.getDecomptesList().remove(decomptes);
                succursaleId = em.merge(succursaleId);
            }
            Zonegeos zonegeoId = decomptes.getZonegeoId();
            if (zonegeoId != null) {
                zonegeoId.getDecomptesList().remove(decomptes);
                zonegeoId = em.merge(zonegeoId);
            }
            em.remove(decomptes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Decomptes> findDecomptesEntities() {
        return findDecomptesEntities(true, -1, -1);
    }

    public List<Decomptes> findDecomptesEntities(int maxResults, int firstResult) {
        return findDecomptesEntities(false, maxResults, firstResult);
    }

    private List<Decomptes> findDecomptesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Decomptes.class));
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

    public Decomptes findDecomptes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Decomptes.class, id);
        } finally {
            em.close();
        }
    }

    public int getDecomptesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Decomptes> rt = cq.from(Decomptes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
