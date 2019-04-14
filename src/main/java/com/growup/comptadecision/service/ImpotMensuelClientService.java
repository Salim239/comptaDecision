package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.FicheClient;
import com.growup.comptadecision.domain.ImpotMensuel;
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

import java.util.ArrayList;
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

    private final static Integer[] MOIS_EXERCICE_COMPTABLE = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    public ImpotMensuelClientService(ImpotMensuelClientRepository impotMensuelClientRepository, ImpotMensuelRepository impotMensuelRepository, ImpotMensuelClientMapper impotMensuelClientMapper) {
        this.impotMensuelClientRepository = impotMensuelClientRepository;
        this.impotMensuelRepository = impotMensuelRepository;
        this.impotMensuelClientMapper = impotMensuelClientMapper;
    }

    @Transactional(readOnly = true)
    public List<ImpotMensuelClient> initAllByFicheClient(FicheClient ficheClient) {
        log.debug("Request to init empty list impotMensuelClient for ficheClient {}", ficheClient);
        List<ImpotMensuelClient> impotMensuelClients = new ArrayList<>();
        if (ficheClient.getImpotMensuelClients().isEmpty()) {
            List<ImpotMensuel> impotMensuels = impotMensuelRepository.findAll();
            impotMensuels.forEach(impotMensuel -> {                ;
                for (Integer mois : MOIS_EXERCICE_COMPTABLE) {
                    ImpotMensuelClient impotMensuelClient = new ImpotMensuelClient();
                    impotMensuelClient.setFicheClient(ficheClient);
                    impotMensuelClient.setImpotMensuel(impotMensuel);
                    impotMensuelClient.setApplicable(false);
                    impotMensuelClient.setMois(mois);
                    impotMensuelClients.add(impotMensuelClient);
                }
            });
            return impotMensuelClients;
        } else {
            return ficheClient.getImpotMensuelClients();
        }
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
     * @param mois the mois
     * @return the entity
     */
    @Transactional(readOnly = true)
    public List<ImpotMensuelClientDTO> findByFicheClientIdAndMois(Long ficheClientId, Integer mois) {
        log.debug("Request to get ImpotMensuelClient by ficheClientId {} and mois {}", ficheClientId, mois);
        List<ImpotMensuelClient> impotMensuelClients =  impotMensuelClientRepository.findByFicheClientIdAndMois(ficheClientId, mois);
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
