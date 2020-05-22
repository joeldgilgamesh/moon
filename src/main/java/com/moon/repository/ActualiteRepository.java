package com.moon.repository;

import com.moon.domain.Actualite;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Actualite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActualiteRepository extends JpaRepository<Actualite, Long> {
}
