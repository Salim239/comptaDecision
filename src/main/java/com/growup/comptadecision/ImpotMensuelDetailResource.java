package com.growup.comptadecision;

import com.growup.comptadecision.service.ImpotMensuelDetailService;
import com.growup.comptadecision.service.dto.ImpotMensuelDetailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing ImpotMensuelDetail.
 */
@RestController
@RequestMapping("/api")
public class ImpotMensuelDetailResource {

    private final Logger log = LoggerFactory.getLogger(ImpotMensuelDetailResource.class);

    private static final String ENTITY_NAME = "impotMensuelDetail";

    private final ImpotMensuelDetailService impotMensuelDetailService;

    public ImpotMensuelDetailResource(ImpotMensuelDetailService impotMensuelDetailService) {
        this.impotMensuelDetailService = impotMensuelDetailService;
    }

    /**
     * GET  /impot-mensuel-details : get all the impotMensuelDetails.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of impotMensuelDetails in body
     */
    @GetMapping("/impot-mensuel-details")
    public List<ImpotMensuelDetailDTO> getAllImpotMensuelDetails() {
        log.debug("REST request to get all ImpotMensuelDetails");
        return impotMensuelDetailService.findAll();
    }

}
