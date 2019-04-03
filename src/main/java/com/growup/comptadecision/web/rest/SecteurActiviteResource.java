package com.growup.comptadecision.web.rest;
import com.growup.comptadecision.service.SecteurActiviteService;
import com.growup.comptadecision.web.rest.errors.BadRequestAlertException;
import com.growup.comptadecision.web.rest.util.HeaderUtil;
import com.growup.comptadecision.service.dto.SecteurActiviteDTO;
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
 * REST controller for managing SecteurActivite.
 */
@RestController
@RequestMapping("/api")
public class SecteurActiviteResource {

    private final Logger log = LoggerFactory.getLogger(SecteurActiviteResource.class);

    private static final String ENTITY_NAME = "secteurActivite";

    private final SecteurActiviteService secteurActiviteService;

    public SecteurActiviteResource(SecteurActiviteService secteurActiviteService) {
        this.secteurActiviteService = secteurActiviteService;
    }

    /**
     * POST  /secteur-activites : Create a new secteurActivite.
     *
     * @param secteurActiviteDTO the secteurActiviteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new secteurActiviteDTO, or with status 400 (Bad Request) if the secteurActivite has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/secteur-activites")
    public ResponseEntity<SecteurActiviteDTO> createSecteurActivite(@Valid @RequestBody SecteurActiviteDTO secteurActiviteDTO) throws URISyntaxException {
        log.debug("REST request to save SecteurActivite : {}", secteurActiviteDTO);
        if (secteurActiviteDTO.getId() != null) {
            throw new BadRequestAlertException("A new secteurActivite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SecteurActiviteDTO result = secteurActiviteService.save(secteurActiviteDTO);
        return ResponseEntity.created(new URI("/api/secteur-activites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /secteur-activites : Updates an existing secteurActivite.
     *
     * @param secteurActiviteDTO the secteurActiviteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated secteurActiviteDTO,
     * or with status 400 (Bad Request) if the secteurActiviteDTO is not valid,
     * or with status 500 (Internal Server Error) if the secteurActiviteDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/secteur-activites")
    public ResponseEntity<SecteurActiviteDTO> updateSecteurActivite(@Valid @RequestBody SecteurActiviteDTO secteurActiviteDTO) throws URISyntaxException {
        log.debug("REST request to update SecteurActivite : {}", secteurActiviteDTO);
        if (secteurActiviteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SecteurActiviteDTO result = secteurActiviteService.save(secteurActiviteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, secteurActiviteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /secteur-activites : get all the secteurActivites.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of secteurActivites in body
     */
    @GetMapping("/secteur-activites")
    public List<SecteurActiviteDTO> getAllSecteurActivites() {
        log.debug("REST request to get all SecteurActivites");
        return secteurActiviteService.findAll();
    }

    /**
     * GET  /secteur-activites/:id : get the "id" secteurActivite.
     *
     * @param id the id of the secteurActiviteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the secteurActiviteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/secteur-activites/{id}")
    public ResponseEntity<SecteurActiviteDTO> getSecteurActivite(@PathVariable Long id) {
        log.debug("REST request to get SecteurActivite : {}", id);
        Optional<SecteurActiviteDTO> secteurActiviteDTO = secteurActiviteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(secteurActiviteDTO);
    }

    /**
     * DELETE  /secteur-activites/:id : delete the "id" secteurActivite.
     *
     * @param id the id of the secteurActiviteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/secteur-activites/{id}")
    public ResponseEntity<Void> deleteSecteurActivite(@PathVariable Long id) {
        log.debug("REST request to delete SecteurActivite : {}", id);
        secteurActiviteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
