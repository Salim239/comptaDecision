package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.ApplicationUser;
import com.growup.comptadecision.domain.ApplicationUser_;
import com.growup.comptadecision.domain.CabinetComptable_;
import com.growup.comptadecision.repository.ApplicationUserRepository;
import com.growup.comptadecision.service.dto.ApplicationUserCriteria;
import com.growup.comptadecision.service.dto.ApplicationUserDTO;
import com.growup.comptadecision.service.mapper.ApplicationUserMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;
import java.util.List;

/**
 * Service for executing complex queries for ApplicationUser entities in the database.
 * The main input is a {@link ApplicationUserCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ApplicationUserDTO} or a {@link Page} of {@link ApplicationUserDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ApplicationUserQueryService extends QueryService<ApplicationUser> {

    private final Logger log = LoggerFactory.getLogger(ApplicationUserQueryService.class);

    private final ApplicationUserRepository applicationUserRepository;

    private final ApplicationUserMapper applicationUserMapper;

    public ApplicationUserQueryService(ApplicationUserRepository applicationUserRepository, ApplicationUserMapper applicationUserMapper) {
        this.applicationUserRepository = applicationUserRepository;
        this.applicationUserMapper = applicationUserMapper;
    }

    /**
     * Return a {@link List} of {@link ApplicationUserDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ApplicationUserDTO> findByCriteria(ApplicationUserCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ApplicationUser> specification = createSpecification(criteria);
        return applicationUserMapper.toDto(applicationUserRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ApplicationUserDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ApplicationUserDTO> findByCriteria(ApplicationUserCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ApplicationUser> specification = createSpecification(criteria);
        return applicationUserRepository.findAll(specification, page)
            .map(applicationUserMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ApplicationUserCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ApplicationUser> specification = createSpecification(criteria);
        return applicationUserRepository.count(specification);
    }

    /**
     * Function to convert ApplicationUserCriteria to a {@link Specification}
     */
    private Specification<ApplicationUser> createSpecification(ApplicationUserCriteria criteria) {
        Specification<ApplicationUser> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ApplicationUser_.id));
            }
            if (criteria.getMatricule() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMatricule(), ApplicationUser_.matricule));
            }
            if (criteria.getPoste() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPoste(), ApplicationUser_.poste));
            }
            if (criteria.getDateEmbauche() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDateEmbauche(), ApplicationUser_.dateEmbauche));
            }
            if (criteria.getCabinetComptableId() != null) {
                specification = specification.and(buildSpecification(criteria.getCabinetComptableId(),
                    root -> root.join(ApplicationUser_.cabinetComptable, JoinType.LEFT).get(CabinetComptable_.id)));
            }
        }
        return specification;
    }
}
