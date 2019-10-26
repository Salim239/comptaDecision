package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.ComptaDecisionApp;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test class for the QuittanceMensuelleImpotDetailResource REST controller.
 *
 * @see QuittanceMensuelleImpotDetailResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComptaDecisionApp.class)
@Ignore
public class QuittanceMensuelleImpotDetailResourceIntTest {

//    private static final BigDecimal DEFAULT_MONTANT_PAYE = new BigDecimal(1);
//    private static final BigDecimal UPDATED_MONTANT_PAYE = new BigDecimal(2);
//
//    @Autowired
//    private QuittanceMensuelleImpotDetailRepository quittanceMensuelleImpotDetailRepository;
//
//    @Autowired
//    private QuittanceMensuelleImpotDetailMapper quittanceMensuelleImpotDetailMapper;
//
//    @Autowired
//    private QuittanceMensuelleImpotDetailService quittanceMensuelleImpotDetailService;
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
//    private MockMvc restQuittanceMensuelleImpotDetailMockMvc;
//
//    private QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        final QuittanceMensuelleImpotDetailResource quittanceMensuelleImpotDetailResource = new QuittanceMensuelleImpotDetailResource(quittanceMensuelleImpotDetailService);
//        this.restQuittanceMensuelleImpotDetailMockMvc = MockMvcBuilders.standaloneSetup(quittanceMensuelleImpotDetailResource)
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
//    public static QuittanceMensuelleImpotDetail createEntity(EntityManager em) {
//        QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail = QuittanceMensuelleImpotDetail.builder()
//            .montantTotal(DEFAULT_MONTANT_PAYE).build();
//        return quittanceMensuelleImpotDetail;
//    }
//
//    @Before
//    public void initTest() {
//        quittanceMensuelleImpotDetail = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    public void createQuittanceMensuelleImpotDetail() throws Exception {
//        int databaseSizeBeforeCreate = quittanceMensuelleImpotDetailRepository.findAll().size();
//
//        // Create the QuittanceMensuelleImpotDetail
//        QuittanceMensuelleImpotDetailDTO quittanceMensuelleImpotDetailDTO = quittanceMensuelleImpotDetailMapper.toDto(quittanceMensuelleImpotDetail);
//        restQuittanceMensuelleImpotDetailMockMvc.perform(post("/api/quittance-mensuelle-impot-details")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(quittanceMensuelleImpotDetailDTO)))
//            .andExpect(status().isCreated());
//
//        // Validate the QuittanceMensuelleImpotDetail in the database
//        List<QuittanceMensuelleImpotDetail> quittanceMensuelleImpotDetailList = quittanceMensuelleImpotDetailRepository.findAll();
//        assertThat(quittanceMensuelleImpotDetailList).hasSize(databaseSizeBeforeCreate + 1);
//        QuittanceMensuelleImpotDetail testQuittanceMensuelleImpotDetail = quittanceMensuelleImpotDetailList.get(quittanceMensuelleImpotDetailList.size() - 1);
//        assertThat(testQuittanceMensuelleImpotDetail.getMontantTotal()).isEqualTo(DEFAULT_MONTANT_PAYE);
//    }
//
//    @Test
//    @Transactional
//    public void createQuittanceMensuelleImpotDetailWithExistingId() throws Exception {
//        int databaseSizeBeforeCreate = quittanceMensuelleImpotDetailRepository.findAll().size();
//
//        // Create the QuittanceMensuelleImpotDetail with an existing ID
//        quittanceMensuelleImpotDetail.setId(1L);
//        QuittanceMensuelleImpotDetailDTO quittanceMensuelleImpotDetailDTO = quittanceMensuelleImpotDetailMapper.toDto(quittanceMensuelleImpotDetail);
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restQuittanceMensuelleImpotDetailMockMvc.perform(post("/api/quittance-mensuelle-impot-details")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(quittanceMensuelleImpotDetailDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the QuittanceMensuelleImpotDetail in the database
//        List<QuittanceMensuelleImpotDetail> quittanceMensuelleImpotDetailList = quittanceMensuelleImpotDetailRepository.findAll();
//        assertThat(quittanceMensuelleImpotDetailList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    public void getAllQuittanceMensuelleImpotDetails() throws Exception {
//        // Initialize the database
//        quittanceMensuelleImpotDetailRepository.saveAndFlush(quittanceMensuelleImpotDetail);
//
//        // Get all the quittanceMensuelleImpotDetailList
//        restQuittanceMensuelleImpotDetailMockMvc.perform(get("/api/quittance-mensuelle-impot-details?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(quittanceMensuelleImpotDetail.getId().intValue())))
//            .andExpect(jsonPath("$.[*].montantPaye").value(hasItem(DEFAULT_MONTANT_PAYE.intValue())));
//    }
//
//    @Test
//    @Transactional
//    public void getQuittanceMensuelleImpotDetail() throws Exception {
//        // Initialize the database
//        quittanceMensuelleImpotDetailRepository.saveAndFlush(quittanceMensuelleImpotDetail);
//
//        // Get the quittanceMensuelleImpotDetail
//        restQuittanceMensuelleImpotDetailMockMvc.perform(get("/api/quittance-mensuelle-impot-details/{id}", quittanceMensuelleImpotDetail.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.id").value(quittanceMensuelleImpotDetail.getId().intValue()))
//            .andExpect(jsonPath("$.montantPaye").value(DEFAULT_MONTANT_PAYE.intValue()));
//    }
//
//    @Test
//    @Transactional
//    public void getNonExistingQuittanceMensuelleImpotDetail() throws Exception {
//        // Get the quittanceMensuelleImpotDetail
//        restQuittanceMensuelleImpotDetailMockMvc.perform(get("/api/quittance-mensuelle-impot-details/{id}", Long.MAX_VALUE))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    public void updateQuittanceMensuelleImpotDetail() throws Exception {
//        // Initialize the database
//        quittanceMensuelleImpotDetailRepository.saveAndFlush(quittanceMensuelleImpotDetail);
//
//        int databaseSizeBeforeUpdate = quittanceMensuelleImpotDetailRepository.findAll().size();
//
//        // Update the quittanceMensuelleImpotDetail
//        QuittanceMensuelleImpotDetail updatedQuittanceMensuelleImpotDetail = quittanceMensuelleImpotDetailRepository.findById(quittanceMensuelleImpotDetail.getId()).get();
//        // Disconnect from session so that the updates on updatedQuittanceMensuelleImpotDetail are not directly saved in db
//        em.detach(updatedQuittanceMensuelleImpotDetail);
//        updatedQuittanceMensuelleImpotDetail.setMontantTotal(UPDATED_MONTANT_PAYE);
//        QuittanceMensuelleImpotDetailDTO quittanceMensuelleImpotDetailDTO = quittanceMensuelleImpotDetailMapper.toDto(updatedQuittanceMensuelleImpotDetail);
//
//        restQuittanceMensuelleImpotDetailMockMvc.perform(put("/api/quittance-mensuelle-impot-details")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(quittanceMensuelleImpotDetailDTO)))
//            .andExpect(status().isOk());
//
//        // Validate the QuittanceMensuelleImpotDetail in the database
//        List<QuittanceMensuelleImpotDetail> quittanceMensuelleImpotDetailList = quittanceMensuelleImpotDetailRepository.findAll();
//        assertThat(quittanceMensuelleImpotDetailList).hasSize(databaseSizeBeforeUpdate);
//        QuittanceMensuelleImpotDetail testQuittanceMensuelleImpotDetail = quittanceMensuelleImpotDetailList.get(quittanceMensuelleImpotDetailList.size() - 1);
//        assertThat(testQuittanceMensuelleImpotDetail.getMontantTotal()).isEqualTo(UPDATED_MONTANT_PAYE);
//    }
//
//    @Test
//    @Transactional
//    public void updateNonExistingQuittanceMensuelleImpotDetail() throws Exception {
//        int databaseSizeBeforeUpdate = quittanceMensuelleImpotDetailRepository.findAll().size();
//
//        // Create the QuittanceMensuelleImpotDetail
//        QuittanceMensuelleImpotDetailDTO quittanceMensuelleImpotDetailDTO = quittanceMensuelleImpotDetailMapper.toDto(quittanceMensuelleImpotDetail);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restQuittanceMensuelleImpotDetailMockMvc.perform(put("/api/quittance-mensuelle-impot-details")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(quittanceMensuelleImpotDetailDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the QuittanceMensuelleImpotDetail in the database
//        List<QuittanceMensuelleImpotDetail> quittanceMensuelleImpotDetailList = quittanceMensuelleImpotDetailRepository.findAll();
//        assertThat(quittanceMensuelleImpotDetailList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    public void deleteQuittanceMensuelleImpotDetail() throws Exception {
//        // Initialize the database
//        quittanceMensuelleImpotDetailRepository.saveAndFlush(quittanceMensuelleImpotDetail);
//
//        int databaseSizeBeforeDelete = quittanceMensuelleImpotDetailRepository.findAll().size();
//
//        // Delete the quittanceMensuelleImpotDetail
//        restQuittanceMensuelleImpotDetailMockMvc.perform(delete("/api/quittance-mensuelle-impot-details/{id}", quittanceMensuelleImpotDetail.getId())
//            .accept(TestUtil.APPLICATION_JSON_UTF8))
//            .andExpect(status().isOk());
//
//        // Validate the database is empty
//        List<QuittanceMensuelleImpotDetail> quittanceMensuelleImpotDetailList = quittanceMensuelleImpotDetailRepository.findAll();
//        assertThat(quittanceMensuelleImpotDetailList).hasSize(databaseSizeBeforeDelete - 1);
//    }
//
//    @Test
//    @Transactional
//    public void equalsVerifier() throws Exception {
//        TestUtil.equalsVerifier(QuittanceMensuelleImpotDetail.class);
//        QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail1 = new QuittanceMensuelleImpotDetail();
//        quittanceMensuelleImpotDetail1.setId(1L);
//        QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail2 = new QuittanceMensuelleImpotDetail();
//        quittanceMensuelleImpotDetail2.setId(quittanceMensuelleImpotDetail1.getId());
//        assertThat(quittanceMensuelleImpotDetail1).isEqualTo(quittanceMensuelleImpotDetail2);
//        quittanceMensuelleImpotDetail2.setId(2L);
//        assertThat(quittanceMensuelleImpotDetail1).isNotEqualTo(quittanceMensuelleImpotDetail2);
//        quittanceMensuelleImpotDetail1.setId(null);
//        assertThat(quittanceMensuelleImpotDetail1).isNotEqualTo(quittanceMensuelleImpotDetail2);
//    }
//
//    @Test
//    @Transactional
//    public void dtoEqualsVerifier() throws Exception {
//        TestUtil.equalsVerifier(QuittanceMensuelleImpotDetailDTO.class);
//        QuittanceMensuelleImpotDetailDTO quittanceMensuelleImpotDetailDTO1 = new QuittanceMensuelleImpotDetailDTO();
//        quittanceMensuelleImpotDetailDTO1.setId(1L);
//        QuittanceMensuelleImpotDetailDTO quittanceMensuelleImpotDetailDTO2 = new QuittanceMensuelleImpotDetailDTO();
//        assertThat(quittanceMensuelleImpotDetailDTO1).isNotEqualTo(quittanceMensuelleImpotDetailDTO2);
//        quittanceMensuelleImpotDetailDTO2.setId(quittanceMensuelleImpotDetailDTO1.getId());
//        assertThat(quittanceMensuelleImpotDetailDTO1).isEqualTo(quittanceMensuelleImpotDetailDTO2);
//        quittanceMensuelleImpotDetailDTO2.setId(2L);
//        assertThat(quittanceMensuelleImpotDetailDTO1).isNotEqualTo(quittanceMensuelleImpotDetailDTO2);
//        quittanceMensuelleImpotDetailDTO1.setId(null);
//        assertThat(quittanceMensuelleImpotDetailDTO1).isNotEqualTo(quittanceMensuelleImpotDetailDTO2);
//    }
//
//    @Test
//    @Transactional
//    public void testEntityFromId() {
//        assertThat(quittanceMensuelleImpotDetailMapper.fromId(42L).getId()).isEqualTo(42);
//        assertThat(quittanceMensuelleImpotDetailMapper.fromId(null)).isNull();
//    }
}
