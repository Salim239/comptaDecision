package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.ImpotMensuelClient;
import com.growup.comptadecision.repository.ImpotMensuelClientRepository;
import com.growup.comptadecision.repository.ImpotMensuelRepository;
import com.growup.comptadecision.security.SecurityUtils;
import com.growup.comptadecision.service.dto.ImpotMensuelClientDTO;
import com.growup.comptadecision.service.mapper.ImpotMensuelClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing ImpotMensuelClient.
 */
@Service
@Transactional
public class ImpotMensuelClientService {

    private final Logger log = LoggerFactory.getLogger(ImpotMensuelClientService.class);

    private final ImpotMensuelClientRepository impotMensuelClientRepository;

    private final ImpotMensuelRepository impotMensuelRepository;

    private final ImpotMensuelClientMapper impotMensuelClientMapper;

    public ImpotMensuelClientService(ImpotMensuelClientRepository impotMensuelClientRepository, ImpotMensuelRepository impotMensuelRepository, ImpotMensuelClientMapper impotMensuelClientMapper) {
        this.impotMensuelClientRepository = impotMensuelClientRepository;
        this.impotMensuelRepository = impotMensuelRepository;
        this.impotMensuelClientMapper = impotMensuelClientMapper;
    }

    /**
     * Save a impotMensuelClient.
     *
     * @param impotMensuelClientDTO the entity to save
     * @return the persisted entity
     */
    public ImpotMensuelClientDTO save(ImpotMensuelClientDTO impotMensuelClientDTO) {
        log.debug("Request to save ImpotMensuelClient : {}", impotMensuelClientDTO);
        ImpotMensuelClient impotMensuelClient = impotMensuelClientMapper.toEntity(impotMensuelClientDTO);
        impotMensuelClient = impotMensuelClientRepository.save(impotMensuelClient);
        return impotMensuelClientMapper.toDto(impotMensuelClient);
    }

    /**
     * Get all the impotMensuelClients.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ImpotMensuelClientDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ImpotMensuelClients");
        String creator = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new UsernameNotFoundException("Only loggued user can access"));
        return impotMensuelClientRepository.findAllByCreatedBy(creator, pageable)
            .map(impotMensuelClientMapper::toDto);
    }


    /**
     * Get one impotMensuelClient by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ImpotMensuelClientDTO> findOne(Long id) {
        log.debug("Request to get ImpotMensuelClient : {}", id);
        return impotMensuelClientRepository.findById(id)
            .map(impotMensuelClientMapper::toDto);
    }

    /**
     * find impotMensuelClient by ficheClientId and mois.
     *
     * @param ficheClientId the id of the ficheClient
     * @return the entity
     */
    @Transactional(readOnly = true)
    public List<ImpotMensuelClientDTO> findByFicheClientId(Long ficheClientId) {
        log.debug("Request to get ImpotMensuelClient by ficheClientId {}", ficheClientId);
        List<ImpotMensuelClient> impotMensuelClients =  impotMensuelClientRepository.findByFicheClientId(ficheClientId);
        return impotMensuelClientMapper.toDto(impotMensuelClients);
    }

    /**
     * Delete the impotMensuelClient by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ImpotMensuelClient : {}", id);
        impotMensuelClientRepository.deleteById(id);
    }
}
