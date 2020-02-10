package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.ComptaDecisionApp;

import com.growup.comptadecision.domain.Ville;
import com.growup.comptadecision.repository.VilleRepository;
import com.growup.comptadecision.service.VilleService;
import com.growup.comptadecision.service.dto.VilleDTO;
import com.growup.comptadecision.service.mapper.VilleMapper;
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
import java.util.List;


import static com.growup.comptadecision.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the VilleResource REST controller.
 *
 * @see VilleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComptaDecisionApp.class)
public class VilleResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_POSTAL = "AAAAAAAAAA";
    private static final String UPDATED_CODE_POSTAL = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private VilleRepository villeRepository;

    @Autowired
    private VilleMapper villeMapper;

    @Autowired
    private VilleService villeService;

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

    private MockMvc restVilleMockMvc;

    private Ville ville;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VilleResource villeResource = new VilleResource(villeService);
        this.restVilleMockMvc = MockMvcBuilders.standaloneSetup(villeResource)
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
    public static Ville createEntity(EntityManager em) {
        Ville ville = new Ville()
            .code(DEFAULT_CODE)
            .codePostal(DEFAULT_CODE_POSTAL)
            .libelle(DEFAULT_LIBELLE)
            .description(DEFAULT_DESCRIPTION);
        return ville;
    }

    @Before
    public void initTest() {
        ville = createEntity(em);
    }

    @Test
    @Transactional
    public void createVille() throws Exception {
        int databaseSizeBeforeCreate = villeRepository.findAll().size();

        // Create the Ville
        VilleDTO villeDTO = villeMapper.toDto(ville);
        restVilleMockMvc.perform(post("/api/villes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(villeDTO)))
            .andExpect(status().isCreated());

        // Validate the Ville in the database
        List<Ville> villeList = villeRepository.findAll();
        assertThat(villeList).hasSize(databaseSizeBeforeCreate + 1);
        Ville testVille = villeList.get(villeList.size() - 1);
        assertThat(testVille.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testVille.getCodePostal()).isEqualTo(DEFAULT_CODE_POSTAL);
        assertThat(testVille.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testVille.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createVilleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = villeRepository.findAll().size();

        // Create the Ville with an existing ID
        ville.setId(1L);
        VilleDTO villeDTO = villeMapper.toDto(ville);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVilleMockMvc.perform(post("/api/villes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(villeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ville in the database
        List<Ville> villeList = villeRepository.findAll();
        assertThat(villeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = villeRepository.findAll().size();
        // set the field null
        ville.setCode(null);

        // Create the Ville, which fails.
        VilleDTO villeDTO = villeMapper.toDto(ville);

        restVilleMockMvc.perform(post("/api/villes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(villeDTO)))
            .andExpect(status().isBadRequest());

        List<Ville> villeList = villeRepository.findAll();
        assertThat(villeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = villeRepository.findAll().size();
        // set the field null
        ville.setLibelle(null);

        // Create the Ville, which fails.
        VilleDTO villeDTO = villeMapper.toDto(ville);

        restVilleMockMvc.perform(post("/api/villes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(villeDTO)))
            .andExpect(status().isBadRequest());

        List<Ville> villeList = villeRepository.findAll();
        assertThat(villeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVilles() throws Exception {
        // Initialize the database
        villeRepository.saveAndFlush(ville);

        // Get all the villeList
        restVilleMockMvc.perform(get("/api/villes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ville.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].codePostal").value(hasItem(DEFAULT_CODE_POSTAL.toString())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getVille() throws Exception {
        // Initialize the database
        villeRepository.saveAndFlush(ville);

        // Get the ville
        restVilleMockMvc.perform(get("/api/villes/{id}", ville.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ville.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.codePostal").value(DEFAULT_CODE_POSTAL.toString()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingVille() throws Exception {
        // Get the ville
        restVilleMockMvc.perform(get("/api/villes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVille() throws Exception {
        // Initialize the database
        villeRepository.saveAndFlush(ville);

        int databaseSizeBeforeUpdate = villeRepository.findAll().size();

        // Update the ville
        Ville updatedVille = villeRepository.findById(ville.getId()).get();
        // Disconnect from session so that the updates on updatedVille are not directly saved in db
        em.detach(updatedVille);
        updatedVille
            .code(UPDATED_CODE)
            .codePostal(UPDATED_CODE_POSTAL)
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION);
        VilleDTO villeDTO = villeMapper.toDto(updatedVille);

        restVilleMockMvc.perform(put("/api/villes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(villeDTO)))
            .andExpect(status().isOk());

        // Validate the Ville in the database
        List<Ville> villeList = villeRepository.findAll();
        assertThat(villeList).hasSize(databaseSizeBeforeUpdate);
        Ville testVille = villeList.get(villeList.size() - 1);
        assertThat(testVille.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testVille.getCodePostal()).isEqualTo(UPDATED_CODE_POSTAL);
        assertThat(testVille.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testVille.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingVille() throws Exception {
        int databaseSizeBeforeUpdate = villeRepository.findAll().size();

        // Create the Ville
        VilleDTO villeDTO = villeMapper.toDto(ville);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVilleMockMvc.perform(put("/api/villes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(villeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ville in the database
        List<Ville> villeList = villeRepository.findAll();
        assertThat(villeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVille() throws Exception {
        // Initialize the database
        villeRepository.saveAndFlush(ville);

        int databaseSizeBeforeDelete = villeRepository.findAll().size();

        // Delete the ville
        restVilleMockMvc.perform(delete("/api/villes/{id}", ville.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Ville> villeList = villeRepository.findAll();
        assertThat(villeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ville.class);
        Ville ville1 = new Ville();
        ville1.setId(1L);
        Ville ville2 = new Ville();
        ville2.setId(ville1.getId());
        assertThat(ville1).isEqualTo(ville2);
        ville2.setId(2L);
        assertThat(ville1).isNotEqualTo(ville2);
        ville1.setId(null);
        assertThat(ville1).isNotEqualTo(ville2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VilleDTO.class);
        VilleDTO villeDTO1 = new VilleDTO();
        villeDTO1.setId(1L);
        VilleDTO villeDTO2 = new VilleDTO();
        assertThat(villeDTO1).isNotEqualTo(villeDTO2);
        villeDTO2.setId(villeDTO1.getId());
        assertThat(villeDTO1).isEqualTo(villeDTO2);
        villeDTO2.setId(2L);
        assertThat(villeDTO1).isNotEqualTo(villeDTO2);
        villeDTO1.setId(null);
        assertThat(villeDTO1).isNotEqualTo(villeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(villeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(villeMapper.fromId(null)).isNull();
    }
}
