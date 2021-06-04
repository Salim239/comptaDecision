package com.growup.comptadecision.domain;


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
 * A Region.
 */
@Entity
@Table(name = "declaration_annuelle_ligne")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@ToString
public class DecalrationAnnuelleLigne extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "tri_ordre", nullable = false)
    private Integer triOrdre;

    @Column(name = "calcule")
    private Boolean calcule;

    @Column(name = "montant")
    private BigDecimal montant;

    @Column(name = "montant_calcule")
    private BigDecimal montantCalcule;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Column(name = "description")
    private String description;

    @JoinColumn(name = "impot_annuel_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ImpotAnnuel impotAnnuel;

    @JoinColumn(name = "declaration_annuelle_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private DeclarationAnnuelle declarationAnnuelle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getTriOrdre() {
        return triOrdre;
    }

    public void setTriOrdre(Integer triOrdre) {
        this.triOrdre = triOrdre;
    }

    public Boolean getCalcule() {
        return calcule;
    }

    public void setCalcule(Boolean calcule) {
        this.calcule = calcule;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public BigDecimal getMontantCalcule() {
        return montantCalcule;
    }

    public void setMontantCalcule(BigDecimal montantCalcule) {
        this.montantCalcule = montantCalcule;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImpotAnnuel getImpotAnnuel() {
        return impotAnnuel;
    }

    public void setImpotAnnuel(ImpotAnnuel impotAnnuel) {
        this.impotAnnuel = impotAnnuel;
    }

    public DeclarationAnnuelle getDeclarationAnnuelle() {
        return declarationAnnuelle;
    }

    public void setDeclarationAnnuelle(DeclarationAnnuelle declarationAnnuelle) {
        this.declarationAnnuelle = declarationAnnuelle;
    }
}
