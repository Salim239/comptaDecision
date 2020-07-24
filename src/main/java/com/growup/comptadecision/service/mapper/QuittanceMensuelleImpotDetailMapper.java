package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.QuittanceMensuelleImpotDetail;
import com.growup.comptadecision.domain.QuittanceMensuelleImpotSousDetail;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotDetailDTO;
import com.growup.comptadecision.service.dto.QuittanceMensuelleImpotSousDetailDTO;
import org.apache.commons.lang3.BooleanUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for the entity QuittanceMensuelleImpotDetail and its DTO QuittanceMensuelleImpotDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {QuittanceMensuelleImpotMapper.class, ImpotMensuelMapper.class, QuittanceMensuelleImpotSousDetailMapper.class})
public interface QuittanceMensuelleImpotDetailMapper extends EntityMapper<QuittanceMensuelleImpotDetailDTO, QuittanceMensuelleImpotDetail> {

    //////
    default BigDecimal sum(QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail) {
        return quittanceMensuelleImpotDetail.getQuittanceMensuelleImpotSousDetails().stream()
                .filter(quittanceMensuelleImpotSousDetail -> quittanceMensuelleImpotSousDetail.getMontantTotal() != null)
                .map(QuittanceMensuelleImpotSousDetail::getMontantTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    default BigDecimal sumWithChildren(QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail) {

        BigDecimal montant = quittanceMensuelleImpotDetail.getChildQuittanceMensuelleImpotDetails().stream()
                .map(childQuittanceMensuelleImpotDetail ->
                        childQuittanceMensuelleImpotDetail.getMontantTotal().multiply(new BigDecimal(childQuittanceMensuelleImpotDetail.getCoefficientMontant())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        //Add report amount
        if (BooleanUtils.isTrue(quittanceMensuelleImpotDetail.getAppliquerReportMontant())) {
            return montant.subtract(quittanceMensuelleImpotDetail.getMontantReport());
        } else {
            return montant;
        }
    }

    default BigDecimal sum(QuittanceMensuelleImpotDetailDTO quittanceMensuelleImpotDetail) {

        return quittanceMensuelleImpotDetail.getQuittanceMensuelleImpotSousDetails().stream()
                .filter(quittanceMensuelleImpotSousDetail -> quittanceMensuelleImpotSousDetail.getMontantTotal() != null)
                .map(QuittanceMensuelleImpotSousDetailDTO::getMontantTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    ////
    default BigDecimal sumWithChildren(QuittanceMensuelleImpotDetailDTO quittanceMensuelleImpotDetail) {
        return quittanceMensuelleImpotDetail.getChildQuittanceMensuelleImpotDetails().stream()
                .map(childQuittanceMensuelleImpotDetail ->
                        sum(childQuittanceMensuelleImpotDetail).multiply(new BigDecimal(childQuittanceMensuelleImpotDetail.getCoefficientMontant())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    default BigDecimal getMontantTotal(QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail) {

        return BooleanUtils.isTrue(quittanceMensuelleImpotDetail.getParent()) ?
            sumWithChildren(quittanceMensuelleImpotDetail) :
            sum(quittanceMensuelleImpotDetail);
    }

    default BigDecimal getMontantTotal(QuittanceMensuelleImpotDetailDTO quittanceMensuelleImpotDetail) {

        return BooleanUtils.isTrue(quittanceMensuelleImpotDetail.getParent()) ?
            sumWithChildren(quittanceMensuelleImpotDetail) :
            sum(quittanceMensuelleImpotDetail);
    }

    default List<QuittanceMensuelleImpotSousDetail> getQuittanceMensuelleImpotSousDetails(QuittanceMensuelleImpotDetailDTO quittanceMensuelleImpotDetailDTO,
                                                                                          QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail,
                                                                                          QuittanceMensuelleImpotSousDetailMapper quittanceMensuelleImpotSousDetailMapper) {

        return quittanceMensuelleImpotDetailDTO.getQuittanceMensuelleImpotSousDetails().stream().map(quittanceMensuelleImpotSousDetailDTO -> {
            QuittanceMensuelleImpotSousDetail quittanceMensuelleImpotSousDetail = quittanceMensuelleImpotSousDetailMapper.toEntity(quittanceMensuelleImpotSousDetailDTO);
            quittanceMensuelleImpotSousDetail.setQuittanceMensuelleImpotDetail(quittanceMensuelleImpotDetail);
            return quittanceMensuelleImpotSousDetail;
        })
                .collect(Collectors.toList());
    }

    default List<QuittanceMensuelleImpotDetail> getChildQuittanceMensuelleImpotDetails(QuittanceMensuelleImpotDetailDTO quittanceMensuelleImpotDetailDTO,
                                                                                       QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail) {

        return quittanceMensuelleImpotDetailDTO.getChildQuittanceMensuelleImpotDetails().stream().map(childQuittanceMensuelleImpotDetailDTO -> {
            QuittanceMensuelleImpotDetail childQuittanceMensuelleImpotDetail = toEntity(childQuittanceMensuelleImpotDetailDTO);
            childQuittanceMensuelleImpotDetail.setParentQuittanceMensuelleImpotDetail(quittanceMensuelleImpotDetail);
            return childQuittanceMensuelleImpotDetail;
        })
                .collect(java.util.stream.Collectors.toList());
    }

    @Mapping(source = "parentQuittanceMensuelleImpotDetail.id", target = "parentQuittanceMensuelleImpotDetailId")
    @Mapping(source = "parentQuittanceMensuelleImpotDetail.code", target = "parentQuittanceMensuelleImpotDetailCode")
    @Mapping(source = "parentQuittanceMensuelleImpotDetail.libelle", target = "parentQuittanceMensuelleImpotDetailLibelle")
    @Mapping(source = "impotMensuel.id", target = "impotMensuelId")
//    @Mapping(target = "montantTotal", expression = "java(getMontantTotal(quittanceMensuelleImpotDetail))")
    QuittanceMensuelleImpotDetailDTO toDto(QuittanceMensuelleImpotDetail quittanceMensuelleImpotDetail);


    @Mapping(source = "impotMensuelId", target = "impotMensuel.id")
    @Mapping(target = "parentQuittanceMensuelleImpotDetail",
            expression = "java(quittanceMensuelleImpotDetailDTO.getParentQuittanceMensuelleImpotDetailId() == null ? null : " +
                    "new com.growup.comptadecision.domain.QuittanceMensuelleImpotDetail(quittanceMensuelleImpotDetailDTO.getParentQuittanceMensuelleImpotDetailId()))" )
    @Mapping(target = "quittanceMensuelleImpotSousDetails",
            expression = "java(getQuittanceMensuelleImpotSousDetails(quittanceMensuelleImpotDetailDTO, quittanceMensuelleImpotDetail, quittanceMensuelleImpotSousDetailMapper))")
    @Mapping(target = "childQuittanceMensuelleImpotDetails", expression = "java(getChildQuittanceMensuelleImpotDetails(quittanceMensuelleImpotDetailDTO, quittanceMensuelleImpotDetail))")
    @Mapping(target = "montantTotal", expression = "java(getMontantTotal(quittanceMensuelleImpotDetail))", dependsOn = {"quittanceMensuelleImpotSousDetails", "childQuittanceMensuelleImpotDetails"})
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
