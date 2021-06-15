package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.service.LigneCaisseService;
import com.growup.comptadecision.service.dto.LigneCaisseDTO;
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
 * REST controller for managing LigneCaisse.
 */
@RestController
@RequestMapping("/api")
public class LigneCaisseResource {

    private final Logger log = LoggerFactory.getLogger(LigneCaisseResource.class);

    private static final String ENTITY_NAME = "ligneCaisse";

    private final LigneCaisseService ligneCaisseService;

    public LigneCaisseResource(LigneCaisseService ligneCaisseService) {
        this.ligneCaisseService = ligneCaisseService;
    }

    /**
     * POST  /ligne-caisses : Create a new ligneCaisse.
     *
     * @param ligneCaisseDTO the ligneCaisseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ligneCaisseDTO, or with status 400 (Bad Request) if the ligneCaisse has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ligne-caisses")
    public ResponseEntity<LigneCaisseDTO> createLigneCaisse(@Valid @RequestBody LigneCaisseDTO ligneCaisseDTO) throws URISyntaxException {
        log.debug("REST request to save LigneCaisse : {}", ligneCaisseDTO);
        if (ligneCaisseDTO.getId() != null) {
            throw new BadRequestAlertException("A new ligneCaisse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LigneCaisseDTO result = ligneCaisseService.save(ligneCaisseDTO);
        return ResponseEntity.created(new URI("/api/ligne-caisses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ligne-caisses : Updates an existing ligneCaisse.
     *
     * @param ligneCaisseDTO the ligneCaisseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ligneCaisseDTO,
     * or with status 400 (Bad Request) if the ligneCaisseDTO is not valid,
     * or with status 500 (Internal Server Error) if the ligneCaisseDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ligne-caisses")
    public ResponseEntity<LigneCaisseDTO> updateLigneCaisse(@Valid @RequestBody LigneCaisseDTO ligneCaisseDTO) throws URISyntaxException {
        log.debug("REST request to update LigneCaisse : {}", ligneCaisseDTO);
        if (ligneCaisseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LigneCaisseDTO result = ligneCaisseService.save(ligneCaisseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ligneCaisseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ligne-caisses : get all the ligneCaisses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of ligneCaisses in body
     */
    @GetMapping("/ligne-caisses")
    public ResponseEntity<List<LigneCaisseDTO>> getAllLigneCaisses(Pageable pageable) {
        log.debug("REST request to get a page of LigneCaisses");
        Page<LigneCaisseDTO> page = ligneCaisseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ligne-caisses");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ligne-caisses/:id : get the "id" ligneCaisse.
     *
     * @param id the id of the ligneCaisseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ligneCaisseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ligne-caisses/{id}")
    public ResponseEntity<LigneCaisseDTO> getLigneCaisse(@PathVariable Long id) {
        log.debug("REST request to get LigneCaisse : {}", id);
        Optional<LigneCaisseDTO> ligneCaisseDTO = ligneCaisseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ligneCaisseDTO);
    }

    /**
     * DELETE  /ligne-caisses/:id : delete the "id" ligneCaisse.
     *
     * @param id the id of the ligneCaisseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ligne-caisses/{id}")
    public ResponseEntity<Void> deleteLigneCaisse(@PathVariable Long id) {
        log.debug("REST request to delete LigneCaisse : {}", id);
        ligneCaisseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
