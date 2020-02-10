package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.AcompteProvisionnel;
import com.growup.comptadecision.repository.AcompteProvisionnelRepository;
import com.growup.comptadecision.security.SecurityUtils;
import com.growup.comptadecision.service.dto.AcompteProvisionnelDTO;
import com.growup.comptadecision.service.mapper.AcompteProvisionnelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing AcompteProvisionnel.
 */
@Service
@Transactional
public class AcompteProvisionnelService {

    private final Logger log = LoggerFactory.getLogger(AcompteProvisionnelService.class);

    private final AcompteProvisionnelRepository acompteProvisionnelRepository;

    private final AcompteProvisionnelMapper acompteProvisionnelMapper;

    public AcompteProvisionnelService(AcompteProvisionnelRepository acompteProvisionnelRepository, AcompteProvisionnelMapper acompteProvisionnelMapper) {
        this.acompteProvisionnelRepository = acompteProvisionnelRepository;
        this.acompteProvisionnelMapper = acompteProvisionnelMapper;
    }

    /**
     * Save a acompteProvisionnel.
     *
     * @param acompteProvisionnelDTO the entity to save
     * @return the persisted entity
     */
    public AcompteProvisionnelDTO save(AcompteProvisionnelDTO acompteProvisionnelDTO) {
        log.debug("Request to save AcompteProvisionnel : {}", acompteProvisionnelDTO);
        AcompteProvisionnel acompteProvisionnel = acompteProvisionnelMapper.toEntity(acompteProvisionnelDTO);
        acompteProvisionnel = acompteProvisionnelRepository.save(acompteProvisionnel);
        return acompteProvisionnelMapper.toDto(acompteProvisionnel);
    }

    /**
     * Get all the acompteProvisionnels.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AcompteProvisionnelDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AcompteProvisionnels");
        String creator = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new UsernameNotFoundException("Only loggued user can access"));
        return acompteProvisionnelRepository.findAllByCreatedBy(creator, pageable)
            .map(acompteProvisionnelMapper::toDto);
    }


    /**
     * Get one acompteProvisionnel by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AcompteProvisionnelDTO> findOne(Long id) {
        log.debug("Request to get AcompteProvisionnel : {}", id);
        return acompteProvisionnelRepository.findById(id)
            .map(acompteProvisionnelMapper::toDto);
    }

    /**
     * Delete the acompteProvisionnel by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AcompteProvisionnel : {}", id);
        acompteProvisionnelRepository.deleteById(id);
    }
}
