/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author EDY TCHOKOUANI
 */
@Entity
@Table(name = "assures", catalog = "sogecar", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assures.findAll", query = "SELECT a FROM Assures a")
    , @NamedQuery(name = "Assures.findById", query = "SELECT a FROM Assures a WHERE a.id = :id")
    , @NamedQuery(name = "Assures.findByMatricule", query = "SELECT a FROM Assures a WHERE a.matricule = :matricule")
    , @NamedQuery(name = "Assures.findByCodeFamille", query = "SELECT a FROM Assures a WHERE a.codeFamille = :codeFamille")
    , @NamedQuery(name = "Assures.findByNom", query = "SELECT a FROM Assures a WHERE a.nom = :nom")
    , @NamedQuery(name = "Assures.findByAvatar", query = "SELECT a FROM Assures a WHERE a.avatar = :avatar")
    , @NamedQuery(name = "Assures.findByLieuNaiss", query = "SELECT a FROM Assures a WHERE a.lieuNaiss = :lieuNaiss")
    , @NamedQuery(name = "Assures.findBySituaMarital", query = "SELECT a FROM Assures a WHERE a.situaMarital = :situaMarital")
    , @NamedQuery(name = "Assures.findByType", query = "SELECT a FROM Assures a WHERE a.type = :type")
    , @NamedQuery(name = "Assures.findByFctEmploye", query = "SELECT a FROM Assures a WHERE a.fctEmploye = :fctEmploye")
    , @NamedQuery(name = "Assures.findByObservation", query = "SELECT a FROM Assures a WHERE a.observation = :observation")
    , @NamedQuery(name = "Assures.findByTauxCouverture", query = "SELECT a FROM Assures a WHERE a.tauxCouverture = :tauxCouverture")
    , @NamedQuery(name = "Assures.findByPlafond", query = "SELECT a FROM Assures a WHERE a.plafond = :plafond")
    , @NamedQuery(name = "Assures.findBySolde", query = "SELECT a FROM Assures a WHERE a.solde = :solde")
    , @NamedQuery(name = "Assures.findByEncourConso", query = "SELECT a FROM Assures a WHERE a.encourConso = :encourConso")
    , @NamedQuery(name = "Assures.findByNationalite", query = "SELECT a FROM Assures a WHERE a.nationalite = :nationalite")
    , @NamedQuery(name = "Assures.findByDateIncorporation", query = "SELECT a FROM Assures a WHERE a.dateIncorporation = :dateIncorporation")
    , @NamedQuery(name = "Assures.findByDateNaiss", query = "SELECT a FROM Assures a WHERE a.dateNaiss = :dateNaiss")})
public class Assures implements Serializable {

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
    @OneToMany(mappedBy = "assureId", fetch = FetchType.EAGER)
    private List<Decomptes> decomptesList;
    @OneToMany(mappedBy = "assureId", fetch = FetchType.EAGER)
    private List<Bpc> bpcList;
    @OneToMany(mappedBy = "assureId", fetch = FetchType.EAGER)
    private List<BonusMalus> bonusMalusList;

    public Assures() {
    }

    public Assures(Integer id) {
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

    @XmlTransient
    public List<Decomptes> getDecomptesList() {
        return decomptesList;
    }

    public void setDecomptesList(List<Decomptes> decomptesList) {
        this.decomptesList = decomptesList;
    }

    @XmlTransient
    public List<Bpc> getBpcList() {
        return bpcList;
    }

    public void setBpcList(List<Bpc> bpcList) {
        this.bpcList = bpcList;
    }

    @XmlTransient
    public List<BonusMalus> getBonusMalusList() {
        return bonusMalusList;
    }

    public void setBonusMalusList(List<BonusMalus> bonusMalusList) {
        this.bonusMalusList = bonusMalusList;
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
        if (!(object instanceof Assures)) {
            return false;
        }
        Assures other = (Assures) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Assures[ id=" + id + " ]";
    }
    
}
