package com.example.MovieBookingApplicationRespository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MovieBookingApplicationEntity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	Optional<List<Movie>> findByGenre(String genre);
	Optional<List<Movie>> findByLanguage(String language);
	Optional<Movie> findByName(String name);
}
