package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.QuittanceMensuelleImpotDetail;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotDTO;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotDetailDTO;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotSousDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

/**
 * Mapper for the entity QuittanceMensuelleImpotDetail and its DTO QuittanceMensuelleImpotDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {QuittanceMensuelleImpotMapper.class, ImpotMensuelMapper.class, QuittanceMensuelleImpotSousDetailMapper.class})
public interface QuittanceMensuelleImpotDetailMapper extends EntityMapper<QuittanceMensuelleImpotDetailDTO, QuittanceMensuelleImpotDetail> {

    default BigDecimal sum(QuittanceMensuelleImpotDetailDTO QuittanceMensuelleImpotDetail) {
        return QuittanceMensuelleImpotDetail.getQuittanceMensuelleImpotSousDetails().stream()
                .filter(quittanceMensuelleImpotSousDetail -> quittanceMensuelleImpotSousDetail.getMontantTotal() != null)
                .map(QuittanceMensuelleImpotSousDetailDTO::getMontantTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Mapping(source = "parentQuittanceMensuelleImpotDetail.id", target = "parentQuittanceMensuelleImpotDetailId")
    @Mapping(source = "parentQuittanceMensuelleImpotDetail.libelle", target = "parentQuittanceMensuelleImpotDetailLibelle")
    QuittanceMensuelleImpotDetailDTO toDto(QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail);

    @Mapping(target = "parentQuittanceMensuelleImpotDetail",
            expression = "java(quittanceMensuelleImpotDetailDTO.getParentQuittanceMensuelleImpotDetailId() == null ? null : " +
                    "new com.growup.comptadecision.domain.QuittanceMensuelleImpotDetail(quittanceMensuelleImpotDetailDTO.getParentQuittanceMensuelleImpotDetailId()))" )
    @Mapping(target = "quittanceMensuelleImpotSousDetails", expression = "java(" +
            "quittanceMensuelleImpotDetailDTO.getQuittanceMensuelleImpotSousDetails().stream().map(quittanceMensuelleImpotSousDetailDTO ->  {" +
            "com.growup.comptadecision.domain.QuittanceMensuelleImpotSousDetail quittanceMensuelleImpotSousDetail = " +
            "quittanceMensuelleImpotSousDetailMapper.toEntity(quittanceMensuelleImpotSousDetailDTO);" +
            "quittanceMensuelleImpotSousDetail.setQuittanceMensuelleImpotDetail(quittanceMensuelleImpotDetail);" +
            "return quittanceMensuelleImpotSousDetail;})" +
            ".collect(java.util.stream.Collectors.toList()))")
    @Mapping(target = "childQuittanceMensuelleImpotDetails", expression = "java(" +
            "quittanceMensuelleImpotDetailDTO.getChildQuittanceMensuelleImpotDetails().stream().map(childQuittanceMensuelleImpotDetailDTO ->  {" +
            "com.growup.comptadecision.domain.QuittanceMensuelleImpotDetail childQuittanceMensuelleImpotDetail = " +
            "this.toEntity(childQuittanceMensuelleImpotDetailDTO);" +
            "childQuittanceMensuelleImpotDetail.setParentQuittanceMensuelleImpotDetail(quittanceMensuelleImpotDetail);" +
            "return childQuittanceMensuelleImpotDetail;})" +
            ".collect(java.util.stream.Collectors.toList()))")
    @Mapping(target = "montantTotal", expression = "java(this.sum(quittanceMensuelleImpotDetailDTO))")
    QuittanceMensuelleImpotDetail toEntity(QuittanceMensuelleImpotDetailDTO quittanceMensuelleImpotDetailDTO);

    default QuittanceMensuelleImpotDetail fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail = new QuittanceMensuelleImpotDetail();
        quittanceMensuelleImpotDetail.setId(id);
        return quittanceMensuelleImpotDetail;
    }
}
