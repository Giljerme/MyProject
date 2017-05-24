package com.malynovsky.entity;

import javax.persistence.*;

/**
 * Created by Ivan on 09.04.2017.
 */
@Entity
@Table(name = "rates")
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long idMovie;

    @Column
    private Long idUser;

    @Column
    private Integer rateValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Long idMovie) {
        this.idMovie = idMovie;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Integer getRateValue() {
        return rateValue;
    }

    public void setRateValue(Integer rateValue) {
        this.rateValue = rateValue;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", idMovie=" + idMovie +
                ", idUser=" + idUser +
                ", rateValue=" + rateValue +
                '}';
    }
}
