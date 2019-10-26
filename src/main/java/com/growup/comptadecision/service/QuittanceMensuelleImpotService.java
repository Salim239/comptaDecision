package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.FicheClient;
import com.growup.comptadecision.domain.ImpotMensuel;
import com.growup.comptadecision.domain.QuittanceMensuelleImpot;
import com.growup.comptadecision.domain.QuittanceMensuelleImpotDetail;
import com.growup.comptadecision.domain.enumeration.TypeDeclaration;
import com.growup.comptadecision.repository.FicheClientRepository;
import com.growup.comptadecision.repository.ImpotMensuelClientRepository;
import com.growup.comptadecision.repository.ImpotMensuelRepository;
import com.growup.comptadecision.repository.QuittanceMensuelleImpotRepository;
import com.growup.comptadecision.security.SecurityUtils;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotDTO;
import com.growup.comptadecision.service.mapper.QuittanceMensuelleImpotMapper;
import com.growup.comptadecision.service.mapper.QuittanceMensuelleImpotSousDetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
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

    private final ImpotMensuelRepository impotMensuelRepository;

    private final QuittanceMensuelleImpotMapper quittanceMensuelleImpotMapper;

    private final FicheClientRepository ficheClientRepository;

    private final QuittanceMensuelleImpotSousDetailMapper quittanceMensuelleImpotSousDetailMapper;

    public QuittanceMensuelleImpotService(QuittanceMensuelleImpotRepository quittanceMensuelleImpotRepository,
                                          QuittanceMensuelleImpotMapper quittanceMensuelleImpotMapper,
                                          QuittanceMensuelleImpotSousDetailMapper quittanceMensuelleImpotSousDetailMapper,
                                          ImpotMensuelClientRepository impotMensuelClientRepository,
                                          ImpotMensuelService impotMensuelService,
                                          FicheClientRepository ficheClientRepository,
                                          ImpotMensuelRepository impotMensuelRepository) {
        this.quittanceMensuelleImpotRepository = quittanceMensuelleImpotRepository;
        this.quittanceMensuelleImpotMapper = quittanceMensuelleImpotMapper;
        this.quittanceMensuelleImpotSousDetailMapper = quittanceMensuelleImpotSousDetailMapper;
        this.ficheClientRepository = ficheClientRepository;
        this.impotMensuelClientRepository = impotMensuelClientRepository;
        this.impotMensuelRepository = impotMensuelRepository;
        this.impotMensuelService = impotMensuelService;
    }

    public QuittanceMensuelleImpotDTO getEmptyQuittanceMensuel(Long ficheClientId) {

        Integer anneeCourante = Calendar.getInstance().get(Calendar.YEAR);
        Integer moisCourant = Calendar.getInstance().get(Calendar.MONTH);
        FicheClient ficheClient = ficheClientRepository.findById(ficheClientId).orElse(null);
        boolean existsQuittanceInitiale = quittanceMensuelleImpotRepository.findByAnneeAndMoisAndFicheClientIdAndTypeDeclaration(anneeCourante,
                moisCourant, ficheClientId, TypeDeclaration.DECLARATION_INITIALE) != null;

        QuittanceMensuelleImpot quittanceMensuelleImpot = new QuittanceMensuelleImpot(
                ficheClient,
                anneeCourante,
                moisCourant,
                existsQuittanceInitiale ? TypeDeclaration.DECLARATION_RECTIFICATIVE : TypeDeclaration.DECLARATION_INITIALE);

        List<Long> impotMensuelIds = impotMensuelClientRepository.findImpotMensuelDetailIdApplicableByFicheClientId(ficheClientId);

        List<QuittanceMensuelleImpotDetail> quittanceMensuelleImpotDetails = impotMensuelRepository.findAndChildByIds(impotMensuelIds).stream()
                .map(impotMensuel -> {
                    QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail = new QuittanceMensuelleImpotDetail();
                    quittanceMensuelleImpotDetail.setQuittanceMensuelleImpot(quittanceMensuelleImpot);
                    quittanceMensuelleImpotDetail.setImpotMensuel(new ImpotMensuel(impotMensuel.getId()));
                    quittanceMensuelleImpotDetail.setQuittanceMensuelleImpotSousDetails(
                            quittanceMensuelleImpotSousDetailMapper.map(quittanceMensuelleImpotDetail, impotMensuel.getImpotMensuelDetails()));
                    return quittanceMensuelleImpotDetail;
                }).collect(Collectors.toList());



//        List<ImpotMensuel> impotMensuels = impotMensuelRepository.findImpotMensuelApplicableByFicheClientId(ficheClientId);
//        List<QuittanceMensuelleImpotDetail> quittanceMensuelleImpotDetails = impotMensuels.stream()
//                .map(impotMensuel ->
//                        new QuittanceMensuelleImpotDetail(quittanceMensuelleImpot, impotMensuel))
//                .collect(Collectors.toList());
//
//        List<Long> impotMensuelParentIds = impotMensuels.stream()
//                .filter(ImpotMensuel::getParent)
//                .distinct()
//                .map(ImpotMensuel::getId).collect(Collectors.toList());
//        List<QuittanceMensuelleImpotDetail> quittanceMensuelleImpotDetailsChidren = impotMensuelRepository.findByParentIds(impotMensuelParentIds).stream()
//                .map(impotMensuel ->
//                        new QuittanceMensuelleImpotDetail(quittanceMensuelleImpot, impotMensuel))
//                .collect(Collectors.toList());
//        quittanceMensuelleImpotDetails.addAll(quittanceMensuelleImpotDetailsChidren);

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
