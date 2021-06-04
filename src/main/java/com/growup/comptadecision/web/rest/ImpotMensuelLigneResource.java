package com.growup.comptadecision.web.rest;

import com.growup.comptadecision.service.ImpotMensuelLigneService;
import com.growup.comptadecision.service.dto.ImpotMensuelLigneDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing ImpotMensuelLigne.
 */
@RestController
@RequestMapping("/api")
public class ImpotMensuelLigneResource {

    private final Logger log = LoggerFactory.getLogger(ImpotMensuelLigneResource.class);

    private static final String ENTITY_NAME = "impotMensuelLigne";

    private final ImpotMensuelLigneService impotMensuelLigneService;

    public ImpotMensuelLigneResource(ImpotMensuelLigneService impotMensuelLigneService) {
        this.impotMensuelLigneService = impotMensuelLigneService;
    }

    /**
     * GET  /impot-mensuel-details : get all the impotMensuelLignes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of impotMensuelLignes in body
     */
    @GetMapping("/impot-mensuel-details")
    public List<ImpotMensuelLigneDTO> getAllImpotMensuelLignes() {
        log.debug("REST request to get all ImpotMensuelLignes");
        return impotMensuelLigneService.findAll();
    }

}
