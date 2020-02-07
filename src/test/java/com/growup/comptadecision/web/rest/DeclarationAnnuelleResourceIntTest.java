package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.ComptaDecisionApp;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test class for the DeclarationAnnuelleResource REST controller.
 *
 * @see DeclarationAnnuelleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComptaDecisionApp.class)
public class DeclarationAnnuelleResourceIntTest {

//    private static final Integer DEFAULT_ANNEE = 1;
//    private static final Integer UPDATED_ANNEE = 2;
//
//    private static final LocalDate DEFAULT_DATE_PAIEMENT = LocalDate.ofEpochDay(0L);
//    private static final LocalDate UPDATED_DATE_PAIEMENT = LocalDate.now(ZoneId.systemDefault());
//
//    private static final String DEFAULT_NUMERO_QUITTANCE = "AAAAAAAAAA";
//    private static final String UPDATED_NUMERO_QUITTANCE = "BBBBBBBBBB";
//
//    private static final BigDecimal DEFAULT_MONTANT_CHIFFRE_AFFAIRE_HT = new BigDecimal(1);
//    private static final BigDecimal UPDATED_MONTANT_CHIFFRE_AFFAIRE_HT = new BigDecimal(2);
//
//    private static final BigDecimal DEFAULT_MONTANT_CHIFFRE_AFFAIRE_EXPORT = new BigDecimal(1);
//    private static final BigDecimal UPDATED_MONTANT_CHIFFRE_AFFAIRE_EXPORT = new BigDecimal(2);
//
//    private static final BigDecimal DEFAULT_MONTANT_CHIFFRE_AFFAIRE_IMPOT = new BigDecimal(1);
//    private static final BigDecimal UPDATED_MONTANT_CHIFFRE_AFFAIRE_IMPOT = new BigDecimal(2);
//
//    private static final BigDecimal DEFAULT_MONTANT_CHIFFRE_AFFAIRE_TTC = new BigDecimal(1);
//    private static final BigDecimal UPDATED_MONTANT_CHIFFRE_AFFAIRE_TTC = new BigDecimal(2);
//
//    private static final BigDecimal DEFAULT_MONTANT_RESULTAT_COMPTABLE = new BigDecimal(1);
//    private static final BigDecimal UPDATED_MONTANT_RESULTAT_COMPTABLE = new BigDecimal(2);
//
//    private static final BigDecimal DEFAULT_MONTANT_DEDUCTION_COMMUNE = new BigDecimal(1);
//    private static final BigDecimal UPDATED_MONTANT_DEDUCTION_COMMUNE = new BigDecimal(2);
//
//    private static final BigDecimal DEFAULT_MONTANT_AUTRE_DEDUCTION = new BigDecimal(1);
//    private static final BigDecimal UPDATED_MONTANT_AUTRE_DEDUCTION = new BigDecimal(2);
//
//    private static final BigDecimal DEFAULT_MONTANT_BASE_IMPOSABLE = new BigDecimal(1);
//    private static final BigDecimal UPDATED_MONTANT_BASE_IMPOSABLE = new BigDecimal(2);
//
//    private static final BigDecimal DEFAULT_MONTANT_IMPOT_LIQUIDE = new BigDecimal(1);
//    private static final BigDecimal UPDATED_MONTANT_IMPOT_LIQUIDE = new BigDecimal(2);
//
//    private static final BigDecimal DEFAULT_MONTANT_ACOMPTE_PROVISIONNEL = new BigDecimal(1);
//    private static final BigDecimal UPDATED_MONTANT_ACOMPTE_PROVISIONNEL = new BigDecimal(2);
//
//    private static final BigDecimal DEFAULT_MONTANT_RETENUE_SOURCE = new BigDecimal(1);
//    private static final BigDecimal UPDATED_MONTANT_RETENUE_SOURCE = new BigDecimal(2);
//
//    private static final BigDecimal DEFAULT_MONTANT_NET_A_PAYE = new BigDecimal(1);
//    private static final BigDecimal UPDATED_MONTANT_NET_A_PAYE = new BigDecimal(2);
//
//    @Autowired
//    private DeclarationAnnuelleRepository declarationAnnuelleRepository;
//
//    @Autowired
//    private DeclarationAnnuelleMapper declarationAnnuelleMapper;
//
//    @Autowired
//    private DeclarationAnnuelleService declarationAnnuelleService;
//
//    @Autowired
//    private MappingJackson2HttpMessageConverter jacksonMessageConverter;
//
//    @Autowired
//    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
//
//    @Autowired
//    private ExceptionTranslator exceptionTranslator;
//
//    @Autowired
//    private EntityManager em;
//
//    @Autowired
//    private Validator validator;
//
//    private MockMvc restDeclarationAnnuelleMockMvc;
//
//    private DeclarationAnnuelle declarationAnnuelle;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        final DeclarationAnnuelleResource declarationAnnuelleResource = new DeclarationAnnuelleResource(declarationAnnuelleService);
//        this.restDeclarationAnnuelleMockMvc = MockMvcBuilders.standaloneSetup(declarationAnnuelleResource)
//            .setCustomArgumentResolvers(pageableArgumentResolver)
//            .setControllerAdvice(exceptionTranslator)
//            .setConversionService(createFormattingConversionService())
//            .setMessageConverters(jacksonMessageConverter)
//            .setValidator(validator).build();
//    }
//
//    /**
//     * Create an entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static DeclarationAnnuelle createEntity(EntityManager em) {
//        DeclarationAnnuelle declarationAnnuelle = new DeclarationAnnuelle();
//        return declarationAnnuelle;
//    }
//
//    @Before
//    public void initTest() {
//        declarationAnnuelle = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    public void createDeclarationAnnuelle() throws Exception {
//        int databaseSizeBeforeCreate = declarationAnnuelleRepository.findAll().size();
//
//        // Create the DeclarationAnnuelle
//        DeclarationAnnuelleDTO declarationAnnuelleDTO = declarationAnnuelleMapper.toDto(declarationAnnuelle);
//        restDeclarationAnnuelleMockMvc.perform(post("/api/declaration-annuelles")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(declarationAnnuelleDTO)))
//            .andExpect(status().isCreated());
//
//        // Validate the DeclarationAnnuelle in the database
//        List<DeclarationAnnuelle> declarationAnnuelleList = declarationAnnuelleRepository.findAll();
//        assertThat(declarationAnnuelleList).hasSize(databaseSizeBeforeCreate + 1);
//        DeclarationAnnuelle testDeclarationAnnuelle = declarationAnnuelleList.get(declarationAnnuelleList.size() - 1);
//        assertThat(testDeclarationAnnuelle.getAnnee()).isEqualTo(DEFAULT_ANNEE);
//        assertThat(testDeclarationAnnuelle.getDatePaiement()).isEqualTo(DEFAULT_DATE_PAIEMENT);
//        assertThat(testDeclarationAnnuelle.getNumeroQuittance()).isEqualTo(DEFAULT_NUMERO_QUITTANCE);
//        assertThat(testDeclarationAnnuelle.getMontantChiffreAffaireHT()).isEqualTo(DEFAULT_MONTANT_CHIFFRE_AFFAIRE_HT);
//        assertThat(testDeclarationAnnuelle.getMontantChiffreAffaireExport()).isEqualTo(DEFAULT_MONTANT_CHIFFRE_AFFAIRE_EXPORT);
//        assertThat(testDeclarationAnnuelle.getMontantChiffreAffaireLocal()).isEqualTo(DEFAULT_MONTANT_CHIFFRE_AFFAIRE_IMPOT);
//        assertThat(testDeclarationAnnuelle.getMontantChiffreAffaireTTC()).isEqualTo(DEFAULT_MONTANT_CHIFFRE_AFFAIRE_TTC);
//        assertThat(testDeclarationAnnuelle.getMontantResultatComptable()).isEqualTo(DEFAULT_MONTANT_RESULTAT_COMPTABLE);
//        assertThat(testDeclarationAnnuelle.getMontantResultatFiscal()).isEqualTo(DEFAULT_MONTANT_DEDUCTION_COMMUNE);
//        assertThat(testDeclarationAnnuelle.getMontantAutreDeduction()).isEqualTo(DEFAULT_MONTANT_AUTRE_DEDUCTION);
//        assertThat(testDeclarationAnnuelle.getMontantBaseImposable()).isEqualTo(DEFAULT_MONTANT_BASE_IMPOSABLE);
//        assertThat(testDeclarationAnnuelle.getMontantImpotLiquide()).isEqualTo(DEFAULT_MONTANT_IMPOT_LIQUIDE);
//        assertThat(testDeclarationAnnuelle.getMontantAcompteProvisionnel()).isEqualTo(DEFAULT_MONTANT_ACOMPTE_PROVISIONNEL);
//        assertThat(testDeclarationAnnuelle.getMontantRetenueSource()).isEqualTo(DEFAULT_MONTANT_RETENUE_SOURCE);
//        assertThat(testDeclarationAnnuelle.getMontantNetAPaye()).isEqualTo(DEFAULT_MONTANT_NET_A_PAYE);
//    }
//
//    @Test
//    @Transactional
//    public void createDeclarationAnnuelleWithExistingId() throws Exception {
//        int databaseSizeBeforeCreate = declarationAnnuelleRepository.findAll().size();
//
//        // Create the DeclarationAnnuelle with an existing ID
//        declarationAnnuelle.setId(1L);
//        DeclarationAnnuelleDTO declarationAnnuelleDTO = declarationAnnuelleMapper.toDto(declarationAnnuelle);
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restDeclarationAnnuelleMockMvc.perform(post("/api/declaration-annuelles")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(declarationAnnuelleDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the DeclarationAnnuelle in the database
//        List<DeclarationAnnuelle> declarationAnnuelleList = declarationAnnuelleRepository.findAll();
//        assertThat(declarationAnnuelleList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    public void checkAnneeIsRequired() throws Exception {
//        int databaseSizeBeforeTest = declarationAnnuelleRepository.findAll().size();
//        // set the field null
//        declarationAnnuelle.setAnnee(null);
//
//        // Create the DeclarationAnnuelle, which fails.
//        DeclarationAnnuelleDTO declarationAnnuelleDTO = declarationAnnuelleMapper.toDto(declarationAnnuelle);
//
//        restDeclarationAnnuelleMockMvc.perform(post("/api/declaration-annuelles")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(declarationAnnuelleDTO)))
//            .andExpect(status().isBadRequest());
//
//        List<DeclarationAnnuelle> declarationAnnuelleList = declarationAnnuelleRepository.findAll();
//        assertThat(declarationAnnuelleList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void getAllDeclarationAnnuelles() throws Exception {
//        // Initialize the database
//        declarationAnnuelleRepository.saveAndFlush(declarationAnnuelle);
//
//        // Get all the declarationAnnuelleList
//        restDeclarationAnnuelleMockMvc.perform(get("/api/declaration-annuelles?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(declarationAnnuelle.getId().intValue())))
//            .andExpect(jsonPath("$.[*].annee").value(hasItem(DEFAULT_ANNEE)))
//            .andExpect(jsonPath("$.[*].datePaiement").value(hasItem(DEFAULT_DATE_PAIEMENT.toString())))
//            .andExpect(jsonPath("$.[*].numeroQuittance").value(hasItem(DEFAULT_NUMERO_QUITTANCE.toString())))
//            .andExpect(jsonPath("$.[*].montantChiffreAffaireHT").value(hasItem(DEFAULT_MONTANT_CHIFFRE_AFFAIRE_HT.intValue())))
//            .andExpect(jsonPath("$.[*].montantChiffreAffaireExport").value(hasItem(DEFAULT_MONTANT_CHIFFRE_AFFAIRE_EXPORT.intValue())))
//            .andExpect(jsonPath("$.[*].montantChiffreAffaireImpot").value(hasItem(DEFAULT_MONTANT_CHIFFRE_AFFAIRE_IMPOT.intValue())))
//            .andExpect(jsonPath("$.[*].montantChiffreAffaireTTC").value(hasItem(DEFAULT_MONTANT_CHIFFRE_AFFAIRE_TTC.intValue())))
//            .andExpect(jsonPath("$.[*].montantResultatComptable").value(hasItem(DEFAULT_MONTANT_RESULTAT_COMPTABLE.intValue())))
//            .andExpect(jsonPath("$.[*].montantDeductionCommune").value(hasItem(DEFAULT_MONTANT_DEDUCTION_COMMUNE.intValue())))
//            .andExpect(jsonPath("$.[*].montantAutreDeduction").value(hasItem(DEFAULT_MONTANT_AUTRE_DEDUCTION.intValue())))
//            .andExpect(jsonPath("$.[*].montantBaseImposable").value(hasItem(DEFAULT_MONTANT_BASE_IMPOSABLE.intValue())))
//            .andExpect(jsonPath("$.[*].montantImpotLiquide").value(hasItem(DEFAULT_MONTANT_IMPOT_LIQUIDE.intValue())))
//            .andExpect(jsonPath("$.[*].montantAcompteProvisionnel").value(hasItem(DEFAULT_MONTANT_ACOMPTE_PROVISIONNEL.intValue())))
//            .andExpect(jsonPath("$.[*].montantRetenueSource").value(hasItem(DEFAULT_MONTANT_RETENUE_SOURCE.intValue())))
//            .andExpect(jsonPath("$.[*].montantNetAPaye").value(hasItem(DEFAULT_MONTANT_NET_A_PAYE.intValue())));
//    }
//
//    @Test
//    @Transactional
//    public void getDeclarationAnnuelle() throws Exception {
//        // Initialize the database
//        declarationAnnuelleRepository.saveAndFlush(declarationAnnuelle);
//
//        // Get the declarationAnnuelle
//        restDeclarationAnnuelleMockMvc.perform(get("/api/declaration-annuelles/{id}", declarationAnnuelle.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.id").value(declarationAnnuelle.getId().intValue()))
//            .andExpect(jsonPath("$.annee").value(DEFAULT_ANNEE))
//            .andExpect(jsonPath("$.datePaiement").value(DEFAULT_DATE_PAIEMENT.toString()))
//            .andExpect(jsonPath("$.numeroQuittance").value(DEFAULT_NUMERO_QUITTANCE.toString()))
//            .andExpect(jsonPath("$.montantChiffreAffaireHT").value(DEFAULT_MONTANT_CHIFFRE_AFFAIRE_HT.intValue()))
//            .andExpect(jsonPath("$.montantChiffreAffaireExport").value(DEFAULT_MONTANT_CHIFFRE_AFFAIRE_EXPORT.intValue()))
//            .andExpect(jsonPath("$.montantChiffreAffaireImpot").value(DEFAULT_MONTANT_CHIFFRE_AFFAIRE_IMPOT.intValue()))
//            .andExpect(jsonPath("$.montantChiffreAffaireTTC").value(DEFAULT_MONTANT_CHIFFRE_AFFAIRE_TTC.intValue()))
//            .andExpect(jsonPath("$.montantResultatComptable").value(DEFAULT_MONTANT_RESULTAT_COMPTABLE.intValue()))
//            .andExpect(jsonPath("$.montantDeductionCommune").value(DEFAULT_MONTANT_DEDUCTION_COMMUNE.intValue()))
//            .andExpect(jsonPath("$.montantAutreDeduction").value(DEFAULT_MONTANT_AUTRE_DEDUCTION.intValue()))
//            .andExpect(jsonPath("$.montantBaseImposable").value(DEFAULT_MONTANT_BASE_IMPOSABLE.intValue()))
//            .andExpect(jsonPath("$.montantImpotLiquide").value(DEFAULT_MONTANT_IMPOT_LIQUIDE.intValue()))
//            .andExpect(jsonPath("$.montantAcompteProvisionnel").value(DEFAULT_MONTANT_ACOMPTE_PROVISIONNEL.intValue()))
//            .andExpect(jsonPath("$.montantRetenueSource").value(DEFAULT_MONTANT_RETENUE_SOURCE.intValue()))
//            .andExpect(jsonPath("$.montantNetAPaye").value(DEFAULT_MONTANT_NET_A_PAYE.intValue()));
//    }
//
//    @Test
//    @Transactional
//    public void getNonExistingDeclarationAnnuelle() throws Exception {
//        // Get the declarationAnnuelle
//        restDeclarationAnnuelleMockMvc.perform(get("/api/declaration-annuelles/{id}", Long.MAX_VALUE))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    public void updateDeclarationAnnuelle() throws Exception {
//        // Initialize the database
//        declarationAnnuelleRepository.saveAndFlush(declarationAnnuelle);
//
//        int databaseSizeBeforeUpdate = declarationAnnuelleRepository.findAll().size();
//
//        // Update the declarationAnnuelle
//        DeclarationAnnuelle updatedDeclarationAnnuelle = declarationAnnuelleRepository.findById(declarationAnnuelle.getId()).get();
//        // Disconnect from session so that the updates on updatedDeclarationAnnuelle are not directly saved in db
//        em.detach(updatedDeclarationAnnuelle);
//        updatedDeclarationAnnuelle
//            .annee(UPDATED_ANNEE)
//            .datePaiement(UPDATED_DATE_PAIEMENT)
//            .numeroQuittance(UPDATED_NUMERO_QUITTANCE)
//            .montantChiffreAffaireHT(UPDATED_MONTANT_CHIFFRE_AFFAIRE_HT)
//            .montantChiffreAffaireExport(UPDATED_MONTANT_CHIFFRE_AFFAIRE_EXPORT)
//            .montantChiffreAffaireImpot(UPDATED_MONTANT_CHIFFRE_AFFAIRE_IMPOT)
//            .montantChiffreAffaireTTC(UPDATED_MONTANT_CHIFFRE_AFFAIRE_TTC)
//            .montantResultatComptable(UPDATED_MONTANT_RESULTAT_COMPTABLE)
//            .montantDeductionCommune(UPDATED_MONTANT_DEDUCTION_COMMUNE)
//            .montantAutreDeduction(UPDATED_MONTANT_AUTRE_DEDUCTION)
//            .montantBaseImposable(UPDATED_MONTANT_BASE_IMPOSABLE)
//            .montantImpotLiquide(UPDATED_MONTANT_IMPOT_LIQUIDE)
//            .montantAcompteProvisionnel(UPDATED_MONTANT_ACOMPTE_PROVISIONNEL)
//            .montantRetenueSource(UPDATED_MONTANT_RETENUE_SOURCE)
//            .montantNetAPaye(UPDATED_MONTANT_NET_A_PAYE);
//        DeclarationAnnuelleDTO declarationAnnuelleDTO = declarationAnnuelleMapper.toDto(updatedDeclarationAnnuelle);
//
//        restDeclarationAnnuelleMockMvc.perform(put("/api/declaration-annuelles")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(declarationAnnuelleDTO)))
//            .andExpect(status().isOk());
//
//        // Validate the DeclarationAnnuelle in the database
//        List<DeclarationAnnuelle> declarationAnnuelleList = declarationAnnuelleRepository.findAll();
//        assertThat(declarationAnnuelleList).hasSize(databaseSizeBeforeUpdate);
//        DeclarationAnnuelle testDeclarationAnnuelle = declarationAnnuelleList.get(declarationAnnuelleList.size() - 1);
//        assertThat(testDeclarationAnnuelle.getAnnee()).isEqualTo(UPDATED_ANNEE);
//        assertThat(testDeclarationAnnuelle.getDatePaiement()).isEqualTo(UPDATED_DATE_PAIEMENT);
//        assertThat(testDeclarationAnnuelle.getNumeroQuittance()).isEqualTo(UPDATED_NUMERO_QUITTANCE);
//        assertThat(testDeclarationAnnuelle.getMontantChiffreAffaireHT()).isEqualTo(UPDATED_MONTANT_CHIFFRE_AFFAIRE_HT);
//        assertThat(testDeclarationAnnuelle.getMontantChiffreAffaireExport()).isEqualTo(UPDATED_MONTANT_CHIFFRE_AFFAIRE_EXPORT);
//        assertThat(testDeclarationAnnuelle.getMontantChiffreAffaireLocal()).isEqualTo(UPDATED_MONTANT_CHIFFRE_AFFAIRE_IMPOT);
//        assertThat(testDeclarationAnnuelle.getMontantChiffreAffaireTTC()).isEqualTo(UPDATED_MONTANT_CHIFFRE_AFFAIRE_TTC);
//        assertThat(testDeclarationAnnuelle.getMontantResultatComptable()).isEqualTo(UPDATED_MONTANT_RESULTAT_COMPTABLE);
//        assertThat(testDeclarationAnnuelle.getMontantResultatFiscal()).isEqualTo(UPDATED_MONTANT_DEDUCTION_COMMUNE);
//        assertThat(testDeclarationAnnuelle.getMontantAutreDeduction()).isEqualTo(UPDATED_MONTANT_AUTRE_DEDUCTION);
//        assertThat(testDeclarationAnnuelle.getMontantBaseImposable()).isEqualTo(UPDATED_MONTANT_BASE_IMPOSABLE);
//        assertThat(testDeclarationAnnuelle.getMontantImpotLiquide()).isEqualTo(UPDATED_MONTANT_IMPOT_LIQUIDE);
//        assertThat(testDeclarationAnnuelle.getMontantAcompteProvisionnel()).isEqualTo(UPDATED_MONTANT_ACOMPTE_PROVISIONNEL);
//        assertThat(testDeclarationAnnuelle.getMontantRetenueSource()).isEqualTo(UPDATED_MONTANT_RETENUE_SOURCE);
//        assertThat(testDeclarationAnnuelle.getMontantNetAPaye()).isEqualTo(UPDATED_MONTANT_NET_A_PAYE);
//    }
//
//    @Test
//    @Transactional
//    public void updateNonExistingDeclarationAnnuelle() throws Exception {
//        int databaseSizeBeforeUpdate = declarationAnnuelleRepository.findAll().size();
//
//        // Create the DeclarationAnnuelle
//        DeclarationAnnuelleDTO declarationAnnuelleDTO = declarationAnnuelleMapper.toDto(declarationAnnuelle);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restDeclarationAnnuelleMockMvc.perform(put("/api/declaration-annuelles")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(declarationAnnuelleDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the DeclarationAnnuelle in the database
//        List<DeclarationAnnuelle> declarationAnnuelleList = declarationAnnuelleRepository.findAll();
//        assertThat(declarationAnnuelleList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    public void deleteDeclarationAnnuelle() throws Exception {
//        // Initialize the database
//        declarationAnnuelleRepository.saveAndFlush(declarationAnnuelle);
//
//        int databaseSizeBeforeDelete = declarationAnnuelleRepository.findAll().size();
//
//        // Delete the declarationAnnuelle
//        restDeclarationAnnuelleMockMvc.perform(delete("/api/declaration-annuelles/{id}", declarationAnnuelle.getId())
//            .accept(TestUtil.APPLICATION_JSON_UTF8))
//            .andExpect(status().isOk());
//
//        // Validate the database is empty
//        List<DeclarationAnnuelle> declarationAnnuelleList = declarationAnnuelleRepository.findAll();
//        assertThat(declarationAnnuelleList).hasSize(databaseSizeBeforeDelete - 1);
//    }
//
//    @Test
//    @Transactional
//    public void equalsVerifier() throws Exception {
//        TestUtil.equalsVerifier(DeclarationAnnuelle.class);
//        DeclarationAnnuelle declarationAnnuelle1 = new DeclarationAnnuelle();
//        declarationAnnuelle1.setId(1L);
//        DeclarationAnnuelle declarationAnnuelle2 = new DeclarationAnnuelle();
//        declarationAnnuelle2.setId(declarationAnnuelle1.getId());
//        assertThat(declarationAnnuelle1).isEqualTo(declarationAnnuelle2);
//        declarationAnnuelle2.setId(2L);
//        assertThat(declarationAnnuelle1).isNotEqualTo(declarationAnnuelle2);
//        declarationAnnuelle1.setId(null);
//        assertThat(declarationAnnuelle1).isNotEqualTo(declarationAnnuelle2);
//    }
//
//    @Test
//    @Transactional
//    public void dtoEqualsVerifier() throws Exception {
//        TestUtil.equalsVerifier(DeclarationAnnuelleDTO.class);
//        DeclarationAnnuelleDTO declarationAnnuelleDTO1 = new DeclarationAnnuelleDTO();
//        declarationAnnuelleDTO1.setId(1L);
//        DeclarationAnnuelleDTO declarationAnnuelleDTO2 = new DeclarationAnnuelleDTO();
//        assertThat(declarationAnnuelleDTO1).isNotEqualTo(declarationAnnuelleDTO2);
//        declarationAnnuelleDTO2.setId(declarationAnnuelleDTO1.getId());
//        assertThat(declarationAnnuelleDTO1).isEqualTo(declarationAnnuelleDTO2);
//        declarationAnnuelleDTO2.setId(2L);
//        assertThat(declarationAnnuelleDTO1).isNotEqualTo(declarationAnnuelleDTO2);
//        declarationAnnuelleDTO1.setId(null);
//        assertThat(declarationAnnuelleDTO1).isNotEqualTo(declarationAnnuelleDTO2);
//    }
//
//    @Test
//    @Transactional
//    public void testEntityFromId() {
//        assertThat(declarationAnnuelleMapper.fromId(42L).getId()).isEqualTo(42);
//        assertThat(declarationAnnuelleMapper.fromId(null)).isNull();
//    }
}
