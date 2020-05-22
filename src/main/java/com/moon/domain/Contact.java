package com.moon.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A Contact.
 */
@Entity
@Table(name = "contact")
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "region")
    private String region;

    @Column(name = "type_service")
    private String typeService;

    @Column(name = "description_requete")
    private String descriptionRequete;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Contact nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Contact prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public Contact email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegion() {
        return region;
    }

    public Contact region(String region) {
        this.region = region;
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTypeService() {
        return typeService;
    }

    public Contact typeService(String typeService) {
        this.typeService = typeService;
        return this;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    public String getDescriptionRequete() {
        return descriptionRequete;
    }

    public Contact descriptionRequete(String descriptionRequete) {
        this.descriptionRequete = descriptionRequete;
        return this;
    }

    public void setDescriptionRequete(String descriptionRequete) {
        this.descriptionRequete = descriptionRequete;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contact)) {
            return false;
        }
        return id != null && id.equals(((Contact) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Contact{" +
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
