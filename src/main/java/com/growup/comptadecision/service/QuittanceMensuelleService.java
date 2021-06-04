package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.FicheClient;
import com.growup.comptadecision.domain.ImpotMensuel;
import com.growup.comptadecision.domain.QuittanceMensuelle;
import com.growup.comptadecision.domain.QuittanceMensuelleLigne;
import com.growup.comptadecision.domain.enumeration.CodeAlert;
import com.growup.comptadecision.domain.enumeration.StatutDeclaration;
import com.growup.comptadecision.domain.enumeration.TypeAlert;
import com.growup.comptadecision.domain.enumeration.TypeDeclaration;
import com.growup.comptadecision.repository.FicheClientRepository;
import com.growup.comptadecision.repository.ImpotMensuelRepository;
import com.growup.comptadecision.repository.QuittanceMensuelleRepository;
import com.growup.comptadecision.security.SecurityUtils;
import com.growup.comptadecision.service.dto.BusinessAlertDTO;
import com.growup.comptadecision.service.dto.QuittanceMensuelleDTO;
import com.growup.comptadecision.service.dto.QuittanceMensuelleLigneDTO;
import com.growup.comptadecision.service.mapper.QuittanceMensuelleMapper;
import com.growup.comptadecision.service.mapper.QuittanceMensuelleSousLigneMapper;
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
 * Service Implementation for managing QuittanceMensuelle.
 */
@Service
@Transactional
public class QuittanceMensuelleService {

    private final Logger log = LoggerFactory.getLogger(QuittanceMensuelleService.class);

    private final QuittanceMensuelleRepository quittanceMensuelleRepository;

    private final ImpotMensuelRepository impotMensuelRepository;

    private final QuittanceMensuelleMapper quittanceMensuelleMapper;

    private final FicheClientRepository ficheClientRepository;

    private final QuittanceMensuelleLigneService quittanceMensuelleLigneService;

    private final QuittanceMensuelleSousLigneMapper quittanceMensuelleSousLigneMapper;

    public QuittanceMensuelleService(QuittanceMensuelleRepository quittanceMensuelleRepository,
                                          QuittanceMensuelleMapper quittanceMensuelleMapper,
                                          QuittanceMensuelleSousLigneMapper quittanceMensuelleSousLigneMapper,
                                          FicheClientRepository ficheClientRepository,
                                          ImpotMensuelRepository impotMensuelRepository, QuittanceMensuelleLigneService quittanceMensuelleLigneService) {
        this.quittanceMensuelleRepository = quittanceMensuelleRepository;
        this.quittanceMensuelleMapper = quittanceMensuelleMapper;
        this.quittanceMensuelleSousLigneMapper = quittanceMensuelleSousLigneMapper;
        this.ficheClientRepository = ficheClientRepository;
        this.impotMensuelRepository = impotMensuelRepository;
        this.quittanceMensuelleLigneService = quittanceMensuelleLigneService;
    }

    private void updateStatutQuittance(QuittanceMensuelle quittanceMensuelle) {

        if (quittanceMensuelle.getTypeDeclaration().equals(TypeDeclaration.DECLARATION_RECTIFICATIVE)) {

            QuittanceMensuelle quittanceInitiale = quittanceMensuelleRepository.findByAnneeAndMoisAndFicheClientIdAndTypeDeclaration(
                    quittanceMensuelle.getAnnee(),
                    quittanceMensuelle.getMois(),
                    quittanceMensuelle.getFicheClient().getId(),
                    TypeDeclaration.DECLARATION_INITIALE).orElseThrow(() -> new BusinessErrorException(ErrorConstants.ERR_QUITTANCE_RECTIFICATIVE_SANS_QUITTANCE_INITIALE));

            quittanceInitiale.setStatut(StatutDeclaration.RECTIFIE);
            QuittanceMensuelle quittanceInitialeUpdated = quittanceMensuelleRepository.save(quittanceInitiale);
            quittanceMensuelle.setParentQuittance(quittanceInitialeUpdated);
        }

        StatutDeclaration statutDeclaration = (quittanceMensuelle.getNumeroQuittance() != null && quittanceMensuelle.getDatePaiement() != null) ?
                StatutDeclaration.VALIDE : StatutDeclaration.BROUILLON;
        quittanceMensuelle.setStatut(statutDeclaration);

    }

