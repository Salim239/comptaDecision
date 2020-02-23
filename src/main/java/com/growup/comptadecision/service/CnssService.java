package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.Cnss;
import com.growup.comptadecision.domain.FicheClient;
import com.growup.comptadecision.domain.enumeration.TypeCnss;
import com.growup.comptadecision.repository.CnssRepository;
import com.growup.comptadecision.repository.FicheClientRepository;
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

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Cnss.
 */
@Service
@Transactional
public class CnssService {

    private final Logger log = LoggerFactory.getLogger(CnssService.class);

    private final CnssRepository cnssRepository;

    private final FicheClientRepository ficheClientRepository;

    private final CnssMapper cnssMapper;

    public CnssService(CnssRepository cnssRepository, FicheClientRepository ficheClientRepository, CnssMapper cnssMapper) {
        this.cnssRepository = cnssRepository;
        this.ficheClientRepository = ficheClientRepository;
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
     * Get all the cnss.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CnssDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cnss");
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

    private void validateCreationForm(FicheClient ficheClient, TypeCnss typeCnss, Integer annee, Integer trimestre) {

        List<Cnss> cnss = cnssRepository.findByFicheClientIdAndAnneeAndTrimestre(ficheClient.getId(), annee, trimestre);
        if (cnss.stream().anyMatch(c -> c.getTypeCnss() == typeCnss)) {
            new RuntimeException(String.format("Il existe déjà une déclaration cnss de type %s pour le trimestre %s/%s", typeCnss.toString(), annee, trimestre));
        }
    }

    private CnssDTO getEmptyCnss(FicheClient ficheClient, TypeCnss typeCnss, Integer annee, Integer trimestre) {

        Cnss cnss = new Cnss();
        cnss.setFicheClient(ficheClient);
        cnss.setAnnee(annee);
        cnss.setTrimestre(trimestre);
        cnss.setTypeCnss(typeCnss);
        return cnssMapper.toDto(cnss);
    }

    public CnssDTO init(Long ficheClientId, Integer annee, TypeCnss typeCnss, Integer trimestre) {
        log.debug("Request to init new Cnss type %s for year {} client id {} and trimestre {}", typeCnss.toString(), annee, ficheClientId,  trimestre);
        FicheClient ficheClient = ficheClientRepository.findById(ficheClientId).orElseThrow(() -> new RuntimeException(String.format("FicheClient not found with id %s", ficheClientId)));
        validateCreationForm(ficheClient, typeCnss, annee, trimestre);
        return getEmptyCnss(ficheClient, typeCnss, annee, trimestre);
    }
}
