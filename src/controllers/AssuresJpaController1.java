/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import entities.Assures;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Exercices;
import entities.Polices;
import entities.Succursales;
import entities.Decomptes;
import java.util.ArrayList;
import java.util.List;
import entities.Bpc;
import entities.BonusMalus;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author EDY TCHOKOUANI
 */
public class AssuresJpaController1 implements Serializable {

    public AssuresJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Assures assures) {
        if (assures.getDecomptesList() == null) {
            assures.setDecomptesList(new ArrayList<Decomptes>());
        }
        if (assures.getBpcList() == null) {
            assures.setBpcList(new ArrayList<Bpc>());
        }
        if (assures.getBonusMalusList() == null) {
            assures.setBonusMalusList(new ArrayList<BonusMalus>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Exercices exerciceId = assures.getExerciceId();
            if (exerciceId != null) {
                exerciceId = em.getReference(exerciceId.getClass(), exerciceId.getId());
                assures.setExerciceId(exerciceId);
            }
            Polices policeId = assures.getPoliceId();
            if (policeId != null) {
                policeId = em.getReference(policeId.getClass(), policeId.getId());
                assures.setPoliceId(policeId);
            }
            Succursales succursaleId = assures.getSuccursaleId();
            if (succursaleId != null) {
                succursaleId = em.getReference(succursaleId.getClass(), succursaleId.getId());
                assures.setSuccursaleId(succursaleId);
            }
            List<Decomptes> attachedDecomptesList = new ArrayList<Decomptes>();
            for (Decomptes decomptesListDecomptesToAttach : assures.getDecomptesList()) {
                decomptesListDecomptesToAttach = em.getReference(decomptesListDecomptesToAttach.getClass(), decomptesListDecomptesToAttach.getId());
                attachedDecomptesList.add(decomptesListDecomptesToAttach);
            }
            assures.setDecomptesList(attachedDecomptesList);
            List<Bpc> attachedBpcList = new ArrayList<Bpc>();
            for (Bpc bpcListBpcToAttach : assures.getBpcList()) {
                bpcListBpcToAttach = em.getReference(bpcListBpcToAttach.getClass(), bpcListBpcToAttach.getId());
                attachedBpcList.add(bpcListBpcToAttach);
            }
            assures.setBpcList(attachedBpcList);
            List<BonusMalus> attachedBonusMalusList = new ArrayList<BonusMalus>();
            for (BonusMalus bonusMalusListBonusMalusToAttach : assures.getBonusMalusList()) {
                bonusMalusListBonusMalusToAttach = em.getReference(bonusMalusListBonusMalusToAttach.getClass(), bonusMalusListBonusMalusToAttach.getId());
                attachedBonusMalusList.add(bonusMalusListBonusMalusToAttach);
            }
            assures.setBonusMalusList(attachedBonusMalusList);
            em.persist(assures);
            if (exerciceId != null) {
                exerciceId.getAssuresList().add(assures);
                exerciceId = em.merge(exerciceId);
            }
            if (policeId != null) {
                policeId.getAssuresList().add(assures);
                policeId = em.merge(policeId);
            }
            if (succursaleId != null) {
                succursaleId.getAssuresList().add(assures);
                succursaleId = em.merge(succursaleId);
            }
            for (Decomptes decomptesListDecomptes : assures.getDecomptesList()) {
                Assures oldAssureIdOfDecomptesListDecomptes = decomptesListDecomptes.getAssureId();
                decomptesListDecomptes.setAssureId(assures);
                decomptesListDecomptes = em.merge(decomptesListDecomptes);
                if (oldAssureIdOfDecomptesListDecomptes != null) {
                    oldAssureIdOfDecomptesListDecomptes.getDecomptesList().remove(decomptesListDecomptes);
                    oldAssureIdOfDecomptesListDecomptes = em.merge(oldAssureIdOfDecomptesListDecomptes);
                }
            }
            for (Bpc bpcListBpc : assures.getBpcList()) {
                Assures oldAssureIdOfBpcListBpc = bpcListBpc.getAssureId();
                bpcListBpc.setAssureId(assures);
                bpcListBpc = em.merge(bpcListBpc);
                if (oldAssureIdOfBpcListBpc != null) {
                    oldAssureIdOfBpcListBpc.getBpcList().remove(bpcListBpc);
                    oldAssureIdOfBpcListBpc = em.merge(oldAssureIdOfBpcListBpc);
                }
            }
            for (BonusMalus bonusMalusListBonusMalus : assures.getBonusMalusList()) {
                Assures oldAssureIdOfBonusMalusListBonusMalus = bonusMalusListBonusMalus.getAssureId();
                bonusMalusListBonusMalus.setAssureId(assures);
                bonusMalusListBonusMalus = em.merge(bonusMalusListBonusMalus);
                if (oldAssureIdOfBonusMalusListBonusMalus != null) {
                    oldAssureIdOfBonusMalusListBonusMalus.getBonusMalusList().remove(bonusMalusListBonusMalus);
                    oldAssureIdOfBonusMalusListBonusMalus = em.merge(oldAssureIdOfBonusMalusListBonusMalus);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Assures assures) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Assures persistentAssures = em.find(Assures.class, assures.getId());
            Exercices exerciceIdOld = persistentAssures.getExerciceId();
            Exercices exerciceIdNew = assures.getExerciceId();
            Polices policeIdOld = persistentAssures.getPoliceId();
            Polices policeIdNew = assures.getPoliceId();
            Succursales succursaleIdOld = persistentAssures.getSuccursaleId();
            Succursales succursaleIdNew = assures.getSuccursaleId();
            List<Decomptes> decomptesListOld = persistentAssures.getDecomptesList();
            List<Decomptes> decomptesListNew = assures.getDecomptesList();
            List<Bpc> bpcListOld = persistentAssures.getBpcList();
            List<Bpc> bpcListNew = assures.getBpcList();
            List<BonusMalus> bonusMalusListOld = persistentAssures.getBonusMalusList();
            List<BonusMalus> bonusMalusListNew = assures.getBonusMalusList();
            if (exerciceIdNew != null) {
                exerciceIdNew = em.getReference(exerciceIdNew.getClass(), exerciceIdNew.getId());
                assures.setExerciceId(exerciceIdNew);
            }
            if (policeIdNew != null) {
                policeIdNew = em.getReference(policeIdNew.getClass(), policeIdNew.getId());
                assures.setPoliceId(policeIdNew);
            }
            if (succursaleIdNew != null) {
                succursaleIdNew = em.getReference(succursaleIdNew.getClass(), succursaleIdNew.getId());
                assures.setSuccursaleId(succursaleIdNew);
            }
            List<Decomptes> attachedDecomptesListNew = new ArrayList<Decomptes>();
            for (Decomptes decomptesListNewDecomptesToAttach : decomptesListNew) {
                decomptesListNewDecomptesToAttach = em.getReference(decomptesListNewDecomptesToAttach.getClass(), decomptesListNewDecomptesToAttach.getId());
                attachedDecomptesListNew.add(decomptesListNewDecomptesToAttach);
            }
            decomptesListNew = attachedDecomptesListNew;
            assures.setDecomptesList(decomptesListNew);
            List<Bpc> attachedBpcListNew = new ArrayList<Bpc>();
            for (Bpc bpcListNewBpcToAttach : bpcListNew) {
                bpcListNewBpcToAttach = em.getReference(bpcListNewBpcToAttach.getClass(), bpcListNewBpcToAttach.getId());
                attachedBpcListNew.add(bpcListNewBpcToAttach);
            }
            bpcListNew = attachedBpcListNew;
            assures.setBpcList(bpcListNew);
            List<BonusMalus> attachedBonusMalusListNew = new ArrayList<BonusMalus>();
            for (BonusMalus bonusMalusListNewBonusMalusToAttach : bonusMalusListNew) {
                bonusMalusListNewBonusMalusToAttach = em.getReference(bonusMalusListNewBonusMalusToAttach.getClass(), bonusMalusListNewBonusMalusToAttach.getId());
                attachedBonusMalusListNew.add(bonusMalusListNewBonusMalusToAttach);
            }
            bonusMalusListNew = attachedBonusMalusListNew;
            assures.setBonusMalusList(bonusMalusListNew);
            assures = em.merge(assures);
            if (exerciceIdOld != null && !exerciceIdOld.equals(exerciceIdNew)) {
                exerciceIdOld.getAssuresList().remove(assures);
                exerciceIdOld = em.merge(exerciceIdOld);
            }
            if (exerciceIdNew != null && !exerciceIdNew.equals(exerciceIdOld)) {
                exerciceIdNew.getAssuresList().add(assures);
                exerciceIdNew = em.merge(exerciceIdNew);
            }
            if (policeIdOld != null && !policeIdOld.equals(policeIdNew)) {
                policeIdOld.getAssuresList().remove(assures);
                policeIdOld = em.merge(policeIdOld);
            }
            if (policeIdNew != null && !policeIdNew.equals(policeIdOld)) {
                policeIdNew.getAssuresList().add(assures);
                policeIdNew = em.merge(policeIdNew);
            }
            if (succursaleIdOld != null && !succursaleIdOld.equals(succursaleIdNew)) {
                succursaleIdOld.getAssuresList().remove(assures);
                succursaleIdOld = em.merge(succursaleIdOld);
            }
            if (succursaleIdNew != null && !succursaleIdNew.equals(succursaleIdOld)) {
                succursaleIdNew.getAssuresList().add(assures);
                succursaleIdNew = em.merge(succursaleIdNew);
            }
            for (Decomptes decomptesListOldDecomptes : decomptesListOld) {
                if (!decomptesListNew.contains(decomptesListOldDecomptes)) {
                    decomptesListOldDecomptes.setAssureId(null);
                    decomptesListOldDecomptes = em.merge(decomptesListOldDecomptes);
                }
            }
            for (Decomptes decomptesListNewDecomptes : decomptesListNew) {
                if (!decomptesListOld.contains(decomptesListNewDecomptes)) {
                    Assures oldAssureIdOfDecomptesListNewDecomptes = decomptesListNewDecomptes.getAssureId();
                    decomptesListNewDecomptes.setAssureId(assures);
                    decomptesListNewDecomptes = em.merge(decomptesListNewDecomptes);
                    if (oldAssureIdOfDecomptesListNewDecomptes != null && !oldAssureIdOfDecomptesListNewDecomptes.equals(assures)) {
                        oldAssureIdOfDecomptesListNewDecomptes.getDecomptesList().remove(decomptesListNewDecomptes);
                        oldAssureIdOfDecomptesListNewDecomptes = em.merge(oldAssureIdOfDecomptesListNewDecomptes);
                    }
                }
            }
            for (Bpc bpcListOldBpc : bpcListOld) {
                if (!bpcListNew.contains(bpcListOldBpc)) {
                    bpcListOldBpc.setAssureId(null);
                    bpcListOldBpc = em.merge(bpcListOldBpc);
                }
            }
            for (Bpc bpcListNewBpc : bpcListNew) {
                if (!bpcListOld.contains(bpcListNewBpc)) {
                    Assures oldAssureIdOfBpcListNewBpc = bpcListNewBpc.getAssureId();
                    bpcListNewBpc.setAssureId(assures);
                    bpcListNewBpc = em.merge(bpcListNewBpc);
                    if (oldAssureIdOfBpcListNewBpc != null && !oldAssureIdOfBpcListNewBpc.equals(assures)) {
                        oldAssureIdOfBpcListNewBpc.getBpcList().remove(bpcListNewBpc);
                        oldAssureIdOfBpcListNewBpc = em.merge(oldAssureIdOfBpcListNewBpc);
                    }
                }
            }
            for (BonusMalus bonusMalusListOldBonusMalus : bonusMalusListOld) {
                if (!bonusMalusListNew.contains(bonusMalusListOldBonusMalus)) {
                    bonusMalusListOldBonusMalus.setAssureId(null);
                    bonusMalusListOldBonusMalus = em.merge(bonusMalusListOldBonusMalus);
                }
            }
            for (BonusMalus bonusMalusListNewBonusMalus : bonusMalusListNew) {
                if (!bonusMalusListOld.contains(bonusMalusListNewBonusMalus)) {
                    Assures oldAssureIdOfBonusMalusListNewBonusMalus = bonusMalusListNewBonusMalus.getAssureId();
                    bonusMalusListNewBonusMalus.setAssureId(assures);
                    bonusMalusListNewBonusMalus = em.merge(bonusMalusListNewBonusMalus);
                    if (oldAssureIdOfBonusMalusListNewBonusMalus != null && !oldAssureIdOfBonusMalusListNewBonusMalus.equals(assures)) {
                        oldAssureIdOfBonusMalusListNewBonusMalus.getBonusMalusList().remove(bonusMalusListNewBonusMalus);
                        oldAssureIdOfBonusMalusListNewBonusMalus = em.merge(oldAssureIdOfBonusMalusListNewBonusMalus);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = assures.getId();
                if (findAssures(id) == null) {
                    throw new NonexistentEntityException("The assures with id " + id + " no longer exists.");
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
            Assures assures;
            try {
                assures = em.getReference(Assures.class, id);
                assures.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The assures with id " + id + " no longer exists.", enfe);
            }
            Exercices exerciceId = assures.getExerciceId();
            if (exerciceId != null) {
                exerciceId.getAssuresList().remove(assures);
                exerciceId = em.merge(exerciceId);
            }
            Polices policeId = assures.getPoliceId();
            if (policeId != null) {
                policeId.getAssuresList().remove(assures);
                policeId = em.merge(policeId);
            }
            Succursales succursaleId = assures.getSuccursaleId();
            if (succursaleId != null) {
                succursaleId.getAssuresList().remove(assures);
                succursaleId = em.merge(succursaleId);
            }
            List<Decomptes> decomptesList = assures.getDecomptesList();
            for (Decomptes decomptesListDecomptes : decomptesList) {
                decomptesListDecomptes.setAssureId(null);
                decomptesListDecomptes = em.merge(decomptesListDecomptes);
            }
            List<Bpc> bpcList = assures.getBpcList();
            for (Bpc bpcListBpc : bpcList) {
                bpcListBpc.setAssureId(null);
                bpcListBpc = em.merge(bpcListBpc);
            }
            List<BonusMalus> bonusMalusList = assures.getBonusMalusList();
            for (BonusMalus bonusMalusListBonusMalus : bonusMalusList) {
                bonusMalusListBonusMalus.setAssureId(null);
                bonusMalusListBonusMalus = em.merge(bonusMalusListBonusMalus);
            }
            em.remove(assures);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Assures> findAssuresEntities() {
        return findAssuresEntities(true, -1, -1);
    }

    public List<Assures> findAssuresEntities(int maxResults, int firstResult) {
        return findAssuresEntities(false, maxResults, firstResult);
    }

    private List<Assures> findAssuresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Assures.class));
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

    public Assures findAssures(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Assures.class, id);
        } finally {
            em.close();
        }
    }

    public int getAssuresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Assures> rt = cq.from(Assures.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
