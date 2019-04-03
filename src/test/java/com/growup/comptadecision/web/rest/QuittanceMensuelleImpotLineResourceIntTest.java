package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.ComptaDecisionApp;

import com.growup.comptadecision.domain.QuittanceMensuelleImpotLine;
import com.growup.comptadecision.repository.QuittanceMensuelleImpotLineRepository;
import com.growup.comptadecision.service.QuittanceMensuelleImpotLineService;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotLineDTO;
import com.growup.comptadecision.service.mapper.QuittanceMensuelleImpotLineMapper;
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
 * Test class for the QuittanceMensuelleImpotLineResource REST controller.
 *
 * @see QuittanceMensuelleImpotLineResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComptaDecisionApp.class)
public class QuittanceMensuelleImpotLineResourceIntTest {

    private static final BigDecimal DEFAULT_MONTANT_PAYE = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_PAYE = new BigDecimal(2);

    @Autowired
    private QuittanceMensuelleImpotLineRepository quittanceMensuelleImpotLineRepository;

    @Autowired
    private QuittanceMensuelleImpotLineMapper quittanceMensuelleImpotLineMapper;

    @Autowired
    private QuittanceMensuelleImpotLineService quittanceMensuelleImpotLineService;

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

    private MockMvc restQuittanceMensuelleImpotLineMockMvc;

    private QuittanceMensuelleImpotLine quittanceMensuelleImpotLine;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QuittanceMensuelleImpotLineResource quittanceMensuelleImpotLineResource = new QuittanceMensuelleImpotLineResource(quittanceMensuelleImpotLineService);
        this.restQuittanceMensuelleImpotLineMockMvc = MockMvcBuilders.standaloneSetup(quittanceMensuelleImpotLineResource)
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
    public static QuittanceMensuelleImpotLine createEntity(EntityManager em) {
        QuittanceMensuelleImpotLine quittanceMensuelleImpotLine = new QuittanceMensuelleImpotLine()
            .montantPaye(DEFAULT_MONTANT_PAYE);
        return quittanceMensuelleImpotLine;
    }

