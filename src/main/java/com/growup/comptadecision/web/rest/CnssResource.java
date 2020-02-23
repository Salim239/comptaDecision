package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.domain.enumeration.TypeCnss;
import com.growup.comptadecision.service.CnssService;
import com.growup.comptadecision.service.dto.CnssDTO;
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
 * REST controller for managing Cnss.
 */
@RestController
@RequestMapping("/api")
public class CnssResource {

    private final Logger log = LoggerFactory.getLogger(CnssResource.class);

    private static final String ENTITY_NAME = "cnss";

    private final CnssService cnssService;

    public CnssResource(CnssService cnssService) {
        this.cnssService = cnssService;
    }


    @GetMapping("/cnss/init/{id}/{annee}/{typeCnss}/{trimestre}")
    public ResponseEntity<CnssDTO> init(@PathVariable Long id, @PathVariable Integer annee,
                                                       @PathVariable String typeCnss,
                                                       @PathVariable Integer trimestre) {
        CnssDTO cnssDTO = cnssService.init(id, annee, TypeCnss.valueOf(typeCnss), trimestre);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, null)).body(cnssDTO);
    }

    /**
     * POST  /cnss : Create a new cnss.
     *
     * @param cnssDTO the cnssDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cnssDTO, or with status 400 (Bad Request) if the cnss has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cnss")
    public ResponseEntity<CnssDTO> createCnss(@Valid @RequestBody CnssDTO cnssDTO) throws URISyntaxException {
        log.debug("REST request to save Cnss : {}", cnssDTO);
        if (cnssDTO.getId() != null) {
            throw new BadRequestAlertException("A new cnss cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CnssDTO result = cnssService.save(cnssDTO);
        return ResponseEntity.created(new URI("/api/cnss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cnss : Updates an existing cnss.
     *
     * @param cnssDTO the cnssDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cnssDTO,
     * or with status 400 (Bad Request) if the cnssDTO is not valid,
     * or with status 500 (Internal Server Error) if the cnssDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cnss")
    public ResponseEntity<CnssDTO> updateCnss(@Valid @RequestBody CnssDTO cnssDTO) throws URISyntaxException {
        log.debug("REST request to update Cnss : {}", cnssDTO);
        if (cnssDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CnssDTO result = cnssService.save(cnssDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cnssDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cnss : get all the cnss.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of cnss in body
     */
    @GetMapping("/cnss")
    public ResponseEntity<List<CnssDTO>> getAllCnss(Pageable pageable) {
        log.debug("REST request to get a page of Cnss");
        Page<CnssDTO> page = cnssService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cnss");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /cnss/:id : get the "id" cnss.
     *
     * @param id the id of the cnssDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cnssDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cnss/{id}")
    public ResponseEntity<CnssDTO> getCnss(@PathVariable Long id) {
        log.debug("REST request to get Cnss : {}", id);
        Optional<CnssDTO> cnssDTO = cnssService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cnssDTO);
    }

    /**
     * DELETE  /cnss/:id : delete the "id" cnss.
     *
     * @param id the id of the cnssDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cnss/{id}")
    public ResponseEntity<Void> deleteCnss(@PathVariable Long id) {
        log.debug("REST request to delete Cnss : {}", id);
        cnssService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
