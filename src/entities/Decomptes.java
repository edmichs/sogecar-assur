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
@Table(name = "decomptes", catalog = "sogecar", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Decomptes.findAll", query = "SELECT d FROM Decomptes d")
    , @NamedQuery(name = "Decomptes.findById", query = "SELECT d FROM Decomptes d WHERE d.id = :id")
    , @NamedQuery(name = "Decomptes.findByNumero", query = "SELECT d FROM Decomptes d WHERE d.numero = :numero")
    , @NamedQuery(name = "Decomptes.findByNombrePieces", query = "SELECT d FROM Decomptes d WHERE d.nombrePieces = :nombrePieces")
    , @NamedQuery(name = "Decomptes.findByDateJour", query = "SELECT d FROM Decomptes d WHERE d.dateJour = :dateJour")
    , @NamedQuery(name = "Decomptes.findByNumeroFacture", query = "SELECT d FROM Decomptes d WHERE d.numeroFacture = :numeroFacture")
    , @NamedQuery(name = "Decomptes.findByBeneficiare", query = "SELECT d FROM Decomptes d WHERE d.beneficiare = :beneficiare")
    , @NamedQuery(name = "Decomptes.findByDateDeclaration", query = "SELECT d FROM Decomptes d WHERE d.dateDeclaration = :dateDeclaration")
    , @NamedQuery(name = "Decomptes.findByDateSurvenance", query = "SELECT d FROM Decomptes d WHERE d.dateSurvenance = :dateSurvenance")
    , @NamedQuery(name = "Decomptes.findByTauxRemboursement", query = "SELECT d FROM Decomptes d WHERE d.tauxRemboursement = :tauxRemboursement")
    , @NamedQuery(name = "Decomptes.findByTerritoire", query = "SELECT d FROM Decomptes d WHERE d.territoire = :territoire")
    , @NamedQuery(name = "Decomptes.findByTypeSoins", query = "SELECT d FROM Decomptes d WHERE d.typeSoins = :typeSoins")
    , @NamedQuery(name = "Decomptes.findByRejet", query = "SELECT d FROM Decomptes d WHERE d.rejet = :rejet")
    , @NamedQuery(name = "Decomptes.findByMotifRejet", query = "SELECT d FROM Decomptes d WHERE d.motifRejet = :motifRejet")
    , @NamedQuery(name = "Decomptes.findByNomMedecin", query = "SELECT d FROM Decomptes d WHERE d.nomMedecin = :nomMedecin")
    , @NamedQuery(name = "Decomptes.findByMontantFacture", query = "SELECT d FROM Decomptes d WHERE d.montantFacture = :montantFacture")
    , @NamedQuery(name = "Decomptes.findByQuantite", query = "SELECT d FROM Decomptes d WHERE d.quantite = :quantite")
    , @NamedQuery(name = "Decomptes.findByCategoriePrestationId", query = "SELECT d FROM Decomptes d WHERE d.categoriePrestationId = :categoriePrestationId")
    , @NamedQuery(name = "Decomptes.findByCodeCategorieSoins", query = "SELECT d FROM Decomptes d WHERE d.codeCategorieSoins = :codeCategorieSoins")
    , @NamedQuery(name = "Decomptes.findByMontantUnit", query = "SELECT d FROM Decomptes d WHERE d.montantUnit = :montantUnit")
    , @NamedQuery(name = "Decomptes.findByMontantTotal", query = "SELECT d FROM Decomptes d WHERE d.montantTotal = :montantTotal")
    , @NamedQuery(name = "Decomptes.findByMontantRetenu", query = "SELECT d FROM Decomptes d WHERE d.montantRetenu = :montantRetenu")
    , @NamedQuery(name = "Decomptes.findByMontantPayer", query = "SELECT d FROM Decomptes d WHERE d.montantPayer = :montantPayer")
    , @NamedQuery(name = "Decomptes.findByModePayement", query = "SELECT d FROM Decomptes d WHERE d.modePayement = :modePayement")
    , @NamedQuery(name = "Decomptes.findByReferencePaiement", query = "SELECT d FROM Decomptes d WHERE d.referencePaiement = :referencePaiement")})
