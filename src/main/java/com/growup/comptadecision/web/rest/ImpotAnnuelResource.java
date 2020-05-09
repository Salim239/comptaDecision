package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.service.ImpotAnnuelService;
import com.growup.comptadecision.service.dto.ImpotAnnuelDTO;
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
 * REST controller for managing ImpotAnnuel.
 */
@RestController
@RequestMapping("/api")
public class ImpotAnnuelResource {

    private final Logger log = LoggerFactory.getLogger(ImpotAnnuelResource.class);

    private static final String ENTITY_NAME = "impotAnnuel";

    private final ImpotAnnuelService impotAnnuelService;

    public ImpotAnnuelResource(ImpotAnnuelService impotAnnuelService) {
        this.impotAnnuelService = impotAnnuelService;
    }

    /**
     * POST  /impot-annuels : Create a new impotAnnuel.
     *
     * @param impotAnnuelDTO the impotAnnuelDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new impotAnnuelDTO, or with status 400 (Bad Request) if the impotAnnuel has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/impot-annuels")
    public ResponseEntity<ImpotAnnuelDTO> createImpotAnnuel(@Valid @RequestBody ImpotAnnuelDTO impotAnnuelDTO) throws URISyntaxException {
        log.debug("REST request to save ImpotAnnuel : {}", impotAnnuelDTO);
        if (impotAnnuelDTO.getId() != null) {
            throw new BadRequestAlertException("A new impotAnnuel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ImpotAnnuelDTO result = impotAnnuelService.save(impotAnnuelDTO);
        return ResponseEntity.created(new URI("/api/impot-annuels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /impot-annuels : Updates an existing impotAnnuel.
     *
     * @param impotAnnuelDTO the impotAnnuelDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated impotAnnuelDTO,
     * or with status 400 (Bad Request) if the impotAnnuelDTO is not valid,
     * or with status 500 (Internal Server Error) if the impotAnnuelDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/impot-annuels")
    public ResponseEntity<ImpotAnnuelDTO> updateImpotAnnuel(@Valid @RequestBody ImpotAnnuelDTO impotAnnuelDTO) throws URISyntaxException {
        log.debug("REST request to update ImpotAnnuel : {}", impotAnnuelDTO);
        if (impotAnnuelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ImpotAnnuelDTO result = impotAnnuelService.save(impotAnnuelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, impotAnnuelDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /impot-annuels : get all the impotAnnuels.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of impotAnnuels in body
     */
    @GetMapping("/impot-annuels")
    public List<ImpotAnnuelDTO> getAllImpotAnnuels() {
        log.debug("REST request to get all ImpotAnnuels");
        return impotAnnuelService.findAll();
    }

    /**
     * GET  /impot-annuels/:id : get the "id" impotAnnuel.
     *
     * @param id the id of the impotAnnuelDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the impotAnnuelDTO, or with status 404 (Not Found)
     */
    @GetMapping("/impot-annuels/{id}")
    public ResponseEntity<ImpotAnnuelDTO> getImpotAnnuel(@PathVariable Long id) {
        log.debug("REST request to get ImpotAnnuel : {}", id);
        Optional<ImpotAnnuelDTO> impotAnnuelDTO = impotAnnuelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(impotAnnuelDTO);
    }

    /**
     * DELETE  /impot-annuels/:id : delete the "id" impotAnnuel.
     *
     * @param id the id of the impotAnnuelDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/impot-annuels/{id}")
    public ResponseEntity<Void> deleteImpotAnnuel(@PathVariable Long id) {
        log.debug("REST request to delete ImpotAnnuel : {}", id);
        impotAnnuelService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
