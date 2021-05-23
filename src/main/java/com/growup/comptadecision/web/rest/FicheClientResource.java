package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.service.FicheClientService;
import com.growup.comptadecision.service.dto.FicheClientDTO;
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
 * REST controller for managing FicheClient.
 */
@RestController
@RequestMapping("/api")
public class FicheClientResource {

    private final Logger log = LoggerFactory.getLogger(FicheClientResource.class);

    private static final String ENTITY_NAME = "ficheClient";

    private final FicheClientService ficheClientService;

    public FicheClientResource(FicheClientService ficheClientService) {
        this.ficheClientService = ficheClientService;
    }

    @GetMapping("/fiche-clients/init")
    public ResponseEntity<FicheClientDTO> init() {
        log.debug("REST request to init empty FicheClient");
        FicheClientDTO ficheClientDTO = ficheClientService.init();
        return ResponseEntity.ok().headers(HeaderUtil.initEmptyEntityAlert(ENTITY_NAME, null)).body(ficheClientDTO);
    }

    /**
     * POST  /fiche-clients : Create a new ficheClient.
     *
     * @param ficheClientDTO the ficheClientDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ficheClientDTO, or with status 400 (Bad Request) if the ficheClient has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fiche-clients")
    public ResponseEntity<FicheClientDTO> createFicheClient(@Valid @RequestBody FicheClientDTO ficheClientDTO) throws URISyntaxException {
        log.debug("REST request to save FicheClient : {}", ficheClientDTO);
        if (ficheClientDTO.getId() != null) {
            throw new BadRequestAlertException("A new ficheClient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FicheClientDTO result = ficheClientService.save(ficheClientDTO);
        return ResponseEntity.created(new URI("/api/fiche-clients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fiche-clients : Updates an existing ficheClient.
     *
     * @param ficheClientDTO the ficheClientDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ficheClientDTO,
     * or with status 400 (Bad Request) if the ficheClientDTO is not valid,
     * or with status 500 (Internal Server Error) if the ficheClientDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fiche-clients")
    public ResponseEntity<FicheClientDTO> updateFicheClient(@Valid @RequestBody FicheClientDTO ficheClientDTO) throws URISyntaxException {
        log.debug("REST request to update FicheClient : {}", ficheClientDTO);
        if (ficheClientDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FicheClientDTO result = ficheClientService.save(ficheClientDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ficheClientDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fiche-clients : get all the ficheClients.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of ficheClients in body
     */
    @GetMapping("/fiche-clients")
    public ResponseEntity<List<FicheClientDTO>> getAllFicheClients(Pageable pageable) {
        log.debug("REST request to get a page of FicheClients");
        Page<FicheClientDTO> page = ficheClientService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fiche-clients");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /fiche-clients/all : get all the ficheClients.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of ficheClients in body
     */
    @GetMapping("/fiche-clients/all")
    public ResponseEntity<List<FicheClientDTO>> getAllFicheClients() {
        log.debug("REST request to all FicheClients");
        return ResponseEntity.ok().body(ficheClientService.findAll());
    }

    /**
     * GET  /fiche-clients/:id : get the "id" ficheClient.
     *
     * @param id the id of the ficheClientDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ficheClientDTO, or with status 404 (Not Found)
     */
    @GetMapping("/fiche-clients/{id}")
    public ResponseEntity<FicheClientDTO> getFicheClient(@PathVariable Long id) {
        log.debug("REST request to get FicheClient : {}", id);
        Optional<FicheClientDTO> ficheClientDTO = ficheClientService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ficheClientDTO);
    }

    /**
     * DELETE  /fiche-clients/:id : delete the "id" ficheClient.
     *
     * @param id the id of the ficheClientDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fiche-clients/{id}")
    public ResponseEntity<Void> deleteFicheClient(@PathVariable Long id) {
        log.debug("REST request to delete FicheClient : {}", id);
        ficheClientService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
