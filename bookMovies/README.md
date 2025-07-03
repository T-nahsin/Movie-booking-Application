# 🎬 Movie Booking App - Backend

A simple backend for a BookMyShow-style movie booking system built with **Spring Boot 3.4.6**, **Java 21**, and **MongoDB Atlas**. It supports user authentication, movie filtering, and role-based access using JWT.

## 🔧 Tech Stack
- Java 21
- Spring Boot 3.4.6
- MongoDB Atlas
- Spring Security + JWT
- Maven

## ✨ Features
- User registration & login
- JWT-based authentication
- Role-based access (Admin/User)
- Add & filter movies (Admin/User)
- Movie filtering by genre, language, and city

## 📌 Endpoints
POST /auth/register         → Register

POST /auth/login            → Login and receive JWT

POST /browse-movies         → Filter movies

POST /admin/save-movie       → Add movie (Admin only)

POST /admin/save-theater       → Add theater (Admin only)

POST /admin/save-screen      → Add screens (Admin only)

POST /admin/save-shows       → Add shows (Admin only)

All secured routes require Authorization: Bearer <JWT> in headers.

## ▶️ Run Locally
Clone the repo:

bash
Copy
Edit
git clone https://github.com/T-nahsin/Movie-booking-Application.git
cd bookMovies
Set your MongoDB URI in application.yml.

Start the app:

bash
Copy
Edit
mvn spring-boot:run

## 📂 Folder Structure

bookMovies/
├── controller/
├── model/
├── repository/
├── service/
├── config/


## 🚧 Upcoming
Seat booking

Show timing module

Admin dashboard

Payment integration

## Made with ❤️ by Nishant Singh