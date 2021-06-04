package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.ImpotMensuel;
import com.growup.comptadecision.service.dto.ImpotMensuelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity ImpotMensuel and its DTO ImpotMensuelDTO.
 */
@Mapper(componentModel = "spring", uses = {ImpotMensuelLigneMapper.class})
public interface ImpotMensuelMapper extends EntityMapper<ImpotMensuelDTO, ImpotMensuel> {

    @Mapping(target = "parentImpotMensuel", expression = "java(impotMensuelDTO.getParentImpotMensuelId() == null ? null : new com.growup.comptadecision.domain.ImpotMensuel(impotMensuelDTO.getParentImpotMensuelId()))" )
    @Mapping(target = "impotMensuelLignes", expression = "java(" +
            "impotMensuelDTO.getImpotMensuelLignes().stream().map(impotMensuelLigneDto ->  {" +
            "com.growup.comptadecision.domain.ImpotMensuelLigne impotMensuelLigne = impotMensuelLigneMapper.toEntity(impotMensuelLigneDto);" +
            "impotMensuelLigne.setImpotMensuel(impotMensuel);" +
            "return impotMensuelLigne;})" +
            ".collect(java.util.stream.Collectors.toList()))")
    ImpotMensuel toEntity(ImpotMensuelDTO impotMensuelDTO);

    @Mapping(source = "parentImpotMensuel.id", target = "parentImpotMensuelId")
    @Mapping(source = "parentImpotMensuel.libelle", target = "parentImpotMensuelLibelle")
    ImpotMensuelDTO toDto(ImpotMensuel impotMensuel);

    default ImpotMensuel fromId(Long id) {
        if (id == null) {
            return null;
        }
        ImpotMensuel impotMensuel = new ImpotMensuel();
        impotMensuel.setId(id);
        return impotMensuel;
    }
}
