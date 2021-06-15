package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.service.ApplicationUserFicheClientService;
import com.growup.comptadecision.service.dto.ApplicationUserFicheClientDTO;
import com.growup.comptadecision.web.rest.errors.BadRequestAlertException;
import com.growup.comptadecision.web.rest.util.HeaderUtil;
import com.growup.comptadecision.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ApplicationUserFicheClient.
 */
@RestController
@RequestMapping("/api")
public class ApplicationUserFicheClientResource {

    private final Logger log = LoggerFactory.getLogger(ApplicationUserFicheClientResource.class);

    private static final String ENTITY_NAME = "applicationUserFicheClient";

    private final ApplicationUserFicheClientService applicationUserFicheClientService;

    public ApplicationUserFicheClientResource(ApplicationUserFicheClientService applicationUserFicheClientService) {
        this.applicationUserFicheClientService = applicationUserFicheClientService;
    }

    /**
     * POST  /application-user-fiche-clients : Create a new applicationUserFicheClient.
     *
     * @param applicationUserFicheClientDTO the applicationUserFicheClientDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new applicationUserFicheClientDTO, or with status 400 (Bad Request) if the applicationUserFicheClient has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/application-user-fiche-clients")
    public ResponseEntity<ApplicationUserFicheClientDTO> createApplicationUserFicheClient(@Valid @RequestBody ApplicationUserFicheClientDTO applicationUserFicheClientDTO) throws URISyntaxException {
        log.debug("REST request to save ApplicationUserFicheClient : {}", applicationUserFicheClientDTO);
        if (applicationUserFicheClientDTO.getId() != null) {
            throw new BadRequestAlertException("A new applicationUserFicheClient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApplicationUserFicheClientDTO result = applicationUserFicheClientService.save(applicationUserFicheClientDTO);
        return ResponseEntity.created(new URI("/api/application-user-fiche-clients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /application-user-fiche-clients : Updates an existing applicationUserFicheClient.
     *
     * @param applicationUserFicheClientDTO the applicationUserFicheClientDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated applicationUserFicheClientDTO,
     * or with status 400 (Bad Request) if the applicationUserFicheClientDTO is not valid,
     * or with status 500 (Internal Server Error) if the applicationUserFicheClientDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/application-user-fiche-clients")
    public ResponseEntity<ApplicationUserFicheClientDTO> updateApplicationUserFicheClient(@Valid @RequestBody ApplicationUserFicheClientDTO applicationUserFicheClientDTO) throws URISyntaxException {
        log.debug("REST request to update ApplicationUserFicheClient : {}", applicationUserFicheClientDTO);
        if (applicationUserFicheClientDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApplicationUserFicheClientDTO result = applicationUserFicheClientService.save(applicationUserFicheClientDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, applicationUserFicheClientDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /application-user-fiche-clients : get all the applicationUserFicheClients.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of applicationUserFicheClients in body
     */
    @GetMapping("/application-user-fiche-clients")
    public ResponseEntity<List<ApplicationUserFicheClientDTO>> getAllApplicationUserFicheClients(Pageable pageable) {
        log.debug("REST request to get a page of ApplicationUserFicheClients");
        Page<ApplicationUserFicheClientDTO> page = applicationUserFicheClientService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/application-user-fiche-clients");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /application-user-fiche-clients/:id : get the "id" applicationUserFicheClient.
     *
     * @param id the id of the applicationUserFicheClientDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the applicationUserFicheClientDTO, or with status 404 (Not Found)
     */
    @GetMapping("/application-user-fiche-clients/{id}")
    public ResponseEntity<ApplicationUserFicheClientDTO> getApplicationUserFicheClient(@PathVariable Long id) {
        log.debug("REST request to get ApplicationUserFicheClient : {}", id);
        Optional<ApplicationUserFicheClientDTO> applicationUserFicheClientDTO = applicationUserFicheClientService.findOne(id);
        return ResponseUtil.wrapOrNotFound(applicationUserFicheClientDTO);
    }

    /**
     * DELETE  /application-user-fiche-clients/:id : delete the "id" applicationUserFicheClient.
     *
     * @param id the id of the applicationUserFicheClientDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/application-user-fiche-clients/{id}")
    public ResponseEntity<Void> deleteApplicationUserFicheClient(@PathVariable Long id) {
        log.debug("REST request to delete ApplicationUserFicheClient : {}", id);
        applicationUserFicheClientService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
