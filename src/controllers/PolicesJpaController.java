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
import entities.Exercices;
import entities.Succursales;
import entities.Etablissements;
import java.util.ArrayList;
import java.util.List;
import entities.Assures;
import entities.Decomptes;
import entities.Bpc;
import entities.Incorporations;
import entities.BonusMalus;
import entities.Polices;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author EDY TCHOKOUANI
 */
public class PolicesJpaController implements Serializable {

    public PolicesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Polices polices) {
        if (polices.getEtablissementsList() == null) {
            polices.setEtablissementsList(new ArrayList<Etablissements>());
        }
        if (polices.getAssuresList() == null) {
            polices.setAssuresList(new ArrayList<Assures>());
        }
        if (polices.getDecomptesList() == null) {
            polices.setDecomptesList(new ArrayList<Decomptes>());
        }
        if (polices.getBpcList() == null) {
            polices.setBpcList(new ArrayList<Bpc>());
        }
        if (polices.getIncorporationsList() == null) {
            polices.setIncorporationsList(new ArrayList<Incorporations>());
        }
        if (polices.getBonusMalusList() == null) {
            polices.setBonusMalusList(new ArrayList<BonusMalus>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Exercices exerciceId = polices.getExerciceId();
            if (exerciceId != null) {
                exerciceId = em.getReference(exerciceId.getClass(), exerciceId.getId());
                polices.setExerciceId(exerciceId);
            }
            Succursales succursaleId = polices.getSuccursaleId();
            if (succursaleId != null) {
                succursaleId = em.getReference(succursaleId.getClass(), succursaleId.getId());
                polices.setSuccursaleId(succursaleId);
            }
            List<Etablissements> attachedEtablissementsList = new ArrayList<Etablissements>();
            for (Etablissements etablissementsListEtablissementsToAttach : polices.getEtablissementsList()) {
                etablissementsListEtablissementsToAttach = em.getReference(etablissementsListEtablissementsToAttach.getClass(), etablissementsListEtablissementsToAttach.getId());
                attachedEtablissementsList.add(etablissementsListEtablissementsToAttach);
            }
            polices.setEtablissementsList(attachedEtablissementsList);
            List<Assures> attachedAssuresList = new ArrayList<Assures>();
            for (Assures assuresListAssuresToAttach : polices.getAssuresList()) {
                assuresListAssuresToAttach = em.getReference(assuresListAssuresToAttach.getClass(), assuresListAssuresToAttach.getId());
                attachedAssuresList.add(assuresListAssuresToAttach);
            }
            polices.setAssuresList(attachedAssuresList);
            List<Decomptes> attachedDecomptesList = new ArrayList<Decomptes>();
            for (Decomptes decomptesListDecomptesToAttach : polices.getDecomptesList()) {
                decomptesListDecomptesToAttach = em.getReference(decomptesListDecomptesToAttach.getClass(), decomptesListDecomptesToAttach.getId());
                attachedDecomptesList.add(decomptesListDecomptesToAttach);
            }
            polices.setDecomptesList(attachedDecomptesList);
            List<Bpc> attachedBpcList = new ArrayList<Bpc>();
            for (Bpc bpcListBpcToAttach : polices.getBpcList()) {
                bpcListBpcToAttach = em.getReference(bpcListBpcToAttach.getClass(), bpcListBpcToAttach.getId());
                attachedBpcList.add(bpcListBpcToAttach);
            }
            polices.setBpcList(attachedBpcList);
            List<Incorporations> attachedIncorporationsList = new ArrayList<Incorporations>();
            for (Incorporations incorporationsListIncorporationsToAttach : polices.getIncorporationsList()) {
                incorporationsListIncorporationsToAttach = em.getReference(incorporationsListIncorporationsToAttach.getClass(), incorporationsListIncorporationsToAttach.getId());
                attachedIncorporationsList.add(incorporationsListIncorporationsToAttach);
            }
            polices.setIncorporationsList(attachedIncorporationsList);
            List<BonusMalus> attachedBonusMalusList = new ArrayList<BonusMalus>();
            for (BonusMalus bonusMalusListBonusMalusToAttach : polices.getBonusMalusList()) {
                bonusMalusListBonusMalusToAttach = em.getReference(bonusMalusListBonusMalusToAttach.getClass(), bonusMalusListBonusMalusToAttach.getId());
                attachedBonusMalusList.add(bonusMalusListBonusMalusToAttach);
            }
            polices.setBonusMalusList(attachedBonusMalusList);
            em.persist(polices);
            if (exerciceId != null) {
                exerciceId.getPolicesList().add(polices);
                exerciceId = em.merge(exerciceId);
            }
            if (succursaleId != null) {
                succursaleId.getPolicesList().add(polices);
                succursaleId = em.merge(succursaleId);
            }
            for (Etablissements etablissementsListEtablissements : polices.getEtablissementsList()) {
                etablissementsListEtablissements.getPolicesList().add(polices);
                etablissementsListEtablissements = em.merge(etablissementsListEtablissements);
            }
            for (Assures assuresListAssures : polices.getAssuresList()) {
                Polices oldPoliceIdOfAssuresListAssures = assuresListAssures.getPoliceId();
                assuresListAssures.setPoliceId(polices);
                assuresListAssures = em.merge(assuresListAssures);
                if (oldPoliceIdOfAssuresListAssures != null) {
                    oldPoliceIdOfAssuresListAssures.getAssuresList().remove(assuresListAssures);
                    oldPoliceIdOfAssuresListAssures = em.merge(oldPoliceIdOfAssuresListAssures);
                }
            }
            for (Decomptes decomptesListDecomptes : polices.getDecomptesList()) {
                Polices oldPoliceIdOfDecomptesListDecomptes = decomptesListDecomptes.getPoliceId();
                decomptesListDecomptes.setPoliceId(polices);
                decomptesListDecomptes = em.merge(decomptesListDecomptes);
                if (oldPoliceIdOfDecomptesListDecomptes != null) {
                    oldPoliceIdOfDecomptesListDecomptes.getDecomptesList().remove(decomptesListDecomptes);
                    oldPoliceIdOfDecomptesListDecomptes = em.merge(oldPoliceIdOfDecomptesListDecomptes);
                }
            }
            for (Bpc bpcListBpc : polices.getBpcList()) {
                Polices oldPoliceIdOfBpcListBpc = bpcListBpc.getPoliceId();
                bpcListBpc.setPoliceId(polices);
                bpcListBpc = em.merge(bpcListBpc);
                if (oldPoliceIdOfBpcListBpc != null) {
                    oldPoliceIdOfBpcListBpc.getBpcList().remove(bpcListBpc);
                    oldPoliceIdOfBpcListBpc = em.merge(oldPoliceIdOfBpcListBpc);
                }
            }
            for (Incorporations incorporationsListIncorporations : polices.getIncorporationsList()) {
                Polices oldPoliceIdOfIncorporationsListIncorporations = incorporationsListIncorporations.getPoliceId();
                incorporationsListIncorporations.setPoliceId(polices);
                incorporationsListIncorporations = em.merge(incorporationsListIncorporations);
                if (oldPoliceIdOfIncorporationsListIncorporations != null) {
                    oldPoliceIdOfIncorporationsListIncorporations.getIncorporationsList().remove(incorporationsListIncorporations);
                    oldPoliceIdOfIncorporationsListIncorporations = em.merge(oldPoliceIdOfIncorporationsListIncorporations);
                }
            }
            for (BonusMalus bonusMalusListBonusMalus : polices.getBonusMalusList()) {
                Polices oldPoliceIdOfBonusMalusListBonusMalus = bonusMalusListBonusMalus.getPoliceId();
                bonusMalusListBonusMalus.setPoliceId(polices);
                bonusMalusListBonusMalus = em.merge(bonusMalusListBonusMalus);
                if (oldPoliceIdOfBonusMalusListBonusMalus != null) {
                    oldPoliceIdOfBonusMalusListBonusMalus.getBonusMalusList().remove(bonusMalusListBonusMalus);
                    oldPoliceIdOfBonusMalusListBonusMalus = em.merge(oldPoliceIdOfBonusMalusListBonusMalus);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Polices polices) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Polices persistentPolices = em.find(Polices.class, polices.getId());
            Exercices exerciceIdOld = persistentPolices.getExerciceId();
            Exercices exerciceIdNew = polices.getExerciceId();
            Succursales succursaleIdOld = persistentPolices.getSuccursaleId();
            Succursales succursaleIdNew = polices.getSuccursaleId();
            List<Etablissements> etablissementsListOld = persistentPolices.getEtablissementsList();
            List<Etablissements> etablissementsListNew = polices.getEtablissementsList();
            List<Assures> assuresListOld = persistentPolices.getAssuresList();
            List<Assures> assuresListNew = polices.getAssuresList();
            List<Decomptes> decomptesListOld = persistentPolices.getDecomptesList();
            List<Decomptes> decomptesListNew = polices.getDecomptesList();
            List<Bpc> bpcListOld = persistentPolices.getBpcList();
            List<Bpc> bpcListNew = polices.getBpcList();
            List<Incorporations> incorporationsListOld = persistentPolices.getIncorporationsList();
            List<Incorporations> incorporationsListNew = polices.getIncorporationsList();
            List<BonusMalus> bonusMalusListOld = persistentPolices.getBonusMalusList();
            List<BonusMalus> bonusMalusListNew = polices.getBonusMalusList();
            List<String> illegalOrphanMessages = null;
            for (Assures assuresListOldAssures : assuresListOld) {
                if (!assuresListNew.contains(assuresListOldAssures)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Assures " + assuresListOldAssures + " since its policeId field is not nullable.");
                }
            }
            for (Incorporations incorporationsListOldIncorporations : incorporationsListOld) {
                if (!incorporationsListNew.contains(incorporationsListOldIncorporations)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Incorporations " + incorporationsListOldIncorporations + " since its policeId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (exerciceIdNew != null) {
                exerciceIdNew = em.getReference(exerciceIdNew.getClass(), exerciceIdNew.getId());
                polices.setExerciceId(exerciceIdNew);
            }
            if (succursaleIdNew != null) {
                succursaleIdNew = em.getReference(succursaleIdNew.getClass(), succursaleIdNew.getId());
                polices.setSuccursaleId(succursaleIdNew);
            }
            List<Etablissements> attachedEtablissementsListNew = new ArrayList<Etablissements>();
            for (Etablissements etablissementsListNewEtablissementsToAttach : etablissementsListNew) {
                etablissementsListNewEtablissementsToAttach = em.getReference(etablissementsListNewEtablissementsToAttach.getClass(), etablissementsListNewEtablissementsToAttach.getId());
                attachedEtablissementsListNew.add(etablissementsListNewEtablissementsToAttach);
            }
            etablissementsListNew = attachedEtablissementsListNew;
            polices.setEtablissementsList(etablissementsListNew);
            List<Assures> attachedAssuresListNew = new ArrayList<Assures>();
            for (Assures assuresListNewAssuresToAttach : assuresListNew) {
                assuresListNewAssuresToAttach = em.getReference(assuresListNewAssuresToAttach.getClass(), assuresListNewAssuresToAttach.getId());
                attachedAssuresListNew.add(assuresListNewAssuresToAttach);
            }
            assuresListNew = attachedAssuresListNew;
            polices.setAssuresList(assuresListNew);
            List<Decomptes> attachedDecomptesListNew = new ArrayList<Decomptes>();
            for (Decomptes decomptesListNewDecomptesToAttach : decomptesListNew) {
                decomptesListNewDecomptesToAttach = em.getReference(decomptesListNewDecomptesToAttach.getClass(), decomptesListNewDecomptesToAttach.getId());
                attachedDecomptesListNew.add(decomptesListNewDecomptesToAttach);
            }
            decomptesListNew = attachedDecomptesListNew;
            polices.setDecomptesList(decomptesListNew);
            List<Bpc> attachedBpcListNew = new ArrayList<Bpc>();
            for (Bpc bpcListNewBpcToAttach : bpcListNew) {
                bpcListNewBpcToAttach = em.getReference(bpcListNewBpcToAttach.getClass(), bpcListNewBpcToAttach.getId());
                attachedBpcListNew.add(bpcListNewBpcToAttach);
            }
            bpcListNew = attachedBpcListNew;
            polices.setBpcList(bpcListNew);
            List<Incorporations> attachedIncorporationsListNew = new ArrayList<Incorporations>();
            for (Incorporations incorporationsListNewIncorporationsToAttach : incorporationsListNew) {
                incorporationsListNewIncorporationsToAttach = em.getReference(incorporationsListNewIncorporationsToAttach.getClass(), incorporationsListNewIncorporationsToAttach.getId());
                attachedIncorporationsListNew.add(incorporationsListNewIncorporationsToAttach);
            }
            incorporationsListNew = attachedIncorporationsListNew;
            polices.setIncorporationsList(incorporationsListNew);
            List<BonusMalus> attachedBonusMalusListNew = new ArrayList<BonusMalus>();
            for (BonusMalus bonusMalusListNewBonusMalusToAttach : bonusMalusListNew) {
                bonusMalusListNewBonusMalusToAttach = em.getReference(bonusMalusListNewBonusMalusToAttach.getClass(), bonusMalusListNewBonusMalusToAttach.getId());
                attachedBonusMalusListNew.add(bonusMalusListNewBonusMalusToAttach);
            }
            bonusMalusListNew = attachedBonusMalusListNew;
            polices.setBonusMalusList(bonusMalusListNew);
            polices = em.merge(polices);
            if (exerciceIdOld != null && !exerciceIdOld.equals(exerciceIdNew)) {
                exerciceIdOld.getPolicesList().remove(polices);
                exerciceIdOld = em.merge(exerciceIdOld);
            }
            if (exerciceIdNew != null && !exerciceIdNew.equals(exerciceIdOld)) {
                exerciceIdNew.getPolicesList().add(polices);
                exerciceIdNew = em.merge(exerciceIdNew);
            }
            if (succursaleIdOld != null && !succursaleIdOld.equals(succursaleIdNew)) {
                succursaleIdOld.getPolicesList().remove(polices);
                succursaleIdOld = em.merge(succursaleIdOld);
            }
            if (succursaleIdNew != null && !succursaleIdNew.equals(succursaleIdOld)) {
                succursaleIdNew.getPolicesList().add(polices);
                succursaleIdNew = em.merge(succursaleIdNew);
            }
            for (Etablissements etablissementsListOldEtablissements : etablissementsListOld) {
                if (!etablissementsListNew.contains(etablissementsListOldEtablissements)) {
                    etablissementsListOldEtablissements.getPolicesList().remove(polices);
                    etablissementsListOldEtablissements = em.merge(etablissementsListOldEtablissements);
                }
            }
            for (Etablissements etablissementsListNewEtablissements : etablissementsListNew) {
                if (!etablissementsListOld.contains(etablissementsListNewEtablissements)) {
                    etablissementsListNewEtablissements.getPolicesList().add(polices);
                    etablissementsListNewEtablissements = em.merge(etablissementsListNewEtablissements);
                }
            }
            for (Assures assuresListNewAssures : assuresListNew) {
                if (!assuresListOld.contains(assuresListNewAssures)) {
                    Polices oldPoliceIdOfAssuresListNewAssures = assuresListNewAssures.getPoliceId();
                    assuresListNewAssures.setPoliceId(polices);
                    assuresListNewAssures = em.merge(assuresListNewAssures);
                    if (oldPoliceIdOfAssuresListNewAssures != null && !oldPoliceIdOfAssuresListNewAssures.equals(polices)) {
                        oldPoliceIdOfAssuresListNewAssures.getAssuresList().remove(assuresListNewAssures);
                        oldPoliceIdOfAssuresListNewAssures = em.merge(oldPoliceIdOfAssuresListNewAssures);
                    }
                }
            }
            for (Decomptes decomptesListOldDecomptes : decomptesListOld) {
                if (!decomptesListNew.contains(decomptesListOldDecomptes)) {
                    decomptesListOldDecomptes.setPoliceId(null);
                    decomptesListOldDecomptes = em.merge(decomptesListOldDecomptes);
                }
            }
            for (Decomptes decomptesListNewDecomptes : decomptesListNew) {
                if (!decomptesListOld.contains(decomptesListNewDecomptes)) {
                    Polices oldPoliceIdOfDecomptesListNewDecomptes = decomptesListNewDecomptes.getPoliceId();
                    decomptesListNewDecomptes.setPoliceId(polices);
                    decomptesListNewDecomptes = em.merge(decomptesListNewDecomptes);
                    if (oldPoliceIdOfDecomptesListNewDecomptes != null && !oldPoliceIdOfDecomptesListNewDecomptes.equals(polices)) {
                        oldPoliceIdOfDecomptesListNewDecomptes.getDecomptesList().remove(decomptesListNewDecomptes);
                        oldPoliceIdOfDecomptesListNewDecomptes = em.merge(oldPoliceIdOfDecomptesListNewDecomptes);
                    }
                }
            }
            for (Bpc bpcListOldBpc : bpcListOld) {
                if (!bpcListNew.contains(bpcListOldBpc)) {
                    bpcListOldBpc.setPoliceId(null);
                    bpcListOldBpc = em.merge(bpcListOldBpc);
                }
            }
            for (Bpc bpcListNewBpc : bpcListNew) {
                if (!bpcListOld.contains(bpcListNewBpc)) {
                    Polices oldPoliceIdOfBpcListNewBpc = bpcListNewBpc.getPoliceId();
                    bpcListNewBpc.setPoliceId(polices);
                    bpcListNewBpc = em.merge(bpcListNewBpc);
                    if (oldPoliceIdOfBpcListNewBpc != null && !oldPoliceIdOfBpcListNewBpc.equals(polices)) {
                        oldPoliceIdOfBpcListNewBpc.getBpcList().remove(bpcListNewBpc);
                        oldPoliceIdOfBpcListNewBpc = em.merge(oldPoliceIdOfBpcListNewBpc);
                    }
                }
            }
            for (Incorporations incorporationsListNewIncorporations : incorporationsListNew) {
                if (!incorporationsListOld.contains(incorporationsListNewIncorporations)) {
                    Polices oldPoliceIdOfIncorporationsListNewIncorporations = incorporationsListNewIncorporations.getPoliceId();
                    incorporationsListNewIncorporations.setPoliceId(polices);
                    incorporationsListNewIncorporations = em.merge(incorporationsListNewIncorporations);
                    if (oldPoliceIdOfIncorporationsListNewIncorporations != null && !oldPoliceIdOfIncorporationsListNewIncorporations.equals(polices)) {
                        oldPoliceIdOfIncorporationsListNewIncorporations.getIncorporationsList().remove(incorporationsListNewIncorporations);
                        oldPoliceIdOfIncorporationsListNewIncorporations = em.merge(oldPoliceIdOfIncorporationsListNewIncorporations);
                    }
                }
            }
            for (BonusMalus bonusMalusListOldBonusMalus : bonusMalusListOld) {
                if (!bonusMalusListNew.contains(bonusMalusListOldBonusMalus)) {
                    bonusMalusListOldBonusMalus.setPoliceId(null);
                    bonusMalusListOldBonusMalus = em.merge(bonusMalusListOldBonusMalus);
                }
            }
            for (BonusMalus bonusMalusListNewBonusMalus : bonusMalusListNew) {
                if (!bonusMalusListOld.contains(bonusMalusListNewBonusMalus)) {
                    Polices oldPoliceIdOfBonusMalusListNewBonusMalus = bonusMalusListNewBonusMalus.getPoliceId();
                    bonusMalusListNewBonusMalus.setPoliceId(polices);
                    bonusMalusListNewBonusMalus = em.merge(bonusMalusListNewBonusMalus);
                    if (oldPoliceIdOfBonusMalusListNewBonusMalus != null && !oldPoliceIdOfBonusMalusListNewBonusMalus.equals(polices)) {
                        oldPoliceIdOfBonusMalusListNewBonusMalus.getBonusMalusList().remove(bonusMalusListNewBonusMalus);
                        oldPoliceIdOfBonusMalusListNewBonusMalus = em.merge(oldPoliceIdOfBonusMalusListNewBonusMalus);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = polices.getId();
                if (findPolices(id) == null) {
                    throw new NonexistentEntityException("The polices with id " + id + " no longer exists.");
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
            Polices polices;
            try {
                polices = em.getReference(Polices.class, id);
                polices.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The polices with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Assures> assuresListOrphanCheck = polices.getAssuresList();
            for (Assures assuresListOrphanCheckAssures : assuresListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Polices (" + polices + ") cannot be destroyed since the Assures " + assuresListOrphanCheckAssures + " in its assuresList field has a non-nullable policeId field.");
            }
            List<Incorporations> incorporationsListOrphanCheck = polices.getIncorporationsList();
            for (Incorporations incorporationsListOrphanCheckIncorporations : incorporationsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Polices (" + polices + ") cannot be destroyed since the Incorporations " + incorporationsListOrphanCheckIncorporations + " in its incorporationsList field has a non-nullable policeId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Exercices exerciceId = polices.getExerciceId();
            if (exerciceId != null) {
                exerciceId.getPolicesList().remove(polices);
                exerciceId = em.merge(exerciceId);
            }
            Succursales succursaleId = polices.getSuccursaleId();
            if (succursaleId != null) {
                succursaleId.getPolicesList().remove(polices);
                succursaleId = em.merge(succursaleId);
            }
            List<Etablissements> etablissementsList = polices.getEtablissementsList();
            for (Etablissements etablissementsListEtablissements : etablissementsList) {
                etablissementsListEtablissements.getPolicesList().remove(polices);
                etablissementsListEtablissements = em.merge(etablissementsListEtablissements);
            }
            List<Decomptes> decomptesList = polices.getDecomptesList();
            for (Decomptes decomptesListDecomptes : decomptesList) {
                decomptesListDecomptes.setPoliceId(null);
                decomptesListDecomptes = em.merge(decomptesListDecomptes);
            }
            List<Bpc> bpcList = polices.getBpcList();
            for (Bpc bpcListBpc : bpcList) {
                bpcListBpc.setPoliceId(null);
                bpcListBpc = em.merge(bpcListBpc);
            }
            List<BonusMalus> bonusMalusList = polices.getBonusMalusList();
            for (BonusMalus bonusMalusListBonusMalus : bonusMalusList) {
                bonusMalusListBonusMalus.setPoliceId(null);
                bonusMalusListBonusMalus = em.merge(bonusMalusListBonusMalus);
            }
            em.remove(polices);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Polices> findPolicesEntities() {
        return findPolicesEntities(true, -1, -1);
    }

    public List<Polices> findPolicesEntities(int maxResults, int firstResult) {
        return findPolicesEntities(false, maxResults, firstResult);
    }

    private List<Polices> findPolicesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Polices.class));
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

    public Polices findPolices(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Polices.class, id);
        } finally {
            em.close();
        }
    }

    public int getPolicesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Polices> rt = cq.from(Polices.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
