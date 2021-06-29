package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.DeclarationAnnuelle;
import com.growup.comptadecision.domain.DeclarationAnnuelleLigne_;
import com.growup.comptadecision.domain.DeclarationAnnuelle_;
import com.growup.comptadecision.domain.FicheClient_;
import com.growup.comptadecision.repository.DeclarationAnnuelleRepository;
import com.growup.comptadecision.service.dto.DeclarationAnnuelleCriteria;
import com.growup.comptadecision.service.dto.DeclarationAnnuelleDTO;
import com.growup.comptadecision.service.mapper.DeclarationAnnuelleMapper;
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
 * Service for executing complex queries for DeclarationAnnuelle entities in the database.
 * The main input is a {@link DeclarationAnnuelleCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DeclarationAnnuelleDTO} or a {@link Page} of {@link DeclarationAnnuelleDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DeclarationAnnuelleQueryService extends QueryService<DeclarationAnnuelle> {

    private final Logger log = LoggerFactory.getLogger(DeclarationAnnuelleQueryService.class);

    private final DeclarationAnnuelleRepository declarationAnnuelleRepository;

    private final DeclarationAnnuelleMapper declarationAnnuelleMapper;

    public DeclarationAnnuelleQueryService(DeclarationAnnuelleRepository declarationAnnuelleRepository, DeclarationAnnuelleMapper declarationAnnuelleMapper) {
        this.declarationAnnuelleRepository = declarationAnnuelleRepository;
        this.declarationAnnuelleMapper = declarationAnnuelleMapper;
    }

    /**
     * Return a {@link List} of {@link DeclarationAnnuelleDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DeclarationAnnuelleDTO> findByCriteria(DeclarationAnnuelleCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DeclarationAnnuelle> specification = createSpecification(criteria);
        return declarationAnnuelleMapper.toDto(declarationAnnuelleRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DeclarationAnnuelleDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DeclarationAnnuelleDTO> findByCriteria(DeclarationAnnuelleCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DeclarationAnnuelle> specification = createSpecification(criteria);
        return declarationAnnuelleRepository.findAll(specification, page)
            .map(declarationAnnuelleMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DeclarationAnnuelleCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DeclarationAnnuelle> specification = createSpecification(criteria);
        return declarationAnnuelleRepository.count(specification);
    }

    /**
     * Function to convert DeclarationAnnuelleCriteria to a {@link Specification}
     */
    private Specification<DeclarationAnnuelle> createSpecification(DeclarationAnnuelleCriteria criteria) {
        Specification<DeclarationAnnuelle> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), DeclarationAnnuelle_.id));
            }
            if (criteria.getTypeDeclaration() != null) {
                specification = specification.and(buildSpecification(criteria.getTypeDeclaration(), DeclarationAnnuelle_.typeDeclaration));
            }
            if (criteria.getAnnee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAnnee(), DeclarationAnnuelle_.annee));
            }
            if (criteria.getDatePaiement() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDatePaiement(), DeclarationAnnuelle_.datePaiement));
            }
            if (criteria.getNumeroQuittance() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroQuittance(), DeclarationAnnuelle_.numeroQuittance));
            }
            if (criteria.getMontantImpotAnnuel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantImpotAnnuel(), DeclarationAnnuelle_.montantImpotAnnuel));
            }
            if (criteria.getMontantApPayes() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantApPayes(), DeclarationAnnuelle_.montantApPayes));
            }
            if (criteria.getMontantRetenueSource() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantRetenueSource(), DeclarationAnnuelle_.montantRetenueSource));
            }
            if (criteria.getMontantReportAnterieur() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantReportAnterieur(), DeclarationAnnuelle_.montantReportAnterieur));
            }
            if (criteria.getMontantNet() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantNet(), DeclarationAnnuelle_.montantNet));
            }
            if (criteria.getStatut() != null) {
                specification = specification.and(buildSpecification(criteria.getStatut(), DeclarationAnnuelle_.statut));
            }
            if (criteria.getDeclarationAnnuelleLigneId() != null) {
                specification = specification.and(buildSpecification(criteria.getDeclarationAnnuelleLigneId(),
                    root -> root.join(DeclarationAnnuelle_.declarationAnnuelleLignes, JoinType.LEFT).get(DeclarationAnnuelleLigne_.id)));
            }
            if (criteria.getFicheClientId() != null) {
                specification = specification.and(buildSpecification(criteria.getFicheClientId(),
                    root -> root.join(DeclarationAnnuelle_.ficheClient, JoinType.LEFT).get(FicheClient_.id)));
            }
        }
        return specification;
    }
}
