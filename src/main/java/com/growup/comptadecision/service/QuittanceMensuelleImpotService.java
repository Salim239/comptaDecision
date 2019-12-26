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

import java.util.ArrayList;
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
        this.impotMensuelRepository = impotMensuelRepository;
    }

    public QuittanceMensuelleImpotDTO init() {

        return getEmptyQuittanceMensuel(null);
    }

    public QuittanceMensuelleImpotDTO getEmptyQuittanceMensuel(Long ficheClientId) {

        Integer anneeCourante = Calendar.getInstance().get(Calendar.YEAR);
        Integer moisCourant = Calendar.getInstance().get(Calendar.MONTH);
        FicheClient ficheClient = null;
        if (ficheClientId != null) {
            ficheClient = ficheClientRepository.findById(ficheClientId)
                    .orElseThrow(() -> new RuntimeException(String.format("FicheClient not found with id %s", ficheClientId)));
        } else {
            ficheClient = ficheClientRepository.count() > 0 ? ficheClientRepository.findAll().get(0) : null;
        }
        boolean existsQuittanceInitiale = quittanceMensuelleImpotRepository.findByAnneeAndMoisAndFicheClientIdAndTypeDeclaration(anneeCourante,
                moisCourant, ficheClientId, TypeDeclaration.DECLARATION_INITIALE) != null;

        QuittanceMensuelleImpot quittanceMensuelleImpot = new QuittanceMensuelleImpot(
                ficheClient,
                anneeCourante,
                moisCourant,
                !existsQuittanceInitiale || ficheClient == null? TypeDeclaration.DECLARATION_INITIALE : TypeDeclaration.DECLARATION_RECTIFICATIVE);

        if (quittanceMensuelleImpot.getFicheClient() != null) {
            initQuittanceImpotMensuelDetails(quittanceMensuelleImpot);
        }

            return quittanceMensuelleImpotMapper.toDto(quittanceMensuelleImpot);
    }

    /**
     * Init quittance men,suel details from impot mensuel applicable au client
     * @param quittanceMensuelleImpot quittanceMensuel object
     */
    private void initQuittanceImpotMensuelDetails(QuittanceMensuelleImpot quittanceMensuelleImpot) {
        List<ImpotMensuel> impotMensuels = impotMensuelRepository.findImpotMensuelApplicableByFicheClientId(quittanceMensuelleImpot.getFicheClient().getId());
        List<QuittanceMensuelleImpotDetail> quittanceMensuelleImpotDetails = impotMensuels.stream()
                .map(impotMensuel -> {
                    QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail = getQuittanceMensuelImpotDetail(quittanceMensuelleImpot, impotMensuel);
                    List<QuittanceMensuelleImpotDetail> childQuittanceMensuelleImpotDetails = new ArrayList<>();
                    for (ImpotMensuel childImpotMensuel : impotMensuel.getChildImpotMensuels()) {
                        QuittanceMensuelleImpotDetail childQuittanceMensuelleImpotDetail = getQuittanceMensuelImpotDetail(quittanceMensuelleImpot, childImpotMensuel, quittanceMensuelleImpotDetail);
                        childQuittanceMensuelleImpotDetails.add(childQuittanceMensuelleImpotDetail);
                        quittanceMensuelleImpotDetail.getChildQuittanceMensuelleImpotDetails().add(childQuittanceMensuelleImpotDetail);
                    }
                    quittanceMensuelleImpotDetail.setChildQuittanceMensuelleImpotDetails(childQuittanceMensuelleImpotDetails);

                    return quittanceMensuelleImpotDetail;
                }).collect(Collectors.toList());
        quittanceMensuelleImpot.setQuittanceMensuelleImpotDetails(quittanceMensuelleImpotDetails);
    }

    private QuittanceMensuelleImpotDetail getQuittanceMensuelImpotDetail(QuittanceMensuelleImpot quittanceMensuelleImpot, ImpotMensuel impotMensuel) {
        return getQuittanceMensuelImpotDetail(quittanceMensuelleImpot, impotMensuel, null);
    }

    private QuittanceMensuelleImpotDetail getQuittanceMensuelImpotDetail(QuittanceMensuelleImpot quittanceMensuelleImpot, ImpotMensuel impotMensuel, QuittanceMensuelleImpotDetail parentQuittanceMensuelleImpotDetail) {
        QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail = new QuittanceMensuelleImpotDetail();
        quittanceMensuelleImpotDetail.setQuittanceMensuelleImpot(quittanceMensuelleImpot);
        quittanceMensuelleImpotDetail.setImpotMensuel(impotMensuel);
        quittanceMensuelleImpotDetail.setParent(impotMensuel.getParent());
        quittanceMensuelleImpotDetail.setLibelle(impotMensuel.getLibelle());
        quittanceMensuelleImpotDetail.setChild(impotMensuel.getChild());
        quittanceMensuelleImpotDetail.setParentQuittanceMensuelleImpotDetail(parentQuittanceMensuelleImpotDetail);
        quittanceMensuelleImpotDetail.setQuittanceMensuelleImpotSousDetails(
                quittanceMensuelleImpotSousDetailMapper.map(quittanceMensuelleImpotDetail, impotMensuel.getImpotMensuelDetails()));
        return quittanceMensuelleImpotDetail;
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
