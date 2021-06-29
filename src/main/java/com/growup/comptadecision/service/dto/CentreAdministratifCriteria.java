package com.growup.comptadecision.service.dto;

import com.growup.comptadecision.domain.enumeration.TypeCentreAdministratif;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the CentreAdministratif entity. This class is used in CentreAdministratifResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /centre-administratifs?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CentreAdministratifCriteria implements Serializable {
    /**
     * Class for filtering TypeCentreAdministratif
     */
    public static class TypeCentreAdministratifFilter extends Filter<TypeCentreAdministratif> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter libelle;

    private StringFilter description;

    private TypeCentreAdministratifFilter type;

    private StringFilter telephone1;

    private StringFilter telephone2;

    private StringFilter telephone3;

    private StringFilter email1;

    private StringFilter email2;

    private StringFilter email3;

    private StringFilter fax;

    private StringFilter rib;

    private StringFilter banque;

    private StringFilter adresse;

    private StringFilter codePostal;

    private LongFilter villeId;

    private LongFilter regionId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getLibelle() {
        return libelle;
    }

    public void setLibelle(StringFilter libelle) {
        this.libelle = libelle;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public TypeCentreAdministratifFilter getType() {
        return type;
    }

    public void setType(TypeCentreAdministratifFilter type) {
        this.type = type;
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

    public StringFilter getFax() {
        return fax;
    }

    public void setFax(StringFilter fax) {
        this.fax = fax;
    }

    public StringFilter getRib() {
        return rib;
    }

    public void setRib(StringFilter rib) {
        this.rib = rib;
    }

    public StringFilter getBanque() {
        return banque;
    }

    public void setBanque(StringFilter banque) {
        this.banque = banque;
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

    public LongFilter getVilleId() {
        return villeId;
    }

    public void setVilleId(LongFilter villeId) {
        this.villeId = villeId;
    }

    public LongFilter getRegionId() {
        return regionId;
    }

    public void setRegionId(LongFilter regionId) {
        this.regionId = regionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CentreAdministratifCriteria that = (CentreAdministratifCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(description, that.description) &&
            Objects.equals(type, that.type) &&
            Objects.equals(telephone1, that.telephone1) &&
            Objects.equals(telephone2, that.telephone2) &&
            Objects.equals(telephone3, that.telephone3) &&
            Objects.equals(email1, that.email1) &&
            Objects.equals(email2, that.email2) &&
            Objects.equals(email3, that.email3) &&
            Objects.equals(fax, that.fax) &&
            Objects.equals(rib, that.rib) &&
            Objects.equals(banque, that.banque) &&
            Objects.equals(adresse, that.adresse) &&
            Objects.equals(codePostal, that.codePostal) &&
            Objects.equals(villeId, that.villeId) &&
            Objects.equals(regionId, that.regionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        libelle,
        description,
        type,
        telephone1,
        telephone2,
        telephone3,
        email1,
        email2,
        email3,
        fax,
        rib,
        banque,
        adresse,
        codePostal,
        villeId,
        regionId
        );
    }

    @Override
    public String toString() {
        return "CentreAdministratifCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (type != null ? "type=" + type + ", " : "") +
                (telephone1 != null ? "telephone1=" + telephone1 + ", " : "") +
                (telephone2 != null ? "telephone2=" + telephone2 + ", " : "") +
                (telephone3 != null ? "telephone3=" + telephone3 + ", " : "") +
                (email1 != null ? "email1=" + email1 + ", " : "") +
                (email2 != null ? "email2=" + email2 + ", " : "") +
                (email3 != null ? "email3=" + email3 + ", " : "") +
                (fax != null ? "fax=" + fax + ", " : "") +
                (rib != null ? "rib=" + rib + ", " : "") +
                (banque != null ? "banque=" + banque + ", " : "") +
                (adresse != null ? "adresse=" + adresse + ", " : "") +
                (codePostal != null ? "codePostal=" + codePostal + ", " : "") +
                (villeId != null ? "villeId=" + villeId + ", " : "") +
                (regionId != null ? "regionId=" + regionId + ", " : "") +
            "}";
    }

}
