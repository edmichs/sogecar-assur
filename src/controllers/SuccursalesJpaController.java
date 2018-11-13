/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Souscripteurs;
import entities.Assures;
import java.util.ArrayList;
import java.util.List;
import entities.Decomptes;
import entities.Polices;
import entities.Incorporations;
import entities.Succursales;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author EDY TCHOKOUANI
 */
public class SuccursalesJpaController implements Serializable {

    public SuccursalesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Succursales succursales) {
        if (succursales.getAssuresList() == null) {
            succursales.setAssuresList(new ArrayList<Assures>());
        }
        if (succursales.getDecomptesList() == null) {
            succursales.setDecomptesList(new ArrayList<Decomptes>());
        }
        if (succursales.getPolicesList() == null) {
            succursales.setPolicesList(new ArrayList<Polices>());
        }
        if (succursales.getIncorporationsList() == null) {
            succursales.setIncorporationsList(new ArrayList<Incorporations>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Souscripteurs souscripteurId = succursales.getSouscripteurId();
            if (souscripteurId != null) {
                souscripteurId = em.getReference(souscripteurId.getClass(), souscripteurId.getId());
                succursales.setSouscripteurId(souscripteurId);
            }
            List<Assures> attachedAssuresList = new ArrayList<Assures>();
            for (Assures assuresListAssuresToAttach : succursales.getAssuresList()) {
                assuresListAssuresToAttach = em.getReference(assuresListAssuresToAttach.getClass(), assuresListAssuresToAttach.getId());
                attachedAssuresList.add(assuresListAssuresToAttach);
            }
            succursales.setAssuresList(attachedAssuresList);
            List<Decomptes> attachedDecomptesList = new ArrayList<Decomptes>();
            for (Decomptes decomptesListDecomptesToAttach : succursales.getDecomptesList()) {
                decomptesListDecomptesToAttach = em.getReference(decomptesListDecomptesToAttach.getClass(), decomptesListDecomptesToAttach.getId());
                attachedDecomptesList.add(decomptesListDecomptesToAttach);
            }
            succursales.setDecomptesList(attachedDecomptesList);
            List<Polices> attachedPolicesList = new ArrayList<Polices>();
            for (Polices policesListPolicesToAttach : succursales.getPolicesList()) {
                policesListPolicesToAttach = em.getReference(policesListPolicesToAttach.getClass(), policesListPolicesToAttach.getId());
                attachedPolicesList.add(policesListPolicesToAttach);
            }
            succursales.setPolicesList(attachedPolicesList);
            List<Incorporations> attachedIncorporationsList = new ArrayList<Incorporations>();
            for (Incorporations incorporationsListIncorporationsToAttach : succursales.getIncorporationsList()) {
                incorporationsListIncorporationsToAttach = em.getReference(incorporationsListIncorporationsToAttach.getClass(), incorporationsListIncorporationsToAttach.getId());
                attachedIncorporationsList.add(incorporationsListIncorporationsToAttach);
            }
            succursales.setIncorporationsList(attachedIncorporationsList);
            em.persist(succursales);
            if (souscripteurId != null) {
                souscripteurId.getSuccursalesList().add(succursales);
                souscripteurId = em.merge(souscripteurId);
            }
            for (Assures assuresListAssures : succursales.getAssuresList()) {
                Succursales oldSuccursaleIdOfAssuresListAssures = assuresListAssures.getSuccursaleId();
                assuresListAssures.setSuccursaleId(succursales);
                assuresListAssures = em.merge(assuresListAssures);
                if (oldSuccursaleIdOfAssuresListAssures != null) {
                    oldSuccursaleIdOfAssuresListAssures.getAssuresList().remove(assuresListAssures);
                    oldSuccursaleIdOfAssuresListAssures = em.merge(oldSuccursaleIdOfAssuresListAssures);
                }
            }
            for (Decomptes decomptesListDecomptes : succursales.getDecomptesList()) {
                Succursales oldSuccursaleIdOfDecomptesListDecomptes = decomptesListDecomptes.getSuccursaleId();
                decomptesListDecomptes.setSuccursaleId(succursales);
                decomptesListDecomptes = em.merge(decomptesListDecomptes);
                if (oldSuccursaleIdOfDecomptesListDecomptes != null) {
                    oldSuccursaleIdOfDecomptesListDecomptes.getDecomptesList().remove(decomptesListDecomptes);
                    oldSuccursaleIdOfDecomptesListDecomptes = em.merge(oldSuccursaleIdOfDecomptesListDecomptes);
                }
            }
            for (Polices policesListPolices : succursales.getPolicesList()) {
                Succursales oldSuccursaleIdOfPolicesListPolices = policesListPolices.getSuccursaleId();
                policesListPolices.setSuccursaleId(succursales);
                policesListPolices = em.merge(policesListPolices);
                if (oldSuccursaleIdOfPolicesListPolices != null) {
                    oldSuccursaleIdOfPolicesListPolices.getPolicesList().remove(policesListPolices);
                    oldSuccursaleIdOfPolicesListPolices = em.merge(oldSuccursaleIdOfPolicesListPolices);
                }
            }
            for (Incorporations incorporationsListIncorporations : succursales.getIncorporationsList()) {
                Succursales oldSuccursaleIdOfIncorporationsListIncorporations = incorporationsListIncorporations.getSuccursaleId();
                incorporationsListIncorporations.setSuccursaleId(succursales);
                incorporationsListIncorporations = em.merge(incorporationsListIncorporations);
                if (oldSuccursaleIdOfIncorporationsListIncorporations != null) {
                    oldSuccursaleIdOfIncorporationsListIncorporations.getIncorporationsList().remove(incorporationsListIncorporations);
                    oldSuccursaleIdOfIncorporationsListIncorporations = em.merge(oldSuccursaleIdOfIncorporationsListIncorporations);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Succursales succursales) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Succursales persistentSuccursales = em.find(Succursales.class, succursales.getId());
            Souscripteurs souscripteurIdOld = persistentSuccursales.getSouscripteurId();
            Souscripteurs souscripteurIdNew = succursales.getSouscripteurId();
            List<Assures> assuresListOld = persistentSuccursales.getAssuresList();
            List<Assures> assuresListNew = succursales.getAssuresList();
            List<Decomptes> decomptesListOld = persistentSuccursales.getDecomptesList();
            List<Decomptes> decomptesListNew = succursales.getDecomptesList();
            List<Polices> policesListOld = persistentSuccursales.getPolicesList();
            List<Polices> policesListNew = succursales.getPolicesList();
            List<Incorporations> incorporationsListOld = persistentSuccursales.getIncorporationsList();
            List<Incorporations> incorporationsListNew = succursales.getIncorporationsList();
            List<String> illegalOrphanMessages = null;
            for (Assures assuresListOldAssures : assuresListOld) {
                if (!assuresListNew.contains(assuresListOldAssures)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Assures " + assuresListOldAssures + " since its succursaleId field is not nullable.");
                }
            }
            for (Incorporations incorporationsListOldIncorporations : incorporationsListOld) {
                if (!incorporationsListNew.contains(incorporationsListOldIncorporations)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Incorporations " + incorporationsListOldIncorporations + " since its succursaleId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (souscripteurIdNew != null) {
                souscripteurIdNew = em.getReference(souscripteurIdNew.getClass(), souscripteurIdNew.getId());
                succursales.setSouscripteurId(souscripteurIdNew);
            }
            List<Assures> attachedAssuresListNew = new ArrayList<Assures>();
            for (Assures assuresListNewAssuresToAttach : assuresListNew) {
                assuresListNewAssuresToAttach = em.getReference(assuresListNewAssuresToAttach.getClass(), assuresListNewAssuresToAttach.getId());
                attachedAssuresListNew.add(assuresListNewAssuresToAttach);
            }
            assuresListNew = attachedAssuresListNew;
            succursales.setAssuresList(assuresListNew);
            List<Decomptes> attachedDecomptesListNew = new ArrayList<Decomptes>();
            for (Decomptes decomptesListNewDecomptesToAttach : decomptesListNew) {
                decomptesListNewDecomptesToAttach = em.getReference(decomptesListNewDecomptesToAttach.getClass(), decomptesListNewDecomptesToAttach.getId());
                attachedDecomptesListNew.add(decomptesListNewDecomptesToAttach);
            }
            decomptesListNew = attachedDecomptesListNew;
            succursales.setDecomptesList(decomptesListNew);
            List<Polices> attachedPolicesListNew = new ArrayList<Polices>();
            for (Polices policesListNewPolicesToAttach : policesListNew) {
                policesListNewPolicesToAttach = em.getReference(policesListNewPolicesToAttach.getClass(), policesListNewPolicesToAttach.getId());
                attachedPolicesListNew.add(policesListNewPolicesToAttach);
            }
            policesListNew = attachedPolicesListNew;
            succursales.setPolicesList(policesListNew);
            List<Incorporations> attachedIncorporationsListNew = new ArrayList<Incorporations>();
            for (Incorporations incorporationsListNewIncorporationsToAttach : incorporationsListNew) {
                incorporationsListNewIncorporationsToAttach = em.getReference(incorporationsListNewIncorporationsToAttach.getClass(), incorporationsListNewIncorporationsToAttach.getId());
                attachedIncorporationsListNew.add(incorporationsListNewIncorporationsToAttach);
            }
            incorporationsListNew = attachedIncorporationsListNew;
            succursales.setIncorporationsList(incorporationsListNew);
            succursales = em.merge(succursales);
            if (souscripteurIdOld != null && !souscripteurIdOld.equals(souscripteurIdNew)) {
                souscripteurIdOld.getSuccursalesList().remove(succursales);
                souscripteurIdOld = em.merge(souscripteurIdOld);
            }
            if (souscripteurIdNew != null && !souscripteurIdNew.equals(souscripteurIdOld)) {
                souscripteurIdNew.getSuccursalesList().add(succursales);
                souscripteurIdNew = em.merge(souscripteurIdNew);
            }
            for (Assures assuresListNewAssures : assuresListNew) {
                if (!assuresListOld.contains(assuresListNewAssures)) {
                    Succursales oldSuccursaleIdOfAssuresListNewAssures = assuresListNewAssures.getSuccursaleId();
                    assuresListNewAssures.setSuccursaleId(succursales);
                    assuresListNewAssures = em.merge(assuresListNewAssures);
                    if (oldSuccursaleIdOfAssuresListNewAssures != null && !oldSuccursaleIdOfAssuresListNewAssures.equals(succursales)) {
                        oldSuccursaleIdOfAssuresListNewAssures.getAssuresList().remove(assuresListNewAssures);
                        oldSuccursaleIdOfAssuresListNewAssures = em.merge(oldSuccursaleIdOfAssuresListNewAssures);
                    }
                }
            }
            for (Decomptes decomptesListOldDecomptes : decomptesListOld) {
                if (!decomptesListNew.contains(decomptesListOldDecomptes)) {
                    decomptesListOldDecomptes.setSuccursaleId(null);
                    decomptesListOldDecomptes = em.merge(decomptesListOldDecomptes);
                }
            }
            for (Decomptes decomptesListNewDecomptes : decomptesListNew) {
                if (!decomptesListOld.contains(decomptesListNewDecomptes)) {
                    Succursales oldSuccursaleIdOfDecomptesListNewDecomptes = decomptesListNewDecomptes.getSuccursaleId();
                    decomptesListNewDecomptes.setSuccursaleId(succursales);
                    decomptesListNewDecomptes = em.merge(decomptesListNewDecomptes);
                    if (oldSuccursaleIdOfDecomptesListNewDecomptes != null && !oldSuccursaleIdOfDecomptesListNewDecomptes.equals(succursales)) {
                        oldSuccursaleIdOfDecomptesListNewDecomptes.getDecomptesList().remove(decomptesListNewDecomptes);
                        oldSuccursaleIdOfDecomptesListNewDecomptes = em.merge(oldSuccursaleIdOfDecomptesListNewDecomptes);
                    }
                }
            }
            for (Polices policesListOldPolices : policesListOld) {
                if (!policesListNew.contains(policesListOldPolices)) {
                    policesListOldPolices.setSuccursaleId(null);
                    policesListOldPolices = em.merge(policesListOldPolices);
                }
            }
            for (Polices policesListNewPolices : policesListNew) {
                if (!policesListOld.contains(policesListNewPolices)) {
                    Succursales oldSuccursaleIdOfPolicesListNewPolices = policesListNewPolices.getSuccursaleId();
                    policesListNewPolices.setSuccursaleId(succursales);
                    policesListNewPolices = em.merge(policesListNewPolices);
                    if (oldSuccursaleIdOfPolicesListNewPolices != null && !oldSuccursaleIdOfPolicesListNewPolices.equals(succursales)) {
                        oldSuccursaleIdOfPolicesListNewPolices.getPolicesList().remove(policesListNewPolices);
                        oldSuccursaleIdOfPolicesListNewPolices = em.merge(oldSuccursaleIdOfPolicesListNewPolices);
                    }
                }
            }
            for (Incorporations incorporationsListNewIncorporations : incorporationsListNew) {
                if (!incorporationsListOld.contains(incorporationsListNewIncorporations)) {
                    Succursales oldSuccursaleIdOfIncorporationsListNewIncorporations = incorporationsListNewIncorporations.getSuccursaleId();
                    incorporationsListNewIncorporations.setSuccursaleId(succursales);
                    incorporationsListNewIncorporations = em.merge(incorporationsListNewIncorporations);
                    if (oldSuccursaleIdOfIncorporationsListNewIncorporations != null && !oldSuccursaleIdOfIncorporationsListNewIncorporations.equals(succursales)) {
                        oldSuccursaleIdOfIncorporationsListNewIncorporations.getIncorporationsList().remove(incorporationsListNewIncorporations);
                        oldSuccursaleIdOfIncorporationsListNewIncorporations = em.merge(oldSuccursaleIdOfIncorporationsListNewIncorporations);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = succursales.getId();
                if (findSuccursales(id) == null) {
                    throw new NonexistentEntityException("The succursales with id " + id + " no longer exists.");
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
            Succursales succursales;
            try {
                succursales = em.getReference(Succursales.class, id);
                succursales.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The succursales with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Assures> assuresListOrphanCheck = succursales.getAssuresList();
            for (Assures assuresListOrphanCheckAssures : assuresListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Succursales (" + succursales + ") cannot be destroyed since the Assures " + assuresListOrphanCheckAssures + " in its assuresList field has a non-nullable succursaleId field.");
            }
            List<Incorporations> incorporationsListOrphanCheck = succursales.getIncorporationsList();
            for (Incorporations incorporationsListOrphanCheckIncorporations : incorporationsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Succursales (" + succursales + ") cannot be destroyed since the Incorporations " + incorporationsListOrphanCheckIncorporations + " in its incorporationsList field has a non-nullable succursaleId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Souscripteurs souscripteurId = succursales.getSouscripteurId();
            if (souscripteurId != null) {
                souscripteurId.getSuccursalesList().remove(succursales);
                souscripteurId = em.merge(souscripteurId);
            }
            List<Decomptes> decomptesList = succursales.getDecomptesList();
            for (Decomptes decomptesListDecomptes : decomptesList) {
                decomptesListDecomptes.setSuccursaleId(null);
                decomptesListDecomptes = em.merge(decomptesListDecomptes);
            }
            List<Polices> policesList = succursales.getPolicesList();
            for (Polices policesListPolices : policesList) {
                policesListPolices.setSuccursaleId(null);
                policesListPolices = em.merge(policesListPolices);
            }
            em.remove(succursales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Succursales> findSuccursalesEntities() {
        return findSuccursalesEntities(true, -1, -1);
    }

    public List<Succursales> findSuccursalesEntities(int maxResults, int firstResult) {
        return findSuccursalesEntities(false, maxResults, firstResult);
    }

    private List<Succursales> findSuccursalesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Succursales.class));
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

    public Succursales findSuccursales(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Succursales.class, id);
        } finally {
            em.close();
        }
    }

    public int getSuccursalesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Succursales> rt = cq.from(Succursales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
