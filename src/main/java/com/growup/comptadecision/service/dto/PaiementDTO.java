package com.growup.comptadecision.service.dto;

import com.growup.comptadecision.domain.enumeration.ModePaiement;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the Paiement entity.
 */
public class PaiementDTO implements Serializable {

    private Long id;

    @NotNull
    private String libelle;

    /**
     * cheque, virement, caisse, ...
     */
    @NotNull
    @ApiModelProperty(value = "cheque, virement, caisse, ...", required = true)
    private ModePaiement modePaiement;

    /**
     * num cheque, virement, operation caisse ...
     */
    @NotNull
    @ApiModelProperty(value = "num cheque, virement, operation caisse ...", required = true)
    private String numeroPreuvePaiement;

    /**
     * date cheque, date virement
     */
    @NotNull
    @ApiModelProperty(value = "date cheque, date virement", required = true)
    private LocalDate datePaiement;

    @NotNull
    private BigDecimal montantPaye;

    @NotNull
    private BigDecimal montantDu;


    private Long ligneCaisseId;

    private Long ficheClientId;

    private Long cnssId;

    private Long quittanceMensuelleId;

    private Long declarationAnnuelleId;

    private Long declarationEmployeurAnnuelleId;

    private Long acompteProvisionnelId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public ModePaiement getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(ModePaiement modePaiement) {
        this.modePaiement = modePaiement;
    }

    public String getNumeroPreuvePaiement() {
        return numeroPreuvePaiement;
    }

    public void setNumeroPreuvePaiement(String numeroPreuvePaiement) {
        this.numeroPreuvePaiement = numeroPreuvePaiement;
    }

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    public BigDecimal getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(BigDecimal montantPaye) {
        this.montantPaye = montantPaye;
    }

    public BigDecimal getMontantDu() {
        return montantDu;
    }

    public void setMontantDu(BigDecimal montantDu) {
        this.montantDu = montantDu;
    }

    public Long getLigneCaisseId() {
        return ligneCaisseId;
    }

    public void setLigneCaisseId(Long ligneCaisseId) {
        this.ligneCaisseId = ligneCaisseId;
    }

    public Long getFicheClientId() {
        return ficheClientId;
    }

    public void setFicheClientId(Long ficheClientId) {
        this.ficheClientId = ficheClientId;
    }

    public Long getCnssId() {
        return cnssId;
    }

    public void setCnssId(Long cnssId) {
        this.cnssId = cnssId;
    }

    public Long getQuittanceMensuelleId() {
        return quittanceMensuelleId;
    }

    public void setQuittanceMensuelleId(Long quittanceMensuelleId) {
        this.quittanceMensuelleId = quittanceMensuelleId;
    }

    public Long getDeclarationAnnuelleId() {
        return declarationAnnuelleId;
    }

    public void setDeclarationAnnuelleId(Long declarationAnnuelleId) {
        this.declarationAnnuelleId = declarationAnnuelleId;
    }

    public Long getDeclarationEmployeurAnnuelleId() {
        return declarationEmployeurAnnuelleId;
    }

    public void setDeclarationEmployeurAnnuelleId(Long declarationEmployeurAnnuelleId) {
        this.declarationEmployeurAnnuelleId = declarationEmployeurAnnuelleId;
    }

    public Long getAcompteProvisionnelId() {
        return acompteProvisionnelId;
    }

    public void setAcompteProvisionnelId(Long acompteProvisionnelId) {
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

        PaiementDTO paiementDTO = (PaiementDTO) o;
        if (paiementDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paiementDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PaiementDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", modePaiement='" + getModePaiement() + "'" +
            ", numeroPreuvePaiement='" + getNumeroPreuvePaiement() + "'" +
            ", datePaiement='" + getDatePaiement() + "'" +
            ", montantPaye=" + getMontantPaye() +
            ", montantDu=" + getMontantDu() +
            ", ligneCaisse=" + getLigneCaisseId() +
            ", ficheClient=" + getFicheClientId() +
            ", cnss=" + getCnssId() +
            ", quittanceMensuelle=" + getQuittanceMensuelleId() +
            ", declarationAnnuelle=" + getDeclarationAnnuelleId() +
            ", declarationEmployeurAnnuelle=" + getDeclarationEmployeurAnnuelleId() +
            ", acompteProvisionnel=" + getAcompteProvisionnelId() +
            "}";
    }
}
