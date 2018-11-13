/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author EDY TCHOKOUANI
 */
@Entity
@Table(name = "categorie_prestations", catalog = "sogecar", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriePrestations.findAll", query = "SELECT c FROM CategoriePrestations c")
    , @NamedQuery(name = "CategoriePrestations.findById", query = "SELECT c FROM CategoriePrestations c WHERE c.id = :id")
    , @NamedQuery(name = "CategoriePrestations.findByCode", query = "SELECT c FROM CategoriePrestations c WHERE c.code = :code")
    , @NamedQuery(name = "CategoriePrestations.findByLibelle", query = "SELECT c FROM CategoriePrestations c WHERE c.libelle = :libelle")})
public class CategoriePrestations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "code", length = 60)
    private String code;
    @Column(name = "libelle", length = 255)
    private String libelle;
    @OneToMany(mappedBy = "categoriePrestationId", fetch = FetchType.EAGER)
    private List<BaremePrestations> baremePrestationsList;

    public CategoriePrestations() {
    }

    public CategoriePrestations(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @XmlTransient
    public List<BaremePrestations> getBaremePrestationsList() {
        return baremePrestationsList;
    }

    public void setBaremePrestationsList(List<BaremePrestations> baremePrestationsList) {
        this.baremePrestationsList = baremePrestationsList;
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
        if (!(object instanceof CategoriePrestations)) {
            return false;
        }
        CategoriePrestations other = (CategoriePrestations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CategoriePrestations[ id=" + id + " ]";
    }
    
}
