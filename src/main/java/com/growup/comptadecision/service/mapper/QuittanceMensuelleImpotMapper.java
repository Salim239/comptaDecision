package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.QuittanceMensuelleImpot;
import com.growup.comptadecision.domain.QuittanceMensuelleImpotDetail;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

/**
 * Mapper for the entity QuittanceMensuelleImpot and its DTO QuittanceMensuelleImpotDTO.
 */
@Mapper(componentModel = "spring", uses = {FicheClientMapper.class, QuittanceMensuelleImpotDetailMapper.class})
public interface QuittanceMensuelleImpotMapper extends EntityMapper<QuittanceMensuelleImpotDTO, QuittanceMensuelleImpot> {

    default BigDecimal sum(QuittanceMensuelleImpot quittanceMensuelleImpot) {
       return quittanceMensuelleImpot.getQuittanceMensuelleImpotDetails().stream()
               .filter(quittanceMensuelleImpotDetail -> quittanceMensuelleImpotDetail.getMontantTotal() != null)
               .map(QuittanceMensuelleImpotDetail::getMontantTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Mapping(source = "ficheClient.id", target = "ficheClientId")
    @Mapping(source = "ficheClient.designation", target = "ficheClientDesignation")
    @Mapping(source = "ficheClient.matriculeFiscale", target = "ficheClientMatriculeFiscale")
    @Mapping(source = "ficheClient.registreCommerce", target = "ficheClientRegistreCommerce")
    @Mapping(source = "ficheClient.dateCreation", target = "ficheClientDateCreation")
    QuittanceMensuelleImpotDTO toDto(QuittanceMensuelleImpot quittanceMensuelleImpot);

    @Mapping(source = "ficheClientId", target = "ficheClient")
    @Mapping(target = "quittanceMensuelleImpotDetails", expression = "java(" +
            "quittanceMensuelleImpotDTO.getQuittanceMensuelleImpotDetails().stream().map(quittanceMensuelleImpotDetailDTO ->  {" +
            "com.growup.comptadecision.domain.QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail = quittanceMensuelleImpotDetailMapper.toEntity(quittanceMensuelleImpotDetailDTO);" +
            "quittanceMensuelleImpotDetail.setQuittanceMensuelleImpot(quittanceMensuelleImpot);" +
            "return quittanceMensuelleImpotDetail;})" +
            ".collect(java.util.stream.Collectors.toList()))")
    @Mapping(target = "montantTotal", expression = "java(this.sum(quittanceMensuelleImpot))", dependsOn = {"quittanceMensuelleImpotDetails"})
    QuittanceMensuelleImpot toEntity(QuittanceMensuelleImpotDTO quittanceMensuelleImpotDTO);

    default QuittanceMensuelleImpot fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuittanceMensuelleImpot quittanceMensuelleImpot = new QuittanceMensuelleImpot();
        quittanceMensuelleImpot.setId(id);
        return quittanceMensuelleImpot;
    }
}