    @Before
    public void initTest() {
        quittanceMensuelleImpotLine = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuittanceMensuelleImpotLine() throws Exception {
        int databaseSizeBeforeCreate = quittanceMensuelleImpotLineRepository.findAll().size();

        // Create the QuittanceMensuelleImpotLine
        QuittanceMensuelleImpotLineDTO quittanceMensuelleImpotLineDTO = quittanceMensuelleImpotLineMapper.toDto(quittanceMensuelleImpotLine);
        restQuittanceMensuelleImpotLineMockMvc.perform(post("/api/quittance-mensuelle-impot-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quittanceMensuelleImpotLineDTO)))
            .andExpect(status().isCreated());

        // Validate the QuittanceMensuelleImpotLine in the database
        List<QuittanceMensuelleImpotLine> quittanceMensuelleImpotLineList = quittanceMensuelleImpotLineRepository.findAll();
        assertThat(quittanceMensuelleImpotLineList).hasSize(databaseSizeBeforeCreate + 1);
        QuittanceMensuelleImpotLine testQuittanceMensuelleImpotLine = quittanceMensuelleImpotLineList.get(quittanceMensuelleImpotLineList.size() - 1);
        assertThat(testQuittanceMensuelleImpotLine.getMontantPaye()).isEqualTo(DEFAULT_MONTANT_PAYE);
    }

    @Test
    @Transactional
    public void createQuittanceMensuelleImpotLineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = quittanceMensuelleImpotLineRepository.findAll().size();

        // Create the QuittanceMensuelleImpotLine with an existing ID
        quittanceMensuelleImpotLine.setId(1L);
        QuittanceMensuelleImpotLineDTO quittanceMensuelleImpotLineDTO = quittanceMensuelleImpotLineMapper.toDto(quittanceMensuelleImpotLine);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuittanceMensuelleImpotLineMockMvc.perform(post("/api/quittance-mensuelle-impot-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quittanceMensuelleImpotLineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QuittanceMensuelleImpotLine in the database
        List<QuittanceMensuelleImpotLine> quittanceMensuelleImpotLineList = quittanceMensuelleImpotLineRepository.findAll();
        assertThat(quittanceMensuelleImpotLineList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllQuittanceMensuelleImpotLines() throws Exception {
        // Initialize the database
        quittanceMensuelleImpotLineRepository.saveAndFlush(quittanceMensuelleImpotLine);

        // Get all the quittanceMensuelleImpotLineList
        restQuittanceMensuelleImpotLineMockMvc.perform(get("/api/quittance-mensuelle-impot-lines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quittanceMensuelleImpotLine.getId().intValue())))
            .andExpect(jsonPath("$.[*].montantPaye").value(hasItem(DEFAULT_MONTANT_PAYE.intValue())));
    }
    
    @Test
    @Transactional
    public void getQuittanceMensuelleImpotLine() throws Exception {
        // Initialize the database
        quittanceMensuelleImpotLineRepository.saveAndFlush(quittanceMensuelleImpotLine);

        // Get the quittanceMensuelleImpotLine
        restQuittanceMensuelleImpotLineMockMvc.perform(get("/api/quittance-mensuelle-impot-lines/{id}", quittanceMensuelleImpotLine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(quittanceMensuelleImpotLine.getId().intValue()))
            .andExpect(jsonPath("$.montantPaye").value(DEFAULT_MONTANT_PAYE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingQuittanceMensuelleImpotLine() throws Exception {
        // Get the quittanceMensuelleImpotLine
        restQuittanceMensuelleImpotLineMockMvc.perform(get("/api/quittance-mensuelle-impot-lines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuittanceMensuelleImpotLine() throws Exception {
        // Initialize the database
        quittanceMensuelleImpotLineRepository.saveAndFlush(quittanceMensuelleImpotLine);

        int databaseSizeBeforeUpdate = quittanceMensuelleImpotLineRepository.findAll().size();

        // Update the quittanceMensuelleImpotLine
        QuittanceMensuelleImpotLine updatedQuittanceMensuelleImpotLine = quittanceMensuelleImpotLineRepository.findById(quittanceMensuelleImpotLine.getId()).get();
        // Disconnect from session so that the updates on updatedQuittanceMensuelleImpotLine are not directly saved in db
        em.detach(updatedQuittanceMensuelleImpotLine);
        updatedQuittanceMensuelleImpotLine
            .montantPaye(UPDATED_MONTANT_PAYE);
        QuittanceMensuelleImpotLineDTO quittanceMensuelleImpotLineDTO = quittanceMensuelleImpotLineMapper.toDto(updatedQuittanceMensuelleImpotLine);

        restQuittanceMensuelleImpotLineMockMvc.perform(put("/api/quittance-mensuelle-impot-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quittanceMensuelleImpotLineDTO)))
            .andExpect(status().isOk());

        // Validate the QuittanceMensuelleImpotLine in the database
        List<QuittanceMensuelleImpotLine> quittanceMensuelleImpotLineList = quittanceMensuelleImpotLineRepository.findAll();
        assertThat(quittanceMensuelleImpotLineList).hasSize(databaseSizeBeforeUpdate);
        QuittanceMensuelleImpotLine testQuittanceMensuelleImpotLine = quittanceMensuelleImpotLineList.get(quittanceMensuelleImpotLineList.size() - 1);
        assertThat(testQuittanceMensuelleImpotLine.getMontantPaye()).isEqualTo(UPDATED_MONTANT_PAYE);
    }

    @Test
    @Transactional
    public void updateNonExistingQuittanceMensuelleImpotLine() throws Exception {
        int databaseSizeBeforeUpdate = quittanceMensuelleImpotLineRepository.findAll().size();

        // Create the QuittanceMensuelleImpotLine
        QuittanceMensuelleImpotLineDTO quittanceMensuelleImpotLineDTO = quittanceMensuelleImpotLineMapper.toDto(quittanceMensuelleImpotLine);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuittanceMensuelleImpotLineMockMvc.perform(put("/api/quittance-mensuelle-impot-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quittanceMensuelleImpotLineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QuittanceMensuelleImpotLine in the database
        List<QuittanceMensuelleImpotLine> quittanceMensuelleImpotLineList = quittanceMensuelleImpotLineRepository.findAll();
        assertThat(quittanceMensuelleImpotLineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQuittanceMensuelleImpotLine() throws Exception {
        // Initialize the database
        quittanceMensuelleImpotLineRepository.saveAndFlush(quittanceMensuelleImpotLine);

        int databaseSizeBeforeDelete = quittanceMensuelleImpotLineRepository.findAll().size();

        // Delete the quittanceMensuelleImpotLine
        restQuittanceMensuelleImpotLineMockMvc.perform(delete("/api/quittance-mensuelle-impot-lines/{id}", quittanceMensuelleImpotLine.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<QuittanceMensuelleImpotLine> quittanceMensuelleImpotLineList = quittanceMensuelleImpotLineRepository.findAll();
        assertThat(quittanceMensuelleImpotLineList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuittanceMensuelleImpotLine.class);
        QuittanceMensuelleImpotLine quittanceMensuelleImpotLine1 = new QuittanceMensuelleImpotLine();
        quittanceMensuelleImpotLine1.setId(1L);
        QuittanceMensuelleImpotLine quittanceMensuelleImpotLine2 = new QuittanceMensuelleImpotLine();
        quittanceMensuelleImpotLine2.setId(quittanceMensuelleImpotLine1.getId());
        assertThat(quittanceMensuelleImpotLine1).isEqualTo(quittanceMensuelleImpotLine2);
        quittanceMensuelleImpotLine2.setId(2L);
        assertThat(quittanceMensuelleImpotLine1).isNotEqualTo(quittanceMensuelleImpotLine2);
        quittanceMensuelleImpotLine1.setId(null);
        assertThat(quittanceMensuelleImpotLine1).isNotEqualTo(quittanceMensuelleImpotLine2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuittanceMensuelleImpotLineDTO.class);
        QuittanceMensuelleImpotLineDTO quittanceMensuelleImpotLineDTO1 = new QuittanceMensuelleImpotLineDTO();
        quittanceMensuelleImpotLineDTO1.setId(1L);
        QuittanceMensuelleImpotLineDTO quittanceMensuelleImpotLineDTO2 = new QuittanceMensuelleImpotLineDTO();
        assertThat(quittanceMensuelleImpotLineDTO1).isNotEqualTo(quittanceMensuelleImpotLineDTO2);
        quittanceMensuelleImpotLineDTO2.setId(quittanceMensuelleImpotLineDTO1.getId());
        assertThat(quittanceMensuelleImpotLineDTO1).isEqualTo(quittanceMensuelleImpotLineDTO2);
        quittanceMensuelleImpotLineDTO2.setId(2L);
        assertThat(quittanceMensuelleImpotLineDTO1).isNotEqualTo(quittanceMensuelleImpotLineDTO2);
        quittanceMensuelleImpotLineDTO1.setId(null);
        assertThat(quittanceMensuelleImpotLineDTO1).isNotEqualTo(quittanceMensuelleImpotLineDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(quittanceMensuelleImpotLineMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(quittanceMensuelleImpotLineMapper.fromId(null)).isNull();
    }
}
