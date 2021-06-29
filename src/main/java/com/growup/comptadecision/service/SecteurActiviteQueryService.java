package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.SecteurActivite;
import com.growup.comptadecision.domain.SecteurActivite_;
import com.growup.comptadecision.repository.SecteurActiviteRepository;
import com.growup.comptadecision.service.dto.SecteurActiviteCriteria;
import com.growup.comptadecision.service.dto.SecteurActiviteDTO;
import com.growup.comptadecision.service.mapper.SecteurActiviteMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for executing complex queries for SecteurActivite entities in the database.
 * The main input is a {@link SecteurActiviteCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SecteurActiviteDTO} or a {@link Page} of {@link SecteurActiviteDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SecteurActiviteQueryService extends QueryService<SecteurActivite> {

    private final Logger log = LoggerFactory.getLogger(SecteurActiviteQueryService.class);

    private final SecteurActiviteRepository secteurActiviteRepository;

    private final SecteurActiviteMapper secteurActiviteMapper;

    public SecteurActiviteQueryService(SecteurActiviteRepository secteurActiviteRepository, SecteurActiviteMapper secteurActiviteMapper) {
        this.secteurActiviteRepository = secteurActiviteRepository;
        this.secteurActiviteMapper = secteurActiviteMapper;
    }

    /**
     * Return a {@link List} of {@link SecteurActiviteDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SecteurActiviteDTO> findByCriteria(SecteurActiviteCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SecteurActivite> specification = createSpecification(criteria);
        return secteurActiviteMapper.toDto(secteurActiviteRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SecteurActiviteDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SecteurActiviteDTO> findByCriteria(SecteurActiviteCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SecteurActivite> specification = createSpecification(criteria);
        return secteurActiviteRepository.findAll(specification, page)
            .map(secteurActiviteMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SecteurActiviteCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SecteurActivite> specification = createSpecification(criteria);
        return secteurActiviteRepository.count(specification);
    }

    /**
     * Function to convert SecteurActiviteCriteria to a {@link Specification}
     */
    private Specification<SecteurActivite> createSpecification(SecteurActiviteCriteria criteria) {
        Specification<SecteurActivite> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SecteurActivite_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), SecteurActivite_.code));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), SecteurActivite_.libelle));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), SecteurActivite_.description));
            }
        }
        return specification;
    }
}
