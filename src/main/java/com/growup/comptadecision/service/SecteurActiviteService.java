package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.SecteurActivite;
import com.growup.comptadecision.repository.SecteurActiviteRepository;
import com.growup.comptadecision.service.dto.SecteurActiviteDTO;
import com.growup.comptadecision.service.mapper.SecteurActiviteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing SecteurActivite.
 */
@Service
@Transactional
public class SecteurActiviteService {

    private final Logger log = LoggerFactory.getLogger(SecteurActiviteService.class);

    private final SecteurActiviteRepository secteurActiviteRepository;

    private final SecteurActiviteMapper secteurActiviteMapper;

    public SecteurActiviteService(SecteurActiviteRepository secteurActiviteRepository, SecteurActiviteMapper secteurActiviteMapper) {
        this.secteurActiviteRepository = secteurActiviteRepository;
        this.secteurActiviteMapper = secteurActiviteMapper;
    }

    /**
     * Save a secteurActivite.
     *
     * @param secteurActiviteDTO the entity to save
     * @return the persisted entity
     */
    public SecteurActiviteDTO save(SecteurActiviteDTO secteurActiviteDTO) {
        log.debug("Request to save SecteurActivite : {}", secteurActiviteDTO);
        SecteurActivite secteurActivite = secteurActiviteMapper.toEntity(secteurActiviteDTO);
        secteurActivite = secteurActiviteRepository.save(secteurActivite);
        return secteurActiviteMapper.toDto(secteurActivite);
    }

    /**
     * Get all the secteurActivites.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<SecteurActiviteDTO> findAll() {
        log.debug("Request to get all SecteurActivites");
        return secteurActiviteRepository.findAll().stream()
            .map(secteurActiviteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one secteurActivite by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<SecteurActiviteDTO> findOne(Long id) {
        log.debug("Request to get SecteurActivite : {}", id);
        return secteurActiviteRepository.findById(id)
            .map(secteurActiviteMapper::toDto);
    }

    /**
     * Delete the secteurActivite by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete SecteurActivite : {}", id);
        secteurActiviteRepository.deleteById(id);
    }
}
