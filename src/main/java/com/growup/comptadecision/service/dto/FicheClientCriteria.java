package com.growup.comptadecision.service.dto;

import com.growup.comptadecision.domain.enumeration.CategorieActivite;
import com.growup.comptadecision.domain.enumeration.CategorieClient;
import com.growup.comptadecision.domain.enumeration.CodeTVA;
import io.github.jhipster.service.filter.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the FicheClient entity. This class is used in FicheClientResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /fiche-clients?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FicheClientCriteria implements Serializable {
    /**
     * Class for filtering CategorieClient
     */
    public static class CategorieClientFilter extends Filter<CategorieClient> {
    }
    /**
     * Class for filtering CategorieActivite
     */
    public static class CategorieActiviteFilter extends Filter<CategorieActivite> {
    }
    /**
     * Class for filtering CodeTVA
     */
    public static class CodeTVAFilter extends Filter<CodeTVA> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private CategorieClientFilter categorieClient;

    private StringFilter designation;

    private StringFilter prenomGerant;

    private StringFilter nomGerant;

    private LocalDateFilter dateNaissanceGerant;

    private StringFilter adresseGerant;

    private StringFilter telephoneGerant1;

    private StringFilter telephoneGerant2;

    private StringFilter emailGerant;

    private StringFilter cinGerant;

    private LocalDateFilter dateDelivranceCINGerant;

    private StringFilter adresse;

    private StringFilter codePostal;

    private StringFilter telephone1;

    private StringFilter telephone2;

    private StringFilter telephone3;

    private StringFilter fax;

    private StringFilter email1;

    private StringFilter email2;

    private StringFilter email3;

    private StringFilter matriculeFiscale;

    private StringFilter registreCommerce;

    private LocalDateFilter dateCreation;

    private StringFilter cnssEmployeur;

    private StringFilter cnssGerant;

    private CategorieActiviteFilter categorieActivite;

    private CodeTVAFilter codeTVA;

    private StringFilter numeroEtablissementSecondaire;

    private DoubleFilter tauxCnssAccident;

    private LongFilter internalUserId;

    private LongFilter impotMensuelClientId;

    private LongFilter centreAdministratifId;

    private LongFilter activite1Id;

    private LongFilter activite2Id;

    private LongFilter activite3Id;

    private LongFilter secteurActivite1Id;

    private LongFilter secteurActivite2Id;

    private LongFilter secteurActivite3Id;

    private LongFilter regionId;

    private LongFilter villeId;

    private LongFilter categorieCnssGerantId;

    private LongFilter clientTypeComptableInfoId;

    private LongFilter comptableClientId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public CategorieClientFilter getCategorieClient() {
        return categorieClient;
    }

    public void setCategorieClient(CategorieClientFilter categorieClient) {
        this.categorieClient = categorieClient;
    }

    public StringFilter getDesignation() {
        return designation;
    }

    public void setDesignation(StringFilter designation) {
        this.designation = designation;
    }

    public StringFilter getPrenomGerant() {
        return prenomGerant;
    }

    public void setPrenomGerant(StringFilter prenomGerant) {
        this.prenomGerant = prenomGerant;
    }

    public StringFilter getNomGerant() {
        return nomGerant;
    }

    public void setNomGerant(StringFilter nomGerant) {
        this.nomGerant = nomGerant;
    }

    public LocalDateFilter getDateNaissanceGerant() {
        return dateNaissanceGerant;
    }

    public void setDateNaissanceGerant(LocalDateFilter dateNaissanceGerant) {
        this.dateNaissanceGerant = dateNaissanceGerant;
    }

    public StringFilter getAdresseGerant() {
        return adresseGerant;
    }

    public void setAdresseGerant(StringFilter adresseGerant) {
        this.adresseGerant = adresseGerant;
    }

    public StringFilter getTelephoneGerant1() {
        return telephoneGerant1;
    }

    public void setTelephoneGerant1(StringFilter telephoneGerant1) {
        this.telephoneGerant1 = telephoneGerant1;
    }

    public StringFilter getTelephoneGerant2() {
        return telephoneGerant2;
    }

    public void setTelephoneGerant2(StringFilter telephoneGerant2) {
        this.telephoneGerant2 = telephoneGerant2;
    }

    public StringFilter getEmailGerant() {
        return emailGerant;
    }

    public void setEmailGerant(StringFilter emailGerant) {
        this.emailGerant = emailGerant;
    }

    public StringFilter getCinGerant() {
        return cinGerant;
    }

    public void setCinGerant(StringFilter cinGerant) {
        this.cinGerant = cinGerant;
    }

    public LocalDateFilter getDateDelivranceCINGerant() {
        return dateDelivranceCINGerant;
    }

    public void setDateDelivranceCINGerant(LocalDateFilter dateDelivranceCINGerant) {
        this.dateDelivranceCINGerant = dateDelivranceCINGerant;
    }

    public StringFilter getAdresse() {
        return adresse;
    }

    public void setAdresse(StringFilter adresse) {
        this.adresse = adresse;
    }

    public StringFilter getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(StringFilter codePostal) {
        this.codePostal = codePostal;
    }

    public StringFilter getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(StringFilter telephone1) {
        this.telephone1 = telephone1;
    }

    public StringFilter getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(StringFilter telephone2) {
        this.telephone2 = telephone2;
    }

    public StringFilter getTelephone3() {
        return telephone3;
    }

    public void setTelephone3(StringFilter telephone3) {
        this.telephone3 = telephone3;
    }

    public StringFilter getFax() {
        return fax;
    }

    public void setFax(StringFilter fax) {
        this.fax = fax;
    }

    public StringFilter getEmail1() {
        return email1;
    }

    public void setEmail1(StringFilter email1) {
        this.email1 = email1;
    }

    public StringFilter getEmail2() {
        return email2;
    }

    public void setEmail2(StringFilter email2) {
        this.email2 = email2;
    }

    public StringFilter getEmail3() {
        return email3;
    }

    public void setEmail3(StringFilter email3) {
        this.email3 = email3;
    }

    public StringFilter getMatriculeFiscale() {
        return matriculeFiscale;
    }

    public void setMatriculeFiscale(StringFilter matriculeFiscale) {
        this.matriculeFiscale = matriculeFiscale;
    }

    public StringFilter getRegistreCommerce() {
        return registreCommerce;
    }

    public void setRegistreCommerce(StringFilter registreCommerce) {
        this.registreCommerce = registreCommerce;
    }

    public LocalDateFilter getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateFilter dateCreation) {
        this.dateCreation = dateCreation;
    }

    public StringFilter getCnssEmployeur() {
        return cnssEmployeur;
    }

    public void setCnssEmployeur(StringFilter cnssEmployeur) {
        this.cnssEmployeur = cnssEmployeur;
    }

    public StringFilter getCnssGerant() {
        return cnssGerant;
    }

    public void setCnssGerant(StringFilter cnssGerant) {
        this.cnssGerant = cnssGerant;
    }

    public CategorieActiviteFilter getCategorieActivite() {
        return categorieActivite;
    }

    public void setCategorieActivite(CategorieActiviteFilter categorieActivite) {
        this.categorieActivite = categorieActivite;
    }

    public CodeTVAFilter getCodeTVA() {
        return codeTVA;
    }

    public void setCodeTVA(CodeTVAFilter codeTVA) {
        this.codeTVA = codeTVA;
    }

    public StringFilter getNumeroEtablissementSecondaire() {
        return numeroEtablissementSecondaire;
    }

    public void setNumeroEtablissementSecondaire(StringFilter numeroEtablissementSecondaire) {
        this.numeroEtablissementSecondaire = numeroEtablissementSecondaire;
    }

    public DoubleFilter getTauxCnssAccident() {
        return tauxCnssAccident;
    }

    public void setTauxCnssAccident(DoubleFilter tauxCnssAccident) {
        this.tauxCnssAccident = tauxCnssAccident;
    }

    public LongFilter getInternalUserId() {
        return internalUserId;
    }

    public void setInternalUserId(LongFilter internalUserId) {
        this.internalUserId = internalUserId;
    }

    public LongFilter getImpotMensuelClientId() {
        return impotMensuelClientId;
    }

    public void setImpotMensuelClientId(LongFilter impotMensuelClientId) {
        this.impotMensuelClientId = impotMensuelClientId;
    }

    public LongFilter getCentreAdministratifId() {
        return centreAdministratifId;
    }

    public void setCentreAdministratifId(LongFilter centreAdministratifId) {
        this.centreAdministratifId = centreAdministratifId;
    }

    public LongFilter getActivite1Id() {
        return activite1Id;
    }

    public void setActivite1Id(LongFilter activite1Id) {
        this.activite1Id = activite1Id;
    }

    public LongFilter getActivite2Id() {
        return activite2Id;
    }

    public void setActivite2Id(LongFilter activite2Id) {
        this.activite2Id = activite2Id;
    }

    public LongFilter getActivite3Id() {
        return activite3Id;
    }

    public void setActivite3Id(LongFilter activite3Id) {
        this.activite3Id = activite3Id;
    }

    public LongFilter getSecteurActivite1Id() {
        return secteurActivite1Id;
    }

    public void setSecteurActivite1Id(LongFilter secteurActivite1Id) {
        this.secteurActivite1Id = secteurActivite1Id;
    }

    public LongFilter getSecteurActivite2Id() {
        return secteurActivite2Id;
    }

    public void setSecteurActivite2Id(LongFilter secteurActivite2Id) {
        this.secteurActivite2Id = secteurActivite2Id;
    }

    public LongFilter getSecteurActivite3Id() {
        return secteurActivite3Id;
    }

    public void setSecteurActivite3Id(LongFilter secteurActivite3Id) {
        this.secteurActivite3Id = secteurActivite3Id;
    }

    public LongFilter getRegionId() {
        return regionId;
    }

    public void setRegionId(LongFilter regionId) {
        this.regionId = regionId;
    }

    public LongFilter getVilleId() {
        return villeId;
    }

    public void setVilleId(LongFilter villeId) {
        this.villeId = villeId;
    }

    public LongFilter getCategorieCnssGerantId() {
        return categorieCnssGerantId;
    }

    public void setCategorieCnssGerantId(LongFilter categorieCnssGerantId) {
        this.categorieCnssGerantId = categorieCnssGerantId;
    }

    public LongFilter getClientTypeComptableInfoId() {
        return clientTypeComptableInfoId;
    }

    public void setClientTypeComptableInfoId(LongFilter clientTypeComptableInfoId) {
        this.clientTypeComptableInfoId = clientTypeComptableInfoId;
    }

    public LongFilter getComptableClientId() {
        return comptableClientId;
    }

    public void setComptableClientId(LongFilter comptableClientId) {
        this.comptableClientId = comptableClientId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FicheClientCriteria that = (FicheClientCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(categorieClient, that.categorieClient) &&
            Objects.equals(designation, that.designation) &&
            Objects.equals(prenomGerant, that.prenomGerant) &&
            Objects.equals(nomGerant, that.nomGerant) &&
            Objects.equals(dateNaissanceGerant, that.dateNaissanceGerant) &&
            Objects.equals(adresseGerant, that.adresseGerant) &&
            Objects.equals(telephoneGerant1, that.telephoneGerant1) &&
            Objects.equals(telephoneGerant2, that.telephoneGerant2) &&
            Objects.equals(emailGerant, that.emailGerant) &&
            Objects.equals(cinGerant, that.cinGerant) &&
            Objects.equals(dateDelivranceCINGerant, that.dateDelivranceCINGerant) &&
            Objects.equals(adresse, that.adresse) &&
            Objects.equals(codePostal, that.codePostal) &&
            Objects.equals(telephone1, that.telephone1) &&
            Objects.equals(telephone2, that.telephone2) &&
            Objects.equals(telephone3, that.telephone3) &&
            Objects.equals(fax, that.fax) &&
            Objects.equals(email1, that.email1) &&
            Objects.equals(email2, that.email2) &&
            Objects.equals(email3, that.email3) &&
            Objects.equals(matriculeFiscale, that.matriculeFiscale) &&
            Objects.equals(registreCommerce, that.registreCommerce) &&
            Objects.equals(dateCreation, that.dateCreation) &&
            Objects.equals(cnssEmployeur, that.cnssEmployeur) &&
            Objects.equals(cnssGerant, that.cnssGerant) &&
            Objects.equals(categorieActivite, that.categorieActivite) &&
            Objects.equals(codeTVA, that.codeTVA) &&
            Objects.equals(numeroEtablissementSecondaire, that.numeroEtablissementSecondaire) &&
            Objects.equals(tauxCnssAccident, that.tauxCnssAccident) &&
            Objects.equals(internalUserId, that.internalUserId) &&
            Objects.equals(impotMensuelClientId, that.impotMensuelClientId) &&
            Objects.equals(centreAdministratifId, that.centreAdministratifId) &&
            Objects.equals(activite1Id, that.activite1Id) &&
            Objects.equals(activite2Id, that.activite2Id) &&
            Objects.equals(activite3Id, that.activite3Id) &&
            Objects.equals(secteurActivite1Id, that.secteurActivite1Id) &&
            Objects.equals(secteurActivite2Id, that.secteurActivite2Id) &&
            Objects.equals(secteurActivite3Id, that.secteurActivite3Id) &&
            Objects.equals(regionId, that.regionId) &&
            Objects.equals(villeId, that.villeId) &&
            Objects.equals(categorieCnssGerantId, that.categorieCnssGerantId) &&
            Objects.equals(clientTypeComptableInfoId, that.clientTypeComptableInfoId) &&
            Objects.equals(comptableClientId, that.comptableClientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        categorieClient,
        designation,
        prenomGerant,
        nomGerant,
        dateNaissanceGerant,
        adresseGerant,
        telephoneGerant1,
        telephoneGerant2,
        emailGerant,
        cinGerant,
        dateDelivranceCINGerant,
        adresse,
        codePostal,
        telephone1,
        telephone2,
        telephone3,
        fax,
        email1,
        email2,
        email3,
        matriculeFiscale,
        registreCommerce,
        dateCreation,
        cnssEmployeur,
        cnssGerant,
        categorieActivite,
        codeTVA,
        numeroEtablissementSecondaire,
        tauxCnssAccident,
        internalUserId,
        impotMensuelClientId,
        centreAdministratifId,
        activite1Id,
        activite2Id,
        activite3Id,
        secteurActivite1Id,
        secteurActivite2Id,
        secteurActivite3Id,
        regionId,
        villeId,
        categorieCnssGerantId,
        clientTypeComptableInfoId,
        comptableClientId
        );
    }

    @Override
    public String toString() {
        return "FicheClientCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (categorieClient != null ? "categorieClient=" + categorieClient + ", " : "") +
                (designation != null ? "designation=" + designation + ", " : "") +
                (prenomGerant != null ? "prenomGerant=" + prenomGerant + ", " : "") +
                (nomGerant != null ? "nomGerant=" + nomGerant + ", " : "") +
                (dateNaissanceGerant != null ? "dateNaissanceGerant=" + dateNaissanceGerant + ", " : "") +
                (adresseGerant != null ? "adresseGerant=" + adresseGerant + ", " : "") +
                (telephoneGerant1 != null ? "telephoneGerant1=" + telephoneGerant1 + ", " : "") +
                (telephoneGerant2 != null ? "telephoneGerant2=" + telephoneGerant2 + ", " : "") +
                (emailGerant != null ? "emailGerant=" + emailGerant + ", " : "") +
                (cinGerant != null ? "cinGerant=" + cinGerant + ", " : "") +
                (dateDelivranceCINGerant != null ? "dateDelivranceCINGerant=" + dateDelivranceCINGerant + ", " : "") +
                (adresse != null ? "adresse=" + adresse + ", " : "") +
                (codePostal != null ? "codePostal=" + codePostal + ", " : "") +
                (telephone1 != null ? "telephone1=" + telephone1 + ", " : "") +
                (telephone2 != null ? "telephone2=" + telephone2 + ", " : "") +
                (telephone3 != null ? "telephone3=" + telephone3 + ", " : "") +
                (fax != null ? "fax=" + fax + ", " : "") +
                (email1 != null ? "email1=" + email1 + ", " : "") +
                (email2 != null ? "email2=" + email2 + ", " : "") +
                (email3 != null ? "email3=" + email3 + ", " : "") +
                (matriculeFiscale != null ? "matriculeFiscale=" + matriculeFiscale + ", " : "") +
                (registreCommerce != null ? "registreCommerce=" + registreCommerce + ", " : "") +
                (dateCreation != null ? "dateCreation=" + dateCreation + ", " : "") +
                (cnssEmployeur != null ? "cnssEmployeur=" + cnssEmployeur + ", " : "") +
                (cnssGerant != null ? "cnssGerant=" + cnssGerant + ", " : "") +
                (categorieActivite != null ? "categorieActivite=" + categorieActivite + ", " : "") +
                (codeTVA != null ? "codeTVA=" + codeTVA + ", " : "") +
                (numeroEtablissementSecondaire != null ? "numeroEtablissementSecondaire=" + numeroEtablissementSecondaire + ", " : "") +
                (tauxCnssAccident != null ? "tauxCnssAccident=" + tauxCnssAccident + ", " : "") +
                (internalUserId != null ? "internalUserId=" + internalUserId + ", " : "") +
                (impotMensuelClientId != null ? "impotMensuelClientId=" + impotMensuelClientId + ", " : "") +
                (centreAdministratifId != null ? "centreAdministratifId=" + centreAdministratifId + ", " : "") +
                (activite1Id != null ? "activite1Id=" + activite1Id + ", " : "") +
                (activite2Id != null ? "activite2Id=" + activite2Id + ", " : "") +
                (activite3Id != null ? "activite3Id=" + activite3Id + ", " : "") +
                (secteurActivite1Id != null ? "secteurActivite1Id=" + secteurActivite1Id + ", " : "") +
                (secteurActivite2Id != null ? "secteurActivite2Id=" + secteurActivite2Id + ", " : "") +
                (secteurActivite3Id != null ? "secteurActivite3Id=" + secteurActivite3Id + ", " : "") +
                (regionId != null ? "regionId=" + regionId + ", " : "") +
                (villeId != null ? "villeId=" + villeId + ", " : "") +
                (categorieCnssGerantId != null ? "categorieCnssGerantId=" + categorieCnssGerantId + ", " : "") +
                (clientTypeComptableInfoId != null ? "clientTypeComptableInfoId=" + clientTypeComptableInfoId + ", " : "") +
                (comptableClientId != null ? "comptableClientId=" + comptableClientId + ", " : "") +
            "}";
    }

}
