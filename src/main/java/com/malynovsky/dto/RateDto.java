package com.malynovsky.dto;

import com.malynovsky.entity.Rate;

import javax.persistence.Column;

/**
 * Created by Ivan on 09.04.2017.
 */
public class RateDto {
    private Long id;
    private Long idMovie;
    private Long idUser;
    private Integer rateValue;

    public RateDto(Long idMovie, Long idUser, Integer rateValue) {
        this.idMovie = idMovie;
        this.idUser = idUser;
        this.rateValue = rateValue;
    }

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

    public Rate toDto() {
        Rate rate = new Rate();

        rate.setIdUser(this.idUser);
        rate.setIdMovie(this.idMovie);
        rate.setRateValue(this.rateValue);

        return rate;
    }
}
