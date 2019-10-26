package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.ImpotMensuel;
import com.growup.comptadecision.repository.ImpotMensuelRepository;
import com.growup.comptadecision.service.dto.ImpotMensuelDTO;
import com.growup.comptadecision.service.mapper.ImpotMensuelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ImpotMensuel.
 */
@Service
@Transactional
public class ImpotMensuelService {

    private final Logger log = LoggerFactory.getLogger(ImpotMensuelService.class);

    private final ImpotMensuelRepository impotMensuelRepository;

    private final ImpotMensuelMapper impotMensuelMapper;

    public ImpotMensuelService(ImpotMensuelRepository impotMensuelRepository, ImpotMensuelMapper impotMensuelMapper) {
        this.impotMensuelRepository = impotMensuelRepository;
        this.impotMensuelMapper = impotMensuelMapper;
    }

    /**
     * Save a impotMensuel.
     *
     * @param impotMensuelDTO the entity to save
     * @return the persisted entity
     */
    public ImpotMensuelDTO save(ImpotMensuelDTO impotMensuelDTO) {
        log.debug("Request to save ImpotMensuel : {}", impotMensuelDTO);
        ImpotMensuel impotMensuel = impotMensuelMapper.toEntity(impotMensuelDTO);
        impotMensuel = impotMensuelRepository.save(impotMensuel);
        return impotMensuelMapper.toDto(impotMensuel);
    }

    /**
     * Get all the impotMensuels.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ImpotMensuelDTO> findAll() {
        log.debug("Request to get all ImpotMensuels");
        return impotMensuelRepository.findAll().stream()
            .map(impotMensuelMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional(readOnly = true)
    public List<ImpotMensuelDTO> findByParentIds(List<Long> parentIds) {
        log.debug("Request to get all ImpotMensuels by parent!ids");
        return impotMensuelRepository.findByParentIds(parentIds).stream()
            .map(impotMensuelMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional(readOnly = true)
    public List<ImpotMensuelDTO> findWithoutChildren() {
        log.debug("Request to get all ImpotMensuels");
        return impotMensuelRepository.findWithoutChildren().stream()
                .map(impotMensuelMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the impotMensuels.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ImpotMensuelDTO> findParentsWithoutChildren() {
        log.debug("Request to get all ImpotMensuels parent without children");
        return impotMensuelRepository.findParents().stream()
                .map(impotMensuelMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one impotMensuel by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ImpotMensuelDTO> findOne(Long id) {
        log.debug("Request to get ImpotMensuel : {}", id);
        return impotMensuelRepository.findById(id)
            .map(impotMensuelMapper::toDto);
    }

    /**
     * Delete the impotMensuel by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ImpotMensuel : {}", id);
        impotMensuelRepository.deleteById(id);
    }
}
