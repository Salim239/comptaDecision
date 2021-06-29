package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.service.PaiementQueryService;
import com.growup.comptadecision.service.PaiementService;
import com.growup.comptadecision.service.dto.PaiementCriteria;
import com.growup.comptadecision.service.dto.PaiementDTO;
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
 * REST controller for managing Paiement.
 */
@RestController
@RequestMapping("/api")
public class PaiementResource {

    private final Logger log = LoggerFactory.getLogger(PaiementResource.class);

    private static final String ENTITY_NAME = "paiement";

    private final PaiementService paiementService;

    private final PaiementQueryService paiementQueryService;

    public PaiementResource(PaiementService paiementService, PaiementQueryService paiementQueryService) {
        this.paiementService = paiementService;
        this.paiementQueryService = paiementQueryService;
    }

    /**
     * POST  /paiements : Create a new paiement.
     *
     * @param paiementDTO the paiementDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new paiementDTO, or with status 400 (Bad Request) if the paiement has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/paiements")
    public ResponseEntity<PaiementDTO> createPaiement(@Valid @RequestBody PaiementDTO paiementDTO) throws URISyntaxException {
        log.debug("REST request to save Paiement : {}", paiementDTO);
        if (paiementDTO.getId() != null) {
            throw new BadRequestAlertException("A new paiement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PaiementDTO result = paiementService.save(paiementDTO);
        return ResponseEntity.created(new URI("/api/paiements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /paiements : Updates an existing paiement.
     *
     * @param paiementDTO the paiementDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated paiementDTO,
     * or with status 400 (Bad Request) if the paiementDTO is not valid,
     * or with status 500 (Internal Server Error) if the paiementDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/paiements")
    public ResponseEntity<PaiementDTO> updatePaiement(@Valid @RequestBody PaiementDTO paiementDTO) throws URISyntaxException {
        log.debug("REST request to update Paiement : {}", paiementDTO);
        if (paiementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PaiementDTO result = paiementService.save(paiementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, paiementDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /paiements : get all the paiements.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of paiements in body
     */
    @GetMapping("/paiements")
    public ResponseEntity<List<PaiementDTO>> getAllPaiements(PaiementCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Paiements by criteria: {}", criteria);
        Page<PaiementDTO> page = paiementQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/paiements");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /paiements/count : count all the paiements.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/paiements/count")
    public ResponseEntity<Long> countPaiements(PaiementCriteria criteria) {
        log.debug("REST request to count Paiements by criteria: {}", criteria);
        return ResponseEntity.ok().body(paiementQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /paiements/:id : get the "id" paiement.
     *
     * @param id the id of the paiementDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the paiementDTO, or with status 404 (Not Found)
     */
    @GetMapping("/paiements/{id}")
    public ResponseEntity<PaiementDTO> getPaiement(@PathVariable Long id) {
        log.debug("REST request to get Paiement : {}", id);
        Optional<PaiementDTO> paiementDTO = paiementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paiementDTO);
    }

    /**
     * DELETE  /paiements/:id : delete the "id" paiement.
     *
     * @param id the id of the paiementDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/paiements/{id}")
    public ResponseEntity<Void> deletePaiement(@PathVariable Long id) {
        log.debug("REST request to delete Paiement : {}", id);
        paiementService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
