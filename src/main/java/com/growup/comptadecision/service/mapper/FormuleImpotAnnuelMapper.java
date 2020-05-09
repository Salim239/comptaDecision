package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.FormuleImpotAnnuel;
import com.growup.comptadecision.domain.ImpotAnnuel;
import com.growup.comptadecision.domain.ImpotAnnuelDetail;
import com.growup.comptadecision.service.dto.FormuleImpotAnnuelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity ImpotAnnuel and its DTO ImpotAnnuelDTO.
 */
@Mapper(componentModel = "spring", uses = {ImpotAnnuelMapper.class})
public interface FormuleImpotAnnuelMapper extends EntityMapper<FormuleImpotAnnuelDTO, FormuleImpotAnnuel> {

    default ImpotAnnuel getImpotAnnuelEnfant(FormuleImpotAnnuelDTO formuleImpotAnnuelDTO) {
        ImpotAnnuel impotAnnuel = new ImpotAnnuel();
        impotAnnuel.setId(formuleImpotAnnuelDTO.getImpotAnnuelEnfantId());
        return impotAnnuel;
    }

    default ImpotAnnuel getImpotAnnuelParent(FormuleImpotAnnuelDTO formuleImpotAnnuelDTO) {
        ImpotAnnuel impotAnnuel = new ImpotAnnuel();
        impotAnnuel.setId(formuleImpotAnnuelDTO.getImpotAnnuelParentId());
        return impotAnnuel;
    }


    default ImpotAnnuelDetail fromId(Long id) {
        if (id == null) {
            return null;
        }
        ImpotAnnuelDetail impotAnnuelDetail = new ImpotAnnuelDetail();
        impotAnnuelDetail.setId(id);
        return impotAnnuelDetail;
    }

    @Mapping(target = "impotAnnuelParent", expression = "java(this.getImpotAnnuelParent(formuleImpotAnnuelDTO))")
    @Mapping(target = "impotAnnuelEnfant", expression = "java(this.getImpotAnnuelEnfant(formuleImpotAnnuelDTO))")
    FormuleImpotAnnuel toEntity(FormuleImpotAnnuelDTO formuleImpotAnnuelDTO);

    @Mapping(source = "impotAnnuelParent.id", target = "impotAnnuelParentId")
    @Mapping(source = "impotAnnuelParent.libelle", target = "impotAnnuelParentLibelle")
    @Mapping(source = "impotAnnuelEnfant.id", target = "impotAnnuelEnfantId")
    @Mapping(source = "impotAnnuelEnfant.libelle", target = "impotAnnuelEnfantLibelle")
    FormuleImpotAnnuelDTO toDto(FormuleImpotAnnuel formuleImpotAnnuel);
}
