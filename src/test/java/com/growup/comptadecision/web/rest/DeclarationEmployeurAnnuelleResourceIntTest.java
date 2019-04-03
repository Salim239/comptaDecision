package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.ComptaDecisionApp;

import com.growup.comptadecision.domain.DeclarationEmployeurAnnuelle;
import com.growup.comptadecision.repository.DeclarationEmployeurAnnuelleRepository;
import com.growup.comptadecision.service.DeclarationEmployeurAnnuelleService;
import com.growup.comptadecision.service.dto.DeclarationEmployeurAnnuelleDTO;
import com.growup.comptadecision.service.mapper.DeclarationEmployeurAnnuelleMapper;
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
import java.util.List;


import static com.growup.comptadecision.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DeclarationEmployeurAnnuelleResource REST controller.
 *
 * @see DeclarationEmployeurAnnuelleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComptaDecisionApp.class)
public class DeclarationEmployeurAnnuelleResourceIntTest {

    private static final Integer DEFAULT_ANNEE = 1;
    private static final Integer UPDATED_ANNEE = 2;

    private static final BigDecimal DEFAULT_MONTANT_ANNEXE_1 = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_ANNEXE_1 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_ANNEXE_2 = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_ANNEXE_2 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_ANNEXE_3 = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_ANNEXE_3 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_ANNEXE_4 = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_ANNEXE_4 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_ANNEXE_5 = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_ANNEXE_5 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_ANNEXE_6 = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_ANNEXE_6 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_ANNEXE_7 = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_ANNEXE_7 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_ANNEXE_8 = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_ANNEXE_8 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_ANNEXE_9 = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_ANNEXE_9 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_ANNEXE_10 = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_ANNEXE_10 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_ANNEXE_11 = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_ANNEXE_11 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTANT_ANNEXE_12 = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_ANNEXE_12 = new BigDecimal(2);

    @Autowired
    private DeclarationEmployeurAnnuelleRepository declarationEmployeurAnnuelleRepository;

    @Autowired
    private DeclarationEmployeurAnnuelleMapper declarationEmployeurAnnuelleMapper;

    @Autowired
    private DeclarationEmployeurAnnuelleService declarationEmployeurAnnuelleService;

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

    private MockMvc restDeclarationEmployeurAnnuelleMockMvc;

    private DeclarationEmployeurAnnuelle declarationEmployeurAnnuelle;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DeclarationEmployeurAnnuelleResource declarationEmployeurAnnuelleResource = new DeclarationEmployeurAnnuelleResource(declarationEmployeurAnnuelleService);
        this.restDeclarationEmployeurAnnuelleMockMvc = MockMvcBuilders.standaloneSetup(declarationEmployeurAnnuelleResource)
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
    public static DeclarationEmployeurAnnuelle createEntity(EntityManager em) {
        DeclarationEmployeurAnnuelle declarationEmployeurAnnuelle = new DeclarationEmployeurAnnuelle()
            .annee(DEFAULT_ANNEE)
            .montantAnnexe1(DEFAULT_MONTANT_ANNEXE_1)
            .montantAnnexe2(DEFAULT_MONTANT_ANNEXE_2)
            .montantAnnexe3(DEFAULT_MONTANT_ANNEXE_3)
            .montantAnnexe4(DEFAULT_MONTANT_ANNEXE_4)
            .montantAnnexe5(DEFAULT_MONTANT_ANNEXE_5)
            .montantAnnexe6(DEFAULT_MONTANT_ANNEXE_6)
            .montantAnnexe7(DEFAULT_MONTANT_ANNEXE_7)
            .montantAnnexe8(DEFAULT_MONTANT_ANNEXE_8)
            .montantAnnexe9(DEFAULT_MONTANT_ANNEXE_9)
            .montantAnnexe10(DEFAULT_MONTANT_ANNEXE_10)
            .montantAnnexe11(DEFAULT_MONTANT_ANNEXE_11)
            .montantAnnexe12(DEFAULT_MONTANT_ANNEXE_12);
        return declarationEmployeurAnnuelle;
    }

    @Before
    public void initTest() {
        declarationEmployeurAnnuelle = createEntity(em);
    }

