package com.moon.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.moon.domain.Offre} entity.
 */
public class OffreDTO implements Serializable {
    
    private Long id;

    private String type;

    private String description;

    private String image;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OffreDTO)) {
            return false;
        }

        return id != null && id.equals(((OffreDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OffreDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", description='" + getDescription() + "'" +
            ", image='" + getImage() + "'" +
            "}";
    }
}
