package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.FicheClient;
import com.growup.comptadecision.repository.FicheClientRepository;
import com.growup.comptadecision.security.SecurityUtils;
import com.growup.comptadecision.service.dto.FicheClientDTO;
import com.growup.comptadecision.service.dto.ImpotMensuelClientDTO;
import com.growup.comptadecision.service.dto.ImpotMensuelDTO;
import com.growup.comptadecision.service.mapper.FicheClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing FicheClient.
 */
@Service
@Transactional
public class FicheClientService {

    //todo ajouter dans fichier properties
    private static final Double TAUX_CNSS_ACCIDENT_PAR_DEFAUT = 0.05;

    private final Logger log = LoggerFactory.getLogger(FicheClientService.class);

    private final FicheClientRepository ficheClientRepository;

    private final ImpotMensuelClientService impotMensuelClientService;

    private final ImpotMensuelService impotMensuelService;

    private final FicheClientMapper ficheClientMapper;

    public FicheClientService(FicheClientRepository ficheClientRepository, ImpotMensuelService impotMensuelService, ImpotMensuelClientService impotMensuelClientService, FicheClientMapper ficheClientMapper) {
        this.ficheClientRepository = ficheClientRepository;
        this.impotMensuelClientService = impotMensuelClientService;
        this.impotMensuelService = impotMensuelService;
        this.ficheClientMapper = ficheClientMapper;
    }

    /**
     * Init empty fiche client
     * @return
     */
    public FicheClientDTO init() {

        log.debug("REST to init empty FicheClient");
        FicheClientDTO ficheClientDTO = new FicheClientDTO();
        List<ImpotMensuelDTO> impotMensuelDTOs = impotMensuelService.findWithoutChildren();
        List<ImpotMensuelClientDTO> impotMensuelClientDTOs = new ArrayList<>();
        impotMensuelDTOs.forEach(impotMensuelDTO -> {
                ImpotMensuelClientDTO impotMensuelClientDTO = new ImpotMensuelClientDTO();
                impotMensuelClientDTO.setApplicable(false);
                impotMensuelClientDTO.setImpotMensuelId(impotMensuelDTO.getId());
                impotMensuelClientDTO.setImpotMensuelDescription(impotMensuelDTO.getDescription());
                impotMensuelClientDTO.setImpotMensuelLibelle(impotMensuelDTO.getLibelle());
                impotMensuelClientDTO.setImpotMensuelChild(impotMensuelDTO.getChild());
                impotMensuelClientDTO.setImpotMensuelParent(impotMensuelDTO.getParent());
                impotMensuelClientDTO.setImpotMensuelParentId(impotMensuelDTO.getParentImpotMensuelId());
                impotMensuelClientDTOs.add(impotMensuelClientDTO);
        });
        ficheClientDTO.setImpotMensuelClients(impotMensuelClientDTOs);
        ficheClientDTO.setTauxCnssAccident(TAUX_CNSS_ACCIDENT_PAR_DEFAUT);
        return ficheClientDTO;
    }

    /**
     * Save a ficheClient.
     *
     * @param ficheClientDTO the entity to save
     * @return the persisted entity
     */
    public FicheClientDTO save(FicheClientDTO ficheClientDTO) {
        log.debug("Request to save FicheClient : {}", ficheClientDTO);
        updateImpotMensuelClientChildren(ficheClientDTO);
        FicheClient ficheClient = ficheClientMapper.toEntity(ficheClientDTO);
        ficheClient = ficheClientRepository.save(ficheClient);
        return ficheClientMapper.toDto(ficheClient);
    }

    private void updateImpotMensuelClientChildren(FicheClientDTO ficheClientDTO) {
        List<ImpotMensuelClientDTO> parentImpotMensuelClientDtos = ficheClientDTO.getImpotMensuelClients().stream()
                .filter(impotMensuelClientDTO -> impotMensuelClientDTO.getImpotMensuelParentId() != null)
                .collect(Collectors.toList());

        parentImpotMensuelClientDtos.forEach(parentImpotMensuelClientDto -> {
            ficheClientDTO.getImpotMensuelClients().stream()
                    .filter(impotMensuelClientDTO -> impotMensuelClientDTO.getImpotMensuelParentId().equals(parentImpotMensuelClientDto.getId()))
                    .forEach(impotMensuelClientDTO -> impotMensuelClientDTO.setApplicable(parentImpotMensuelClientDto.getApplicable()));
        });
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
        ficheClientRepository.findById(id);
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
