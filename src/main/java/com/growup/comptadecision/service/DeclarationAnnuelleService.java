package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.*;
import com.growup.comptadecision.domain.enumeration.StatutDeclaration;
import com.growup.comptadecision.domain.enumeration.TypeAlert;
import com.growup.comptadecision.domain.enumeration.TypeDeclaration;
import com.growup.comptadecision.repository.DeclarationAnnuelleRepository;
import com.growup.comptadecision.repository.FicheClientRepository;
import com.growup.comptadecision.repository.ImpotAnnuelRepository;
import com.growup.comptadecision.repository.QuittanceMensuelleSousLigneRepository;
import com.growup.comptadecision.security.SecurityUtils;
import com.growup.comptadecision.service.dto.BusinessAlertDTO;
import com.growup.comptadecision.service.dto.DeclarationAnnuelleDTO;
import com.growup.comptadecision.service.mapper.DeclarationAnnuelleMapper;
import com.growup.comptadecision.web.rest.errors.BusinessErrorException;
import com.growup.comptadecision.web.rest.errors.ErrorConstants;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.growup.comptadecision.domain.enumeration.CodeAlert.*;

/**
 * Service Implementation for managing DeclarationAnnuelle.
 */
@Service
@Transactional
public class DeclarationAnnuelleService {

    private final Logger log = LoggerFactory.getLogger(DeclarationAnnuelleService.class);

    private final DeclarationAnnuelleRepository declarationAnnuelleRepository;

    private final FicheClientRepository ficheClientRepository;

    private final ImpotAnnuelRepository impotAnnuelRepository;

    private final QuittanceMensuelleSousLigneRepository quittanceMensuelleSousLigneRepository;

    private final DeclarationAnnuelleMapper declarationAnnuelleMapper;

    private final AcompteProvisionnelService acompteProvisionnelService;

    public DeclarationAnnuelleService(DeclarationAnnuelleRepository declarationAnnuelleRepository, DeclarationAnnuelleMapper declarationAnnuelleMapper,
                                      FicheClientRepository ficheClientRepository, ImpotAnnuelRepository impotAnnuelRepository, QuittanceMensuelleSousLigneRepository quittanceMensuelleSousLigneRepository, AcompteProvisionnelService acompteProvisionnelService) {
        this.declarationAnnuelleRepository = declarationAnnuelleRepository;
        this.declarationAnnuelleMapper = declarationAnnuelleMapper;
        this.ficheClientRepository = ficheClientRepository;
        this.impotAnnuelRepository = impotAnnuelRepository;
        this.quittanceMensuelleSousLigneRepository = quittanceMensuelleSousLigneRepository;
        this.acompteProvisionnelService = acompteProvisionnelService;
    }

    /**
     * Save a declarationAnnuelle.
     *
     * @param declarationAnnuelleDTO the entity to save
     * @return the persisted entity
     */
    public DeclarationAnnuelleDTO save(DeclarationAnnuelleDTO declarationAnnuelleDTO) {
        log.debug("Request to save DeclarationAnnuelle : {}", declarationAnnuelleDTO);
        DeclarationAnnuelle declarationAnnuelle = declarationAnnuelleMapper.toEntity(declarationAnnuelleDTO);
        declarationAnnuelle.setMontantNet(calculerMontantNet(declarationAnnuelle));
        StatutDeclaration statut = updateStatut(declarationAnnuelle);
        declarationAnnuelle.setStatut(statut);
        declarationAnnuelle = declarationAnnuelleRepository.save(declarationAnnuelle);
        return declarationAnnuelleMapper.toDto(declarationAnnuelle);
    }


    private StatutDeclaration updateStatut(DeclarationAnnuelle declarationAnnuelle) {
        if (declarationAnnuelle.getTypeDeclaration() == TypeDeclaration.DECLARATION_RECTIFICATIVE) {
            DeclarationAnnuelle declarationAnnuelleInitiale = declarationAnnuelleRepository.
                findByAnneeAndFicheClientIdAndTypeDeclaration(
                    declarationAnnuelle.getAnnee(),
                    declarationAnnuelle.getFicheClient().getId(),
                    TypeDeclaration.DECLARATION_INITIALE).orElseThrow(() -> new BusinessErrorException(String.format("Il n'existe pas de déclaration annuelle initiale pour le client %s et l'annee %s",
                declarationAnnuelle.getFicheClient().getId(), declarationAnnuelle.getAnnee())));
            declarationAnnuelleInitiale.setStatut(StatutDeclaration.BROUILLON);
            declarationAnnuelleRepository.save(declarationAnnuelleInitiale);
            if (declarationAnnuelle.getDatePaiement() != null && declarationAnnuelle.getNumeroQuittance() != null) {
                return StatutDeclaration.VALIDE;
            } else {
                return StatutDeclaration.BROUILLON;
            }
        } else {
            if (declarationAnnuelle.getTypeDeclaration() == TypeDeclaration.DECLARATION_INITIALE &&
                declarationAnnuelle.getDatePaiement() != null &&
                declarationAnnuelle.getNumeroQuittance() != null) {
                return StatutDeclaration.VALIDE;
            } else {
                return StatutDeclaration.BROUILLON;
            }
        }
    }

