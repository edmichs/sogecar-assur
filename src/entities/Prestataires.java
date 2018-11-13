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
@Table(name = "prestataires", catalog = "sogecar", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestataires.findAll", query = "SELECT p FROM Prestataires p")
    , @NamedQuery(name = "Prestataires.findById", query = "SELECT p FROM Prestataires p WHERE p.id = :id")
    , @NamedQuery(name = "Prestataires.findByNom", query = "SELECT p FROM Prestataires p WHERE p.nom = :nom")
    , @NamedQuery(name = "Prestataires.findByAdresse", query = "SELECT p FROM Prestataires p WHERE p.adresse = :adresse")
    , @NamedQuery(name = "Prestataires.findByTelephone", query = "SELECT p FROM Prestataires p WHERE p.telephone = :telephone")
    , @NamedQuery(name = "Prestataires.findByEmail", query = "SELECT p FROM Prestataires p WHERE p.email = :email")
    , @NamedQuery(name = "Prestataires.findByNomContact", query = "SELECT p FROM Prestataires p WHERE p.nomContact = :nomContact")
    , @NamedQuery(name = "Prestataires.findByVille", query = "SELECT p FROM Prestataires p WHERE p.ville = :ville")
    , @NamedQuery(name = "Prestataires.findByQuartier", query = "SELECT p FROM Prestataires p WHERE p.quartier = :quartier")
    , @NamedQuery(name = "Prestataires.findByPays", query = "SELECT p FROM Prestataires p WHERE p.pays = :pays")})
public class Prestataires implements Serializable {

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
    @Column(name = "telephone", length = 255)
    private String telephone;
    @Column(name = "email", length = 255)
    private String email;
    @Column(name = "nom_contact", length = 255)
    private String nomContact;
    @Column(name = "ville", length = 255)
    private String ville;
    @Column(name = "quartier", length = 255)
    private String quartier;
    @Column(name = "pays", length = 255)
    private String pays;
    @OneToMany(mappedBy = "prestataireId", fetch = FetchType.EAGER)
    private List<BaremePrestations> baremePrestationsList;

    public Prestataires() {
    }

    public Prestataires(Integer id) {
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

    public String getNomContact() {
        return nomContact;
    }

    public void setNomContact(String nomContact) {
        this.nomContact = nomContact;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
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
        if (!(object instanceof Prestataires)) {
            return false;
        }
        Prestataires other = (Prestataires) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Prestataires[ id=" + id + " ]";
    }
    
}
