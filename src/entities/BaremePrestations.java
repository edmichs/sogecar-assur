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
@Table(name = "bareme_prestations", catalog = "sogecar", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BaremePrestations.findAll", query = "SELECT b FROM BaremePrestations b")
    , @NamedQuery(name = "BaremePrestations.findById", query = "SELECT b FROM BaremePrestations b WHERE b.id = :id")
    , @NamedQuery(name = "BaremePrestations.findByLibelle", query = "SELECT b FROM BaremePrestations b WHERE b.libelle = :libelle")
    , @NamedQuery(name = "BaremePrestations.findByBaseRemboursement", query = "SELECT b FROM BaremePrestations b WHERE b.baseRemboursement = :baseRemboursement")
    , @NamedQuery(name = "BaremePrestations.findByTauxRemboursement", query = "SELECT b FROM BaremePrestations b WHERE b.tauxRemboursement = :tauxRemboursement")
    , @NamedQuery(name = "BaremePrestations.findByPoliceId", query = "SELECT b FROM BaremePrestations b WHERE b.policeId = :policeId")})
public class BaremePrestations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "libelle", length = 255)
    private String libelle;
    @Column(name = "base_remboursement", length = 255)
    private String baseRemboursement;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "taux_remboursement", precision = 17, scale = 17)
    private Double tauxRemboursement;
    @Column(name = "police_id")
    private Integer policeId;
    @JoinColumn(name = "categorie_prestation_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private CategoriePrestations categoriePrestationId;
    @JoinColumn(name = "prestataire_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Prestataires prestataireId;
    @JoinColumn(name = "type_employe_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private TypeEmploye typeEmployeId;
    @JoinColumn(name = "zonegeo_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Zonegeos zonegeoId;

    public BaremePrestations() {
    }

    public BaremePrestations(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getBaseRemboursement() {
        return baseRemboursement;
    }

    public void setBaseRemboursement(String baseRemboursement) {
        this.baseRemboursement = baseRemboursement;
    }

    public Double getTauxRemboursement() {
        return tauxRemboursement;
    }

    public void setTauxRemboursement(Double tauxRemboursement) {
        this.tauxRemboursement = tauxRemboursement;
    }

    public Integer getPoliceId() {
        return policeId;
    }

    public void setPoliceId(Integer policeId) {
        this.policeId = policeId;
    }

    public CategoriePrestations getCategoriePrestationId() {
        return categoriePrestationId;
    }

    public void setCategoriePrestationId(CategoriePrestations categoriePrestationId) {
        this.categoriePrestationId = categoriePrestationId;
    }

    public Prestataires getPrestataireId() {
        return prestataireId;
    }

    public void setPrestataireId(Prestataires prestataireId) {
        this.prestataireId = prestataireId;
    }

    public TypeEmploye getTypeEmployeId() {
        return typeEmployeId;
    }

    public void setTypeEmployeId(TypeEmploye typeEmployeId) {
        this.typeEmployeId = typeEmployeId;
    }

    public Zonegeos getZonegeoId() {
        return zonegeoId;
    }

    public void setZonegeoId(Zonegeos zonegeoId) {
        this.zonegeoId = zonegeoId;
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
        if (!(object instanceof BaremePrestations)) {
            return false;
        }
        BaremePrestations other = (BaremePrestations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.BaremePrestations[ id=" + id + " ]";
    }
    
}
