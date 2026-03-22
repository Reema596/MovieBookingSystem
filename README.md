# 🎬 Movie Booking Application — Backend API

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.3-green?style=flat-square)
![MySQL](https://img.shields.io/badge/MySQL-8.x-blue?style=flat-square)
![JWT](https://img.shields.io/badge/Auth-JWT-purple?style=flat-square)
![Maven](https://img.shields.io/badge/Build-Maven-red?style=flat-square)

A full-featured Movie Booking REST API built with Spring Boot 3. Supports user registration, JWT authentication, movie catalogue management, theater and show scheduling, and seat booking — with role-based access control for Admin and User roles.

---

## 🛠️ Tech Stack

| Technology | Version / Details |
|---|---|
| Java | 17 |
| Spring Boot | 3.2.3 |
| Spring Security | JWT-based stateless auth |
| Spring Data JPA | Hibernate ORM |
| MySQL | 8.x |
| Lombok | Boilerplate reduction |
| JJWT | 0.12.3 |
| Maven | Build tool |

---

## 📁 Project Structure
```
src/main/java/com/example/
├── MovieBookingApplication.java
├── jwt/
│   ├── JwtService.java
│   └── JwtAuthenticationFilter.java
├── MovieBookingApplicationController/
│   ├── AuthController.java
│   ├── AdminController.java
│   ├── MovieController.java
│   ├── TheaterController.java
│   ├── ShowController.java
│   └── BookingController.java
├── MovieBookingApplicationDTO/
├── MovieBookingApplicationEntity/
├── MovieBookingApplicationRespository/
├── MovieBookingApplicationService/
└── MovieBookingApplicationSecurity/
```

---

## ✅ Prerequisites

- Java 17+
- Maven 3.6+
- MySQL 8.x
- Git

---

## 🚀 Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/YOUR_USERNAME/MovieBookingApplication.git
cd MovieBookingApplication
```

### 2. Create MySQL database
```sql
CREATE DATABASE movie;
```

### 3. Configure `application.yml`

Edit `src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/movie
    username: root          # your MySQL username
    password: your_password # your MySQL password

jwt:
  secret: YOUR_SECRET_KEY   # change this in production!
  expiration: 3600000       # 1 hour

server:
  port: 8081
```

### 4. Build and run
```bash
mvn clean install
mvn spring-boot:run
```

Server starts at: **http://localhost:8081**

### 5. Create your first Admin user

Register a normal user via the API, then run in MySQL:
```sql
-- Find user ID
SELECT id FROM users WHERE username = 'yourusername';

-- Grant admin role
INSERT INTO user_roles (user_id, roles) VALUES (1, 'ROLE_ADMIN');
```

---

## 📡 API Reference

### Auth — `/api/auth`

| Method | Endpoint | Description | Auth |
|---|---|---|---|
| POST | `/api/auth/registernormaluser` | Register new user | No |
| POST | `/api/auth/login` | Login & get JWT | No |

### Admin — `/api/admin`

| Method | Endpoint | Description | Auth |
|---|---|---|---|
| POST | `/api/admin/registeradminuser` | Register admin user | ADMIN only |

### Movies — `/api/movies`

| Method | Endpoint | Description | Auth |
|---|---|---|---|
| GET | `/api/movies/getallmovies` | Get all movies | Yes |
| GET | `/api/movies/getmoviebytitle?title=` | Search by title | Yes |
| GET | `/api/movies/getmoviesbygenre?genre=` | Filter by genre | Yes |
| GET | `/api/movies/getmoviesbylanguage?language=` | Filter by language | Yes |
| POST | `/api/movies/addmovie` | Add movie | ADMIN only |
| PUT | `/api/movies/updatemovie/{id}` | Update movie | ADMIN only |
| DELETE | `/api/movies/deletemovie/{id}` | Delete movie | ADMIN only |

### Theaters — `/api/theater`

| Method | Endpoint | Description | Auth |
|---|---|---|---|
| GET | `/api/theater/gettheaterbylocation?location=` | Get theaters by location | Yes |
| POST | `/api/theater/addtheater` | Add theater | ADMIN only |
| PUT | `/api/theater/updatetheater/{id}` | Update theater | ADMIN only |
| DELETE | `/api/theater/deletetheater/{id}` | Delete theater | ADMIN only |

### Shows — `/api/show`

| Method | Endpoint | Description | Auth |
|---|---|---|---|
| GET | `/api/show/getallshows` | Get all shows | Yes |
| GET | `/api/show/getshowsbymovie/{id}` | Shows for a movie | Yes |
| GET | `/api/show/getshowsbytheater/{id}` | Shows at a theater | Yes |
| POST | `/api/show/createshow` | Create show | Yes |
| PUT | `/api/show/updateshow/{id}` | Update show | Yes |
| DELETE | `/api/show/deleteshow/{id}` | Delete show | Yes |

### Bookings — `/api/bookings`

| Method | Endpoint | Description | Auth |
|---|---|---|---|
| POST | `/api/bookings/createbooking` | Create booking | Yes |
| GET | `/api/bookings/getuserBookings/{id}` | User's bookings | Yes |
| GET | `/api/bookings/getshowBookings/{id}` | Show's bookings | Yes |
| PUT | `/api/bookings/{id}/confirm` | Confirm booking | Yes |
| PUT | `/api/bookings/{id}/cancel` | Cancel booking | Yes |

---

## 📦 Request & Response Examples

### Login
```json
POST /api/auth/login
{
  "username": "john",
  "password": "secret123"
}

Response:
{
  "jwtToken": "eyJhbGciOiJIUzI1NiJ9...",
  "username": "john",
  "roles": ["ROLE_USER"]
}
```

### Add Movie (Admin)
```json
POST /api/movies/addmovie
Authorization: Bearer <jwtToken>

{
  "name": "Interstellar",
  "description": "A journey beyond the stars",
  "genre": "Sci-Fi",
  "duration": 169,
  "releaseDate": "2014-11-07",
  "language": "English"
}
```

### Create Booking
```json
POST /api/bookings/createbooking
Authorization: Bearer <jwtToken>

{
  "numberOfSeats": 2,
  "bookingTime": "2025-06-01T14:00:00",
  "price": 500.00,
  "bookingStatus": "PENDING",
  "seatNumbers": ["A1", "A2"],
  "userId": 1,
  "showId": 3
}
```

---

## 🗃️ Database Schema

Tables are auto-created by Hibernate on startup.

| Table | Key Columns |
|---|---|
| `users` | id, username, email, password (BCrypt) |
| `user_roles` | user_id, roles (ROLE_USER / ROLE_ADMIN) |
| `movie` | id, name, genre, language, duration, releaseDate |
| `theater` | id, theaterName, theaterLocation, theaterCapacity, theaterScreenType |
| `shows` | id, showTime, price, movie_id, theater_id |
| `booking` | id, numberOfSeats, bookingTime, price, bookingStatus, user_id, show_id |
| `booking_seat_numbers` | booking_id, seatNumbers |

---

## 🔐 Security

- Passwords hashed with **BCrypt**
- **Stateless JWT** authentication — no sessions
- Token expires in **1 hour** (configurable)
- CORS enabled for `localhost` and `file://` origins
- Public endpoints: `/api/auth/**`
- Admin-only: `/api/admin/**`, add/update/delete movies & theaters
- All other endpoints require `Authorization: Bearer <token>` header

---

## ⚙️ Configuration Reference

| Property | Default | Description |
|---|---|---|
| `spring.datasource.url` | `jdbc:mysql://localhost:3306/movie` | MySQL URL |
| `spring.datasource.username` | `root` | DB username |
| `spring.datasource.password` | `1234` | DB password — **change this!** |
| `jwt.secret` | `ABCD123...` | JWT signing key — **change in production!** |
| `jwt.expiration` | `3600000` | Token expiry in ms (1 hour) |
| `server.port` | `8081` | Server port |

---

## ⚠️ Before Pushing to GitHub

> **Never commit real credentials.** Replace passwords and JWT secret in `application.yml` before pushing, or add it to `.gitignore`.

Recommended `.gitignore` additions:
```
target/
.idea/
*.class
*.iml
src/main/resources/application.yml
```

Create an `application.yml.example` with placeholder values for other developers.

---

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -m "Add feature"`
4. Push to the branch: `git push origin feature/your-feature`
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License.

---

*Built with Spring Boot 3 · Java 17 · MySQL · JWT*
