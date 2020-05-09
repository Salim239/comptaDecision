package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.FormuleImpotAnnuel;
import com.growup.comptadecision.repository.FormuleImpotAnnuelRepository;
import com.growup.comptadecision.service.dto.FormuleImpotAnnuelDTO;
import com.growup.comptadecision.service.mapper.FormuleImpotAnnuelMapper;
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
public class FormuleImpotAnnuelService {

    private final Logger log = LoggerFactory.getLogger(FormuleImpotAnnuelService.class);

    private final FormuleImpotAnnuelRepository formuleImpotAnnuelRepository;

    private final FormuleImpotAnnuelMapper formuleImpotAnnuelMapper;

    public FormuleImpotAnnuelService(FormuleImpotAnnuelRepository formuleImpotAnnuelRepository, FormuleImpotAnnuelMapper formuleImpotAnnuelMapper) {
        this.formuleImpotAnnuelRepository = formuleImpotAnnuelRepository;
        this.formuleImpotAnnuelMapper = formuleImpotAnnuelMapper;
    }

    /**
     * Save a impotAnnuel.
     *
     * @param formuleImpotAnnuelDTO the entity to save
     * @return the persisted entity
     */
    public FormuleImpotAnnuelDTO save(FormuleImpotAnnuelDTO formuleImpotAnnuelDTO) {
        log.debug("Request to save ImpotAnnuel : {}", formuleImpotAnnuelDTO);
        FormuleImpotAnnuel formuleImpotAnnuel = formuleImpotAnnuelMapper.toEntity(formuleImpotAnnuelDTO);
formuleImpotAnnuel = formuleImpotAnnuelRepository.save(formuleImpotAnnuel);
        return formuleImpotAnnuelMapper.toDto(formuleImpotAnnuel);
    }

    /**
     * Get all the impotAnnuels.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<FormuleImpotAnnuelDTO> findAll() {
        log.debug("Request to get all ImpotAnnuels");
        return formuleImpotAnnuelRepository.findAll().stream()
            .map(formuleImpotAnnuelMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one impotAnnuel by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<FormuleImpotAnnuelDTO> findOne(Long id) {
        log.debug("Request to get ImpotAnnuel : {}", id);
        return formuleImpotAnnuelRepository.findById(id)
            .map(formuleImpotAnnuelMapper::toDto);
    }

    /**
     * Delete the impotAnnuel by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ImpotAnnuel : {}", id);
        formuleImpotAnnuelRepository.deleteById(id);
    }
}
