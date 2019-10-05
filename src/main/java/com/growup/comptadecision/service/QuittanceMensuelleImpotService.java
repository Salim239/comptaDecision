package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.ImpotMensuelClient;
import com.growup.comptadecision.domain.QuittanceMensuelleImpot;
import com.growup.comptadecision.domain.QuittanceMensuelleImpotDetail;
import com.growup.comptadecision.repository.ImpotMensuelClientRepository;
import com.growup.comptadecision.repository.ImpotMensuelDetailRepository;
import com.growup.comptadecision.repository.QuittanceMensuelleImpotRepository;
import com.growup.comptadecision.security.SecurityUtils;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotDTO;
import com.growup.comptadecision.service.mapper.QuittanceMensuelleImpotDetailMapper;
import com.growup.comptadecision.service.mapper.QuittanceMensuelleImpotMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing QuittanceMensuelleImpot.
 */
@Service
@Transactional
public class QuittanceMensuelleImpotService {

    private final Logger log = LoggerFactory.getLogger(QuittanceMensuelleImpotService.class);

    private final QuittanceMensuelleImpotRepository quittanceMensuelleImpotRepository;

    private final ImpotMensuelClientRepository impotMensuelClientRepository;

    private final ImpotMensuelService impotMensuelService;

    private final ImpotMensuelDetailRepository impotMensuelDetailRepository;

    private final QuittanceMensuelleImpotMapper quittanceMensuelleImpotMapper;

    private final QuittanceMensuelleImpotDetailMapper quittanceMensuelleImpotDetailMapper;

    public QuittanceMensuelleImpotService(QuittanceMensuelleImpotRepository quittanceMensuelleImpotRepository,
                                          QuittanceMensuelleImpotMapper quittanceMensuelleImpotMapper,
                                          ImpotMensuelClientRepository impotMensuelClientRepository,
                                          ImpotMensuelService impotMensuelService,
                                          QuittanceMensuelleImpotDetailMapper quittanceMensuelleImpotDetailMapper,
                                          ImpotMensuelDetailRepository impotMensuelDetailRepository) {
        this.quittanceMensuelleImpotRepository = quittanceMensuelleImpotRepository;
        this.quittanceMensuelleImpotMapper = quittanceMensuelleImpotMapper;
        this.quittanceMensuelleImpotDetailMapper = quittanceMensuelleImpotDetailMapper;
        this.impotMensuelClientRepository = impotMensuelClientRepository;
        this.impotMensuelDetailRepository = impotMensuelDetailRepository;
        this.impotMensuelService = impotMensuelService;
    }

    public QuittanceMensuelleImpotDTO getEmptyQuittanceMensuel(Long ficheClientId) {
        QuittanceMensuelleImpot quittanceMensuelleImpot = new QuittanceMensuelleImpot();
        List<ImpotMensuelClient> impotMensuelClients = impotMensuelClientRepository.findByFicheClientId(ficheClientId);
        List<QuittanceMensuelleImpotDetail> quittanceMensuelleImpotDetails = impotMensuelClients.stream()
                .flatMap(impotMensuelClient -> impotMensuelClient.getImpotMensuel().getImpotMensuelDetails().stream()
                        .map(impotMensuelDetail -> new QuittanceMensuelleImpotDetail(quittanceMensuelleImpot, impotMensuelClient, impotMensuelDetail)))
                .collect(Collectors.toList());
        quittanceMensuelleImpot.setQuittanceMensuelleImpotDetails(quittanceMensuelleImpotDetails);
        return quittanceMensuelleImpotMapper.toDto(quittanceMensuelleImpot);
    }

    /**
     * Save a quittanceMensuelleImpot.
     *
     * @param quittanceMensuelleImpotDTO the entity to save
     * @return the persisted entity
     */
    public QuittanceMensuelleImpotDTO save(QuittanceMensuelleImpotDTO quittanceMensuelleImpotDTO) {
        log.debug("Request to save QuittanceMensuelleImpot : {}", quittanceMensuelleImpotDTO);
        QuittanceMensuelleImpot quittanceMensuelleImpot = quittanceMensuelleImpotMapper.toEntity(quittanceMensuelleImpotDTO);
        quittanceMensuelleImpot = quittanceMensuelleImpotRepository.save(quittanceMensuelleImpot);
        return quittanceMensuelleImpotMapper.toDto(quittanceMensuelleImpot);
    }

    /**
     * Get all the quittanceMensuelleImpots.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<QuittanceMensuelleImpotDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuittanceMensuelleImpots");
        String creator = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new UsernameNotFoundException("Only loggued user can access"));
        return quittanceMensuelleImpotRepository.findAllByCreatedBy(creator, pageable)
            .map(quittanceMensuelleImpotMapper::toDto);
    }


    /**
     * Get one quittanceMensuelleImpot by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<QuittanceMensuelleImpotDTO> findOne(Long id) {
        log.debug("Request to get QuittanceMensuelleImpot : {}", id);
        return quittanceMensuelleImpotRepository.findById(id)
            .map(quittanceMensuelleImpotMapper::toDto);
    }

    /**
     * Delete the quittanceMensuelleImpot by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete QuittanceMensuelleImpot : {}", id);
        quittanceMensuelleImpotRepository.deleteById(id);
    }
}
