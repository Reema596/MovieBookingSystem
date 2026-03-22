package com.example.MovieBookingApplicationRespository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MovieBookingApplicationEntity.Booking;
import com.example.MovieBookingApplicationEntity.BookingStatus;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findByUserId(Long userId);
	List<Booking> findByShowId(Long showId);
	List<Booking> findByBookingStatus(BookingStatus bookingStatus);
}
