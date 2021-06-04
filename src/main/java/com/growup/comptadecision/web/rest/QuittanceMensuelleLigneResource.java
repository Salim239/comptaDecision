package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.service.QuittanceMensuelleLigneService;
import com.growup.comptadecision.service.dto.QuittanceMensuelleLigneDTO;
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
 * REST controller for managing QuittanceMensuelleLigne.
 */
@RestController
@RequestMapping("/api")
public class QuittanceMensuelleLigneResource {

    private final Logger log = LoggerFactory.getLogger(QuittanceMensuelleLigneResource.class);

    private static final String ENTITY_NAME = "quittanceMensuelleLigne";

    private final QuittanceMensuelleLigneService quittanceMensuelleLigneService;

    public QuittanceMensuelleLigneResource(QuittanceMensuelleLigneService quittanceMensuelleLigneService) {
        this.quittanceMensuelleLigneService = quittanceMensuelleLigneService;
    }

    /**
     * POST  /quittance-mensuelle-lignes : Create a new quittanceMensuelleLigne.
     *
     * @param quittanceMensuelleLigneDTO the quittanceMensuelleLigneDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quittanceMensuelleLigneDTO, or with status 400 (Bad Request) if the quittanceMensuelleLigne has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quittance-mensuelle-lignes")
    public ResponseEntity<QuittanceMensuelleLigneDTO> createQuittanceMensuelleLigne(@RequestBody QuittanceMensuelleLigneDTO quittanceMensuelleLigneDTO) throws URISyntaxException {
        log.debug("REST request to save QuittanceMensuelleLigne : {}", quittanceMensuelleLigneDTO);
        if (quittanceMensuelleLigneDTO.getId() != null) {
            throw new BadRequestAlertException("A new quittanceMensuelleLigne cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuittanceMensuelleLigneDTO result = quittanceMensuelleLigneService.save(quittanceMensuelleLigneDTO);
        return ResponseEntity.created(new URI("/api/quittance-mensuelle-lignes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quittance-mensuelle-lignes : Updates an existing quittanceMensuelleLigne.
     *
     * @param quittanceMensuelleLigneDTO the quittanceMensuelleLigneDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quittanceMensuelleLigneDTO,
     * or with status 400 (Bad Request) if the quittanceMensuelleLigneDTO is not valid,
     * or with status 500 (Internal Server Error) if the quittanceMensuelleLigneDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quittance-mensuelle-lignes")
    public ResponseEntity<QuittanceMensuelleLigneDTO> updateQuittanceMensuelleLigne(@RequestBody QuittanceMensuelleLigneDTO quittanceMensuelleLigneDTO) throws URISyntaxException {
        log.debug("REST request to update QuittanceMensuelleLigne : {}", quittanceMensuelleLigneDTO);
        if (quittanceMensuelleLigneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QuittanceMensuelleLigneDTO result = quittanceMensuelleLigneService.save(quittanceMensuelleLigneDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quittanceMensuelleLigneDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quittance-mensuelle-lignes : get all the quittanceMensuelleLignes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of quittanceMensuelleLignes in body
     */
    @GetMapping("/quittance-mensuelle-lignes")
    public ResponseEntity<List<QuittanceMensuelleLigneDTO>> getAllQuittanceMensuelleLignes(Pageable pageable) {
        log.debug("REST request to get a page of QuittanceMensuelleLignes");
        Page<QuittanceMensuelleLigneDTO> page = quittanceMensuelleLigneService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quittance-mensuelle-lignes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /quittance-mensuelle-lignes/:id : get the "id" quittanceMensuelleLigne.
     *
     * @param id the id of the quittanceMensuelleLigneDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quittanceMensuelleLigneDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quittance-mensuelle-lignes/{id}")
    public ResponseEntity<QuittanceMensuelleLigneDTO> getQuittanceMensuelleLigne(@PathVariable Long id) {
        log.debug("REST request to get QuittanceMensuelleLigne : {}", id);
        Optional<QuittanceMensuelleLigneDTO> quittanceMensuelleLigneDTO = quittanceMensuelleLigneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(quittanceMensuelleLigneDTO);
    }

    /**
     * DELETE  /quittance-mensuelle-lignes/:id : delete the "id" quittanceMensuelleLigne.
     *
     * @param id the id of the quittanceMensuelleLigneDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quittance-mensuelle-lignes/{id}")
    public ResponseEntity<Void> deleteQuittanceMensuelleLigne(@PathVariable Long id) {
        log.debug("REST request to delete QuittanceMensuelleLigne : {}", id);
        quittanceMensuelleLigneService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
