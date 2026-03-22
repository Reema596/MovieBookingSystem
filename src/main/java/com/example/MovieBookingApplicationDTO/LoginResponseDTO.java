package com.example.MovieBookingApplicationDTO;

import java.util.Set;

public class LoginResponseDTO {

	private String jwtToken;
	private String username;
	private Set<String> roles;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String jwtToken;
		private String username;
		private Set<String> roles;

		public Builder jwtToken(String jwtToken) {
			this.jwtToken = jwtToken;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder roles(Set<String> roles) {
			this.roles = roles;
			return this;
		}

		public LoginResponseDTO build() {
			LoginResponseDTO dto = new LoginResponseDTO();
			dto.jwtToken = this.jwtToken;
			dto.username = this.username;
			dto.roles = this.roles;
			return dto;
		}
	}

	public String getJwtToken() { return jwtToken; }
	public String getUsername() { return username; }
	public Set<String> getRoles() { return roles; }

	public void setJwtToken(String jwtToken) { this.jwtToken = jwtToken; }
	public void setUsername(String username) { this.username = username; }
	public void setRoles(Set<String> roles) { this.roles = roles; }
}
