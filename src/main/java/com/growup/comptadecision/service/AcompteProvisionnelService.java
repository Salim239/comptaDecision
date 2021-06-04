package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.AcompteProvisionnel;
import com.growup.comptadecision.domain.DeclarationAnnuelle;
import com.growup.comptadecision.domain.FicheClient;
import com.growup.comptadecision.domain.enumeration.StatutDeclaration;
import com.growup.comptadecision.domain.enumeration.TypeAlert;
import com.growup.comptadecision.domain.enumeration.TypeDeclaration;
import com.growup.comptadecision.repository.AcompteProvisionnelRepository;
import com.growup.comptadecision.repository.DeclarationAnnuelleRepository;
import com.growup.comptadecision.repository.FicheClientRepository;
import com.growup.comptadecision.security.SecurityUtils;
import com.growup.comptadecision.service.dto.AcompteProvisionnelDTO;
import com.growup.comptadecision.service.dto.BusinessAlertDTO;
import com.growup.comptadecision.service.mapper.AcompteProvisionnelMapper;
import com.growup.comptadecision.web.rest.errors.BusinessErrorException;
import com.growup.comptadecision.web.rest.errors.ErrorConstants;
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

import static com.growup.comptadecision.domain.enumeration.CodeAlert.*;

/**
 * Service Implementation for managing AcompteProvisionnel.
 */
@Service
@Transactional
public class AcompteProvisionnelService {

    private final Logger log = LoggerFactory.getLogger(AcompteProvisionnelService.class);

    private final AcompteProvisionnelRepository acompteProvisionnelRepository;

    private final DeclarationAnnuelleRepository declarationAnnuelleRepository;

    private final FicheClientRepository ficheClientRepository;

    private final AcompteProvisionnelMapper acompteProvisionnelMapper;

    private static final double COEFFICIENT_BASE_AP = 0.3;

    public AcompteProvisionnelService(AcompteProvisionnelRepository acompteProvisionnelRepository, DeclarationAnnuelleRepository declarationAnnuelleRepository, FicheClientRepository ficheClientRepository, AcompteProvisionnelMapper acompteProvisionnelMapper) {
        this.acompteProvisionnelRepository = acompteProvisionnelRepository;
        this.declarationAnnuelleRepository = declarationAnnuelleRepository;
        this.ficheClientRepository = ficheClientRepository;
        this.acompteProvisionnelMapper = acompteProvisionnelMapper;
    }

    /**
     * Save a acompteProvisionnel.
     *
     * @param acompteProvisionnelDTO the entity to save
     * @return the persisted entity
     */
    public AcompteProvisionnelDTO save(AcompteProvisionnelDTO acompteProvisionnelDTO) {
        log.debug("Request to save AcompteProvisionnel : {}", acompteProvisionnelDTO);
        AcompteProvisionnel acompteProvisionnel = acompteProvisionnelMapper.toEntity(acompteProvisionnelDTO);
        acompteProvisionnel.setMontantAcompteProvisionnel(acompteProvisionnel.getMontantBase().multiply(BigDecimal.valueOf(COEFFICIENT_BASE_AP)));
        acompteProvisionnel.setMontantReportAnterieur(calculerReportAnterieur(acompteProvisionnel.getFicheClient(), acompteProvisionnel.getAnnee(), acompteProvisionnel.getNumero()));
        acompteProvisionnel.setMontantNet(acompteProvisionnel.getMontantAcompteProvisionnel()
            .subtract(acompteProvisionnel.getMontantReportAnterieur())
            .subtract(acompteProvisionnel.getMontantRetenueSource())
        );
        StatutDeclaration statut = updateStatut(acompteProvisionnel);
        acompteProvisionnel.setStatut(statut);
        acompteProvisionnel = acompteProvisionnelRepository.save(acompteProvisionnel);
        return acompteProvisionnelMapper.toDto(acompteProvisionnel);
    }

