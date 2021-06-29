package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.Caisse;
import com.growup.comptadecision.domain.FicheClient;
import com.growup.comptadecision.repository.CaisseRepository;
import com.growup.comptadecision.service.dto.CaisseDTO;
import com.growup.comptadecision.service.mapper.CaisseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Caisse.
 */
@Service
@Transactional
public class CaisseService {

    private final Logger log = LoggerFactory.getLogger(CaisseService.class);

    private final CaisseRepository caisseRepository;

    private final CaisseMapper caisseMapper;

    public CaisseService(CaisseRepository caisseRepository, CaisseMapper caisseMapper) {
        this.caisseRepository = caisseRepository;
        this.caisseMapper = caisseMapper;
    }

    /**
     * Save a caisse.
     *
     * @param caisseDTO the entity to save
     * @return the persisted entity
     */
    public CaisseDTO save(CaisseDTO caisseDTO) {
        log.debug("Request to save Caisse : {}", caisseDTO);
        Caisse caisse = caisseMapper.toEntity(caisseDTO);
        caisse = caisseRepository.save(caisse);
        return caisseMapper.toDto(caisse);
    }

    /**
     * Get all the caisses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CaisseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Caisses");
        return caisseRepository.findAll(pageable)
            .map(caisseMapper::toDto);
    }


    /**
     * Get one caisse by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<CaisseDTO> findOne(Long id) {
        log.debug("Request to get Caisse : {}", id);
        return caisseRepository.findById(id)
            .map(caisseMapper::toDto);
    }

    /**
     * Delete the caisse by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Caisse : {}", id);
        caisseRepository.deleteById(id);
    }

    public CaisseDTO createIfNotExists(FicheClient ficheClient) {
        log.info("Request to find or create new caisse for ficeclientId {}", ficheClient);
        Optional<Caisse> caisseOptional = caisseRepository.findByClientId(ficheClient.getId());
        if (!caisseOptional.isPresent()) {
            Caisse caisse = new Caisse();
            caisse.setFicheClient(ficheClient);
            caisse = caisseRepository.save(caisse);
            return caisseMapper.toDto(caisse);
        } else {
            return caisseMapper.toDto(caisseOptional.get());
        }
    }
}
