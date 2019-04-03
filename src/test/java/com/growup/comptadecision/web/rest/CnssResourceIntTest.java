package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.ComptaDecisionApp;

import com.growup.comptadecision.domain.Cnss;
import com.growup.comptadecision.repository.CnssRepository;
import com.growup.comptadecision.service.CnssService;
import com.growup.comptadecision.service.dto.CnssDTO;
import com.growup.comptadecision.service.mapper.CnssMapper;
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
 * Test class for the CnssResource REST controller.
 *
 * @see CnssResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComptaDecisionApp.class)
public class CnssResourceIntTest {

    private static final Integer DEFAULT_ANNEE = 1;
    private static final Integer UPDATED_ANNEE = 2;

    private static final Integer DEFAULT_TRIMESTRE = 1;
    private static final Integer UPDATED_TRIMESTRE = 2;

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NUMERO_QUITTANCE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_QUITTANCE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_MONTANT_SALAIRE_BRUT_NORMAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_SALAIRE_BRUT_NORMAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_SALAIRE_BRUT_KARAMA = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_SALAIRE_BRUT_KARAMA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_SALAIRE_BRUT_AUTRE = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_SALAIRE_BRUT_AUTRE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_CHIFFRE_AFFAIRE_TTC = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_CHIFFRE_AFFAIRE_TTC = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TOT = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CNSS = new BigDecimal(1);
    private static final BigDecimal UPDATED_CNSS = new BigDecimal(2);

    @Autowired
    private CnssRepository cnssRepository;

    @Autowired
    private CnssMapper cnssMapper;

    @Autowired
    private CnssService cnssService;

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

    private MockMvc restCnssMockMvc;

    private Cnss cnss;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CnssResource cnssResource = new CnssResource(cnssService);
        this.restCnssMockMvc = MockMvcBuilders.standaloneSetup(cnssResource)
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
    public static Cnss createEntity(EntityManager em) {
        Cnss cnss = new Cnss()
            .annee(DEFAULT_ANNEE)
            .trimestre(DEFAULT_TRIMESTRE)
            .date(DEFAULT_DATE)
            .numeroQuittance(DEFAULT_NUMERO_QUITTANCE)
            .montantSalaireBrutNormal(DEFAULT_MONTANT_SALAIRE_BRUT_NORMAL)
            .montantSalaireBrutKarama(DEFAULT_MONTANT_SALAIRE_BRUT_KARAMA)
            .montantSalaireBrutAutre(DEFAULT_MONTANT_SALAIRE_BRUT_AUTRE)
            .montantChiffreAffaireTTC(DEFAULT_MONTANT_CHIFFRE_AFFAIRE_TTC)
            .tot(DEFAULT_TOT)
            .cnss(DEFAULT_CNSS);
        return cnss;
    }

    @Before
    public void initTest() {
        cnss = createEntity(em);
    }

