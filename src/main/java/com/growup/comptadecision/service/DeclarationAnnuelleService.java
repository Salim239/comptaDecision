package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.DeclarationAnnuelle;
import com.growup.comptadecision.repository.DeclarationAnnuelleRepository;
import com.growup.comptadecision.service.dto.DeclarationAnnuelleDTO;
import com.growup.comptadecision.service.mapper.DeclarationAnnuelleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing DeclarationAnnuelle.
 */
@Service
@Transactional
public class DeclarationAnnuelleService {

    private final Logger log = LoggerFactory.getLogger(DeclarationAnnuelleService.class);

    private final DeclarationAnnuelleRepository declarationAnnuelleRepository;

    private final DeclarationAnnuelleMapper declarationAnnuelleMapper;

    public DeclarationAnnuelleService(DeclarationAnnuelleRepository declarationAnnuelleRepository, DeclarationAnnuelleMapper declarationAnnuelleMapper) {
        this.declarationAnnuelleRepository = declarationAnnuelleRepository;
        this.declarationAnnuelleMapper = declarationAnnuelleMapper;
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
        return declarationAnnuelleRepository.findAll(pageable)
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
}