    /**
     * Get all the declarationAnnuelles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<DeclarationAnnuelleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DeclarationAnnuelles");
        String creator = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new UsernameNotFoundException("Only loggued user can access"));
        return declarationAnnuelleRepository.findAllByCreatedBy(creator, pageable)
            .map(declarationAnnuelleMapper::toDto);
    }


    /**
     * Get one declarationAnnuelle by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<DeclarationAnnuelleDTO> findOne(Long id) {
        log.debug("Request to get DeclarationAnnuelle : {}", id);
        Optional<DeclarationAnnuelleDTO> declarationAnnuelleDTOOptional =  declarationAnnuelleRepository.findById(id).map(declarationAnnuelleMapper::toDto);
        if (declarationAnnuelleDTOOptional.isPresent()) {
            DeclarationAnnuelleDTO declarationAnnuelleDTO = declarationAnnuelleDTOOptional.get();
            declarationAnnuelleDTO.setMontantReportAnterieurCalc(calculerMontantReportAnterieur(declarationAnnuelleDTO.getFicheClientId(), declarationAnnuelleDTO.getAnnee()));
            declarationAnnuelleDTO.setMontantApPayesCalc(calculerMontantAps(declarationAnnuelleDTO.getFicheClientId(), declarationAnnuelleDTO.getAnnee()));
            return Optional.of(declarationAnnuelleDTO);
        } else {
            return declarationAnnuelleDTOOptional;
        }
    }

    /**
     * Delete the declarationAnnuelle by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete DeclarationAnnuelle : {}", id);
        declarationAnnuelleRepository.deleteById(id);
    }

    private DeclarationAnnuelleDTO getDeclarationRectificativeForDeclarationInitiale(DeclarationAnnuelle declarationAnnuelle) {

        Optional<DeclarationAnnuelle> declarationRectificativeOpt = declarationAnnuelleRepository.findByAnneeAndFicheClientIdAndTypeDeclaration(
                declarationAnnuelle.getAnnee(),
                declarationAnnuelle.getFicheClient().getId(),
                TypeDeclaration.DECLARATION_RECTIFICATIVE);

        if (declarationRectificativeOpt.isPresent() && StringUtils.isNotBlank(declarationRectificativeOpt.get().getNumeroQuittance())) {
            DeclarationAnnuelle declarationRectificative = declarationRectificativeOpt.get();
            declarationRectificative.setDeclarationAnnuelleLignes(new ArrayList<>());
            initDeclarationAnnuelLignes(declarationRectificative);
            return declarationAnnuelleMapper.toDto(declarationRectificative);
        } else {

            DeclarationAnnuelleDTO quittanceRectificativeDTO = declarationAnnuelleMapper.toDto(declarationAnnuelle);
            quittanceRectificativeDTO.setTypeDeclaration(TypeDeclaration.DECLARATION_RECTIFICATIVE);
            quittanceRectificativeDTO.setId(null);
            quittanceRectificativeDTO.setDatePaiement(null);
            quittanceRectificativeDTO.setNumeroQuittance(null);
            BigDecimal montantAps = calculerMontantAps(quittanceRectificativeDTO.getFicheClientId(), quittanceRectificativeDTO.getAnnee());
            BigDecimal reportAnterieur = calculerMontantReportAnterieur(quittanceRectificativeDTO.getFicheClientId(), quittanceRectificativeDTO.getAnnee());
            quittanceRectificativeDTO.setMontantApPayesCalc(montantAps);
            quittanceRectificativeDTO.setMontantReportAnterieurCalc(reportAnterieur);
            return quittanceRectificativeDTO;
        }
    }

    private void initDeclarationAnnuelLignes(DeclarationAnnuelle declarationAnnuelle) {

        List<ImpotAnnuel> impotAnnuels = impotAnnuelRepository.findAll();
        List<DecalrationAnnuelleLigne> declarationAnnuelleLignes = impotAnnuels.stream()
                .map(impotAnnuel -> {
                    DecalrationAnnuelleLigne declarationAnnuelleLigne = new DecalrationAnnuelleLigne();
                    declarationAnnuelleLigne.setCode(impotAnnuel.getCode());
                    declarationAnnuelleLigne.setDescription(impotAnnuel.getDescription());
                    declarationAnnuelleLigne.setLibelle(impotAnnuel.getLibelle());
                    declarationAnnuelleLigne.setCalcule(impotAnnuel.getCalcule());
                    declarationAnnuelleLigne.setTriOrdre(impotAnnuel.getTriOrdre());
                    declarationAnnuelleLigne.setImpotAnnuel(impotAnnuel);
                    List<String> impotMensuelLigneToSum = impotAnnuel.getImpotAnnuelLignes().stream()
                            .map(ImpotAnnuelLigne::getImpotMensuelLigne)
                            .map(ImpotMensuelLigne::getCode)
                            .collect(Collectors.toList());
                    BigDecimal montantcalcule = null;
                    if (BooleanUtils.isTrue(impotAnnuel.getCalcule()) && !impotMensuelLigneToSum.isEmpty()) {
                        montantcalcule = quittanceMensuelleSousLigneRepository.sumMontantBaseByFicheClientIdAndByAnneeAndByCodes(
                                declarationAnnuelle.getFicheClient().getId(),
                                declarationAnnuelle.getAnnee(),
                                impotMensuelLigneToSum);
                        declarationAnnuelleLigne.setMontantCalcule(montantcalcule);
                    }
                    return declarationAnnuelleLigne;
                }).collect(Collectors.toList());
        declarationAnnuelle.setDeclarationAnnuelleLignes(declarationAnnuelleLignes);
    }

    private DeclarationAnnuelleDTO getEmptyDeclarationAnnuelle(FicheClient ficheClient, Integer annee) {

        Optional<DeclarationAnnuelle> declarationAnnuelleOptional = declarationAnnuelleRepository.findByAnneeAndFicheClientIdAndTypeDeclaration(annee,
                ficheClient.getId(), TypeDeclaration.DECLARATION_INITIALE);

        //Si existe déjà une déclartion annuelle initiale avec un numéro (donc validée) alors créer une quittance type corrective
        //Sinon si la quittance initiale trouvée de possède pas de numéro alors l'éditer
        //Enfin si pas de quittance initiale trouvée alors créé une nouvelle
        if (declarationAnnuelleOptional.isPresent()) {

            return StringUtils.isNotBlank(declarationAnnuelleOptional.get().getNumeroQuittance()) ?
                    getDeclarationRectificativeForDeclarationInitiale(declarationAnnuelleOptional.get()) :
                    declarationAnnuelleMapper.toDto(declarationAnnuelleOptional.get());

        } else {

            DeclarationAnnuelle declarationAnnuelleInitiale = new DeclarationAnnuelle(
                    ficheClient,
                    annee,
                    TypeDeclaration.DECLARATION_INITIALE);
            initDeclarationAnnuelLignes(declarationAnnuelleInitiale);
            declarationAnnuelleInitiale.setMontantNet(calculerMontantNet(declarationAnnuelleInitiale));
            DeclarationAnnuelleDTO declarationAnnuelleDTO = declarationAnnuelleMapper.toDto(declarationAnnuelleInitiale);
            BigDecimal montantAps = calculerMontantAps(declarationAnnuelleDTO.getFicheClientId(), declarationAnnuelleDTO.getAnnee());
            BigDecimal reportAnterieur = calculerMontantReportAnterieur(declarationAnnuelleDTO.getFicheClientId(), declarationAnnuelleDTO.getAnnee());
            declarationAnnuelleDTO.setMontantApPayesCalc(montantAps);
            declarationAnnuelleDTO.setMontantApPayes(montantAps);
            declarationAnnuelleDTO.setMontantReportAnterieur(reportAnterieur);
            declarationAnnuelleDTO.setMontantReportAnterieurCalc(reportAnterieur);
            return declarationAnnuelleDTO;
        }
    }

    private BigDecimal calculerMontantNet(DeclarationAnnuelle declarationAnnuelle) {
        return declarationAnnuelle.getMontantImpotAnnuel()
            .add(declarationAnnuelle.getMontantRetenueSource())
            .subtract(declarationAnnuelle.getMontantApPayes())
            .subtract(declarationAnnuelle.getMontantReportAnterieur())
            ;
    }

    private BigDecimal calculerMontantAps(Long FicheClientId, Integer annee) {
        return acompteProvisionnelService.sumAcomptePrevisionnelPositifs(FicheClientId, annee);
    }

    private BigDecimal calculerMontantReportAnterieur(Long ficheClientId, Integer annee) {
        AcompteProvisionnel acompteProvisionnel = acompteProvisionnelService.findByFicheClientIdAndAnneeAndNumeroAndNotArchived(ficheClientId, annee, 1)
            .orElseGet(() -> {
                //todo mange error
                //new BusinessErrorException("Impossible de calculer le report antérieur car il n'existe pas d'accopte provisionnel numéro 1"));
                return new AcompteProvisionnel();
            });
        return acompteProvisionnel.getMontantReportAnterieur() != null ? acompteProvisionnel.getMontantReportAnterieur() : BigDecimal.ZERO;
    }

    private List<BusinessAlertDTO> validateCreationForm(FicheClient ficheClient, Integer annee, TypeDeclaration typeDeclaration) {

        List<BusinessAlertDTO> businessAlerts = new ArrayList<>();

        if (ficheClient.getDateCreation().getYear() > annee) {
            List<DeclarationAnnuelle> declarationAnnuelles = declarationAnnuelleRepository.findByAnneeAndFicheClientId(annee - 1, ficheClient.getId());
            if (declarationAnnuelles.isEmpty()) {
                BusinessAlertDTO businessAlert = new BusinessAlertDTO(TypeAlert.warning, WARNING_DECLARATION_ANNUELLE_PRECEDENTE_INNEXISTANTE);
                businessAlert.addParam("annee", annee - 1);
                businessAlerts.add(businessAlert);
            } else {
                if (declarationAnnuelles.stream().anyMatch(declarationAnnuelle -> declarationAnnuelle.getStatut() == StatutDeclaration.BROUILLON)) {
                    BusinessAlertDTO  businessAlert = new BusinessAlertDTO(TypeAlert.warning, WARNING_QUITTANCE_PRECEDENTE_NON_VALIDE);
                    businessAlert.addParam("annee", annee);
                    businessAlerts.add(businessAlert);
                }
            }
        }

        List<DeclarationAnnuelle> declarationAnnuelles = declarationAnnuelleRepository.findByAnneeAndFicheClientId(annee, ficheClient.getId());
        if (declarationAnnuelles.isEmpty() && typeDeclaration.equals(TypeDeclaration.DECLARATION_RECTIFICATIVE)) {
            throw new BusinessErrorException(ErrorConstants.ERR_DECLARATION_RECTIFICATIVE_SANS_QUITTANCE_INITIALE);
        }
        if (declarationAnnuelles.size() == 1 && typeDeclaration.equals(TypeDeclaration.DECLARATION_INITIALE)) {
            throw new BusinessErrorException(ErrorConstants.ERR_DECLARATION_INITIALE_EXISTE);
        }
        if (declarationAnnuelles.size() == 0 && typeDeclaration.equals(TypeDeclaration.DECLARATION_RECTIFICATIVE)) {
            throw new BusinessErrorException(ErrorConstants.ERR_DECLARATION_RECTIFICATIVE_SANS_QUITTANCE_INITIALE);
        }
        if (declarationAnnuelles.size() == 1 && typeDeclaration.equals(TypeDeclaration.DECLARATION_RECTIFICATIVE) &&
                declarationAnnuelles.get(0).getStatut().equals(StatutDeclaration.BROUILLON)) {
            BusinessAlertDTO businessAlert = new BusinessAlertDTO(TypeAlert.warning, WARNING_QUITTANCE_INITIALE_NON_VALIDE);
            businessAlert.addParam("annee", annee);
            businessAlerts.add(businessAlert);
        }
        if (declarationAnnuelles.size() == 2 && typeDeclaration.equals(TypeDeclaration.DECLARATION_RECTIFICATIVE)) {
            throw new BusinessErrorException(ErrorConstants.ERR_DECLARATION_RECTIFICATIVE_INEXISTANTE);
        }

        return businessAlerts;
    }

    public DeclarationAnnuelleDTO init(Long ficheClientId, Integer annee, TypeDeclaration typeDeclaration) {
        log.debug("Request to init new DeclarationAnnuelle for year {} client id {} and declaration type {}", annee, ficheClientId,  typeDeclaration);
        FicheClient ficheClient = ficheClientRepository.findById(ficheClientId).orElseThrow(() -> new BusinessErrorException(String.format("FicheClient not found with id %s", ficheClientId)));
        List<BusinessAlertDTO> businessAlerts = validateCreationForm(ficheClient, annee, typeDeclaration);
        DeclarationAnnuelleDTO declarationAnnuelle = getEmptyDeclarationAnnuelle(ficheClient, annee);
        declarationAnnuelle.setBusinessAlerts(businessAlerts);
        return declarationAnnuelle;
    }

}
