package com.example.MovieBookingApplicationService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MovieBookingApplicationDTO.ShowDTO;
import com.example.MovieBookingApplicationEntity.Booking;
import com.example.MovieBookingApplicationEntity.Movie;
import com.example.MovieBookingApplicationEntity.Show;
import com.example.MovieBookingApplicationEntity.Theater;
import com.example.MovieBookingApplicationRespository.MovieRepository;
import com.example.MovieBookingApplicationRespository.ShowRepository;
import com.example.MovieBookingApplicationRespository.TheaterRepository;

@Service
public class ShowService {

	@Autowired
	private ShowRepository showRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private TheaterRepository theaterRepository;

	public Show createShow(ShowDTO showDTO) {
		Movie movie = movieRepository.findById(showDTO.getMovieId())
				.orElseThrow(() -> new RuntimeException("No Movie Found for id " + showDTO.getMovieId()));

		Theater theater = theaterRepository.findById(showDTO.getTheaterId())
				.orElseThrow(() -> new RuntimeException("No Theater Found for id " + showDTO.getTheaterId()));

		Show show = new Show();
		show.setShowTime(showDTO.getShowTime());
		show.setPrice(showDTO.getPrice());
		show.setMovie(movie);
		show.setTheater(theater);

		return showRepository.save(show);
	}

	public List<Show> getAllShows() {
		return showRepository.findAll();
	}

	public List<Show> getShowsByMovie(Long movieId) {
		Optional<List<Show>> showListBox = showRepository.findByMovieId(movieId);
		if (showListBox.isPresent()) {
			return showListBox.get();
		} else {
			throw new RuntimeException("No shows available for the movie");
		}
	}

	public List<Show> getShowsByTheater(Long theaterId) {
		Optional<List<Show>> showListBox = showRepository.findByTheaterId(theaterId);
		if (showListBox.isPresent()) {
			return showListBox.get();
		} else {
			throw new RuntimeException("No shows available for the theater");
		}
	}

	public Show updateShow(Long id, ShowDTO showDTO) {
		Show show = showRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("No Show available for the id " + id));

		Movie movie = movieRepository.findById(showDTO.getMovieId())
				.orElseThrow(() -> new RuntimeException("No Movie Found for id " + showDTO.getMovieId()));

		Theater theater = theaterRepository.findById(showDTO.getTheaterId())
				.orElseThrow(() -> new RuntimeException("No Theater Found for id " + showDTO.getTheaterId()));

		show.setShowTime(showDTO.getShowTime());
		show.setPrice(showDTO.getPrice());
		show.setMovie(movie);
		show.setTheater(theater);

		return showRepository.save(show);
	}

	public void deleteShow(Long id) {
		Show show = showRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("No show available for the id " + id));

		List<Booking> bookings = show.getBookings();
		if (!bookings.isEmpty()) {
			throw new RuntimeException("Can't delete show with existing bookings");
		}

		showRepository.deleteById(id);
	}
}
