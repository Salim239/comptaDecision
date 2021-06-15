package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.LigneCaisse;
import com.growup.comptadecision.repository.LigneCaisseRepository;
import com.growup.comptadecision.service.dto.LigneCaisseDTO;
import com.growup.comptadecision.service.mapper.LigneCaisseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing LigneCaisse.
 */
@Service
@Transactional
public class LigneCaisseService {

    private final Logger log = LoggerFactory.getLogger(LigneCaisseService.class);

    private final LigneCaisseRepository ligneCaisseRepository;

    private final LigneCaisseMapper ligneCaisseMapper;

    public LigneCaisseService(LigneCaisseRepository ligneCaisseRepository, LigneCaisseMapper ligneCaisseMapper) {
        this.ligneCaisseRepository = ligneCaisseRepository;
        this.ligneCaisseMapper = ligneCaisseMapper;
    }

    /**
     * Save a ligneCaisse.
     *
     * @param ligneCaisseDTO the entity to save
     * @return the persisted entity
     */
    public LigneCaisseDTO save(LigneCaisseDTO ligneCaisseDTO) {
        log.debug("Request to save LigneCaisse : {}", ligneCaisseDTO);
        LigneCaisse ligneCaisse = ligneCaisseMapper.toEntity(ligneCaisseDTO);
        ligneCaisse = ligneCaisseRepository.save(ligneCaisse);
        return ligneCaisseMapper.toDto(ligneCaisse);
    }

    /**
     * Get all the ligneCaisses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<LigneCaisseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LigneCaisses");
        return ligneCaisseRepository.findAll(pageable)
            .map(ligneCaisseMapper::toDto);
    }


    /**
     * Get one ligneCaisse by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<LigneCaisseDTO> findOne(Long id) {
        log.debug("Request to get LigneCaisse : {}", id);
        return ligneCaisseRepository.findById(id)
            .map(ligneCaisseMapper::toDto);
    }

    /**
     * Delete the ligneCaisse by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete LigneCaisse : {}", id);
        ligneCaisseRepository.deleteById(id);
    }
}
