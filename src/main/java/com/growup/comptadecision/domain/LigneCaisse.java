package com.growup.comptadecision.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Ligne caisse
 */
@ApiModel(description = "Ligne caisse")
@Entity
@Table(name = "ligne_caisse")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LigneCaisse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * typeOperation 1 si Encaissement -1 si decaissement
     */
    @NotNull
    @ApiModelProperty(value = "typeOperation 1 si Encaissement -1 si decaissement", required = true)
    @Column(name = "type_operation", nullable = false)
    private Integer typeOperation;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Column(name = "montant_operation", precision = 21, scale = 2)
    private BigDecimal montantOperation;

    @ManyToOne
    @JsonIgnoreProperties("ligneCaisses")
    private QuittanceMensuelle quittanceMensuelle;

    @ManyToOne
    @JsonIgnoreProperties("ligneCaisses")
    private DeclarationAnnuelle declarationAnnuelle;

    @ManyToOne
    @JsonIgnoreProperties("ligneCaisses")
    private Cnss cnss;

    @ManyToOne
    @JsonIgnoreProperties("ligneCaisses")
    private Caisse caisse;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTypeOperation() {
        return typeOperation;
    }

    public LigneCaisse typeOperation(Integer typeOperation) {
        this.typeOperation = typeOperation;
        return this;
    }

    public void setTypeOperation(Integer typeOperation) {
        this.typeOperation = typeOperation;
    }

    public String getLibelle() {
        return libelle;
    }

    public LigneCaisse libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public BigDecimal getMontantOperation() {
        return montantOperation;
    }

    public LigneCaisse montantOperation(BigDecimal montantOperation) {
        this.montantOperation = montantOperation;
        return this;
    }

    public void setMontantOperation(BigDecimal montantOperation) {
        this.montantOperation = montantOperation;
    }

    public QuittanceMensuelle getQuittanceMensuelle() {
        return quittanceMensuelle;
    }

    public LigneCaisse quittanceMensuelle(QuittanceMensuelle quittanceMensuelle) {
        this.quittanceMensuelle = quittanceMensuelle;
        return this;
    }

    public void setQuittanceMensuelle(QuittanceMensuelle quittanceMensuelle) {
        this.quittanceMensuelle = quittanceMensuelle;
    }

    public DeclarationAnnuelle getDeclarationAnnuelle() {
        return declarationAnnuelle;
    }

    public LigneCaisse declarationAnnuelle(DeclarationAnnuelle declarationAnnuelle) {
        this.declarationAnnuelle = declarationAnnuelle;
        return this;
    }

    public void setDeclarationAnnuelle(DeclarationAnnuelle declarationAnnuelle) {
        this.declarationAnnuelle = declarationAnnuelle;
    }

    public Cnss getCnss() {
        return cnss;
    }

    public LigneCaisse cnss(Cnss cnss) {
        this.cnss = cnss;
        return this;
    }

    public void setCnss(Cnss cnss) {
        this.cnss = cnss;
    }

    public Caisse getCaisse() {
        return caisse;
    }

    public LigneCaisse caisse(Caisse caisse) {
        this.caisse = caisse;
        return this;
    }

    public void setCaisse(Caisse caisse) {
        this.caisse = caisse;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LigneCaisse)) {
            return false;
        }
        return id != null && id.equals(((LigneCaisse) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "LigneCaisse{" +
            "id=" + getId() +
            ", typeOperation=" + getTypeOperation() +
            ", libelle='" + getLibelle() + "'" +
            ", montantOperation=" + getMontantOperation() +
            "}";
    }
}
