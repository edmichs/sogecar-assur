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
@Table(name = "exercices", catalog = "sogecar", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exercices.findAll", query = "SELECT e FROM Exercices e")
    , @NamedQuery(name = "Exercices.findById", query = "SELECT e FROM Exercices e WHERE e.id = :id")
    , @NamedQuery(name = "Exercices.findByDateDebut", query = "SELECT e FROM Exercices e WHERE e.dateDebut = :dateDebut")
    , @NamedQuery(name = "Exercices.findByDateFin", query = "SELECT e FROM Exercices e WHERE e.dateFin = :dateFin")
    , @NamedQuery(name = "Exercices.findByCloture", query = "SELECT e FROM Exercices e WHERE e.cloture = :cloture")
    , @NamedQuery(name = "Exercices.findByDateCloture", query = "SELECT e FROM Exercices e WHERE e.dateCloture = :dateCloture")})
public class Exercices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "date_debut")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Column(name = "date_fin")
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    @Column(name = "cloture")
    private Boolean cloture;
    @Column(name = "date_cloture")
    @Temporal(TemporalType.DATE)
    private Date dateCloture;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exerciceId", fetch = FetchType.EAGER)
    private List<Assures> assuresList;
    @OneToMany(mappedBy = "exerciceId", fetch = FetchType.EAGER)
    private List<Decomptes> decomptesList;
    @OneToMany(mappedBy = "exerciceId", fetch = FetchType.EAGER)
    private List<Polices> policesList;
    @OneToMany(mappedBy = "exerciceId", fetch = FetchType.EAGER)
    private List<Bpc> bpcList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exerciceId", fetch = FetchType.EAGER)
    private List<Incorporations> incorporationsList;
    @OneToMany(mappedBy = "exerciceId", fetch = FetchType.EAGER)
    private List<BonusMalus> bonusMalusList;

    public Exercices() {
    }

    public Exercices(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Boolean getCloture() {
        return cloture;
    }

    public void setCloture(Boolean cloture) {
        this.cloture = cloture;
    }

    public Date getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(Date dateCloture) {
        this.dateCloture = dateCloture;
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

    @XmlTransient
    public List<Polices> getPolicesList() {
        return policesList;
    }

    public void setPolicesList(List<Polices> policesList) {
        this.policesList = policesList;
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
        if (!(object instanceof Exercices)) {
            return false;
        }
        Exercices other = (Exercices) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Exercices[ id=" + id + " ]";
    }
    
}
