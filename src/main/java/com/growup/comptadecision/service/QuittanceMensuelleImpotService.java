package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.ImpotMensuelClient;
import com.growup.comptadecision.domain.QuittanceMensuelleImpot;
import com.growup.comptadecision.domain.QuittanceMensuelleImpotLine;
import com.growup.comptadecision.repository.ImpotMensuelClientRepository;
import com.growup.comptadecision.repository.QuittanceMensuelleImpotRepository;
import com.growup.comptadecision.service.dto.ImpotMensuelClientDTO;
import com.growup.comptadecision.service.dto.ImpotMensuelDTO;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotDTO;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotLineDTO;
import com.growup.comptadecision.service.mapper.ImpotMensuelClientMapper;
import com.growup.comptadecision.service.mapper.QuittanceMensuelleImpotLineMapper;
import com.growup.comptadecision.service.mapper.QuittanceMensuelleImpotMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

    private final ImpotMensuelService impotMensuelService;

    private final QuittanceMensuelleImpotMapper quittanceMensuelleImpotMapper;

    private final QuittanceMensuelleImpotLineMapper quittanceMensuelleImpotLineMapper;

    public QuittanceMensuelleImpotService(QuittanceMensuelleImpotRepository quittanceMensuelleImpotRepository,
                                          QuittanceMensuelleImpotMapper quittanceMensuelleImpotMapper,
                                          ImpotMensuelClientService impotMensuelClientService,
                                          ImpotMensuelService impotMensuelService,
                                          QuittanceMensuelleImpotLineMapper quittanceMensuelleImpotLineMapper) {
        this.quittanceMensuelleImpotRepository = quittanceMensuelleImpotRepository;
        this.quittanceMensuelleImpotMapper = quittanceMensuelleImpotMapper;
        this.quittanceMensuelleImpotLineMapper = quittanceMensuelleImpotLineMapper;
        this.impotMensuelClientService = impotMensuelClientService;
        this.impotMensuelService = impotMensuelService;
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
        quittanceMensuelleImpot.getQuittanceMensuelleImpotLines().clear();
        for (QuittanceMensuelleImpotLineDTO quittanceMensuelleImpotLine : quittanceMensuelleImpotDTO.getQuittanceMensuelleImpotLines()) {
            quittanceMensuelleImpot.addQuittanceMensuelleImpotLine( quittanceMensuelleImpotLineMapper.toEntity( quittanceMensuelleImpotLine ))  ;
        }
        quittanceMensuelleImpot.setMontantPaye(quittanceMensuelleImpot.getQuittanceMensuelleImpotLines()
                .stream().map(QuittanceMensuelleImpotLine::getMontantPaye)
                        .reduce(BigDecimal.ZERO, BigDecimal::add));
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

    public QuittanceMensuelleImpotDTO initByParams(Long ficheClientId, Integer mois) {

        log.debug("REST to init QuittanceMensuelleImpot de la fiche client : {}, pour le mois {}", ficheClientId, mois);
        List<ImpotMensuelClientDTO> impotMensuelClientDTOs = impotMensuelClientService.findByFicheClientIdAndMois(ficheClientId, mois);
        List<QuittanceMensuelleImpotLineDTO> quittanceMensuelleImpotLineDTOs = impotMensuelClientDTOs.stream().map(impotMensuelClientDTO -> {
            QuittanceMensuelleImpotLineDTO quittanceMensuelleImpotLineDTO = new QuittanceMensuelleImpotLineDTO();
            quittanceMensuelleImpotLineDTO.setImpotMensuelClient(impotMensuelClientDTO);
            quittanceMensuelleImpotLineDTO.setMontantPaye(BigDecimal.ZERO);
            return quittanceMensuelleImpotLineDTO;
        }).collect(Collectors.toList());

        QuittanceMensuelleImpotDTO quittanceMensuelleImpotDTO = new QuittanceMensuelleImpotDTO();
        quittanceMensuelleImpotDTO.setQuittanceMensuelleImpotLines(quittanceMensuelleImpotLineDTOs);
        quittanceMensuelleImpotDTO.setMois(mois);
        quittanceMensuelleImpotDTO.setFicheClientId(ficheClientId);
        return quittanceMensuelleImpotDTO;
    }

    public QuittanceMensuelleImpotDTO init() {

        log.debug("REST to init empty QuittanceMensuelleImpot");

        List<ImpotMensuelDTO> impotMensuelDTOs = impotMensuelService.findAll();
        List<QuittanceMensuelleImpotLineDTO> quittanceMensuelleImpotLineDTOs = impotMensuelDTOs.stream().map(impotMensuelDTO -> {
            QuittanceMensuelleImpotLineDTO quittanceMensuelleImpotLineDTO = new QuittanceMensuelleImpotLineDTO();
            ImpotMensuelClientDTO impotMensuelClientDTO = new ImpotMensuelClientDTO();
            impotMensuelClientDTO.setApplicable(true);
            impotMensuelClientDTO.setImpotMensuelId(impotMensuelDTO.getId());
            impotMensuelClientDTO.setImpotMensuelDescription(impotMensuelDTO.getDescription());
            impotMensuelClientDTO.setImpotMensuelLibelle(impotMensuelDTO.getLibelle());
            quittanceMensuelleImpotLineDTO.setMontantPaye(BigDecimal.ZERO);
            quittanceMensuelleImpotLineDTO.setImpotMensuelClient(impotMensuelClientDTO);
            return quittanceMensuelleImpotLineDTO;
        }).collect(Collectors.toList());

        QuittanceMensuelleImpotDTO quittanceMensuelleImpotDTO = new QuittanceMensuelleImpotDTO();
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
