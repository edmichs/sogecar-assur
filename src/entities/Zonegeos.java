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
@Table(name = "zonegeos", catalog = "sogecar", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zonegeos.findAll", query = "SELECT z FROM Zonegeos z")
    , @NamedQuery(name = "Zonegeos.findById", query = "SELECT z FROM Zonegeos z WHERE z.id = :id")
    , @NamedQuery(name = "Zonegeos.findByCode", query = "SELECT z FROM Zonegeos z WHERE z.code = :code")
    , @NamedQuery(name = "Zonegeos.findByLibelle", query = "SELECT z FROM Zonegeos z WHERE z.libelle = :libelle")})
public class Zonegeos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "code", length = 30)
    private String code;
    @Column(name = "libelle", length = 255)
    private String libelle;
    @OneToMany(mappedBy = "zonegeoId", fetch = FetchType.EAGER)
    private List<Decomptes> decomptesList;
    @OneToMany(mappedBy = "zonegeoId", fetch = FetchType.EAGER)
    private List<BaremePrestations> baremePrestationsList;

    public Zonegeos() {
    }

    public Zonegeos(Integer id) {
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
    public List<Decomptes> getDecomptesList() {
        return decomptesList;
    }

    public void setDecomptesList(List<Decomptes> decomptesList) {
        this.decomptesList = decomptesList;
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
        if (!(object instanceof Zonegeos)) {
            return false;
        }
        Zonegeos other = (Zonegeos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Zonegeos[ id=" + id + " ]";
    }
    
}
