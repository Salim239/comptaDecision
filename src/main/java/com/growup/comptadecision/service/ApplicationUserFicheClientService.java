package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.ApplicationUserFicheClient;
import com.growup.comptadecision.repository.ApplicationUserFicheClientRepository;
import com.growup.comptadecision.service.dto.ApplicationUserFicheClientDTO;
import com.growup.comptadecision.service.mapper.ApplicationUserFicheClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ApplicationUserFicheClient.
 */
@Service
@Transactional
public class ApplicationUserFicheClientService {

    private final Logger log = LoggerFactory.getLogger(ApplicationUserFicheClientService.class);

    private final ApplicationUserFicheClientRepository applicationUserFicheClientRepository;

    private final ApplicationUserFicheClientMapper applicationUserFicheClientMapper;

    public ApplicationUserFicheClientService(ApplicationUserFicheClientRepository applicationUserFicheClientRepository, ApplicationUserFicheClientMapper applicationUserFicheClientMapper) {
        this.applicationUserFicheClientRepository = applicationUserFicheClientRepository;
        this.applicationUserFicheClientMapper = applicationUserFicheClientMapper;
    }

    /**
     * Save a applicationUserFicheClient.
     *
     * @param applicationUserFicheClientDTO the entity to save
     * @return the persisted entity
     */
    public ApplicationUserFicheClientDTO save(ApplicationUserFicheClientDTO applicationUserFicheClientDTO) {
        log.debug("Request to save ApplicationUserFicheClient : {}", applicationUserFicheClientDTO);
        ApplicationUserFicheClient applicationUserFicheClient = applicationUserFicheClientMapper.toEntity(applicationUserFicheClientDTO);
        applicationUserFicheClient = applicationUserFicheClientRepository.save(applicationUserFicheClient);
        return applicationUserFicheClientMapper.toDto(applicationUserFicheClient);
    }

    /**
     * Get all the applicationUserFicheClients.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ApplicationUserFicheClientDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ApplicationUserFicheClients");
        return applicationUserFicheClientRepository.findAll(pageable)
            .map(applicationUserFicheClientMapper::toDto);
    }


    /**
     * Get one applicationUserFicheClient by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ApplicationUserFicheClientDTO> findOne(Long id) {
        log.debug("Request to get ApplicationUserFicheClient : {}", id);
        return applicationUserFicheClientRepository.findById(id)
            .map(applicationUserFicheClientMapper::toDto);
    }

    /**
     * Delete the applicationUserFicheClient by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ApplicationUserFicheClient : {}", id);
        applicationUserFicheClientRepository.deleteById(id);
    }
}
