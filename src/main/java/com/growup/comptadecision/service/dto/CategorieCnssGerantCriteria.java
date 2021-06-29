package com.growup.comptadecision.service.dto;

import io.github.jhipster.service.filter.BigDecimalFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the CategorieCnssGerant entity. This class is used in CategorieCnssGerantResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /categorie-cnss-gerants?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CategorieCnssGerantCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter libelle;

    private StringFilter description;

    private BigDecimalFilter montantCotisationCnss;

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

    public BigDecimalFilter getMontantCotisationCnss() {
        return montantCotisationCnss;
    }

    public void setMontantCotisationCnss(BigDecimalFilter montantCotisationCnss) {
        this.montantCotisationCnss = montantCotisationCnss;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CategorieCnssGerantCriteria that = (CategorieCnssGerantCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(description, that.description) &&
            Objects.equals(montantCotisationCnss, that.montantCotisationCnss);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        libelle,
        description,
        montantCotisationCnss
        );
    }

    @Override
    public String toString() {
        return "CategorieCnssGerantCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (montantCotisationCnss != null ? "montantCotisationCnss=" + montantCotisationCnss + ", " : "") +
            "}";
    }

}
