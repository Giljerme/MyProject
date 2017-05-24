package com.malynovsky.dto;

import com.malynovsky.entity.User;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * Created by Ivan on 10.04.2017.
 */
public class UserDto implements Serializable {

    private Long id;
    private String username;
    private Long voices;
    private Double rates;

    public UserDto() {
    }

    public UserDto(Long id, String username, Long voices, Double rates) {
        this.id = id;
        this.username = username;
        this.voices = voices;
        this.rates = rates;
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getVoices() {
        return voices;
    }

    public void setVoices(Long voices) {
        this.voices = voices;
    }

    public Double getRates() {
        return rates;
    }

    public void setRates(Double rates) {
        this.rates = rates;
    }
}
