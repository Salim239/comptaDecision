package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.*;
import com.growup.comptadecision.service.dto.DeclarationAnnuelleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DeclarationAnnuelle and its DTO DeclarationAnnuelleDTO.
 */
@Mapper(componentModel = "spring", uses = {FicheClientMapper.class})
public interface DeclarationAnnuelleMapper extends EntityMapper<DeclarationAnnuelleDTO, DeclarationAnnuelle> {

    @Mapping(source = "ficheClient.id", target = "ficheClientId")
    @Mapping(source = "ficheClient.designation", target = "ficheClientDesignation")
    @Mapping(source = "ficheClient.matriculeFiscale", target = "ficheClientMatriculeFiscale")
    @Mapping(source = "ficheClient.registreCommerce", target = "ficheClientRegistreCommerce")
    DeclarationAnnuelleDTO toDto(DeclarationAnnuelle declarationAnnuelle);

    @Mapping(source = "ficheClientId", target = "ficheClient")
    DeclarationAnnuelle toEntity(DeclarationAnnuelleDTO declarationAnnuelleDTO);

    default DeclarationAnnuelle fromId(Long id) {
        if (id == null) {
            return null;
        }
        DeclarationAnnuelle declarationAnnuelle = new DeclarationAnnuelle();
        declarationAnnuelle.setId(id);
        return declarationAnnuelle;
    }
}
