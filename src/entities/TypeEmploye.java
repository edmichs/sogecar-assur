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
@Table(name = "type_employe", catalog = "sogecar", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeEmploye.findAll", query = "SELECT t FROM TypeEmploye t")
    , @NamedQuery(name = "TypeEmploye.findById", query = "SELECT t FROM TypeEmploye t WHERE t.id = :id")
    , @NamedQuery(name = "TypeEmploye.findByLibelle", query = "SELECT t FROM TypeEmploye t WHERE t.libelle = :libelle")})
public class TypeEmploye implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "libelle", length = 255)
    private String libelle;
    @OneToMany(mappedBy = "typeEmployeId", fetch = FetchType.EAGER)
    private List<BaremePrestations> baremePrestationsList;

    public TypeEmploye() {
    }

    public TypeEmploye(Integer id) {
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
        if (!(object instanceof TypeEmploye)) {
            return false;
        }
        TypeEmploye other = (TypeEmploye) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TypeEmploye[ id=" + id + " ]";
    }
    
}
