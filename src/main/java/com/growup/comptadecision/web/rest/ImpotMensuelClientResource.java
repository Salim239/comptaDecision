package com.growup.comptadecision.web.rest;
import com.growup.comptadecision.service.ImpotMensuelClientService;
import com.growup.comptadecision.web.rest.errors.BadRequestAlertException;
import com.growup.comptadecision.web.rest.util.HeaderUtil;
import com.growup.comptadecision.web.rest.util.PaginationUtil;
import com.growup.comptadecision.service.dto.ImpotMensuelClientDTO;
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
 * REST controller for managing ImpotMensuelClient.
 */
@RestController
@RequestMapping("/api")
public class ImpotMensuelClientResource {

    private final Logger log = LoggerFactory.getLogger(ImpotMensuelClientResource.class);

    private static final String ENTITY_NAME = "impotMensuelClient";

    private final ImpotMensuelClientService impotMensuelClientService;

    public ImpotMensuelClientResource(ImpotMensuelClientService impotMensuelClientService) {
        this.impotMensuelClientService = impotMensuelClientService;
    }

    /**
     * POST  /impot-mensuel-clients : Create a new impotMensuelClient.
     *
     * @param impotMensuelClientDTO the impotMensuelClientDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new impotMensuelClientDTO, or with status 400 (Bad Request) if the impotMensuelClient has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/impot-mensuel-clients")
    public ResponseEntity<ImpotMensuelClientDTO> createImpotMensuelClient(@Valid @RequestBody ImpotMensuelClientDTO impotMensuelClientDTO) throws URISyntaxException {
        log.debug("REST request to save ImpotMensuelClient : {}", impotMensuelClientDTO);
        if (impotMensuelClientDTO.getId() != null) {
            throw new BadRequestAlertException("A new impotMensuelClient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ImpotMensuelClientDTO result = impotMensuelClientService.save(impotMensuelClientDTO);
        return ResponseEntity.created(new URI("/api/impot-mensuel-clients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /impot-mensuel-clients : Updates an existing impotMensuelClient.
     *
     * @param impotMensuelClientDTO the impotMensuelClientDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated impotMensuelClientDTO,
     * or with status 400 (Bad Request) if the impotMensuelClientDTO is not valid,
     * or with status 500 (Internal Server Error) if the impotMensuelClientDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/impot-mensuel-clients")
    public ResponseEntity<ImpotMensuelClientDTO> updateImpotMensuelClient(@Valid @RequestBody ImpotMensuelClientDTO impotMensuelClientDTO) throws URISyntaxException {
        log.debug("REST request to update ImpotMensuelClient : {}", impotMensuelClientDTO);
        if (impotMensuelClientDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ImpotMensuelClientDTO result = impotMensuelClientService.save(impotMensuelClientDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, impotMensuelClientDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /impot-mensuel-clients : get all the impotMensuelClients.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of impotMensuelClients in body
     */
    @GetMapping("/impot-mensuel-clients")
    public ResponseEntity<List<ImpotMensuelClientDTO>> getAllImpotMensuelClients(Pageable pageable) {
        log.debug("REST request to get a page of ImpotMensuelClients");
        Page<ImpotMensuelClientDTO> page = impotMensuelClientService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/impot-mensuel-clients");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /impot-mensuel-clients/:id : get the "id" impotMensuelClient.
     *
     * @param id the id of the impotMensuelClientDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the impotMensuelClientDTO, or with status 404 (Not Found)
     */
    @GetMapping("/impot-mensuel-clients/{id}")
    public ResponseEntity<ImpotMensuelClientDTO> getImpotMensuelClient(@PathVariable Long id) {
        log.debug("REST request to get ImpotMensuelClient : {}", id);
        Optional<ImpotMensuelClientDTO> impotMensuelClientDTO = impotMensuelClientService.findOne(id);
        return ResponseUtil.wrapOrNotFound(impotMensuelClientDTO);
    }

    /**
     * DELETE  /impot-mensuel-clients/:id : delete the "id" impotMensuelClient.
     *
     * @param id the id of the impotMensuelClientDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/impot-mensuel-clients/{id}")
    public ResponseEntity<Void> deleteImpotMensuelClient(@PathVariable Long id) {
        log.debug("REST request to delete ImpotMensuelClient : {}", id);
        impotMensuelClientService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
