package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.QuittanceMensuelleImpotLine;
import com.growup.comptadecision.repository.QuittanceMensuelleImpotLineRepository;
import com.growup.comptadecision.security.SecurityUtils;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotLineDTO;
import com.growup.comptadecision.service.mapper.QuittanceMensuelleImpotLineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing QuittanceMensuelleImpotLine.
 */
@Service
@Transactional
public class QuittanceMensuelleImpotLineService {

    private final Logger log = LoggerFactory.getLogger(QuittanceMensuelleImpotLineService.class);

    private final QuittanceMensuelleImpotLineRepository quittanceMensuelleImpotLineRepository;

    private final QuittanceMensuelleImpotLineMapper quittanceMensuelleImpotLineMapper;

    public QuittanceMensuelleImpotLineService(QuittanceMensuelleImpotLineRepository quittanceMensuelleImpotLineRepository, QuittanceMensuelleImpotLineMapper quittanceMensuelleImpotLineMapper) {
        this.quittanceMensuelleImpotLineRepository = quittanceMensuelleImpotLineRepository;
        this.quittanceMensuelleImpotLineMapper = quittanceMensuelleImpotLineMapper;
    }

    /**
     * Save a quittanceMensuelleImpotLine.
     *
     * @param quittanceMensuelleImpotLineDTO the entity to save
     * @return the persisted entity
     */
    public QuittanceMensuelleImpotLineDTO save(QuittanceMensuelleImpotLineDTO quittanceMensuelleImpotLineDTO) {
        log.debug("Request to save QuittanceMensuelleImpotLine : {}", quittanceMensuelleImpotLineDTO);
        QuittanceMensuelleImpotLine quittanceMensuelleImpotLine = quittanceMensuelleImpotLineMapper.toEntity(quittanceMensuelleImpotLineDTO);
        quittanceMensuelleImpotLine = quittanceMensuelleImpotLineRepository.save(quittanceMensuelleImpotLine);
        return quittanceMensuelleImpotLineMapper.toDto(quittanceMensuelleImpotLine);
    }

    /**
     * Get all the quittanceMensuelleImpotLines.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<QuittanceMensuelleImpotLineDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuittanceMensuelleImpotLines");
        String creator = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new UsernameNotFoundException("Only loggued user can access"));
        return quittanceMensuelleImpotLineRepository.findAllByCreatedBy(creator, pageable)
            .map(quittanceMensuelleImpotLineMapper::toDto);
    }


    /**
     * Get one quittanceMensuelleImpotLine by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<QuittanceMensuelleImpotLineDTO> findOne(Long id) {
        log.debug("Request to get QuittanceMensuelleImpotLine : {}", id);
        return quittanceMensuelleImpotLineRepository.findById(id)
            .map(quittanceMensuelleImpotLineMapper::toDto);
    }

    /**
     * Delete the quittanceMensuelleImpotLine by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete QuittanceMensuelleImpotLine : {}", id);
        quittanceMensuelleImpotLineRepository.deleteById(id);
    }
}
