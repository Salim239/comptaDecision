package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.ImpotAnnuel;
import com.growup.comptadecision.repository.ImpotAnnuelRepository;
import com.growup.comptadecision.service.dto.ImpotAnnuelDTO;
import com.growup.comptadecision.service.mapper.ImpotAnnuelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ImpotAnnuel.
 */
@Service
@Transactional
public class ImpotAnnuelService {

    private final Logger log = LoggerFactory.getLogger(ImpotAnnuelService.class);

    private final ImpotAnnuelRepository impotAnnuelRepository;

    private final ImpotAnnuelMapper impotAnnuelMapper;

    public ImpotAnnuelService(ImpotAnnuelRepository impotAnnuelRepository, ImpotAnnuelMapper impotAnnuelMapper) {
        this.impotAnnuelRepository = impotAnnuelRepository;
        this.impotAnnuelMapper = impotAnnuelMapper;
    }

    /**
     * Save a impotAnnuel.
     *
     * @param impotAnnuelDTO the entity to save
     * @return the persisted entity
     */
    public ImpotAnnuelDTO save(ImpotAnnuelDTO impotAnnuelDTO) {
        log.debug("Request to save ImpotAnnuel : {}", impotAnnuelDTO);
        ImpotAnnuel impotAnnuel = impotAnnuelMapper.toEntity(impotAnnuelDTO);
        impotAnnuel = impotAnnuelRepository.save(impotAnnuel);
        return impotAnnuelMapper.toDto(impotAnnuel);
    }

    /**
     * Get all the impotAnnuels.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ImpotAnnuelDTO> findAll() {
        log.debug("Request to get all ImpotAnnuels");
        return impotAnnuelRepository.findAll().stream()
            .map(impotAnnuelMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one impotAnnuel by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ImpotAnnuelDTO> findOne(Long id) {
        log.debug("Request to get ImpotAnnuel : {}", id);
        return impotAnnuelRepository.findById(id)
            .map(impotAnnuelMapper::toDto);
    }

    /**
     * Delete the impotAnnuel by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ImpotAnnuel : {}", id);
        impotAnnuelRepository.deleteById(id);
    }
}
