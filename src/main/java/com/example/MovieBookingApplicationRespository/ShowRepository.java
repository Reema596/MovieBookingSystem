package com.example.MovieBookingApplicationRespository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MovieBookingApplicationEntity.Show;

public interface ShowRepository extends JpaRepository<Show, Long> {
	Optional<List<Show>> findByMovieId(Long movieId);
	Optional<List<Show>> findByTheaterId(Long theaterId);
}