    @Test
    @Transactional
    public void createDeclarationEmployeurAnnuelle() throws Exception {
        int databaseSizeBeforeCreate = declarationEmployeurAnnuelleRepository.findAll().size();

        // Create the DeclarationEmployeurAnnuelle
        DeclarationEmployeurAnnuelleDTO declarationEmployeurAnnuelleDTO = declarationEmployeurAnnuelleMapper.toDto(declarationEmployeurAnnuelle);
        restDeclarationEmployeurAnnuelleMockMvc.perform(post("/api/declaration-employeur-annuelles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(declarationEmployeurAnnuelleDTO)))
            .andExpect(status().isCreated());

        // Validate the DeclarationEmployeurAnnuelle in the database
        List<DeclarationEmployeurAnnuelle> declarationEmployeurAnnuelleList = declarationEmployeurAnnuelleRepository.findAll();
        assertThat(declarationEmployeurAnnuelleList).hasSize(databaseSizeBeforeCreate + 1);
        DeclarationEmployeurAnnuelle testDeclarationEmployeurAnnuelle = declarationEmployeurAnnuelleList.get(declarationEmployeurAnnuelleList.size() - 1);
        assertThat(testDeclarationEmployeurAnnuelle.getAnnee()).isEqualTo(DEFAULT_ANNEE);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe1()).isEqualTo(DEFAULT_MONTANT_ANNEXE_1);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe2()).isEqualTo(DEFAULT_MONTANT_ANNEXE_2);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe3()).isEqualTo(DEFAULT_MONTANT_ANNEXE_3);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe4()).isEqualTo(DEFAULT_MONTANT_ANNEXE_4);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe5()).isEqualTo(DEFAULT_MONTANT_ANNEXE_5);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe6()).isEqualTo(DEFAULT_MONTANT_ANNEXE_6);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe7()).isEqualTo(DEFAULT_MONTANT_ANNEXE_7);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe8()).isEqualTo(DEFAULT_MONTANT_ANNEXE_8);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe9()).isEqualTo(DEFAULT_MONTANT_ANNEXE_9);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe10()).isEqualTo(DEFAULT_MONTANT_ANNEXE_10);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe11()).isEqualTo(DEFAULT_MONTANT_ANNEXE_11);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe12()).isEqualTo(DEFAULT_MONTANT_ANNEXE_12);
    }

    @Test
    @Transactional
    public void createDeclarationEmployeurAnnuelleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = declarationEmployeurAnnuelleRepository.findAll().size();

        // Create the DeclarationEmployeurAnnuelle with an existing ID
        declarationEmployeurAnnuelle.setId(1L);
        DeclarationEmployeurAnnuelleDTO declarationEmployeurAnnuelleDTO = declarationEmployeurAnnuelleMapper.toDto(declarationEmployeurAnnuelle);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeclarationEmployeurAnnuelleMockMvc.perform(post("/api/declaration-employeur-annuelles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(declarationEmployeurAnnuelleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DeclarationEmployeurAnnuelle in the database
        List<DeclarationEmployeurAnnuelle> declarationEmployeurAnnuelleList = declarationEmployeurAnnuelleRepository.findAll();
        assertThat(declarationEmployeurAnnuelleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAnneeIsRequired() throws Exception {
        int databaseSizeBeforeTest = declarationEmployeurAnnuelleRepository.findAll().size();
        // set the field null
        declarationEmployeurAnnuelle.setAnnee(null);

        // Create the DeclarationEmployeurAnnuelle, which fails.
        DeclarationEmployeurAnnuelleDTO declarationEmployeurAnnuelleDTO = declarationEmployeurAnnuelleMapper.toDto(declarationEmployeurAnnuelle);

        restDeclarationEmployeurAnnuelleMockMvc.perform(post("/api/declaration-employeur-annuelles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(declarationEmployeurAnnuelleDTO)))
            .andExpect(status().isBadRequest());

        List<DeclarationEmployeurAnnuelle> declarationEmployeurAnnuelleList = declarationEmployeurAnnuelleRepository.findAll();
        assertThat(declarationEmployeurAnnuelleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDeclarationEmployeurAnnuelles() throws Exception {
        // Initialize the database
        declarationEmployeurAnnuelleRepository.saveAndFlush(declarationEmployeurAnnuelle);

        // Get all the declarationEmployeurAnnuelleList
        restDeclarationEmployeurAnnuelleMockMvc.perform(get("/api/declaration-employeur-annuelles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(declarationEmployeurAnnuelle.getId().intValue())))
            .andExpect(jsonPath("$.[*].annee").value(hasItem(DEFAULT_ANNEE)))
            .andExpect(jsonPath("$.[*].montantAnnexe1").value(hasItem(DEFAULT_MONTANT_ANNEXE_1.intValue())))
            .andExpect(jsonPath("$.[*].montantAnnexe2").value(hasItem(DEFAULT_MONTANT_ANNEXE_2.intValue())))
            .andExpect(jsonPath("$.[*].montantAnnexe3").value(hasItem(DEFAULT_MONTANT_ANNEXE_3.intValue())))
            .andExpect(jsonPath("$.[*].montantAnnexe4").value(hasItem(DEFAULT_MONTANT_ANNEXE_4.intValue())))
            .andExpect(jsonPath("$.[*].montantAnnexe5").value(hasItem(DEFAULT_MONTANT_ANNEXE_5.intValue())))
            .andExpect(jsonPath("$.[*].montantAnnexe6").value(hasItem(DEFAULT_MONTANT_ANNEXE_6.intValue())))
            .andExpect(jsonPath("$.[*].montantAnnexe7").value(hasItem(DEFAULT_MONTANT_ANNEXE_7.intValue())))
            .andExpect(jsonPath("$.[*].montantAnnexe8").value(hasItem(DEFAULT_MONTANT_ANNEXE_8.intValue())))
            .andExpect(jsonPath("$.[*].montantAnnexe9").value(hasItem(DEFAULT_MONTANT_ANNEXE_9.intValue())))
            .andExpect(jsonPath("$.[*].montantAnnexe10").value(hasItem(DEFAULT_MONTANT_ANNEXE_10.intValue())))
            .andExpect(jsonPath("$.[*].montantAnnexe11").value(hasItem(DEFAULT_MONTANT_ANNEXE_11.intValue())))
            .andExpect(jsonPath("$.[*].montantAnnexe12").value(hasItem(DEFAULT_MONTANT_ANNEXE_12.intValue())));
    }
    
    @Test
    @Transactional
    public void getDeclarationEmployeurAnnuelle() throws Exception {
        // Initialize the database
        declarationEmployeurAnnuelleRepository.saveAndFlush(declarationEmployeurAnnuelle);

        // Get the declarationEmployeurAnnuelle
        restDeclarationEmployeurAnnuelleMockMvc.perform(get("/api/declaration-employeur-annuelles/{id}", declarationEmployeurAnnuelle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(declarationEmployeurAnnuelle.getId().intValue()))
            .andExpect(jsonPath("$.annee").value(DEFAULT_ANNEE))
            .andExpect(jsonPath("$.montantAnnexe1").value(DEFAULT_MONTANT_ANNEXE_1.intValue()))
            .andExpect(jsonPath("$.montantAnnexe2").value(DEFAULT_MONTANT_ANNEXE_2.intValue()))
            .andExpect(jsonPath("$.montantAnnexe3").value(DEFAULT_MONTANT_ANNEXE_3.intValue()))
            .andExpect(jsonPath("$.montantAnnexe4").value(DEFAULT_MONTANT_ANNEXE_4.intValue()))
            .andExpect(jsonPath("$.montantAnnexe5").value(DEFAULT_MONTANT_ANNEXE_5.intValue()))
            .andExpect(jsonPath("$.montantAnnexe6").value(DEFAULT_MONTANT_ANNEXE_6.intValue()))
            .andExpect(jsonPath("$.montantAnnexe7").value(DEFAULT_MONTANT_ANNEXE_7.intValue()))
            .andExpect(jsonPath("$.montantAnnexe8").value(DEFAULT_MONTANT_ANNEXE_8.intValue()))
            .andExpect(jsonPath("$.montantAnnexe9").value(DEFAULT_MONTANT_ANNEXE_9.intValue()))
            .andExpect(jsonPath("$.montantAnnexe10").value(DEFAULT_MONTANT_ANNEXE_10.intValue()))
            .andExpect(jsonPath("$.montantAnnexe11").value(DEFAULT_MONTANT_ANNEXE_11.intValue()))
            .andExpect(jsonPath("$.montantAnnexe12").value(DEFAULT_MONTANT_ANNEXE_12.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDeclarationEmployeurAnnuelle() throws Exception {
        // Get the declarationEmployeurAnnuelle
        restDeclarationEmployeurAnnuelleMockMvc.perform(get("/api/declaration-employeur-annuelles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDeclarationEmployeurAnnuelle() throws Exception {
        // Initialize the database
        declarationEmployeurAnnuelleRepository.saveAndFlush(declarationEmployeurAnnuelle);

        int databaseSizeBeforeUpdate = declarationEmployeurAnnuelleRepository.findAll().size();

        // Update the declarationEmployeurAnnuelle
        DeclarationEmployeurAnnuelle updatedDeclarationEmployeurAnnuelle = declarationEmployeurAnnuelleRepository.findById(declarationEmployeurAnnuelle.getId()).get();
        // Disconnect from session so that the updates on updatedDeclarationEmployeurAnnuelle are not directly saved in db
        em.detach(updatedDeclarationEmployeurAnnuelle);
        updatedDeclarationEmployeurAnnuelle
            .annee(UPDATED_ANNEE)
            .montantAnnexe1(UPDATED_MONTANT_ANNEXE_1)
            .montantAnnexe2(UPDATED_MONTANT_ANNEXE_2)
            .montantAnnexe3(UPDATED_MONTANT_ANNEXE_3)
            .montantAnnexe4(UPDATED_MONTANT_ANNEXE_4)
            .montantAnnexe5(UPDATED_MONTANT_ANNEXE_5)
            .montantAnnexe6(UPDATED_MONTANT_ANNEXE_6)
            .montantAnnexe7(UPDATED_MONTANT_ANNEXE_7)
            .montantAnnexe8(UPDATED_MONTANT_ANNEXE_8)
            .montantAnnexe9(UPDATED_MONTANT_ANNEXE_9)
            .montantAnnexe10(UPDATED_MONTANT_ANNEXE_10)
            .montantAnnexe11(UPDATED_MONTANT_ANNEXE_11)
            .montantAnnexe12(UPDATED_MONTANT_ANNEXE_12);
        DeclarationEmployeurAnnuelleDTO declarationEmployeurAnnuelleDTO = declarationEmployeurAnnuelleMapper.toDto(updatedDeclarationEmployeurAnnuelle);

        restDeclarationEmployeurAnnuelleMockMvc.perform(put("/api/declaration-employeur-annuelles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(declarationEmployeurAnnuelleDTO)))
            .andExpect(status().isOk());

        // Validate the DeclarationEmployeurAnnuelle in the database
        List<DeclarationEmployeurAnnuelle> declarationEmployeurAnnuelleList = declarationEmployeurAnnuelleRepository.findAll();
        assertThat(declarationEmployeurAnnuelleList).hasSize(databaseSizeBeforeUpdate);
        DeclarationEmployeurAnnuelle testDeclarationEmployeurAnnuelle = declarationEmployeurAnnuelleList.get(declarationEmployeurAnnuelleList.size() - 1);
        assertThat(testDeclarationEmployeurAnnuelle.getAnnee()).isEqualTo(UPDATED_ANNEE);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe1()).isEqualTo(UPDATED_MONTANT_ANNEXE_1);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe2()).isEqualTo(UPDATED_MONTANT_ANNEXE_2);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe3()).isEqualTo(UPDATED_MONTANT_ANNEXE_3);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe4()).isEqualTo(UPDATED_MONTANT_ANNEXE_4);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe5()).isEqualTo(UPDATED_MONTANT_ANNEXE_5);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe6()).isEqualTo(UPDATED_MONTANT_ANNEXE_6);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe7()).isEqualTo(UPDATED_MONTANT_ANNEXE_7);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe8()).isEqualTo(UPDATED_MONTANT_ANNEXE_8);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe9()).isEqualTo(UPDATED_MONTANT_ANNEXE_9);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe10()).isEqualTo(UPDATED_MONTANT_ANNEXE_10);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe11()).isEqualTo(UPDATED_MONTANT_ANNEXE_11);
        assertThat(testDeclarationEmployeurAnnuelle.getMontantAnnexe12()).isEqualTo(UPDATED_MONTANT_ANNEXE_12);
    }

    @Test
    @Transactional
    public void updateNonExistingDeclarationEmployeurAnnuelle() throws Exception {
        int databaseSizeBeforeUpdate = declarationEmployeurAnnuelleRepository.findAll().size();

        // Create the DeclarationEmployeurAnnuelle
        DeclarationEmployeurAnnuelleDTO declarationEmployeurAnnuelleDTO = declarationEmployeurAnnuelleMapper.toDto(declarationEmployeurAnnuelle);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeclarationEmployeurAnnuelleMockMvc.perform(put("/api/declaration-employeur-annuelles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(declarationEmployeurAnnuelleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DeclarationEmployeurAnnuelle in the database
        List<DeclarationEmployeurAnnuelle> declarationEmployeurAnnuelleList = declarationEmployeurAnnuelleRepository.findAll();
        assertThat(declarationEmployeurAnnuelleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDeclarationEmployeurAnnuelle() throws Exception {
        // Initialize the database
        declarationEmployeurAnnuelleRepository.saveAndFlush(declarationEmployeurAnnuelle);

        int databaseSizeBeforeDelete = declarationEmployeurAnnuelleRepository.findAll().size();

        // Delete the declarationEmployeurAnnuelle
        restDeclarationEmployeurAnnuelleMockMvc.perform(delete("/api/declaration-employeur-annuelles/{id}", declarationEmployeurAnnuelle.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DeclarationEmployeurAnnuelle> declarationEmployeurAnnuelleList = declarationEmployeurAnnuelleRepository.findAll();
        assertThat(declarationEmployeurAnnuelleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeclarationEmployeurAnnuelle.class);
        DeclarationEmployeurAnnuelle declarationEmployeurAnnuelle1 = new DeclarationEmployeurAnnuelle();
        declarationEmployeurAnnuelle1.setId(1L);
        DeclarationEmployeurAnnuelle declarationEmployeurAnnuelle2 = new DeclarationEmployeurAnnuelle();
        declarationEmployeurAnnuelle2.setId(declarationEmployeurAnnuelle1.getId());
        assertThat(declarationEmployeurAnnuelle1).isEqualTo(declarationEmployeurAnnuelle2);
        declarationEmployeurAnnuelle2.setId(2L);
        assertThat(declarationEmployeurAnnuelle1).isNotEqualTo(declarationEmployeurAnnuelle2);
        declarationEmployeurAnnuelle1.setId(null);
        assertThat(declarationEmployeurAnnuelle1).isNotEqualTo(declarationEmployeurAnnuelle2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeclarationEmployeurAnnuelleDTO.class);
        DeclarationEmployeurAnnuelleDTO declarationEmployeurAnnuelleDTO1 = new DeclarationEmployeurAnnuelleDTO();
        declarationEmployeurAnnuelleDTO1.setId(1L);
        DeclarationEmployeurAnnuelleDTO declarationEmployeurAnnuelleDTO2 = new DeclarationEmployeurAnnuelleDTO();
        assertThat(declarationEmployeurAnnuelleDTO1).isNotEqualTo(declarationEmployeurAnnuelleDTO2);
        declarationEmployeurAnnuelleDTO2.setId(declarationEmployeurAnnuelleDTO1.getId());
        assertThat(declarationEmployeurAnnuelleDTO1).isEqualTo(declarationEmployeurAnnuelleDTO2);
        declarationEmployeurAnnuelleDTO2.setId(2L);
        assertThat(declarationEmployeurAnnuelleDTO1).isNotEqualTo(declarationEmployeurAnnuelleDTO2);
        declarationEmployeurAnnuelleDTO1.setId(null);
        assertThat(declarationEmployeurAnnuelleDTO1).isNotEqualTo(declarationEmployeurAnnuelleDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(declarationEmployeurAnnuelleMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(declarationEmployeurAnnuelleMapper.fromId(null)).isNull();
    }
}
