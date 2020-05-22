package com.moon.service;

import com.moon.service.dto.ActualiteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.moon.domain.Actualite}.
 */
public interface ActualiteService {

    /**
     * Save a actualite.
     *
     * @param actualiteDTO the entity to save.
     * @return the persisted entity.
     */
    ActualiteDTO save(ActualiteDTO actualiteDTO);

    /**
     * Get all the actualites.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ActualiteDTO> findAll(Pageable pageable);


    /**
     * Get the "id" actualite.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ActualiteDTO> findOne(Long id);

    /**
     * Delete the "id" actualite.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
