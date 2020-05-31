package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.Activite;
import com.growup.comptadecision.repository.ActiviteRepository;
import com.growup.comptadecision.service.dto.ActiviteDTO;
import com.growup.comptadecision.service.mapper.ActiviteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Activite.
 */
@Service
@Transactional
public class ActiviteService {

    private final Logger log = LoggerFactory.getLogger(ActiviteService.class);

    private final ActiviteRepository activiteRepository;

    private final ActiviteMapper activiteMapper;

    public ActiviteService(ActiviteRepository activiteRepository, ActiviteMapper activiteMapper) {
        this.activiteRepository = activiteRepository;
        this.activiteMapper = activiteMapper;
    }

    /**
     * Save a activite.
     *
     * @param activiteDTO the entity to save
     * @return the persisted entity
     */
    public ActiviteDTO save(ActiviteDTO activiteDTO) {
        log.debug("Request to save Activite : {}", activiteDTO);
        Activite activite = activiteMapper.toEntity(activiteDTO);
        activite = activiteRepository.save(activite);
        return activiteMapper.toDto(activite);
    }

    /**
     * Get all the activites.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ActiviteDTO> findAll() {
        log.debug("Request to get all Activites");
        return activiteRepository.findAll().stream()
            .map(activiteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get  activites by secteur activite id.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ActiviteDTO> findBySecteurActiviteId(Long secteurActiviteId) {
        log.debug("Request to get Activites by secteur activite id {}", secteurActiviteId);
        return activiteRepository.findAll().stream()
            .map(activiteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one activite by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ActiviteDTO> findOne(Long id) {
        log.debug("Request to get Activite : {}", id);
        return activiteRepository.findById(id)
            .map(activiteMapper::toDto);
    }

    /**
     * Delete the activite by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Activite : {}", id);
        activiteRepository.deleteById(id);
    }
}
