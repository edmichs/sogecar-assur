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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author EDY TCHOKOUANI
 */
@Entity
@Table(name = "etablissements", catalog = "sogecar", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etablissements.findAll", query = "SELECT e FROM Etablissements e")
    , @NamedQuery(name = "Etablissements.findById", query = "SELECT e FROM Etablissements e WHERE e.id = :id")
    , @NamedQuery(name = "Etablissements.findByNom", query = "SELECT e FROM Etablissements e WHERE e.nom = :nom")
    , @NamedQuery(name = "Etablissements.findByAdresse", query = "SELECT e FROM Etablissements e WHERE e.adresse = :adresse")
    , @NamedQuery(name = "Etablissements.findByEmail", query = "SELECT e FROM Etablissements e WHERE e.email = :email")
    , @NamedQuery(name = "Etablissements.findByTelephone", query = "SELECT e FROM Etablissements e WHERE e.telephone = :telephone")
    , @NamedQuery(name = "Etablissements.findByNomContact", query = "SELECT e FROM Etablissements e WHERE e.nomContact = :nomContact")
    , @NamedQuery(name = "Etablissements.findByTelephoneContact", query = "SELECT e FROM Etablissements e WHERE e.telephoneContact = :telephoneContact")})
public class Etablissements implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "nom", length = 255)
    private String nom;
    @Column(name = "adresse", length = 255)
    private String adresse;
    @Column(name = "email", length = 255)
    private String email;
    @Column(name = "telephone", length = 255)
    private String telephone;
    @Column(name = "nom_contact", length = 255)
    private String nomContact;
    @Column(name = "telephone_contact", length = 255)
    private String telephoneContact;
    @JoinTable(name = "police_etablissements", joinColumns = {
        @JoinColumn(name = "etablissement_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "police_id", referencedColumnName = "id", nullable = false)})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Polices> policesList;

    public Etablissements() {
    }

    public Etablissements(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNomContact() {
        return nomContact;
    }

    public void setNomContact(String nomContact) {
        this.nomContact = nomContact;
    }

    public String getTelephoneContact() {
        return telephoneContact;
    }

    public void setTelephoneContact(String telephoneContact) {
        this.telephoneContact = telephoneContact;
    }

    @XmlTransient
    public List<Polices> getPolicesList() {
        return policesList;
    }

    public void setPolicesList(List<Polices> policesList) {
        this.policesList = policesList;
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
        if (!(object instanceof Etablissements)) {
            return false;
        }
        Etablissements other = (Etablissements) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Etablissements[ id=" + id + " ]";
    }
    
}
