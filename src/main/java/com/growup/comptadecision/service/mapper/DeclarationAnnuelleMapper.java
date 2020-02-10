package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.DeclarationAnnuelle;
import com.growup.comptadecision.service.dto.DeclarationAnnuelleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity DeclarationAnnuelle and its DTO DeclarationAnnuelleDTO.
 */
@Mapper(componentModel = "spring", uses = {FicheClientMapper.class, DeclarationAnnuelleDetailMapper.class})
public interface DeclarationAnnuelleMapper extends EntityMapper<DeclarationAnnuelleDTO, DeclarationAnnuelle> {

    @Mapping(source = "ficheClient.id", target = "ficheClientId")
    @Mapping(source = "ficheClient.designation", target = "ficheClientDesignation")
    @Mapping(source = "ficheClient.matriculeFiscale", target = "ficheClientMatriculeFiscale")
    @Mapping(source = "ficheClient.registreCommerce", target = "ficheClientRegistreCommerce")
    @Mapping(source = "ficheClient.dateCreation", target = "ficheClientDateCreation")
//    @Mapping(target = "declarationAnnuelleDetails", expression = "java(" +
//            "declarationAnnuelle.getDeclarationAnnuelleDetails().stream().map(declarationAnnuelleDetailDTO ->  {" +
//            "com.growup.comptadecision.domain.DeclarationAnnuelleDetail declarationAnnuelleDetail = declarationAnnuelleDetailMapper.toEntity(declarationAnnuelleDetailDTO);" +
//            "declarationAnnuelleDetail.setDeclarationAnnuelle(declarationAnnuelle);" +
//            "return quittanceMensuelleImpotDetail;})" +
//            ".collect(java.util.stream.Collectors.toList()))")
    DeclarationAnnuelleDTO toDto(DeclarationAnnuelle declarationAnnuelle);

    @Mapping(source = "ficheClientId", target = "ficheClient")
    @Mapping(target = "declarationAnnuelleDetails", expression = "java(" +
            "declarationAnnuelleDTO.getDeclarationAnnuelleDetails().stream().map(" +
            "declarationAnnuelleDetailDTO -> {" +
            "com.growup.comptadecision.domain.DecalrationAnnuelleDetail declarationAnnuelleDetail = " +
            "declarationAnnuelleDetailMapper.toEntity(declarationAnnuelleDetailDTO);" +
            "declarationAnnuelleDetail.setDeclarationAnnuelle(declarationAnnuelle);" +
            "return declarationAnnuelleDetail;})" +
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
