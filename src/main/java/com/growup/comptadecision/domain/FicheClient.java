package com.growup.comptadecision.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.growup.comptadecision.domain.enumeration.CategorieClient;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A FicheClient.
 */
@Entity
@Table(name = "fiche_client")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@EqualsAndHashCode
@Builder
@ToString
public class FicheClient extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    public FicheClient(Long id) {
        this.id = id;
    }

    public FicheClient() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "categorie_client")
    private CategorieClient categorieClient;

    @NotNull
    @Column(name = "designation", nullable = false)
    private String designation;

    @Lob
    @Column(name = "logo")
    private byte[] logo;

    @Column(name = "logo_content_type")
    private String logoContentType;

    @NotNull
    @Column(name = "adresse", nullable = false)
    private String adresse;

    @NotNull
    @Column(name = "code_postal", nullable = false)
    private String codePostal;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "matricule_fiscale", nullable = false)
    private String matriculeFiscale;

    @Column(name = "registre_commerce")
    private String registreCommerce;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @Column(name = "cnss_employeur")
    private String cnssEmployeur;

    @Column(name = "cnss_gerant")
    private String cnssGerant;

    @Lob
    @Column(name = "fichier_patente")
    private byte[] fichierPatente;

    @Column(name = "fichier_patente_content_type")
    private String fichierPatenteContentType;

    @ManyToOne
    @JsonIgnoreProperties("ficheClients")
    private SecteurActivite secteurActivite;

    @ManyToOne
    @JsonIgnoreProperties("ficheClients")
    private Activite activite;

    @ManyToOne
    @JsonIgnoreProperties("ficheClients")
    private Activite activiteScondaire;

    @ManyToOne
    @JsonIgnoreProperties("ficheClients")
    private Region region;

    @ManyToOne
    @JsonIgnoreProperties("ficheClients")
    private Ville ville;

    @OneToMany(mappedBy = "ficheClient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImpotMensuelClient> impotMensuelClients = new ArrayList<>();

    public Activite getActiviteScondaire() {
        return activiteScondaire;
    }

    public void setActiviteScondaire(Activite activiteScondaire) {
        this.activiteScondaire = activiteScondaire;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategorieClient getCategorieClient() {
        return categorieClient;
    }

    public FicheClient categorieClient(CategorieClient categorieClient) {
        this.categorieClient = categorieClient;
        return this;
    }

    public void setCategorieClient(CategorieClient categorieClient) {
        this.categorieClient = categorieClient;
    }

    public String getDesignation() {
        return designation;
    }

    public FicheClient designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public byte[] getLogo() {
        return logo;
    }

    public FicheClient logo(byte[] logo) {
        this.logo = logo;
        return this;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public void addImpotMensuelClient(ImpotMensuelClient impotMensuelClient) {
        impotMensuelClients.add(impotMensuelClient);
        impotMensuelClient.setFicheClient(this);
    }

    public void removeImpotMensuelClient(ImpotMensuelClient impotMensuelClient) {
        impotMensuelClients.remove(impotMensuelClient);
        impotMensuelClient.setFicheClient(null);
    }

    public String getLogoContentType() {
        return logoContentType;
    }

    public FicheClient logoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
        return this;
    }

    public void setLogoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
    }

    public String getAdresse() {
        return adresse;
    }

    public FicheClient adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public FicheClient codePostal(String codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getTelephone() {
        return telephone;
    }

    public FicheClient telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public FicheClient fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public FicheClient email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatriculeFiscale() {
        return matriculeFiscale;
    }

    public FicheClient matriculeFiscale(String matriculeFiscale) {
        this.matriculeFiscale = matriculeFiscale;
        return this;
    }

    public void setMatriculeFiscale(String matriculeFiscale) {
        this.matriculeFiscale = matriculeFiscale;
    }

    public String getRegistreCommerce() {
        return registreCommerce;
    }

    public FicheClient registreCommerce(String registreCommerce) {
        this.registreCommerce = registreCommerce;
        return this;
    }

    public void setRegistreCommerce(String registreCommerce) {
        this.registreCommerce = registreCommerce;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public FicheClient dateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getCnssEmployeur() {
        return cnssEmployeur;
    }

    public FicheClient cnssEmployeur(String cnssEmployeur) {
        this.cnssEmployeur = cnssEmployeur;
        return this;
    }

    public void setCnssEmployeur(String cnssEmployeur) {
        this.cnssEmployeur = cnssEmployeur;
    }

    public String getCnssGerant() {
        return cnssGerant;
    }

    public FicheClient cnssGerant(String cnssGerant) {
        this.cnssGerant = cnssGerant;
        return this;
    }

    public void setCnssGerant(String cnssGerant) {
        this.cnssGerant = cnssGerant;
    }

    public byte[] getFichierPatente() {
        return fichierPatente;
    }

    public FicheClient fichierPatente(byte[] fichierPatente) {
        this.fichierPatente = fichierPatente;
        return this;
    }

    public void setFichierPatente(byte[] fichierPatente) {
        this.fichierPatente = fichierPatente;
    }

    public String getFichierPatenteContentType() {
        return fichierPatenteContentType;
    }

    public FicheClient fichierPatenteContentType(String fichierPatenteContentType) {
        this.fichierPatenteContentType = fichierPatenteContentType;
        return this;
    }

    public void setFichierPatenteContentType(String fichierPatenteContentType) {
        this.fichierPatenteContentType = fichierPatenteContentType;
    }

    public SecteurActivite getSecteurActivite() {
        return secteurActivite;
    }

    public FicheClient secteurActivite(SecteurActivite secteurActivite) {
        this.secteurActivite = secteurActivite;
        return this;
    }

    public void setSecteurActivite(SecteurActivite secteurActivite) {
        this.secteurActivite = secteurActivite;
    }

    public Activite getActivite() {
        return activite;
    }

    public FicheClient activite(Activite activite) {
        this.activite = activite;
        return this;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public FicheClient activiteScondaire(Activite activite) {
        this.activiteScondaire = activite;
        return this;
    }

    public List<ImpotMensuelClient> getImpotMensuelClients() {
        return impotMensuelClients;
    }

    public void setImpotMensuelClients(List<ImpotMensuelClient> impotMensuelClients) {
        this.impotMensuelClients = impotMensuelClients;
    }

    public Region getRegion() {
        return region;
    }

    public FicheClient region(Region region) {
        this.region = region;
        return this;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Ville getVille() {
        return ville;
    }

    public FicheClient ville(Ville ville) {
        this.ville = ville;
        return this;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

}
