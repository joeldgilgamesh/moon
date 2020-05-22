package com.moon.service;

import com.moon.service.dto.OffreDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.moon.domain.Offre}.
 */
public interface OffreService {

    /**
     * Save a offre.
     *
     * @param offreDTO the entity to save.
     * @return the persisted entity.
     */
    OffreDTO save(OffreDTO offreDTO);

    /**
     * Get all the offres.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OffreDTO> findAll(Pageable pageable);


    /**
     * Get the "id" offre.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OffreDTO> findOne(Long id);

    /**
     * Delete the "id" offre.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
