/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import entities.BaremePrestations;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.CategoriePrestations;
import entities.Prestataires;
import entities.TypeEmploye;
import entities.Zonegeos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author EDY TCHOKOUANI
 */
public class BaremePrestationsJpaController implements Serializable {

    public BaremePrestationsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BaremePrestations baremePrestations) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriePrestations categoriePrestationId = baremePrestations.getCategoriePrestationId();
            if (categoriePrestationId != null) {
                categoriePrestationId = em.getReference(categoriePrestationId.getClass(), categoriePrestationId.getId());
                baremePrestations.setCategoriePrestationId(categoriePrestationId);
            }
            Prestataires prestataireId = baremePrestations.getPrestataireId();
            if (prestataireId != null) {
                prestataireId = em.getReference(prestataireId.getClass(), prestataireId.getId());
                baremePrestations.setPrestataireId(prestataireId);
            }
            TypeEmploye typeEmployeId = baremePrestations.getTypeEmployeId();
            if (typeEmployeId != null) {
                typeEmployeId = em.getReference(typeEmployeId.getClass(), typeEmployeId.getId());
                baremePrestations.setTypeEmployeId(typeEmployeId);
            }
            Zonegeos zonegeoId = baremePrestations.getZonegeoId();
            if (zonegeoId != null) {
                zonegeoId = em.getReference(zonegeoId.getClass(), zonegeoId.getId());
                baremePrestations.setZonegeoId(zonegeoId);
            }
            em.persist(baremePrestations);
            if (categoriePrestationId != null) {
                categoriePrestationId.getBaremePrestationsList().add(baremePrestations);
                categoriePrestationId = em.merge(categoriePrestationId);
            }
            if (prestataireId != null) {
                prestataireId.getBaremePrestationsList().add(baremePrestations);
                prestataireId = em.merge(prestataireId);
            }
            if (typeEmployeId != null) {
                typeEmployeId.getBaremePrestationsList().add(baremePrestations);
                typeEmployeId = em.merge(typeEmployeId);
            }
            if (zonegeoId != null) {
                zonegeoId.getBaremePrestationsList().add(baremePrestations);
                zonegeoId = em.merge(zonegeoId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BaremePrestations baremePrestations) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BaremePrestations persistentBaremePrestations = em.find(BaremePrestations.class, baremePrestations.getId());
            CategoriePrestations categoriePrestationIdOld = persistentBaremePrestations.getCategoriePrestationId();
            CategoriePrestations categoriePrestationIdNew = baremePrestations.getCategoriePrestationId();
            Prestataires prestataireIdOld = persistentBaremePrestations.getPrestataireId();
            Prestataires prestataireIdNew = baremePrestations.getPrestataireId();
            TypeEmploye typeEmployeIdOld = persistentBaremePrestations.getTypeEmployeId();
            TypeEmploye typeEmployeIdNew = baremePrestations.getTypeEmployeId();
            Zonegeos zonegeoIdOld = persistentBaremePrestations.getZonegeoId();
            Zonegeos zonegeoIdNew = baremePrestations.getZonegeoId();
            if (categoriePrestationIdNew != null) {
                categoriePrestationIdNew = em.getReference(categoriePrestationIdNew.getClass(), categoriePrestationIdNew.getId());
                baremePrestations.setCategoriePrestationId(categoriePrestationIdNew);
            }
            if (prestataireIdNew != null) {
                prestataireIdNew = em.getReference(prestataireIdNew.getClass(), prestataireIdNew.getId());
                baremePrestations.setPrestataireId(prestataireIdNew);
            }
            if (typeEmployeIdNew != null) {
                typeEmployeIdNew = em.getReference(typeEmployeIdNew.getClass(), typeEmployeIdNew.getId());
                baremePrestations.setTypeEmployeId(typeEmployeIdNew);
            }
            if (zonegeoIdNew != null) {
                zonegeoIdNew = em.getReference(zonegeoIdNew.getClass(), zonegeoIdNew.getId());
                baremePrestations.setZonegeoId(zonegeoIdNew);
            }
            baremePrestations = em.merge(baremePrestations);
            if (categoriePrestationIdOld != null && !categoriePrestationIdOld.equals(categoriePrestationIdNew)) {
                categoriePrestationIdOld.getBaremePrestationsList().remove(baremePrestations);
                categoriePrestationIdOld = em.merge(categoriePrestationIdOld);
            }
            if (categoriePrestationIdNew != null && !categoriePrestationIdNew.equals(categoriePrestationIdOld)) {
                categoriePrestationIdNew.getBaremePrestationsList().add(baremePrestations);
                categoriePrestationIdNew = em.merge(categoriePrestationIdNew);
            }
            if (prestataireIdOld != null && !prestataireIdOld.equals(prestataireIdNew)) {
                prestataireIdOld.getBaremePrestationsList().remove(baremePrestations);
                prestataireIdOld = em.merge(prestataireIdOld);
            }
            if (prestataireIdNew != null && !prestataireIdNew.equals(prestataireIdOld)) {
                prestataireIdNew.getBaremePrestationsList().add(baremePrestations);
                prestataireIdNew = em.merge(prestataireIdNew);
            }
            if (typeEmployeIdOld != null && !typeEmployeIdOld.equals(typeEmployeIdNew)) {
                typeEmployeIdOld.getBaremePrestationsList().remove(baremePrestations);
                typeEmployeIdOld = em.merge(typeEmployeIdOld);
            }
            if (typeEmployeIdNew != null && !typeEmployeIdNew.equals(typeEmployeIdOld)) {
                typeEmployeIdNew.getBaremePrestationsList().add(baremePrestations);
                typeEmployeIdNew = em.merge(typeEmployeIdNew);
            }
            if (zonegeoIdOld != null && !zonegeoIdOld.equals(zonegeoIdNew)) {
                zonegeoIdOld.getBaremePrestationsList().remove(baremePrestations);
                zonegeoIdOld = em.merge(zonegeoIdOld);
            }
            if (zonegeoIdNew != null && !zonegeoIdNew.equals(zonegeoIdOld)) {
                zonegeoIdNew.getBaremePrestationsList().add(baremePrestations);
                zonegeoIdNew = em.merge(zonegeoIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = baremePrestations.getId();
                if (findBaremePrestations(id) == null) {
                    throw new NonexistentEntityException("The baremePrestations with id " + id + " no longer exists.");
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
            BaremePrestations baremePrestations;
            try {
                baremePrestations = em.getReference(BaremePrestations.class, id);
                baremePrestations.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The baremePrestations with id " + id + " no longer exists.", enfe);
            }
            CategoriePrestations categoriePrestationId = baremePrestations.getCategoriePrestationId();
            if (categoriePrestationId != null) {
                categoriePrestationId.getBaremePrestationsList().remove(baremePrestations);
                categoriePrestationId = em.merge(categoriePrestationId);
            }
            Prestataires prestataireId = baremePrestations.getPrestataireId();
            if (prestataireId != null) {
                prestataireId.getBaremePrestationsList().remove(baremePrestations);
                prestataireId = em.merge(prestataireId);
            }
            TypeEmploye typeEmployeId = baremePrestations.getTypeEmployeId();
            if (typeEmployeId != null) {
                typeEmployeId.getBaremePrestationsList().remove(baremePrestations);
                typeEmployeId = em.merge(typeEmployeId);
            }
            Zonegeos zonegeoId = baremePrestations.getZonegeoId();
            if (zonegeoId != null) {
                zonegeoId.getBaremePrestationsList().remove(baremePrestations);
                zonegeoId = em.merge(zonegeoId);
            }
            em.remove(baremePrestations);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BaremePrestations> findBaremePrestationsEntities() {
        return findBaremePrestationsEntities(true, -1, -1);
    }

    public List<BaremePrestations> findBaremePrestationsEntities(int maxResults, int firstResult) {
        return findBaremePrestationsEntities(false, maxResults, firstResult);
    }

    private List<BaremePrestations> findBaremePrestationsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BaremePrestations.class));
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

    public BaremePrestations findBaremePrestations(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BaremePrestations.class, id);
        } finally {
            em.close();
        }
    }

    public int getBaremePrestationsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BaremePrestations> rt = cq.from(BaremePrestations.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
