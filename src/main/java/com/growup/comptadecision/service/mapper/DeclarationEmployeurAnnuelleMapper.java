package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.*;
import com.growup.comptadecision.service.dto.DeclarationEmployeurAnnuelleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DeclarationEmployeurAnnuelle and its DTO DeclarationEmployeurAnnuelleDTO.
 */
@Mapper(componentModel = "spring", uses = {FicheClientMapper.class})
public interface DeclarationEmployeurAnnuelleMapper extends EntityMapper<DeclarationEmployeurAnnuelleDTO, DeclarationEmployeurAnnuelle> {

    @Mapping(source = "ficheClient.id", target = "ficheClientId")
    @Mapping(source = "ficheClient.designation", target = "ficheClientDesignation")
    @Mapping(source = "ficheClient.matriculeFiscale", target = "ficheClientMatriculeFiscale")
    @Mapping(source = "ficheClient.registreCommerce", target = "ficheClientRegistreCommerce")
    DeclarationEmployeurAnnuelleDTO toDto(DeclarationEmployeurAnnuelle declarationEmployeurAnnuelle);

    @Mapping(source = "ficheClientId", target = "ficheClient")
    DeclarationEmployeurAnnuelle toEntity(DeclarationEmployeurAnnuelleDTO declarationEmployeurAnnuelleDTO);

    default DeclarationEmployeurAnnuelle fromId(Long id) {
        if (id == null) {
            return null;
        }
        DeclarationEmployeurAnnuelle declarationEmployeurAnnuelle = new DeclarationEmployeurAnnuelle();
        declarationEmployeurAnnuelle.setId(id);
        return declarationEmployeurAnnuelle;
    }
}
