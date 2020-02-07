package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.*;
import com.growup.comptadecision.domain.enumeration.StatutDeclaration;
import com.growup.comptadecision.domain.enumeration.TypeDeclaration;
import com.growup.comptadecision.repository.DeclarationAnnuelleRepository;
import com.growup.comptadecision.repository.FicheClientRepository;
import com.growup.comptadecision.repository.ImpotAnnuelRepository;
import com.growup.comptadecision.repository.QuittanceMensuelleImpotSousDetailRepository;
import com.growup.comptadecision.security.SecurityUtils;
import com.growup.comptadecision.service.dto.DeclarationAnnuelleDTO;
import com.growup.comptadecision.service.mapper.DeclarationAnnuelleMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private final QuittanceMensuelleImpotSousDetailRepository quittanceMensuelleSousDetailRepository;

    private final DeclarationAnnuelleMapper declarationAnnuelleMapper;

    public DeclarationAnnuelleService(DeclarationAnnuelleRepository declarationAnnuelleRepository, DeclarationAnnuelleMapper declarationAnnuelleMapper,
                                      FicheClientRepository ficheClientRepository, ImpotAnnuelRepository impotAnnuelRepository, QuittanceMensuelleImpotSousDetailRepository quittanceMensuelleSousDetailRepository) {
        this.declarationAnnuelleRepository = declarationAnnuelleRepository;
        this.declarationAnnuelleMapper = declarationAnnuelleMapper;
        this.ficheClientRepository = ficheClientRepository;
        this.impotAnnuelRepository = impotAnnuelRepository;
        this.quittanceMensuelleSousDetailRepository = quittanceMensuelleSousDetailRepository;
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
        declarationAnnuelle = declarationAnnuelleRepository.save(declarationAnnuelle);
        return declarationAnnuelleMapper.toDto(declarationAnnuelle);
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
        return declarationAnnuelleRepository.findById(id)
            .map(declarationAnnuelleMapper::toDto);
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

        Optional<DeclarationAnnuelle> declarationRectificative = declarationAnnuelleRepository.findByAnneeAndFicheClientIdAndTypeDeclaration(
                declarationAnnuelle.getAnnee(),
                declarationAnnuelle.getFicheClient().getId(),
                TypeDeclaration.DECLARATION_RECTIFICATIVE);

        if (declarationRectificative.isPresent() && StringUtils.isNotBlank(declarationRectificative.get().getNumeroQuittance())) {

            return declarationAnnuelleMapper.toDto(declarationRectificative.get());
        } else {

            DeclarationAnnuelleDTO quittanceRectificativeDTO = declarationAnnuelleMapper.toDto(declarationAnnuelle);

            quittanceRectificativeDTO.setTypeDeclaration(TypeDeclaration.DECLARATION_RECTIFICATIVE);
            quittanceRectificativeDTO.setId(null);
            quittanceRectificativeDTO.setDatePaiement(null);
            quittanceRectificativeDTO.setNumeroQuittance(null);
            quittanceRectificativeDTO.getDeclarationAnnuelleDetails().forEach(declarationAnnuelleDetail -> {
                declarationAnnuelleDetail.setId(null);
                declarationAnnuelleDetail.setDeclarationAnnuelId(null);
                });
            return quittanceRectificativeDTO;
        }
    }

    private void initDeclarationAnnuelDetails(DeclarationAnnuelle declarationAnnuelle) {

        List<ImpotAnnuel> impotMensuels = impotAnnuelRepository.findAll();
        List<DecalrationAnnuelleDetail> declarationAnnuelleDetails = impotMensuels.stream()
                .map(impotAnnuel -> {
                    DecalrationAnnuelleDetail declarationAnnuelleDetail = new DecalrationAnnuelleDetail();
                    declarationAnnuelleDetail.setCode(impotAnnuel.getCode());
                    declarationAnnuelleDetail.setDescription(impotAnnuel.getDescription());
                    declarationAnnuelleDetail.setCalcule(impotAnnuel.getCalcule());
                    declarationAnnuelleDetail.setTriOrdre(impotAnnuel.getTriOrdre());
                    List<String> impotMensuelDetailToSum = impotAnnuel.getImpotAnnuelDetails().stream()
                            .map(ImpotAnnuelDetail::getImpotMensuelDetail)
                            .map(ImpotMensuelDetail::getCode)
                            .collect(Collectors.toList());
                    BigDecimal montantcalcule = null;
                    if (impotAnnuel.getCalcule() && !impotMensuelDetailToSum.isEmpty()) {
                        montantcalcule = quittanceMensuelleSousDetailRepository.sumMontantBaseByFicheClientIdAndByAnneeAndByCodes(
                                declarationAnnuelle.getFicheClient().getId(),
                                declarationAnnuelle.getAnnee(),
                                impotMensuelDetailToSum);
                    }
                    declarationAnnuelleDetail.setMontantCalcule(montantcalcule);
                    return declarationAnnuelleDetail;
                }).collect(Collectors.toList());
        declarationAnnuelle.setDeclarationAnnuelleDetails(declarationAnnuelleDetails);
    }

    private DeclarationAnnuelleDTO getEmptyDeclarationAnnuelle(FicheClient ficheClient, Integer annee) {

        Optional<DeclarationAnnuelle> declarationAnnuelleOptional = declarationAnnuelleRepository.findByAnneeAndFicheClientIdAndTypeDeclaration(annee,
                ficheClient.getId(), TypeDeclaration.DECLARATION_INITIALE);

        //Si existe déjà une quittance mensuelle initiale avec un numéro (donc validée) alors créer une quittance type corrective
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
            initDeclarationAnnuelDetails(declarationAnnuelleInitiale);
            return declarationAnnuelleMapper.toDto(declarationAnnuelleInitiale);
        }
    }

    private void validateCreationForm(FicheClient ficheClient, Integer annee, TypeDeclaration typeDeclaration) {

        List<DeclarationAnnuelle> declarationAnnuelles = declarationAnnuelleRepository.findByAnneeAndFicheClientId(annee, ficheClient.getId());
        if (declarationAnnuelles.isEmpty() && typeDeclaration.equals(TypeDeclaration.DECLARATION_RECTIFICATIVE)) {
            throw new RuntimeException("declaration annuelle initiale not exists. Create quiitance initiale before can create quittantance rectifcative!!");
        }
        if (declarationAnnuelles.size() == 1 && typeDeclaration.equals(TypeDeclaration.DECLARATION_INITIALE)) {
            throw new RuntimeException("declaration annuelle initiale already exists for this mouth. Edit it or create one for another mounth!");
        }
        if (declarationAnnuelles.size() == 1 && typeDeclaration.equals(TypeDeclaration.DECLARATION_RECTIFICATIVE) &&
                declarationAnnuelles.get(0).getStatut().equals(StatutDeclaration.BROUILLON)) {
            throw new RuntimeException("declaration annuelle initiale not validated yet. Validate it to can create quittance rectificative!");
        }
        if (declarationAnnuelles.size() == 2 && typeDeclaration.equals(TypeDeclaration.DECLARATION_RECTIFICATIVE)) {
            throw new RuntimeException("declaration annuelle rectificative already exists for this mouth. Edit it or create one for another mounth!");
        }
    }

    public DeclarationAnnuelleDTO init(Long ficheClientId, Integer annee, TypeDeclaration typeDeclaration) {
        log.debug("Request to init new DeclarationAnnuelle for year {} client id {} and declaration type {}", annee, ficheClientId,  typeDeclaration);
        FicheClient ficheClient = ficheClientRepository.findById(ficheClientId).orElseThrow(() -> new RuntimeException(String.format("FicheClient not found with id %s", ficheClientId)));
        validateCreationForm(ficheClient, annee, typeDeclaration);
        return getEmptyDeclarationAnnuelle(ficheClient, annee);
    }
}
