package com.moon.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.moon.domain.Contact} entity.
 */
public class ContactDTO implements Serializable {
    
    private Long id;

    private String nom;

    private String prenom;

    private String email;

    private String region;

    private String typeService;

    private String descriptionRequete;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTypeService() {
        return typeService;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    public String getDescriptionRequete() {
        return descriptionRequete;
    }

    public void setDescriptionRequete(String descriptionRequete) {
        this.descriptionRequete = descriptionRequete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContactDTO)) {
            return false;
        }

        return id != null && id.equals(((ContactDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContactDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", email='" + getEmail() + "'" +
            ", region='" + getRegion() + "'" +
            ", typeService='" + getTypeService() + "'" +
            ", descriptionRequete='" + getDescriptionRequete() + "'" +
            "}";
    }
}
