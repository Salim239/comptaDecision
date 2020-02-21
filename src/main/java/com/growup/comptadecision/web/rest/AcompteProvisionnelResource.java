package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.service.AcompteProvisionnelService;
import com.growup.comptadecision.service.dto.AcompteProvisionnelDTO;
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
 * REST controller for managing AcompteProvisionnel.
 */
@RestController
@RequestMapping("/api")
public class AcompteProvisionnelResource {

    private final Logger log = LoggerFactory.getLogger(AcompteProvisionnelResource.class);

    private static final String ENTITY_NAME = "acompteProvisionnel";

    private final AcompteProvisionnelService acompteProvisionnelService;

    public AcompteProvisionnelResource(AcompteProvisionnelService acompteProvisionnelService) {
        this.acompteProvisionnelService = acompteProvisionnelService;
    }

    @GetMapping("/acompte-provisionnels/init/{id}/{annee}/{numroAcompte}")
    public ResponseEntity<AcompteProvisionnelDTO> init(@PathVariable Long id, @PathVariable Integer annee,
                                        @PathVariable Integer numroAcompte) {
        AcompteProvisionnelDTO acompteProvisionnelDTO = acompteProvisionnelService.init(id, annee, numroAcompte);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, null)).body(acompteProvisionnelDTO);
    }

    /**
     * POST  /acompte-provisionnels : Create a new acompteProvisionnel.
     *
     * @param acompteProvisionnelDTO the acompteProvisionnelDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new acompteProvisionnelDTO, or with status 400 (Bad Request) if the acompteProvisionnel has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/acompte-provisionnels")
    public ResponseEntity<AcompteProvisionnelDTO> createAcompteProvisionnel(@Valid @RequestBody AcompteProvisionnelDTO acompteProvisionnelDTO) throws URISyntaxException {
        log.debug("REST request to save AcompteProvisionnel : {}", acompteProvisionnelDTO);
        if (acompteProvisionnelDTO.getId() != null) {
            throw new BadRequestAlertException("A new acompteProvisionnel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AcompteProvisionnelDTO result = acompteProvisionnelService.save(acompteProvisionnelDTO);
        return ResponseEntity.created(new URI("/api/acompte-provisionnels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /acompte-provisionnels : Updates an existing acompteProvisionnel.
     *
     * @param acompteProvisionnelDTO the acompteProvisionnelDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated acompteProvisionnelDTO,
     * or with status 400 (Bad Request) if the acompteProvisionnelDTO is not valid,
     * or with status 500 (Internal Server Error) if the acompteProvisionnelDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/acompte-provisionnels")
    public ResponseEntity<AcompteProvisionnelDTO> updateAcompteProvisionnel(@Valid @RequestBody AcompteProvisionnelDTO acompteProvisionnelDTO) throws URISyntaxException {
        log.debug("REST request to update AcompteProvisionnel : {}", acompteProvisionnelDTO);
        if (acompteProvisionnelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AcompteProvisionnelDTO result = acompteProvisionnelService.save(acompteProvisionnelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, acompteProvisionnelDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /acompte-provisionnels : get all the acompteProvisionnels.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of acompteProvisionnels in body
     */
    @GetMapping("/acompte-provisionnels")
    public ResponseEntity<List<AcompteProvisionnelDTO>> getAllAcompteProvisionnels(Pageable pageable) {
        log.debug("REST request to get a page of AcompteProvisionnels");
        Page<AcompteProvisionnelDTO> page = acompteProvisionnelService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/acompte-provisionnels");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /acompte-provisionnels/:id : get the "id" acompteProvisionnel.
     *
     * @param id the id of the acompteProvisionnelDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the acompteProvisionnelDTO, or with status 404 (Not Found)
     */
    @GetMapping("/acompte-provisionnels/{id}")
    public ResponseEntity<AcompteProvisionnelDTO> getAcompteProvisionnel(@PathVariable Long id) {
        log.debug("REST request to get AcompteProvisionnel : {}", id);
        Optional<AcompteProvisionnelDTO> acompteProvisionnelDTO = acompteProvisionnelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(acompteProvisionnelDTO);
    }

    /**
     * DELETE  /acompte-provisionnels/:id : delete the "id" acompteProvisionnel.
     *
     * @param id the id of the acompteProvisionnelDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/acompte-provisionnels/{id}")
    public ResponseEntity<Void> deleteAcompteProvisionnel(@PathVariable Long id) {
        log.debug("REST request to delete AcompteProvisionnel : {}", id);
        acompteProvisionnelService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
