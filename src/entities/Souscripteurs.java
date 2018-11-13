/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author EDY TCHOKOUANI
 */
@Entity
@Table(name = "souscripteurs", catalog = "sogecar", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Souscripteurs.findAll", query = "SELECT s FROM Souscripteurs s")
    , @NamedQuery(name = "Souscripteurs.findById", query = "SELECT s FROM Souscripteurs s WHERE s.id = :id")
    , @NamedQuery(name = "Souscripteurs.findByStatus", query = "SELECT s FROM Souscripteurs s WHERE s.status = :status")
    , @NamedQuery(name = "Souscripteurs.findByNom", query = "SELECT s FROM Souscripteurs s WHERE s.nom = :nom")
    , @NamedQuery(name = "Souscripteurs.findByRaisonSocial", query = "SELECT s FROM Souscripteurs s WHERE s.raisonSocial = :raisonSocial")
    , @NamedQuery(name = "Souscripteurs.findByActivite", query = "SELECT s FROM Souscripteurs s WHERE s.activite = :activite")
    , @NamedQuery(name = "Souscripteurs.findByAdresse", query = "SELECT s FROM Souscripteurs s WHERE s.adresse = :adresse")
    , @NamedQuery(name = "Souscripteurs.findByTelephone", query = "SELECT s FROM Souscripteurs s WHERE s.telephone = :telephone")
    , @NamedQuery(name = "Souscripteurs.findByEmail", query = "SELECT s FROM Souscripteurs s WHERE s.email = :email")
    , @NamedQuery(name = "Souscripteurs.findByNomContact", query = "SELECT s FROM Souscripteurs s WHERE s.nomContact = :nomContact")
    , @NamedQuery(name = "Souscripteurs.findByLocalisationGeo", query = "SELECT s FROM Souscripteurs s WHERE s.localisationGeo = :localisationGeo")
    , @NamedQuery(name = "Souscripteurs.findByNombreTotalAssure", query = "SELECT s FROM Souscripteurs s WHERE s.nombreTotalAssure = :nombreTotalAssure")})
public class Souscripteurs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "status")
    private Integer status;
    @Column(name = "nom", length = 255)
    private String nom;
    @Column(name = "raison_social", length = 255)
    private String raisonSocial;
    @Column(name = "activite", length = 255)
    private String activite;
    @Column(name = "adresse", length = 255)
    private String adresse;
    @Column(name = "telephone", length = 255)
    private String telephone;
    @Column(name = "email", length = 255)
    private String email;
    @Column(name = "nom_contact", length = 255)
    private String nomContact;
    @Column(name = "localisation_geo", length = 255)
    private String localisationGeo;
    @Column(name = "nombre_total_assure")
    private Integer nombreTotalAssure;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "souscripteurId", fetch = FetchType.EAGER)
    private List<Succursales> succursalesList;

    public Souscripteurs() {
    }

    public Souscripteurs(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
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

    public String getLocalisationGeo() {
        return localisationGeo;
    }

    public void setLocalisationGeo(String localisationGeo) {
        this.localisationGeo = localisationGeo;
    }

    public Integer getNombreTotalAssure() {
        return nombreTotalAssure;
    }

    public void setNombreTotalAssure(Integer nombreTotalAssure) {
        this.nombreTotalAssure = nombreTotalAssure;
    }

    @XmlTransient
    public List<Succursales> getSuccursalesList() {
        return succursalesList;
    }

    public void setSuccursalesList(List<Succursales> succursalesList) {
        this.succursalesList = succursalesList;
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
        if (!(object instanceof Souscripteurs)) {
            return false;
        }
        Souscripteurs other = (Souscripteurs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Souscripteurs[ id=" + id + " ]";
    }
    
}
