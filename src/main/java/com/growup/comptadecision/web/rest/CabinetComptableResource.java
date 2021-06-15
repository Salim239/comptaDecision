package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.service.CabinetComptableService;
import com.growup.comptadecision.service.dto.CabinetComptableDTO;
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
 * REST controller for managing CabinetComptable.
 */
@RestController
@RequestMapping("/api")
public class CabinetComptableResource {

    private final Logger log = LoggerFactory.getLogger(CabinetComptableResource.class);

    private static final String ENTITY_NAME = "cabinetComptable";

    private final CabinetComptableService cabinetComptableService;

    public CabinetComptableResource(CabinetComptableService cabinetComptableService) {
        this.cabinetComptableService = cabinetComptableService;
    }

    /**
     * POST  /cabinet-comptables : Create a new cabinetComptable.
     *
     * @param cabinetComptableDTO the cabinetComptableDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cabinetComptableDTO, or with status 400 (Bad Request) if the cabinetComptable has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cabinet-comptables")
    public ResponseEntity<CabinetComptableDTO> createCabinetComptable(@Valid @RequestBody CabinetComptableDTO cabinetComptableDTO) throws URISyntaxException {
        log.debug("REST request to save CabinetComptable : {}", cabinetComptableDTO);
        if (cabinetComptableDTO.getId() != null) {
            throw new BadRequestAlertException("A new cabinetComptable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CabinetComptableDTO result = cabinetComptableService.save(cabinetComptableDTO);
        return ResponseEntity.created(new URI("/api/cabinet-comptables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cabinet-comptables : Updates an existing cabinetComptable.
     *
     * @param cabinetComptableDTO the cabinetComptableDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cabinetComptableDTO,
     * or with status 400 (Bad Request) if the cabinetComptableDTO is not valid,
     * or with status 500 (Internal Server Error) if the cabinetComptableDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cabinet-comptables")
    public ResponseEntity<CabinetComptableDTO> updateCabinetComptable(@Valid @RequestBody CabinetComptableDTO cabinetComptableDTO) throws URISyntaxException {
        log.debug("REST request to update CabinetComptable : {}", cabinetComptableDTO);
        if (cabinetComptableDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CabinetComptableDTO result = cabinetComptableService.save(cabinetComptableDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cabinetComptableDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cabinet-comptables : get all the cabinetComptables.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of cabinetComptables in body
     */
    @GetMapping("/cabinet-comptables")
    public ResponseEntity<List<CabinetComptableDTO>> getAllCabinetComptables(Pageable pageable) {
        log.debug("REST request to get a page of CabinetComptables");
        Page<CabinetComptableDTO> page = cabinetComptableService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cabinet-comptables");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /cabinet-comptables/:id : get the "id" cabinetComptable.
     *
     * @param id the id of the cabinetComptableDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cabinetComptableDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cabinet-comptables/{id}")
    public ResponseEntity<CabinetComptableDTO> getCabinetComptable(@PathVariable Long id) {
        log.debug("REST request to get CabinetComptable : {}", id);
        Optional<CabinetComptableDTO> cabinetComptableDTO = cabinetComptableService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cabinetComptableDTO);
    }

    /**
     * DELETE  /cabinet-comptables/:id : delete the "id" cabinetComptable.
     *
     * @param id the id of the cabinetComptableDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cabinet-comptables/{id}")
    public ResponseEntity<Void> deleteCabinetComptable(@PathVariable Long id) {
        log.debug("REST request to delete CabinetComptable : {}", id);
        cabinetComptableService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
