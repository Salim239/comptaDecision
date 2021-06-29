package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.DeclarationAnnuelleLigne;
import com.growup.comptadecision.domain.DeclarationAnnuelleLigne_;
import com.growup.comptadecision.domain.DeclarationAnnuelle_;
import com.growup.comptadecision.domain.ImpotAnnuel_;
import com.growup.comptadecision.repository.DeclarationAnnuelleLigneRepository;
import com.growup.comptadecision.service.dto.DeclarationAnnuelleLigneCriteria;
import com.growup.comptadecision.service.dto.DeclarationAnnuelleLigneDTO;
import com.growup.comptadecision.service.mapper.DeclarationAnnuelleLigneMapper;
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
 * Service for executing complex queries for DeclarationAnnuelleLigne entities in the database.
 * The main input is a {@link DeclarationAnnuelleLigneCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DeclarationAnnuelleLigneDTO} or a {@link Page} of {@link DeclarationAnnuelleLigneDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DeclarationAnnuelleLigneQueryService extends QueryService<DeclarationAnnuelleLigne> {

    private final Logger log = LoggerFactory.getLogger(DeclarationAnnuelleLigneQueryService.class);

    private final DeclarationAnnuelleLigneRepository declarationAnnuelleLigneRepository;

    private final DeclarationAnnuelleLigneMapper declarationAnnuelleLigneMapper;

    public DeclarationAnnuelleLigneQueryService(DeclarationAnnuelleLigneRepository declarationAnnuelleLigneRepository, DeclarationAnnuelleLigneMapper declarationAnnuelleLigneMapper) {
        this.declarationAnnuelleLigneRepository = declarationAnnuelleLigneRepository;
        this.declarationAnnuelleLigneMapper = declarationAnnuelleLigneMapper;
    }

    /**
     * Return a {@link List} of {@link DeclarationAnnuelleLigneDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DeclarationAnnuelleLigneDTO> findByCriteria(DeclarationAnnuelleLigneCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DeclarationAnnuelleLigne> specification = createSpecification(criteria);
        return declarationAnnuelleLigneMapper.toDto(declarationAnnuelleLigneRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DeclarationAnnuelleLigneDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DeclarationAnnuelleLigneDTO> findByCriteria(DeclarationAnnuelleLigneCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DeclarationAnnuelleLigne> specification = createSpecification(criteria);
        return declarationAnnuelleLigneRepository.findAll(specification, page)
            .map(declarationAnnuelleLigneMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DeclarationAnnuelleLigneCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DeclarationAnnuelleLigne> specification = createSpecification(criteria);
        return declarationAnnuelleLigneRepository.count(specification);
    }

    /**
     * Function to convert DeclarationAnnuelleLigneCriteria to a {@link Specification}
     */
    private Specification<DeclarationAnnuelleLigne> createSpecification(DeclarationAnnuelleLigneCriteria criteria) {
        Specification<DeclarationAnnuelleLigne> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), DeclarationAnnuelleLigne_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), DeclarationAnnuelleLigne_.code));
            }
            if (criteria.getTriOrdre() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTriOrdre(), DeclarationAnnuelleLigne_.triOrdre));
            }
            if (criteria.getCalcule() != null) {
                specification = specification.and(buildSpecification(criteria.getCalcule(), DeclarationAnnuelleLigne_.calcule));
            }
            if (criteria.getMontant() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontant(), DeclarationAnnuelleLigne_.montant));
            }
            if (criteria.getMontantCalcule() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantCalcule(), DeclarationAnnuelleLigne_.montantCalcule));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), DeclarationAnnuelleLigne_.libelle));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), DeclarationAnnuelleLigne_.description));
            }
            if (criteria.getImpotAnnuelId() != null) {
                specification = specification.and(buildSpecification(criteria.getImpotAnnuelId(),
                    root -> root.join(DeclarationAnnuelleLigne_.impotAnnuel, JoinType.LEFT).get(ImpotAnnuel_.id)));
            }
            if (criteria.getDeclarationAnnuelleId() != null) {
                specification = specification.and(buildSpecification(criteria.getDeclarationAnnuelleId(),
                    root -> root.join(DeclarationAnnuelleLigne_.declarationAnnuelle, JoinType.LEFT).get(DeclarationAnnuelle_.id)));
            }
        }
        return specification;
    }
}
