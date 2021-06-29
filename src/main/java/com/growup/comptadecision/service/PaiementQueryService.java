package com.growup.comptadecision.service;

import com.growup.comptadecision.domain.*;
import com.growup.comptadecision.repository.PaiementRepository;
import com.growup.comptadecision.service.dto.PaiementCriteria;
import com.growup.comptadecision.service.dto.PaiementDTO;
import com.growup.comptadecision.service.mapper.PaiementMapper;
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
 * Service for executing complex queries for Paiement entities in the database.
 * The main input is a {@link PaiementCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PaiementDTO} or a {@link Page} of {@link PaiementDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PaiementQueryService extends QueryService<Paiement> {

    private final Logger log = LoggerFactory.getLogger(PaiementQueryService.class);

    private final PaiementRepository paiementRepository;

    private final PaiementMapper paiementMapper;

    public PaiementQueryService(PaiementRepository paiementRepository, PaiementMapper paiementMapper) {
        this.paiementRepository = paiementRepository;
        this.paiementMapper = paiementMapper;
    }

    /**
     * Return a {@link List} of {@link PaiementDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PaiementDTO> findByCriteria(PaiementCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Paiement> specification = createSpecification(criteria);
        return paiementMapper.toDto(paiementRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PaiementDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PaiementDTO> findByCriteria(PaiementCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Paiement> specification = createSpecification(criteria);
        return paiementRepository.findAll(specification, page)
            .map(paiementMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PaiementCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Paiement> specification = createSpecification(criteria);
        return paiementRepository.count(specification);
    }

    /**
     * Function to convert PaiementCriteria to a {@link Specification}
     */
    private Specification<Paiement> createSpecification(PaiementCriteria criteria) {
        Specification<Paiement> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Paiement_.id));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), Paiement_.libelle));
            }
            if (criteria.getModePaiement() != null) {
                specification = specification.and(buildSpecification(criteria.getModePaiement(), Paiement_.modePaiement));
            }
            if (criteria.getNumeroPreuvePaiement() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroPreuvePaiement(), Paiement_.numeroPreuvePaiement));
            }
            if (criteria.getDatePaiement() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDatePaiement(), Paiement_.datePaiement));
            }
            if (criteria.getMontantPaye() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantPaye(), Paiement_.montantPaye));
            }
            if (criteria.getMontantDu() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontantDu(), Paiement_.montantDu));
            }
            if (criteria.getLigneCaisseId() != null) {
                specification = specification.and(buildSpecification(criteria.getLigneCaisseId(),
                    root -> root.join(Paiement_.ligneCaisse, JoinType.LEFT).get(LigneCaisse_.id)));
            }
            if (criteria.getFicheClientId() != null) {
                specification = specification.and(buildSpecification(criteria.getFicheClientId(),
                    root -> root.join(Paiement_.ficheClient, JoinType.LEFT).get(FicheClient_.id)));
            }
            if (criteria.getCnssId() != null) {
                specification = specification.and(buildSpecification(criteria.getCnssId(),
                    root -> root.join(Paiement_.cnss, JoinType.LEFT).get(Cnss_.id)));
            }
            if (criteria.getQuittanceMensuelleId() != null) {
                specification = specification.and(buildSpecification(criteria.getQuittanceMensuelleId(),
                    root -> root.join(Paiement_.quittanceMensuelle, JoinType.LEFT).get(QuittanceMensuelle_.id)));
            }
            if (criteria.getDeclarationAnnuelleId() != null) {
                specification = specification.and(buildSpecification(criteria.getDeclarationAnnuelleId(),
                    root -> root.join(Paiement_.declarationAnnuelle, JoinType.LEFT).get(DeclarationAnnuelle_.id)));
            }
            if (criteria.getDeclarationEmployeurAnnuelleId() != null) {
                specification = specification.and(buildSpecification(criteria.getDeclarationEmployeurAnnuelleId(),
                    root -> root.join(Paiement_.declarationEmployeurAnnuelle, JoinType.LEFT).get(DeclarationEmployeurAnnuelle_.id)));
            }
            if (criteria.getAcompteProvisionnelId() != null) {
                specification = specification.and(buildSpecification(criteria.getAcompteProvisionnelId(),
                    root -> root.join(Paiement_.acompteProvisionnel, JoinType.LEFT).get(AcompteProvisionnel_.id)));
            }
        }
        return specification;
    }
}
