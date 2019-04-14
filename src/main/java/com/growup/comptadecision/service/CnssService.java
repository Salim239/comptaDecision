package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.Cnss;
import com.growup.comptadecision.repository.CnssRepository;
import com.growup.comptadecision.security.SecurityUtils;
import com.growup.comptadecision.service.dto.CnssDTO;
import com.growup.comptadecision.service.mapper.CnssMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Cnss.
 */
@Service
@Transactional
public class CnssService {

    private final Logger log = LoggerFactory.getLogger(CnssService.class);

    private final CnssRepository cnssRepository;

    private final CnssMapper cnssMapper;

    public CnssService(CnssRepository cnssRepository, CnssMapper cnssMapper) {
        this.cnssRepository = cnssRepository;
        this.cnssMapper = cnssMapper;
    }

    /**
     * Save a cnss.
     *
     * @param cnssDTO the entity to save
     * @return the persisted entity
     */
    public CnssDTO save(CnssDTO cnssDTO) {
        log.debug("Request to save Cnss : {}", cnssDTO);
        Cnss cnss = cnssMapper.toEntity(cnssDTO);
        cnss = cnssRepository.save(cnss);
        return cnssMapper.toDto(cnss);
    }

    /**
     * Get all the cnsses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CnssDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cnsses");
        String creator = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new UsernameNotFoundException("Only loggued user can access"));
        return cnssRepository.findAllByCreatedBy(creator, pageable)
            .map(cnssMapper::toDto);
    }


    /**
     * Get one cnss by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<CnssDTO> findOne(Long id) {
        log.debug("Request to get Cnss : {}", id);
        return cnssRepository.findById(id)
            .map(cnssMapper::toDto);
    }

    /**
     * Delete the cnss by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Cnss : {}", id);
        cnssRepository.deleteById(id);
    }
}
