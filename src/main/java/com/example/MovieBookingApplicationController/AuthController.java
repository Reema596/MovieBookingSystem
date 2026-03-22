package com.example.MovieBookingApplicationController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MovieBookingApplicationDTO.LoginRequestDTO;
import com.example.MovieBookingApplicationDTO.LoginResponseDTO;
import com.example.MovieBookingApplicationDTO.RegisterRequestDTO;
import com.example.MovieBookingApplicationEntity.User;
import com.example.MovieBookingApplicationService.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/registernormaluser")
	public ResponseEntity<User> registerNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
		return ResponseEntity.ok(authenticationService.registerNormalUser(registerRequestDTO));
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
		return ResponseEntity.ok(authenticationService.login(loginRequestDTO));
	}
}