    private List<BusinessAlertDTO> validateCreationForm(FicheClient ficheClient, Integer annee, Integer mois, TypeDeclaration typeDeclaration) {

        List<BusinessAlertDTO> businessAlerts = new ArrayList<>();

        if (ficheClient.getDateCreation().getYear() > annee) throw new BusinessErrorException(ErrorConstants.ERR_ANNEE_NON_VALIDE);
        if (mois > 12 || mois < 1) {
            throw new BusinessErrorException(ErrorConstants.ERR_MOIS_NON_VALIDE);
        }
        if (mois > 1) {
            List<QuittanceMensuelle> previousMounthQuittances = quittanceMensuelleRepository.findByAnneeAndMoisAndFicheClientId(annee,
                    mois - 1, ficheClient.getId());
            if (previousMounthQuittances.isEmpty()) {
                BusinessAlertDTO businessAlert = new BusinessAlertDTO(TypeAlert.warning, WARNING_QUITTANCE_PRECEDENTE_INEXISTANTE);
                businessAlert.addParam("mois", new DateFormatSymbols().getMonths()[mois - 2] + "/" + annee);
                businessAlerts.add(businessAlert);
            }
            Optional<QuittanceMensuelle> previousMounthQuittanceOptional = previousMounthQuittances.stream()
                    .filter(previousQuittance -> previousQuittance.getStatut().equals(StatutDeclaration.BROUILLON))
                    .findFirst();
            if (previousMounthQuittanceOptional.isPresent()) {
                BusinessAlertDTO businessAlert = new BusinessAlertDTO(TypeAlert.warning, WARNING_QUITTANCE_PRECEDENTE_NON_VALIDE);
                businessAlert.addParam("mois", new DateFormatSymbols().getMonths()[mois - 2] + "/" + annee);
                businessAlerts.add(businessAlert);
            }
        }
        List<QuittanceMensuelle> quittances = quittanceMensuelleRepository.findByAnneeAndMoisAndFicheClientId(annee,
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

    public QuittanceMensuelleDTO init(Long ficheClientId, Integer annee, Integer mois, TypeDeclaration typeDeclaration) {

        FicheClient ficheClient = ficheClientRepository.findById(ficheClientId).orElseThrow(() -> new BusinessErrorException(String.format("Il n'existe pas de fiche client avec l'id %s", ficheClientId)));
        List<BusinessAlertDTO> businessAlerts = validateCreationForm(ficheClient, annee, mois, typeDeclaration);
        QuittanceMensuelleDTO quittanceMensuelleDTO = getEmptyQuittanceMensuel(ficheClient, annee, mois);
        quittanceMensuelleDTO.addBusinessAlerts(businessAlerts);
        return quittanceMensuelleDTO;
    }

    public QuittanceMensuelleDTO getEmptyQuittanceMensuel(FicheClient ficheClient, Integer annee, Integer mois) {

        QuittanceMensuelleDTO quittanceMensuelleDTO = new QuittanceMensuelleDTO();
        Optional<QuittanceMensuelle> quittanceInitialeOptional = quittanceMensuelleRepository.findByAnneeAndMoisAndFicheClientIdAndTypeDeclaration(annee,
                mois, ficheClient.getId(), TypeDeclaration.DECLARATION_INITIALE);

        //Si existe déjà une quittance mensuelle initiale avec un numéro (donc validée) alors créer une quittance type corrective
        //Sinon si la quittance initiale trouvée de possède pas de numéro alors l'éditer
        //Enfin si pas de quittance initiale trouvée alors créé une nouvelle
        if (quittanceInitialeOptional.isPresent()) {

            quittanceMensuelleDTO = StringUtils.isNotBlank(quittanceInitialeOptional.get().getNumeroQuittance()) ?
                    getQuittanceRectificativeForQuittanceInitiale(quittanceInitialeOptional.get()) :
                    quittanceMensuelleMapper.toDto(quittanceInitialeOptional.get());

        } else {

            QuittanceMensuelle quittanceInitiale = new QuittanceMensuelle(
                    ficheClient,
                    annee,
                    mois,
                    TypeDeclaration.DECLARATION_INITIALE);
            initQuittanceImpotMensuelLignes(quittanceInitiale);
            quittanceMensuelleDTO = quittanceMensuelleMapper.toDto(quittanceInitiale);

        }
        initMontantReportCalc(quittanceMensuelleDTO);
        return quittanceMensuelleDTO;
    }

    /**
     * Init montant report calculé et init montant report si c'est une déclaration initiale et que c'est une création
     * @param quittanceMensuelleDTO
     */
    private void initMontantReportCalc(QuittanceMensuelleDTO quittanceMensuelleDTO) {
        TypeDeclaration typeDeclarationQuittanceToCreate = quittanceMensuelleDTO.getTypeDeclaration();
        quittanceMensuelleDTO.getQuittanceMensuelleLignes().stream()
            .filter(QuittanceMensuelleLigneDTO::getAppliquerReportMontant)
            .forEach(quittanceLigne -> {
                BigDecimal montantReport = BigDecimal.ZERO;
                try {
                 montantReport = calculerMontantReport(quittanceMensuelleDTO.getFicheClientId(),
                     quittanceMensuelleDTO.getAnnee(), quittanceMensuelleDTO.getMois(), quittanceLigne.getCode());
                } catch (BusinessErrorException be) {
                    BusinessAlertDTO businessAlert = new BusinessAlertDTO(TypeAlert.warning, CodeAlert.WARNING_QUITTANCE_PRECEDENTE_INEXISTANTE);
                    String moisSansQuittance = new DateFormatSymbols().getMonths()[quittanceMensuelleDTO.getMois() == 1 ? 11 : quittanceMensuelleDTO.getMois() - 2];
                    Integer anneeMoisSansQuittance = quittanceMensuelleDTO.getMois() == 1 ? quittanceMensuelleDTO.getAnnee() - 1 : quittanceMensuelleDTO.getAnnee();
                    businessAlert.addParam("mois", moisSansQuittance + "/" + anneeMoisSansQuittance);
                    quittanceMensuelleDTO.addBusinessAlert(businessAlert);
                }
                quittanceLigne.setMontantReportCalc(montantReport);
                if (quittanceMensuelleDTO.getId() == null &&
                    typeDeclarationQuittanceToCreate == TypeDeclaration.DECLARATION_INITIALE) {
                    quittanceLigne.setMontantReport(quittanceLigne.getMontantReportCalc());
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
    private QuittanceMensuelleDTO getQuittanceRectificativeForQuittanceInitiale(QuittanceMensuelle quittanceInitiale) {

        Optional<QuittanceMensuelle> quittanceRectificativeOptional = quittanceMensuelleRepository.findByAnneeAndMoisAndFicheClientIdAndTypeDeclaration(
                quittanceInitiale.getAnnee(),
                quittanceInitiale.getMois(),
                quittanceInitiale.getFicheClient().getId(),
                TypeDeclaration.DECLARATION_RECTIFICATIVE);

        if (quittanceRectificativeOptional.isPresent() && StringUtils.isNotBlank(quittanceRectificativeOptional.get().getNumeroQuittance())) {

            return quittanceMensuelleMapper.toDto(quittanceRectificativeOptional.get());
        } else {

            QuittanceMensuelleDTO quittanceRectificativeDTO = quittanceMensuelleMapper.toDto(quittanceInitiale);

            quittanceRectificativeDTO.setTypeDeclaration(TypeDeclaration.DECLARATION_RECTIFICATIVE);
            quittanceRectificativeDTO.setId(null);
            quittanceRectificativeDTO.setDatePaiement(null);
            quittanceRectificativeDTO.setNumeroQuittance(null);
            quittanceRectificativeDTO.getQuittanceMensuelleLignes().forEach(quittanceMensuelleLigne -> {
                quittanceMensuelleLigne.setId(null);
                quittanceMensuelleLigne.setQuittanceMensuelleId(null);
                quittanceMensuelleLigne.getChildQuittanceMensuelleLignes().forEach(child -> {
                    child.setId(null);
                    child.setQuittanceMensuelleId(null);
                    child.setParentQuittanceMensuelleLigneId(null);
                    child.getQuittanceMensuelleSousLignes().forEach(sousLigne -> {
                        sousLigne.setId(null);
                        sousLigne.setQuittanceMensuelleLigneId(null);
                    });
                });
                quittanceMensuelleLigne.getQuittanceMensuelleSousLignes().forEach(sousLigne -> {
                    sousLigne.setId(null);
                    sousLigne.setQuittanceMensuelleLigneId(null);
                });
            });
            return quittanceRectificativeDTO;
        }
    }

    /**
     * Init quittance men,suel details from impot mensuel applicable au client
     * @param quittanceMensuelle quittanceMensuel object
     */
    private void initQuittanceImpotMensuelLignes(QuittanceMensuelle quittanceMensuelle) {

        List<ImpotMensuel> impotMensuels = impotMensuelRepository.findImpotMensuelApplicableByFicheClientId(quittanceMensuelle.getFicheClient().getId());
        List<QuittanceMensuelleLigne> quittanceMensuelleLignes = impotMensuels.stream()
                .map(impotMensuel -> {
                    QuittanceMensuelleLigne quittanceMensuelleLigne = getQuittanceMensuelImpotLigne(quittanceMensuelle, impotMensuel);
                    List<QuittanceMensuelleLigne> childQuittanceMensuelleLignes = new ArrayList<>();
                    for (ImpotMensuel childImpotMensuel : impotMensuel.getChildImpotMensuels()) {
                        QuittanceMensuelleLigne childQuittanceMensuelleLigne = getQuittanceMensuelImpotLigne(quittanceMensuelle, childImpotMensuel, quittanceMensuelleLigne);
                        childQuittanceMensuelleLignes.add(childQuittanceMensuelleLigne);
                        quittanceMensuelleLigne.getChildQuittanceMensuelleLignes().add(childQuittanceMensuelleLigne);
                    }
                    quittanceMensuelleLigne.setChildQuittanceMensuelleLignes(childQuittanceMensuelleLignes);

                    return quittanceMensuelleLigne;
                }).collect(Collectors.toList());
        quittanceMensuelle.setQuittanceMensuelleLignes(quittanceMensuelleLignes);
    }

    private QuittanceMensuelleLigne getQuittanceMensuelImpotLigne(QuittanceMensuelle quittanceMensuelle, ImpotMensuel impotMensuel) {
        return getQuittanceMensuelImpotLigne(quittanceMensuelle, impotMensuel, null);
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
            QuittanceMensuelleLigne quittanceMensuelleLignePrecedente = quittanceMensuelleLigneService.findByFicheClientIdAndQuittanceStatutAndAnneeAndMoisAndCode(
                ficheClientId,
                Arrays.asList(StatutDeclaration.VALIDE, StatutDeclaration.BROUILLON),
                anneeReport,
                moisReport,
                impotMensuelCode).orElseThrow(() -> new BusinessErrorException(ErrorConstants.ERR_QUITTANCE_PRECEDENTE_INEXISTANTE));
            return quittanceMensuelleLignePrecedente.getMontantTotal().compareTo(BigDecimal.ZERO) < 0 ?
                quittanceMensuelleLignePrecedente.getMontantTotal().multiply(new BigDecimal("-1")) :
                BigDecimal.ZERO;

    }

    private QuittanceMensuelleLigne getQuittanceMensuelImpotLigne(QuittanceMensuelle quittanceMensuelle, ImpotMensuel impotMensuel, QuittanceMensuelleLigne parentQuittanceMensuelleLigne) {
        QuittanceMensuelleLigne quittanceMensuelleLigne = new QuittanceMensuelleLigne();
        quittanceMensuelleLigne.setQuittanceMensuelle(quittanceMensuelle);
        quittanceMensuelleLigne.setImpotMensuel(impotMensuel);
        quittanceMensuelleLigne.setParent(impotMensuel.getParent());
        quittanceMensuelleLigne.setCode(impotMensuel.getCode());
        quittanceMensuelleLigne.setLibelle(impotMensuel.getLibelle());
        quittanceMensuelleLigne.setChild(impotMensuel.getChild());
        quittanceMensuelleLigne.setAppliquerReportMontant(impotMensuel.getAppliquerReportMontant());
        quittanceMensuelleLigne.setMontantReport(BigDecimal.ZERO);
        quittanceMensuelleLigne.setCoefficientMontant(impotMensuel.getCoefficientMontant());
        quittanceMensuelleLigne.setChild(impotMensuel.getChild());
        quittanceMensuelleLigne.setParentQuittanceMensuelleLigne(parentQuittanceMensuelleLigne);
        quittanceMensuelleLigne.setQuittanceMensuelleSousLignes(
                quittanceMensuelleSousLigneMapper.map(quittanceMensuelleLigne, impotMensuel.getImpotMensuelLignes()));
        return quittanceMensuelleLigne;
    }

    /**
     * Save a quittanceMensuelle.
     *
     * @param quittanceMensuelleDTO the entity to save
     * @return the persisted entity
     */
    public QuittanceMensuelleDTO save(QuittanceMensuelleDTO quittanceMensuelleDTO) {
        log.debug("Request to save QuittanceMensuelle : {}", quittanceMensuelleDTO);
        QuittanceMensuelle quittanceMensuelle = quittanceMensuelleMapper.toEntity(quittanceMensuelleDTO);
        updateStatutQuittance(quittanceMensuelle);
        quittanceMensuelle = quittanceMensuelleRepository.save(quittanceMensuelle);
        return quittanceMensuelleMapper.toDto(quittanceMensuelle);
    }

    /**
     * Get all the quittanceMensuelles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<QuittanceMensuelleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuittanceMensuelles");
        String creator = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new UsernameNotFoundException("Only loggued user can access"));
        return quittanceMensuelleRepository.findAllByCreatedBy(creator, pageable)
            .map(quittanceMensuelleMapper::toDto);
    }


    /**
     * Get one quittanceMensuelle by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<QuittanceMensuelleDTO> findOne(Long id) {
        log.debug("Request to get QuittanceMensuelle : {}", id);
        Optional<QuittanceMensuelleDTO> quittanceMensuelleDTOOptional = quittanceMensuelleRepository.findById(id)
            .map(quittanceMensuelleMapper::toDto);
        if (quittanceMensuelleDTOOptional.isPresent()) {
            QuittanceMensuelleDTO quittanceMensuelleDTO = quittanceMensuelleDTOOptional.get();
            initMontantReportCalc(quittanceMensuelleDTO);
            return Optional.of(quittanceMensuelleDTO);
        } else {
            return quittanceMensuelleDTOOptional;
        }
    }

    /**
     * Delete the quittanceMensuelle by id. if quittance to delete is rectificative, set associated initale quittanced statut to validated
     *
     * @param id the id of the entity
     */
    @Transactional
    public void delete(Long id) {
        log.debug("Request to delete QuittanceMensuelle : {}", id);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        QuittanceMensuelle quittanceMensuelle = quittanceMensuelleRepository.findById(id)
                .orElseThrow(() -> new BusinessErrorException(ErrorConstants.ERR_QUITTANCE_A_SUPPRIMER_INEXISTANTE, params));
        if (quittanceMensuelle.getNumeroQuittance() != null && quittanceMensuelle.getDatePaiement() != null)
            throw new BusinessErrorException(String.format(ErrorConstants.ERR_QUITTANCEA_SUPPRIMER_VALIDEE, params));
        if (quittanceMensuelle.getTypeDeclaration().equals(TypeDeclaration.DECLARATION_RECTIFICATIVE)) {
            QuittanceMensuelle quittanceInitiale = quittanceMensuelleRepository.findById(quittanceMensuelle.getParentQuittance().getId())
                    .orElseThrow(() -> new BusinessErrorException(ErrorConstants.ERR_QUITTANCE_RECTIFICATIVE_A_SUPPRIMER_SANS_QUITTANCE_INITIALE, params));
            quittanceInitiale.setStatut(StatutDeclaration.VALIDE);
            quittanceMensuelleRepository.save(quittanceInitiale);
        }
        quittanceMensuelleRepository.deleteById(id);
    }

    public void print(Long quittanceMensuelleId, HttpServletResponse response) throws DocumentException, IOException {

        PdfReader reader = new PdfReader("templates/pdf/model_quittance-mensuelle.pdf");
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
