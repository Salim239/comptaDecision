package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.ComptaDecisionApp;

import com.growup.comptadecision.domain.AcompteProvisionnel;
import com.growup.comptadecision.repository.AcompteProvisionnelRepository;
import com.growup.comptadecision.service.AcompteProvisionnelService;
import com.growup.comptadecision.service.dto.AcompteProvisionnelDTO;
import com.growup.comptadecision.service.mapper.AcompteProvisionnelMapper;
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
 * Test class for the AcompteProvisionnelResource REST controller.
 *
 * @see AcompteProvisionnelResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComptaDecisionApp.class)
public class AcompteProvisionnelResourceIntTest {

    private static final Integer DEFAULT_ANNEE = 1;
    private static final Integer UPDATED_ANNEE = 2;

    private static final Integer DEFAULT_NUMERO = 1;
    private static final Integer UPDATED_NUMERO = 2;

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NUMERO_QUITTANCE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_QUITTANCE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_MONTANT_BASE = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_BASE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_ACOMPTE_PROVISIONNEL = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_ACOMPTE_PROVISIONNEL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_REPORT_ANTERIEUR = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_REPORT_ANTERIEUR = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_RETENUE_SOURCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_RETENUE_SOURCE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_NET = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_NET = new BigDecimal(2);

    @Autowired
    private AcompteProvisionnelRepository acompteProvisionnelRepository;

    @Autowired
    private AcompteProvisionnelMapper acompteProvisionnelMapper;

    @Autowired
    private AcompteProvisionnelService acompteProvisionnelService;

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

    private MockMvc restAcompteProvisionnelMockMvc;

    private AcompteProvisionnel acompteProvisionnel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AcompteProvisionnelResource acompteProvisionnelResource = new AcompteProvisionnelResource(acompteProvisionnelService);
        this.restAcompteProvisionnelMockMvc = MockMvcBuilders.standaloneSetup(acompteProvisionnelResource)
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
    public static AcompteProvisionnel createEntity(EntityManager em) {
        AcompteProvisionnel acompteProvisionnel = new AcompteProvisionnel()
            .annee(DEFAULT_ANNEE)
            .numero(DEFAULT_NUMERO)
            .date(DEFAULT_DATE)
            .numeroQuittance(DEFAULT_NUMERO_QUITTANCE)
            .montantBase(DEFAULT_MONTANT_BASE)
            .montantAcompteProvisionnel(DEFAULT_MONTANT_ACOMPTE_PROVISIONNEL)
            .montantReportAnterieur(DEFAULT_MONTANT_REPORT_ANTERIEUR)
            .montantRetenueSource(DEFAULT_MONTANT_RETENUE_SOURCE)
            .montantNet(DEFAULT_MONTANT_NET);
        return acompteProvisionnel;
    }

    @Before
    public void initTest() {
        acompteProvisionnel = createEntity(em);
    }

