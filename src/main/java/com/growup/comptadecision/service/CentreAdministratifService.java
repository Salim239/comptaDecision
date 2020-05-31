package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.CentreAdministratif;
import com.growup.comptadecision.domain.CentreAdministratifMapper;
import com.growup.comptadecision.domain.enumeration.TypeCentreAdministratif;
import com.growup.comptadecision.repository.CentreAdministratifRepository;
import com.growup.comptadecision.service.dto.CentreAdministratifDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing CentreAdministratif.
 */
@Service
@Transactional
public class CentreAdministratifService {

    private final Logger log = LoggerFactory.getLogger(CentreAdministratifService.class);

    private final CentreAdministratifRepository centreAdministratifRepository;

    private final CentreAdministratifMapper centreAdministratifMapper;

    public CentreAdministratifService(CentreAdministratifRepository centreAdministratifRepository, CentreAdministratifMapper centreAdministratifMapper) {
        this.centreAdministratifRepository = centreAdministratifRepository;
        this.centreAdministratifMapper = centreAdministratifMapper;
    }

    /**
     * Save a centreAdministratif.
     *
     * @param centreAdministratifDTO the entity to save
     * @return the persisted entity
     */
    public CentreAdministratifDTO save(CentreAdministratifDTO centreAdministratifDTO) {
        log.debug("Request to save CentreAdministratif : {}", centreAdministratifDTO);
        CentreAdministratif centreAdministratif = centreAdministratifMapper.toEntity(centreAdministratifDTO);
        centreAdministratif = centreAdministratifRepository.save(centreAdministratif);
        return centreAdministratifMapper.toDto(centreAdministratif);
    }

    /**
     * Get all the centreAdministratifs.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<CentreAdministratifDTO> findAll() {
        log.debug("Request to get all CentreAdministratifs");
        return centreAdministratifRepository.findAll().stream()
            .map(centreAdministratifMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get centreAdministratifs by types
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<CentreAdministratifDTO> findByType(TypeCentreAdministratif typeCentreAdministratif) {
        log.debug("Request to get CentreAdministratifs by type {}", typeCentreAdministratif);
        return centreAdministratifRepository.findByType(typeCentreAdministratif).stream()
            .map(centreAdministratifMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one centreAdministratif by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<CentreAdministratifDTO> findOne(Long id) {
        log.debug("Request to get CentreAdministratif : {}", id);
        return centreAdministratifRepository.findById(id)
            .map(centreAdministratifMapper::toDto);
    }

    /**
     * Delete the centreAdministratif by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CentreAdministratif : {}", id);
        centreAdministratifRepository.deleteById(id);
    }
}
