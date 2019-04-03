package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.DeclarationEmployeurAnnuelle;
import com.growup.comptadecision.repository.DeclarationEmployeurAnnuelleRepository;
import com.growup.comptadecision.service.dto.DeclarationEmployeurAnnuelleDTO;
import com.growup.comptadecision.service.mapper.DeclarationEmployeurAnnuelleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing DeclarationEmployeurAnnuelle.
 */
@Service
@Transactional
public class DeclarationEmployeurAnnuelleService {

    private final Logger log = LoggerFactory.getLogger(DeclarationEmployeurAnnuelleService.class);

    private final DeclarationEmployeurAnnuelleRepository declarationEmployeurAnnuelleRepository;

    private final DeclarationEmployeurAnnuelleMapper declarationEmployeurAnnuelleMapper;

    public DeclarationEmployeurAnnuelleService(DeclarationEmployeurAnnuelleRepository declarationEmployeurAnnuelleRepository, DeclarationEmployeurAnnuelleMapper declarationEmployeurAnnuelleMapper) {
        this.declarationEmployeurAnnuelleRepository = declarationEmployeurAnnuelleRepository;
        this.declarationEmployeurAnnuelleMapper = declarationEmployeurAnnuelleMapper;
    }

    /**
     * Save a declarationEmployeurAnnuelle.
     *
     * @param declarationEmployeurAnnuelleDTO the entity to save
     * @return the persisted entity
     */
    public DeclarationEmployeurAnnuelleDTO save(DeclarationEmployeurAnnuelleDTO declarationEmployeurAnnuelleDTO) {
        log.debug("Request to save DeclarationEmployeurAnnuelle : {}", declarationEmployeurAnnuelleDTO);
        DeclarationEmployeurAnnuelle declarationEmployeurAnnuelle = declarationEmployeurAnnuelleMapper.toEntity(declarationEmployeurAnnuelleDTO);
        declarationEmployeurAnnuelle = declarationEmployeurAnnuelleRepository.save(declarationEmployeurAnnuelle);
        return declarationEmployeurAnnuelleMapper.toDto(declarationEmployeurAnnuelle);
    }

    /**
     * Get all the declarationEmployeurAnnuelles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<DeclarationEmployeurAnnuelleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DeclarationEmployeurAnnuelles");
        return declarationEmployeurAnnuelleRepository.findAll(pageable)
            .map(declarationEmployeurAnnuelleMapper::toDto);
    }


    /**
     * Get one declarationEmployeurAnnuelle by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<DeclarationEmployeurAnnuelleDTO> findOne(Long id) {
        log.debug("Request to get DeclarationEmployeurAnnuelle : {}", id);
        return declarationEmployeurAnnuelleRepository.findById(id)
            .map(declarationEmployeurAnnuelleMapper::toDto);
    }

    /**
     * Delete the declarationEmployeurAnnuelle by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete DeclarationEmployeurAnnuelle : {}", id);
        declarationEmployeurAnnuelleRepository.deleteById(id);
    }
}
