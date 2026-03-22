package com.example.MovieBookingApplicationDTO;

import java.time.LocalDate;
import lombok.Data;

@Data
public class MovieDTO {
	private String name;
	private String description;
	private String genre;
	private Integer duration;
	private LocalDate releaseDate;
	private String language;
}
