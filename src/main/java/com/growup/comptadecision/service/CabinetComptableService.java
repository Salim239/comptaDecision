package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.CabinetComptable;
import com.growup.comptadecision.repository.CabinetComptableRepository;
import com.growup.comptadecision.service.dto.CabinetComptableDTO;
import com.growup.comptadecision.service.mapper.CabinetComptableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CabinetComptable.
 */
@Service
@Transactional
public class CabinetComptableService {

    private final Logger log = LoggerFactory.getLogger(CabinetComptableService.class);

    private final CabinetComptableRepository cabinetComptableRepository;

    private final CabinetComptableMapper cabinetComptableMapper;

    public CabinetComptableService(CabinetComptableRepository cabinetComptableRepository, CabinetComptableMapper cabinetComptableMapper) {
        this.cabinetComptableRepository = cabinetComptableRepository;
        this.cabinetComptableMapper = cabinetComptableMapper;
    }

    /**
     * Save a cabinetComptable.
     *
     * @param cabinetComptableDTO the entity to save
     * @return the persisted entity
     */
    public CabinetComptableDTO save(CabinetComptableDTO cabinetComptableDTO) {
        log.debug("Request to save CabinetComptable : {}", cabinetComptableDTO);
        CabinetComptable cabinetComptable = cabinetComptableMapper.toEntity(cabinetComptableDTO);
        cabinetComptable = cabinetComptableRepository.save(cabinetComptable);
        return cabinetComptableMapper.toDto(cabinetComptable);
    }

    /**
     * Get all the cabinetComptables.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CabinetComptableDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CabinetComptables");
        return cabinetComptableRepository.findAll(pageable)
            .map(cabinetComptableMapper::toDto);
    }


    /**
     * Get one cabinetComptable by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<CabinetComptableDTO> findOne(Long id) {
        log.debug("Request to get CabinetComptable : {}", id);
        return cabinetComptableRepository.findById(id)
            .map(cabinetComptableMapper::toDto);
    }

    /**
     * Delete the cabinetComptable by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CabinetComptable : {}", id);
        cabinetComptableRepository.deleteById(id);
    }
}
