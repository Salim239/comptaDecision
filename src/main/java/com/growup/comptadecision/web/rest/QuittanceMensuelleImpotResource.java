package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.service.QuittanceMensuelleImpotService;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotDTO;
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
 * REST controller for managing QuittanceMensuelleImpot.
 */
@RestController
@RequestMapping("/api")
public class QuittanceMensuelleImpotResource {

    private final Logger log = LoggerFactory.getLogger(QuittanceMensuelleImpotResource.class);

    private static final String ENTITY_NAME = "quittanceMensuelleImpot";

    private final QuittanceMensuelleImpotService quittanceMensuelleImpotService;

    public QuittanceMensuelleImpotResource(QuittanceMensuelleImpotService quittanceMensuelleImpotService) {
        this.quittanceMensuelleImpotService = quittanceMensuelleImpotService;
    }

    /**
     * POST  /quittance-mensuelle-impots : Create a new quittanceMensuelleImpot.
     *
     * @param quittanceMensuelleImpotDTO the quittanceMensuelleImpotDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quittanceMensuelleImpotDTO, or with status 400 (Bad Request) if the quittanceMensuelleImpot has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quittance-mensuelle-impots")
    public ResponseEntity<QuittanceMensuelleImpotDTO> createQuittanceMensuelleImpot(@Valid @RequestBody QuittanceMensuelleImpotDTO quittanceMensuelleImpotDTO) throws URISyntaxException {
        log.debug("REST request to save QuittanceMensuelleImpot : {}", quittanceMensuelleImpotDTO);
        if (quittanceMensuelleImpotDTO.getId() != null) {
            throw new BadRequestAlertException("A new quittanceMensuelleImpot cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuittanceMensuelleImpotDTO result = quittanceMensuelleImpotService.save(quittanceMensuelleImpotDTO);
        return ResponseEntity.created(new URI("/api/quittance-mensuelle-impots/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quittance-mensuelle-impots : Updates an existing quittanceMensuelleImpot.
     *
     * @param quittanceMensuelleImpotDTO the quittanceMensuelleImpotDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quittanceMensuelleImpotDTO,
     * or with status 400 (Bad Request) if the quittanceMensuelleImpotDTO is not valid,
     * or with status 500 (Internal Server Error) if the quittanceMensuelleImpotDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quittance-mensuelle-impots")
    public ResponseEntity<QuittanceMensuelleImpotDTO> updateQuittanceMensuelleImpot(@Valid @RequestBody QuittanceMensuelleImpotDTO quittanceMensuelleImpotDTO) throws URISyntaxException {
        log.debug("REST request to update QuittanceMensuelleImpot : {}", quittanceMensuelleImpotDTO);
        if (quittanceMensuelleImpotDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QuittanceMensuelleImpotDTO result = quittanceMensuelleImpotService.save(quittanceMensuelleImpotDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quittanceMensuelleImpotDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quittance-mensuelle-impots : get all the quittanceMensuelleImpots.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of quittanceMensuelleImpots in body
     */
    @GetMapping("/quittance-mensuelle-impots")
    public ResponseEntity<List<QuittanceMensuelleImpotDTO>> getAllQuittanceMensuelleImpots(Pageable pageable) {
        log.debug("REST request to get a page of QuittanceMensuelleImpots");
        Page<QuittanceMensuelleImpotDTO> page = quittanceMensuelleImpotService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quittance-mensuelle-impots");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /quittance-mensuelle-impots/:id : get the "id" quittanceMensuelleImpot.
     *
     * @param id the id of the quittanceMensuelleImpotDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quittanceMensuelleImpotDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quittance-mensuelle-impots/{id}")
    public ResponseEntity<QuittanceMensuelleImpotDTO> getQuittanceMensuelleImpot(@PathVariable Long id) {
        log.debug("REST request to get QuittanceMensuelleImpot : {}", id);
        Optional<QuittanceMensuelleImpotDTO> quittanceMensuelleImpotDTO = quittanceMensuelleImpotService.findOne(id);
        return ResponseUtil.wrapOrNotFound(quittanceMensuelleImpotDTO);
    }

    /**
     * POST  /quittance-mensuelle-impots/init
     *
     * @param quittanceMensuelleImpotDTO the quittanceMensuelleImpotDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quittanceMensuelleImpotDTO, or with status 400 (Bad Request) if the quittanceMensuelleImpot has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @GetMapping("/quittance-mensuelle-impots/init")
    public ResponseEntity<QuittanceMensuelleImpotDTO> init() {
        log.debug("REST request to init empty QuittanceMensuelleImpot");
        QuittanceMensuelleImpotDTO quittanceMensuelleImpotDTO = quittanceMensuelleImpotService.init();
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, null)).body(quittanceMensuelleImpotDTO);
    }

    @GetMapping("/quittance-mensuelle-impots/ficheClient/{ficheClientId}")
    public ResponseEntity<QuittanceMensuelleImpotDTO> getEmptyQuittanceMensuel(@PathVariable("ficheClientId") Long ficheClientId) {
        log.debug("REST request to get QuittanceMensuelleImpot de la fiche client : {}", ficheClientId);
        QuittanceMensuelleImpotDTO quittanceMensuelleImpotDTO = quittanceMensuelleImpotService.getEmptyQuittanceMensuel(ficheClientId);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, null)).body(quittanceMensuelleImpotDTO);
    }

    /**
     * DELETE  /quittance-mensuelle-impots/:id : delete the "id" quittanceMensuelleImpot.
     *
     * @param id the id of the quittanceMensuelleImpotDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quittance-mensuelle-impots/{id}")
    public ResponseEntity<Void> deleteQuittanceMensuelleImpot(@PathVariable Long id) {
        log.debug("REST request to delete QuittanceMensuelleImpot : {}", id);
        quittanceMensuelleImpotService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
