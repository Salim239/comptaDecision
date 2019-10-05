package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.service.QuittanceMensuelleImpotDetailService;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotDetailDTO;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing QuittanceMensuelleImpotDetail.
 */
@RestController
@RequestMapping("/api")
public class QuittanceMensuelleImpotDetailResource {

    private final Logger log = LoggerFactory.getLogger(QuittanceMensuelleImpotDetailResource.class);

    private static final String ENTITY_NAME = "quittanceMensuelleImpotDetail";

    private final QuittanceMensuelleImpotDetailService quittanceMensuelleImpotDetailService;

    public QuittanceMensuelleImpotDetailResource(QuittanceMensuelleImpotDetailService quittanceMensuelleImpotDetailService) {
        this.quittanceMensuelleImpotDetailService = quittanceMensuelleImpotDetailService;
    }

    /**
     * POST  /quittance-mensuelle-impot-details : Create a new quittanceMensuelleImpotDetail.
     *
     * @param quittanceMensuelleImpotDetailDTO the quittanceMensuelleImpotDetailDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quittanceMensuelleImpotDetailDTO, or with status 400 (Bad Request) if the quittanceMensuelleImpotDetail has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quittance-mensuelle-impot-details")
    public ResponseEntity<QuittanceMensuelleImpotDetailDTO> createQuittanceMensuelleImpotDetail(@RequestBody QuittanceMensuelleImpotDetailDTO quittanceMensuelleImpotDetailDTO) throws URISyntaxException {
        log.debug("REST request to save QuittanceMensuelleImpotDetail : {}", quittanceMensuelleImpotDetailDTO);
        if (quittanceMensuelleImpotDetailDTO.getId() != null) {
            throw new BadRequestAlertException("A new quittanceMensuelleImpotDetail cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuittanceMensuelleImpotDetailDTO result = quittanceMensuelleImpotDetailService.save(quittanceMensuelleImpotDetailDTO);
        return ResponseEntity.created(new URI("/api/quittance-mensuelle-impot-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quittance-mensuelle-impot-details : Updates an existing quittanceMensuelleImpotDetail.
     *
     * @param quittanceMensuelleImpotDetailDTO the quittanceMensuelleImpotDetailDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quittanceMensuelleImpotDetailDTO,
     * or with status 400 (Bad Request) if the quittanceMensuelleImpotDetailDTO is not valid,
     * or with status 500 (Internal Server Error) if the quittanceMensuelleImpotDetailDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quittance-mensuelle-impot-details")
    public ResponseEntity<QuittanceMensuelleImpotDetailDTO> updateQuittanceMensuelleImpotDetail(@RequestBody QuittanceMensuelleImpotDetailDTO quittanceMensuelleImpotDetailDTO) throws URISyntaxException {
        log.debug("REST request to update QuittanceMensuelleImpotDetail : {}", quittanceMensuelleImpotDetailDTO);
        if (quittanceMensuelleImpotDetailDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QuittanceMensuelleImpotDetailDTO result = quittanceMensuelleImpotDetailService.save(quittanceMensuelleImpotDetailDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quittanceMensuelleImpotDetailDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quittance-mensuelle-impot-details : get all the quittanceMensuelleImpotDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of quittanceMensuelleImpotDetails in body
     */
    @GetMapping("/quittance-mensuelle-impot-details")
    public ResponseEntity<List<QuittanceMensuelleImpotDetailDTO>> getAllQuittanceMensuelleImpotDetails(Pageable pageable) {
        log.debug("REST request to get a page of QuittanceMensuelleImpotDetails");
        Page<QuittanceMensuelleImpotDetailDTO> page = quittanceMensuelleImpotDetailService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quittance-mensuelle-impot-details");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /quittance-mensuelle-impot-details/:id : get the "id" quittanceMensuelleImpotDetail.
     *
     * @param id the id of the quittanceMensuelleImpotDetailDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quittanceMensuelleImpotDetailDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quittance-mensuelle-impot-details/{id}")
    public ResponseEntity<QuittanceMensuelleImpotDetailDTO> getQuittanceMensuelleImpotDetail(@PathVariable Long id) {
        log.debug("REST request to get QuittanceMensuelleImpotDetail : {}", id);
        Optional<QuittanceMensuelleImpotDetailDTO> quittanceMensuelleImpotDetailDTO = quittanceMensuelleImpotDetailService.findOne(id);
        return ResponseUtil.wrapOrNotFound(quittanceMensuelleImpotDetailDTO);
    }

    /**
     * DELETE  /quittance-mensuelle-impot-details/:id : delete the "id" quittanceMensuelleImpotDetail.
     *
     * @param id the id of the quittanceMensuelleImpotDetailDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quittance-mensuelle-impot-details/{id}")
    public ResponseEntity<Void> deleteQuittanceMensuelleImpotDetail(@PathVariable Long id) {
        log.debug("REST request to delete QuittanceMensuelleImpotDetail : {}", id);
        quittanceMensuelleImpotDetailService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
