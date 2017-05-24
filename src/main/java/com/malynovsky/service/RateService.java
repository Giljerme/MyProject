package com.malynovsky.service;

import com.malynovsky.dto.RateDto;
import com.malynovsky.entity.Rate;

import java.util.List;

/**
 * Created by Ivan on 09.04.2017.
 */
public interface RateService {

    void addRate(RateDto rate);

    Rate findByMovieId(long id);

    Rate findByUserId(long id);

    List<Rate> getAllUniqueRates();
}
