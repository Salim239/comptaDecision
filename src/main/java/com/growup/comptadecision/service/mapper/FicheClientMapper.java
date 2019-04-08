package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.*;
import com.growup.comptadecision.service.dto.FicheClientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FicheClient and its DTO FicheClientDTO.
 */
@Mapper(componentModel = "spring", uses = {SecteurActiviteMapper.class, ActiviteMapper.class, RegionMapper.class, VilleMapper.class , ImpotMensuelClientMapper.class})
public interface FicheClientMapper extends EntityMapper<FicheClientDTO, FicheClient> {

    @Mapping(source = "secteurActivite.id", target = "secteurActiviteId")
    @Mapping(source = "secteurActivite.libelle", target = "secteurActiviteLibelle")
    @Mapping(source = "activite.id", target = "activiteId")
    @Mapping(source = "activite.libelle", target = "activiteLibelle")
    @Mapping(source = "activiteScondaire.id", target = "activiteScondaireId")
    @Mapping(source = "activiteScondaire.libelle", target = "activiteScondaireLibelle")
    @Mapping(source = "impotMensuelClients", target = "impotMensuelClients")
    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "region.libelle", target = "regionLibelle")
    @Mapping(source = "ville.id", target = "villeId")
    @Mapping(source = "ville.libelle", target = "villeLibelle")
    FicheClientDTO toDto(FicheClient ficheClient);

    @Mapping(source = "secteurActiviteId", target = "secteurActivite")
    @Mapping(source = "activiteId", target = "activite")
    @Mapping(source = "activiteScondaireId", target = "activiteScondaire")
    @Mapping(source = "regionId", target = "region")
    @Mapping(source = "villeId", target = "ville")
    FicheClient toEntity(FicheClientDTO ficheClientDTO);

    default FicheClient fromId(Long id) {
        if (id == null) {
            return null;
        }
        FicheClient ficheClient = new FicheClient();
        ficheClient.setId(id);
        return ficheClient;
    }
}
