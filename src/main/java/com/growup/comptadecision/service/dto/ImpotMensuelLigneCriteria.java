package com.growup.comptadecision.service.dto;

import com.growup.comptadecision.domain.enumeration.TypeValeur;
import io.github.jhipster.service.filter.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the ImpotMensuelLigne entity. This class is used in ImpotMensuelLigneResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /impot-mensuel-lignes?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ImpotMensuelLigneCriteria implements Serializable {
    /**
     * Class for filtering TypeValeur
     */
    public static class TypeValeurFilter extends Filter<TypeValeur> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter libelle;

    private StringFilter description;

    private IntegerFilter triOrdre;

    private FloatFilter valeur;

    private TypeValeurFilter typeValeur;

    private BooleanFilter valeurModifiable;

    private LongFilter impotMensuelId;

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

    public IntegerFilter getTriOrdre() {
        return triOrdre;
    }

    public void setTriOrdre(IntegerFilter triOrdre) {
        this.triOrdre = triOrdre;
    }

    public FloatFilter getValeur() {
        return valeur;
    }

    public void setValeur(FloatFilter valeur) {
        this.valeur = valeur;
    }

    public TypeValeurFilter getTypeValeur() {
        return typeValeur;
    }

    public void setTypeValeur(TypeValeurFilter typeValeur) {
        this.typeValeur = typeValeur;
    }

    public BooleanFilter getValeurModifiable() {
        return valeurModifiable;
    }

    public void setValeurModifiable(BooleanFilter valeurModifiable) {
        this.valeurModifiable = valeurModifiable;
    }

    public LongFilter getImpotMensuelId() {
        return impotMensuelId;
    }

    public void setImpotMensuelId(LongFilter impotMensuelId) {
        this.impotMensuelId = impotMensuelId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ImpotMensuelLigneCriteria that = (ImpotMensuelLigneCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(description, that.description) &&
            Objects.equals(triOrdre, that.triOrdre) &&
            Objects.equals(valeur, that.valeur) &&
            Objects.equals(typeValeur, that.typeValeur) &&
            Objects.equals(valeurModifiable, that.valeurModifiable) &&
            Objects.equals(impotMensuelId, that.impotMensuelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        libelle,
        description,
        triOrdre,
        valeur,
        typeValeur,
        valeurModifiable,
        impotMensuelId
        );
    }

    @Override
    public String toString() {
        return "ImpotMensuelLigneCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (triOrdre != null ? "triOrdre=" + triOrdre + ", " : "") +
                (valeur != null ? "valeur=" + valeur + ", " : "") +
                (typeValeur != null ? "typeValeur=" + typeValeur + ", " : "") +
                (valeurModifiable != null ? "valeurModifiable=" + valeurModifiable + ", " : "") +
                (impotMensuelId != null ? "impotMensuelId=" + impotMensuelId + ", " : "") +
            "}";
    }

}
