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
import entities.Decomptes;
import java.util.ArrayList;
import java.util.List;
import entities.BaremePrestations;
import entities.Zonegeos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author EDY TCHOKOUANI
 */
public class ZonegeosJpaController implements Serializable {

    public ZonegeosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Zonegeos zonegeos) {
        if (zonegeos.getDecomptesList() == null) {
            zonegeos.setDecomptesList(new ArrayList<Decomptes>());
        }
        if (zonegeos.getBaremePrestationsList() == null) {
            zonegeos.setBaremePrestationsList(new ArrayList<BaremePrestations>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Decomptes> attachedDecomptesList = new ArrayList<Decomptes>();
            for (Decomptes decomptesListDecomptesToAttach : zonegeos.getDecomptesList()) {
                decomptesListDecomptesToAttach = em.getReference(decomptesListDecomptesToAttach.getClass(), decomptesListDecomptesToAttach.getId());
                attachedDecomptesList.add(decomptesListDecomptesToAttach);
            }
            zonegeos.setDecomptesList(attachedDecomptesList);
            List<BaremePrestations> attachedBaremePrestationsList = new ArrayList<BaremePrestations>();
            for (BaremePrestations baremePrestationsListBaremePrestationsToAttach : zonegeos.getBaremePrestationsList()) {
                baremePrestationsListBaremePrestationsToAttach = em.getReference(baremePrestationsListBaremePrestationsToAttach.getClass(), baremePrestationsListBaremePrestationsToAttach.getId());
                attachedBaremePrestationsList.add(baremePrestationsListBaremePrestationsToAttach);
            }
            zonegeos.setBaremePrestationsList(attachedBaremePrestationsList);
            em.persist(zonegeos);
            for (Decomptes decomptesListDecomptes : zonegeos.getDecomptesList()) {
                Zonegeos oldZonegeoIdOfDecomptesListDecomptes = decomptesListDecomptes.getZonegeoId();
                decomptesListDecomptes.setZonegeoId(zonegeos);
                decomptesListDecomptes = em.merge(decomptesListDecomptes);
                if (oldZonegeoIdOfDecomptesListDecomptes != null) {
                    oldZonegeoIdOfDecomptesListDecomptes.getDecomptesList().remove(decomptesListDecomptes);
                    oldZonegeoIdOfDecomptesListDecomptes = em.merge(oldZonegeoIdOfDecomptesListDecomptes);
                }
            }
            for (BaremePrestations baremePrestationsListBaremePrestations : zonegeos.getBaremePrestationsList()) {
                Zonegeos oldZonegeoIdOfBaremePrestationsListBaremePrestations = baremePrestationsListBaremePrestations.getZonegeoId();
                baremePrestationsListBaremePrestations.setZonegeoId(zonegeos);
                baremePrestationsListBaremePrestations = em.merge(baremePrestationsListBaremePrestations);
                if (oldZonegeoIdOfBaremePrestationsListBaremePrestations != null) {
                    oldZonegeoIdOfBaremePrestationsListBaremePrestations.getBaremePrestationsList().remove(baremePrestationsListBaremePrestations);
                    oldZonegeoIdOfBaremePrestationsListBaremePrestations = em.merge(oldZonegeoIdOfBaremePrestationsListBaremePrestations);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Zonegeos zonegeos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Zonegeos persistentZonegeos = em.find(Zonegeos.class, zonegeos.getId());
            List<Decomptes> decomptesListOld = persistentZonegeos.getDecomptesList();
            List<Decomptes> decomptesListNew = zonegeos.getDecomptesList();
            List<BaremePrestations> baremePrestationsListOld = persistentZonegeos.getBaremePrestationsList();
            List<BaremePrestations> baremePrestationsListNew = zonegeos.getBaremePrestationsList();
            List<Decomptes> attachedDecomptesListNew = new ArrayList<Decomptes>();
            for (Decomptes decomptesListNewDecomptesToAttach : decomptesListNew) {
                decomptesListNewDecomptesToAttach = em.getReference(decomptesListNewDecomptesToAttach.getClass(), decomptesListNewDecomptesToAttach.getId());
                attachedDecomptesListNew.add(decomptesListNewDecomptesToAttach);
            }
            decomptesListNew = attachedDecomptesListNew;
            zonegeos.setDecomptesList(decomptesListNew);
            List<BaremePrestations> attachedBaremePrestationsListNew = new ArrayList<BaremePrestations>();
            for (BaremePrestations baremePrestationsListNewBaremePrestationsToAttach : baremePrestationsListNew) {
                baremePrestationsListNewBaremePrestationsToAttach = em.getReference(baremePrestationsListNewBaremePrestationsToAttach.getClass(), baremePrestationsListNewBaremePrestationsToAttach.getId());
                attachedBaremePrestationsListNew.add(baremePrestationsListNewBaremePrestationsToAttach);
            }
            baremePrestationsListNew = attachedBaremePrestationsListNew;
            zonegeos.setBaremePrestationsList(baremePrestationsListNew);
            zonegeos = em.merge(zonegeos);
            for (Decomptes decomptesListOldDecomptes : decomptesListOld) {
                if (!decomptesListNew.contains(decomptesListOldDecomptes)) {
                    decomptesListOldDecomptes.setZonegeoId(null);
                    decomptesListOldDecomptes = em.merge(decomptesListOldDecomptes);
                }
            }
            for (Decomptes decomptesListNewDecomptes : decomptesListNew) {
                if (!decomptesListOld.contains(decomptesListNewDecomptes)) {
                    Zonegeos oldZonegeoIdOfDecomptesListNewDecomptes = decomptesListNewDecomptes.getZonegeoId();
                    decomptesListNewDecomptes.setZonegeoId(zonegeos);
                    decomptesListNewDecomptes = em.merge(decomptesListNewDecomptes);
                    if (oldZonegeoIdOfDecomptesListNewDecomptes != null && !oldZonegeoIdOfDecomptesListNewDecomptes.equals(zonegeos)) {
                        oldZonegeoIdOfDecomptesListNewDecomptes.getDecomptesList().remove(decomptesListNewDecomptes);
                        oldZonegeoIdOfDecomptesListNewDecomptes = em.merge(oldZonegeoIdOfDecomptesListNewDecomptes);
                    }
                }
            }
            for (BaremePrestations baremePrestationsListOldBaremePrestations : baremePrestationsListOld) {
                if (!baremePrestationsListNew.contains(baremePrestationsListOldBaremePrestations)) {
                    baremePrestationsListOldBaremePrestations.setZonegeoId(null);
                    baremePrestationsListOldBaremePrestations = em.merge(baremePrestationsListOldBaremePrestations);
                }
            }
            for (BaremePrestations baremePrestationsListNewBaremePrestations : baremePrestationsListNew) {
                if (!baremePrestationsListOld.contains(baremePrestationsListNewBaremePrestations)) {
                    Zonegeos oldZonegeoIdOfBaremePrestationsListNewBaremePrestations = baremePrestationsListNewBaremePrestations.getZonegeoId();
                    baremePrestationsListNewBaremePrestations.setZonegeoId(zonegeos);
                    baremePrestationsListNewBaremePrestations = em.merge(baremePrestationsListNewBaremePrestations);
                    if (oldZonegeoIdOfBaremePrestationsListNewBaremePrestations != null && !oldZonegeoIdOfBaremePrestationsListNewBaremePrestations.equals(zonegeos)) {
                        oldZonegeoIdOfBaremePrestationsListNewBaremePrestations.getBaremePrestationsList().remove(baremePrestationsListNewBaremePrestations);
                        oldZonegeoIdOfBaremePrestationsListNewBaremePrestations = em.merge(oldZonegeoIdOfBaremePrestationsListNewBaremePrestations);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = zonegeos.getId();
                if (findZonegeos(id) == null) {
                    throw new NonexistentEntityException("The zonegeos with id " + id + " no longer exists.");
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
            Zonegeos zonegeos;
            try {
                zonegeos = em.getReference(Zonegeos.class, id);
                zonegeos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The zonegeos with id " + id + " no longer exists.", enfe);
            }
            List<Decomptes> decomptesList = zonegeos.getDecomptesList();
            for (Decomptes decomptesListDecomptes : decomptesList) {
                decomptesListDecomptes.setZonegeoId(null);
                decomptesListDecomptes = em.merge(decomptesListDecomptes);
            }
            List<BaremePrestations> baremePrestationsList = zonegeos.getBaremePrestationsList();
            for (BaremePrestations baremePrestationsListBaremePrestations : baremePrestationsList) {
                baremePrestationsListBaremePrestations.setZonegeoId(null);
                baremePrestationsListBaremePrestations = em.merge(baremePrestationsListBaremePrestations);
            }
            em.remove(zonegeos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Zonegeos> findZonegeosEntities() {
        return findZonegeosEntities(true, -1, -1);
    }

    public List<Zonegeos> findZonegeosEntities(int maxResults, int firstResult) {
        return findZonegeosEntities(false, maxResults, firstResult);
    }

    private List<Zonegeos> findZonegeosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Zonegeos.class));
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

    public Zonegeos findZonegeos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Zonegeos.class, id);
        } finally {
            em.close();
        }
    }

    public int getZonegeosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Zonegeos> rt = cq.from(Zonegeos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
