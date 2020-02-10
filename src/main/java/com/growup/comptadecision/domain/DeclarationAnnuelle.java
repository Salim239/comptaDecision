package com.growup.comptadecision.domain;


import com.growup.comptadecision.domain.enumeration.StatutDeclaration;
import com.growup.comptadecision.domain.enumeration.TypeDeclaration;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A DeclarationAnnuelle.
 */
@Entity
@Table(name = "declaration_annuelle")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@EqualsAndHashCode
@Builder
@ToString
public class DeclarationAnnuelle extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    public DeclarationAnnuelle() {
    }

    public DeclarationAnnuelle(FicheClient ficheClient, @NotNull Integer annee, @NotNull TypeDeclaration typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
        this.annee = annee;
        this.ficheClient = ficheClient;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_declaration", nullable = false)
    private TypeDeclaration typeDeclaration;

    @NotNull
    @Column(name = "annee", nullable = false)
    private Integer annee;

    @Column(name = "date_paiement")
    private LocalDate datePaiement;

    @Column(name = "numero")
    private String numeroQuittance;

    @Column(name = "montant")
    private BigDecimal montant;

    @Column(name = "statut")
    @Enumerated(EnumType.STRING)
    private StatutDeclaration statut;

    @OneToMany(mappedBy = "declarationAnnuelle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DecalrationAnnuelleDetail> declarationAnnuelleDetails = new ArrayList<>();

    @ManyToOne
    private FicheClient ficheClient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeDeclaration getTypeDeclaration() {
        return typeDeclaration;
    }

    public void setTypeDeclaration(TypeDeclaration typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    public String getNumeroQuittance() {
        return numeroQuittance;
    }

    public void setNumeroQuittance(String numeroQuittance) {
        this.numeroQuittance = numeroQuittance;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public StatutDeclaration getStatut() {
        return statut;
    }

    public void setStatut(StatutDeclaration statut) {
        this.statut = statut;
    }

    public FicheClient getFicheClient() {
        return ficheClient;
    }

    public void setFicheClient(FicheClient ficheClient) {
        this.ficheClient = ficheClient;
    }

    public List<DecalrationAnnuelleDetail> getDeclarationAnnuelleDetails() {
        return declarationAnnuelleDetails;
    }

    public void setDeclarationAnnuelleDetails(List<DecalrationAnnuelleDetail> declarationAnnuelleDetails) {
        this.declarationAnnuelleDetails = declarationAnnuelleDetails;
    }
}
