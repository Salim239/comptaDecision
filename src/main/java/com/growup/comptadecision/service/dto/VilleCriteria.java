package com.growup.comptadecision.service.dto;

import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the Ville entity. This class is used in VilleResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /villes?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class VilleCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter codePostal;

    private StringFilter libelle;

    private StringFilter description;

    private LongFilter regionId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public StringFilter getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(StringFilter codePostal) {
        this.codePostal = codePostal;
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
        final VilleCriteria that = (VilleCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(codePostal, that.codePostal) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(description, that.description) &&
            Objects.equals(regionId, that.regionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        codePostal,
        libelle,
        description,
        regionId
        );
    }

    @Override
    public String toString() {
        return "VilleCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (codePostal != null ? "codePostal=" + codePostal + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (regionId != null ? "regionId=" + regionId + ", " : "") +
            "}";
    }

}
