package com.growup.comptadecision.service.dto;

import com.growup.comptadecision.domain.enumeration.ModePaiement;
import io.github.jhipster.service.filter.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the Paiement entity. This class is used in PaiementResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /paiements?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PaiementCriteria implements Serializable {
    /**
     * Class for filtering ModePaiement
     */
    public static class ModePaiementFilter extends Filter<ModePaiement> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter libelle;

    private ModePaiementFilter modePaiement;

    private StringFilter numeroPreuvePaiement;

    private LocalDateFilter datePaiement;

    private BigDecimalFilter montantPaye;

    private BigDecimalFilter montantDu;

    private LongFilter ligneCaisseId;

    private LongFilter ficheClientId;

    private LongFilter cnssId;

    private LongFilter quittanceMensuelleId;

    private LongFilter declarationAnnuelleId;

    private LongFilter declarationEmployeurAnnuelleId;

    private LongFilter acompteProvisionnelId;

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

    public ModePaiementFilter getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(ModePaiementFilter modePaiement) {
        this.modePaiement = modePaiement;
    }

    public StringFilter getNumeroPreuvePaiement() {
        return numeroPreuvePaiement;
    }

    public void setNumeroPreuvePaiement(StringFilter numeroPreuvePaiement) {
        this.numeroPreuvePaiement = numeroPreuvePaiement;
    }

    public LocalDateFilter getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDateFilter datePaiement) {
        this.datePaiement = datePaiement;
    }

    public BigDecimalFilter getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(BigDecimalFilter montantPaye) {
        this.montantPaye = montantPaye;
    }

    public BigDecimalFilter getMontantDu() {
        return montantDu;
    }

    public void setMontantDu(BigDecimalFilter montantDu) {
        this.montantDu = montantDu;
    }

    public LongFilter getLigneCaisseId() {
        return ligneCaisseId;
    }

    public void setLigneCaisseId(LongFilter ligneCaisseId) {
        this.ligneCaisseId = ligneCaisseId;
    }

    public LongFilter getFicheClientId() {
        return ficheClientId;
    }

    public void setFicheClientId(LongFilter ficheClientId) {
        this.ficheClientId = ficheClientId;
    }

    public LongFilter getCnssId() {
        return cnssId;
    }

    public void setCnssId(LongFilter cnssId) {
        this.cnssId = cnssId;
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

    public LongFilter getDeclarationEmployeurAnnuelleId() {
        return declarationEmployeurAnnuelleId;
    }

    public void setDeclarationEmployeurAnnuelleId(LongFilter declarationEmployeurAnnuelleId) {
        this.declarationEmployeurAnnuelleId = declarationEmployeurAnnuelleId;
    }

    public LongFilter getAcompteProvisionnelId() {
        return acompteProvisionnelId;
    }

    public void setAcompteProvisionnelId(LongFilter acompteProvisionnelId) {
        this.acompteProvisionnelId = acompteProvisionnelId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PaiementCriteria that = (PaiementCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(modePaiement, that.modePaiement) &&
            Objects.equals(numeroPreuvePaiement, that.numeroPreuvePaiement) &&
            Objects.equals(datePaiement, that.datePaiement) &&
            Objects.equals(montantPaye, that.montantPaye) &&
            Objects.equals(montantDu, that.montantDu) &&
            Objects.equals(ligneCaisseId, that.ligneCaisseId) &&
            Objects.equals(ficheClientId, that.ficheClientId) &&
            Objects.equals(cnssId, that.cnssId) &&
            Objects.equals(quittanceMensuelleId, that.quittanceMensuelleId) &&
            Objects.equals(declarationAnnuelleId, that.declarationAnnuelleId) &&
            Objects.equals(declarationEmployeurAnnuelleId, that.declarationEmployeurAnnuelleId) &&
            Objects.equals(acompteProvisionnelId, that.acompteProvisionnelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        libelle,
        modePaiement,
        numeroPreuvePaiement,
        datePaiement,
        montantPaye,
        montantDu,
        ligneCaisseId,
        ficheClientId,
        cnssId,
        quittanceMensuelleId,
        declarationAnnuelleId,
        declarationEmployeurAnnuelleId,
        acompteProvisionnelId
        );
    }

    @Override
    public String toString() {
        return "PaiementCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (modePaiement != null ? "modePaiement=" + modePaiement + ", " : "") +
                (numeroPreuvePaiement != null ? "numeroPreuvePaiement=" + numeroPreuvePaiement + ", " : "") +
                (datePaiement != null ? "datePaiement=" + datePaiement + ", " : "") +
                (montantPaye != null ? "montantPaye=" + montantPaye + ", " : "") +
                (montantDu != null ? "montantDu=" + montantDu + ", " : "") +
                (ligneCaisseId != null ? "ligneCaisseId=" + ligneCaisseId + ", " : "") +
                (ficheClientId != null ? "ficheClientId=" + ficheClientId + ", " : "") +
                (cnssId != null ? "cnssId=" + cnssId + ", " : "") +
                (quittanceMensuelleId != null ? "quittanceMensuelleId=" + quittanceMensuelleId + ", " : "") +
                (declarationAnnuelleId != null ? "declarationAnnuelleId=" + declarationAnnuelleId + ", " : "") +
                (declarationEmployeurAnnuelleId != null ? "declarationEmployeurAnnuelleId=" + declarationEmployeurAnnuelleId + ", " : "") +
                (acompteProvisionnelId != null ? "acompteProvisionnelId=" + acompteProvisionnelId + ", " : "") +
            "}";
    }

}
