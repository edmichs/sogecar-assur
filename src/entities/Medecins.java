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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author EDY TCHOKOUANI
 */
@Entity
@Table(name = "medecins", catalog = "sogecar", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medecins.findAll", query = "SELECT m FROM Medecins m")
    , @NamedQuery(name = "Medecins.findById", query = "SELECT m FROM Medecins m WHERE m.id = :id")
    , @NamedQuery(name = "Medecins.findByTelephone", query = "SELECT m FROM Medecins m WHERE m.telephone = :telephone")
    , @NamedQuery(name = "Medecins.findByEmail", query = "SELECT m FROM Medecins m WHERE m.email = :email")})
public class Medecins implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "telephone", length = 255)
    private String telephone;
    @Column(name = "email", length = 255)
    private String email;

    public Medecins() {
    }

    public Medecins(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (!(object instanceof Medecins)) {
            return false;
        }
        Medecins other = (Medecins) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Medecins[ id=" + id + " ]";
    }
    
}
