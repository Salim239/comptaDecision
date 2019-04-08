package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.ImpotMensuelClient;
import com.growup.comptadecision.domain.QuittanceMensuelleImpot;
import com.growup.comptadecision.domain.QuittanceMensuelleImpotLine;
import com.growup.comptadecision.repository.ImpotMensuelClientRepository;
import com.growup.comptadecision.repository.QuittanceMensuelleImpotRepository;
import com.growup.comptadecision.service.dto.ImpotMensuelClientDTO;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotDTO;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotLineDTO;
import com.growup.comptadecision.service.mapper.QuittanceMensuelleImpotMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    private final ImpotMensuelClientService impotMensuelClientService;

    private final QuittanceMensuelleImpotMapper quittanceMensuelleImpotMapper;

    public QuittanceMensuelleImpotService(QuittanceMensuelleImpotRepository quittanceMensuelleImpotRepository,
                                          QuittanceMensuelleImpotMapper quittanceMensuelleImpotMapper,
                                          ImpotMensuelClientService impotMensuelClientService) {
        this.quittanceMensuelleImpotRepository = quittanceMensuelleImpotRepository;
        this.quittanceMensuelleImpotMapper = quittanceMensuelleImpotMapper;
        this.impotMensuelClientService = impotMensuelClientService;
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
        return quittanceMensuelleImpotRepository.findAll(pageable)
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

    public QuittanceMensuelleImpotDTO initEmptyQuittanceMensuelleImpot(QuittanceMensuelleImpotDTO quittanceMensuelleImpotDTO) {

        log.debug("REST to init empty QuittanceMensuelleImpot de la fiche client : {}, pour le mois {}", quittanceMensuelleImpotDTO.getFicheClientId(), quittanceMensuelleImpotDTO.getMois());
        if (quittanceMensuelleImpotDTO.getMois() == null || quittanceMensuelleImpotDTO.getFicheClientId() == null) {
            return quittanceMensuelleImpotDTO;
        }
        List<ImpotMensuelClientDTO> impotMensuelClientDTOs = impotMensuelClientService.findByFicheClientIdAndMois(quittanceMensuelleImpotDTO.getFicheClientId(), quittanceMensuelleImpotDTO.getMois());
        List<QuittanceMensuelleImpotLineDTO> quittanceMensuelleImpotLineDTOs = impotMensuelClientDTOs.stream().map(impotMensuelClientDTO -> {
            QuittanceMensuelleImpotLineDTO quittanceMensuelleImpotLineDTO = new QuittanceMensuelleImpotLineDTO();
            quittanceMensuelleImpotLineDTO.setImpotMensuelClient(impotMensuelClientDTO);
            return quittanceMensuelleImpotLineDTO;
        }).collect(Collectors.toList());
        quittanceMensuelleImpotDTO.setQuittanceMensuelleImpotLines(quittanceMensuelleImpotLineDTOs);
        return quittanceMensuelleImpotDTO;
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