    private StatutDeclaration updateStatut(AcompteProvisionnel acompteProvisionnel) {
        if (acompteProvisionnel.getType() == TypeDeclaration.DECLARATION_RECTIFICATIVE) {
            AcompteProvisionnel acompteProvisionnelInitial = acompteProvisionnelRepository.
                findByFicheClientIdAndAnneeAndNumeroAndType(
                    acompteProvisionnel.getFicheClient().getId(),
                    acompteProvisionnel.getAnnee(),
                    acompteProvisionnel.getNumero(),
                    TypeDeclaration.DECLARATION_INITIALE).orElseThrow(() -> new RuntimeException(String.format("Il n'existe pas de déclaration annuelle initiale pour le client %s et l'annee %s",
                acompteProvisionnel.getFicheClient().getId(), acompteProvisionnel.getAnnee())));
            acompteProvisionnelInitial.setStatut(StatutDeclaration.BROUILLON);
            acompteProvisionnelRepository.save(acompteProvisionnelInitial);
            if (acompteProvisionnel.getDate() != null && acompteProvisionnel.getNumeroQuittance() != null) {
                return StatutDeclaration.VALIDE;
            } else {
                return StatutDeclaration.BROUILLON;
            }
        } else {
            if (acompteProvisionnel.getType() == TypeDeclaration.DECLARATION_INITIALE &&
                acompteProvisionnel.getDate() != null &&
                acompteProvisionnel.getNumeroQuittance() != null) {
                return StatutDeclaration.VALIDE;
            } else {
                return StatutDeclaration.BROUILLON;
            }
        }
    }

