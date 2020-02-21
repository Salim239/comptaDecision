package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.service.DeclarationEmployeurAnnuelleService;
import com.growup.comptadecision.service.dto.DeclarationEmployeurAnnuelleDTO;
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
 * REST controller for managing DeclarationEmployeurAnnuelle.
 */
@RestController
@RequestMapping("/api")
public class DeclarationEmployeurAnnuelleResource {

    private final Logger log = LoggerFactory.getLogger(DeclarationEmployeurAnnuelleResource.class);

    private static final String ENTITY_NAME = "declarationEmployeurAnnuelle";

    private final DeclarationEmployeurAnnuelleService declarationEmployeurAnnuelleService;

    public DeclarationEmployeurAnnuelleResource(DeclarationEmployeurAnnuelleService declarationEmployeurAnnuelleService) {
        this.declarationEmployeurAnnuelleService = declarationEmployeurAnnuelleService;
    }

    @GetMapping("/declaration-employeur-annuelles/init/{id}/{annee}")
    public ResponseEntity<DeclarationEmployeurAnnuelleDTO> init(@PathVariable Long id, @PathVariable Integer annee) {
        log.debug("REST request to init empty DeclarationEmployeurAnnuelle");
        DeclarationEmployeurAnnuelleDTO quittanceMensuelleImpotDTO = declarationEmployeurAnnuelleService.init(id, annee);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, null)).body(quittanceMensuelleImpotDTO);
    }

    /**
     * POST  /declaration-employeur-annuelles : Create a new declarationEmployeurAnnuelle.
     *
     * @param declarationEmployeurAnnuelleDTO the declarationEmployeurAnnuelleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new declarationEmployeurAnnuelleDTO, or with status 400 (Bad Request) if the declarationEmployeurAnnuelle has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/declaration-employeur-annuelles")
    public ResponseEntity<DeclarationEmployeurAnnuelleDTO> createDeclarationEmployeurAnnuelle(@Valid @RequestBody DeclarationEmployeurAnnuelleDTO declarationEmployeurAnnuelleDTO) throws URISyntaxException {
        log.debug("REST request to save DeclarationEmployeurAnnuelle : {}", declarationEmployeurAnnuelleDTO);
        if (declarationEmployeurAnnuelleDTO.getId() != null) {
            throw new BadRequestAlertException("A new declarationEmployeurAnnuelle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeclarationEmployeurAnnuelleDTO result = declarationEmployeurAnnuelleService.save(declarationEmployeurAnnuelleDTO);
        return ResponseEntity.created(new URI("/api/declaration-employeur-annuelles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /declaration-employeur-annuelles : Updates an existing declarationEmployeurAnnuelle.
     *
     * @param declarationEmployeurAnnuelleDTO the declarationEmployeurAnnuelleDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated declarationEmployeurAnnuelleDTO,
     * or with status 400 (Bad Request) if the declarationEmployeurAnnuelleDTO is not valid,
     * or with status 500 (Internal Server Error) if the declarationEmployeurAnnuelleDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/declaration-employeur-annuelles")
    public ResponseEntity<DeclarationEmployeurAnnuelleDTO> updateDeclarationEmployeurAnnuelle(@Valid @RequestBody DeclarationEmployeurAnnuelleDTO declarationEmployeurAnnuelleDTO) throws URISyntaxException {
        log.debug("REST request to update DeclarationEmployeurAnnuelle : {}", declarationEmployeurAnnuelleDTO);
        if (declarationEmployeurAnnuelleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DeclarationEmployeurAnnuelleDTO result = declarationEmployeurAnnuelleService.save(declarationEmployeurAnnuelleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, declarationEmployeurAnnuelleDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /declaration-employeur-annuelles : get all the declarationEmployeurAnnuelles.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of declarationEmployeurAnnuelles in body
     */
    @GetMapping("/declaration-employeur-annuelles")
    public ResponseEntity<List<DeclarationEmployeurAnnuelleDTO>> getAllDeclarationEmployeurAnnuelles(Pageable pageable) {
        log.debug("REST request to get a page of DeclarationEmployeurAnnuelles");
        Page<DeclarationEmployeurAnnuelleDTO> page = declarationEmployeurAnnuelleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/declaration-employeur-annuelles");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /declaration-employeur-annuelles/:id : get the "id" declarationEmployeurAnnuelle.
     *
     * @param id the id of the declarationEmployeurAnnuelleDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the declarationEmployeurAnnuelleDTO, or with status 404 (Not Found)
     */
    @GetMapping("/declaration-employeur-annuelles/{id}")
    public ResponseEntity<DeclarationEmployeurAnnuelleDTO> getDeclarationEmployeurAnnuelle(@PathVariable Long id) {
        log.debug("REST request to get DeclarationEmployeurAnnuelle : {}", id);
        Optional<DeclarationEmployeurAnnuelleDTO> declarationEmployeurAnnuelleDTO = declarationEmployeurAnnuelleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(declarationEmployeurAnnuelleDTO);
    }

    /**
     * DELETE  /declaration-employeur-annuelles/:id : delete the "id" declarationEmployeurAnnuelle.
     *
     * @param id the id of the declarationEmployeurAnnuelleDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/declaration-employeur-annuelles/{id}")
    public ResponseEntity<Void> deleteDeclarationEmployeurAnnuelle(@PathVariable Long id) {
        log.debug("REST request to delete DeclarationEmployeurAnnuelle : {}", id);
        declarationEmployeurAnnuelleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
