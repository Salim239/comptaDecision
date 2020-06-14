package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.CategorieCnssGerant;
import com.growup.comptadecision.repository.CategorieCnssGerantRepository;
import com.growup.comptadecision.service.dto.CategorieCnssGerantDTO;
import com.growup.comptadecision.service.mapper.CategorieCnssGerantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing CategorieCnssGerant.
 */
@Service
@Transactional
public class CategorieCnssGerantService {

    private final Logger log = LoggerFactory.getLogger(CategorieCnssGerantService.class);

    private final CategorieCnssGerantRepository categorieCnssGerantRepository;

    private final CategorieCnssGerantMapper categorieCnssGerantMapper;

    public CategorieCnssGerantService(CategorieCnssGerantRepository categorieCnssGerantRepository, CategorieCnssGerantMapper categorieCnssGerantMapper) {
        this.categorieCnssGerantRepository = categorieCnssGerantRepository;
        this.categorieCnssGerantMapper = categorieCnssGerantMapper;
    }

    /**
     * Save a categorieCnssGerant.
     *
     * @param categorieCnssGerantDTO the entity to save
     * @return the persisted entity
     */
    public CategorieCnssGerantDTO save(CategorieCnssGerantDTO categorieCnssGerantDTO) {
        log.debug("Request to save CategorieCnssGerant : {}", categorieCnssGerantDTO);
        CategorieCnssGerant categorieCnssGerant = categorieCnssGerantMapper.toEntity(categorieCnssGerantDTO);
        categorieCnssGerant = categorieCnssGerantRepository.save(categorieCnssGerant);
        return categorieCnssGerantMapper.toDto(categorieCnssGerant);
    }

    /**
     * Get all the categorieCnssGerants.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<CategorieCnssGerantDTO> findAll() {
        log.debug("Request to get all CategorieCnssGerants");
        return categorieCnssGerantRepository.findAll().stream()
            .map(categorieCnssGerantMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one categorieCnssGerant by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<CategorieCnssGerantDTO> findOne(Long id) {
        log.debug("Request to get CategorieCnssGerant : {}", id);
        return categorieCnssGerantRepository.findById(id)
            .map(categorieCnssGerantMapper::toDto);
    }

    /**
     * Delete the categorieCnssGerant by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CategorieCnssGerant : {}", id);
        categorieCnssGerantRepository.deleteById(id);
    }
}
