package com.moon.service.mapper;


import com.moon.domain.*;
import com.moon.service.dto.OffreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Offre} and its DTO {@link OffreDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OffreMapper extends EntityMapper<OffreDTO, Offre> {



    default Offre fromId(Long id) {
        if (id == null) {
            return null;
        }
        Offre offre = new Offre();
        offre.setId(id);
        return offre;
    }
}
