package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.FicheClient;
import com.growup.comptadecision.domain.ImpotMensuel;
import com.growup.comptadecision.domain.QuittanceMensuelleImpot;
import com.growup.comptadecision.domain.QuittanceMensuelleImpotDetail;
import com.growup.comptadecision.domain.enumeration.StatutDeclaration;
import com.growup.comptadecision.domain.enumeration.TypeDeclaration;
import com.growup.comptadecision.repository.FicheClientRepository;
import com.growup.comptadecision.repository.ImpotMensuelRepository;
import com.growup.comptadecision.repository.QuittanceMensuelleImpotRepository;
import com.growup.comptadecision.security.SecurityUtils;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotDTO;
import com.growup.comptadecision.service.mapper.QuittanceMensuelleImpotMapper;
import com.growup.comptadecision.service.mapper.QuittanceMensuelleImpotSousDetailMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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

    private final QuittanceMensuelleImpotDetailService quittanceMensuelleImpotDetailService;

    private final QuittanceMensuelleImpotSousDetailMapper quittanceMensuelleImpotSousDetailMapper;

    public QuittanceMensuelleImpotService(QuittanceMensuelleImpotRepository quittanceMensuelleImpotRepository,
                                          QuittanceMensuelleImpotMapper quittanceMensuelleImpotMapper,
                                          QuittanceMensuelleImpotSousDetailMapper quittanceMensuelleImpotSousDetailMapper,
                                          FicheClientRepository ficheClientRepository,
                                          ImpotMensuelRepository impotMensuelRepository, QuittanceMensuelleImpotDetailService quittanceMensuelleImpotDetailService) {
        this.quittanceMensuelleImpotRepository = quittanceMensuelleImpotRepository;
        this.quittanceMensuelleImpotMapper = quittanceMensuelleImpotMapper;
        this.quittanceMensuelleImpotSousDetailMapper = quittanceMensuelleImpotSousDetailMapper;
        this.ficheClientRepository = ficheClientRepository;
        this.impotMensuelRepository = impotMensuelRepository;
        this.quittanceMensuelleImpotDetailService = quittanceMensuelleImpotDetailService;
    }

    private QuittanceMensuelleImpot getParentQuittance(QuittanceMensuelleImpot quittanceMensuelleImpot) {

        return quittanceMensuelleImpotRepository.findByAnneeAndMoisAndFicheClientIdAndTypeDeclaration(
                quittanceMensuelleImpot.getAnnee(),
                quittanceMensuelleImpot.getMois(),
                quittanceMensuelleImpot.getFicheClient().getId(),
                TypeDeclaration.DECLARATION_INITIALE).orElseThrow(() -> new RuntimeException("Cette quittance rectificative ne possède pas de quittance initiale associée"));
    }

    private void updateStatutQuittance(QuittanceMensuelleImpot quittanceMensuelleImpot) {

        if (quittanceMensuelleImpot.getTypeDeclaration().equals(TypeDeclaration.DECLARATION_RECTIFICATIVE)) {

            QuittanceMensuelleImpot quittanceInitiale = quittanceMensuelleImpotRepository.findByAnneeAndMoisAndFicheClientIdAndTypeDeclaration(
                    quittanceMensuelleImpot.getAnnee(),
                    quittanceMensuelleImpot.getMois(),
                    quittanceMensuelleImpot.getFicheClient().getId(),
                    TypeDeclaration.DECLARATION_INITIALE).orElseThrow(() -> new RuntimeException("Cette quittance rectificative ne possède pas de quittance initiale associée"));

            quittanceInitiale.setStatut(StatutDeclaration.RECTIFIE);
            QuittanceMensuelleImpot quittanceInitialeUpdated = quittanceMensuelleImpotRepository.save(quittanceInitiale);
            quittanceMensuelleImpot.setParentQuittance(quittanceInitialeUpdated);
        }

        StatutDeclaration statutDeclaration = (quittanceMensuelleImpot.getNumeroQuittance() != null && quittanceMensuelleImpot.getDatePaiement() != null) ?
                StatutDeclaration.VALIDE : StatutDeclaration.BROUILLON;
        quittanceMensuelleImpot.setStatut(statutDeclaration);

    }

    private void validateCreationForm(FicheClient ficheClient, Integer annee, Integer mois, TypeDeclaration typeDeclaration) {

        if (ficheClient.getDateCreation().getYear() > annee) throw new RuntimeException("Annee non valide");
        if (mois > 12 || mois < 1) {
            throw new RuntimeException("mois non valide");
        }
        if (mois > 1) {
            List<QuittanceMensuelleImpot> previousMounthQuittances = quittanceMensuelleImpotRepository.findByAnneeAndMoisAndFicheClientId(annee,
                    mois - 1, ficheClient.getId());
            if (previousMounthQuittances.isEmpty()) throw new RuntimeException("Previous mounth quittance not found");
            Optional<QuittanceMensuelleImpot> previousMounthQuittanceOptional = previousMounthQuittances.stream()
                    .filter(previousQuittance -> previousQuittance.getStatut().equals(StatutDeclaration.BROUILLON))
                    .findFirst();
            if (previousMounthQuittanceOptional.isPresent()) {
                throw new RuntimeException("Previous mounth quittance is not yet validated. Validate it to continue!");
            }
        }
        List<QuittanceMensuelleImpot> quittances = quittanceMensuelleImpotRepository.findByAnneeAndMoisAndFicheClientId(annee,
                mois, ficheClient.getId());
        if (quittances.isEmpty() && typeDeclaration.equals(TypeDeclaration.DECLARATION_RECTIFICATIVE)) {
            throw new RuntimeException("Quittance initiale not exists. Create quiitance initiale before can create quittantance rectifcative!!");
        }
        if (quittances.size() == 1 && typeDeclaration.equals(TypeDeclaration.DECLARATION_INITIALE)) {
            throw new RuntimeException("Quittance initiale already exists for this mouth. Edit it or create one for another mounth!");
        }
        if (quittances.size() == 1 && typeDeclaration.equals(TypeDeclaration.DECLARATION_RECTIFICATIVE) &&
                quittances.get(0).getStatut().equals(StatutDeclaration.BROUILLON)) {
            throw new RuntimeException("Quittance initiale not validated yet. Validate it to can create quittance rectificative!");
        }
        if (quittances.size() == 2 && typeDeclaration.equals(TypeDeclaration.DECLARATION_RECTIFICATIVE)) {
            throw new RuntimeException("Quittance rectificative already exists for this mouth. Edit it or create one for another mounth!");
        }
    }

    public QuittanceMensuelleImpotDTO init(Long ficheClientId, Integer annee, Integer mois, TypeDeclaration typeDeclaration) {

        FicheClient ficheClient = ficheClientRepository.findById(ficheClientId).orElseThrow(() -> new RuntimeException(String.format("FicheClient not found with id %s", ficheClientId)));
        validateCreationForm(ficheClient, annee, mois, typeDeclaration);
        return getEmptyQuittanceMensuel(ficheClient, annee, mois);
    }

    public QuittanceMensuelleImpotDTO getEmptyQuittanceMensuel(FicheClient ficheClient, Integer annee, Integer mois) {

        Optional<QuittanceMensuelleImpot> quittanceInitialeOptional = quittanceMensuelleImpotRepository.findByAnneeAndMoisAndFicheClientIdAndTypeDeclaration(annee,
                mois, ficheClient.getId(), TypeDeclaration.DECLARATION_INITIALE);

        //Si existe déjà une quittance mensuelle initiale avec un numéro (donc validée) alors créer une quittance type corrective
        //Sinon si la quittance initiale trouvée de possède pas de numéro alors l'éditer
        //Enfin si pas de quittance initiale trouvée alors créé une nouvelle
        if (quittanceInitialeOptional.isPresent()) {

            return StringUtils.isNotBlank(quittanceInitialeOptional.get().getNumeroQuittance()) ?
                    getQuittanceRectificativeForQuittanceInitiale(quittanceInitialeOptional.get()) :
                    quittanceMensuelleImpotMapper.toDto(quittanceInitialeOptional.get());

        } else {

            QuittanceMensuelleImpot quittanceInitiale = new QuittanceMensuelleImpot(
                    ficheClient,
                    annee,
                    mois,
                    TypeDeclaration.DECLARATION_INITIALE);
            initQuittanceImpotMensuelDetails(quittanceInitiale);
            return quittanceMensuelleImpotMapper.toDto(quittanceInitiale);
        }
    }

    /**
     * S'il existe déjà une quittance rectificative associée à une quittance initiale (même mois, année, client)
     * alors retournée celle ci, sinon créée une nouvelle quittance rectificative avec les même données que la quittance initiale
     * tout en réinitialisant numéro de la quittance et la date de paiement et en modifiant le type par rectificative
     *
     * @param quittanceInitiale
     * @return
     */
    private QuittanceMensuelleImpotDTO getQuittanceRectificativeForQuittanceInitiale(QuittanceMensuelleImpot quittanceInitiale) {

        Optional<QuittanceMensuelleImpot> quittanceRectificativeOptional = quittanceMensuelleImpotRepository.findByAnneeAndMoisAndFicheClientIdAndTypeDeclaration(
                quittanceInitiale.getAnnee(),
                quittanceInitiale.getMois(),
                quittanceInitiale.getFicheClient().getId(),
                TypeDeclaration.DECLARATION_RECTIFICATIVE);

        if (quittanceRectificativeOptional.isPresent() && StringUtils.isNotBlank(quittanceRectificativeOptional.get().getNumeroQuittance())) {

            return quittanceMensuelleImpotMapper.toDto(quittanceRectificativeOptional.get());
        } else {

            QuittanceMensuelleImpotDTO quittanceRectificativeDTO = quittanceMensuelleImpotMapper.toDto(quittanceInitiale);

            quittanceRectificativeDTO.setTypeDeclaration(TypeDeclaration.DECLARATION_RECTIFICATIVE);
            quittanceRectificativeDTO.setId(null);
            quittanceRectificativeDTO.setDatePaiement(null);
            quittanceRectificativeDTO.setNumeroQuittance(null);
            quittanceRectificativeDTO.getQuittanceMensuelleImpotDetails().forEach(quittanceMensuelleImpotDetail -> {
                quittanceMensuelleImpotDetail.setId(null);
                quittanceMensuelleImpotDetail.setQuittanceMensuelleImpotId(null);
                quittanceMensuelleImpotDetail.getChildQuittanceMensuelleImpotDetails().forEach(child -> {
                    child.setId(null);
                    child.setQuittanceMensuelleImpotId(null);
                    child.setParentQuittanceMensuelleImpotDetailId(null);
                    child.getQuittanceMensuelleImpotSousDetails().forEach(sousDetail -> {
                        sousDetail.setId(null);
                        sousDetail.setQuittanceMensuelleImpotDetailId(null);
                    });
                });
                quittanceMensuelleImpotDetail.getQuittanceMensuelleImpotSousDetails().forEach(sousDetail -> {
                    sousDetail.setId(null);
                    sousDetail.setQuittanceMensuelleImpotDetailId(null);
                });
            });
            return quittanceRectificativeDTO;
        }
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
        quittanceMensuelleImpotDetail.setCode(impotMensuel.getCode());
        quittanceMensuelleImpotDetail.setLibelle(impotMensuel.getLibelle());
        quittanceMensuelleImpotDetail.setChild(impotMensuel.getChild());
        quittanceMensuelleImpotDetail.setAppliquerReportMontant(impotMensuel.getAppliquerReportMontant());
        if (impotMensuel.getAppliquerReportMontant()) {
            //S'il s'agit du mois de Janvier le report est 0 sinon récupérer le report du mois précédent
            if (quittanceMensuelleImpot.getMois() > 1) {
                QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetailPrecedente = quittanceMensuelleImpotDetailService.findByFicheClientIdAndQuittanceStatutAndAnneeAndMoisAndCode(
                        quittanceMensuelleImpot.getFicheClient().getId(),
                        Arrays.asList(StatutDeclaration.VALIDE, StatutDeclaration.BROUILLON),
                        quittanceMensuelleImpot.getAnnee(),
                        quittanceMensuelleImpot.getMois() - 1,
//                        impotMensuel.getCode()).orElseThrow(() -> new RuntimeException("Il n'existe pas de quittance pour le mois précédent"));
                        impotMensuel.getCode()).orElseThrow(() -> new RuntimeException("Il n'existe pas de quittance pour le mois précédent"));
                quittanceMensuelleImpotDetail.setMontantReport(quittanceMensuelleImpotDetailPrecedente.getMontantTotal().compareTo(BigDecimal.ZERO) == -1 ?
                        quittanceMensuelleImpotDetailPrecedente.getMontantTotal() :
                        BigDecimal.ZERO);
            } else {
                quittanceMensuelleImpotDetail.setMontantReport(BigDecimal.ZERO);
            }

        }
        quittanceMensuelleImpotDetail.setCoefficientMontant(impotMensuel.getCoefficientMontant());
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
        updateStatutQuittance(quittanceMensuelleImpot);
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
     * Delete the quittanceMensuelleImpot by id. if quittance to delete is rectificative, set associated initale quittanced statut to validated
     *
     * @param id the id of the entity
     */
    @Transactional
    public void delete(Long id) {
        log.debug("Request to delete QuittanceMensuelleImpot : {}", id);
        QuittanceMensuelleImpot quittanceMensuelleImpot = quittanceMensuelleImpotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("The quittance to delete with id %s doesn't exist", id)));
        if (quittanceMensuelleImpot.getNumeroQuittance() != null && quittanceMensuelleImpot.getDatePaiement() != null)
            throw new RuntimeException(String.format("The quittance with id %s can't be delete because it was validated", id));
        if (quittanceMensuelleImpot.getTypeDeclaration().equals(TypeDeclaration.DECLARATION_RECTIFICATIVE)) {
            QuittanceMensuelleImpot quittanceInitiale = quittanceMensuelleImpotRepository.findById(quittanceMensuelleImpot.getParentQuittance().getId())
                    .orElseThrow(() -> new RuntimeException(String.format("The quittance RECTIFICATIVE to delete with id %s doesn't have associated quittance initiale", id)));
            quittanceInitiale.setStatut(StatutDeclaration.VALIDE);
            quittanceMensuelleImpotRepository.save(quittanceInitiale);
        }
        quittanceMensuelleImpotRepository.deleteById(id);
    }
}
