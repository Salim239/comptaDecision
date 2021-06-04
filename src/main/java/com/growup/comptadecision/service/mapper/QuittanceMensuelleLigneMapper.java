package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.QuittanceMensuelleLigne;
import com.growup.comptadecision.domain.QuittanceMensuelleSousLigne;
import com.growup.comptadecision.service.dto.QuittanceMensuelleLigneDTO;
import com.growup.comptadecision.service.dto.QuittanceMensuelleSousLigneDTO;
import org.apache.commons.lang3.BooleanUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for the entity QuittanceMensuelleLigne and its DTO QuittanceMensuelleLigneDTO.
 */
@Mapper(componentModel = "spring", uses = {QuittanceMensuelleMapper.class, ImpotMensuelMapper.class, QuittanceMensuelleSousLigneMapper.class})
public interface QuittanceMensuelleLigneMapper extends EntityMapper<QuittanceMensuelleLigneDTO, QuittanceMensuelleLigne> {

    //////
    default BigDecimal sum(QuittanceMensuelleLigne quittanceMensuelleLigne) {
        return quittanceMensuelleLigne.getQuittanceMensuelleSousLignes().stream()
                .filter(quittanceMensuelleSousLigne -> quittanceMensuelleSousLigne.getMontantTotal() != null)
                .map(QuittanceMensuelleSousLigne::getMontantTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    default BigDecimal sumWithChildren(QuittanceMensuelleLigne quittanceMensuelleLigne) {

        BigDecimal montant = quittanceMensuelleLigne.getChildQuittanceMensuelleLignes().stream()
                .map(childQuittanceMensuelleLigne ->
                        childQuittanceMensuelleLigne.getMontantTotal().multiply(new BigDecimal(childQuittanceMensuelleLigne.getCoefficientMontant())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        //Add report amount
        if (BooleanUtils.isTrue(quittanceMensuelleLigne.getAppliquerReportMontant())) {
            return montant.subtract(quittanceMensuelleLigne.getMontantReport());
        } else {
            return montant;
        }
    }

    default BigDecimal sum(QuittanceMensuelleLigneDTO quittanceMensuelleLigne) {

        return quittanceMensuelleLigne.getQuittanceMensuelleSousLignes().stream()
                .filter(quittanceMensuelleSousLigne -> quittanceMensuelleSousLigne.getMontantTotal() != null)
                .map(QuittanceMensuelleSousLigneDTO::getMontantTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    ////
    default BigDecimal sumWithChildren(QuittanceMensuelleLigneDTO quittanceMensuelleLigne) {
        return quittanceMensuelleLigne.getChildQuittanceMensuelleLignes().stream()
                .map(childQuittanceMensuelleLigne ->
                        sum(childQuittanceMensuelleLigne).multiply(new BigDecimal(childQuittanceMensuelleLigne.getCoefficientMontant())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    default BigDecimal getMontantTotal(QuittanceMensuelleLigne quittanceMensuelleLigne) {

        return BooleanUtils.isTrue(quittanceMensuelleLigne.getParent()) ?
            sumWithChildren(quittanceMensuelleLigne) :
            sum(quittanceMensuelleLigne);
    }

    default BigDecimal getMontantTotal(QuittanceMensuelleLigneDTO quittanceMensuelleLigne) {

        return BooleanUtils.isTrue(quittanceMensuelleLigne.getParent()) ?
            sumWithChildren(quittanceMensuelleLigne) :
            sum(quittanceMensuelleLigne);
    }

    default List<QuittanceMensuelleSousLigne> getQuittanceMensuelleSousLignes(QuittanceMensuelleLigneDTO quittanceMensuelleLigneDTO,
                                                                                          QuittanceMensuelleLigne quittanceMensuelleLigne,
                                                                                          QuittanceMensuelleSousLigneMapper quittanceMensuelleSousLigneMapper) {

        return quittanceMensuelleLigneDTO.getQuittanceMensuelleSousLignes().stream().map(quittanceMensuelleSousLigneDTO -> {
            QuittanceMensuelleSousLigne quittanceMensuelleSousLigne = quittanceMensuelleSousLigneMapper.toEntity(quittanceMensuelleSousLigneDTO);
            quittanceMensuelleSousLigne.setQuittanceMensuelleLigne(quittanceMensuelleLigne);
            return quittanceMensuelleSousLigne;
        })
                .collect(Collectors.toList());
    }

    default List<QuittanceMensuelleLigne> getChildQuittanceMensuelleLignes(QuittanceMensuelleLigneDTO quittanceMensuelleLigneDTO,
                                                                                       QuittanceMensuelleLigne quittanceMensuelleLigne) {

        return quittanceMensuelleLigneDTO.getChildQuittanceMensuelleLignes().stream().map(childQuittanceMensuelleLigneDTO -> {
            QuittanceMensuelleLigne childQuittanceMensuelleLigne = toEntity(childQuittanceMensuelleLigneDTO);
            childQuittanceMensuelleLigne.setParentQuittanceMensuelleLigne(quittanceMensuelleLigne);
            return childQuittanceMensuelleLigne;
        })
                .collect(java.util.stream.Collectors.toList());
    }

    @Mapping(source = "parentQuittanceMensuelleLigne.id", target = "parentQuittanceMensuelleLigneId")
    @Mapping(source = "parentQuittanceMensuelleLigne.code", target = "parentQuittanceMensuelleLigneCode")
    @Mapping(source = "parentQuittanceMensuelleLigne.libelle", target = "parentQuittanceMensuelleLigneLibelle")
    @Mapping(source = "impotMensuel.id", target = "impotMensuelId")
//    @Mapping(target = "montantTotal", expression = "java(getMontantTotal(quittanceMensuelleLigne))")
    QuittanceMensuelleLigneDTO toDto(QuittanceMensuelleLigne quittanceMensuelleLigne);


    @Mapping(source = "impotMensuelId", target = "impotMensuel.id")
    @Mapping(target = "parentQuittanceMensuelleLigne",
            expression = "java(quittanceMensuelleLigneDTO.getParentQuittanceMensuelleLigneId() == null ? null : " +
                    "new com.growup.comptadecision.domain.QuittanceMensuelleLigne(quittanceMensuelleLigneDTO.getParentQuittanceMensuelleLigneId()))" )
    @Mapping(target = "quittanceMensuelleSousLignes",
            expression = "java(getQuittanceMensuelleSousLignes(quittanceMensuelleLigneDTO, quittanceMensuelleLigne, quittanceMensuelleSousLigneMapper))")
    @Mapping(target = "childQuittanceMensuelleLignes", expression = "java(getChildQuittanceMensuelleLignes(quittanceMensuelleLigneDTO, quittanceMensuelleLigne))")
    @Mapping(target = "montantTotal", expression = "java(getMontantTotal(quittanceMensuelleLigne))", dependsOn = {"quittanceMensuelleSousLignes", "childQuittanceMensuelleLignes"})
    QuittanceMensuelleLigne toEntity(QuittanceMensuelleLigneDTO quittanceMensuelleLigneDTO);

    default QuittanceMensuelleLigne fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuittanceMensuelleLigne quittanceMensuelleLigne = new QuittanceMensuelleLigne();
        quittanceMensuelleLigne.setId(id);
        return quittanceMensuelleLigne;
    }
}
