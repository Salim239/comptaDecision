package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.*;
import com.growup.comptadecision.repository.LigneCaisseRepository;
import com.growup.comptadecision.service.dto.LigneCaisseCriteria;
import com.growup.comptadecision.service.dto.LigneCaisseDTO;
import com.growup.comptadecision.service.mapper.LigneCaisseMapper;
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
 * Service for executing complex queries for LigneCaisse entities in the database.
 * The main input is a {@link LigneCaisseCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link LigneCaisseDTO} or a {@link Page} of {@link LigneCaisseDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LigneCaisseQueryService extends QueryService<LigneCaisse> {

    private final Logger log = LoggerFactory.getLogger(LigneCaisseQueryService.class);

    private final LigneCaisseRepository ligneCaisseRepository;

    private final LigneCaisseMapper ligneCaisseMapper;

    public LigneCaisseQueryService(LigneCaisseRepository ligneCaisseRepository, LigneCaisseMapper ligneCaisseMapper) {
        this.ligneCaisseRepository = ligneCaisseRepository;
        this.ligneCaisseMapper = ligneCaisseMapper;
    }

    /**
     * Return a {@link List} of {@link LigneCaisseDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<LigneCaisseDTO> findByCriteria(LigneCaisseCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<LigneCaisse> specification = createSpecification(criteria);
        return ligneCaisseMapper.toDto(ligneCaisseRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link LigneCaisseDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<LigneCaisseDTO> findByCriteria(LigneCaisseCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<LigneCaisse> specification = createSpecification(criteria);
        return ligneCaisseRepository.findAll(specification, page)
            .map(ligneCaisseMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LigneCaisseCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<LigneCaisse> specification = createSpecification(criteria);
        return ligneCaisseRepository.count(specification);
    }

    /**
     * Function to convert LigneCaisseCriteria to a {@link Specification}
     */
    private Specification<LigneCaisse> createSpecification(LigneCaisseCriteria criteria) {
        Specification<LigneCaisse> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), LigneCaisse_.id));
            }
            if (criteria.getTypeOperation() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTypeOperation(), LigneCaisse_.typeOperation));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), LigneCaisse_.libelle));
            }
            if (criteria.getMontantOperation() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantOperation(), LigneCaisse_.montantOperation));
            }
            if (criteria.getPaiementId() != null) {
                specification = specification.and(buildSpecification(criteria.getPaiementId(),
                    root -> root.join(LigneCaisse_.paiement, JoinType.LEFT).get(Paiement_.id)));
            }
            if (criteria.getQuittanceMensuelleId() != null) {
                specification = specification.and(buildSpecification(criteria.getQuittanceMensuelleId(),
                    root -> root.join(LigneCaisse_.quittanceMensuelle, JoinType.LEFT).get(QuittanceMensuelle_.id)));
            }
            if (criteria.getDeclarationAnnuelleId() != null) {
                specification = specification.and(buildSpecification(criteria.getDeclarationAnnuelleId(),
                    root -> root.join(LigneCaisse_.declarationAnnuelle, JoinType.LEFT).get(DeclarationAnnuelle_.id)));
            }
            if (criteria.getCnssId() != null) {
                specification = specification.and(buildSpecification(criteria.getCnssId(),
                    root -> root.join(LigneCaisse_.cnss, JoinType.LEFT).get(Cnss_.id)));
            }
            if (criteria.getCaisseId() != null) {
                specification = specification.and(buildSpecification(criteria.getCaisseId(),
                    root -> root.join(LigneCaisse_.caisse, JoinType.LEFT).get(Caisse_.id)));
            }
        }
        return specification;
    }
}
