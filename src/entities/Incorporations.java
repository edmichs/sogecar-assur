/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author EDY TCHOKOUANI
 */
@Entity
@Table(name = "incorporations", catalog = "sogecar", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incorporations.findAll", query = "SELECT i FROM Incorporations i")
    , @NamedQuery(name = "Incorporations.findById", query = "SELECT i FROM Incorporations i WHERE i.id = :id")
    , @NamedQuery(name = "Incorporations.findByMatricule", query = "SELECT i FROM Incorporations i WHERE i.matricule = :matricule")
    , @NamedQuery(name = "Incorporations.findByCodeFamille", query = "SELECT i FROM Incorporations i WHERE i.codeFamille = :codeFamille")
    , @NamedQuery(name = "Incorporations.findByNom", query = "SELECT i FROM Incorporations i WHERE i.nom = :nom")
    , @NamedQuery(name = "Incorporations.findByAvatar", query = "SELECT i FROM Incorporations i WHERE i.avatar = :avatar")
    , @NamedQuery(name = "Incorporations.findByLieuNaiss", query = "SELECT i FROM Incorporations i WHERE i.lieuNaiss = :lieuNaiss")
    , @NamedQuery(name = "Incorporations.findBySituaMarital", query = "SELECT i FROM Incorporations i WHERE i.situaMarital = :situaMarital")
    , @NamedQuery(name = "Incorporations.findByType", query = "SELECT i FROM Incorporations i WHERE i.type = :type")
    , @NamedQuery(name = "Incorporations.findByFctEmploye", query = "SELECT i FROM Incorporations i WHERE i.fctEmploye = :fctEmploye")
    , @NamedQuery(name = "Incorporations.findByObservation", query = "SELECT i FROM Incorporations i WHERE i.observation = :observation")
    , @NamedQuery(name = "Incorporations.findByTauxCouverture", query = "SELECT i FROM Incorporations i WHERE i.tauxCouverture = :tauxCouverture")
    , @NamedQuery(name = "Incorporations.findByPlafond", query = "SELECT i FROM Incorporations i WHERE i.plafond = :plafond")
    , @NamedQuery(name = "Incorporations.findBySolde", query = "SELECT i FROM Incorporations i WHERE i.solde = :solde")
    , @NamedQuery(name = "Incorporations.findByEncourConso", query = "SELECT i FROM Incorporations i WHERE i.encourConso = :encourConso")
    , @NamedQuery(name = "Incorporations.findByNationalite", query = "SELECT i FROM Incorporations i WHERE i.nationalite = :nationalite")
    , @NamedQuery(name = "Incorporations.findByDateIncorporation", query = "SELECT i FROM Incorporations i WHERE i.dateIncorporation = :dateIncorporation")
    , @NamedQuery(name = "Incorporations.findByDateNaiss", query = "SELECT i FROM Incorporations i WHERE i.dateNaiss = :dateNaiss")})
public class Incorporations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "matricule", length = 255)
    private String matricule;
    @Column(name = "code_famille", length = 255)
    private String codeFamille;
    @Column(name = "nom", length = 255)
    private String nom;
    @Column(name = "avatar", length = 255)
    private String avatar;
    @Column(name = "lieu_naiss", length = 255)
    private String lieuNaiss;
    @Column(name = "situa_marital")
    private Boolean situaMarital;
    @Column(name = "type")
    private Integer type;
    @Column(name = "fct_employe", length = 255)
    private String fctEmploye;
    @Column(name = "observation", length = 255)
    private String observation;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "taux_couverture", precision = 17, scale = 17)
    private Double tauxCouverture;
    @Column(name = "plafond", precision = 17, scale = 17)
    private Double plafond;
    @Column(name = "solde", precision = 17, scale = 17)
    private Double solde;
    @Column(name = "encour_conso", precision = 17, scale = 17)
    private Double encourConso;
    @Column(name = "nationalite", length = 255)
    private String nationalite;
    @Column(name = "date_incorporation")
    @Temporal(TemporalType.DATE)
    private Date dateIncorporation;
    @Column(name = "date_naiss")
    @Temporal(TemporalType.DATE)
    private Date dateNaiss;
    @JoinColumn(name = "exercice_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Exercices exerciceId;
    @JoinColumn(name = "police_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Polices policeId;
    @JoinColumn(name = "succursale_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Succursales succursaleId;

    public Incorporations() {
    }

    public Incorporations(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getCodeFamille() {
        return codeFamille;
    }

    public void setCodeFamille(String codeFamille) {
        this.codeFamille = codeFamille;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLieuNaiss() {
        return lieuNaiss;
    }

    public void setLieuNaiss(String lieuNaiss) {
        this.lieuNaiss = lieuNaiss;
    }

    public Boolean getSituaMarital() {
        return situaMarital;
    }

    public void setSituaMarital(Boolean situaMarital) {
        this.situaMarital = situaMarital;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFctEmploye() {
        return fctEmploye;
    }

    public void setFctEmploye(String fctEmploye) {
        this.fctEmploye = fctEmploye;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Double getTauxCouverture() {
        return tauxCouverture;
    }

    public void setTauxCouverture(Double tauxCouverture) {
        this.tauxCouverture = tauxCouverture;
    }

    public Double getPlafond() {
        return plafond;
    }

    public void setPlafond(Double plafond) {
        this.plafond = plafond;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public Double getEncourConso() {
        return encourConso;
    }

    public void setEncourConso(Double encourConso) {
        this.encourConso = encourConso;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public Date getDateIncorporation() {
        return dateIncorporation;
    }

    public void setDateIncorporation(Date dateIncorporation) {
        this.dateIncorporation = dateIncorporation;
    }

    public Date getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public Exercices getExerciceId() {
        return exerciceId;
    }

    public void setExerciceId(Exercices exerciceId) {
        this.exerciceId = exerciceId;
    }

    public Polices getPoliceId() {
        return policeId;
    }

    public void setPoliceId(Polices policeId) {
        this.policeId = policeId;
    }

    public Succursales getSuccursaleId() {
        return succursaleId;
    }

    public void setSuccursaleId(Succursales succursaleId) {
        this.succursaleId = succursaleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Incorporations)) {
            return false;
        }
        Incorporations other = (Incorporations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Incorporations[ id=" + id + " ]";
    }
    
}
