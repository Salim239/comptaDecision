package com.growup.comptadecision.web.rest;
import com.growup.comptadecision.service.ActiviteService;
import com.growup.comptadecision.web.rest.errors.BadRequestAlertException;
import com.growup.comptadecision.web.rest.util.HeaderUtil;
import com.growup.comptadecision.service.dto.ActiviteDTO;
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
 * REST controller for managing Activite.
 */
@RestController
@RequestMapping("/api")
public class ActiviteResource {

    private final Logger log = LoggerFactory.getLogger(ActiviteResource.class);

    private static final String ENTITY_NAME = "activite";

    private final ActiviteService activiteService;

    public ActiviteResource(ActiviteService activiteService) {
        this.activiteService = activiteService;
    }

    /**
     * POST  /activites : Create a new activite.
     *
     * @param activiteDTO the activiteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new activiteDTO, or with status 400 (Bad Request) if the activite has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/activites")
    public ResponseEntity<ActiviteDTO> createActivite(@Valid @RequestBody ActiviteDTO activiteDTO) throws URISyntaxException {
        log.debug("REST request to save Activite : {}", activiteDTO);
        if (activiteDTO.getId() != null) {
            throw new BadRequestAlertException("A new activite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ActiviteDTO result = activiteService.save(activiteDTO);
        return ResponseEntity.created(new URI("/api/activites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /activites : Updates an existing activite.
     *
     * @param activiteDTO the activiteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated activiteDTO,
     * or with status 400 (Bad Request) if the activiteDTO is not valid,
     * or with status 500 (Internal Server Error) if the activiteDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/activites")
    public ResponseEntity<ActiviteDTO> updateActivite(@Valid @RequestBody ActiviteDTO activiteDTO) throws URISyntaxException {
        log.debug("REST request to update Activite : {}", activiteDTO);
        if (activiteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ActiviteDTO result = activiteService.save(activiteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, activiteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /activites : get all the activites.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of activites in body
     */
    @GetMapping("/activites")
    public List<ActiviteDTO> getAllActivites() {
        log.debug("REST request to get all Activites");
        return activiteService.findAll();
    }

    /**
     * GET  /activites/:id : get the "id" activite.
     *
     * @param id the id of the activiteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the activiteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/activites/{id}")
    public ResponseEntity<ActiviteDTO> getActivite(@PathVariable Long id) {
        log.debug("REST request to get Activite : {}", id);
        Optional<ActiviteDTO> activiteDTO = activiteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(activiteDTO);
    }

    /**
     * DELETE  /activites/:id : delete the "id" activite.
     *
     * @param id the id of the activiteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/activites/{id}")
    public ResponseEntity<Void> deleteActivite(@PathVariable Long id) {
        log.debug("REST request to delete Activite : {}", id);
        activiteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /activites : get activites by secteur activite id.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of activites in body
     */
    @GetMapping("/activites/secteur-activite/{secteurActiviteId}")
    public List<ActiviteDTO> getActivitesBySecteurActivite(@PathVariable Long secteurActiviteId) {
        log.debug("REST request to get Activites by secteur activite id {}", secteurActiviteId);
        return activiteService.findBySecteurActiviteId(secteurActiviteId);
    }
}
