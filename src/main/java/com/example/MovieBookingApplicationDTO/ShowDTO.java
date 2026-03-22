package com.example.MovieBookingApplicationDTO;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ShowDTO {
	private LocalDateTime showTime;
	private Double price;
	private Long movieId;
	private Long theaterId;
}
