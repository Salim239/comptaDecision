package com.growup.comptadecision.service.dto;

import io.github.jhipster.service.filter.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the LigneCaisse entity. This class is used in LigneCaisseResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /ligne-caisses?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class LigneCaisseCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter typeOperation;

    private StringFilter libelle;

    private BigDecimalFilter montantOperation;

    private LongFilter paiementId;

    private LongFilter quittanceMensuelleId;

    private LongFilter declarationAnnuelleId;

    private LongFilter cnssId;

    private LongFilter caisseId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(IntegerFilter typeOperation) {
        this.typeOperation = typeOperation;
    }

    public StringFilter getLibelle() {
        return libelle;
    }

    public void setLibelle(StringFilter libelle) {
        this.libelle = libelle;
    }

    public BigDecimalFilter getMontantOperation() {
        return montantOperation;
    }

    public void setMontantOperation(BigDecimalFilter montantOperation) {
        this.montantOperation = montantOperation;
    }

    public LongFilter getPaiementId() {
        return paiementId;
    }

    public void setPaiementId(LongFilter paiementId) {
        this.paiementId = paiementId;
    }

    public LongFilter getQuittanceMensuelleId() {
        return quittanceMensuelleId;
    }

    public void setQuittanceMensuelleId(LongFilter quittanceMensuelleId) {
        this.quittanceMensuelleId = quittanceMensuelleId;
    }

    public LongFilter getDeclarationAnnuelleId() {
        return declarationAnnuelleId;
    }

    public void setDeclarationAnnuelleId(LongFilter declarationAnnuelleId) {
        this.declarationAnnuelleId = declarationAnnuelleId;
    }

    public LongFilter getCnssId() {
        return cnssId;
    }

    public void setCnssId(LongFilter cnssId) {
        this.cnssId = cnssId;
    }

    public LongFilter getCaisseId() {
        return caisseId;
    }

    public void setCaisseId(LongFilter caisseId) {
        this.caisseId = caisseId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final LigneCaisseCriteria that = (LigneCaisseCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(typeOperation, that.typeOperation) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(montantOperation, that.montantOperation) &&
            Objects.equals(paiementId, that.paiementId) &&
            Objects.equals(quittanceMensuelleId, that.quittanceMensuelleId) &&
            Objects.equals(declarationAnnuelleId, that.declarationAnnuelleId) &&
            Objects.equals(cnssId, that.cnssId) &&
            Objects.equals(caisseId, that.caisseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        typeOperation,
        libelle,
        montantOperation,
        paiementId,
        quittanceMensuelleId,
        declarationAnnuelleId,
        cnssId,
        caisseId
        );
    }

    @Override
    public String toString() {
        return "LigneCaisseCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (typeOperation != null ? "typeOperation=" + typeOperation + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (montantOperation != null ? "montantOperation=" + montantOperation + ", " : "") +
                (paiementId != null ? "paiementId=" + paiementId + ", " : "") +
                (quittanceMensuelleId != null ? "quittanceMensuelleId=" + quittanceMensuelleId + ", " : "") +
                (declarationAnnuelleId != null ? "declarationAnnuelleId=" + declarationAnnuelleId + ", " : "") +
                (cnssId != null ? "cnssId=" + cnssId + ", " : "") +
                (caisseId != null ? "caisseId=" + caisseId + ", " : "") +
            "}";
    }

}
