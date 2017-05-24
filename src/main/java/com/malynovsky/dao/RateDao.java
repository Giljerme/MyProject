package com.malynovsky.dao;

import com.malynovsky.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Ivan on 09.04.2017.
 */
public interface RateDao extends JpaRepository<Rate, Long> {

    @Query(value = "select r1.id, r1.idMovie, r1.idUser, r1.rateValue " +
            "from rates as r1 LEFT JOIN rates as r2 on r1.idMovie = r2.idMovie and r1.idUser = r2.idUser and r1.id < r2.id " +
            "WHERE r2.id is NULL ", nativeQuery = true)
    List<Rate> findAllUniqueRates();
}
