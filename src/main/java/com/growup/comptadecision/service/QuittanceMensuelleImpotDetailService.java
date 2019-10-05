package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.QuittanceMensuelleImpotDetail;
import com.growup.comptadecision.repository.QuittanceMensuelleImpotDetailRepository;
import com.growup.comptadecision.security.SecurityUtils;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotDetailDTO;
import com.growup.comptadecision.service.mapper.QuittanceMensuelleImpotDetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing QuittanceMensuelleImpotDetail.
 */
@Service
@Transactional
public class QuittanceMensuelleImpotDetailService {

    private final Logger log = LoggerFactory.getLogger(QuittanceMensuelleImpotDetailService.class);

    private final QuittanceMensuelleImpotDetailRepository quittanceMensuelleImpotDetailRepository;

    private final QuittanceMensuelleImpotDetailMapper quittanceMensuelleImpotDetailMapper;

    public QuittanceMensuelleImpotDetailService(QuittanceMensuelleImpotDetailRepository quittanceMensuelleImpotDetailRepository, QuittanceMensuelleImpotDetailMapper quittanceMensuelleImpotDetailMapper) {
        this.quittanceMensuelleImpotDetailRepository = quittanceMensuelleImpotDetailRepository;
        this.quittanceMensuelleImpotDetailMapper = quittanceMensuelleImpotDetailMapper;
    }

    /**
     * Save a quittanceMensuelleImpotDetail.
     *
     * @param quittanceMensuelleImpotDetailDTO the entity to save
     * @return the persisted entity
     */
    public QuittanceMensuelleImpotDetailDTO save(QuittanceMensuelleImpotDetailDTO quittanceMensuelleImpotDetailDTO) {
        log.debug("Request to save QuittanceMensuelleImpotDetail : {}", quittanceMensuelleImpotDetailDTO);
        QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail = quittanceMensuelleImpotDetailMapper.toEntity(quittanceMensuelleImpotDetailDTO);
        quittanceMensuelleImpotDetail = quittanceMensuelleImpotDetailRepository.save(quittanceMensuelleImpotDetail);
        return quittanceMensuelleImpotDetailMapper.toDto(quittanceMensuelleImpotDetail);
    }

    /**
     * Get all the quittanceMensuelleImpotDetails.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<QuittanceMensuelleImpotDetailDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuittanceMensuelleImpotDetails");
        String creator = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new UsernameNotFoundException("Only loggued user can access"));
        return quittanceMensuelleImpotDetailRepository.findAllByCreatedBy(creator, pageable)
            .map(quittanceMensuelleImpotDetailMapper::toDto);
    }


    /**
     * Get one quittanceMensuelleImpotDetail by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<QuittanceMensuelleImpotDetailDTO> findOne(Long id) {
        log.debug("Request to get QuittanceMensuelleImpotDetail : {}", id);
        return quittanceMensuelleImpotDetailRepository.findById(id)
            .map(quittanceMensuelleImpotDetailMapper::toDto);
    }

    /**
     * Delete the quittanceMensuelleImpotDetail by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete QuittanceMensuelleImpotDetail : {}", id);
        quittanceMensuelleImpotDetailRepository.deleteById(id);
    }
}
