package com.example.MovieBookingApplicationController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MovieBookingApplicationDTO.ShowDTO;
import com.example.MovieBookingApplicationEntity.Show;
import com.example.MovieBookingApplicationService.ShowService;
import java.util.List;

@RestController
@RequestMapping("/api/show")
public class ShowController {

	@Autowired
	private ShowService showService;

	@PostMapping("/createshow")
	public ResponseEntity<Show> createShow(@RequestBody ShowDTO showDTO) {
		return ResponseEntity.ok(showService.createShow(showDTO));
	}

	@GetMapping("/getallshows")
	public ResponseEntity<List<Show>> getAllShows() {
		return ResponseEntity.ok(showService.getAllShows());
	}

	@GetMapping("/getshowsbymovie/{id}")
	public ResponseEntity<List<Show>> getShowsByMovie(@PathVariable Long id) {
		return ResponseEntity.ok(showService.getShowsByMovie(id));
	}

	@GetMapping("/getshowsbytheater/{id}")
	public ResponseEntity<List<Show>> getShowsByTheater(@PathVariable Long id) {
		return ResponseEntity.ok(showService.getShowsByTheater(id));
	}

	@PutMapping("/updateshow/{id}")
	public ResponseEntity<Show> updateShow(@PathVariable Long id, @RequestBody ShowDTO showDTO) {
		return ResponseEntity.ok(showService.updateShow(id, showDTO));
	}

	@DeleteMapping("/deleteshow/{id}")
	public ResponseEntity<Void> deleteShow(@PathVariable Long id) {
		showService.deleteShow(id);
		return ResponseEntity.ok().build();
	}
}
