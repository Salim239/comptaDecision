package com.growup.comptadecision.service.dto;

import com.growup.comptadecision.domain.enumeration.CategorieActivite;
import com.growup.comptadecision.domain.enumeration.CategorieClient;
import com.growup.comptadecision.domain.enumeration.CodeTVA;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Lob;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the FicheClient entity.
 */
//@Data
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@ToString
public class FicheClientDTO implements Serializable {

    private Long id;

    private CategorieClient categorieClient;

    @NotNull
    private String designation;

    @Lob
    private byte[] logo;

    private String logoContentType;
    @NotNull
    private String adresse;

    private String email;

    @NotNull
    private String codePostal;

    private String telephone;

    private String fax;

    @NotNull
    private String matriculeFiscale;

    private String registreCommerce;

    private LocalDate dateCreation;

    private String cnssEmployeur;

    private String cnssGerant;

    private Long categorieCnssGerantId;

    private String categorieCnssGerantLibelle;

    @Lob
    private byte[] fichierPatente;

    private String fichierPatenteContentType;

    private Long secteurActivite1Id;

    private String secteurActivite1Libelle;

    private Long secteurActivite2Id;

    private String secteurActivite2Libelle;

    private Long secteurActivite3Id;

    private String secteurActivite3Libelle;

    private Long activite1Id;

    private String activite1Libelle;

    private Long activite2Id;

    private String activite2Libelle;

    private Long activite3Id;

    private String activite3Libelle;

    private Long regionId;

    private String regionLibelle;

    private Long villeId;

    private String villeLibelle;

    /**
     * Utilisé dans le matricule
     */
    private String numeroEtablissementSecondaire = "000";

    /**
     * Utilisé dans le matricule
     */
    private CategorieActivite categorieActivite;

    /**
     * Utilisé dans le matricule
     */
    private CodeTVA codeTva;

    @Min(value = 0)
    private Double tauxCnssAccident;

    private CentreAdministratifDTO administrationCnss;

    private CentreAdministratifDTO administrationFiscale;

    private CentreAdministratifDTO administrationImpot;

    private String prenomGerant;

    private String nomGerant;

    private LocalDate dateNaissanceGerant;


    private String lieuNaissanceGerant;

    private String lieuDelivranceCINGerant;

    private String cinGerant;

    private LocalDate dateDelivranceCINGerant;

    private String adresseGerant;

    private String telephoneGerant1;

    private String telephoneGerant2;

    private String emailGerant;

    @Lob
    private byte[] copieCINGerant;

    private String copieCINGerantContentType;

    private String telephone2;

    private String telephone3;

    private String email2;

    private String email3;

    private Long cabinetComptableCode;

    private Double tauxCnssNormal;

    private Double tauxCnssKarama;

    private String etiquettes;

    private BigDecimal montantFraisCabinet;

    private Integer nombreMoisFraisCabinet;


    public void setTauxCnssAccident(Double tauxCnssAccident) {
        this.tauxCnssAccident = tauxCnssAccident;
    }

    public CentreAdministratifDTO getAdministrationCnss() {
        return administrationCnss;
    }

    public void setAdministrationCnss(CentreAdministratifDTO administrationCnss) {
        this.administrationCnss = administrationCnss;
    }

    public CentreAdministratifDTO getAdministrationFiscale() {
        return administrationFiscale;
    }

    public void setAdministrationFiscale(CentreAdministratifDTO administrationFiscale) {
        this.administrationFiscale = administrationFiscale;
    }

    public CentreAdministratifDTO getAdministrationImpot() {
        return administrationImpot;
    }

    public void setAdministrationImpot(CentreAdministratifDTO administrationImpot) {
        this.administrationImpot = administrationImpot;
    }

    public String getRegionLibelle() {
        return regionLibelle;
    }

    public void setRegionLibelle(String regionLibelle) {
        this.regionLibelle = regionLibelle;
    }

    public String getVilleLibelle() {
        return villeLibelle;
    }

    public void setVilleLibelle(String villeLibelle) {
        this.villeLibelle = villeLibelle;
    }

    private List<ImpotMensuelClientDTO> impotMensuelClients = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategorieClient getCategorieClient() {
        return categorieClient;
    }

