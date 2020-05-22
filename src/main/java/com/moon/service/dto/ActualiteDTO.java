package com.moon.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;

/**
 * A DTO for the {@link com.moon.domain.Actualite} entity.
 */
@ApiModel(description = "not an ignored comment")
public class ActualiteDTO implements Serializable {
    
    private Long id;

    private String titre;

    private String description;

    private String datePublication;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActualiteDTO)) {
            return false;
        }

        return id != null && id.equals(((ActualiteDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ActualiteDTO{" +
            "id=" + getId() +
            ", titre='" + getTitre() + "'" +
            ", description='" + getDescription() + "'" +
            ", datePublication='" + getDatePublication() + "'" +
            "}";
    }
}
