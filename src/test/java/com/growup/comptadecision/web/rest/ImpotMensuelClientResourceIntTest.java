package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.ComptaDecisionApp;
import com.growup.comptadecision.domain.ImpotMensuelClient;
import com.growup.comptadecision.repository.ImpotMensuelClientRepository;
import com.growup.comptadecision.service.ImpotMensuelClientService;
import com.growup.comptadecision.service.dto.ImpotMensuelClientDTO;
import com.growup.comptadecision.service.mapper.ImpotMensuelClientMapper;
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
 * Test class for the ImpotMensuelClientResource REST controller.
 *
 * @see ImpotMensuelClientResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComptaDecisionApp.class)
public class ImpotMensuelClientResourceIntTest {

    private static final Integer DEFAULT_MOIS = 1;
    private static final Integer UPDATED_MOIS = 2;

    private static final Boolean DEFAULT_APPLICABLE = false;
    private static final Boolean UPDATED_APPLICABLE = true;

    @Autowired
    private ImpotMensuelClientRepository impotMensuelClientRepository;

    @Autowired
    private ImpotMensuelClientMapper impotMensuelClientMapper;

    @Autowired
    private ImpotMensuelClientService impotMensuelClientService;

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

    private MockMvc restImpotMensuelClientMockMvc;

    private ImpotMensuelClient impotMensuelClient;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ImpotMensuelClientResource impotMensuelClientResource = new ImpotMensuelClientResource(impotMensuelClientService);
        this.restImpotMensuelClientMockMvc = MockMvcBuilders.standaloneSetup(impotMensuelClientResource)
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
    public static ImpotMensuelClient createEntity(EntityManager em) {
        ImpotMensuelClient impotMensuelClient = new ImpotMensuelClient()
            .applicable(DEFAULT_APPLICABLE);
        return impotMensuelClient;
    }

    @Before
    public void initTest() {
        impotMensuelClient = createEntity(em);
    }

