package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.ComptaDecisionApp;

import com.growup.comptadecision.domain.ImpotMensuel;
import com.growup.comptadecision.repository.ImpotMensuelRepository;
import com.growup.comptadecision.service.ImpotMensuelService;
import com.growup.comptadecision.service.dto.ImpotMensuelDTO;
import com.growup.comptadecision.service.mapper.ImpotMensuelMapper;
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
 * Test class for the ImpotMensuelResource REST controller.
 *
 * @see ImpotMensuelResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComptaDecisionApp.class)
public class ImpotMensuelResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private ImpotMensuelRepository impotMensuelRepository;

    @Autowired
    private ImpotMensuelMapper impotMensuelMapper;

    @Autowired
    private ImpotMensuelService impotMensuelService;

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

    private MockMvc restImpotMensuelMockMvc;

    private ImpotMensuel impotMensuel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ImpotMensuelResource impotMensuelResource = new ImpotMensuelResource(impotMensuelService);
        this.restImpotMensuelMockMvc = MockMvcBuilders.standaloneSetup(impotMensuelResource)
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
    public static ImpotMensuel createEntity(EntityManager em) {
        ImpotMensuel impotMensuel = new ImpotMensuel()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE)
            .description(DEFAULT_DESCRIPTION);
        return impotMensuel;
    }

    @Before
    public void initTest() {
        impotMensuel = createEntity(em);
    }

    @Test
    @Transactional
    public void createImpotMensuel() throws Exception {
        int databaseSizeBeforeCreate = impotMensuelRepository.findAll().size();

        // Create the ImpotMensuel
        ImpotMensuelDTO impotMensuelDTO = impotMensuelMapper.toDto(impotMensuel);
        restImpotMensuelMockMvc.perform(post("/api/impot-mensuels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(impotMensuelDTO)))
            .andExpect(status().isCreated());

        // Validate the ImpotMensuel in the database
        List<ImpotMensuel> impotMensuelList = impotMensuelRepository.findAll();
        assertThat(impotMensuelList).hasSize(databaseSizeBeforeCreate + 1);
        ImpotMensuel testImpotMensuel = impotMensuelList.get(impotMensuelList.size() - 1);
        assertThat(testImpotMensuel.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testImpotMensuel.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testImpotMensuel.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createImpotMensuelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = impotMensuelRepository.findAll().size();

        // Create the ImpotMensuel with an existing ID
        impotMensuel.setId(1L);
        ImpotMensuelDTO impotMensuelDTO = impotMensuelMapper.toDto(impotMensuel);

        // An entity with an existing ID cannot be created, so this API call must fail
        restImpotMensuelMockMvc.perform(post("/api/impot-mensuels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(impotMensuelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ImpotMensuel in the database
        List<ImpotMensuel> impotMensuelList = impotMensuelRepository.findAll();
        assertThat(impotMensuelList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = impotMensuelRepository.findAll().size();
        // set the field null
        impotMensuel.setCode(null);

        // Create the ImpotMensuel, which fails.
        ImpotMensuelDTO impotMensuelDTO = impotMensuelMapper.toDto(impotMensuel);

        restImpotMensuelMockMvc.perform(post("/api/impot-mensuels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(impotMensuelDTO)))
            .andExpect(status().isBadRequest());

        List<ImpotMensuel> impotMensuelList = impotMensuelRepository.findAll();
        assertThat(impotMensuelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = impotMensuelRepository.findAll().size();
        // set the field null
        impotMensuel.setLibelle(null);

        // Create the ImpotMensuel, which fails.
        ImpotMensuelDTO impotMensuelDTO = impotMensuelMapper.toDto(impotMensuel);

        restImpotMensuelMockMvc.perform(post("/api/impot-mensuels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(impotMensuelDTO)))
            .andExpect(status().isBadRequest());

        List<ImpotMensuel> impotMensuelList = impotMensuelRepository.findAll();
        assertThat(impotMensuelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllImpotMensuels() throws Exception {
        // Initialize the database
        impotMensuelRepository.saveAndFlush(impotMensuel);

        // Get all the impotMensuelList
        restImpotMensuelMockMvc.perform(get("/api/impot-mensuels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(impotMensuel.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getImpotMensuel() throws Exception {
        // Initialize the database
        impotMensuelRepository.saveAndFlush(impotMensuel);

        // Get the impotMensuel
        restImpotMensuelMockMvc.perform(get("/api/impot-mensuels/{id}", impotMensuel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(impotMensuel.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingImpotMensuel() throws Exception {
        // Get the impotMensuel
        restImpotMensuelMockMvc.perform(get("/api/impot-mensuels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateImpotMensuel() throws Exception {
        // Initialize the database
        impotMensuelRepository.saveAndFlush(impotMensuel);

        int databaseSizeBeforeUpdate = impotMensuelRepository.findAll().size();

        // Update the impotMensuel
        ImpotMensuel updatedImpotMensuel = impotMensuelRepository.findById(impotMensuel.getId()).get();
        // Disconnect from session so that the updates on updatedImpotMensuel are not directly saved in db
        em.detach(updatedImpotMensuel);
        updatedImpotMensuel
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION);
        ImpotMensuelDTO impotMensuelDTO = impotMensuelMapper.toDto(updatedImpotMensuel);

        restImpotMensuelMockMvc.perform(put("/api/impot-mensuels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(impotMensuelDTO)))
            .andExpect(status().isOk());

        // Validate the ImpotMensuel in the database
        List<ImpotMensuel> impotMensuelList = impotMensuelRepository.findAll();
        assertThat(impotMensuelList).hasSize(databaseSizeBeforeUpdate);
        ImpotMensuel testImpotMensuel = impotMensuelList.get(impotMensuelList.size() - 1);
        assertThat(testImpotMensuel.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testImpotMensuel.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testImpotMensuel.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingImpotMensuel() throws Exception {
        int databaseSizeBeforeUpdate = impotMensuelRepository.findAll().size();

        // Create the ImpotMensuel
        ImpotMensuelDTO impotMensuelDTO = impotMensuelMapper.toDto(impotMensuel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImpotMensuelMockMvc.perform(put("/api/impot-mensuels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(impotMensuelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ImpotMensuel in the database
        List<ImpotMensuel> impotMensuelList = impotMensuelRepository.findAll();
        assertThat(impotMensuelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteImpotMensuel() throws Exception {
        // Initialize the database
        impotMensuelRepository.saveAndFlush(impotMensuel);

        int databaseSizeBeforeDelete = impotMensuelRepository.findAll().size();

        // Delete the impotMensuel
        restImpotMensuelMockMvc.perform(delete("/api/impot-mensuels/{id}", impotMensuel.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ImpotMensuel> impotMensuelList = impotMensuelRepository.findAll();
        assertThat(impotMensuelList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImpotMensuel.class);
        ImpotMensuel impotMensuel1 = new ImpotMensuel();
        impotMensuel1.setId(1L);
        ImpotMensuel impotMensuel2 = new ImpotMensuel();
        impotMensuel2.setId(impotMensuel1.getId());
        assertThat(impotMensuel1).isEqualTo(impotMensuel2);
        impotMensuel2.setId(2L);
        assertThat(impotMensuel1).isNotEqualTo(impotMensuel2);
        impotMensuel1.setId(null);
        assertThat(impotMensuel1).isNotEqualTo(impotMensuel2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImpotMensuelDTO.class);
        ImpotMensuelDTO impotMensuelDTO1 = new ImpotMensuelDTO();
        impotMensuelDTO1.setId(1L);
        ImpotMensuelDTO impotMensuelDTO2 = new ImpotMensuelDTO();
        assertThat(impotMensuelDTO1).isNotEqualTo(impotMensuelDTO2);
        impotMensuelDTO2.setId(impotMensuelDTO1.getId());
        assertThat(impotMensuelDTO1).isEqualTo(impotMensuelDTO2);
        impotMensuelDTO2.setId(2L);
        assertThat(impotMensuelDTO1).isNotEqualTo(impotMensuelDTO2);
        impotMensuelDTO1.setId(null);
        assertThat(impotMensuelDTO1).isNotEqualTo(impotMensuelDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(impotMensuelMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(impotMensuelMapper.fromId(null)).isNull();
    }
}
