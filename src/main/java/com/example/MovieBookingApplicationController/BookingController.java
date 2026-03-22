package com.example.MovieBookingApplicationController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MovieBookingApplicationDTO.BookingDTO;
import com.example.MovieBookingApplicationEntity.Booking;
import com.example.MovieBookingApplicationService.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping("/createbooking")
	public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDTO) {
		return ResponseEntity.ok(bookingService.createBooking(bookingDTO));
	}

	@GetMapping("/getuserBookings/{id}")
	public ResponseEntity<List<Booking>> getUserBookings(@PathVariable Long id) {
		return ResponseEntity.ok(bookingService.getUserBookings(id));
	}

	@GetMapping("/getshowBookings/{id}")
	public ResponseEntity<List<Booking>> getShowBookings(@PathVariable Long id) {
		return ResponseEntity.ok(bookingService.getShowBookings(id));
	}

	@PutMapping("/{id}/confirm")
	public ResponseEntity<Booking> confirmBooking(@PathVariable Long id) {
		return ResponseEntity.ok(bookingService.confirmBooking(id));
	}

	@PutMapping("/{id}/cancel")
	public ResponseEntity<Booking> cancelBooking(@PathVariable Long id) {
		return ResponseEntity.ok(bookingService.cancelBooking(id));
	}
}
