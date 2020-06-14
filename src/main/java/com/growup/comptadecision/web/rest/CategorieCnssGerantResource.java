package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.service.CategorieCnssGerantService;
import com.growup.comptadecision.service.dto.CategorieCnssGerantDTO;
import com.growup.comptadecision.web.rest.errors.BadRequestAlertException;
import com.growup.comptadecision.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CategorieCnssGerant.
 */
@RestController
@RequestMapping("/api")
public class CategorieCnssGerantResource {

    private final Logger log = LoggerFactory.getLogger(CategorieCnssGerantResource.class);

    private static final String ENTITY_NAME = "categorieCnssGerant";

    private final CategorieCnssGerantService categorieCnssGerantService;

    public CategorieCnssGerantResource(CategorieCnssGerantService categorieCnssGerantService) {
        this.categorieCnssGerantService = categorieCnssGerantService;
    }

    /**
     * POST  /categorie-cnss-gerants : Create a new categorieCnssGerant.
     *
     * @param categorieCnssGerantDTO the categorieCnssGerantDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new categorieCnssGerantDTO, or with status 400 (Bad Request) if the categorieCnssGerant has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/categorie-cnss-gerants")
    public ResponseEntity<CategorieCnssGerantDTO> createCategorieCnssGerant(@Valid @RequestBody CategorieCnssGerantDTO categorieCnssGerantDTO) throws URISyntaxException {
        log.debug("REST request to save CategorieCnssGerant : {}", categorieCnssGerantDTO);
        if (categorieCnssGerantDTO.getId() != null) {
            throw new BadRequestAlertException("A new categorieCnssGerant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategorieCnssGerantDTO result = categorieCnssGerantService.save(categorieCnssGerantDTO);
        return ResponseEntity.created(new URI("/api/categorie-cnss-gerants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /categorie-cnss-gerants : Updates an existing categorieCnssGerant.
     *
     * @param categorieCnssGerantDTO the categorieCnssGerantDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated categorieCnssGerantDTO,
     * or with status 400 (Bad Request) if the categorieCnssGerantDTO is not valid,
     * or with status 500 (Internal Server Error) if the categorieCnssGerantDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/categorie-cnss-gerants")
    public ResponseEntity<CategorieCnssGerantDTO> updateCategorieCnssGerant(@Valid @RequestBody CategorieCnssGerantDTO categorieCnssGerantDTO) throws URISyntaxException {
        log.debug("REST request to update CategorieCnssGerant : {}", categorieCnssGerantDTO);
        if (categorieCnssGerantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CategorieCnssGerantDTO result = categorieCnssGerantService.save(categorieCnssGerantDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, categorieCnssGerantDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /categorie-cnss-gerants : get all the categorieCnssGerants.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of categorieCnssGerants in body
     */
    @GetMapping("/categorie-cnss-gerants")
    public List<CategorieCnssGerantDTO> getAllCategorieCnssGerants() {
        log.debug("REST request to get all CategorieCnssGerants");
        return categorieCnssGerantService.findAll();
    }

    /**
     * GET  /categorie-cnss-gerants/:id : get the "id" categorieCnssGerant.
     *
     * @param id the id of the categorieCnssGerantDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the categorieCnssGerantDTO, or with status 404 (Not Found)
     */
    @GetMapping("/categorie-cnss-gerants/{id}")
    public ResponseEntity<CategorieCnssGerantDTO> getCategorieCnssGerant(@PathVariable Long id) {
        log.debug("REST request to get CategorieCnssGerant : {}", id);
        Optional<CategorieCnssGerantDTO> categorieCnssGerantDTO = categorieCnssGerantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categorieCnssGerantDTO);
    }

    /**
     * DELETE  /categorie-cnss-gerants/:id : delete the "id" categorieCnssGerant.
     *
     * @param id the id of the categorieCnssGerantDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/categorie-cnss-gerants/{id}")
    public ResponseEntity<Void> deleteCategorieCnssGerant(@PathVariable Long id) {
        log.debug("REST request to delete CategorieCnssGerant : {}", id);
        categorieCnssGerantService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
