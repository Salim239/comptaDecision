package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.service.ApplicationUserService;
import com.growup.comptadecision.service.dto.ApplicationUserDTO;
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
 * REST controller for managing ApplicationUser.
 */
@RestController
@RequestMapping("/api")
public class ApplicationUserResource {

    private final Logger log = LoggerFactory.getLogger(ApplicationUserResource.class);

    private static final String ENTITY_NAME = "applicationUser";

    private final ApplicationUserService applicationUserService;

    public ApplicationUserResource(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    /**
     * POST  /application-users : Create a new applicationUser.
     *
     * @param applicationUserDTO the applicationUserDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new applicationUserDTO, or with status 400 (Bad Request) if the applicationUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/application-users")
    public ResponseEntity<ApplicationUserDTO> createApplicationUser(@Valid @RequestBody ApplicationUserDTO applicationUserDTO) throws URISyntaxException {
        log.debug("REST request to save ApplicationUser : {}", applicationUserDTO);
        if (applicationUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new applicationUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApplicationUserDTO result = applicationUserService.save(applicationUserDTO);
        return ResponseEntity.created(new URI("/api/application-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /application-users : Updates an existing applicationUser.
     *
     * @param applicationUserDTO the applicationUserDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated applicationUserDTO,
     * or with status 400 (Bad Request) if the applicationUserDTO is not valid,
     * or with status 500 (Internal Server Error) if the applicationUserDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/application-users")
    public ResponseEntity<ApplicationUserDTO> updateApplicationUser(@Valid @RequestBody ApplicationUserDTO applicationUserDTO) throws URISyntaxException {
        log.debug("REST request to update ApplicationUser : {}", applicationUserDTO);
        if (applicationUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApplicationUserDTO result = applicationUserService.save(applicationUserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, applicationUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /application-users : get all the applicationUsers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of applicationUsers in body
     */
    @GetMapping("/application-users")
    public ResponseEntity<List<ApplicationUserDTO>> getAllApplicationUsers(Pageable pageable) {
        log.debug("REST request to get a page of ApplicationUsers");
        Page<ApplicationUserDTO> page = applicationUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/application-users");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /application-users/:id : get the "id" applicationUser.
     *
     * @param id the id of the applicationUserDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the applicationUserDTO, or with status 404 (Not Found)
     */
    @GetMapping("/application-users/{id}")
    public ResponseEntity<ApplicationUserDTO> getApplicationUser(@PathVariable Long id) {
        log.debug("REST request to get ApplicationUser : {}", id);
        Optional<ApplicationUserDTO> applicationUserDTO = applicationUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(applicationUserDTO);
    }

    /**
     * DELETE  /application-users/:id : delete the "id" applicationUser.
     *
     * @param id the id of the applicationUserDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/application-users/{id}")
    public ResponseEntity<Void> deleteApplicationUser(@PathVariable Long id) {
        log.debug("REST request to delete ApplicationUser : {}", id);
        applicationUserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
