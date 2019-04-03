package com.growup.comptadecision.web.rest;
import com.growup.comptadecision.service.DeclarationAnnuelleService;
import com.growup.comptadecision.web.rest.errors.BadRequestAlertException;
import com.growup.comptadecision.web.rest.util.HeaderUtil;
import com.growup.comptadecision.web.rest.util.PaginationUtil;
import com.growup.comptadecision.service.dto.DeclarationAnnuelleDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DeclarationAnnuelle.
 */
@RestController
@RequestMapping("/api")
public class DeclarationAnnuelleResource {

    private final Logger log = LoggerFactory.getLogger(DeclarationAnnuelleResource.class);

    private static final String ENTITY_NAME = "declarationAnnuelle";

    private final DeclarationAnnuelleService declarationAnnuelleService;

    public DeclarationAnnuelleResource(DeclarationAnnuelleService declarationAnnuelleService) {
        this.declarationAnnuelleService = declarationAnnuelleService;
    }

    /**
     * POST  /declaration-annuelles : Create a new declarationAnnuelle.
     *
     * @param declarationAnnuelleDTO the declarationAnnuelleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new declarationAnnuelleDTO, or with status 400 (Bad Request) if the declarationAnnuelle has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/declaration-annuelles")
    public ResponseEntity<DeclarationAnnuelleDTO> createDeclarationAnnuelle(@Valid @RequestBody DeclarationAnnuelleDTO declarationAnnuelleDTO) throws URISyntaxException {
        log.debug("REST request to save DeclarationAnnuelle : {}", declarationAnnuelleDTO);
        if (declarationAnnuelleDTO.getId() != null) {
            throw new BadRequestAlertException("A new declarationAnnuelle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeclarationAnnuelleDTO result = declarationAnnuelleService.save(declarationAnnuelleDTO);
        return ResponseEntity.created(new URI("/api/declaration-annuelles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /declaration-annuelles : Updates an existing declarationAnnuelle.
     *
     * @param declarationAnnuelleDTO the declarationAnnuelleDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated declarationAnnuelleDTO,
     * or with status 400 (Bad Request) if the declarationAnnuelleDTO is not valid,
     * or with status 500 (Internal Server Error) if the declarationAnnuelleDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/declaration-annuelles")
    public ResponseEntity<DeclarationAnnuelleDTO> updateDeclarationAnnuelle(@Valid @RequestBody DeclarationAnnuelleDTO declarationAnnuelleDTO) throws URISyntaxException {
        log.debug("REST request to update DeclarationAnnuelle : {}", declarationAnnuelleDTO);
        if (declarationAnnuelleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DeclarationAnnuelleDTO result = declarationAnnuelleService.save(declarationAnnuelleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, declarationAnnuelleDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /declaration-annuelles : get all the declarationAnnuelles.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of declarationAnnuelles in body
     */
    @GetMapping("/declaration-annuelles")
    public ResponseEntity<List<DeclarationAnnuelleDTO>> getAllDeclarationAnnuelles(Pageable pageable) {
        log.debug("REST request to get a page of DeclarationAnnuelles");
        Page<DeclarationAnnuelleDTO> page = declarationAnnuelleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/declaration-annuelles");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /declaration-annuelles/:id : get the "id" declarationAnnuelle.
     *
     * @param id the id of the declarationAnnuelleDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the declarationAnnuelleDTO, or with status 404 (Not Found)
     */
    @GetMapping("/declaration-annuelles/{id}")
    public ResponseEntity<DeclarationAnnuelleDTO> getDeclarationAnnuelle(@PathVariable Long id) {
        log.debug("REST request to get DeclarationAnnuelle : {}", id);
        Optional<DeclarationAnnuelleDTO> declarationAnnuelleDTO = declarationAnnuelleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(declarationAnnuelleDTO);
    }

    /**
     * DELETE  /declaration-annuelles/:id : delete the "id" declarationAnnuelle.
     *
     * @param id the id of the declarationAnnuelleDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/declaration-annuelles/{id}")
    public ResponseEntity<Void> deleteDeclarationAnnuelle(@PathVariable Long id) {
        log.debug("REST request to delete DeclarationAnnuelle : {}", id);
        declarationAnnuelleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
