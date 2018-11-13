/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import entities.Etablissements;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Polices;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author EDY TCHOKOUANI
 */
public class EtablissementsJpaController implements Serializable {

    public EtablissementsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Etablissements etablissements) {
        if (etablissements.getPolicesList() == null) {
            etablissements.setPolicesList(new ArrayList<Polices>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Polices> attachedPolicesList = new ArrayList<Polices>();
            for (Polices policesListPolicesToAttach : etablissements.getPolicesList()) {
                policesListPolicesToAttach = em.getReference(policesListPolicesToAttach.getClass(), policesListPolicesToAttach.getId());
                attachedPolicesList.add(policesListPolicesToAttach);
            }
            etablissements.setPolicesList(attachedPolicesList);
            em.persist(etablissements);
            for (Polices policesListPolices : etablissements.getPolicesList()) {
                policesListPolices.getEtablissementsList().add(etablissements);
                policesListPolices = em.merge(policesListPolices);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Etablissements etablissements) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Etablissements persistentEtablissements = em.find(Etablissements.class, etablissements.getId());
            List<Polices> policesListOld = persistentEtablissements.getPolicesList();
            List<Polices> policesListNew = etablissements.getPolicesList();
            List<Polices> attachedPolicesListNew = new ArrayList<Polices>();
            for (Polices policesListNewPolicesToAttach : policesListNew) {
                policesListNewPolicesToAttach = em.getReference(policesListNewPolicesToAttach.getClass(), policesListNewPolicesToAttach.getId());
                attachedPolicesListNew.add(policesListNewPolicesToAttach);
            }
            policesListNew = attachedPolicesListNew;
            etablissements.setPolicesList(policesListNew);
            etablissements = em.merge(etablissements);
            for (Polices policesListOldPolices : policesListOld) {
                if (!policesListNew.contains(policesListOldPolices)) {
                    policesListOldPolices.getEtablissementsList().remove(etablissements);
                    policesListOldPolices = em.merge(policesListOldPolices);
                }
            }
            for (Polices policesListNewPolices : policesListNew) {
                if (!policesListOld.contains(policesListNewPolices)) {
                    policesListNewPolices.getEtablissementsList().add(etablissements);
                    policesListNewPolices = em.merge(policesListNewPolices);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = etablissements.getId();
                if (findEtablissements(id) == null) {
                    throw new NonexistentEntityException("The etablissements with id " + id + " no longer exists.");
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
            Etablissements etablissements;
            try {
                etablissements = em.getReference(Etablissements.class, id);
                etablissements.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The etablissements with id " + id + " no longer exists.", enfe);
            }
            List<Polices> policesList = etablissements.getPolicesList();
            for (Polices policesListPolices : policesList) {
                policesListPolices.getEtablissementsList().remove(etablissements);
                policesListPolices = em.merge(policesListPolices);
            }
            em.remove(etablissements);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Etablissements> findEtablissementsEntities() {
        return findEtablissementsEntities(true, -1, -1);
    }

    public List<Etablissements> findEtablissementsEntities(int maxResults, int firstResult) {
        return findEtablissementsEntities(false, maxResults, firstResult);
    }

    private List<Etablissements> findEtablissementsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Etablissements.class));
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

    public Etablissements findEtablissements(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Etablissements.class, id);
        } finally {
            em.close();
        }
    }

    public int getEtablissementsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Etablissements> rt = cq.from(Etablissements.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
