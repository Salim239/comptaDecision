package com.growup.comptadecision.service;

import com.growup.comptadecision.repository.ImpotMensuelDetailRepository;
import com.growup.comptadecision.service.dto.ImpotMensuelDetailDTO;
import com.growup.comptadecision.service.mapper.ImpotMensuelDetailMapper;
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
public class ImpotMensuelDetailService {

    private final Logger log = LoggerFactory.getLogger(ImpotMensuelDetailService.class);

    private final ImpotMensuelDetailRepository impotMensuelDetailRepository;

    private final ImpotMensuelDetailMapper impotMensuelDetailMapper;

    public ImpotMensuelDetailService(ImpotMensuelDetailRepository impotMensuelDetailRepository, ImpotMensuelDetailMapper impotMensuelDetailMapper) {
        this.impotMensuelDetailRepository = impotMensuelDetailRepository;
        this.impotMensuelDetailMapper = impotMensuelDetailMapper;
    }

    public List<ImpotMensuelDetailDTO> findAll() {
        return impotMensuelDetailRepository.findAll().stream()
            .map(impotMensuelDetail -> impotMensuelDetailMapper.toDto(impotMensuelDetail))
            .collect(Collectors.toList());
    }


}
