package com.growup.comptadecision.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.growup.comptadecision.domain.enumeration.TypeValeur;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * A Region.
 */
@Entity
@Table(name = "impot_mensuel_detail")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//@Data
@EqualsAndHashCode
@Builder
@ToString
public class ImpotMensuelDetail extends AbstractAuditingEntity {

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

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Column(name = "valeur")
    private Float valeur;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_valeur")
    private TypeValeur typeValeur = TypeValeur.TAUX;

    @Column(name = "valeur_modifiable")
    private Boolean valeurModifiable;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("impotMensuelDetails")
    private ImpotMensuel impotMensuel;

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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Float getValeur() {
        return valeur;
    }

    public void setValeur(Float valeur) {
        this.valeur = valeur;
    }

    public TypeValeur getTypeValeur() {
        return typeValeur;
    }

    public void setTypeValeur(TypeValeur typeValeur) {
        this.typeValeur = typeValeur;
    }

    public Boolean getValeurModifiable() {
        return valeurModifiable;
    }

    public void setValeurModifiable(Boolean valeurModifiable) {
        this.valeurModifiable = valeurModifiable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImpotMensuel getImpotMensuel() {
        return impotMensuel;
    }

    public void setImpotMensuel(ImpotMensuel impotMensuel) {
        this.impotMensuel = impotMensuel;
    }
}
