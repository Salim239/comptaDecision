package com.growup.comptadecision.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * A DeclarationEmployeurAnnuelle.
 */
@Entity
@Table(name = "declaration_employeur_annuelle")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@ToString
public class DeclarationEmployeurAnnuelle extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "annee", nullable = false)
    private Integer annee;

    @Column(name = "montant_annexe_1")
    private BigDecimal montantAnnexe1;

    @Column(name = "montant_annexe_2")
    private BigDecimal montantAnnexe2;

    @Column(name = "montant_annexe_3")
    private BigDecimal montantAnnexe3;

    @Column(name = "montant_annexe_4")
    private BigDecimal montantAnnexe4;

    @Column(name = "montant_annexe_5")
    private BigDecimal montantAnnexe5;

    @Column(name = "montant_annexe_6")
    private BigDecimal montantAnnexe6;

    @Column(name = "montant_annexe_7")
    private BigDecimal montantAnnexe7;

    @Column(name = "montant_annexe_8")
    private BigDecimal montantAnnexe8;

    @Column(name = "montant_annexe_9")
    private BigDecimal montantAnnexe9;

    @Column(name = "montant_annexe_10")
    private BigDecimal montantAnnexe10;

    @Column(name = "montant_annexe_11")
    private BigDecimal montantAnnexe11;

    @Column(name = "montant_annexe_12")
    private BigDecimal montantAnnexe12;

    @ManyToOne
    @JsonIgnoreProperties("declarationEmployeurAnnuelles")
    private FicheClient ficheClient;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnnee() {
        return annee;
    }

    public DeclarationEmployeurAnnuelle annee(Integer annee) {
        this.annee = annee;
        return this;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public BigDecimal getMontantAnnexe1() {
        return montantAnnexe1;
    }

    public DeclarationEmployeurAnnuelle montantAnnexe1(BigDecimal montantAnnexe1) {
        this.montantAnnexe1 = montantAnnexe1;
        return this;
    }

    public void setMontantAnnexe1(BigDecimal montantAnnexe1) {
        this.montantAnnexe1 = montantAnnexe1;
    }

    public BigDecimal getMontantAnnexe2() {
        return montantAnnexe2;
    }

    public DeclarationEmployeurAnnuelle montantAnnexe2(BigDecimal montantAnnexe2) {
        this.montantAnnexe2 = montantAnnexe2;
        return this;
    }

    public void setMontantAnnexe2(BigDecimal montantAnnexe2) {
        this.montantAnnexe2 = montantAnnexe2;
    }

    public BigDecimal getMontantAnnexe3() {
        return montantAnnexe3;
    }

    public DeclarationEmployeurAnnuelle montantAnnexe3(BigDecimal montantAnnexe3) {
        this.montantAnnexe3 = montantAnnexe3;
        return this;
    }

    public void setMontantAnnexe3(BigDecimal montantAnnexe3) {
        this.montantAnnexe3 = montantAnnexe3;
    }

    public BigDecimal getMontantAnnexe4() {
        return montantAnnexe4;
    }

    public DeclarationEmployeurAnnuelle montantAnnexe4(BigDecimal montantAnnexe4) {
        this.montantAnnexe4 = montantAnnexe4;
        return this;
    }

    public void setMontantAnnexe4(BigDecimal montantAnnexe4) {
        this.montantAnnexe4 = montantAnnexe4;
    }

    public BigDecimal getMontantAnnexe5() {
        return montantAnnexe5;
    }

    public DeclarationEmployeurAnnuelle montantAnnexe5(BigDecimal montantAnnexe5) {
        this.montantAnnexe5 = montantAnnexe5;
        return this;
    }

    public void setMontantAnnexe5(BigDecimal montantAnnexe5) {
        this.montantAnnexe5 = montantAnnexe5;
    }

    public BigDecimal getMontantAnnexe6() {
        return montantAnnexe6;
    }

    public DeclarationEmployeurAnnuelle montantAnnexe6(BigDecimal montantAnnexe6) {
        this.montantAnnexe6 = montantAnnexe6;
        return this;
    }

    public void setMontantAnnexe6(BigDecimal montantAnnexe6) {
        this.montantAnnexe6 = montantAnnexe6;
    }

    public BigDecimal getMontantAnnexe7() {
        return montantAnnexe7;
    }

    public DeclarationEmployeurAnnuelle montantAnnexe7(BigDecimal montantAnnexe7) {
        this.montantAnnexe7 = montantAnnexe7;
        return this;
    }

    public void setMontantAnnexe7(BigDecimal montantAnnexe7) {
        this.montantAnnexe7 = montantAnnexe7;
    }

    public BigDecimal getMontantAnnexe8() {
        return montantAnnexe8;
    }

    public DeclarationEmployeurAnnuelle montantAnnexe8(BigDecimal montantAnnexe8) {
        this.montantAnnexe8 = montantAnnexe8;
        return this;
    }

    public void setMontantAnnexe8(BigDecimal montantAnnexe8) {
        this.montantAnnexe8 = montantAnnexe8;
    }

    public BigDecimal getMontantAnnexe9() {
        return montantAnnexe9;
    }

    public DeclarationEmployeurAnnuelle montantAnnexe9(BigDecimal montantAnnexe9) {
        this.montantAnnexe9 = montantAnnexe9;
        return this;
    }

    public void setMontantAnnexe9(BigDecimal montantAnnexe9) {
        this.montantAnnexe9 = montantAnnexe9;
    }

    public BigDecimal getMontantAnnexe10() {
        return montantAnnexe10;
    }

    public DeclarationEmployeurAnnuelle montantAnnexe10(BigDecimal montantAnnexe10) {
        this.montantAnnexe10 = montantAnnexe10;
        return this;
    }

    public void setMontantAnnexe10(BigDecimal montantAnnexe10) {
        this.montantAnnexe10 = montantAnnexe10;
    }

    public BigDecimal getMontantAnnexe11() {
        return montantAnnexe11;
    }

    public DeclarationEmployeurAnnuelle montantAnnexe11(BigDecimal montantAnnexe11) {
        this.montantAnnexe11 = montantAnnexe11;
        return this;
    }

    public void setMontantAnnexe11(BigDecimal montantAnnexe11) {
        this.montantAnnexe11 = montantAnnexe11;
    }

    public BigDecimal getMontantAnnexe12() {
        return montantAnnexe12;
    }

    public DeclarationEmployeurAnnuelle montantAnnexe12(BigDecimal montantAnnexe12) {
        this.montantAnnexe12 = montantAnnexe12;
        return this;
    }

    public void setMontantAnnexe12(BigDecimal montantAnnexe12) {
        this.montantAnnexe12 = montantAnnexe12;
    }

    public FicheClient getFicheClient() {
        return ficheClient;
    }

    public DeclarationEmployeurAnnuelle ficheClient(FicheClient ficheClient) {
        this.ficheClient = ficheClient;
        return this;
    }

    public void setFicheClient(FicheClient ficheClient) {
        this.ficheClient = ficheClient;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

}
