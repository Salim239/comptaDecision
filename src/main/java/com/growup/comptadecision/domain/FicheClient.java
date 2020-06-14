package com.growup.comptadecision.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.growup.comptadecision.domain.enumeration.CategorieActivite;
import com.growup.comptadecision.domain.enumeration.CategorieClient;
import com.growup.comptadecision.domain.enumeration.CodeTVA;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    /**
     * Utilisé dans le matricule
     */
    @Column(name = "numero_etablissement_secondaire")
    private String numeroEtablissementSecondaire = "000";

    /**
     * Utilisé dans le matricule
     */
    @Enumerated
    @Column(name = "code_categorie")
    private CategorieActivite categorieActivite;

    @Column(name = "taux_cnss_accident")
    private Double tauxCnssAccident;

    @JoinColumn(name = "administration_cnss_id")
    @ManyToOne
    CentreAdministratif administrationCnss;

    @JoinColumn(name = "administration_fiscale_id")
    @ManyToOne
    CentreAdministratif administrationFiscale;


    @JoinColumn(name = "administration_impot_id")
    @ManyToOne
    CentreAdministratif administrationImpot;

    @JoinColumn(name = "categorie_cnss_gerant_id")
    @ManyToOne
    CategorieCnssGerant categorieCnssGerant;


    public String getNumeroEtablissementSecondaire() {
        return numeroEtablissementSecondaire;
    }

    public void setNumeroEtablissementSecondaire(String numeroEtablissementSecondaire) {
        this.numeroEtablissementSecondaire = numeroEtablissementSecondaire;
    }

    public CategorieActivite getCategorieActivite() {
        return categorieActivite;
    }

    public void setCategorieActivite(CategorieActivite categorieActivite) {
        this.categorieActivite = categorieActivite;
    }

    public Double getTauxCnssAccident() {
        return tauxCnssAccident;
    }

    public void setTauxCnssAccident(Double tauxCnssAccident) {
        this.tauxCnssAccident = tauxCnssAccident;
    }

    public CodeTVA getCodeTva() {
        return codeTva;
    }

    public void setCodeTva(CodeTVA codeTva) {
        this.codeTva = codeTva;
    }

    /**
     * Utilisé dans le matricule
     */
    @Enumerated
    @Column(name = "code_tva")
    private CodeTVA codeTva;

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
    @JoinColumn(name = "activite1_id")
    private Activite activite1;

    @ManyToOne
    @JoinColumn(name = "activite2_id")
    private Activite activite2;

    @ManyToOne
    @JoinColumn(name = "activite3_id")
    private Activite activite3;

    @ManyToOne
    @JsonIgnoreProperties("secteur_activite1_id")
    private SecteurActivite secteurActivite1;

    @ManyToOne
    @JsonIgnoreProperties("secteur_activite2_id")
    private SecteurActivite secteurActivite2;

    @ManyToOne
    @JsonIgnoreProperties("secteur_activite3_id")
    private SecteurActivite secteurActivite3;

    @ManyToOne
    private Region region;

    @ManyToOne
    private Ville ville;

    @OneToMany(mappedBy = "ficheClient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImpotMensuelClient> impotMensuelClients = new ArrayList<>();

    public Activite getActivite1() {
        return activite1;
    }

    public void setActivite1(Activite activite1) {
        this.activite1 = activite1;
    }

    public Activite getActivite2() {
        return activite2;
    }

    public void setActivite2(Activite activite2) {
        this.activite2 = activite2;
    }

    public Activite getActivite3() {
        return activite3;
    }

    public void setActivite3(Activite activite3) {
        this.activite3 = activite3;
    }

    public SecteurActivite getSecteurActivite1() {
        return secteurActivite1;
    }

    public void setSecteurActivite1(SecteurActivite secteurActivite1) {
        this.secteurActivite1 = secteurActivite1;
    }

    public SecteurActivite getSecteurActivite2() {
        return secteurActivite2;
    }

    public void setSecteurActivite2(SecteurActivite secteurActivite2) {
        this.secteurActivite2 = secteurActivite2;
    }

    public SecteurActivite getSecteurActivite3() {
        return secteurActivite3;
    }

    public void setSecteurActivite3(SecteurActivite secteurActivite3) {
        this.secteurActivite3 = secteurActivite3;
    }

    public CentreAdministratif getAdministrationImpot() {
        return administrationImpot;
    }

    public void setAdministrationImpot(CentreAdministratif administrationImpot) {
        this.administrationImpot = administrationImpot;
    }

    public CentreAdministratif getAdministrationFiscale() {
        return administrationFiscale;
    }

    public void setAdministrationFiscale(CentreAdministratif administrationFiscale) {
        this.administrationFiscale = administrationFiscale;
    }

    public CentreAdministratif getAdministrationCnss() {
        return administrationCnss;
    }

    public void setAdministrationCnss(CentreAdministratif administrationCnss) {
        this.administrationCnss = administrationCnss;
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


    public CategorieCnssGerant getCategorieCnssGerant() {
        return categorieCnssGerant;
    }

    public void setCategorieCnssGerant(CategorieCnssGerant categorieCnssGerant) {
        this.categorieCnssGerant = categorieCnssGerant;
    }
}
