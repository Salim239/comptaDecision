package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.*;
import com.growup.comptadecision.service.dto.AcompteProvisionnelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AcompteProvisionnel and its DTO AcompteProvisionnelDTO.
 */
@Mapper(componentModel = "spring", uses = {FicheClientMapper.class})
public interface AcompteProvisionnelMapper extends EntityMapper<AcompteProvisionnelDTO, AcompteProvisionnel> {

    @Mapping(source = "ficheClient.id", target = "ficheClientId")
    @Mapping(source = "ficheClient.designation", target = "ficheClientDesignation")
    @Mapping(source = "ficheClient.matriculeFiscale", target = "ficheClientMatriculeFiscale")
    @Mapping(source = "ficheClient.registreCommerce", target = "ficheClientRegistreCommerce")
    @Mapping(source = "ficheClient.dateCreation", target = "ficheClientDateCreation")
    AcompteProvisionnelDTO toDto(AcompteProvisionnel acompteProvisionnel);

    @Mapping(source = "ficheClientId", target = "ficheClient")
    AcompteProvisionnel toEntity(AcompteProvisionnelDTO acompteProvisionnelDTO);

    default AcompteProvisionnel fromId(Long id) {
        if (id == null) {
            return null;
        }
        AcompteProvisionnel acompteProvisionnel = new AcompteProvisionnel();
        acompteProvisionnel.setId(id);
        return acompteProvisionnel;
    }
}
