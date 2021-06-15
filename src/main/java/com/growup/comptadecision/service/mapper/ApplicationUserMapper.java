package com.growup.comptadecision.service.mapper;

import com.growup.comptadecision.domain.ApplicationUser;
import com.growup.comptadecision.service.dto.ApplicationUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link ApplicationUser} and its DTO {@link ApplicationUserDTO}.
 */
@Mapper(componentModel = "spring", uses = {CabinetComptableMapper.class})
public interface ApplicationUserMapper extends EntityMapper<ApplicationUserDTO, ApplicationUser> {

    @Mapping(source = "cabinetComptable.id", target = "cabinetComptableId")
    @Mapping(source = "cabinetComptable.code", target = "cabinetComptableCode")
    ApplicationUserDTO toDto(ApplicationUser applicationUser);

    @Mapping(source = "cabinetComptableId", target = "cabinetComptable")
    ApplicationUser toEntity(ApplicationUserDTO applicationUserDTO);

    default ApplicationUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setId(id);
        return applicationUser;
    }
}
