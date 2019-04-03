package com.growup.comptadecision.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the DeclarationEmployeurAnnuelle entity.
 */
public class DeclarationEmployeurAnnuelleDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer annee;

    private BigDecimal montantAnnexe1;

    private BigDecimal montantAnnexe2;

    private BigDecimal montantAnnexe3;

    private BigDecimal montantAnnexe4;

    private BigDecimal montantAnnexe5;

    private BigDecimal montantAnnexe6;

    private BigDecimal montantAnnexe7;

    private BigDecimal montantAnnexe8;

    private BigDecimal montantAnnexe9;

    private BigDecimal montantAnnexe10;

    private BigDecimal montantAnnexe11;

    private BigDecimal montantAnnexe12;


    private Long ficheClientId;

    private String ficheClientDesignation;
    private String ficheClientMatriculeFiscale;
    private String ficheClientRegistreCommerce;

    public String getFicheClientDesignation() {
        return ficheClientDesignation;
    }

    public void setFicheClientDesignation(String ficheClientDesignation) {
        this.ficheClientDesignation = ficheClientDesignation;
    }

    public String getFicheClientMatriculeFiscale() {
        return ficheClientMatriculeFiscale;
    }

    public void setFicheClientMatriculeFiscale(String ficheClientMatriculeFiscale) {
        this.ficheClientMatriculeFiscale = ficheClientMatriculeFiscale;
    }

    public String getFicheClientRegistreCommerce() {
        return ficheClientRegistreCommerce;
    }

    public void setFicheClientRegistreCommerce(String ficheClientRegistreCommerce) {
        this.ficheClientRegistreCommerce = ficheClientRegistreCommerce;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public BigDecimal getMontantAnnexe1() {
        return montantAnnexe1;
    }

    public void setMontantAnnexe1(BigDecimal montantAnnexe1) {
        this.montantAnnexe1 = montantAnnexe1;
    }

    public BigDecimal getMontantAnnexe2() {
        return montantAnnexe2;
    }

    public void setMontantAnnexe2(BigDecimal montantAnnexe2) {
        this.montantAnnexe2 = montantAnnexe2;
    }

    public BigDecimal getMontantAnnexe3() {
        return montantAnnexe3;
    }

    public void setMontantAnnexe3(BigDecimal montantAnnexe3) {
        this.montantAnnexe3 = montantAnnexe3;
    }

    public BigDecimal getMontantAnnexe4() {
        return montantAnnexe4;
    }

    public void setMontantAnnexe4(BigDecimal montantAnnexe4) {
        this.montantAnnexe4 = montantAnnexe4;
    }

    public BigDecimal getMontantAnnexe5() {
        return montantAnnexe5;
    }

    public void setMontantAnnexe5(BigDecimal montantAnnexe5) {
        this.montantAnnexe5 = montantAnnexe5;
    }

    public BigDecimal getMontantAnnexe6() {
        return montantAnnexe6;
    }

    public void setMontantAnnexe6(BigDecimal montantAnnexe6) {
        this.montantAnnexe6 = montantAnnexe6;
    }

    public BigDecimal getMontantAnnexe7() {
        return montantAnnexe7;
    }

    public void setMontantAnnexe7(BigDecimal montantAnnexe7) {
        this.montantAnnexe7 = montantAnnexe7;
    }

    public BigDecimal getMontantAnnexe8() {
        return montantAnnexe8;
    }

    public void setMontantAnnexe8(BigDecimal montantAnnexe8) {
        this.montantAnnexe8 = montantAnnexe8;
    }

    public BigDecimal getMontantAnnexe9() {
        return montantAnnexe9;
    }

    public void setMontantAnnexe9(BigDecimal montantAnnexe9) {
        this.montantAnnexe9 = montantAnnexe9;
    }

    public BigDecimal getMontantAnnexe10() {
        return montantAnnexe10;
    }

    public void setMontantAnnexe10(BigDecimal montantAnnexe10) {
        this.montantAnnexe10 = montantAnnexe10;
    }

    public BigDecimal getMontantAnnexe11() {
        return montantAnnexe11;
    }

    public void setMontantAnnexe11(BigDecimal montantAnnexe11) {
        this.montantAnnexe11 = montantAnnexe11;
    }

    public BigDecimal getMontantAnnexe12() {
        return montantAnnexe12;
    }

    public void setMontantAnnexe12(BigDecimal montantAnnexe12) {
        this.montantAnnexe12 = montantAnnexe12;
    }

    public Long getFicheClientId() {
        return ficheClientId;
    }

    public void setFicheClientId(Long ficheClientId) {
        this.ficheClientId = ficheClientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeclarationEmployeurAnnuelleDTO declarationEmployeurAnnuelleDTO = (DeclarationEmployeurAnnuelleDTO) o;
        if (declarationEmployeurAnnuelleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), declarationEmployeurAnnuelleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DeclarationEmployeurAnnuelleDTO{" +
            "id=" + getId() +
            ", annee=" + getAnnee() +
            ", montantAnnexe1=" + getMontantAnnexe1() +
            ", montantAnnexe2=" + getMontantAnnexe2() +
            ", montantAnnexe3=" + getMontantAnnexe3() +
            ", montantAnnexe4=" + getMontantAnnexe4() +
            ", montantAnnexe5=" + getMontantAnnexe5() +
            ", montantAnnexe6=" + getMontantAnnexe6() +
            ", montantAnnexe7=" + getMontantAnnexe7() +
            ", montantAnnexe8=" + getMontantAnnexe8() +
            ", montantAnnexe9=" + getMontantAnnexe9() +
            ", montantAnnexe10=" + getMontantAnnexe10() +
            ", montantAnnexe11=" + getMontantAnnexe11() +
            ", montantAnnexe12=" + getMontantAnnexe12() +
            ", ficheClient=" + getFicheClientId() +
            "}";
    }
}
