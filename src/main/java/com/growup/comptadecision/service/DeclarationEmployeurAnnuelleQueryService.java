package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.DeclarationEmployeurAnnuelle;
import com.growup.comptadecision.domain.DeclarationEmployeurAnnuelle_;
import com.growup.comptadecision.domain.FicheClient_;
import com.growup.comptadecision.repository.DeclarationEmployeurAnnuelleRepository;
import com.growup.comptadecision.service.dto.DeclarationEmployeurAnnuelleCriteria;
import com.growup.comptadecision.service.dto.DeclarationEmployeurAnnuelleDTO;
import com.growup.comptadecision.service.mapper.DeclarationEmployeurAnnuelleMapper;
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
 * Service for executing complex queries for DeclarationEmployeurAnnuelle entities in the database.
 * The main input is a {@link DeclarationEmployeurAnnuelleCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DeclarationEmployeurAnnuelleDTO} or a {@link Page} of {@link DeclarationEmployeurAnnuelleDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DeclarationEmployeurAnnuelleQueryService extends QueryService<DeclarationEmployeurAnnuelle> {

    private final Logger log = LoggerFactory.getLogger(DeclarationEmployeurAnnuelleQueryService.class);

    private final DeclarationEmployeurAnnuelleRepository declarationEmployeurAnnuelleRepository;

    private final DeclarationEmployeurAnnuelleMapper declarationEmployeurAnnuelleMapper;

    public DeclarationEmployeurAnnuelleQueryService(DeclarationEmployeurAnnuelleRepository declarationEmployeurAnnuelleRepository, DeclarationEmployeurAnnuelleMapper declarationEmployeurAnnuelleMapper) {
        this.declarationEmployeurAnnuelleRepository = declarationEmployeurAnnuelleRepository;
        this.declarationEmployeurAnnuelleMapper = declarationEmployeurAnnuelleMapper;
    }

    /**
     * Return a {@link List} of {@link DeclarationEmployeurAnnuelleDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DeclarationEmployeurAnnuelleDTO> findByCriteria(DeclarationEmployeurAnnuelleCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DeclarationEmployeurAnnuelle> specification = createSpecification(criteria);
        return declarationEmployeurAnnuelleMapper.toDto(declarationEmployeurAnnuelleRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DeclarationEmployeurAnnuelleDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DeclarationEmployeurAnnuelleDTO> findByCriteria(DeclarationEmployeurAnnuelleCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DeclarationEmployeurAnnuelle> specification = createSpecification(criteria);
        return declarationEmployeurAnnuelleRepository.findAll(specification, page)
            .map(declarationEmployeurAnnuelleMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DeclarationEmployeurAnnuelleCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DeclarationEmployeurAnnuelle> specification = createSpecification(criteria);
        return declarationEmployeurAnnuelleRepository.count(specification);
    }

    /**
     * Function to convert DeclarationEmployeurAnnuelleCriteria to a {@link Specification}
     */
    private Specification<DeclarationEmployeurAnnuelle> createSpecification(DeclarationEmployeurAnnuelleCriteria criteria) {
        Specification<DeclarationEmployeurAnnuelle> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), DeclarationEmployeurAnnuelle_.id));
            }
            if (criteria.getAnnee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAnnee(), DeclarationEmployeurAnnuelle_.annee));
            }
            if (criteria.getMontantAnnexe1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantAnnexe1(), DeclarationEmployeurAnnuelle_.montantAnnexe1));
            }
            if (criteria.getMontantAnnexe2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantAnnexe2(), DeclarationEmployeurAnnuelle_.montantAnnexe2));
            }
            if (criteria.getMontantAnnexe3() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantAnnexe3(), DeclarationEmployeurAnnuelle_.montantAnnexe3));
            }
            if (criteria.getMontantAnnexe4() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantAnnexe4(), DeclarationEmployeurAnnuelle_.montantAnnexe4));
            }
            if (criteria.getMontantAnnexe5() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantAnnexe5(), DeclarationEmployeurAnnuelle_.montantAnnexe5));
            }
            if (criteria.getMontantAnnexe6() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantAnnexe6(), DeclarationEmployeurAnnuelle_.montantAnnexe6));
            }
            if (criteria.getMontantAnnexe7() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantAnnexe7(), DeclarationEmployeurAnnuelle_.montantAnnexe7));
            }
            if (criteria.getMontantAnnexe8() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantAnnexe8(), DeclarationEmployeurAnnuelle_.montantAnnexe8));
            }
            if (criteria.getMontantAnnexe9() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantAnnexe9(), DeclarationEmployeurAnnuelle_.montantAnnexe9));
            }
            if (criteria.getMontantAnnexe10() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantAnnexe10(), DeclarationEmployeurAnnuelle_.montantAnnexe10));
            }
            if (criteria.getMontantAnnexe11() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantAnnexe11(), DeclarationEmployeurAnnuelle_.montantAnnexe11));
            }
            if (criteria.getMontantAnnexe12() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantAnnexe12(), DeclarationEmployeurAnnuelle_.montantAnnexe12));
            }
            if (criteria.getFicheClientId() != null) {
                specification = specification.and(buildSpecification(criteria.getFicheClientId(),
                    root -> root.join(DeclarationEmployeurAnnuelle_.ficheClient, JoinType.LEFT).get(FicheClient_.id)));
            }
        }
        return specification;
    }
}
