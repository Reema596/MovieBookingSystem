package com.example.MovieBookingApplicationRespository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MovieBookingApplicationEntity.Theater;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
	Optional<List<Theater>> findByTheaterLocation(String location);
}
