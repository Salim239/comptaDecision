package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.AcompteProvisionnel;
import com.growup.comptadecision.domain.FicheClient;
import com.growup.comptadecision.repository.AcompteProvisionnelRepository;
import com.growup.comptadecision.repository.FicheClientRepository;
import com.growup.comptadecision.security.SecurityUtils;
import com.growup.comptadecision.service.dto.AcompteProvisionnelDTO;
import com.growup.comptadecision.service.mapper.AcompteProvisionnelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing AcompteProvisionnel.
 */
@Service
@Transactional
public class AcompteProvisionnelService {

    private final Logger log = LoggerFactory.getLogger(AcompteProvisionnelService.class);

    private final AcompteProvisionnelRepository acompteProvisionnelRepository;

    private final FicheClientRepository ficheClientRepository;

    private final AcompteProvisionnelMapper acompteProvisionnelMapper;

    public AcompteProvisionnelService(AcompteProvisionnelRepository acompteProvisionnelRepository, FicheClientRepository ficheClientRepository, AcompteProvisionnelMapper acompteProvisionnelMapper) {
        this.acompteProvisionnelRepository = acompteProvisionnelRepository;
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
        acompteProvisionnel = acompteProvisionnelRepository.save(acompteProvisionnel);
        return acompteProvisionnelMapper.toDto(acompteProvisionnel);
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

    private void validateCreationForm(FicheClient ficheClient, Integer annee, Integer numero) {

        Optional<AcompteProvisionnel> acompteOptional = acompteProvisionnelRepository.findByFicheClientIdAndAnneeAndNumero(ficheClient.getId(), annee, numero);
        acompteOptional.ifPresent(acompte -> new RuntimeException(String.format("Il existe déjà un acompte numero %s annee %s pour le client %s", acompte.getNumero(), acompte.getAnnee(), acompte.getFicheClient().getDesignation())));
    }

    private AcompteProvisionnelDTO getEmptyAcompteProvisionnel(FicheClient ficheClient, Integer annee, Integer numero) {

        AcompteProvisionnel acompteProvisionnel = new AcompteProvisionnel();
        acompteProvisionnel.setFicheClient(ficheClient);
        acompteProvisionnel.setAnnee(annee);
        acompteProvisionnel.setNumero(numero);
        return acompteProvisionnelMapper.toDto(acompteProvisionnel);
    }

    public AcompteProvisionnelDTO init(Long ficheClientId, Integer annee, Integer numeroAccompte) {
        log.debug("Request to init new AcompteProvisionnel number {} for year {} client id {}", numeroAccompte, annee, ficheClientId);
        FicheClient ficheClient = ficheClientRepository.findById(ficheClientId).orElseThrow(() -> new RuntimeException(String.format("FicheClient not found with id %s", ficheClientId)));
        validateCreationForm(ficheClient, annee, numeroAccompte);
        return getEmptyAcompteProvisionnel(ficheClient, annee, numeroAccompte);
    }
}