    /**
     * Get all the acompteProvisionnels.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AcompteProvisionnelDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AcompteProvisionnels");
        String creator = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new UsernameNotFoundException("Only loggued user can access"));
        return acompteProvisionnelRepository.findAllByCreatedBy(creator, pageable)
            .map(acompteProvisionnelMapper::toDto);
    }


    /**
     * Get one acompteProvisionnel by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AcompteProvisionnelDTO> findOne(Long id) {
        log.debug("Request to get AcompteProvisionnel : {}", id);
        return acompteProvisionnelRepository.findById(id)
            .map(acompteProvisionnelMapper::toDto);
    }

    /**
     * Delete the acompteProvisionnel by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AcompteProvisionnel : {}", id);
        acompteProvisionnelRepository.deleteById(id);
    }

    private List<BusinessAlertDTO> validateCreationForm(FicheClient ficheClient, Integer annee, Integer numeroAcompte, TypeDeclaration typeDeclaration) {

        List<BusinessAlertDTO> businessAlerts = new ArrayList<>();

        if (ficheClient.getDateCreation().getYear() > annee)
            throw new BusinessErrorException(ErrorConstants.ERR_ANNEE_NON_VALIDE);
        if (numeroAcompte > 3 || numeroAcompte < 1) {
            throw new BusinessErrorException(ErrorConstants.ERR_NUM_ACCOMPTE_NON_VALIDE);
        }
        if (numeroAcompte > 1) {
            List<AcompteProvisionnel> previousAcompte = acompteProvisionnelRepository.findByFicheClientIdAndAnneeAndNumero(ficheClient.getId(), annee,
                numeroAcompte - 1);
            if (previousAcompte.isEmpty()) {
                BusinessAlertDTO businessAlert = new BusinessAlertDTO(TypeAlert.warning, WARNING_ACOMPTE_PRECEDENTE_INEXISTANTE);
                businessAlert.addParam("annee", annee);
                businessAlert.addParam("numero", numeroAcompte - 1);
                businessAlerts.add(businessAlert);
            }
            Optional<AcompteProvisionnel> previousAcompteOptional = previousAcompte.stream()
                .filter(previousQuittance -> previousQuittance.getStatut().equals(StatutDeclaration.BROUILLON))
                .findFirst();
            if (previousAcompteOptional.isPresent()) {
                BusinessAlertDTO businessAlert = new BusinessAlertDTO(TypeAlert.warning, WARNING_ACOMPTE_PRECEDENTE_NON_VALIDE);
                businessAlert.addParam("annee", annee);
                businessAlert.addParam("numero", numeroAcompte - 1);
                businessAlerts.add(businessAlert);
            }
        }
        List<AcompteProvisionnel> acomptes = acompteProvisionnelRepository.findByFicheClientIdAndAnneeAndNumero(ficheClient.getId(), annee,
            numeroAcompte);

        if (acomptes.isEmpty() && typeDeclaration.equals(TypeDeclaration.DECLARATION_RECTIFICATIVE)) {
            throw new BusinessErrorException(ErrorConstants.ERR_ACOMPTE_INITIALE_INEXISTANTE);
        }
        if (acomptes.size() == 1 && typeDeclaration.equals(TypeDeclaration.DECLARATION_INITIALE)) {
            throw new BusinessErrorException(ErrorConstants.ERR_ACOMPTE_INITIALE_INEXISTANTE);
        }
        if (acomptes.size() == 1 && typeDeclaration.equals(TypeDeclaration.DECLARATION_RECTIFICATIVE) &&
            acomptes.get(0).getStatut().equals(StatutDeclaration.BROUILLON)) {

            BusinessAlertDTO businessAlert = new BusinessAlertDTO(TypeAlert.warning, WARNING_ACOMPTE_INITIALE_NON_VALIDE);
            businessAlerts.add(businessAlert);
        }

        if (acomptes.size() == 2 && typeDeclaration.equals(TypeDeclaration.DECLARATION_RECTIFICATIVE)) {
            throw new BusinessErrorException(ErrorConstants.ERR_ACOMPTE_RECTIFICATIVE_EXISTANTE);
        }

        return businessAlerts;
    }

    //    private void validateCreationForm(FicheClient ficheClient, Integer annee, Integer numero, TypeDeclaration type) {
//
//        Optional<AcompteProvisionnel> acompteOptional = acompteProvisionnelRepository.findValidByFicheClientIdAndAnneeAndNumero(ficheClient.getId(), annee, numero);
//        acompteOptional.ifPresent(acompte -> new RuntimeException(String.format("Il existe déjà un acompte numero %s annee %s pour le client %s", acompte.getNumero(), acompte.getAnnee(), acompte.getFicheClient().getDesignation())));
//    }
//
    private BigDecimal getImpotAnterieur(Long ficheClientId, Integer annerAnterieure) {
        Optional<DeclarationAnnuelle> declarationAnnuelleAnterieure = declarationAnnuelleRepository.findValidByAnneeAndFicheClientId(annerAnterieure, ficheClientId);
        return declarationAnnuelleAnterieure.isPresent() ? declarationAnnuelleAnterieure.get().getMontantImpotAnnuel() : BigDecimal.ZERO;
    }

    private AcompteProvisionnelDTO getEmptyAcompteProvisionnel(FicheClient ficheClient, Integer annee, Integer numero, TypeDeclaration type) {

        AcompteProvisionnel acompteProvisionnel = new AcompteProvisionnel();
        acompteProvisionnel.setFicheClient(ficheClient);
        acompteProvisionnel.setAnnee(annee);
        acompteProvisionnel.setNumero(numero);
        acompteProvisionnel.setType(type);
        BigDecimal impotAnterieurCalc = getImpotAnterieur(ficheClient.getId(), annee - 1);
        BigDecimal montantReportAnterieurCalc = calculerReportAnterieur(ficheClient, annee, numero);
        acompteProvisionnel.setMontantBase(impotAnterieurCalc);
        acompteProvisionnel.setMontantReportAnterieur(montantReportAnterieurCalc);
        AcompteProvisionnelDTO acompteProvisionnelDTO = acompteProvisionnelMapper.toDto(acompteProvisionnel);
        acompteProvisionnelDTO.setMontantBaseCal(impotAnterieurCalc);
        acompteProvisionnelDTO.setMontantReportAnterieurCalc(montantReportAnterieurCalc);
        return acompteProvisionnelDTO;
    }

    /**
     * Calculer le montant du report antéreir
     *
     * @param ficheClient
     * @param annee
     * @param numero
     * @return
     */
    private BigDecimal calculerReportAnterieur(FicheClient ficheClient, Integer annee, Integer numero) {
        //Si numéro AP = 1, montant report = montant report de la déclaration annuelle antérieure,
        // Sinon report équal montant net AP antérieur si négative
        BigDecimal montantReportAp = BigDecimal.ZERO;
        if (numero == 1) {
            DeclarationAnnuelle declarationAnnuelleAnterieure = declarationAnnuelleRepository.findValidByAnneeAndFicheClientId(annee - 1, ficheClient.getId())
                .orElseGet(() -> {
                    //todo mange error
                    //   new RuntimeException("Impossible de claculer le report antérieur à partir de la déclaration annuelle de l'année précédente car elle n'existe pas")
                    return new DeclarationAnnuelle();
                });
            //Remplacer ce champ par le montant report de la déclaration antérieure
            montantReportAp = declarationAnnuelleAnterieure.getMontantReportAnterieur();
        } else {
            Optional<AcompteProvisionnel> acompteProvisionnelAnterieureOptional = acompteProvisionnelRepository.findValidByFicheClientIdAndAnneeAndNumero(ficheClient.getId(), annee, numero - 1);
            //Montant Ap antérieur = montant net ap anterier si négatif
            if (acompteProvisionnelAnterieureOptional.isPresent()) {
                BigDecimal montantNetApAnterieure = acompteProvisionnelAnterieureOptional.get().getMontantNet();
                montantReportAp = montantNetApAnterieure.compareTo(BigDecimal.ZERO) > 0 ? BigDecimal.ZERO : montantNetApAnterieure.multiply(new BigDecimal(-1));
            }
        }
        return montantReportAp;
    }

