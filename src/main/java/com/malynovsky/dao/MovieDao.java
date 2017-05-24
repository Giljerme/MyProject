package com.malynovsky.dao;

import com.malynovsky.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Ivan on 08.04.2017.
 */
public interface MovieDao extends JpaRepository<Movie, Long> {
    /*@Query(value = "SELECT * from movies where director like :name" , nativeQuery = true)*/
    List<Movie> findByDirector(String director); //add Containing

    @Query(value = "SELECT * from movies where movies.name like :name" , nativeQuery = true)
    List<Movie> findByMovieName(@Param("name") String name);

    @Query(value = "SELECT * from movies where movies.description like :descr" , nativeQuery = true)
    List<Movie> findByMovieDescription(@Param("descr") String descr);
}
