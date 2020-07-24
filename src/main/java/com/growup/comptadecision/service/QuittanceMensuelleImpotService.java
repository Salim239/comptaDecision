package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.FicheClient;
import com.growup.comptadecision.domain.ImpotMensuel;
import com.growup.comptadecision.domain.QuittanceMensuelleImpot;
import com.growup.comptadecision.domain.QuittanceMensuelleImpotDetail;
import com.growup.comptadecision.domain.enumeration.CodeAlert;
import com.growup.comptadecision.domain.enumeration.StatutDeclaration;
import com.growup.comptadecision.domain.enumeration.TypeAlert;
import com.growup.comptadecision.domain.enumeration.TypeDeclaration;
import com.growup.comptadecision.repository.FicheClientRepository;
import com.growup.comptadecision.repository.ImpotMensuelRepository;
import com.growup.comptadecision.repository.QuittanceMensuelleImpotRepository;
import com.growup.comptadecision.security.SecurityUtils;
import com.growup.comptadecision.service.dto.BusinessAlertDTO;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotDTO;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotDetailDTO;
import com.growup.comptadecision.service.mapper.QuittanceMensuelleImpotMapper;
import com.growup.comptadecision.service.mapper.QuittanceMensuelleImpotSousDetailMapper;
import com.growup.comptadecision.web.rest.errors.BusinessErrorException;
import com.growup.comptadecision.web.rest.errors.ErrorConstants;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.util.*;
import java.util.stream.Collectors;

