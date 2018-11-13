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
import entities.Assures;
import java.util.ArrayList;
import java.util.List;
import entities.Decomptes;
import entities.Polices;
import entities.Bpc;
import entities.Incorporations;
import entities.BonusMalus;
import entities.Exercices;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author EDY TCHOKOUANI
 */
public class ExercicesJpaController implements Serializable {

    public ExercicesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Exercices exercices) {
        if (exercices.getAssuresList() == null) {
            exercices.setAssuresList(new ArrayList<Assures>());
        }
        if (exercices.getDecomptesList() == null) {
            exercices.setDecomptesList(new ArrayList<Decomptes>());
        }
        if (exercices.getPolicesList() == null) {
            exercices.setPolicesList(new ArrayList<Polices>());
        }
        if (exercices.getBpcList() == null) {
            exercices.setBpcList(new ArrayList<Bpc>());
        }
        if (exercices.getIncorporationsList() == null) {
            exercices.setIncorporationsList(new ArrayList<Incorporations>());
        }
        if (exercices.getBonusMalusList() == null) {
            exercices.setBonusMalusList(new ArrayList<BonusMalus>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Assures> attachedAssuresList = new ArrayList<Assures>();
            for (Assures assuresListAssuresToAttach : exercices.getAssuresList()) {
                assuresListAssuresToAttach = em.getReference(assuresListAssuresToAttach.getClass(), assuresListAssuresToAttach.getId());
                attachedAssuresList.add(assuresListAssuresToAttach);
            }
            exercices.setAssuresList(attachedAssuresList);
            List<Decomptes> attachedDecomptesList = new ArrayList<Decomptes>();
            for (Decomptes decomptesListDecomptesToAttach : exercices.getDecomptesList()) {
                decomptesListDecomptesToAttach = em.getReference(decomptesListDecomptesToAttach.getClass(), decomptesListDecomptesToAttach.getId());
                attachedDecomptesList.add(decomptesListDecomptesToAttach);
            }
            exercices.setDecomptesList(attachedDecomptesList);
            List<Polices> attachedPolicesList = new ArrayList<Polices>();
            for (Polices policesListPolicesToAttach : exercices.getPolicesList()) {
                policesListPolicesToAttach = em.getReference(policesListPolicesToAttach.getClass(), policesListPolicesToAttach.getId());
                attachedPolicesList.add(policesListPolicesToAttach);
            }
            exercices.setPolicesList(attachedPolicesList);
            List<Bpc> attachedBpcList = new ArrayList<Bpc>();
            for (Bpc bpcListBpcToAttach : exercices.getBpcList()) {
                bpcListBpcToAttach = em.getReference(bpcListBpcToAttach.getClass(), bpcListBpcToAttach.getId());
                attachedBpcList.add(bpcListBpcToAttach);
            }
            exercices.setBpcList(attachedBpcList);
            List<Incorporations> attachedIncorporationsList = new ArrayList<Incorporations>();
            for (Incorporations incorporationsListIncorporationsToAttach : exercices.getIncorporationsList()) {
                incorporationsListIncorporationsToAttach = em.getReference(incorporationsListIncorporationsToAttach.getClass(), incorporationsListIncorporationsToAttach.getId());
                attachedIncorporationsList.add(incorporationsListIncorporationsToAttach);
            }
            exercices.setIncorporationsList(attachedIncorporationsList);
            List<BonusMalus> attachedBonusMalusList = new ArrayList<BonusMalus>();
            for (BonusMalus bonusMalusListBonusMalusToAttach : exercices.getBonusMalusList()) {
                bonusMalusListBonusMalusToAttach = em.getReference(bonusMalusListBonusMalusToAttach.getClass(), bonusMalusListBonusMalusToAttach.getId());
                attachedBonusMalusList.add(bonusMalusListBonusMalusToAttach);
            }
            exercices.setBonusMalusList(attachedBonusMalusList);
            em.persist(exercices);
            for (Assures assuresListAssures : exercices.getAssuresList()) {
                Exercices oldExerciceIdOfAssuresListAssures = assuresListAssures.getExerciceId();
                assuresListAssures.setExerciceId(exercices);
                assuresListAssures = em.merge(assuresListAssures);
                if (oldExerciceIdOfAssuresListAssures != null) {
                    oldExerciceIdOfAssuresListAssures.getAssuresList().remove(assuresListAssures);
                    oldExerciceIdOfAssuresListAssures = em.merge(oldExerciceIdOfAssuresListAssures);
                }
            }
            for (Decomptes decomptesListDecomptes : exercices.getDecomptesList()) {
                Exercices oldExerciceIdOfDecomptesListDecomptes = decomptesListDecomptes.getExerciceId();
                decomptesListDecomptes.setExerciceId(exercices);
                decomptesListDecomptes = em.merge(decomptesListDecomptes);
                if (oldExerciceIdOfDecomptesListDecomptes != null) {
                    oldExerciceIdOfDecomptesListDecomptes.getDecomptesList().remove(decomptesListDecomptes);
                    oldExerciceIdOfDecomptesListDecomptes = em.merge(oldExerciceIdOfDecomptesListDecomptes);
                }
            }
            for (Polices policesListPolices : exercices.getPolicesList()) {
                Exercices oldExerciceIdOfPolicesListPolices = policesListPolices.getExerciceId();
                policesListPolices.setExerciceId(exercices);
                policesListPolices = em.merge(policesListPolices);
                if (oldExerciceIdOfPolicesListPolices != null) {
                    oldExerciceIdOfPolicesListPolices.getPolicesList().remove(policesListPolices);
                    oldExerciceIdOfPolicesListPolices = em.merge(oldExerciceIdOfPolicesListPolices);
                }
            }
            for (Bpc bpcListBpc : exercices.getBpcList()) {
                Exercices oldExerciceIdOfBpcListBpc = bpcListBpc.getExerciceId();
                bpcListBpc.setExerciceId(exercices);
                bpcListBpc = em.merge(bpcListBpc);
                if (oldExerciceIdOfBpcListBpc != null) {
                    oldExerciceIdOfBpcListBpc.getBpcList().remove(bpcListBpc);
                    oldExerciceIdOfBpcListBpc = em.merge(oldExerciceIdOfBpcListBpc);
                }
            }
            for (Incorporations incorporationsListIncorporations : exercices.getIncorporationsList()) {
                Exercices oldExerciceIdOfIncorporationsListIncorporations = incorporationsListIncorporations.getExerciceId();
                incorporationsListIncorporations.setExerciceId(exercices);
                incorporationsListIncorporations = em.merge(incorporationsListIncorporations);
                if (oldExerciceIdOfIncorporationsListIncorporations != null) {
                    oldExerciceIdOfIncorporationsListIncorporations.getIncorporationsList().remove(incorporationsListIncorporations);
                    oldExerciceIdOfIncorporationsListIncorporations = em.merge(oldExerciceIdOfIncorporationsListIncorporations);
                }
            }
            for (BonusMalus bonusMalusListBonusMalus : exercices.getBonusMalusList()) {
                Exercices oldExerciceIdOfBonusMalusListBonusMalus = bonusMalusListBonusMalus.getExerciceId();
                bonusMalusListBonusMalus.setExerciceId(exercices);
                bonusMalusListBonusMalus = em.merge(bonusMalusListBonusMalus);
                if (oldExerciceIdOfBonusMalusListBonusMalus != null) {
                    oldExerciceIdOfBonusMalusListBonusMalus.getBonusMalusList().remove(bonusMalusListBonusMalus);
                    oldExerciceIdOfBonusMalusListBonusMalus = em.merge(oldExerciceIdOfBonusMalusListBonusMalus);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Exercices exercices) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Exercices persistentExercices = em.find(Exercices.class, exercices.getId());
            List<Assures> assuresListOld = persistentExercices.getAssuresList();
            List<Assures> assuresListNew = exercices.getAssuresList();
            List<Decomptes> decomptesListOld = persistentExercices.getDecomptesList();
            List<Decomptes> decomptesListNew = exercices.getDecomptesList();
            List<Polices> policesListOld = persistentExercices.getPolicesList();
            List<Polices> policesListNew = exercices.getPolicesList();
            List<Bpc> bpcListOld = persistentExercices.getBpcList();
            List<Bpc> bpcListNew = exercices.getBpcList();
            List<Incorporations> incorporationsListOld = persistentExercices.getIncorporationsList();
            List<Incorporations> incorporationsListNew = exercices.getIncorporationsList();
            List<BonusMalus> bonusMalusListOld = persistentExercices.getBonusMalusList();
            List<BonusMalus> bonusMalusListNew = exercices.getBonusMalusList();
            List<String> illegalOrphanMessages = null;
            for (Assures assuresListOldAssures : assuresListOld) {
                if (!assuresListNew.contains(assuresListOldAssures)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Assures " + assuresListOldAssures + " since its exerciceId field is not nullable.");
                }
            }
            for (Incorporations incorporationsListOldIncorporations : incorporationsListOld) {
                if (!incorporationsListNew.contains(incorporationsListOldIncorporations)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Incorporations " + incorporationsListOldIncorporations + " since its exerciceId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Assures> attachedAssuresListNew = new ArrayList<Assures>();
            for (Assures assuresListNewAssuresToAttach : assuresListNew) {
                assuresListNewAssuresToAttach = em.getReference(assuresListNewAssuresToAttach.getClass(), assuresListNewAssuresToAttach.getId());
                attachedAssuresListNew.add(assuresListNewAssuresToAttach);
            }
            assuresListNew = attachedAssuresListNew;
            exercices.setAssuresList(assuresListNew);
            List<Decomptes> attachedDecomptesListNew = new ArrayList<Decomptes>();
            for (Decomptes decomptesListNewDecomptesToAttach : decomptesListNew) {
                decomptesListNewDecomptesToAttach = em.getReference(decomptesListNewDecomptesToAttach.getClass(), decomptesListNewDecomptesToAttach.getId());
                attachedDecomptesListNew.add(decomptesListNewDecomptesToAttach);
            }
            decomptesListNew = attachedDecomptesListNew;
            exercices.setDecomptesList(decomptesListNew);
            List<Polices> attachedPolicesListNew = new ArrayList<Polices>();
            for (Polices policesListNewPolicesToAttach : policesListNew) {
                policesListNewPolicesToAttach = em.getReference(policesListNewPolicesToAttach.getClass(), policesListNewPolicesToAttach.getId());
                attachedPolicesListNew.add(policesListNewPolicesToAttach);
            }
            policesListNew = attachedPolicesListNew;
            exercices.setPolicesList(policesListNew);
            List<Bpc> attachedBpcListNew = new ArrayList<Bpc>();
            for (Bpc bpcListNewBpcToAttach : bpcListNew) {
                bpcListNewBpcToAttach = em.getReference(bpcListNewBpcToAttach.getClass(), bpcListNewBpcToAttach.getId());
                attachedBpcListNew.add(bpcListNewBpcToAttach);
            }
            bpcListNew = attachedBpcListNew;
            exercices.setBpcList(bpcListNew);
            List<Incorporations> attachedIncorporationsListNew = new ArrayList<Incorporations>();
            for (Incorporations incorporationsListNewIncorporationsToAttach : incorporationsListNew) {
                incorporationsListNewIncorporationsToAttach = em.getReference(incorporationsListNewIncorporationsToAttach.getClass(), incorporationsListNewIncorporationsToAttach.getId());
                attachedIncorporationsListNew.add(incorporationsListNewIncorporationsToAttach);
            }
            incorporationsListNew = attachedIncorporationsListNew;
            exercices.setIncorporationsList(incorporationsListNew);
            List<BonusMalus> attachedBonusMalusListNew = new ArrayList<BonusMalus>();
            for (BonusMalus bonusMalusListNewBonusMalusToAttach : bonusMalusListNew) {
                bonusMalusListNewBonusMalusToAttach = em.getReference(bonusMalusListNewBonusMalusToAttach.getClass(), bonusMalusListNewBonusMalusToAttach.getId());
                attachedBonusMalusListNew.add(bonusMalusListNewBonusMalusToAttach);
            }
            bonusMalusListNew = attachedBonusMalusListNew;
            exercices.setBonusMalusList(bonusMalusListNew);
            exercices = em.merge(exercices);
            for (Assures assuresListNewAssures : assuresListNew) {
                if (!assuresListOld.contains(assuresListNewAssures)) {
                    Exercices oldExerciceIdOfAssuresListNewAssures = assuresListNewAssures.getExerciceId();
                    assuresListNewAssures.setExerciceId(exercices);
                    assuresListNewAssures = em.merge(assuresListNewAssures);
                    if (oldExerciceIdOfAssuresListNewAssures != null && !oldExerciceIdOfAssuresListNewAssures.equals(exercices)) {
                        oldExerciceIdOfAssuresListNewAssures.getAssuresList().remove(assuresListNewAssures);
                        oldExerciceIdOfAssuresListNewAssures = em.merge(oldExerciceIdOfAssuresListNewAssures);
                    }
                }
            }
            for (Decomptes decomptesListOldDecomptes : decomptesListOld) {
                if (!decomptesListNew.contains(decomptesListOldDecomptes)) {
                    decomptesListOldDecomptes.setExerciceId(null);
                    decomptesListOldDecomptes = em.merge(decomptesListOldDecomptes);
                }
            }
            for (Decomptes decomptesListNewDecomptes : decomptesListNew) {
                if (!decomptesListOld.contains(decomptesListNewDecomptes)) {
                    Exercices oldExerciceIdOfDecomptesListNewDecomptes = decomptesListNewDecomptes.getExerciceId();
                    decomptesListNewDecomptes.setExerciceId(exercices);
                    decomptesListNewDecomptes = em.merge(decomptesListNewDecomptes);
                    if (oldExerciceIdOfDecomptesListNewDecomptes != null && !oldExerciceIdOfDecomptesListNewDecomptes.equals(exercices)) {
                        oldExerciceIdOfDecomptesListNewDecomptes.getDecomptesList().remove(decomptesListNewDecomptes);
                        oldExerciceIdOfDecomptesListNewDecomptes = em.merge(oldExerciceIdOfDecomptesListNewDecomptes);
                    }
                }
            }
            for (Polices policesListOldPolices : policesListOld) {
                if (!policesListNew.contains(policesListOldPolices)) {
                    policesListOldPolices.setExerciceId(null);
                    policesListOldPolices = em.merge(policesListOldPolices);
                }
            }
            for (Polices policesListNewPolices : policesListNew) {
                if (!policesListOld.contains(policesListNewPolices)) {
                    Exercices oldExerciceIdOfPolicesListNewPolices = policesListNewPolices.getExerciceId();
                    policesListNewPolices.setExerciceId(exercices);
                    policesListNewPolices = em.merge(policesListNewPolices);
                    if (oldExerciceIdOfPolicesListNewPolices != null && !oldExerciceIdOfPolicesListNewPolices.equals(exercices)) {
                        oldExerciceIdOfPolicesListNewPolices.getPolicesList().remove(policesListNewPolices);
                        oldExerciceIdOfPolicesListNewPolices = em.merge(oldExerciceIdOfPolicesListNewPolices);
                    }
                }
            }
            for (Bpc bpcListOldBpc : bpcListOld) {
                if (!bpcListNew.contains(bpcListOldBpc)) {
                    bpcListOldBpc.setExerciceId(null);
                    bpcListOldBpc = em.merge(bpcListOldBpc);
                }
            }
            for (Bpc bpcListNewBpc : bpcListNew) {
                if (!bpcListOld.contains(bpcListNewBpc)) {
                    Exercices oldExerciceIdOfBpcListNewBpc = bpcListNewBpc.getExerciceId();
                    bpcListNewBpc.setExerciceId(exercices);
                    bpcListNewBpc = em.merge(bpcListNewBpc);
                    if (oldExerciceIdOfBpcListNewBpc != null && !oldExerciceIdOfBpcListNewBpc.equals(exercices)) {
                        oldExerciceIdOfBpcListNewBpc.getBpcList().remove(bpcListNewBpc);
                        oldExerciceIdOfBpcListNewBpc = em.merge(oldExerciceIdOfBpcListNewBpc);
                    }
                }
            }
            for (Incorporations incorporationsListNewIncorporations : incorporationsListNew) {
                if (!incorporationsListOld.contains(incorporationsListNewIncorporations)) {
                    Exercices oldExerciceIdOfIncorporationsListNewIncorporations = incorporationsListNewIncorporations.getExerciceId();
                    incorporationsListNewIncorporations.setExerciceId(exercices);
                    incorporationsListNewIncorporations = em.merge(incorporationsListNewIncorporations);
                    if (oldExerciceIdOfIncorporationsListNewIncorporations != null && !oldExerciceIdOfIncorporationsListNewIncorporations.equals(exercices)) {
                        oldExerciceIdOfIncorporationsListNewIncorporations.getIncorporationsList().remove(incorporationsListNewIncorporations);
                        oldExerciceIdOfIncorporationsListNewIncorporations = em.merge(oldExerciceIdOfIncorporationsListNewIncorporations);
                    }
                }
            }
            for (BonusMalus bonusMalusListOldBonusMalus : bonusMalusListOld) {
                if (!bonusMalusListNew.contains(bonusMalusListOldBonusMalus)) {
                    bonusMalusListOldBonusMalus.setExerciceId(null);
                    bonusMalusListOldBonusMalus = em.merge(bonusMalusListOldBonusMalus);
                }
            }
            for (BonusMalus bonusMalusListNewBonusMalus : bonusMalusListNew) {
                if (!bonusMalusListOld.contains(bonusMalusListNewBonusMalus)) {
                    Exercices oldExerciceIdOfBonusMalusListNewBonusMalus = bonusMalusListNewBonusMalus.getExerciceId();
                    bonusMalusListNewBonusMalus.setExerciceId(exercices);
                    bonusMalusListNewBonusMalus = em.merge(bonusMalusListNewBonusMalus);
                    if (oldExerciceIdOfBonusMalusListNewBonusMalus != null && !oldExerciceIdOfBonusMalusListNewBonusMalus.equals(exercices)) {
                        oldExerciceIdOfBonusMalusListNewBonusMalus.getBonusMalusList().remove(bonusMalusListNewBonusMalus);
                        oldExerciceIdOfBonusMalusListNewBonusMalus = em.merge(oldExerciceIdOfBonusMalusListNewBonusMalus);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = exercices.getId();
                if (findExercices(id) == null) {
                    throw new NonexistentEntityException("The exercices with id " + id + " no longer exists.");
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
            Exercices exercices;
            try {
                exercices = em.getReference(Exercices.class, id);
                exercices.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The exercices with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Assures> assuresListOrphanCheck = exercices.getAssuresList();
            for (Assures assuresListOrphanCheckAssures : assuresListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Exercices (" + exercices + ") cannot be destroyed since the Assures " + assuresListOrphanCheckAssures + " in its assuresList field has a non-nullable exerciceId field.");
            }
            List<Incorporations> incorporationsListOrphanCheck = exercices.getIncorporationsList();
            for (Incorporations incorporationsListOrphanCheckIncorporations : incorporationsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Exercices (" + exercices + ") cannot be destroyed since the Incorporations " + incorporationsListOrphanCheckIncorporations + " in its incorporationsList field has a non-nullable exerciceId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Decomptes> decomptesList = exercices.getDecomptesList();
            for (Decomptes decomptesListDecomptes : decomptesList) {
                decomptesListDecomptes.setExerciceId(null);
                decomptesListDecomptes = em.merge(decomptesListDecomptes);
            }
            List<Polices> policesList = exercices.getPolicesList();
            for (Polices policesListPolices : policesList) {
                policesListPolices.setExerciceId(null);
                policesListPolices = em.merge(policesListPolices);
            }
            List<Bpc> bpcList = exercices.getBpcList();
            for (Bpc bpcListBpc : bpcList) {
                bpcListBpc.setExerciceId(null);
                bpcListBpc = em.merge(bpcListBpc);
            }
            List<BonusMalus> bonusMalusList = exercices.getBonusMalusList();
            for (BonusMalus bonusMalusListBonusMalus : bonusMalusList) {
                bonusMalusListBonusMalus.setExerciceId(null);
                bonusMalusListBonusMalus = em.merge(bonusMalusListBonusMalus);
            }
            em.remove(exercices);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Exercices> findExercicesEntities() {
        return findExercicesEntities(true, -1, -1);
    }

    public List<Exercices> findExercicesEntities(int maxResults, int firstResult) {
        return findExercicesEntities(false, maxResults, firstResult);
    }

    private List<Exercices> findExercicesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Exercices.class));
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

    public Exercices findExercices(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Exercices.class, id);
        } finally {
            em.close();
        }
    }

    public int getExercicesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Exercices> rt = cq.from(Exercices.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
