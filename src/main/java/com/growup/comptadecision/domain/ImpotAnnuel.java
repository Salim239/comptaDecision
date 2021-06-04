package com.growup.comptadecision.domain;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * A ImpotMensuel.
 */
@Entity
@Table(name = "impot_annuel")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@EqualsAndHashCode
@Builder
@ToString
public class ImpotAnnuel extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    public ImpotAnnuel() {
    }

    public ImpotAnnuel(Long id) {
        this.id = id;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Column(name = "description")
    private String description;

    @Column(name = "calcule")
    private Boolean calcule = Boolean.FALSE;

    @Column(name = "tri_ordre")
    private Integer triOrdre;

    @OneToMany(mappedBy = "impotAnnuel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImpotAnnuelLigne> impotAnnuelLignes = new ArrayList<>();

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

    public Boolean getCalcule() {
        return calcule;
    }

    public void setCalcule(Boolean calcule) {
        this.calcule = calcule;
    }

    public Integer getTriOrdre() {
        return triOrdre;
    }

    public void setTriOrdre(Integer triOrdre) {
        this.triOrdre = triOrdre;
    }

    public List<ImpotAnnuelLigne> getImpotAnnuelLignes() {
        return impotAnnuelLignes;
    }

    public void setImpotAnnuelLignes(List<ImpotAnnuelLigne> impotAnnuelLignes) {
        this.impotAnnuelLignes = impotAnnuelLignes;
    }
}