import static com.growup.comptadecision.domain.enumeration.CodeAlert.*;

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

    private void updateStatutQuittance(QuittanceMensuelleImpot quittanceMensuelleImpot) {

        if (quittanceMensuelleImpot.getTypeDeclaration().equals(TypeDeclaration.DECLARATION_RECTIFICATIVE)) {

            QuittanceMensuelleImpot quittanceInitiale = quittanceMensuelleImpotRepository.findByAnneeAndMoisAndFicheClientIdAndTypeDeclaration(
                    quittanceMensuelleImpot.getAnnee(),
                    quittanceMensuelleImpot.getMois(),
                    quittanceMensuelleImpot.getFicheClient().getId(),
                    TypeDeclaration.DECLARATION_INITIALE).orElseThrow(() -> new BusinessErrorException(ErrorConstants.ERR_QUITTANCE_RECTIFICATIVE_SANS_QUITTANCE_INITIALE));

            quittanceInitiale.setStatut(StatutDeclaration.RECTIFIE);
            QuittanceMensuelleImpot quittanceInitialeUpdated = quittanceMensuelleImpotRepository.save(quittanceInitiale);
            quittanceMensuelleImpot.setParentQuittance(quittanceInitialeUpdated);
        }

        StatutDeclaration statutDeclaration = (quittanceMensuelleImpot.getNumeroQuittance() != null && quittanceMensuelleImpot.getDatePaiement() != null) ?
                StatutDeclaration.VALIDE : StatutDeclaration.BROUILLON;
        quittanceMensuelleImpot.setStatut(statutDeclaration);

    }

    private List<BusinessAlertDTO> validateCreationForm(FicheClient ficheClient, Integer annee, Integer mois, TypeDeclaration typeDeclaration) {

        List<BusinessAlertDTO> businessAlerts = new ArrayList<>();

        if (ficheClient.getDateCreation().getYear() > annee) throw new BusinessErrorException(ErrorConstants.ERR_ANNEE_NON_VALIDE);
        if (mois > 12 || mois < 1) {
            throw new BusinessErrorException(ErrorConstants.ERR_MOIS_NON_VALIDE);
        }
        if (mois > 1) {
            List<QuittanceMensuelleImpot> previousMounthQuittances = quittanceMensuelleImpotRepository.findByAnneeAndMoisAndFicheClientId(annee,
                    mois - 1, ficheClient.getId());
            if (previousMounthQuittances.isEmpty()) {
                BusinessAlertDTO businessAlert = new BusinessAlertDTO(TypeAlert.warning, WARNING_QUITTANCE_PRECEDENTE_INEXISTANTE);
                businessAlert.addParam("mois", new DateFormatSymbols().getMonths()[mois - 2] + "/" + annee);
                businessAlerts.add(businessAlert);
            }
            Optional<QuittanceMensuelleImpot> previousMounthQuittanceOptional = previousMounthQuittances.stream()
                    .filter(previousQuittance -> previousQuittance.getStatut().equals(StatutDeclaration.BROUILLON))
                    .findFirst();
            if (previousMounthQuittanceOptional.isPresent()) {
                BusinessAlertDTO businessAlert = new BusinessAlertDTO(TypeAlert.warning, WARNING_QUITTANCE_PRECEDENTE_NON_VALIDE);
                businessAlert.addParam("mois", new DateFormatSymbols().getMonths()[mois - 2] + "/" + annee);
                businessAlerts.add(businessAlert);
            }
        }
        List<QuittanceMensuelleImpot> quittances = quittanceMensuelleImpotRepository.findByAnneeAndMoisAndFicheClientId(annee,
                mois, ficheClient.getId());
        if (quittances.isEmpty() && typeDeclaration.equals(TypeDeclaration.DECLARATION_RECTIFICATIVE)) {
            throw new BusinessErrorException(ErrorConstants.ERR_QUITTANCE_INITIALE_INEXISTANTE);
        }
        if (quittances.size() == 1 && typeDeclaration.equals(TypeDeclaration.DECLARATION_INITIALE)) {
            throw new BusinessErrorException(ErrorConstants.ERR_QUITTANCE_INITIALE_INEXISTANTE);
        }
        if (quittances.size() == 1 && typeDeclaration.equals(TypeDeclaration.DECLARATION_RECTIFICATIVE) &&
                quittances.get(0).getStatut().equals(StatutDeclaration.BROUILLON)) {

            BusinessAlertDTO businessAlert = new BusinessAlertDTO(TypeAlert.warning, WARNING_QUITTANCE_INITIALE_NON_VALIDE);
            businessAlert.addParam("mois", new DateFormatSymbols().getMonths()[mois -1] + "/" + annee);
            businessAlerts.add(businessAlert);
        }
        if (quittances.size() == 2 && typeDeclaration.equals(TypeDeclaration.DECLARATION_RECTIFICATIVE)) {
            throw new BusinessErrorException(ErrorConstants.ERR_QUITTANCE_RECTIFICATIVE_INEXISTANTE);
        }

        return businessAlerts;
    }

    public QuittanceMensuelleImpotDTO init(Long ficheClientId, Integer annee, Integer mois, TypeDeclaration typeDeclaration) {

        FicheClient ficheClient = ficheClientRepository.findById(ficheClientId).orElseThrow(() -> new BusinessErrorException(String.format("Il n'existe pas de fiche client avec l'id %s", ficheClientId)));
        List<BusinessAlertDTO> businessAlerts = validateCreationForm(ficheClient, annee, mois, typeDeclaration);
        QuittanceMensuelleImpotDTO quittanceMensuelleImpotDTO = getEmptyQuittanceMensuel(ficheClient, annee, mois);
        quittanceMensuelleImpotDTO.addBusinessAlerts(businessAlerts);
        return quittanceMensuelleImpotDTO;
    }

    public QuittanceMensuelleImpotDTO getEmptyQuittanceMensuel(FicheClient ficheClient, Integer annee, Integer mois) {

        QuittanceMensuelleImpotDTO quittanceMensuelleImpotDTO = new QuittanceMensuelleImpotDTO();
        Optional<QuittanceMensuelleImpot> quittanceInitialeOptional = quittanceMensuelleImpotRepository.findByAnneeAndMoisAndFicheClientIdAndTypeDeclaration(annee,
                mois, ficheClient.getId(), TypeDeclaration.DECLARATION_INITIALE);

        //Si existe déjà une quittance mensuelle initiale avec un numéro (donc validée) alors créer une quittance type corrective
        //Sinon si la quittance initiale trouvée de possède pas de numéro alors l'éditer
        //Enfin si pas de quittance initiale trouvée alors créé une nouvelle
        if (quittanceInitialeOptional.isPresent()) {

            quittanceMensuelleImpotDTO = StringUtils.isNotBlank(quittanceInitialeOptional.get().getNumeroQuittance()) ?
                    getQuittanceRectificativeForQuittanceInitiale(quittanceInitialeOptional.get()) :
                    quittanceMensuelleImpotMapper.toDto(quittanceInitialeOptional.get());

        } else {

            QuittanceMensuelleImpot quittanceInitiale = new QuittanceMensuelleImpot(
                    ficheClient,
                    annee,
                    mois,
                    TypeDeclaration.DECLARATION_INITIALE);
            initQuittanceImpotMensuelDetails(quittanceInitiale);
            quittanceMensuelleImpotDTO = quittanceMensuelleImpotMapper.toDto(quittanceInitiale);

        }
        initMontantReportCalc(quittanceMensuelleImpotDTO);
        return quittanceMensuelleImpotDTO;
    }

    /**
     * Init montant report calculé et init montant report si c'est une déclaration initiale et que c'est une création
     * @param quittanceMensuelleImpotDTO
     */
    private void initMontantReportCalc(QuittanceMensuelleImpotDTO quittanceMensuelleImpotDTO) {
        TypeDeclaration typeDeclarationQuittanceToCreate = quittanceMensuelleImpotDTO.getTypeDeclaration();
        quittanceMensuelleImpotDTO.getQuittanceMensuelleImpotDetails().stream()
            .filter(QuittanceMensuelleImpotDetailDTO::getAppliquerReportMontant)
            .forEach(quittanceDetail -> {
                BigDecimal montantReport = BigDecimal.ZERO;
                try {
                 montantReport = calculerMontantReport(quittanceMensuelleImpotDTO.getFicheClientId(),
                     quittanceMensuelleImpotDTO.getAnnee(), quittanceMensuelleImpotDTO.getMois(), quittanceDetail.getCode());
                } catch (BusinessErrorException be) {
                    BusinessAlertDTO businessAlert = new BusinessAlertDTO(TypeAlert.warning, CodeAlert.WARNING_QUITTANCE_PRECEDENTE_INEXISTANTE);
                    String moisSansQuittance = new DateFormatSymbols().getMonths()[quittanceMensuelleImpotDTO.getMois() == 1 ? 11 : quittanceMensuelleImpotDTO.getMois() - 2];
                    Integer anneeMoisSansQuittance = quittanceMensuelleImpotDTO.getMois() == 1 ? quittanceMensuelleImpotDTO.getAnnee() - 1 : quittanceMensuelleImpotDTO.getAnnee();
                    businessAlert.addParam("mois", moisSansQuittance + "/" + anneeMoisSansQuittance);
                    quittanceMensuelleImpotDTO.addBusinessAlert(businessAlert);
                }
                quittanceDetail.setMontantReportCalc(montantReport);
                if (quittanceMensuelleImpotDTO.getId() == null &&
                    typeDeclarationQuittanceToCreate == TypeDeclaration.DECLARATION_INITIALE) {
                    quittanceDetail.setMontantReport(quittanceDetail.getMontantReportCalc());
                }
            });
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

    private BigDecimal calculerMontantReport (Long ficheClientId, Integer annee, Integer mois, String impotMensuelCode) throws BusinessErrorException {
        Integer anneeReport, moisReport;
        if (mois == 1) {
            anneeReport = annee - 1;
            moisReport = 12;
        } else {
            anneeReport = annee;
            moisReport = mois - 1;
        }
        //S'il s'agit du mois de Janvier le report est 0 sinon récupérer le report du mois précédent
            QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetailPrecedente = quittanceMensuelleImpotDetailService.findByFicheClientIdAndQuittanceStatutAndAnneeAndMoisAndCode(
                ficheClientId,
                Arrays.asList(StatutDeclaration.VALIDE, StatutDeclaration.BROUILLON),
                anneeReport,
                moisReport,
                impotMensuelCode).orElseThrow(() -> new BusinessErrorException(ErrorConstants.ERR_QUITTANCE_PRECEDENTE_INEXISTANTE));
            return quittanceMensuelleImpotDetailPrecedente.getMontantTotal().compareTo(BigDecimal.ZERO) < 0 ?
                quittanceMensuelleImpotDetailPrecedente.getMontantTotal().multiply(new BigDecimal("-1")) :
                BigDecimal.ZERO;

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
        quittanceMensuelleImpotDetail.setMontantReport(BigDecimal.ZERO);
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
        Optional<QuittanceMensuelleImpotDTO> quittanceMensuelleImpotDTOOptional = quittanceMensuelleImpotRepository.findById(id)
            .map(quittanceMensuelleImpotMapper::toDto);
        if (quittanceMensuelleImpotDTOOptional.isPresent()) {
            QuittanceMensuelleImpotDTO quittanceMensuelleImpotDTO = quittanceMensuelleImpotDTOOptional.get();
            initMontantReportCalc(quittanceMensuelleImpotDTO);
            return Optional.of(quittanceMensuelleImpotDTO);
        } else {
            return quittanceMensuelleImpotDTOOptional;
        }
    }

    /**
     * Delete the quittanceMensuelleImpot by id. if quittance to delete is rectificative, set associated initale quittanced statut to validated
     *
     * @param id the id of the entity
     */
    @Transactional
    public void delete(Long id) {
        log.debug("Request to delete QuittanceMensuelleImpot : {}", id);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        QuittanceMensuelleImpot quittanceMensuelleImpot = quittanceMensuelleImpotRepository.findById(id)
                .orElseThrow(() -> new BusinessErrorException(ErrorConstants.ERR_QUITTANCE_A_SUPPRIMER_INEXISTANTE, params));
        if (quittanceMensuelleImpot.getNumeroQuittance() != null && quittanceMensuelleImpot.getDatePaiement() != null)
            throw new BusinessErrorException(String.format(ErrorConstants.ERR_QUITTANCEA_SUPPRIMER_VALIDEE, params));
        if (quittanceMensuelleImpot.getTypeDeclaration().equals(TypeDeclaration.DECLARATION_RECTIFICATIVE)) {
            QuittanceMensuelleImpot quittanceInitiale = quittanceMensuelleImpotRepository.findById(quittanceMensuelleImpot.getParentQuittance().getId())
                    .orElseThrow(() -> new BusinessErrorException(ErrorConstants.ERR_QUITTANCE_RECTIFICATIVE_A_SUPPRIMER_SANS_QUITTANCE_INITIALE, params));
            quittanceInitiale.setStatut(StatutDeclaration.VALIDE);
            quittanceMensuelleImpotRepository.save(quittanceInitiale);
        }
        quittanceMensuelleImpotRepository.deleteById(id);
    }

    public void print(Long quittanceMensuelleId, HttpServletResponse response) throws DocumentException, IOException {

        PdfReader reader = new PdfReader("templates/pdf/model_quittance-mensuelle-impot.pdf");
        final OutputStream outStream = response.getOutputStream();
        PdfStamper stamper = new PdfStamper(reader, outStream);
        AcroFields fields = stamper.getAcroFields();
        fields.setField("matriculeFiscale", "MON MASTRICULE FISCALE");
        fields.setField("TVA", "true");
        fields.setField("TIMBRE_FISCALE", "MON TIMBRE FISCALE");
//        fields.setField("abbr", "CA");
//        fields.setField("capital", "Sacramento");
//        fields.setField("city", "Los Angeles");
//        fields.setField("population", "36,961,664");
//        fields.setField("surface", "163,707");
//        fields.setField("timezone1", "PT (UTC-8)");
//        fields.setField("timezone2", "-");
//        fields.setField("dst", "YES");
        stamper.setFormFlattening(true);
        stamper.close();
        reader.close();
        response.getOutputStream().close();

    }
}
