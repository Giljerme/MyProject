package com.malynovsky.entity;

import javax.persistence.*;

/**
 * Created by Ivan on 08.04.2017.
 */
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column(name = "premiere_year")
    private Integer yearOfRelease;

    @Column
    private String director;

    @Column
    private Long budget;

    @Column(name = "box_office")
    private Long boxOffice;

    @Column
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYearOfRelease() {
        return yearOfRelease;
    }

    public void setDateOfRelease(Integer dateOfRelease) {
        this.yearOfRelease = dateOfRelease;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public Long getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(Long boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfRelease=" + yearOfRelease +
                ", director='" + director + '\'' +
                ", budget=" + budget +
                ", boxOffice=" + boxOffice +
                ", description='" + description + '\'' +
                '}';
    }
}
