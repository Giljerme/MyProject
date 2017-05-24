package com.malynovsky.dto;

import com.malynovsky.entity.Movie;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Ivan on 09.04.2017.
 */
public class MovieDto implements Serializable {
    long id;
    String name;
    int year;
    String director;
    long budget;
    long boxOffice;
    String description;
    long voiceCount;
    double rate = 0;
    int yourRate = 0;

    public MovieDto() {
    }

    public MovieDto(long id, String name, int year, String director, long budget, long boxOffice, String description) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.director = director;
        this.budget = budget;
        this.boxOffice = boxOffice;
        this.description = description;
    }

    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.year = movie.getYearOfRelease();
        this.director = movie.getDirector();
        this.budget = movie.getBudget();
        this.boxOffice = movie.getBoxOffice();
        this.description = movie.getDescription();
    }

    public Movie toMovie() {
        Movie movie = new Movie();

        movie.setName(name);
        movie.setBoxOffice(boxOffice);
        movie.setBudget(budget);
        movie.setDateOfRelease(year);
        movie.setDirector(director);
        movie.setId(id);
        movie.setDescription(description);

        return movie;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public long getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(long boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYourRate() {
        return yourRate;
    }

    public void setYourRate(int yourRate) {
        this.yourRate = yourRate;
    }

    public long getVoiceCount() {
        return voiceCount;
    }

    public void setVoiceCount(long voiceCount) {
        this.voiceCount = voiceCount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "MovieDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", director='" + director + '\'' +
                ", budget=" + budget +
                ", boxOffice=" + boxOffice +
                ", description='" + description + '\'' +
                ", voiceCount=" + voiceCount +
                ", rate=" + rate +
                ", yourRate=" + yourRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDto movieDto = (MovieDto) o;
        return id == movieDto.id &&
                year == movieDto.year &&
                budget == movieDto.budget &&
                boxOffice == movieDto.boxOffice &&
                voiceCount == movieDto.voiceCount &&
                Double.compare(movieDto.rate, rate) == 0 &&
                yourRate == movieDto.yourRate &&
                Objects.equals(name, movieDto.name) &&
                Objects.equals(director, movieDto.director) &&
                Objects.equals(description, movieDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, year, director, budget, boxOffice, description, voiceCount, rate, yourRate);
    }
}
