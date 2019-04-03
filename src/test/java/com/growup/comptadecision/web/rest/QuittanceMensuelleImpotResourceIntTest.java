package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.ComptaDecisionApp;

import com.growup.comptadecision.domain.QuittanceMensuelleImpot;
import com.growup.comptadecision.repository.QuittanceMensuelleImpotRepository;
import com.growup.comptadecision.service.QuittanceMensuelleImpotService;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotDTO;
import com.growup.comptadecision.service.mapper.QuittanceMensuelleImpotMapper;
import com.growup.comptadecision.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static com.growup.comptadecision.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the QuittanceMensuelleImpotResource REST controller.
 *
 * @see QuittanceMensuelleImpotResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComptaDecisionApp.class)
public class QuittanceMensuelleImpotResourceIntTest {

    private static final Integer DEFAULT_ANNEE = 1;
    private static final Integer UPDATED_ANNEE = 2;

    private static final Integer DEFAULT_MOIS = 1;
    private static final Integer UPDATED_MOIS = 2;

    private static final String DEFAULT_NUMERO_QUITTANCE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_QUITTANCE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_PAIEMENT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_PAIEMENT = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_MONTANT_PAYE = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_PAYE = new BigDecimal(2);

    @Autowired
    private QuittanceMensuelleImpotRepository quittanceMensuelleImpotRepository;

    @Autowired
    private QuittanceMensuelleImpotMapper quittanceMensuelleImpotMapper;

    @Autowired
    private QuittanceMensuelleImpotService quittanceMensuelleImpotService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restQuittanceMensuelleImpotMockMvc;