    @Test
    @Transactional
    public void createImpotMensuelClient() throws Exception {
        int databaseSizeBeforeCreate = impotMensuelClientRepository.findAll().size();

        // Create the ImpotMensuelClient
        ImpotMensuelClientDTO impotMensuelClientDTO = impotMensuelClientMapper.toDto(impotMensuelClient);
        restImpotMensuelClientMockMvc.perform(post("/api/impot-mensuel-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(impotMensuelClientDTO)))
            .andExpect(status().isCreated());

        // Validate the ImpotMensuelClient in the database
        List<ImpotMensuelClient> impotMensuelClientList = impotMensuelClientRepository.findAll();
        assertThat(impotMensuelClientList).hasSize(databaseSizeBeforeCreate + 1);
        ImpotMensuelClient testImpotMensuelClient = impotMensuelClientList.get(impotMensuelClientList.size() - 1);
        assertThat(testImpotMensuelClient.isApplicable()).isEqualTo(DEFAULT_APPLICABLE);
    }

    @Test
    @Transactional
    public void createImpotMensuelClientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = impotMensuelClientRepository.findAll().size();

        // Create the ImpotMensuelClient with an existing ID
        impotMensuelClient.setId(1L);
        ImpotMensuelClientDTO impotMensuelClientDTO = impotMensuelClientMapper.toDto(impotMensuelClient);

        // An entity with an existing ID cannot be created, so this API call must fail
        restImpotMensuelClientMockMvc.perform(post("/api/impot-mensuel-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(impotMensuelClientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ImpotMensuelClient in the database
        List<ImpotMensuelClient> impotMensuelClientList = impotMensuelClientRepository.findAll();
        assertThat(impotMensuelClientList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkMoisIsRequired() throws Exception {
        int databaseSizeBeforeTest = impotMensuelClientRepository.findAll().size();

        // Create the ImpotMensuelClient, which fails.
        ImpotMensuelClientDTO impotMensuelClientDTO = impotMensuelClientMapper.toDto(impotMensuelClient);

        restImpotMensuelClientMockMvc.perform(post("/api/impot-mensuel-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(impotMensuelClientDTO)))
            .andExpect(status().isBadRequest());

        List<ImpotMensuelClient> impotMensuelClientList = impotMensuelClientRepository.findAll();
        assertThat(impotMensuelClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllImpotMensuelClients() throws Exception {
        // Initialize the database
        impotMensuelClientRepository.saveAndFlush(impotMensuelClient);

        // Get all the impotMensuelClientList
        restImpotMensuelClientMockMvc.perform(get("/api/impot-mensuel-clients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(impotMensuelClient.getId().intValue())))
            .andExpect(jsonPath("$.[*].mois").value(hasItem(DEFAULT_MOIS)))
            .andExpect(jsonPath("$.[*].applicable").value(hasItem(DEFAULT_APPLICABLE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getImpotMensuelClient() throws Exception {
        // Initialize the database
        impotMensuelClientRepository.saveAndFlush(impotMensuelClient);

        // Get the impotMensuelClient
        restImpotMensuelClientMockMvc.perform(get("/api/impot-mensuel-clients/{id}", impotMensuelClient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(impotMensuelClient.getId().intValue()))
            .andExpect(jsonPath("$.mois").value(DEFAULT_MOIS))
            .andExpect(jsonPath("$.applicable").value(DEFAULT_APPLICABLE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingImpotMensuelClient() throws Exception {
        // Get the impotMensuelClient
        restImpotMensuelClientMockMvc.perform(get("/api/impot-mensuel-clients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateImpotMensuelClient() throws Exception {
        // Initialize the database
        impotMensuelClientRepository.saveAndFlush(impotMensuelClient);

        int databaseSizeBeforeUpdate = impotMensuelClientRepository.findAll().size();

        // Update the impotMensuelClient
        ImpotMensuelClient updatedImpotMensuelClient = impotMensuelClientRepository.findById(impotMensuelClient.getId()).get();
        // Disconnect from session so that the updates on updatedImpotMensuelClient are not directly saved in db
        em.detach(updatedImpotMensuelClient);
        updatedImpotMensuelClient
            .applicable(UPDATED_APPLICABLE);
        ImpotMensuelClientDTO impotMensuelClientDTO = impotMensuelClientMapper.toDto(updatedImpotMensuelClient);

        restImpotMensuelClientMockMvc.perform(put("/api/impot-mensuel-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(impotMensuelClientDTO)))
            .andExpect(status().isOk());

        // Validate the ImpotMensuelClient in the database
        List<ImpotMensuelClient> impotMensuelClientList = impotMensuelClientRepository.findAll();
        assertThat(impotMensuelClientList).hasSize(databaseSizeBeforeUpdate);
        ImpotMensuelClient testImpotMensuelClient = impotMensuelClientList.get(impotMensuelClientList.size() - 1);
        assertThat(testImpotMensuelClient.isApplicable()).isEqualTo(UPDATED_APPLICABLE);
    }

    @Test
    @Transactional
    public void updateNonExistingImpotMensuelClient() throws Exception {
        int databaseSizeBeforeUpdate = impotMensuelClientRepository.findAll().size();

        // Create the ImpotMensuelClient
        ImpotMensuelClientDTO impotMensuelClientDTO = impotMensuelClientMapper.toDto(impotMensuelClient);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImpotMensuelClientMockMvc.perform(put("/api/impot-mensuel-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(impotMensuelClientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ImpotMensuelClient in the database
        List<ImpotMensuelClient> impotMensuelClientList = impotMensuelClientRepository.findAll();
        assertThat(impotMensuelClientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteImpotMensuelClient() throws Exception {
        // Initialize the database
        impotMensuelClientRepository.saveAndFlush(impotMensuelClient);

        int databaseSizeBeforeDelete = impotMensuelClientRepository.findAll().size();

        // Delete the impotMensuelClient
        restImpotMensuelClientMockMvc.perform(delete("/api/impot-mensuel-clients/{id}", impotMensuelClient.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ImpotMensuelClient> impotMensuelClientList = impotMensuelClientRepository.findAll();
        assertThat(impotMensuelClientList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImpotMensuelClient.class);
        ImpotMensuelClient impotMensuelClient1 = new ImpotMensuelClient();
        impotMensuelClient1.setId(1L);
        ImpotMensuelClient impotMensuelClient2 = new ImpotMensuelClient();
        impotMensuelClient2.setId(impotMensuelClient1.getId());
        assertThat(impotMensuelClient1).isEqualTo(impotMensuelClient2);
        impotMensuelClient2.setId(2L);
        assertThat(impotMensuelClient1).isNotEqualTo(impotMensuelClient2);
        impotMensuelClient1.setId(null);
        assertThat(impotMensuelClient1).isNotEqualTo(impotMensuelClient2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImpotMensuelClientDTO.class);
        ImpotMensuelClientDTO impotMensuelClientDTO1 = new ImpotMensuelClientDTO();
        impotMensuelClientDTO1.setId(1L);
        ImpotMensuelClientDTO impotMensuelClientDTO2 = new ImpotMensuelClientDTO();
        assertThat(impotMensuelClientDTO1).isNotEqualTo(impotMensuelClientDTO2);
        impotMensuelClientDTO2.setId(impotMensuelClientDTO1.getId());
        assertThat(impotMensuelClientDTO1).isEqualTo(impotMensuelClientDTO2);
        impotMensuelClientDTO2.setId(2L);
        assertThat(impotMensuelClientDTO1).isNotEqualTo(impotMensuelClientDTO2);
        impotMensuelClientDTO1.setId(null);
        assertThat(impotMensuelClientDTO1).isNotEqualTo(impotMensuelClientDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(impotMensuelClientMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(impotMensuelClientMapper.fromId(null)).isNull();
    }
}
