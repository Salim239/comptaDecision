package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.*;
import com.growup.comptadecision.repository.QuittanceMensuelleLigneRepository;
import com.growup.comptadecision.service.dto.QuittanceMensuelleLigneCriteria;
import com.growup.comptadecision.service.dto.QuittanceMensuelleLigneDTO;
import com.growup.comptadecision.service.mapper.QuittanceMensuelleLigneMapper;
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
 * Service for executing complex queries for QuittanceMensuelleLigne entities in the database.
 * The main input is a {@link QuittanceMensuelleLigneCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QuittanceMensuelleLigneDTO} or a {@link Page} of {@link QuittanceMensuelleLigneDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QuittanceMensuelleLigneQueryService extends QueryService<QuittanceMensuelleLigne> {

    private final Logger log = LoggerFactory.getLogger(QuittanceMensuelleLigneQueryService.class);

    private final QuittanceMensuelleLigneRepository quittanceMensuelleLigneRepository;

    private final QuittanceMensuelleLigneMapper quittanceMensuelleLigneMapper;

    public QuittanceMensuelleLigneQueryService(QuittanceMensuelleLigneRepository quittanceMensuelleLigneRepository, QuittanceMensuelleLigneMapper quittanceMensuelleLigneMapper) {
        this.quittanceMensuelleLigneRepository = quittanceMensuelleLigneRepository;
        this.quittanceMensuelleLigneMapper = quittanceMensuelleLigneMapper;
    }

    /**
     * Return a {@link List} of {@link QuittanceMensuelleLigneDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QuittanceMensuelleLigneDTO> findByCriteria(QuittanceMensuelleLigneCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<QuittanceMensuelleLigne> specification = createSpecification(criteria);
        return quittanceMensuelleLigneMapper.toDto(quittanceMensuelleLigneRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QuittanceMensuelleLigneDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QuittanceMensuelleLigneDTO> findByCriteria(QuittanceMensuelleLigneCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<QuittanceMensuelleLigne> specification = createSpecification(criteria);
        return quittanceMensuelleLigneRepository.findAll(specification, page)
            .map(quittanceMensuelleLigneMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(QuittanceMensuelleLigneCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<QuittanceMensuelleLigne> specification = createSpecification(criteria);
        return quittanceMensuelleLigneRepository.count(specification);
    }

    /**
     * Function to convert QuittanceMensuelleLigneCriteria to a {@link Specification}
     */
    private Specification<QuittanceMensuelleLigne> createSpecification(QuittanceMensuelleLigneCriteria criteria) {
        Specification<QuittanceMensuelleLigne> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), QuittanceMensuelleLigne_.id));
            }
            if (criteria.getMontantTotal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantTotal(), QuittanceMensuelleLigne_.montantTotal));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), QuittanceMensuelleLigne_.code));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), QuittanceMensuelleLigne_.libelle));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), QuittanceMensuelleLigne_.description));
            }
            if (criteria.getParent() != null) {
                specification = specification.and(buildSpecification(criteria.getParent(), QuittanceMensuelleLigne_.parent));
            }
            if (criteria.getChild() != null) {
                specification = specification.and(buildSpecification(criteria.getChild(), QuittanceMensuelleLigne_.child));
            }
            if (criteria.getAppliquerReportMontant() != null) {
                specification = specification.and(buildSpecification(criteria.getAppliquerReportMontant(), QuittanceMensuelleLigne_.appliquerReportMontant));
            }
            if (criteria.getMontantReport() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantReport(), QuittanceMensuelleLigne_.montantReport));
            }
            if (criteria.getCoefficientMontant() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoefficientMontant(), QuittanceMensuelleLigne_.coefficientMontant));
            }
            if (criteria.getQuittanceMensuelleId() != null) {
                specification = specification.and(buildSpecification(criteria.getQuittanceMensuelleId(),
                    root -> root.join(QuittanceMensuelleLigne_.quittanceMensuelle, JoinType.LEFT).get(QuittanceMensuelle_.id)));
            }
            if (criteria.getQuittanceMensuelleSousLigneId() != null) {
                specification = specification.and(buildSpecification(criteria.getQuittanceMensuelleSousLigneId(),
                    root -> root.join(QuittanceMensuelleLigne_.quittanceMensuelleSousLignes, JoinType.LEFT).get(QuittanceMensuelleSousLigne_.id)));
            }
            if (criteria.getParentQuittanceMensuelleLigneId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentQuittanceMensuelleLigneId(),
                    root -> root.join(QuittanceMensuelleLigne_.parentQuittanceMensuelleLigne, JoinType.LEFT).get(QuittanceMensuelleLigne_.id)));
            }
            if (criteria.getImpotMensuelId() != null) {
                specification = specification.and(buildSpecification(criteria.getImpotMensuelId(),
                    root -> root.join(QuittanceMensuelleLigne_.impotMensuel, JoinType.LEFT).get(ImpotMensuel_.id)));
            }
        }
        return specification;
    }
}
