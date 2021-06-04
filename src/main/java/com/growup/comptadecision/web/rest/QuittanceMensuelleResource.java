package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.domain.enumeration.TypeDeclaration;
import com.growup.comptadecision.service.QuittanceMensuelleService;
import com.growup.comptadecision.service.dto.QuittanceMensuelleDTO;
import com.growup.comptadecision.web.rest.errors.BadRequestAlertException;
import com.growup.comptadecision.web.rest.util.HeaderUtil;
import com.growup.comptadecision.web.rest.util.PaginationUtil;
import com.itextpdf.text.DocumentException;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing QuittanceMensuelle.
 */
@RestController
@RequestMapping("/api")
public class QuittanceMensuelleResource {

    private final Logger log = LoggerFactory.getLogger(QuittanceMensuelleResource.class);

    private static final String ENTITY_NAME = "quittanceMensuelle";

    private final QuittanceMensuelleService quittanceMensuelleService;

    public QuittanceMensuelleResource(QuittanceMensuelleService quittanceMensuelleService) {
        this.quittanceMensuelleService = quittanceMensuelleService;
    }

    /**
     * POST  /quittance-mensuelles : Create a new quittanceMensuelle.
     *
     * @param quittanceMensuelleDTO the quittanceMensuelleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quittanceMensuelleDTO, or with status 400 (Bad Request) if the quittanceMensuelle has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quittance-mensuelles")
    public ResponseEntity<QuittanceMensuelleDTO> createQuittanceMensuelle(@Valid @RequestBody QuittanceMensuelleDTO quittanceMensuelleDTO) throws URISyntaxException {
        log.debug("REST request to save QuittanceMensuelle : {}", quittanceMensuelleDTO);
        if (quittanceMensuelleDTO.getId() != null) {
            throw new BadRequestAlertException("A new quittanceMensuelle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuittanceMensuelleDTO result = quittanceMensuelleService.save(quittanceMensuelleDTO);
        return ResponseEntity.created(new URI("/api/quittance-mensuelles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quittance-mensuelles : Updates an existing quittanceMensuelle.
     *
     * @param quittanceMensuelleDTO the quittanceMensuelleDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quittanceMensuelleDTO,
     * or with status 400 (Bad Request) if the quittanceMensuelleDTO is not valid,
     * or with status 500 (Internal Server Error) if the quittanceMensuelleDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quittance-mensuelles")
    public ResponseEntity<QuittanceMensuelleDTO> updateQuittanceMensuelle(@Valid @RequestBody QuittanceMensuelleDTO quittanceMensuelleDTO) throws URISyntaxException {
        log.debug("REST request to update QuittanceMensuelle : {}", quittanceMensuelleDTO);
        if (quittanceMensuelleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QuittanceMensuelleDTO result = quittanceMensuelleService.save(quittanceMensuelleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quittanceMensuelleDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quittance-mensuelles : get all the quittanceMensuelles.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of quittanceMensuelles in body
     */
    @GetMapping("/quittance-mensuelles")
    public ResponseEntity<List<QuittanceMensuelleDTO>> getAllQuittanceMensuelles(Pageable pageable) {
        log.debug("REST request to get a page of QuittanceMensuelles");
        Page<QuittanceMensuelleDTO> page = quittanceMensuelleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quittance-mensuelles");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /quittance-mensuelles/:id : get the "id" quittanceMensuelle.
     *
     * @param id the id of the quittanceMensuelleDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quittanceMensuelleDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quittance-mensuelles/{id}")
    public ResponseEntity<QuittanceMensuelleDTO> getQuittanceMensuelle(@PathVariable Long id) {
        log.debug("REST request to get QuittanceMensuelle : {}", id);
        Optional<QuittanceMensuelleDTO> quittanceMensuelleDTO = quittanceMensuelleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(quittanceMensuelleDTO);
    }

//    @GetMapping("/quittance-mensuelles/ficheClient/{ficheClientId}")
//    public ResponseEntity<QuittanceMensuelleDTO> getEmptyQuittanceMensuel(@PathVariable("ficheClientId") Long ficheClientId) {
//        log.debug("REST request to get QuittanceMensuelle de la fiche client : {}", ficheClientId);
//        QuittanceMensuelleDTO quittanceMensuelleDTO = quittanceMensuelleService.getEmptyQuittanceMensuel(ficheClientId);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, null)).body(quittanceMensuelleDTO);
//    }

    /**
     * DELETE  /quittance-mensuelles/:id : delete the "id" quittanceMensuelle.
     *
     * @param id the id of the quittanceMensuelleDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quittance-mensuelles/{id}")
    public ResponseEntity<Void> deleteQuittanceMensuelle(@PathVariable Long id) {
        log.debug("REST request to delete QuittanceMensuelle : {}", id);
        quittanceMensuelleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * POST  /quittance-mensuelles/init
     * @param id
     * @param annee
     * @param mois
     * @param typeDeclaration
     * @return
     */
    @GetMapping("/quittance-mensuelles/init/{id}/{annee}/{mois}/{typeDeclaration}")
    public ResponseEntity<QuittanceMensuelleDTO> init(@PathVariable Long id, @PathVariable Integer annee, @PathVariable Integer mois,
                                                      @PathVariable String typeDeclaration) {
        log.debug("REST request to init empty QuittanceMensuelle");
        QuittanceMensuelleDTO quittanceMensuelleDTO = quittanceMensuelleService.init(id, annee, mois, TypeDeclaration.valueOf(typeDeclaration));
        return ResponseEntity.ok().headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, null)).body(quittanceMensuelleDTO);
    }

    @GetMapping("/quittance-mensuelles/{id}/print")
    public ResponseEntity<Void> printQuittanceMensuelle(HttpServletResponse response, @PathVariable Long id) {
        log.debug("REST request to print QuittanceMensuelle : {}", id);
        try {
            quittanceMensuelleService.print(id, response);
        } catch (IOException | DocumentException ex) {
            throw new RuntimeException("Error When genererate Quittance report");
        }
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
