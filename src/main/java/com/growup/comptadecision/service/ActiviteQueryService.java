package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.Activite;
import com.growup.comptadecision.domain.Activite_;
import com.growup.comptadecision.domain.SecteurActivite_;
import com.growup.comptadecision.repository.ActiviteRepository;
import com.growup.comptadecision.service.dto.ActiviteCriteria;
import com.growup.comptadecision.service.dto.ActiviteDTO;
import com.growup.comptadecision.service.mapper.ActiviteMapper;
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
 * Service for executing complex queries for Activite entities in the database.
 * The main input is a {@link ActiviteCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ActiviteDTO} or a {@link Page} of {@link ActiviteDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ActiviteQueryService extends QueryService<Activite> {

    private final Logger log = LoggerFactory.getLogger(ActiviteQueryService.class);

    private final ActiviteRepository activiteRepository;

    private final ActiviteMapper activiteMapper;

    public ActiviteQueryService(ActiviteRepository activiteRepository, ActiviteMapper activiteMapper) {
        this.activiteRepository = activiteRepository;
        this.activiteMapper = activiteMapper;
    }

    /**
     * Return a {@link List} of {@link ActiviteDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ActiviteDTO> findByCriteria(ActiviteCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Activite> specification = createSpecification(criteria);
        return activiteMapper.toDto(activiteRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ActiviteDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ActiviteDTO> findByCriteria(ActiviteCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Activite> specification = createSpecification(criteria);
        return activiteRepository.findAll(specification, page)
            .map(activiteMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ActiviteCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Activite> specification = createSpecification(criteria);
        return activiteRepository.count(specification);
    }

    /**
     * Function to convert ActiviteCriteria to a {@link Specification}
     */
    private Specification<Activite> createSpecification(ActiviteCriteria criteria) {
        Specification<Activite> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Activite_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Activite_.code));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), Activite_.libelle));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Activite_.description));
            }
            if (criteria.getSecteurActiviteId() != null) {
                specification = specification.and(buildSpecification(criteria.getSecteurActiviteId(),
                    root -> root.join(Activite_.secteurActivite, JoinType.LEFT).get(SecteurActivite_.id)));
            }
        }
        return specification;
    }
}
