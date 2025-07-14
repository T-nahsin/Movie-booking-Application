# 🎬 Movie Booking App - Backend

This is a full-fledged backend service for a Movie Booking Application, inspired by BookMyShow. The application allows users to register, browse movies, select theatres and screens, view available seats, and book/cancel seats for shows.

---

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot 3.4.6**
- **MongoDB Atlas**
- **Spring Security with JWT**
- **Maven**
- **Lombok**
- **Postman** (for API testing)
- **IntelliJ IDEA**

---

## ✅ Features

### 👤 User
- Register new users
- Login using username & password
- JWT token-based authentication

### 🎞️ Movie Management
- Add movies
- Filter movies by language and genre

### 🏢 Theatre & Screen Management
- Create theatres
- Add screens to theatres

### 🪑 Seat Management
- Add seats to screens (A1 to G3)
- Define seat types (Silver, Gold)

### 📅 Show Management
- Assign movies to shows
- Create shows with timing, price, and seats

### 🎟️ Booking System
- Book specific seats for a show
- Cancel booked seats
- View available seats for a show

---

## 🧩 Project Structure

bookMovies/
├── controller/
├── service/
├── repository/
├── entity/
├── config/
├── security/
└── BookMoviesApplication.java

yaml
Copy
Edit

---

## 🔐 Authentication

- JWT Token is issued after successful login.
- Include the token in the `Authorization` header as:
Bearer <token>

yaml
Copy
Edit

---

## 📦 Sample JSON (Create Seats)

```json
[
{ "id": "A1", "seatType": "Silver" },
{ "id": "B1", "seatType": "Silver" },
{ "id": "F1", "seatType": "Gold" }
]
📬 API Endpoints
Endpoint	Method	Description	Secured
/auth/register	POST	Register a new user	
/auth/login	POST	Login and receive JWT	
/movies/add	POST	Add a new movie	
/movies/browse	GET	Browse movies by filters	
/admin/save-theater	POST	Create a theatre	
/admin/create-screen	POST	Add screen to theatre	
/admin/save-seats	POST	Add seats to screen	
/admin/create	POST	Create a show	
/admin/save-show	POST	Generate seats for a show	
/user/book-seats	POST	Book selected seats	
/user/cancel-booking	POST	Cancel selected seats	

🧪 Testing
Use Postman or Swagger (upcoming) to test endpoints. JWT must be added to headers for secured routes.

🚀 Future Enhancements
Add Swagger for API documentation

Frontend using React or Flutter

Email notifications

Payment gateway integration

Deploy on Heroku / Render

📂 How to Run Locally
Clone the repo

bash
Copy
Edit
git clone https://github.com/T-nahsin/Movie-booking-Application.git
Navigate to the backend folder

bash
Copy
Edit
cd bookMovies
Run the application

arduino
Copy
Edit
mvn spring-boot:run
Test via Postman or any REST client



## Made with ❤️ by Nishant Singh
