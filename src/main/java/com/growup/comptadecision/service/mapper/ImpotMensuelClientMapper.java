package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.ImpotMensuelClient;
import com.growup.comptadecision.service.dto.ImpotMensuelClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity ImpotMensuelClient and its DTO ImpotMensuelClientDTO.
 */
@Mapper(componentModel = "spring", uses = {FicheClientMapper.class, ImpotMensuelMapper.class, QuittanceMensuelleImpotDetailMapper.class})
public interface ImpotMensuelClientMapper extends EntityMapper<ImpotMensuelClientDTO, ImpotMensuelClient> {

    @Mapping(source = "ficheClient.id", target = "ficheClientId")
    @Mapping(source = "ficheClient.designation", target = "ficheClientDesignation")
    @Mapping(source = "ficheClient.matriculeFiscale", target = "ficheClientMatriculeFiscale")
    @Mapping(source = "ficheClient.registreCommerce", target = "ficheClientRegistreCommerce")
    @Mapping(source = "impotMensuel.id", target = "impotMensuelId")
    @Mapping(source = "impotMensuel.libelle", target = "impotMensuelLibelle")
    @Mapping(source = "impotMensuel.description", target = "impotMensuelDescription")
    @Mapping(source = "impotMensuel.parent", target = "impotMensuelParent")
    @Mapping(source = "impotMensuel.child", target = "impotMensuelChild")
    @Mapping(expression = "java(impotMensuelClient.getImpotMensuel().getParentImpotMensuel() == null ? null : impotMensuelClient.getImpotMensuel().getParentImpotMensuel().getId())", target = "impotMensuelParentId")


    ImpotMensuelClientDTO toDto(ImpotMensuelClient impotMensuelClient);

    @Mapping(source = "ficheClientId", target = "ficheClient", ignore = true)
    @Mapping(source = "impotMensuelId", target = "impotMensuel")
    ImpotMensuelClient toEntity(ImpotMensuelClientDTO impotMensuelClientDTO);

    default ImpotMensuelClient fromId(Long id) {
        if (id == null) {
            return null;
        }
        ImpotMensuelClient impotMensuelClient = new ImpotMensuelClient();
        impotMensuelClient.setId(id);
        return impotMensuelClient;
    }
}
