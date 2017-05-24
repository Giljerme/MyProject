package com.malynovsky.service.impl;

import com.malynovsky.dao.RateDao;
import com.malynovsky.dto.RateDto;
import com.malynovsky.entity.Rate;
import com.malynovsky.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ivan on 09.04.2017.
 */
@Service
public class RateServiceImpl implements RateService {
    @Autowired
    private RateDao rateDao;

    @Override
    public void addRate(RateDto rate) {
        rateDao.save(rate.toDto());
    }

    @Override
    public Rate findByMovieId(long id) {
        return null;
    }

    @Override
    public Rate findByUserId(long id) {
        return null;
    }

    @Override
    public List<Rate> getAllUniqueRates() {

        return rateDao.findAllUniqueRates();
    }
}
