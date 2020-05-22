package com.moon.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * not an ignored comment
 */
@Entity
@Table(name = "actualite")
public class Actualite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titre")
    private String titre;

    @Column(name = "description")
    private String description;

    @Column(name = "date_publication")
    private String datePublication;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public Actualite titre(String titre) {
        this.titre = titre;
        return this;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public Actualite description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public Actualite datePublication(String datePublication) {
        this.datePublication = datePublication;
        return this;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Actualite)) {
            return false;
        }
        return id != null && id.equals(((Actualite) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Actualite{" +
            "id=" + getId() +
            ", titre='" + getTitre() + "'" +
            ", description='" + getDescription() + "'" +
            ", datePublication='" + getDatePublication() + "'" +
            "}";
    }
}
