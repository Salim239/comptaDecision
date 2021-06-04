package com.growup.comptadecision.service;

import com.growup.comptadecision.repository.ImpotMensuelLigneRepository;
import com.growup.comptadecision.service.dto.ImpotMensuelLigneDTO;
import com.growup.comptadecision.service.mapper.ImpotMensuelLigneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ImpotMensuel.
 */
@Service
@Transactional
public class ImpotMensuelLigneService {

    private final Logger log = LoggerFactory.getLogger(ImpotMensuelLigneService.class);

    private final ImpotMensuelLigneRepository impotMensuelLigneRepository;

    private final ImpotMensuelLigneMapper impotMensuelLigneMapper;

    public ImpotMensuelLigneService(ImpotMensuelLigneRepository impotMensuelLigneRepository, ImpotMensuelLigneMapper impotMensuelLigneMapper) {
        this.impotMensuelLigneRepository = impotMensuelLigneRepository;
        this.impotMensuelLigneMapper = impotMensuelLigneMapper;
    }

    public List<ImpotMensuelLigneDTO> findAll() {
        return impotMensuelLigneRepository.findAll().stream()
            .map(impotMensuelLigne -> impotMensuelLigneMapper.toDto(impotMensuelLigne))
            .collect(Collectors.toList());
    }


}
