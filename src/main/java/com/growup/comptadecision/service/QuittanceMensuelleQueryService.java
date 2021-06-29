package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.FicheClient_;
import com.growup.comptadecision.domain.QuittanceMensuelle;
import com.growup.comptadecision.domain.QuittanceMensuelleLigne_;
import com.growup.comptadecision.domain.QuittanceMensuelle_;
import com.growup.comptadecision.repository.QuittanceMensuelleRepository;
import com.growup.comptadecision.service.dto.QuittanceMensuelleCriteria;
import com.growup.comptadecision.service.dto.QuittanceMensuelleDTO;
import com.growup.comptadecision.service.mapper.QuittanceMensuelleMapper;
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
 * Service for executing complex queries for QuittanceMensuelle entities in the database.
 * The main input is a {@link QuittanceMensuelleCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QuittanceMensuelleDTO} or a {@link Page} of {@link QuittanceMensuelleDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QuittanceMensuelleQueryService extends QueryService<QuittanceMensuelle> {

    private final Logger log = LoggerFactory.getLogger(QuittanceMensuelleQueryService.class);

    private final QuittanceMensuelleRepository quittanceMensuelleRepository;

    private final QuittanceMensuelleMapper quittanceMensuelleMapper;

    public QuittanceMensuelleQueryService(QuittanceMensuelleRepository quittanceMensuelleRepository, QuittanceMensuelleMapper quittanceMensuelleMapper) {
        this.quittanceMensuelleRepository = quittanceMensuelleRepository;
        this.quittanceMensuelleMapper = quittanceMensuelleMapper;
    }

    /**
     * Return a {@link List} of {@link QuittanceMensuelleDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QuittanceMensuelleDTO> findByCriteria(QuittanceMensuelleCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<QuittanceMensuelle> specification = createSpecification(criteria);
        return quittanceMensuelleMapper.toDto(quittanceMensuelleRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QuittanceMensuelleDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QuittanceMensuelleDTO> findByCriteria(QuittanceMensuelleCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<QuittanceMensuelle> specification = createSpecification(criteria);
        return quittanceMensuelleRepository.findAll(specification, page)
            .map(quittanceMensuelleMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(QuittanceMensuelleCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<QuittanceMensuelle> specification = createSpecification(criteria);
        return quittanceMensuelleRepository.count(specification);
    }

    /**
     * Function to convert QuittanceMensuelleCriteria to a {@link Specification}
     */
    private Specification<QuittanceMensuelle> createSpecification(QuittanceMensuelleCriteria criteria) {
        Specification<QuittanceMensuelle> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), QuittanceMensuelle_.id));
            }
            if (criteria.getAnnee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAnnee(), QuittanceMensuelle_.annee));
            }
            if (criteria.getMois() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMois(), QuittanceMensuelle_.mois));
            }
            if (criteria.getNumeroQuittance() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroQuittance(), QuittanceMensuelle_.numeroQuittance));
            }
            if (criteria.getDatePaiement() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDatePaiement(), QuittanceMensuelle_.datePaiement));
            }
            if (criteria.getMontantTotal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantTotal(), QuittanceMensuelle_.montantTotal));
            }
            if (criteria.getTypeDeclaration() != null) {
                specification = specification.and(buildSpecification(criteria.getTypeDeclaration(), QuittanceMensuelle_.typeDeclaration));
            }
            if (criteria.getStatut() != null) {
                specification = specification.and(buildSpecification(criteria.getStatut(), QuittanceMensuelle_.statut));
            }
            if (criteria.getQuittanceMensuelleLigneId() != null) {
                specification = specification.and(buildSpecification(criteria.getQuittanceMensuelleLigneId(),
                    root -> root.join(QuittanceMensuelle_.quittanceMensuelleLignes, JoinType.LEFT).get(QuittanceMensuelleLigne_.id)));
            }
            if (criteria.getFicheClientId() != null) {
                specification = specification.and(buildSpecification(criteria.getFicheClientId(),
                    root -> root.join(QuittanceMensuelle_.ficheClient, JoinType.LEFT).get(FicheClient_.id)));
            }
            if (criteria.getParentQuittanceId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentQuittanceId(),
                    root -> root.join(QuittanceMensuelle_.parentQuittance, JoinType.LEFT).get(QuittanceMensuelle_.id)));
            }
        }
        return specification;
    }
}
