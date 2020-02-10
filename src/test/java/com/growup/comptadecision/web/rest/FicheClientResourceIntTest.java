package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.ComptaDecisionApp;

import com.growup.comptadecision.domain.FicheClient;
import com.growup.comptadecision.repository.FicheClientRepository;
import com.growup.comptadecision.service.FicheClientService;
import com.growup.comptadecision.service.dto.FicheClientDTO;
import com.growup.comptadecision.service.mapper.FicheClientMapper;
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
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static com.growup.comptadecision.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.growup.comptadecision.domain.enumeration.CategorieClient;
/**
 * Test class for the FicheClientResource REST controller.
 *
 * @see FicheClientResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComptaDecisionApp.class)
public class FicheClientResourceIntTest {

    private static final CategorieClient DEFAULT_CATEGORIE_CLIENT = CategorieClient.PERSONNE_PHYSIQUE;
    private static final CategorieClient UPDATED_CATEGORIE_CLIENT = CategorieClient.PERSONNE_MORALE;

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_LOGO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_LOGO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_LOGO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_LOGO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_POSTAL = "AAAAAAAAAA";
    private static final String UPDATED_CODE_POSTAL = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_MATRICULE_FISCALE = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULE_FISCALE = "BBBBBBBBBB";

    private static final String DEFAULT_REGISTRE_COMMERCE = "AAAAAAAAAA";
    private static final String UPDATED_REGISTRE_COMMERCE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_CREATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_CREATION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CNSS_EMPLOYEUR = "AAAAAAAAAA";
    private static final String UPDATED_CNSS_EMPLOYEUR = "BBBBBBBBBB";

    private static final String DEFAULT_CNSS_GERANT = "AAAAAAAAAA";
    private static final String UPDATED_CNSS_GERANT = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FICHIER_PATENTE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FICHIER_PATENTE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FICHIER_PATENTE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FICHIER_PATENTE_CONTENT_TYPE = "image/png";

    @Autowired
    private FicheClientRepository ficheClientRepository;

    @Autowired
    private FicheClientMapper ficheClientMapper;

    @Autowired
    private FicheClientService ficheClientService;

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

    private MockMvc restFicheClientMockMvc;

    private FicheClient ficheClient;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FicheClientResource ficheClientResource = new FicheClientResource(ficheClientService);
        this.restFicheClientMockMvc = MockMvcBuilders.standaloneSetup(ficheClientResource)
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
    public static FicheClient createEntity(EntityManager em) {
        FicheClient ficheClient = new FicheClient()
            .categorieClient(DEFAULT_CATEGORIE_CLIENT)
            .designation(DEFAULT_DESIGNATION)
            .logo(DEFAULT_LOGO)
            .logoContentType(DEFAULT_LOGO_CONTENT_TYPE)
            .adresse(DEFAULT_ADRESSE)
            .codePostal(DEFAULT_CODE_POSTAL)
            .telephone(DEFAULT_TELEPHONE)
            .fax(DEFAULT_FAX)
            .email(DEFAULT_EMAIL)
            .matriculeFiscale(DEFAULT_MATRICULE_FISCALE)
            .registreCommerce(DEFAULT_REGISTRE_COMMERCE)
            .dateCreation(DEFAULT_DATE_CREATION)
            .cnssEmployeur(DEFAULT_CNSS_EMPLOYEUR)
            .cnssGerant(DEFAULT_CNSS_GERANT)
            .fichierPatente(DEFAULT_FICHIER_PATENTE)
            .fichierPatenteContentType(DEFAULT_FICHIER_PATENTE_CONTENT_TYPE);
        return ficheClient;
    }

    @Before
    public void initTest() {
        ficheClient = createEntity(em);
    }

    @Test
    @Transactional
    public void createFicheClient() throws Exception {
        int databaseSizeBeforeCreate = ficheClientRepository.findAll().size();

        // Create the FicheClient
        FicheClientDTO ficheClientDTO = ficheClientMapper.toDto(ficheClient);
        restFicheClientMockMvc.perform(post("/api/fiche-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ficheClientDTO)))
            .andExpect(status().isCreated());

        // Validate the FicheClient in the database
        List<FicheClient> ficheClientList = ficheClientRepository.findAll();
        assertThat(ficheClientList).hasSize(databaseSizeBeforeCreate + 1);
        FicheClient testFicheClient = ficheClientList.get(ficheClientList.size() - 1);
        assertThat(testFicheClient.getCategorieClient()).isEqualTo(DEFAULT_CATEGORIE_CLIENT);
        assertThat(testFicheClient.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testFicheClient.getLogo()).isEqualTo(DEFAULT_LOGO);
        assertThat(testFicheClient.getLogoContentType()).isEqualTo(DEFAULT_LOGO_CONTENT_TYPE);
        assertThat(testFicheClient.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testFicheClient.getCodePostal()).isEqualTo(DEFAULT_CODE_POSTAL);
        assertThat(testFicheClient.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testFicheClient.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testFicheClient.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testFicheClient.getMatriculeFiscale()).isEqualTo(DEFAULT_MATRICULE_FISCALE);
        assertThat(testFicheClient.getRegistreCommerce()).isEqualTo(DEFAULT_REGISTRE_COMMERCE);
        assertThat(testFicheClient.getDateCreation()).isEqualTo(DEFAULT_DATE_CREATION);
        assertThat(testFicheClient.getCnssEmployeur()).isEqualTo(DEFAULT_CNSS_EMPLOYEUR);
        assertThat(testFicheClient.getCnssGerant()).isEqualTo(DEFAULT_CNSS_GERANT);
        assertThat(testFicheClient.getFichierPatente()).isEqualTo(DEFAULT_FICHIER_PATENTE);
        assertThat(testFicheClient.getFichierPatenteContentType()).isEqualTo(DEFAULT_FICHIER_PATENTE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createFicheClientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ficheClientRepository.findAll().size();

        // Create the FicheClient with an existing ID
        ficheClient.setId(1L);
        FicheClientDTO ficheClientDTO = ficheClientMapper.toDto(ficheClient);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFicheClientMockMvc.perform(post("/api/fiche-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ficheClientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FicheClient in the database
        List<FicheClient> ficheClientList = ficheClientRepository.findAll();
        assertThat(ficheClientList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDesignationIsRequired() throws Exception {
        int databaseSizeBeforeTest = ficheClientRepository.findAll().size();
        // set the field null
        ficheClient.setDesignation(null);

        // Create the FicheClient, which fails.
        FicheClientDTO ficheClientDTO = ficheClientMapper.toDto(ficheClient);

        restFicheClientMockMvc.perform(post("/api/fiche-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ficheClientDTO)))
            .andExpect(status().isBadRequest());

        List<FicheClient> ficheClientList = ficheClientRepository.findAll();
        assertThat(ficheClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAdresseIsRequired() throws Exception {
        int databaseSizeBeforeTest = ficheClientRepository.findAll().size();
        // set the field null
        ficheClient.setAdresse(null);

        // Create the FicheClient, which fails.
        FicheClientDTO ficheClientDTO = ficheClientMapper.toDto(ficheClient);

        restFicheClientMockMvc.perform(post("/api/fiche-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ficheClientDTO)))
            .andExpect(status().isBadRequest());

        List<FicheClient> ficheClientList = ficheClientRepository.findAll();
        assertThat(ficheClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodePostalIsRequired() throws Exception {
        int databaseSizeBeforeTest = ficheClientRepository.findAll().size();
        // set the field null
        ficheClient.setCodePostal(null);

        // Create the FicheClient, which fails.
        FicheClientDTO ficheClientDTO = ficheClientMapper.toDto(ficheClient);

        restFicheClientMockMvc.perform(post("/api/fiche-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ficheClientDTO)))
            .andExpect(status().isBadRequest());

        List<FicheClient> ficheClientList = ficheClientRepository.findAll();
        assertThat(ficheClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMatriculeFiscaleIsRequired() throws Exception {
        int databaseSizeBeforeTest = ficheClientRepository.findAll().size();
        // set the field null
        ficheClient.setMatriculeFiscale(null);

        // Create the FicheClient, which fails.
        FicheClientDTO ficheClientDTO = ficheClientMapper.toDto(ficheClient);

        restFicheClientMockMvc.perform(post("/api/fiche-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ficheClientDTO)))
            .andExpect(status().isBadRequest());

        List<FicheClient> ficheClientList = ficheClientRepository.findAll();
        assertThat(ficheClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFicheClients() throws Exception {
        // Initialize the database
        ficheClientRepository.saveAndFlush(ficheClient);

        // Get all the ficheClientList
        restFicheClientMockMvc.perform(get("/api/fiche-clients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ficheClient.getId().intValue())))
            .andExpect(jsonPath("$.[*].categorieClient").value(hasItem(DEFAULT_CATEGORIE_CLIENT.toString())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())))
            .andExpect(jsonPath("$.[*].logoContentType").value(hasItem(DEFAULT_LOGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].logo").value(hasItem(Base64Utils.encodeToString(DEFAULT_LOGO))))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE.toString())))
            .andExpect(jsonPath("$.[*].codePostal").value(hasItem(DEFAULT_CODE_POSTAL.toString())))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE.toString())))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].matriculeFiscale").value(hasItem(DEFAULT_MATRICULE_FISCALE.toString())))
            .andExpect(jsonPath("$.[*].registreCommerce").value(hasItem(DEFAULT_REGISTRE_COMMERCE.toString())))
            .andExpect(jsonPath("$.[*].dateCreation").value(hasItem(DEFAULT_DATE_CREATION.toString())))
            .andExpect(jsonPath("$.[*].cnssEmployeur").value(hasItem(DEFAULT_CNSS_EMPLOYEUR.toString())))
            .andExpect(jsonPath("$.[*].cnssGerant").value(hasItem(DEFAULT_CNSS_GERANT.toString())))
            .andExpect(jsonPath("$.[*].fichierPatenteContentType").value(hasItem(DEFAULT_FICHIER_PATENTE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fichierPatente").value(hasItem(Base64Utils.encodeToString(DEFAULT_FICHIER_PATENTE))));
    }
    
    @Test
    @Transactional
    public void getFicheClient() throws Exception {
        // Initialize the database
        ficheClientRepository.saveAndFlush(ficheClient);

        // Get the ficheClient
        restFicheClientMockMvc.perform(get("/api/fiche-clients/{id}", ficheClient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ficheClient.getId().intValue()))
            .andExpect(jsonPath("$.categorieClient").value(DEFAULT_CATEGORIE_CLIENT.toString()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()))
            .andExpect(jsonPath("$.logoContentType").value(DEFAULT_LOGO_CONTENT_TYPE))
            .andExpect(jsonPath("$.logo").value(Base64Utils.encodeToString(DEFAULT_LOGO)))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE.toString()))
            .andExpect(jsonPath("$.codePostal").value(DEFAULT_CODE_POSTAL.toString()))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE.toString()))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.matriculeFiscale").value(DEFAULT_MATRICULE_FISCALE.toString()))
            .andExpect(jsonPath("$.registreCommerce").value(DEFAULT_REGISTRE_COMMERCE.toString()))
            .andExpect(jsonPath("$.dateCreation").value(DEFAULT_DATE_CREATION.toString()))
            .andExpect(jsonPath("$.cnssEmployeur").value(DEFAULT_CNSS_EMPLOYEUR.toString()))
            .andExpect(jsonPath("$.cnssGerant").value(DEFAULT_CNSS_GERANT.toString()))
            .andExpect(jsonPath("$.fichierPatenteContentType").value(DEFAULT_FICHIER_PATENTE_CONTENT_TYPE))
            .andExpect(jsonPath("$.fichierPatente").value(Base64Utils.encodeToString(DEFAULT_FICHIER_PATENTE)));
    }

    @Test
    @Transactional
    public void getNonExistingFicheClient() throws Exception {
        // Get the ficheClient
        restFicheClientMockMvc.perform(get("/api/fiche-clients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFicheClient() throws Exception {
        // Initialize the database
        ficheClientRepository.saveAndFlush(ficheClient);

        int databaseSizeBeforeUpdate = ficheClientRepository.findAll().size();

        // Update the ficheClient
        FicheClient updatedFicheClient = ficheClientRepository.findById(ficheClient.getId()).get();
        // Disconnect from session so that the updates on updatedFicheClient are not directly saved in db
        em.detach(updatedFicheClient);
        updatedFicheClient
            .categorieClient(UPDATED_CATEGORIE_CLIENT)
            .designation(UPDATED_DESIGNATION)
            .logo(UPDATED_LOGO)
            .logoContentType(UPDATED_LOGO_CONTENT_TYPE)
            .adresse(UPDATED_ADRESSE)
            .codePostal(UPDATED_CODE_POSTAL)
            .telephone(UPDATED_TELEPHONE)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL)
            .matriculeFiscale(UPDATED_MATRICULE_FISCALE)
            .registreCommerce(UPDATED_REGISTRE_COMMERCE)
            .dateCreation(UPDATED_DATE_CREATION)
            .cnssEmployeur(UPDATED_CNSS_EMPLOYEUR)
            .cnssGerant(UPDATED_CNSS_GERANT)
            .fichierPatente(UPDATED_FICHIER_PATENTE)
            .fichierPatenteContentType(UPDATED_FICHIER_PATENTE_CONTENT_TYPE);
        FicheClientDTO ficheClientDTO = ficheClientMapper.toDto(updatedFicheClient);

        restFicheClientMockMvc.perform(put("/api/fiche-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ficheClientDTO)))
            .andExpect(status().isOk());

        // Validate the FicheClient in the database
        List<FicheClient> ficheClientList = ficheClientRepository.findAll();
        assertThat(ficheClientList).hasSize(databaseSizeBeforeUpdate);
        FicheClient testFicheClient = ficheClientList.get(ficheClientList.size() - 1);
        assertThat(testFicheClient.getCategorieClient()).isEqualTo(UPDATED_CATEGORIE_CLIENT);
        assertThat(testFicheClient.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testFicheClient.getLogo()).isEqualTo(UPDATED_LOGO);
        assertThat(testFicheClient.getLogoContentType()).isEqualTo(UPDATED_LOGO_CONTENT_TYPE);
        assertThat(testFicheClient.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testFicheClient.getCodePostal()).isEqualTo(UPDATED_CODE_POSTAL);
        assertThat(testFicheClient.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testFicheClient.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testFicheClient.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testFicheClient.getMatriculeFiscale()).isEqualTo(UPDATED_MATRICULE_FISCALE);
        assertThat(testFicheClient.getRegistreCommerce()).isEqualTo(UPDATED_REGISTRE_COMMERCE);
        assertThat(testFicheClient.getDateCreation()).isEqualTo(UPDATED_DATE_CREATION);
        assertThat(testFicheClient.getCnssEmployeur()).isEqualTo(UPDATED_CNSS_EMPLOYEUR);
        assertThat(testFicheClient.getCnssGerant()).isEqualTo(UPDATED_CNSS_GERANT);
        assertThat(testFicheClient.getFichierPatente()).isEqualTo(UPDATED_FICHIER_PATENTE);
        assertThat(testFicheClient.getFichierPatenteContentType()).isEqualTo(UPDATED_FICHIER_PATENTE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingFicheClient() throws Exception {
        int databaseSizeBeforeUpdate = ficheClientRepository.findAll().size();

        // Create the FicheClient
        FicheClientDTO ficheClientDTO = ficheClientMapper.toDto(ficheClient);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFicheClientMockMvc.perform(put("/api/fiche-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ficheClientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FicheClient in the database
        List<FicheClient> ficheClientList = ficheClientRepository.findAll();
        assertThat(ficheClientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFicheClient() throws Exception {
        // Initialize the database
        ficheClientRepository.saveAndFlush(ficheClient);

        int databaseSizeBeforeDelete = ficheClientRepository.findAll().size();

        // Delete the ficheClient
        restFicheClientMockMvc.perform(delete("/api/fiche-clients/{id}", ficheClient.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FicheClient> ficheClientList = ficheClientRepository.findAll();
        assertThat(ficheClientList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FicheClient.class);
        FicheClient ficheClient1 = new FicheClient();
        ficheClient1.setId(1L);
        FicheClient ficheClient2 = new FicheClient();
        ficheClient2.setId(ficheClient1.getId());
        assertThat(ficheClient1).isEqualTo(ficheClient2);
        ficheClient2.setId(2L);
        assertThat(ficheClient1).isNotEqualTo(ficheClient2);
        ficheClient1.setId(null);
        assertThat(ficheClient1).isNotEqualTo(ficheClient2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FicheClientDTO.class);
        FicheClientDTO ficheClientDTO1 = new FicheClientDTO();
        ficheClientDTO1.setId(1L);
        FicheClientDTO ficheClientDTO2 = new FicheClientDTO();
        assertThat(ficheClientDTO1).isNotEqualTo(ficheClientDTO2);
        ficheClientDTO2.setId(ficheClientDTO1.getId());
        assertThat(ficheClientDTO1).isEqualTo(ficheClientDTO2);
        ficheClientDTO2.setId(2L);
        assertThat(ficheClientDTO1).isNotEqualTo(ficheClientDTO2);
        ficheClientDTO1.setId(null);
        assertThat(ficheClientDTO1).isNotEqualTo(ficheClientDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(ficheClientMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(ficheClientMapper.fromId(null)).isNull();
    }
}
