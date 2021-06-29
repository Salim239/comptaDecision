package com.growup.comptadecision.service.dto;

import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the ApplicationUser entity. This class is used in ApplicationUserResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /application-users?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ApplicationUserCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter matricule;

    private StringFilter poste;

    private StringFilter dateEmbauche;

    private LongFilter cabinetComptableId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMatricule() {
        return matricule;
    }

    public void setMatricule(StringFilter matricule) {
        this.matricule = matricule;
    }

    public StringFilter getPoste() {
        return poste;
    }

    public void setPoste(StringFilter poste) {
        this.poste = poste;
    }

    public StringFilter getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(StringFilter dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public LongFilter getCabinetComptableId() {
        return cabinetComptableId;
    }

    public void setCabinetComptableId(LongFilter cabinetComptableId) {
        this.cabinetComptableId = cabinetComptableId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ApplicationUserCriteria that = (ApplicationUserCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(matricule, that.matricule) &&
            Objects.equals(poste, that.poste) &&
            Objects.equals(dateEmbauche, that.dateEmbauche) &&
            Objects.equals(cabinetComptableId, that.cabinetComptableId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        matricule,
        poste,
        dateEmbauche,
        cabinetComptableId
        );
    }

    @Override
    public String toString() {
        return "ApplicationUserCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (matricule != null ? "matricule=" + matricule + ", " : "") +
                (poste != null ? "poste=" + poste + ", " : "") +
                (dateEmbauche != null ? "dateEmbauche=" + dateEmbauche + ", " : "") +
                (cabinetComptableId != null ? "cabinetComptableId=" + cabinetComptableId + ", " : "") +
            "}";
    }

}
