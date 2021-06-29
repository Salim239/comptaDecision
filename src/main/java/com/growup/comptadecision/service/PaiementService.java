package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.Paiement;
import com.growup.comptadecision.repository.PaiementRepository;
import com.growup.comptadecision.service.dto.PaiementDTO;
import com.growup.comptadecision.service.mapper.PaiementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Paiement.
 */
@Service
@Transactional
public class PaiementService {

    private final Logger log = LoggerFactory.getLogger(PaiementService.class);

    private final PaiementRepository paiementRepository;

    private final PaiementMapper paiementMapper;

    public PaiementService(PaiementRepository paiementRepository, PaiementMapper paiementMapper) {
        this.paiementRepository = paiementRepository;
        this.paiementMapper = paiementMapper;
    }

    /**
     * Save a paiement.
     *
     * @param paiementDTO the entity to save
     * @return the persisted entity
     */
    public PaiementDTO save(PaiementDTO paiementDTO) {
        log.debug("Request to save Paiement : {}", paiementDTO);
        Paiement paiement = paiementMapper.toEntity(paiementDTO);
        paiement = paiementRepository.save(paiement);
        return paiementMapper.toDto(paiement);
    }

    /**
     * Get all the paiements.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<PaiementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Paiements");
        return paiementRepository.findAll(pageable)
            .map(paiementMapper::toDto);
    }


    /**
     * Get one paiement by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<PaiementDTO> findOne(Long id) {
        log.debug("Request to get Paiement : {}", id);
        return paiementRepository.findById(id)
            .map(paiementMapper::toDto);
    }

    /**
     * Delete the paiement by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Paiement : {}", id);
        paiementRepository.deleteById(id);
    }
}
