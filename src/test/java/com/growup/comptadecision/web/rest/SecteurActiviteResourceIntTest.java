package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.ComptaDecisionApp;

import com.growup.comptadecision.domain.SecteurActivite;
import com.growup.comptadecision.repository.SecteurActiviteRepository;
import com.growup.comptadecision.service.SecteurActiviteService;
import com.growup.comptadecision.service.dto.SecteurActiviteDTO;
import com.growup.comptadecision.service.mapper.SecteurActiviteMapper;
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
 * Test class for the SecteurActiviteResource REST controller.
 *
 * @see SecteurActiviteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComptaDecisionApp.class)
public class SecteurActiviteResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private SecteurActiviteRepository secteurActiviteRepository;

    @Autowired
    private SecteurActiviteMapper secteurActiviteMapper;

    @Autowired
    private SecteurActiviteService secteurActiviteService;

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

    private MockMvc restSecteurActiviteMockMvc;

    private SecteurActivite secteurActivite;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SecteurActiviteResource secteurActiviteResource = new SecteurActiviteResource(secteurActiviteService);
        this.restSecteurActiviteMockMvc = MockMvcBuilders.standaloneSetup(secteurActiviteResource)
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
    public static SecteurActivite createEntity(EntityManager em) {
        SecteurActivite secteurActivite = new SecteurActivite()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE)
            .description(DEFAULT_DESCRIPTION);
        return secteurActivite;
    }

    @Before
    public void initTest() {
        secteurActivite = createEntity(em);
    }

    @Test
    @Transactional
    public void createSecteurActivite() throws Exception {
        int databaseSizeBeforeCreate = secteurActiviteRepository.findAll().size();

        // Create the SecteurActivite
        SecteurActiviteDTO secteurActiviteDTO = secteurActiviteMapper.toDto(secteurActivite);
        restSecteurActiviteMockMvc.perform(post("/api/secteur-activites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(secteurActiviteDTO)))
            .andExpect(status().isCreated());

        // Validate the SecteurActivite in the database
        List<SecteurActivite> secteurActiviteList = secteurActiviteRepository.findAll();
        assertThat(secteurActiviteList).hasSize(databaseSizeBeforeCreate + 1);
        SecteurActivite testSecteurActivite = secteurActiviteList.get(secteurActiviteList.size() - 1);
        assertThat(testSecteurActivite.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testSecteurActivite.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testSecteurActivite.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createSecteurActiviteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = secteurActiviteRepository.findAll().size();

        // Create the SecteurActivite with an existing ID
        secteurActivite.setId(1L);
        SecteurActiviteDTO secteurActiviteDTO = secteurActiviteMapper.toDto(secteurActivite);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSecteurActiviteMockMvc.perform(post("/api/secteur-activites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(secteurActiviteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SecteurActivite in the database
        List<SecteurActivite> secteurActiviteList = secteurActiviteRepository.findAll();
        assertThat(secteurActiviteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = secteurActiviteRepository.findAll().size();
        // set the field null
        secteurActivite.setCode(null);

        // Create the SecteurActivite, which fails.
        SecteurActiviteDTO secteurActiviteDTO = secteurActiviteMapper.toDto(secteurActivite);

        restSecteurActiviteMockMvc.perform(post("/api/secteur-activites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(secteurActiviteDTO)))
            .andExpect(status().isBadRequest());

        List<SecteurActivite> secteurActiviteList = secteurActiviteRepository.findAll();
        assertThat(secteurActiviteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = secteurActiviteRepository.findAll().size();
        // set the field null
        secteurActivite.setLibelle(null);

        // Create the SecteurActivite, which fails.
        SecteurActiviteDTO secteurActiviteDTO = secteurActiviteMapper.toDto(secteurActivite);

        restSecteurActiviteMockMvc.perform(post("/api/secteur-activites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(secteurActiviteDTO)))
            .andExpect(status().isBadRequest());

        List<SecteurActivite> secteurActiviteList = secteurActiviteRepository.findAll();
        assertThat(secteurActiviteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSecteurActivites() throws Exception {
        // Initialize the database
        secteurActiviteRepository.saveAndFlush(secteurActivite);

        // Get all the secteurActiviteList
        restSecteurActiviteMockMvc.perform(get("/api/secteur-activites?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(secteurActivite.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getSecteurActivite() throws Exception {
        // Initialize the database
        secteurActiviteRepository.saveAndFlush(secteurActivite);

        // Get the secteurActivite
        restSecteurActiviteMockMvc.perform(get("/api/secteur-activites/{id}", secteurActivite.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(secteurActivite.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSecteurActivite() throws Exception {
        // Get the secteurActivite
        restSecteurActiviteMockMvc.perform(get("/api/secteur-activites/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSecteurActivite() throws Exception {
        // Initialize the database
        secteurActiviteRepository.saveAndFlush(secteurActivite);

        int databaseSizeBeforeUpdate = secteurActiviteRepository.findAll().size();

        // Update the secteurActivite
        SecteurActivite updatedSecteurActivite = secteurActiviteRepository.findById(secteurActivite.getId()).get();
        // Disconnect from session so that the updates on updatedSecteurActivite are not directly saved in db
        em.detach(updatedSecteurActivite);
        updatedSecteurActivite
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION);
        SecteurActiviteDTO secteurActiviteDTO = secteurActiviteMapper.toDto(updatedSecteurActivite);

        restSecteurActiviteMockMvc.perform(put("/api/secteur-activites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(secteurActiviteDTO)))
            .andExpect(status().isOk());

        // Validate the SecteurActivite in the database
        List<SecteurActivite> secteurActiviteList = secteurActiviteRepository.findAll();
        assertThat(secteurActiviteList).hasSize(databaseSizeBeforeUpdate);
        SecteurActivite testSecteurActivite = secteurActiviteList.get(secteurActiviteList.size() - 1);
        assertThat(testSecteurActivite.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testSecteurActivite.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testSecteurActivite.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingSecteurActivite() throws Exception {
        int databaseSizeBeforeUpdate = secteurActiviteRepository.findAll().size();

        // Create the SecteurActivite
        SecteurActiviteDTO secteurActiviteDTO = secteurActiviteMapper.toDto(secteurActivite);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSecteurActiviteMockMvc.perform(put("/api/secteur-activites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(secteurActiviteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SecteurActivite in the database
        List<SecteurActivite> secteurActiviteList = secteurActiviteRepository.findAll();
        assertThat(secteurActiviteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSecteurActivite() throws Exception {
        // Initialize the database
        secteurActiviteRepository.saveAndFlush(secteurActivite);

        int databaseSizeBeforeDelete = secteurActiviteRepository.findAll().size();

        // Delete the secteurActivite
        restSecteurActiviteMockMvc.perform(delete("/api/secteur-activites/{id}", secteurActivite.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SecteurActivite> secteurActiviteList = secteurActiviteRepository.findAll();
        assertThat(secteurActiviteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SecteurActivite.class);
        SecteurActivite secteurActivite1 = new SecteurActivite();
        secteurActivite1.setId(1L);
        SecteurActivite secteurActivite2 = new SecteurActivite();
        secteurActivite2.setId(secteurActivite1.getId());
        assertThat(secteurActivite1).isEqualTo(secteurActivite2);
        secteurActivite2.setId(2L);
        assertThat(secteurActivite1).isNotEqualTo(secteurActivite2);
        secteurActivite1.setId(null);
        assertThat(secteurActivite1).isNotEqualTo(secteurActivite2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SecteurActiviteDTO.class);
        SecteurActiviteDTO secteurActiviteDTO1 = new SecteurActiviteDTO();
        secteurActiviteDTO1.setId(1L);
        SecteurActiviteDTO secteurActiviteDTO2 = new SecteurActiviteDTO();
        assertThat(secteurActiviteDTO1).isNotEqualTo(secteurActiviteDTO2);
        secteurActiviteDTO2.setId(secteurActiviteDTO1.getId());
        assertThat(secteurActiviteDTO1).isEqualTo(secteurActiviteDTO2);
        secteurActiviteDTO2.setId(2L);
        assertThat(secteurActiviteDTO1).isNotEqualTo(secteurActiviteDTO2);
        secteurActiviteDTO1.setId(null);
        assertThat(secteurActiviteDTO1).isNotEqualTo(secteurActiviteDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(secteurActiviteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(secteurActiviteMapper.fromId(null)).isNull();
    }
}
