package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.QuittanceMensuelle;
import com.growup.comptadecision.domain.QuittanceMensuelleLigne;
import com.growup.comptadecision.service.dto.QuittanceMensuelleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

/**
 * Mapper for the entity QuittanceMensuelle and its DTO QuittanceMensuelleDTO.
 */
@Mapper(componentModel = "spring", uses = {FicheClientMapper.class, QuittanceMensuelleLigneMapper.class})
public interface QuittanceMensuelleMapper extends EntityMapper<QuittanceMensuelleDTO, QuittanceMensuelle> {

    default BigDecimal sum(QuittanceMensuelle quittanceMensuelle) {
       return quittanceMensuelle.getQuittanceMensuelleLignes().stream()
               .filter(quittanceMensuelleLigne -> quittanceMensuelleLigne.getMontantTotal() != null &&
                       quittanceMensuelleLigne.getMontantTotal().compareTo(BigDecimal.ZERO) >= 0)
               .map(QuittanceMensuelleLigne::getMontantTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Mapping(source = "ficheClient.id", target = "ficheClientId")
    @Mapping(source = "ficheClient.designation", target = "ficheClientDesignation")
    @Mapping(source = "ficheClient.matriculeFiscale", target = "ficheClientMatriculeFiscale")
    @Mapping(source = "ficheClient.registreCommerce", target = "ficheClientRegistreCommerce")
    @Mapping(source = "ficheClient.dateCreation", target = "ficheClientDateCreation")
    @Mapping(target = "parentQuittanceId", expression = "java(quittanceMensuelle.getParentQuittance() != null ? quittanceMensuelle.getParentQuittance().getId() : null)")
    QuittanceMensuelleDTO toDto(QuittanceMensuelle quittanceMensuelle);

    @Mapping(source = "ficheClientId", target = "ficheClient")
    @Mapping(target = "quittanceMensuelleLignes", expression = "java(" +
            "quittanceMensuelleDTO.getQuittanceMensuelleLignes().stream().map(quittanceMensuelleLigneDTO ->  {" +
            "com.growup.comptadecision.domain.QuittanceMensuelleLigne quittanceMensuelleLigne = quittanceMensuelleLigneMapper.toEntity(quittanceMensuelleLigneDTO);" +
            "quittanceMensuelleLigne.setQuittanceMensuelle(quittanceMensuelle);" +
            "return quittanceMensuelleLigne;})" +
            ".collect(java.util.stream.Collectors.toList()))")
    @Mapping(target = "montantTotal", expression = "java(this.sum(quittanceMensuelle))", dependsOn = {"quittanceMensuelleLignes"})
    QuittanceMensuelle toEntity(QuittanceMensuelleDTO quittanceMensuelleDTO);

    default QuittanceMensuelle fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuittanceMensuelle quittanceMensuelle = new QuittanceMensuelle();
        quittanceMensuelle.setId(id);
        return quittanceMensuelle;
    }
}
