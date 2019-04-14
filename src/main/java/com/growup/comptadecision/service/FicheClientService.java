package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.FicheClient;
import com.growup.comptadecision.domain.ImpotMensuelClient;
import com.growup.comptadecision.repository.FicheClientRepository;
import com.growup.comptadecision.security.SecurityUtils;
import com.growup.comptadecision.service.dto.FicheClientDTO;
import com.growup.comptadecision.service.mapper.FicheClientMapper;
import jdk.nashorn.internal.runtime.options.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.growup.comptadecision.security.SecurityUtils.getCurrentUserLogin;

/**
 * Service Implementation for managing FicheClient.
 */
@Service
@Transactional
public class FicheClientService {

    private final Logger log = LoggerFactory.getLogger(FicheClientService.class);

    private final FicheClientRepository ficheClientRepository;

    private final ImpotMensuelClientService impotMensuelClientService;

    private final FicheClientMapper ficheClientMapper;

    public FicheClientService(FicheClientRepository ficheClientRepository, ImpotMensuelClientService impotMensuelClientService, FicheClientMapper ficheClientMapper) {
        this.ficheClientRepository = ficheClientRepository;
        this.impotMensuelClientService = impotMensuelClientService;
        this.ficheClientMapper = ficheClientMapper;
    }

    /**
     * Save a ficheClient.
     *
     * @param ficheClientDTO the entity to save
     * @return the persisted entity
     */
    public FicheClientDTO save(FicheClientDTO ficheClientDTO) {
        log.debug("Request to save FicheClient : {}", ficheClientDTO);
        FicheClient ficheClient = ficheClientMapper.toEntity(ficheClientDTO);
        if (ficheClient.getId() == null) {
            List<ImpotMensuelClient> impotMensuelClients = impotMensuelClientService.initAllByFicheClient(ficheClient);
            for (ImpotMensuelClient impotMensuelClient : impotMensuelClients) {
                ficheClient.addImpotMensuelClient(impotMensuelClient);
            }
        }
        ficheClient = ficheClientRepository.save(ficheClient);
        return ficheClientMapper.toDto(ficheClient);
    }

    /**
     * Get all the ficheClients.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<FicheClientDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FicheClients");
        String creator = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new UsernameNotFoundException("Only loggued user can access"));
        return ficheClientRepository.findAllByCreatedBy(creator, pageable)
            .map(ficheClientMapper::toDto);
    }


    /**
     * Get one ficheClient by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<FicheClientDTO> findOne(Long id) {
        log.debug("Request to get FicheClient : {}", id);
        return ficheClientRepository.findById(id)
            .map(ficheClientMapper::toDto);
    }

    /**
     * Delete the ficheClient by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete FicheClient : {}", id);
        ficheClientRepository.deleteById(id);
    }
}
