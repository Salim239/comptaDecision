package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.domain.enumeration.TypeCentreAdministratif;
import com.growup.comptadecision.service.CentreAdministratifService;
import com.growup.comptadecision.service.dto.CentreAdministratifDTO;
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
 * REST controller for managing CentreAdministratif.
 */
@RestController
@RequestMapping("/api")
public class CentreAdministratifResource {

    private final Logger log = LoggerFactory.getLogger(CentreAdministratifResource.class);

    private static final String ENTITY_NAME = "centreAdministratif";

    private final CentreAdministratifService centreAdministratifService;

    public CentreAdministratifResource(CentreAdministratifService centreAdministratifService) {
        this.centreAdministratifService = centreAdministratifService;
    }

    /**
     * POST  /centre-administratifs : Create a new centreAdministratif.
     *
     * @param centreAdministratifDTO the centreAdministratifDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new centreAdministratifDTO, or with status 400 (Bad Request) if the centreAdministratif has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/centre-administratifs")
    public ResponseEntity<CentreAdministratifDTO> createCentreAdministratif(@Valid @RequestBody CentreAdministratifDTO centreAdministratifDTO) throws URISyntaxException {
        log.debug("REST request to save CentreAdministratif : {}", centreAdministratifDTO);
        if (centreAdministratifDTO.getId() != null) {
            throw new BadRequestAlertException("A new centreAdministratif cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CentreAdministratifDTO result = centreAdministratifService.save(centreAdministratifDTO);
        return ResponseEntity.created(new URI("/api/centre-administratifs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /centre-administratifs : Updates an existing centreAdministratif.
     *
     * @param centreAdministratifDTO the centreAdministratifDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated centreAdministratifDTO,
     * or with status 400 (Bad Request) if the centreAdministratifDTO is not valid,
     * or with status 500 (Internal Server Error) if the centreAdministratifDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/centre-administratifs")
    public ResponseEntity<CentreAdministratifDTO> updateCentreAdministratif(@Valid @RequestBody CentreAdministratifDTO centreAdministratifDTO) throws URISyntaxException {
        log.debug("REST request to update CentreAdministratif : {}", centreAdministratifDTO);
        if (centreAdministratifDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CentreAdministratifDTO result = centreAdministratifService.save(centreAdministratifDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, centreAdministratifDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /centre-administratifs : get all the centreAdministratifs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of centreAdministratifs in body
     */
    @GetMapping("/centre-administratifs")
    public List<CentreAdministratifDTO> getAllCentreAdministratifs() {
        log.debug("REST request to get all centre administratifs");
        return centreAdministratifService.findAll();
    }

    /**
     * GET  /centre-administratifs : get centreAdministratifs by type
     *
     * @return the ResponseEntity with status 200 (OK) and the list of centreAdministratifs in body
     */
    @GetMapping("/centre-administratifs/type/{type}")
    public List<CentreAdministratifDTO> getCentreAdministratifsByType(@PathVariable TypeCentreAdministratif type) {
        log.debug("REST request to get CentreAdministratifs by type {}", type);
        return centreAdministratifService.findByType(type);
    }


    /**
     * GET  /centre-administratifs/:id : get the "id" centreAdministratif.
     *
     * @param id the id of the centreAdministratifDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the centreAdministratifDTO, or with status 404 (Not Found)
     */
    @GetMapping("/centre-administratifs/{id}")
    public ResponseEntity<CentreAdministratifDTO> getCentreAdministratif(@PathVariable Long id) {
        log.debug("REST request to get CentreAdministratif : {}", id);
        Optional<CentreAdministratifDTO> centreAdministratifDTO = centreAdministratifService.findOne(id);
        return ResponseUtil.wrapOrNotFound(centreAdministratifDTO);
    }

    /**
     * DELETE  /centre-administratifs/:id : delete the "id" centreAdministratif.
     *
     * @param id the id of the centreAdministratifDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/centre-administratifs/{id}")
    public ResponseEntity<Void> deleteCentreAdministratif(@PathVariable Long id) {
        log.debug("REST request to delete CentreAdministratif : {}", id);
        centreAdministratifService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