    public void setCategorieClient(CategorieClient categorieClient) {
        this.categorieClient = categorieClient;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getLogoContentType() {
        return logoContentType;
    }

    public void setLogoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatriculeFiscale() {
        return matriculeFiscale;
    }

    public void setMatriculeFiscale(String matriculeFiscale) {
        this.matriculeFiscale = matriculeFiscale;
    }

    public String getRegistreCommerce() {
        return registreCommerce;
    }

    public void setRegistreCommerce(String registreCommerce) {
        this.registreCommerce = registreCommerce;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getCnssEmployeur() {
        return cnssEmployeur;
    }

    public void setCnssEmployeur(String cnssEmployeur) {
        this.cnssEmployeur = cnssEmployeur;
    }

    public String getCnssGerant() {
        return cnssGerant;
    }

    public void setCnssGerant(String cnssGerant) {
        this.cnssGerant = cnssGerant;
    }

    public byte[] getFichierPatente() {
        return fichierPatente;
    }

    public void setFichierPatente(byte[] fichierPatente) {
        this.fichierPatente = fichierPatente;
    }

    public String getFichierPatenteContentType() {
        return fichierPatenteContentType;
    }

    public void setFichierPatenteContentType(String fichierPatenteContentType) {
        this.fichierPatenteContentType = fichierPatenteContentType;
    }

    public Long getActivite1Id() {
        return activite1Id;
    }

    public void setActivite1Id(Long activite1Id) {
        this.activite1Id = activite1Id;
    }

    public String getActivite1Libelle() {
        return activite1Libelle;
    }

    public void setActivite1Libelle(String activite1Libelle) {
        this.activite1Libelle = activite1Libelle;
    }

    public Long getActivite2Id() {
        return activite2Id;
    }

    public void setActivite2Id(Long activite2Id) {
        this.activite2Id = activite2Id;
    }

    public String getActivite2Libelle() {
        return activite2Libelle;
    }

    public void setActivite2Libelle(String activite2Libelle) {
        this.activite2Libelle = activite2Libelle;
    }

    public Long getActivite3Id() {
        return activite3Id;
    }

    public void setActivite3Id(Long activite3Id) {
        this.activite3Id = activite3Id;
    }

    public String getActivite3Libelle() {
        return activite3Libelle;
    }

    public void setActivite3Libelle(String activite3Libelle) {
        this.activite3Libelle = activite3Libelle;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Long getVilleId() {
        return villeId;
    }

    public void setVilleId(Long villeId) {
        this.villeId = villeId;
    }

    public List<ImpotMensuelClientDTO> getImpotMensuelClients() {
        return impotMensuelClients;
    }

    public void setImpotMensuelClients(List<ImpotMensuelClientDTO> impotMensuelClients) {
        this.impotMensuelClients = impotMensuelClients;
    }

    public String getNumeroEtablissementSecondaire() {
        return numeroEtablissementSecondaire;
    }

    public void setNumeroEtablissementSecondaire(String numeroEtablissementSecondaire) {
        this.numeroEtablissementSecondaire = numeroEtablissementSecondaire;
    }

    public CategorieActivite getCategorieActivite() {
        return categorieActivite;
    }

    public void setCategorieActivite(CategorieActivite categorieActivite) {
        this.categorieActivite = categorieActivite;
    }

    public CodeTVA getCodeTva() {
        return codeTva;
    }

    public void setCodeTva(CodeTVA codeTva) {
        this.codeTva = codeTva;
    }

    public Long getSecteurActivite1Id() {
        return secteurActivite1Id;
    }

    public void setSecteurActivite1Id(Long secteurActivite1Id) {
        this.secteurActivite1Id = secteurActivite1Id;
    }

    public String getSecteurActivite1Libelle() {
        return secteurActivite1Libelle;
    }

    public void setSecteurActivite1Libelle(String secteurActivite1Libelle) {
        this.secteurActivite1Libelle = secteurActivite1Libelle;
    }

    public Long getSecteurActivite2Id() {
        return secteurActivite2Id;
    }

    public void setSecteurActivite2Id(Long secteurActivite2Id) {
        this.secteurActivite2Id = secteurActivite2Id;
    }

    public String getSecteurActivite2Libelle() {
        return secteurActivite2Libelle;
    }

    public void setSecteurActivite2Libelle(String secteurActivite2Libelle) {
        this.secteurActivite2Libelle = secteurActivite2Libelle;
    }

    public Long getSecteurActivite3Id() {
        return secteurActivite3Id;
    }

    public void setSecteurActivite3Id(Long secteurActivite3Id) {
        this.secteurActivite3Id = secteurActivite3Id;
    }

    public String getSecteurActivite3Libelle() {
        return secteurActivite3Libelle;
    }

    public void setSecteurActivite3Libelle(String secteurActivite3Libelle) {
        this.secteurActivite3Libelle = secteurActivite3Libelle;
    }

    public Long getCategorieCnssGerantId() {
        return categorieCnssGerantId;
    }

    public void setCategorieCnssGerantId(Long categorieCnssGerantId) {
        this.categorieCnssGerantId = categorieCnssGerantId;
    }

    public String getCategorieCnssGerantLibelle() {
        return categorieCnssGerantLibelle;
    }

    public void setCategorieCnssGerantLibelle(String categorieCnssGerantLibelle) {
        this.categorieCnssGerantLibelle = categorieCnssGerantLibelle;
    }

    public Double getTauxCnssAccident() {
        return tauxCnssAccident;
    }

    public String getPrenomGerant() {
        return prenomGerant;
    }

    public void setPrenomGerant(String prenomGerant) {
        this.prenomGerant = prenomGerant;
    }

    public String getNomGerant() {
        return nomGerant;
    }

    public void setNomGerant(String nomGerant) {
        this.nomGerant = nomGerant;
    }

    public LocalDate getDateNaissanceGerant() {
        return dateNaissanceGerant;
    }

    public void setDateNaissanceGerant(LocalDate dateNaissanceGerant) {
        this.dateNaissanceGerant = dateNaissanceGerant;
    }

    public String getCinGerant() {
        return cinGerant;
    }

    public void setCinGerant(String cinGerant) {
        this.cinGerant = cinGerant;
    }

    public LocalDate getDateDelivranceCINGerant() {
        return dateDelivranceCINGerant;
    }

    public void setDateDelivranceCINGerant(LocalDate dateDelivranceCINGerant) {
        this.dateDelivranceCINGerant = dateDelivranceCINGerant;
    }

    public String getAdresseGerant() {
        return adresseGerant;
    }

    public void setAdresseGerant(String adresseGerant) {
        this.adresseGerant = adresseGerant;
    }

    public String getTelephoneGerant1() {
        return telephoneGerant1;
    }

    public void setTelephoneGerant1(String telephoneGerant1) {
        this.telephoneGerant1 = telephoneGerant1;
    }

    public String getTelephoneGerant2() {
        return telephoneGerant2;
    }

    public void setTelephoneGerant2(String telephoneGerant2) {
        this.telephoneGerant2 = telephoneGerant2;
    }

    public String getEmailGerant() {
        return emailGerant;
    }

    public void setEmailGerant(String emailGerant) {
        this.emailGerant = emailGerant;
    }

    public byte[] getCopieCINGerant() {
        return copieCINGerant;
    }

    @Override
    public String toString() {
        return "FicheClientDTO{" +
            "id=" + id +
            ", categorieClient=" + categorieClient +
            ", designation='" + designation + '\'' +
            ", logoContentType='" + logoContentType + '\'' +
            ", adresse='" + adresse + '\'' +
            ", email='" + email + '\'' +
            ", codePostal='" + codePostal + '\'' +
            ", telephone='" + telephone + '\'' +
            ", fax='" + fax + '\'' +
            ", matriculeFiscale='" + matriculeFiscale + '\'' +
            ", registreCommerce='" + registreCommerce + '\'' +
            ", dateCreation=" + dateCreation +
            ", cnssEmployeur='" + cnssEmployeur + '\'' +
            ", cnssGerant='" + cnssGerant + '\'' +
            ", categorieCnssGerantId=" + categorieCnssGerantId +
            ", categorieCnssGerantLibelle='" + categorieCnssGerantLibelle + '\'' +
            ", fichierPatenteContentType='" + fichierPatenteContentType + '\'' +
            ", secteurActivite1Id=" + secteurActivite1Id +
            ", secteurActivite1Libelle='" + secteurActivite1Libelle + '\'' +
            ", secteurActivite2Id=" + secteurActivite2Id +
            ", secteurActivite2Libelle='" + secteurActivite2Libelle + '\'' +
            ", secteurActivite3Id=" + secteurActivite3Id +
            ", secteurActivite3Libelle='" + secteurActivite3Libelle + '\'' +
            ", activite1Id=" + activite1Id +
            ", activite1Libelle='" + activite1Libelle + '\'' +
            ", activite2Id=" + activite2Id +
            ", activite2Libelle='" + activite2Libelle + '\'' +
            ", activite3Id=" + activite3Id +
            ", activite3Libelle='" + activite3Libelle + '\'' +
            ", regionId=" + regionId +
            ", regionLibelle='" + regionLibelle + '\'' +
            ", villeId=" + villeId +
            ", villeLibelle='" + villeLibelle + '\'' +
            ", numeroEtablissementSecondaire='" + numeroEtablissementSecondaire + '\'' +
            ", categorieActivite=" + categorieActivite +
            ", codeTva=" + codeTva +
            ", tauxCnssAccident=" + tauxCnssAccident +
            ", administrationCnss=" + administrationCnss +
            ", administrationFiscale=" + administrationFiscale +
            ", administrationImpot=" + administrationImpot +
            ", prenomGerant='" + prenomGerant + '\'' +
            ", nomGerant='" + nomGerant + '\'' +
            ", dateNaissanceGerant=" + dateNaissanceGerant +
            ", lieuNaissanceGerant='" + lieuNaissanceGerant + '\'' +
            ", lieuDelivranceCINGerant='" + lieuDelivranceCINGerant + '\'' +
            ", cinGerant='" + cinGerant + '\'' +
            ", dateDelivranceCINGerant=" + dateDelivranceCINGerant +
            ", adresseGerant='" + adresseGerant + '\'' +
            ", telephoneGerant1='" + telephoneGerant1 + '\'' +
            ", telephoneGerant2='" + telephoneGerant2 + '\'' +
            ", emailGerant='" + emailGerant + '\'' +
            ", copieCINGerantContentType='" + copieCINGerantContentType + '\'' +
            ", telephone2='" + telephone2 + '\'' +
            ", telephone3='" + telephone3 + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", cabinetComptableCode=" + cabinetComptableCode +
            ", tauxCnssNormal=" + tauxCnssNormal +
            ", tauxCnssKarama=" + tauxCnssKarama +
            ", etiquettes='" + etiquettes + '\'' +
            ", montantFraisCabinet=" + montantFraisCabinet +
            ", nombreMoisFraisCabinet=" + nombreMoisFraisCabinet +
            ", impotMensuelClients=" + impotMensuelClients +
            '}';
    }

    public void setCopieCINGerant(byte[] copieCINGerant) {
        this.copieCINGerant = copieCINGerant;
    }

    public String getCopieCINGerantContentType() {
        return copieCINGerantContentType;
    }

    public void setCopieCINGerantContentType(String copieCINGerantContentType) {
        this.copieCINGerantContentType = copieCINGerantContentType;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getTelephone3() {
        return telephone3;
    }

    public void setTelephone3(String telephone3) {
        this.telephone3 = telephone3;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getEmail3() {
        return email3;
    }

    public void setEmail3(String email3) {
        this.email3 = email3;
    }

    public Long getCabinetComptableCode() {
        return cabinetComptableCode;
    }

    public void setCabinetComptableCode(Long cabinetComptableCode) {
        this.cabinetComptableCode = cabinetComptableCode;
    }

    public Double getTauxCnssNormal() {
        return tauxCnssNormal;
    }

    public void setTauxCnssNormal(Double tauxCnssNormal) {
        this.tauxCnssNormal = tauxCnssNormal;
    }

    public Double getTauxCnssKarama() {
        return tauxCnssKarama;
    }

    public void setTauxCnssKarama(Double tauxCnssKarama) {
        this.tauxCnssKarama = tauxCnssKarama;
    }

    public String getEtiquettes() {
        return etiquettes;
    }

    public void setEtiquettes(String etiquettes) {
        this.etiquettes = etiquettes;
    }

    public String getLieuNaissanceGerant() {
        return lieuNaissanceGerant;
    }

    public void setLieuNaissanceGerant(String lieuNaissanceGerant) {
        this.lieuNaissanceGerant = lieuNaissanceGerant;
    }

    public String getLieuDelivranceCINGerant() {
        return lieuDelivranceCINGerant;
    }

    public void setLieuDelivranceCINGerant(String lieuDelivranceCINGerant) {
        this.lieuDelivranceCINGerant = lieuDelivranceCINGerant;
    }

    public BigDecimal getMontantFraisCabinet() {
        return montantFraisCabinet;
    }

    public void setMontantFraisCabinet(BigDecimal montantFraisCabinet) {
        this.montantFraisCabinet = montantFraisCabinet;
    }

    public Integer getNombreMoisFraisCabinet() {
        return nombreMoisFraisCabinet;
    }

    public void setNombreMoisFraisCabinet(Integer nombreMoisFraisCabinet) {
        this.nombreMoisFraisCabinet = nombreMoisFraisCabinet;
    }
}
