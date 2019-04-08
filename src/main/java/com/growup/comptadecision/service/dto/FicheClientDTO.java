package com.growup.comptadecision.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.*;
import javax.persistence.Lob;
import com.growup.comptadecision.domain.enumeration.CategorieClient;

import static java.util.stream.Collectors.groupingBy;

/**
 * A DTO for the FicheClient entity.
 */
public class FicheClientDTO implements Serializable {

    private Long id;

    private CategorieClient categorieClient;

    @NotNull
    private String designation;

    @Lob
    private byte[] logo;

    private String logoContentType;
    @NotNull
    private String adresse;

    @NotNull
    private String codePostal;

    private String telephone;

    private String fax;

    private String email;

    @NotNull
    private String matriculeFiscale;

    private String registreCommerce;

    private LocalDate dateCreation;

    private String cnssEmployeur;

    private String cnssGerant;

    @Lob
    private byte[] fichierPatente;

    private String fichierPatenteContentType;

    private Long secteurActiviteId;

    private String secteurActiviteLibelle;

    @Override
    public String toString() {
        return "FicheClientDTO{" +
                "id=" + id +
                ", categorieClient=" + categorieClient +
                ", designation='" + designation + '\'' +
                ", logo=" + Arrays.toString(logo) +
                ", logoContentType='" + logoContentType + '\'' +
                ", adresse='" + adresse + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", telephone='" + telephone + '\'' +
                ", fax='" + fax + '\'' +
                ", email='" + email + '\'' +
                ", matriculeFiscale='" + matriculeFiscale + '\'' +
                ", registreCommerce='" + registreCommerce + '\'' +
                ", dateCreation=" + dateCreation +
                ", cnssEmployeur='" + cnssEmployeur + '\'' +
                ", cnssGerant='" + cnssGerant + '\'' +
                ", fichierPatente=" + Arrays.toString(fichierPatente) +
                ", fichierPatenteContentType='" + fichierPatenteContentType + '\'' +
                ", secteurActiviteId=" + secteurActiviteId +
                ", secteurActiviteLibelle='" + secteurActiviteLibelle + '\'' +
                ", activiteId=" + activiteId +
                ", activiteLibelle='" + activiteLibelle + '\'' +
                ", activiteScondaireId=" + activiteScondaireId +
                ", activiteScondaireLibelle='" + activiteScondaireLibelle + '\'' +
                ", regionId=" + regionId +
                ", regionLibelle='" + regionLibelle + '\'' +
                ", villeId=" + villeId +
                ", villeLibelle='" + villeLibelle + '\'' +
                ", impotMensuelClients=" + impotMensuelClients +
                ", impotMensuelClientsGroupedByMois=" + impotMensuelClientsGroupedByMois +
                '}';
    }

    private Long activiteId;

    private String activiteLibelle;

    private Long activiteScondaireId;

    private String activiteScondaireLibelle;

    private Long regionId;

    private String regionLibelle;

    private Long villeId;

    private String villeLibelle;

    public String getSecteurActiviteLibelle() {
        return secteurActiviteLibelle;
    }

    public void setSecteurActiviteLibelle(String secteurActiviteLibelle) {
        this.secteurActiviteLibelle = secteurActiviteLibelle;
    }

    public String getActiviteLibelle() {
        return activiteLibelle;
    }

    public void setActiviteLibelle(String activiteLibelle) {
        this.activiteLibelle = activiteLibelle;
    }

    public String getActiviteScondaireLibelle() {
        return activiteScondaireLibelle;
    }

    public void setActiviteScondaireLibelle(String activiteScondaireLibelle) {
        this.activiteScondaireLibelle = activiteScondaireLibelle;
    }

    public String getRegionLibelle() {
        return regionLibelle;
    }

    public void setRegionLibelle(String regionLibelle) {
        this.regionLibelle = regionLibelle;
    }

    public String getVilleLibelle() {
        return villeLibelle;
    }

    public void setVilleLibelle(String villeLibelle) {
        this.villeLibelle = villeLibelle;
    }

    public void setImpotMensuelClientsGroupedByMois(Map<Integer, List<ImpotMensuelClientDTO>> impotMensuelClientsGroupedByMois) {
        this.impotMensuelClientsGroupedByMois = impotMensuelClientsGroupedByMois;
    }

    private List<ImpotMensuelClientDTO> impotMensuelClients = new ArrayList<>();

    private Map<Integer, List<ImpotMensuelClientDTO>> impotMensuelClientsGroupedByMois = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategorieClient getCategorieClient() {
        return categorieClient;
    }

    public void setCategorieClient(CategorieClient categorieClient) {
        this.categorieClient = categorieClient;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getLogoContentType() {
        return logoContentType;
    }

    public void setLogoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatriculeFiscale() {
        return matriculeFiscale;
    }

    public void setMatriculeFiscale(String matriculeFiscale) {
        this.matriculeFiscale = matriculeFiscale;
    }

    public String getRegistreCommerce() {
        return registreCommerce;
    }

    public void setRegistreCommerce(String registreCommerce) {
        this.registreCommerce = registreCommerce;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getCnssEmployeur() {
        return cnssEmployeur;
    }

    public void setCnssEmployeur(String cnssEmployeur) {
        this.cnssEmployeur = cnssEmployeur;
    }

    public String getCnssGerant() {
        return cnssGerant;
    }

    public void setCnssGerant(String cnssGerant) {
        this.cnssGerant = cnssGerant;
    }

    public byte[] getFichierPatente() {
        return fichierPatente;
    }

    public void setFichierPatente(byte[] fichierPatente) {
        this.fichierPatente = fichierPatente;
    }

    public String getFichierPatenteContentType() {
        return fichierPatenteContentType;
    }

    public void setFichierPatenteContentType(String fichierPatenteContentType) {
        this.fichierPatenteContentType = fichierPatenteContentType;
    }

    public Long getSecteurActiviteId() {
        return secteurActiviteId;
    }

    public void setSecteurActiviteId(Long secteurActiviteId) {
        this.secteurActiviteId = secteurActiviteId;
    }

    public Long getActiviteId() {
        return activiteId;
    }

    public void setActiviteId(Long activiteId) {
        this.activiteId = activiteId;
    }

    public Long getActiviteScondaireId() {
        return activiteScondaireId;
    }

    public void setActiviteScondaireId(Long activiteScondaireId) {
        this.activiteScondaireId = activiteScondaireId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Long getVilleId() {
        return villeId;
    }

    public void setVilleId(Long villeId) {
        this.villeId = villeId;
    }

    public List<ImpotMensuelClientDTO> getImpotMensuelClients() {
        return impotMensuelClients;
    }

    public void setImpotMensuelClients(List<ImpotMensuelClientDTO> impotMensuelClients) {
        this.impotMensuelClients = impotMensuelClients;
    }

    public Map<Integer, List<ImpotMensuelClientDTO>> getImpotMensuelClientsGroupedByMois() {
        return getImpotMensuelClients().stream()
                .sorted(Comparator.comparing(ImpotMensuelClientDTO::getImpotMensuelLibelle).reversed())
                .collect(groupingBy(ImpotMensuelClientDTO::getMois));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FicheClientDTO ficheClientDTO = (FicheClientDTO) o;
        if (ficheClientDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ficheClientDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