    @Test
    @Transactional
    public void createAcompteProvisionnel() throws Exception {
        int databaseSizeBeforeCreate = acompteProvisionnelRepository.findAll().size();

        // Create the AcompteProvisionnel
        AcompteProvisionnelDTO acompteProvisionnelDTO = acompteProvisionnelMapper.toDto(acompteProvisionnel);
        restAcompteProvisionnelMockMvc.perform(post("/api/acompte-provisionnels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acompteProvisionnelDTO)))
            .andExpect(status().isCreated());

        // Validate the AcompteProvisionnel in the database
        List<AcompteProvisionnel> acompteProvisionnelList = acompteProvisionnelRepository.findAll();
        assertThat(acompteProvisionnelList).hasSize(databaseSizeBeforeCreate + 1);
        AcompteProvisionnel testAcompteProvisionnel = acompteProvisionnelList.get(acompteProvisionnelList.size() - 1);
        assertThat(testAcompteProvisionnel.getAnnee()).isEqualTo(DEFAULT_ANNEE);
        assertThat(testAcompteProvisionnel.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testAcompteProvisionnel.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testAcompteProvisionnel.getNumeroQuittance()).isEqualTo(DEFAULT_NUMERO_QUITTANCE);
        assertThat(testAcompteProvisionnel.getMontantBase()).isEqualTo(DEFAULT_MONTANT_BASE);
        assertThat(testAcompteProvisionnel.getMontantAcompteProvisionnel()).isEqualTo(DEFAULT_MONTANT_ACOMPTE_PROVISIONNEL);
        assertThat(testAcompteProvisionnel.getMontantReportAnterieur()).isEqualTo(DEFAULT_MONTANT_REPORT_ANTERIEUR);
        assertThat(testAcompteProvisionnel.getMontantRetenueSource()).isEqualTo(DEFAULT_MONTANT_RETENUE_SOURCE);
        assertThat(testAcompteProvisionnel.getMontantNet()).isEqualTo(DEFAULT_MONTANT_NET);
    }

    @Test
    @Transactional
    public void createAcompteProvisionnelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = acompteProvisionnelRepository.findAll().size();

        // Create the AcompteProvisionnel with an existing ID
        acompteProvisionnel.setId(1L);
        AcompteProvisionnelDTO acompteProvisionnelDTO = acompteProvisionnelMapper.toDto(acompteProvisionnel);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAcompteProvisionnelMockMvc.perform(post("/api/acompte-provisionnels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acompteProvisionnelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AcompteProvisionnel in the database
        List<AcompteProvisionnel> acompteProvisionnelList = acompteProvisionnelRepository.findAll();
        assertThat(acompteProvisionnelList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAnneeIsRequired() throws Exception {
        int databaseSizeBeforeTest = acompteProvisionnelRepository.findAll().size();
        // set the field null
        acompteProvisionnel.setAnnee(null);

        // Create the AcompteProvisionnel, which fails.
        AcompteProvisionnelDTO acompteProvisionnelDTO = acompteProvisionnelMapper.toDto(acompteProvisionnel);

        restAcompteProvisionnelMockMvc.perform(post("/api/acompte-provisionnels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acompteProvisionnelDTO)))
            .andExpect(status().isBadRequest());

        List<AcompteProvisionnel> acompteProvisionnelList = acompteProvisionnelRepository.findAll();
        assertThat(acompteProvisionnelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumeroIsRequired() throws Exception {
        int databaseSizeBeforeTest = acompteProvisionnelRepository.findAll().size();
        // set the field null
        acompteProvisionnel.setNumero(null);

        // Create the AcompteProvisionnel, which fails.
        AcompteProvisionnelDTO acompteProvisionnelDTO = acompteProvisionnelMapper.toDto(acompteProvisionnel);

        restAcompteProvisionnelMockMvc.perform(post("/api/acompte-provisionnels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acompteProvisionnelDTO)))
            .andExpect(status().isBadRequest());

        List<AcompteProvisionnel> acompteProvisionnelList = acompteProvisionnelRepository.findAll();
        assertThat(acompteProvisionnelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAcompteProvisionnels() throws Exception {
        // Initialize the database
        acompteProvisionnelRepository.saveAndFlush(acompteProvisionnel);

        // Get all the acompteProvisionnelList
        restAcompteProvisionnelMockMvc.perform(get("/api/acompte-provisionnels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(acompteProvisionnel.getId().intValue())))
            .andExpect(jsonPath("$.[*].annee").value(hasItem(DEFAULT_ANNEE)))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].numeroQuittance").value(hasItem(DEFAULT_NUMERO_QUITTANCE.toString())))
            .andExpect(jsonPath("$.[*].montantBase").value(hasItem(DEFAULT_MONTANT_BASE.intValue())))
            .andExpect(jsonPath("$.[*].montantAcompteProvisionnel").value(hasItem(DEFAULT_MONTANT_ACOMPTE_PROVISIONNEL.intValue())))
            .andExpect(jsonPath("$.[*].montantReportAnterieur").value(hasItem(DEFAULT_MONTANT_REPORT_ANTERIEUR.intValue())))
            .andExpect(jsonPath("$.[*].montantRetenueSource").value(hasItem(DEFAULT_MONTANT_RETENUE_SOURCE.intValue())))
            .andExpect(jsonPath("$.[*].montantNet").value(hasItem(DEFAULT_MONTANT_NET.intValue())));
    }
    
    @Test
    @Transactional
    public void getAcompteProvisionnel() throws Exception {
        // Initialize the database
        acompteProvisionnelRepository.saveAndFlush(acompteProvisionnel);

        // Get the acompteProvisionnel
        restAcompteProvisionnelMockMvc.perform(get("/api/acompte-provisionnels/{id}", acompteProvisionnel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(acompteProvisionnel.getId().intValue()))
            .andExpect(jsonPath("$.annee").value(DEFAULT_ANNEE))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.numeroQuittance").value(DEFAULT_NUMERO_QUITTANCE.toString()))
            .andExpect(jsonPath("$.montantBase").value(DEFAULT_MONTANT_BASE.intValue()))
            .andExpect(jsonPath("$.montantAcompteProvisionnel").value(DEFAULT_MONTANT_ACOMPTE_PROVISIONNEL.intValue()))
            .andExpect(jsonPath("$.montantReportAnterieur").value(DEFAULT_MONTANT_REPORT_ANTERIEUR.intValue()))
            .andExpect(jsonPath("$.montantRetenueSource").value(DEFAULT_MONTANT_RETENUE_SOURCE.intValue()))
            .andExpect(jsonPath("$.montantNet").value(DEFAULT_MONTANT_NET.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAcompteProvisionnel() throws Exception {
        // Get the acompteProvisionnel
        restAcompteProvisionnelMockMvc.perform(get("/api/acompte-provisionnels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAcompteProvisionnel() throws Exception {
        // Initialize the database
        acompteProvisionnelRepository.saveAndFlush(acompteProvisionnel);

        int databaseSizeBeforeUpdate = acompteProvisionnelRepository.findAll().size();

        // Update the acompteProvisionnel
        AcompteProvisionnel updatedAcompteProvisionnel = acompteProvisionnelRepository.findById(acompteProvisionnel.getId()).get();
        // Disconnect from session so that the updates on updatedAcompteProvisionnel are not directly saved in db
        em.detach(updatedAcompteProvisionnel);
        updatedAcompteProvisionnel
            .annee(UPDATED_ANNEE)
            .numero(UPDATED_NUMERO)
            .date(UPDATED_DATE)
            .numeroQuittance(UPDATED_NUMERO_QUITTANCE)
            .montantBase(UPDATED_MONTANT_BASE)
            .montantAcompteProvisionnel(UPDATED_MONTANT_ACOMPTE_PROVISIONNEL)
            .montantReportAnterieur(UPDATED_MONTANT_REPORT_ANTERIEUR)
            .montantRetenueSource(UPDATED_MONTANT_RETENUE_SOURCE)
            .montantNet(UPDATED_MONTANT_NET);
        AcompteProvisionnelDTO acompteProvisionnelDTO = acompteProvisionnelMapper.toDto(updatedAcompteProvisionnel);

        restAcompteProvisionnelMockMvc.perform(put("/api/acompte-provisionnels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acompteProvisionnelDTO)))
            .andExpect(status().isOk());

        // Validate the AcompteProvisionnel in the database
        List<AcompteProvisionnel> acompteProvisionnelList = acompteProvisionnelRepository.findAll();
        assertThat(acompteProvisionnelList).hasSize(databaseSizeBeforeUpdate);
        AcompteProvisionnel testAcompteProvisionnel = acompteProvisionnelList.get(acompteProvisionnelList.size() - 1);
        assertThat(testAcompteProvisionnel.getAnnee()).isEqualTo(UPDATED_ANNEE);
        assertThat(testAcompteProvisionnel.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testAcompteProvisionnel.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testAcompteProvisionnel.getNumeroQuittance()).isEqualTo(UPDATED_NUMERO_QUITTANCE);
        assertThat(testAcompteProvisionnel.getMontantBase()).isEqualTo(UPDATED_MONTANT_BASE);
        assertThat(testAcompteProvisionnel.getMontantAcompteProvisionnel()).isEqualTo(UPDATED_MONTANT_ACOMPTE_PROVISIONNEL);
        assertThat(testAcompteProvisionnel.getMontantReportAnterieur()).isEqualTo(UPDATED_MONTANT_REPORT_ANTERIEUR);
        assertThat(testAcompteProvisionnel.getMontantRetenueSource()).isEqualTo(UPDATED_MONTANT_RETENUE_SOURCE);
        assertThat(testAcompteProvisionnel.getMontantNet()).isEqualTo(UPDATED_MONTANT_NET);
    }

    @Test
    @Transactional
    public void updateNonExistingAcompteProvisionnel() throws Exception {
        int databaseSizeBeforeUpdate = acompteProvisionnelRepository.findAll().size();

        // Create the AcompteProvisionnel
        AcompteProvisionnelDTO acompteProvisionnelDTO = acompteProvisionnelMapper.toDto(acompteProvisionnel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAcompteProvisionnelMockMvc.perform(put("/api/acompte-provisionnels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acompteProvisionnelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AcompteProvisionnel in the database
        List<AcompteProvisionnel> acompteProvisionnelList = acompteProvisionnelRepository.findAll();
        assertThat(acompteProvisionnelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAcompteProvisionnel() throws Exception {
        // Initialize the database
        acompteProvisionnelRepository.saveAndFlush(acompteProvisionnel);

        int databaseSizeBeforeDelete = acompteProvisionnelRepository.findAll().size();

        // Delete the acompteProvisionnel
        restAcompteProvisionnelMockMvc.perform(delete("/api/acompte-provisionnels/{id}", acompteProvisionnel.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AcompteProvisionnel> acompteProvisionnelList = acompteProvisionnelRepository.findAll();
        assertThat(acompteProvisionnelList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AcompteProvisionnel.class);
        AcompteProvisionnel acompteProvisionnel1 = new AcompteProvisionnel();
        acompteProvisionnel1.setId(1L);
        AcompteProvisionnel acompteProvisionnel2 = new AcompteProvisionnel();
        acompteProvisionnel2.setId(acompteProvisionnel1.getId());
        assertThat(acompteProvisionnel1).isEqualTo(acompteProvisionnel2);
        acompteProvisionnel2.setId(2L);
        assertThat(acompteProvisionnel1).isNotEqualTo(acompteProvisionnel2);
        acompteProvisionnel1.setId(null);
        assertThat(acompteProvisionnel1).isNotEqualTo(acompteProvisionnel2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AcompteProvisionnelDTO.class);
        AcompteProvisionnelDTO acompteProvisionnelDTO1 = new AcompteProvisionnelDTO();
        acompteProvisionnelDTO1.setId(1L);
        AcompteProvisionnelDTO acompteProvisionnelDTO2 = new AcompteProvisionnelDTO();
        assertThat(acompteProvisionnelDTO1).isNotEqualTo(acompteProvisionnelDTO2);
        acompteProvisionnelDTO2.setId(acompteProvisionnelDTO1.getId());
        assertThat(acompteProvisionnelDTO1).isEqualTo(acompteProvisionnelDTO2);
        acompteProvisionnelDTO2.setId(2L);
        assertThat(acompteProvisionnelDTO1).isNotEqualTo(acompteProvisionnelDTO2);
        acompteProvisionnelDTO1.setId(null);
        assertThat(acompteProvisionnelDTO1).isNotEqualTo(acompteProvisionnelDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(acompteProvisionnelMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(acompteProvisionnelMapper.fromId(null)).isNull();
    }
}
