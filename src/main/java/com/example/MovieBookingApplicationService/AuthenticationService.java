package com.example.MovieBookingApplicationService;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jwt.JwtService;
import com.example.MovieBookingApplicationDTO.LoginRequestDTO;
import com.example.MovieBookingApplicationDTO.LoginResponseDTO;
import com.example.MovieBookingApplicationDTO.RegisterRequestDTO;
import com.example.MovieBookingApplicationEntity.User;
import com.example.MovieBookingApplicationRespository.UserRepository;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	public User registerNormalUser(RegisterRequestDTO registerRequestDTO) {

		if (userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()) {
			throw new RuntimeException("Username already registered");
		}

		Set<String> roles = new HashSet<>();
		roles.add("ROLE_USER");

		User user = new User();
		user.setUsername(registerRequestDTO.getUsername());
		user.setEmail(registerRequestDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
		user.setRoles(roles);

		return userRepository.save(user);
	}

	public User registerAdminUser(RegisterRequestDTO registerRequestDTO) {

		if (userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()) {
			throw new RuntimeException("Username already registered");
		}

		Set<String> roles = new HashSet<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");

		User user = new User();
		user.setUsername(registerRequestDTO.getUsername());
		user.setEmail(registerRequestDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
		user.setRoles(roles);

		return userRepository.save(user);
	}

	public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

		User user = userRepository.findByUsername(loginRequestDTO.getUsername())
				.orElseThrow(() -> new RuntimeException("User not found"));

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequestDTO.getUsername(),
						loginRequestDTO.getPassword()
				)
		);

		String token = jwtService.generateToken(user);

		return LoginResponseDTO.builder()
				.jwtToken(token)
				.username(user.getUsername())
				.roles(user.getRoles())
				.build();
	}
}
