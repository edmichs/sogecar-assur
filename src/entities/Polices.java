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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
@Table(name = "polices", catalog = "sogecar", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Polices.findAll", query = "SELECT p FROM Polices p")
    , @NamedQuery(name = "Polices.findById", query = "SELECT p FROM Polices p WHERE p.id = :id")
    , @NamedQuery(name = "Polices.findByNumero", query = "SELECT p FROM Polices p WHERE p.numero = :numero")
    , @NamedQuery(name = "Polices.findByDateEffet", query = "SELECT p FROM Polices p WHERE p.dateEffet = :dateEffet")
    , @NamedQuery(name = "Polices.findByDateEcheance", query = "SELECT p FROM Polices p WHERE p.dateEcheance = :dateEcheance")
    , @NamedQuery(name = "Polices.findByPrimeTotal", query = "SELECT p FROM Polices p WHERE p.primeTotal = :primeTotal")
    , @NamedQuery(name = "Polices.findByAccessoires", query = "SELECT p FROM Polices p WHERE p.accessoires = :accessoires")
    , @NamedQuery(name = "Polices.findByPrimeNette", query = "SELECT p FROM Polices p WHERE p.primeNette = :primeNette")
    , @NamedQuery(name = "Polices.findByPlafondGaranti", query = "SELECT p FROM Polices p WHERE p.plafondGaranti = :plafondGaranti")
    , @NamedQuery(name = "Polices.findByDateSouscription", query = "SELECT p FROM Polices p WHERE p.dateSouscription = :dateSouscription")
    , @NamedQuery(name = "Polices.findByDateEmission", query = "SELECT p FROM Polices p WHERE p.dateEmission = :dateEmission")})
public class Polices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "numero", length = 255)
    private String numero;
    @Column(name = "date_effet")
    @Temporal(TemporalType.DATE)
    private Date dateEffet;
    @Column(name = "date_echeance")
    @Temporal(TemporalType.DATE)
    private Date dateEcheance;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prime_total", precision = 17, scale = 17)
    private Double primeTotal;
    @Column(name = "accessoires", length = 255)
    private String accessoires;
    @Column(name = "prime_nette", length = 255)
    private String primeNette;
    @Column(name = "plafond_garanti", length = 255)
    private String plafondGaranti;
    @Column(name = "date_souscription")
    @Temporal(TemporalType.DATE)
    private Date dateSouscription;
    @Column(name = "date_emission")
    @Temporal(TemporalType.DATE)
    private Date dateEmission;
    @ManyToMany(mappedBy = "policesList", fetch = FetchType.EAGER)
    private List<Etablissements> etablissementsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "policeId", fetch = FetchType.EAGER)
    private List<Assures> assuresList;
    @OneToMany(mappedBy = "policeId", fetch = FetchType.EAGER)
    private List<Decomptes> decomptesList;
    @JoinColumn(name = "exercice_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Exercices exerciceId;
    @JoinColumn(name = "succursale_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Succursales succursaleId;
    @OneToMany(mappedBy = "policeId", fetch = FetchType.EAGER)
    private List<Bpc> bpcList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "policeId", fetch = FetchType.EAGER)
    private List<Incorporations> incorporationsList;
    @OneToMany(mappedBy = "policeId", fetch = FetchType.EAGER)
    private List<BonusMalus> bonusMalusList;

    public Polices() {
    }

    public Polices(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDateEffet() {
        return dateEffet;
    }

    public void setDateEffet(Date dateEffet) {
        this.dateEffet = dateEffet;
    }

    public Date getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(Date dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public Double getPrimeTotal() {
        return primeTotal;
    }

    public void setPrimeTotal(Double primeTotal) {
        this.primeTotal = primeTotal;
    }

    public String getAccessoires() {
        return accessoires;
    }

    public void setAccessoires(String accessoires) {
        this.accessoires = accessoires;
    }

    public String getPrimeNette() {
        return primeNette;
    }

    public void setPrimeNette(String primeNette) {
        this.primeNette = primeNette;
    }

    public String getPlafondGaranti() {
        return plafondGaranti;
    }

    public void setPlafondGaranti(String plafondGaranti) {
        this.plafondGaranti = plafondGaranti;
    }

    public Date getDateSouscription() {
        return dateSouscription;
    }

    public void setDateSouscription(Date dateSouscription) {
        this.dateSouscription = dateSouscription;
    }

    public Date getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(Date dateEmission) {
        this.dateEmission = dateEmission;
    }

    @XmlTransient
    public List<Etablissements> getEtablissementsList() {
        return etablissementsList;
    }

    public void setEtablissementsList(List<Etablissements> etablissementsList) {
        this.etablissementsList = etablissementsList;
    }

    @XmlTransient
    public List<Assures> getAssuresList() {
        return assuresList;
    }

    public void setAssuresList(List<Assures> assuresList) {
        this.assuresList = assuresList;
    }

    @XmlTransient
    public List<Decomptes> getDecomptesList() {
        return decomptesList;
    }

    public void setDecomptesList(List<Decomptes> decomptesList) {
        this.decomptesList = decomptesList;
    }

    public Exercices getExerciceId() {
        return exerciceId;
    }

    public void setExerciceId(Exercices exerciceId) {
        this.exerciceId = exerciceId;
    }

    public Succursales getSuccursaleId() {
        return succursaleId;
    }

    public void setSuccursaleId(Succursales succursaleId) {
        this.succursaleId = succursaleId;
    }

    @XmlTransient
    public List<Bpc> getBpcList() {
        return bpcList;
    }

    public void setBpcList(List<Bpc> bpcList) {
        this.bpcList = bpcList;
    }

    @XmlTransient
    public List<Incorporations> getIncorporationsList() {
        return incorporationsList;
    }

    public void setIncorporationsList(List<Incorporations> incorporationsList) {
        this.incorporationsList = incorporationsList;
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
        if (!(object instanceof Polices)) {
            return false;
        }
        Polices other = (Polices) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Polices[ id=" + id + " ]";
    }
    
}