    public AcompteProvisionnelDTO init(Long ficheClientId, Integer annee, Integer numeroAccompte, TypeDeclaration type) {
        log.debug("Request to init new AcompteProvisionnel number {} for year {} client id {}", numeroAccompte, annee, ficheClientId);
        FicheClient ficheClient = ficheClientRepository.findById(ficheClientId).orElseThrow(() -> new RuntimeException(String.format("FicheClient not found with id %s", ficheClientId)));
        List<BusinessAlertDTO> businessAlertDTOs = validateCreationForm(ficheClient, annee, numeroAccompte, type);
        AcompteProvisionnelDTO acompteProvisionnelDTO = getEmptyAcompteProvisionnel(ficheClient, annee, numeroAccompte, type);
        acompteProvisionnelDTO.setBusinessAlertDTOs(businessAlertDTOs);
        return acompteProvisionnelDTO;
    }

    public BigDecimal sumAcomptePrevisionnelPositifs(Long ficheClientId, Integer annee) {
        log.debug("Request to init calculation sum acompte prévisionnel for year {} client id {}", annee, ficheClientId);
        BigDecimal sumAps = acompteProvisionnelRepository.sumpAcomptePrevisionnelPositifs(ficheClientId, annee);
        return sumAps == null ? BigDecimal.ZERO : sumAps;
    }

    public Optional<AcompteProvisionnel> findByFicheClientIdAndAnneeAndNumeroAndNotArchived(Long ficheClientId, Integer annee, Integer numero) {
        log.debug("Request to find not archived acompte prévisionnel for year {} client id {} and number", annee, ficheClientId, numero);
        return acompteProvisionnelRepository.findByFicheClientIdAndAnneeAndNumeroAndNotArchived(ficheClientId, annee, numero);
    }


}
