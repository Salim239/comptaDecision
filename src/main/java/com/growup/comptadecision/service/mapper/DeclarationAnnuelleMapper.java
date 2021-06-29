package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.DeclarationAnnuelle;
import com.growup.comptadecision.service.dto.DeclarationAnnuelleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity DeclarationAnnuelle and its DTO DeclarationAnnuelleDTO.
 */
@Mapper(componentModel = "spring", uses = {FicheClientMapper.class, DeclarationAnnuelleLigneMapper.class})
public interface DeclarationAnnuelleMapper extends EntityMapper<DeclarationAnnuelleDTO, DeclarationAnnuelle> {

    @Mapping(source = "ficheClient.id", target = "ficheClientId")
    @Mapping(source = "ficheClient.designation", target = "ficheClientDesignation")
    @Mapping(source = "ficheClient.matriculeFiscale", target = "ficheClientMatriculeFiscale")
    @Mapping(source = "ficheClient.registreCommerce", target = "ficheClientRegistreCommerce")
    @Mapping(source = "ficheClient.dateCreation", target = "ficheClientDateCreation")
//    @Mapping(target = "declarationAnnuelleLignes", expression = "java(" +
//            "declarationAnnuelle.getDeclarationAnnuelleLignes().stream().map(declarationAnnuelleLigneDTO ->  {" +
//            "com.growup.comptadecision.domain.DeclarationAnnuelleLigne declarationAnnuelleLigne = declarationAnnuelleLigneMapper.toEntity(declarationAnnuelleLigneDTO);" +
//            "declarationAnnuelleLigne.setDeclarationAnnuelle(declarationAnnuelle);" +
//            "return quittanceMensuelleLigne;})" +
//            ".collect(java.util.stream.Collectors.toList()))")
    DeclarationAnnuelleDTO toDto(DeclarationAnnuelle declarationAnnuelle);

    @Mapping(source = "ficheClientId", target = "ficheClient")
    @Mapping(target = "declarationAnnuelleLignes", expression = "java(" +
            "declarationAnnuelleDTO.getDeclarationAnnuelleLignes().stream().map(" +
            "declarationAnnuelleLigneDTO -> {" +
            "com.growup.comptadecision.domain.DeclarationAnnuelleLigne declarationAnnuelleLigne = " +
            "declarationAnnuelleLigneMapper.toEntity(declarationAnnuelleLigneDTO);" +
            "declarationAnnuelleLigne.setDeclarationAnnuelle(declarationAnnuelle);" +
            "return declarationAnnuelleLigne;})" +
            ".collect(java.util.stream.Collectors.toList()))")
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
