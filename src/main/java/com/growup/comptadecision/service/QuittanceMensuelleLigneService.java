package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.QuittanceMensuelleLigne;
import com.growup.comptadecision.domain.enumeration.StatutDeclaration;
import com.growup.comptadecision.repository.QuittanceMensuelleLigneRepository;
import com.growup.comptadecision.security.SecurityUtils;
import com.growup.comptadecision.service.dto.QuittanceMensuelleLigneDTO;
import com.growup.comptadecision.service.mapper.QuittanceMensuelleLigneMapper;
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
 * Service Implementation for managing QuittanceMensuelleLigne.
 */
@Service
@Transactional
public class QuittanceMensuelleLigneService {

    private final Logger log = LoggerFactory.getLogger(QuittanceMensuelleLigneService.class);

    private final QuittanceMensuelleLigneRepository quittanceMensuelleLigneRepository;

    private final QuittanceMensuelleLigneMapper quittanceMensuelleLigneMapper;

    public QuittanceMensuelleLigneService(QuittanceMensuelleLigneRepository quittanceMensuelleLigneRepository, QuittanceMensuelleLigneMapper quittanceMensuelleLigneMapper) {
        this.quittanceMensuelleLigneRepository = quittanceMensuelleLigneRepository;
        this.quittanceMensuelleLigneMapper = quittanceMensuelleLigneMapper;
    }

    /**
     * Save a quittanceMensuelleLigne.
     *
     * @param quittanceMensuelleLigneDTO the entity to save
     * @return the persisted entity
     */
    public QuittanceMensuelleLigneDTO save(QuittanceMensuelleLigneDTO quittanceMensuelleLigneDTO) {
        log.debug("Request to save QuittanceMensuelleLigne : {}", quittanceMensuelleLigneDTO);
        QuittanceMensuelleLigne quittanceMensuelleLigne = quittanceMensuelleLigneMapper.toEntity(quittanceMensuelleLigneDTO);
        quittanceMensuelleLigne = quittanceMensuelleLigneRepository.save(quittanceMensuelleLigne);
        return quittanceMensuelleLigneMapper.toDto(quittanceMensuelleLigne);
    }

    /**
     * Get all the quittanceMensuelleLignes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<QuittanceMensuelleLigneDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuittanceMensuelleLignes");
        String creator = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new UsernameNotFoundException("Only loggued user can access"));
        return quittanceMensuelleLigneRepository.findAllByCreatedBy(creator, pageable)
            .map(quittanceMensuelleLigneMapper::toDto);
    }


    /**
     * Get one quittanceMensuelleLigne by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<QuittanceMensuelleLigneDTO> findOne(Long id) {
        log.debug("Request to get QuittanceMensuelleLigne : {}", id);
        return quittanceMensuelleLigneRepository.findById(id)
            .map(quittanceMensuelleLigneMapper::toDto);
    }

    /**
     * Delete the quittanceMensuelleLigne by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete QuittanceMensuelleLigne : {}", id);
        quittanceMensuelleLigneRepository.deleteById(id);
    }

    public Optional<QuittanceMensuelleLigne> findByFicheClientIdAndQuittanceStatutAndAnneeAndMoisAndCode(Long ficheClientId,
                                                                                                               List<StatutDeclaration> statuts,
                                                                                                               Integer annee,
                                                                                                               Integer mois,
                                                                                                               String code) {

        return quittanceMensuelleLigneRepository.findByFicheClientIdAndQuittanceStatutsAndAnneeAndMoisAndCode(ficheClientId, statuts, annee, mois, code);
    }
}
