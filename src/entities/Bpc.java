/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author EDY TCHOKOUANI
 */
@Entity
@Table(name = "bpc", catalog = "sogecar", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bpc.findAll", query = "SELECT b FROM Bpc b")
    , @NamedQuery(name = "Bpc.findById", query = "SELECT b FROM Bpc b WHERE b.id = :id")
    , @NamedQuery(name = "Bpc.findByNumero", query = "SELECT b FROM Bpc b WHERE b.numero = :numero")
    , @NamedQuery(name = "Bpc.findByFormationSanitaire", query = "SELECT b FROM Bpc b WHERE b.formationSanitaire = :formationSanitaire")
    , @NamedQuery(name = "Bpc.findByPlafondRemboursement", query = "SELECT b FROM Bpc b WHERE b.plafondRemboursement = :plafondRemboursement")
    , @NamedQuery(name = "Bpc.findByTauxCouverture", query = "SELECT b FROM Bpc b WHERE b.tauxCouverture = :tauxCouverture")})
public class Bpc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "numero", length = 255)
    private String numero;
    @Column(name = "formation_sanitaire", length = 255)
    private String formationSanitaire;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "plafond_remboursement", precision = 17, scale = 17)
    private Double plafondRemboursement;
    @Column(name = "taux_couverture", precision = 17, scale = 17)
    private Double tauxCouverture;
    @JoinColumn(name = "assure_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Assures assureId;
    @JoinColumn(name = "exercice_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Exercices exerciceId;
    @JoinColumn(name = "police_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Polices policeId;

    public Bpc() {
    }

    public Bpc(Integer id) {
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

    public String getFormationSanitaire() {
        return formationSanitaire;
    }

    public void setFormationSanitaire(String formationSanitaire) {
        this.formationSanitaire = formationSanitaire;
    }

    public Double getPlafondRemboursement() {
        return plafondRemboursement;
    }

    public void setPlafondRemboursement(Double plafondRemboursement) {
        this.plafondRemboursement = plafondRemboursement;
    }

    public Double getTauxCouverture() {
        return tauxCouverture;
    }

    public void setTauxCouverture(Double tauxCouverture) {
        this.tauxCouverture = tauxCouverture;
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
        if (!(object instanceof Bpc)) {
            return false;
        }
        Bpc other = (Bpc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Bpc[ id=" + id + " ]";
    }
    
}