    private QuittanceMensuelleImpot quittanceMensuelleImpot;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QuittanceMensuelleImpotResource quittanceMensuelleImpotResource = new QuittanceMensuelleImpotResource(quittanceMensuelleImpotService);
        this.restQuittanceMensuelleImpotMockMvc = MockMvcBuilders.standaloneSetup(quittanceMensuelleImpotResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuittanceMensuelleImpot createEntity(EntityManager em) {
        QuittanceMensuelleImpot quittanceMensuelleImpot = new QuittanceMensuelleImpot()
            .annee(DEFAULT_ANNEE)
            .mois(DEFAULT_MOIS)
            .numeroQuittance(DEFAULT_NUMERO_QUITTANCE)
            .datePaiement(DEFAULT_DATE_PAIEMENT)
            .montantPaye(DEFAULT_MONTANT_PAYE);
        return quittanceMensuelleImpot;
    }

    @Before
    public void initTest() {
        quittanceMensuelleImpot = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuittanceMensuelleImpot() throws Exception {
        int databaseSizeBeforeCreate = quittanceMensuelleImpotRepository.findAll().size();

        // Create the QuittanceMensuelleImpot
        QuittanceMensuelleImpotDTO quittanceMensuelleImpotDTO = quittanceMensuelleImpotMapper.toDto(quittanceMensuelleImpot);
        restQuittanceMensuelleImpotMockMvc.perform(post("/api/quittance-mensuelle-impots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quittanceMensuelleImpotDTO)))
            .andExpect(status().isCreated());

        // Validate the QuittanceMensuelleImpot in the database
        List<QuittanceMensuelleImpot> quittanceMensuelleImpotList = quittanceMensuelleImpotRepository.findAll();
        assertThat(quittanceMensuelleImpotList).hasSize(databaseSizeBeforeCreate + 1);
        QuittanceMensuelleImpot testQuittanceMensuelleImpot = quittanceMensuelleImpotList.get(quittanceMensuelleImpotList.size() - 1);
        assertThat(testQuittanceMensuelleImpot.getAnnee()).isEqualTo(DEFAULT_ANNEE);
        assertThat(testQuittanceMensuelleImpot.getMois()).isEqualTo(DEFAULT_MOIS);
        assertThat(testQuittanceMensuelleImpot.getNumeroQuittance()).isEqualTo(DEFAULT_NUMERO_QUITTANCE);
        assertThat(testQuittanceMensuelleImpot.getDatePaiement()).isEqualTo(DEFAULT_DATE_PAIEMENT);
        assertThat(testQuittanceMensuelleImpot.getMontantPaye()).isEqualTo(DEFAULT_MONTANT_PAYE);
    }

    @Test
    @Transactional
    public void createQuittanceMensuelleImpotWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = quittanceMensuelleImpotRepository.findAll().size();

        // Create the QuittanceMensuelleImpot with an existing ID
        quittanceMensuelleImpot.setId(1L);
        QuittanceMensuelleImpotDTO quittanceMensuelleImpotDTO = quittanceMensuelleImpotMapper.toDto(quittanceMensuelleImpot);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuittanceMensuelleImpotMockMvc.perform(post("/api/quittance-mensuelle-impots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quittanceMensuelleImpotDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QuittanceMensuelleImpot in the database
        List<QuittanceMensuelleImpot> quittanceMensuelleImpotList = quittanceMensuelleImpotRepository.findAll();
        assertThat(quittanceMensuelleImpotList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAnneeIsRequired() throws Exception {
        int databaseSizeBeforeTest = quittanceMensuelleImpotRepository.findAll().size();
        // set the field null
        quittanceMensuelleImpot.setAnnee(null);

        // Create the QuittanceMensuelleImpot, which fails.
        QuittanceMensuelleImpotDTO quittanceMensuelleImpotDTO = quittanceMensuelleImpotMapper.toDto(quittanceMensuelleImpot);

        restQuittanceMensuelleImpotMockMvc.perform(post("/api/quittance-mensuelle-impots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quittanceMensuelleImpotDTO)))
            .andExpect(status().isBadRequest());

        List<QuittanceMensuelleImpot> quittanceMensuelleImpotList = quittanceMensuelleImpotRepository.findAll();
        assertThat(quittanceMensuelleImpotList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMoisIsRequired() throws Exception {
        int databaseSizeBeforeTest = quittanceMensuelleImpotRepository.findAll().size();
        // set the field null
        quittanceMensuelleImpot.setMois(null);

        // Create the QuittanceMensuelleImpot, which fails.
        QuittanceMensuelleImpotDTO quittanceMensuelleImpotDTO = quittanceMensuelleImpotMapper.toDto(quittanceMensuelleImpot);

        restQuittanceMensuelleImpotMockMvc.perform(post("/api/quittance-mensuelle-impots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quittanceMensuelleImpotDTO)))
            .andExpect(status().isBadRequest());

        List<QuittanceMensuelleImpot> quittanceMensuelleImpotList = quittanceMensuelleImpotRepository.findAll();
        assertThat(quittanceMensuelleImpotList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllQuittanceMensuelleImpots() throws Exception {
        // Initialize the database
        quittanceMensuelleImpotRepository.saveAndFlush(quittanceMensuelleImpot);

        // Get all the quittanceMensuelleImpotList
        restQuittanceMensuelleImpotMockMvc.perform(get("/api/quittance-mensuelle-impots?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quittanceMensuelleImpot.getId().intValue())))
            .andExpect(jsonPath("$.[*].annee").value(hasItem(DEFAULT_ANNEE)))
            .andExpect(jsonPath("$.[*].mois").value(hasItem(DEFAULT_MOIS)))
            .andExpect(jsonPath("$.[*].numeroQuittance").value(hasItem(DEFAULT_NUMERO_QUITTANCE.toString())))
            .andExpect(jsonPath("$.[*].datePaiement").value(hasItem(DEFAULT_DATE_PAIEMENT.toString())))
            .andExpect(jsonPath("$.[*].montantPaye").value(hasItem(DEFAULT_MONTANT_PAYE.intValue())));
    }
    
    @Test
    @Transactional
    public void getQuittanceMensuelleImpot() throws Exception {
        // Initialize the database
        quittanceMensuelleImpotRepository.saveAndFlush(quittanceMensuelleImpot);

        // Get the quittanceMensuelleImpot
        restQuittanceMensuelleImpotMockMvc.perform(get("/api/quittance-mensuelle-impots/{id}", quittanceMensuelleImpot.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(quittanceMensuelleImpot.getId().intValue()))
            .andExpect(jsonPath("$.annee").value(DEFAULT_ANNEE))
            .andExpect(jsonPath("$.mois").value(DEFAULT_MOIS))
            .andExpect(jsonPath("$.numeroQuittance").value(DEFAULT_NUMERO_QUITTANCE.toString()))
            .andExpect(jsonPath("$.datePaiement").value(DEFAULT_DATE_PAIEMENT.toString()))
            .andExpect(jsonPath("$.montantPaye").value(DEFAULT_MONTANT_PAYE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingQuittanceMensuelleImpot() throws Exception {
        // Get the quittanceMensuelleImpot
        restQuittanceMensuelleImpotMockMvc.perform(get("/api/quittance-mensuelle-impots/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuittanceMensuelleImpot() throws Exception {
        // Initialize the database
        quittanceMensuelleImpotRepository.saveAndFlush(quittanceMensuelleImpot);

        int databaseSizeBeforeUpdate = quittanceMensuelleImpotRepository.findAll().size();

        // Update the quittanceMensuelleImpot
        QuittanceMensuelleImpot updatedQuittanceMensuelleImpot = quittanceMensuelleImpotRepository.findById(quittanceMensuelleImpot.getId()).get();
        // Disconnect from session so that the updates on updatedQuittanceMensuelleImpot are not directly saved in db
        em.detach(updatedQuittanceMensuelleImpot);
        updatedQuittanceMensuelleImpot
            .annee(UPDATED_ANNEE)
            .mois(UPDATED_MOIS)
            .numeroQuittance(UPDATED_NUMERO_QUITTANCE)
            .datePaiement(UPDATED_DATE_PAIEMENT)
            .montantPaye(UPDATED_MONTANT_PAYE);
        QuittanceMensuelleImpotDTO quittanceMensuelleImpotDTO = quittanceMensuelleImpotMapper.toDto(updatedQuittanceMensuelleImpot);

        restQuittanceMensuelleImpotMockMvc.perform(put("/api/quittance-mensuelle-impots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quittanceMensuelleImpotDTO)))
            .andExpect(status().isOk());

        // Validate the QuittanceMensuelleImpot in the database
        List<QuittanceMensuelleImpot> quittanceMensuelleImpotList = quittanceMensuelleImpotRepository.findAll();
        assertThat(quittanceMensuelleImpotList).hasSize(databaseSizeBeforeUpdate);
        QuittanceMensuelleImpot testQuittanceMensuelleImpot = quittanceMensuelleImpotList.get(quittanceMensuelleImpotList.size() - 1);
        assertThat(testQuittanceMensuelleImpot.getAnnee()).isEqualTo(UPDATED_ANNEE);
        assertThat(testQuittanceMensuelleImpot.getMois()).isEqualTo(UPDATED_MOIS);
        assertThat(testQuittanceMensuelleImpot.getNumeroQuittance()).isEqualTo(UPDATED_NUMERO_QUITTANCE);
        assertThat(testQuittanceMensuelleImpot.getDatePaiement()).isEqualTo(UPDATED_DATE_PAIEMENT);
        assertThat(testQuittanceMensuelleImpot.getMontantPaye()).isEqualTo(UPDATED_MONTANT_PAYE);
    }

    @Test
    @Transactional
    public void updateNonExistingQuittanceMensuelleImpot() throws Exception {
        int databaseSizeBeforeUpdate = quittanceMensuelleImpotRepository.findAll().size();

        // Create the QuittanceMensuelleImpot
        QuittanceMensuelleImpotDTO quittanceMensuelleImpotDTO = quittanceMensuelleImpotMapper.toDto(quittanceMensuelleImpot);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuittanceMensuelleImpotMockMvc.perform(put("/api/quittance-mensuelle-impots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quittanceMensuelleImpotDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QuittanceMensuelleImpot in the database
        List<QuittanceMensuelleImpot> quittanceMensuelleImpotList = quittanceMensuelleImpotRepository.findAll();
        assertThat(quittanceMensuelleImpotList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQuittanceMensuelleImpot() throws Exception {
        // Initialize the database
        quittanceMensuelleImpotRepository.saveAndFlush(quittanceMensuelleImpot);

        int databaseSizeBeforeDelete = quittanceMensuelleImpotRepository.findAll().size();

        // Delete the quittanceMensuelleImpot
        restQuittanceMensuelleImpotMockMvc.perform(delete("/api/quittance-mensuelle-impots/{id}", quittanceMensuelleImpot.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<QuittanceMensuelleImpot> quittanceMensuelleImpotList = quittanceMensuelleImpotRepository.findAll();
        assertThat(quittanceMensuelleImpotList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuittanceMensuelleImpot.class);
        QuittanceMensuelleImpot quittanceMensuelleImpot1 = new QuittanceMensuelleImpot();
        quittanceMensuelleImpot1.setId(1L);
        QuittanceMensuelleImpot quittanceMensuelleImpot2 = new QuittanceMensuelleImpot();
        quittanceMensuelleImpot2.setId(quittanceMensuelleImpot1.getId());
        assertThat(quittanceMensuelleImpot1).isEqualTo(quittanceMensuelleImpot2);
        quittanceMensuelleImpot2.setId(2L);
        assertThat(quittanceMensuelleImpot1).isNotEqualTo(quittanceMensuelleImpot2);
        quittanceMensuelleImpot1.setId(null);
        assertThat(quittanceMensuelleImpot1).isNotEqualTo(quittanceMensuelleImpot2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuittanceMensuelleImpotDTO.class);
        QuittanceMensuelleImpotDTO quittanceMensuelleImpotDTO1 = new QuittanceMensuelleImpotDTO();
        quittanceMensuelleImpotDTO1.setId(1L);
        QuittanceMensuelleImpotDTO quittanceMensuelleImpotDTO2 = new QuittanceMensuelleImpotDTO();
        assertThat(quittanceMensuelleImpotDTO1).isNotEqualTo(quittanceMensuelleImpotDTO2);
        quittanceMensuelleImpotDTO2.setId(quittanceMensuelleImpotDTO1.getId());
        assertThat(quittanceMensuelleImpotDTO1).isEqualTo(quittanceMensuelleImpotDTO2);
        quittanceMensuelleImpotDTO2.setId(2L);
        assertThat(quittanceMensuelleImpotDTO1).isNotEqualTo(quittanceMensuelleImpotDTO2);
        quittanceMensuelleImpotDTO1.setId(null);
        assertThat(quittanceMensuelleImpotDTO1).isNotEqualTo(quittanceMensuelleImpotDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(quittanceMensuelleImpotMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(quittanceMensuelleImpotMapper.fromId(null)).isNull();
    }
}
