/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import entities.Souscripteurs;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Succursales;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author EDY TCHOKOUANI
 */
public class SouscripteursJpaController1 implements Serializable {

    public SouscripteursJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Souscripteurs souscripteurs) {
        if (souscripteurs.getSuccursalesList() == null) {
            souscripteurs.setSuccursalesList(new ArrayList<Succursales>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Succursales> attachedSuccursalesList = new ArrayList<Succursales>();
            for (Succursales succursalesListSuccursalesToAttach : souscripteurs.getSuccursalesList()) {
                succursalesListSuccursalesToAttach = em.getReference(succursalesListSuccursalesToAttach.getClass(), succursalesListSuccursalesToAttach.getId());
                attachedSuccursalesList.add(succursalesListSuccursalesToAttach);
            }
            souscripteurs.setSuccursalesList(attachedSuccursalesList);
            em.persist(souscripteurs);
            for (Succursales succursalesListSuccursales : souscripteurs.getSuccursalesList()) {
                Souscripteurs oldSouscripteurIdOfSuccursalesListSuccursales = succursalesListSuccursales.getSouscripteurId();
                succursalesListSuccursales.setSouscripteurId(souscripteurs);
                succursalesListSuccursales = em.merge(succursalesListSuccursales);
                if (oldSouscripteurIdOfSuccursalesListSuccursales != null) {
                    oldSouscripteurIdOfSuccursalesListSuccursales.getSuccursalesList().remove(succursalesListSuccursales);
                    oldSouscripteurIdOfSuccursalesListSuccursales = em.merge(oldSouscripteurIdOfSuccursalesListSuccursales);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Souscripteurs souscripteurs) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Souscripteurs persistentSouscripteurs = em.find(Souscripteurs.class, souscripteurs.getId());
            List<Succursales> succursalesListOld = persistentSouscripteurs.getSuccursalesList();
            List<Succursales> succursalesListNew = souscripteurs.getSuccursalesList();
            List<String> illegalOrphanMessages = null;
            for (Succursales succursalesListOldSuccursales : succursalesListOld) {
                if (!succursalesListNew.contains(succursalesListOldSuccursales)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Succursales " + succursalesListOldSuccursales + " since its souscripteurId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Succursales> attachedSuccursalesListNew = new ArrayList<Succursales>();
            for (Succursales succursalesListNewSuccursalesToAttach : succursalesListNew) {
                succursalesListNewSuccursalesToAttach = em.getReference(succursalesListNewSuccursalesToAttach.getClass(), succursalesListNewSuccursalesToAttach.getId());
                attachedSuccursalesListNew.add(succursalesListNewSuccursalesToAttach);
            }
            succursalesListNew = attachedSuccursalesListNew;
            souscripteurs.setSuccursalesList(succursalesListNew);
            souscripteurs = em.merge(souscripteurs);
            for (Succursales succursalesListNewSuccursales : succursalesListNew) {
                if (!succursalesListOld.contains(succursalesListNewSuccursales)) {
                    Souscripteurs oldSouscripteurIdOfSuccursalesListNewSuccursales = succursalesListNewSuccursales.getSouscripteurId();
                    succursalesListNewSuccursales.setSouscripteurId(souscripteurs);
                    succursalesListNewSuccursales = em.merge(succursalesListNewSuccursales);
                    if (oldSouscripteurIdOfSuccursalesListNewSuccursales != null && !oldSouscripteurIdOfSuccursalesListNewSuccursales.equals(souscripteurs)) {
                        oldSouscripteurIdOfSuccursalesListNewSuccursales.getSuccursalesList().remove(succursalesListNewSuccursales);
                        oldSouscripteurIdOfSuccursalesListNewSuccursales = em.merge(oldSouscripteurIdOfSuccursalesListNewSuccursales);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = souscripteurs.getId();
                if (findSouscripteurs(id) == null) {
                    throw new NonexistentEntityException("The souscripteurs with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Souscripteurs souscripteurs;
            try {
                souscripteurs = em.getReference(Souscripteurs.class, id);
                souscripteurs.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The souscripteurs with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Succursales> succursalesListOrphanCheck = souscripteurs.getSuccursalesList();
            for (Succursales succursalesListOrphanCheckSuccursales : succursalesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Souscripteurs (" + souscripteurs + ") cannot be destroyed since the Succursales " + succursalesListOrphanCheckSuccursales + " in its succursalesList field has a non-nullable souscripteurId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(souscripteurs);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Souscripteurs> findSouscripteursEntities() {
        return findSouscripteursEntities(true, -1, -1);
    }

    public List<Souscripteurs> findSouscripteursEntities(int maxResults, int firstResult) {
        return findSouscripteursEntities(false, maxResults, firstResult);
    }

    private List<Souscripteurs> findSouscripteursEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Souscripteurs.class));
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

    public Souscripteurs findSouscripteurs(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Souscripteurs.class, id);
        } finally {
            em.close();
        }
    }

    public int getSouscripteursCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Souscripteurs> rt = cq.from(Souscripteurs.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
