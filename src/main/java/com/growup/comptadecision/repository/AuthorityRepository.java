package com.growup.comptadecision.repository;

import com.growup.comptadecision.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaSpecificationExecutor<AuthorityRepository>, JpaRepository<Authority, String> {
}