public class Decomptes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "numero", length = 255)
    private String numero;
    @Column(name = "nombre_pieces")
    private Integer nombrePieces;
    @Column(name = "date_jour")
    @Temporal(TemporalType.DATE)
    private Date dateJour;
    @Column(name = "numero_facture", length = 255)
    private String numeroFacture;
    @Column(name = "beneficiare", length = 255)
    private String beneficiare;
    @Column(name = "date_declaration")
    @Temporal(TemporalType.DATE)
    private Date dateDeclaration;
    @Column(name = "date_survenance")
    @Temporal(TemporalType.DATE)
    private Date dateSurvenance;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "taux_remboursement", precision = 17, scale = 17)
    private Double tauxRemboursement;
    @Column(name = "territoire", length = 255)
    private String territoire;
    @Column(name = "type_soins", length = 60)
    private String typeSoins;
    @Column(name = "rejet")
    private Boolean rejet;
    @Column(name = "motif_rejet", length = 255)
    private String motifRejet;
    @Column(name = "nom_medecin", length = 255)
    private String nomMedecin;
    @Column(name = "montant_facture", precision = 17, scale = 17)
    private Double montantFacture;
    @Column(name = "quantite", precision = 17, scale = 17)
    private Double quantite;
    @Column(name = "categorie_prestation_id")
    private Integer categoriePrestationId;
    @Column(name = "code_categorie_soins", length = 255)
    private String codeCategorieSoins;
    @Column(name = "montant_unit", precision = 17, scale = 17)
    private Double montantUnit;
    @Column(name = "montant_total", precision = 17, scale = 17)
    private Double montantTotal;
    @Column(name = "montant_retenu", precision = 17, scale = 17)
    private Double montantRetenu;
    @Column(name = "montant_payer", precision = 17, scale = 17)
    private Double montantPayer;
    @Column(name = "mode_payement")
    private Integer modePayement;
    @Column(name = "reference_paiement", length = 255)
    private String referencePaiement;
    @JoinColumn(name = "assure_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Assures assureId;
    @JoinColumn(name = "exercice_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Exercices exerciceId;
    @JoinColumn(name = "police_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Polices policeId;
    @JoinColumn(name = "succursale_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Succursales succursaleId;
    @JoinColumn(name = "zonegeo_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Zonegeos zonegeoId;

    public Decomptes() {
    }

    public Decomptes(Integer id) {
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

    public Integer getNombrePieces() {
        return nombrePieces;
    }

    public void setNombrePieces(Integer nombrePieces) {
        this.nombrePieces = nombrePieces;
    }

    public Date getDateJour() {
        return dateJour;
    }

    public void setDateJour(Date dateJour) {
        this.dateJour = dateJour;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public String getBeneficiare() {
        return beneficiare;
    }

    public void setBeneficiare(String beneficiare) {
        this.beneficiare = beneficiare;
    }

    public Date getDateDeclaration() {
        return dateDeclaration;
    }

    public void setDateDeclaration(Date dateDeclaration) {
        this.dateDeclaration = dateDeclaration;
    }

    public Date getDateSurvenance() {
        return dateSurvenance;
    }

    public void setDateSurvenance(Date dateSurvenance) {
        this.dateSurvenance = dateSurvenance;
    }

    public Double getTauxRemboursement() {
        return tauxRemboursement;
    }

    public void setTauxRemboursement(Double tauxRemboursement) {
        this.tauxRemboursement = tauxRemboursement;
    }

    public String getTerritoire() {
        return territoire;
    }

    public void setTerritoire(String territoire) {
        this.territoire = territoire;
    }

    public String getTypeSoins() {
        return typeSoins;
    }

    public void setTypeSoins(String typeSoins) {
        this.typeSoins = typeSoins;
    }

    public Boolean getRejet() {
        return rejet;
    }

    public void setRejet(Boolean rejet) {
        this.rejet = rejet;
    }

    public String getMotifRejet() {
        return motifRejet;
    }

    public void setMotifRejet(String motifRejet) {
        this.motifRejet = motifRejet;
    }

    public String getNomMedecin() {
        return nomMedecin;
    }

    public void setNomMedecin(String nomMedecin) {
        this.nomMedecin = nomMedecin;
    }

    public Double getMontantFacture() {
        return montantFacture;
    }

    public void setMontantFacture(Double montantFacture) {
        this.montantFacture = montantFacture;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Integer getCategoriePrestationId() {
        return categoriePrestationId;
    }

    public void setCategoriePrestationId(Integer categoriePrestationId) {
        this.categoriePrestationId = categoriePrestationId;
    }

    public String getCodeCategorieSoins() {
        return codeCategorieSoins;
    }

    public void setCodeCategorieSoins(String codeCategorieSoins) {
        this.codeCategorieSoins = codeCategorieSoins;
    }

    public Double getMontantUnit() {
        return montantUnit;
    }

    public void setMontantUnit(Double montantUnit) {
        this.montantUnit = montantUnit;
    }

    public Double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(Double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public Double getMontantRetenu() {
        return montantRetenu;
    }

    public void setMontantRetenu(Double montantRetenu) {
        this.montantRetenu = montantRetenu;
    }

    public Double getMontantPayer() {
        return montantPayer;
    }

    public void setMontantPayer(Double montantPayer) {
        this.montantPayer = montantPayer;
    }

    public Integer getModePayement() {
        return modePayement;
    }

    public void setModePayement(Integer modePayement) {
        this.modePayement = modePayement;
    }

    public String getReferencePaiement() {
        return referencePaiement;
    }

    public void setReferencePaiement(String referencePaiement) {
        this.referencePaiement = referencePaiement;
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

    public Succursales getSuccursaleId() {
        return succursaleId;
    }

    public void setSuccursaleId(Succursales succursaleId) {
        this.succursaleId = succursaleId;
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
        if (!(object instanceof Decomptes)) {
            return false;
        }
        Decomptes other = (Decomptes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Decomptes[ id=" + id + " ]";
    }
    
}