    @Test
    @Transactional
    public void createCnss() throws Exception {
        int databaseSizeBeforeCreate = cnssRepository.findAll().size();

        // Create the Cnss
        CnssDTO cnssDTO = cnssMapper.toDto(cnss);
        restCnssMockMvc.perform(post("/api/cnsses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cnssDTO)))
            .andExpect(status().isCreated());

        // Validate the Cnss in the database
        List<Cnss> cnssList = cnssRepository.findAll();
        assertThat(cnssList).hasSize(databaseSizeBeforeCreate + 1);
        Cnss testCnss = cnssList.get(cnssList.size() - 1);
        assertThat(testCnss.getAnnee()).isEqualTo(DEFAULT_ANNEE);
        assertThat(testCnss.getTrimestre()).isEqualTo(DEFAULT_TRIMESTRE);
        assertThat(testCnss.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testCnss.getNumeroQuittance()).isEqualTo(DEFAULT_NUMERO_QUITTANCE);
        assertThat(testCnss.getMontantSalaireBrutNormal()).isEqualTo(DEFAULT_MONTANT_SALAIRE_BRUT_NORMAL);
        assertThat(testCnss.getMontantSalaireBrutKarama()).isEqualTo(DEFAULT_MONTANT_SALAIRE_BRUT_KARAMA);
        assertThat(testCnss.getMontantSalaireBrutAutre()).isEqualTo(DEFAULT_MONTANT_SALAIRE_BRUT_AUTRE);
        assertThat(testCnss.getMontantChiffreAffaireTTC()).isEqualTo(DEFAULT_MONTANT_CHIFFRE_AFFAIRE_TTC);
        assertThat(testCnss.getTot()).isEqualTo(DEFAULT_TOT);
        assertThat(testCnss.getCnss()).isEqualTo(DEFAULT_CNSS);
    }

    @Test
    @Transactional
    public void createCnssWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cnssRepository.findAll().size();

        // Create the Cnss with an existing ID
        cnss.setId(1L);
        CnssDTO cnssDTO = cnssMapper.toDto(cnss);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCnssMockMvc.perform(post("/api/cnsses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cnssDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cnss in the database
        List<Cnss> cnssList = cnssRepository.findAll();
        assertThat(cnssList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAnneeIsRequired() throws Exception {
        int databaseSizeBeforeTest = cnssRepository.findAll().size();
        // set the field null
        cnss.setAnnee(null);

        // Create the Cnss, which fails.
        CnssDTO cnssDTO = cnssMapper.toDto(cnss);

        restCnssMockMvc.perform(post("/api/cnsses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cnssDTO)))
            .andExpect(status().isBadRequest());

        List<Cnss> cnssList = cnssRepository.findAll();
        assertThat(cnssList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrimestreIsRequired() throws Exception {
        int databaseSizeBeforeTest = cnssRepository.findAll().size();
        // set the field null
        cnss.setTrimestre(null);

        // Create the Cnss, which fails.
        CnssDTO cnssDTO = cnssMapper.toDto(cnss);

        restCnssMockMvc.perform(post("/api/cnsses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cnssDTO)))
            .andExpect(status().isBadRequest());

        List<Cnss> cnssList = cnssRepository.findAll();
        assertThat(cnssList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCnsses() throws Exception {
        // Initialize the database
        cnssRepository.saveAndFlush(cnss);

        // Get all the cnssList
        restCnssMockMvc.perform(get("/api/cnsses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cnss.getId().intValue())))
            .andExpect(jsonPath("$.[*].annee").value(hasItem(DEFAULT_ANNEE)))
            .andExpect(jsonPath("$.[*].trimestre").value(hasItem(DEFAULT_TRIMESTRE)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].numeroQuittance").value(hasItem(DEFAULT_NUMERO_QUITTANCE.toString())))
            .andExpect(jsonPath("$.[*].montantSalaireBrutNormal").value(hasItem(DEFAULT_MONTANT_SALAIRE_BRUT_NORMAL.intValue())))
            .andExpect(jsonPath("$.[*].montantSalaireBrutKarama").value(hasItem(DEFAULT_MONTANT_SALAIRE_BRUT_KARAMA.intValue())))
            .andExpect(jsonPath("$.[*].montantSalaireBrutAutre").value(hasItem(DEFAULT_MONTANT_SALAIRE_BRUT_AUTRE.intValue())))
            .andExpect(jsonPath("$.[*].montantChiffreAffaireTTC").value(hasItem(DEFAULT_MONTANT_CHIFFRE_AFFAIRE_TTC.intValue())))
            .andExpect(jsonPath("$.[*].tot").value(hasItem(DEFAULT_TOT.intValue())))
            .andExpect(jsonPath("$.[*].cnss").value(hasItem(DEFAULT_CNSS.intValue())));
    }
    
    @Test
    @Transactional
    public void getCnss() throws Exception {
        // Initialize the database
        cnssRepository.saveAndFlush(cnss);

        // Get the cnss
        restCnssMockMvc.perform(get("/api/cnsses/{id}", cnss.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cnss.getId().intValue()))
            .andExpect(jsonPath("$.annee").value(DEFAULT_ANNEE))
            .andExpect(jsonPath("$.trimestre").value(DEFAULT_TRIMESTRE))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.numeroQuittance").value(DEFAULT_NUMERO_QUITTANCE.toString()))
            .andExpect(jsonPath("$.montantSalaireBrutNormal").value(DEFAULT_MONTANT_SALAIRE_BRUT_NORMAL.intValue()))
            .andExpect(jsonPath("$.montantSalaireBrutKarama").value(DEFAULT_MONTANT_SALAIRE_BRUT_KARAMA.intValue()))
            .andExpect(jsonPath("$.montantSalaireBrutAutre").value(DEFAULT_MONTANT_SALAIRE_BRUT_AUTRE.intValue()))
            .andExpect(jsonPath("$.montantChiffreAffaireTTC").value(DEFAULT_MONTANT_CHIFFRE_AFFAIRE_TTC.intValue()))
            .andExpect(jsonPath("$.tot").value(DEFAULT_TOT.intValue()))
            .andExpect(jsonPath("$.cnss").value(DEFAULT_CNSS.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCnss() throws Exception {
        // Get the cnss
        restCnssMockMvc.perform(get("/api/cnsses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCnss() throws Exception {
        // Initialize the database
        cnssRepository.saveAndFlush(cnss);

        int databaseSizeBeforeUpdate = cnssRepository.findAll().size();

        // Update the cnss
        Cnss updatedCnss = cnssRepository.findById(cnss.getId()).get();
        // Disconnect from session so that the updates on updatedCnss are not directly saved in db
        em.detach(updatedCnss);
        updatedCnss
            .annee(UPDATED_ANNEE)
            .trimestre(UPDATED_TRIMESTRE)
            .date(UPDATED_DATE)
            .numeroQuittance(UPDATED_NUMERO_QUITTANCE)
            .montantSalaireBrutNormal(UPDATED_MONTANT_SALAIRE_BRUT_NORMAL)
            .montantSalaireBrutKarama(UPDATED_MONTANT_SALAIRE_BRUT_KARAMA)
            .montantSalaireBrutAutre(UPDATED_MONTANT_SALAIRE_BRUT_AUTRE)
            .montantChiffreAffaireTTC(UPDATED_MONTANT_CHIFFRE_AFFAIRE_TTC)
            .tot(UPDATED_TOT)
            .cnss(UPDATED_CNSS);
        CnssDTO cnssDTO = cnssMapper.toDto(updatedCnss);

        restCnssMockMvc.perform(put("/api/cnsses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cnssDTO)))
            .andExpect(status().isOk());

        // Validate the Cnss in the database
        List<Cnss> cnssList = cnssRepository.findAll();
        assertThat(cnssList).hasSize(databaseSizeBeforeUpdate);
        Cnss testCnss = cnssList.get(cnssList.size() - 1);
        assertThat(testCnss.getAnnee()).isEqualTo(UPDATED_ANNEE);
        assertThat(testCnss.getTrimestre()).isEqualTo(UPDATED_TRIMESTRE);
        assertThat(testCnss.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testCnss.getNumeroQuittance()).isEqualTo(UPDATED_NUMERO_QUITTANCE);
        assertThat(testCnss.getMontantSalaireBrutNormal()).isEqualTo(UPDATED_MONTANT_SALAIRE_BRUT_NORMAL);
        assertThat(testCnss.getMontantSalaireBrutKarama()).isEqualTo(UPDATED_MONTANT_SALAIRE_BRUT_KARAMA);
        assertThat(testCnss.getMontantSalaireBrutAutre()).isEqualTo(UPDATED_MONTANT_SALAIRE_BRUT_AUTRE);
        assertThat(testCnss.getMontantChiffreAffaireTTC()).isEqualTo(UPDATED_MONTANT_CHIFFRE_AFFAIRE_TTC);
        assertThat(testCnss.getTot()).isEqualTo(UPDATED_TOT);
        assertThat(testCnss.getCnss()).isEqualTo(UPDATED_CNSS);
    }

    @Test
    @Transactional
    public void updateNonExistingCnss() throws Exception {
        int databaseSizeBeforeUpdate = cnssRepository.findAll().size();

        // Create the Cnss
        CnssDTO cnssDTO = cnssMapper.toDto(cnss);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCnssMockMvc.perform(put("/api/cnsses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cnssDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cnss in the database
        List<Cnss> cnssList = cnssRepository.findAll();
        assertThat(cnssList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCnss() throws Exception {
        // Initialize the database
        cnssRepository.saveAndFlush(cnss);

        int databaseSizeBeforeDelete = cnssRepository.findAll().size();

        // Delete the cnss
        restCnssMockMvc.perform(delete("/api/cnsses/{id}", cnss.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Cnss> cnssList = cnssRepository.findAll();
        assertThat(cnssList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cnss.class);
        Cnss cnss1 = new Cnss();
        cnss1.setId(1L);
        Cnss cnss2 = new Cnss();
        cnss2.setId(cnss1.getId());
        assertThat(cnss1).isEqualTo(cnss2);
        cnss2.setId(2L);
        assertThat(cnss1).isNotEqualTo(cnss2);
        cnss1.setId(null);
        assertThat(cnss1).isNotEqualTo(cnss2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CnssDTO.class);
        CnssDTO cnssDTO1 = new CnssDTO();
        cnssDTO1.setId(1L);
        CnssDTO cnssDTO2 = new CnssDTO();
        assertThat(cnssDTO1).isNotEqualTo(cnssDTO2);
        cnssDTO2.setId(cnssDTO1.getId());
        assertThat(cnssDTO1).isEqualTo(cnssDTO2);
        cnssDTO2.setId(2L);
        assertThat(cnssDTO1).isNotEqualTo(cnssDTO2);
        cnssDTO1.setId(null);
        assertThat(cnssDTO1).isNotEqualTo(cnssDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(cnssMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(cnssMapper.fromId(null)).isNull();
    }
}
