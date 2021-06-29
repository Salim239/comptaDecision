package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.ApplicationUserFicheClient;
import com.growup.comptadecision.domain.ApplicationUserFicheClient_;
import com.growup.comptadecision.domain.ApplicationUser_;
import com.growup.comptadecision.domain.FicheClient_;
import com.growup.comptadecision.repository.ApplicationUserFicheClientRepository;
import com.growup.comptadecision.service.dto.ApplicationUserFicheClientCriteria;
import com.growup.comptadecision.service.dto.ApplicationUserFicheClientDTO;
import com.growup.comptadecision.service.mapper.ApplicationUserFicheClientMapper;
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
 * Service for executing complex queries for ApplicationUserFicheClient entities in the database.
 * The main input is a {@link ApplicationUserFicheClientCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ApplicationUserFicheClientDTO} or a {@link Page} of {@link ApplicationUserFicheClientDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ApplicationUserFicheClientQueryService extends QueryService<ApplicationUserFicheClient> {

    private final Logger log = LoggerFactory.getLogger(ApplicationUserFicheClientQueryService.class);

    private final ApplicationUserFicheClientRepository applicationUserFicheClientRepository;

    private final ApplicationUserFicheClientMapper applicationUserFicheClientMapper;

    public ApplicationUserFicheClientQueryService(ApplicationUserFicheClientRepository applicationUserFicheClientRepository, ApplicationUserFicheClientMapper applicationUserFicheClientMapper) {
        this.applicationUserFicheClientRepository = applicationUserFicheClientRepository;
        this.applicationUserFicheClientMapper = applicationUserFicheClientMapper;
    }

    /**
     * Return a {@link List} of {@link ApplicationUserFicheClientDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ApplicationUserFicheClientDTO> findByCriteria(ApplicationUserFicheClientCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ApplicationUserFicheClient> specification = createSpecification(criteria);
        return applicationUserFicheClientMapper.toDto(applicationUserFicheClientRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ApplicationUserFicheClientDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ApplicationUserFicheClientDTO> findByCriteria(ApplicationUserFicheClientCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ApplicationUserFicheClient> specification = createSpecification(criteria);
        return applicationUserFicheClientRepository.findAll(specification, page)
            .map(applicationUserFicheClientMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ApplicationUserFicheClientCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ApplicationUserFicheClient> specification = createSpecification(criteria);
        return applicationUserFicheClientRepository.count(specification);
    }

    /**
     * Function to convert ApplicationUserFicheClientCriteria to a {@link Specification}
     */
    private Specification<ApplicationUserFicheClient> createSpecification(ApplicationUserFicheClientCriteria criteria) {
        Specification<ApplicationUserFicheClient> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ApplicationUserFicheClient_.id));
            }
            if (criteria.getFicheClientId() != null) {
                specification = specification.and(buildSpecification(criteria.getFicheClientId(),
                    root -> root.join(ApplicationUserFicheClient_.ficheClient, JoinType.LEFT).get(FicheClient_.id)));
            }
            if (criteria.getApplicationUserId() != null) {
                specification = specification.and(buildSpecification(criteria.getApplicationUserId(),
                    root -> root.join(ApplicationUserFicheClient_.applicationUser, JoinType.LEFT).get(ApplicationUser_.id)));
            }
        }
        return specification;
    }
}
