package com.growup.comptadecision.web.rest;
import com.growup.comptadecision.service.VilleService;
import com.growup.comptadecision.web.rest.errors.BadRequestAlertException;
import com.growup.comptadecision.web.rest.util.HeaderUtil;
import com.growup.comptadecision.service.dto.VilleDTO;
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
 * REST controller for managing Ville.
 */
@RestController
@RequestMapping("/api")
public class VilleResource {

    private final Logger log = LoggerFactory.getLogger(VilleResource.class);

    private static final String ENTITY_NAME = "ville";

    private final VilleService villeService;

    public VilleResource(VilleService villeService) {
        this.villeService = villeService;
    }

    /**
     * POST  /villes : Create a new ville.
     *
     * @param villeDTO the villeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new villeDTO, or with status 400 (Bad Request) if the ville has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/villes")
    public ResponseEntity<VilleDTO> createVille(@Valid @RequestBody VilleDTO villeDTO) throws URISyntaxException {
        log.debug("REST request to save Ville : {}", villeDTO);
        if (villeDTO.getId() != null) {
            throw new BadRequestAlertException("A new ville cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VilleDTO result = villeService.save(villeDTO);
        return ResponseEntity.created(new URI("/api/villes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /villes : Updates an existing ville.
     *
     * @param villeDTO the villeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated villeDTO,
     * or with status 400 (Bad Request) if the villeDTO is not valid,
     * or with status 500 (Internal Server Error) if the villeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/villes")
    public ResponseEntity<VilleDTO> updateVille(@Valid @RequestBody VilleDTO villeDTO) throws URISyntaxException {
        log.debug("REST request to update Ville : {}", villeDTO);
        if (villeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VilleDTO result = villeService.save(villeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, villeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /villes : get all the villes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of villes in body
     */
    @GetMapping("/villes")
    public List<VilleDTO> getAllVilles() {
        log.debug("REST request to get all Villes");
        return villeService.findAll();
    }

    /**
     * GET  /villes/:id : get the "id" ville.
     *
     * @param id the id of the villeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the villeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/villes/{id}")
    public ResponseEntity<VilleDTO> getVille(@PathVariable Long id) {
        log.debug("REST request to get Ville : {}", id);
        Optional<VilleDTO> villeDTO = villeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(villeDTO);
    }

    /**
     * DELETE  /villes/:id : delete the "id" ville.
     *
     * @param id the id of the villeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/villes/{id}")
    public ResponseEntity<Void> deleteVille(@PathVariable Long id) {
        log.debug("REST request to delete Ville : {}", id);
        villeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /villes : get all the villes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of villes in body
     */
    @GetMapping("/villes/region/{regionId}")
    public List<VilleDTO> getVillesByRegion(@PathVariable Long regionId) {
        log.debug("REST request to get Villes by region id {}", regionId);
        return villeService.findByRegionId(regionId);
    }
}
