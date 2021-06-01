package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.service.ImpotMensuelService;
import com.growup.comptadecision.service.dto.ImpotMensuelDTO;
import com.growup.comptadecision.web.rest.errors.BadRequestAlertException;
import com.growup.comptadecision.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ImpotMensuel.
 */
@RestController
@RequestMapping("/api")
public class ImpotMensuelResource {

    private final Logger log = LoggerFactory.getLogger(ImpotMensuelResource.class);

    private static final String ENTITY_NAME = "impotMensuel";

    private final ImpotMensuelService impotMensuelService;

    public ImpotMensuelResource(ImpotMensuelService impotMensuelService) {
        this.impotMensuelService = impotMensuelService;
    }

    /**
     * POST  /impot-mensuels : Create a new impotMensuel.
     *
     * @param impotMensuelDTO the impotMensuelDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new impotMensuelDTO, or with status 400 (Bad Request) if the impotMensuel has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/impot-mensuels")
    public ResponseEntity<ImpotMensuelDTO> createImpotMensuel(@Valid @RequestBody ImpotMensuelDTO impotMensuelDTO) throws URISyntaxException {
        log.debug("REST request to save ImpotMensuel : {}", impotMensuelDTO);
        if (impotMensuelDTO.getId() != null) {
            throw new BadRequestAlertException("A new impotMensuel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ImpotMensuelDTO result = impotMensuelService.save(impotMensuelDTO);
        return ResponseEntity.created(new URI("/api/impot-mensuels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /impot-mensuels : Updates an existing impotMensuel.
     *
     * @param impotMensuelDTO the impotMensuelDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated impotMensuelDTO,
     * or with status 400 (Bad Request) if the impotMensuelDTO is not valid,
     * or with status 500 (Internal Server Error) if the impotMensuelDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/impot-mensuels")
    public ResponseEntity<ImpotMensuelDTO> updateImpotMensuel(@Valid @RequestBody ImpotMensuelDTO impotMensuelDTO) throws URISyntaxException {
        log.debug("REST request to update ImpotMensuel : {}", impotMensuelDTO);
        if (impotMensuelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ImpotMensuelDTO result = impotMensuelService.save(impotMensuelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, impotMensuelDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /impot-mensuels : get all the impotMensuels.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of impotMensuels in body
     */
    @GetMapping("/impot-mensuels")
    public List<ImpotMensuelDTO> getAllImpotMensuels() {
        log.debug("REST request to get all ImpotMensuels");
        return impotMensuelService.findAll();
    }

    /**
     * GET  /impot-mensuels/:id : get the "id" impotMensuel.
     *
     * @param id the id of the impotMensuelDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the impotMensuelDTO, or with status 404 (Not Found)
     */
    @GetMapping("/impot-mensuels/{id}")
    public ResponseEntity<ImpotMensuelDTO> getImpotMensuel(@PathVariable Long id) {
        log.debug("REST request to get ImpotMensuel : {}", id);
        Optional<ImpotMensuelDTO> impotMensuelDTO = impotMensuelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(impotMensuelDTO);
    }

    /**
     * DELETE  /impot-mensuels/:id : delete the "id" impotMensuel.
     *
     * @param id the id of the impotMensuelDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/impot-mensuels/{id}")
    public ResponseEntity<Void> deleteImpotMensuel(@PathVariable Long id) {
        log.debug("REST request to delete ImpotMensuel : {}", id);
        impotMensuelService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/impot-mensuels/parents")
    public List<ImpotMensuelDTO> getParentsImpotMensuels() {
        log.debug("REST request to get all parents ImpotMensuels without children");
        return impotMensuelService.findParentsWithoutChildren();
    }
}
