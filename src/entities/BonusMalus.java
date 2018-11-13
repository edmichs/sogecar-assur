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
@Table(name = "bonus_malus", catalog = "sogecar", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BonusMalus.findAll", query = "SELECT b FROM BonusMalus b")
    , @NamedQuery(name = "BonusMalus.findById", query = "SELECT b FROM BonusMalus b WHERE b.id = :id")
    , @NamedQuery(name = "BonusMalus.findByDateEvenement", query = "SELECT b FROM BonusMalus b WHERE b.dateEvenement = :dateEvenement")
    , @NamedQuery(name = "BonusMalus.findByMontantMalus", query = "SELECT b FROM BonusMalus b WHERE b.montantMalus = :montantMalus")
    , @NamedQuery(name = "BonusMalus.findByMontantBonus", query = "SELECT b FROM BonusMalus b WHERE b.montantBonus = :montantBonus")})
public class BonusMalus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "date_evenement")
    @Temporal(TemporalType.DATE)
    private Date dateEvenement;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montant_malus", precision = 17, scale = 17)
    private Double montantMalus;
    @Column(name = "montant_bonus", precision = 17, scale = 17)
    private Double montantBonus;
    @JoinColumn(name = "assure_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Assures assureId;
    @JoinColumn(name = "exercice_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Exercices exerciceId;
    @JoinColumn(name = "police_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Polices policeId;

    public BonusMalus() {
    }

    public BonusMalus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public Double getMontantMalus() {
        return montantMalus;
    }

    public void setMontantMalus(Double montantMalus) {
        this.montantMalus = montantMalus;
    }

    public Double getMontantBonus() {
        return montantBonus;
    }

    public void setMontantBonus(Double montantBonus) {
        this.montantBonus = montantBonus;
    }

    public Assures getAssureId() {
        return assureId;
    }

    public void setAssureId(Assures assureId) {
        this.assureId = assureId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BonusMalus)) {
            return false;
        }
        BonusMalus other = (BonusMalus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.BonusMalus[ id=" + id + " ]";
    }
    
}
