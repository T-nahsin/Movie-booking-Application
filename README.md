
# 🎬 Movie Booking Backend

A backend system built with Spring Boot for managing movie ticket bookings — inspired by BookMyShow. It supports user registration, movie browsing, show booking, email notifications, and more.

---

## 🚀 Features

- ✅ User Registration & Login (JWT Auth)
- 🎟️ Browse Movies with Filters (Language, Genre)
- 🕒 Add & Manage Shows with Date-Time & Pricing
- 🏟️ Book & Cancel Show Tickets
- 📩 Email Notifications for:
  - Account Registration
  - Show Booking Confirmation
  - Booking Cancellation
- 📘 API Documentation with Swagger UI

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot 3.4.6
- MongoDB Atlas
- Spring Security + JWT
- Spring Mail
- Swagger/OpenAPI
- Lombok, Maven

---

## 🔐 Authentication

This project uses JWT token-based authentication. Once you log in, you’ll receive a token to authorize future requests.

---

## 📬 Email Service

Uses Spring Mail to send automated emails for:
- Successful registration
- Booking confirmation
- Booking cancellation

---

## 📘 API Documentation

Swagger UI is enabled for all endpoints:

[http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

---

## 🚀 How to Run

### Clone the repo:
```bash
git clone https://github.com/T-nahsin/Movie-booking-Application.git
cd Movie-booking-Application
```

### Configure `application.properties` with:
- MongoDB URI
- JWT secret
- Mail credentials

### Build and run:
```bash
./mvnw spring-boot:run
```

---

## 📂 Project Structure

```
com.tnahsin.bookMovies
│
├── controller/
├── service/
├── entity/
├── repository/
├── dto/
├── config/
└── utils/
```

---

## ✅ Future Enhancements

- 🔗 Integrate TMDB API for real-time movie data
- 💳 Add payment gateway integration
- 🤖 Add AI-powered movie recommendations
- ☁️ Deploy on Heroku or Render

---

## 🙋‍♂️ Author

**Nishant Singh**  
🔗 [LinkedIn](https://www.linkedin.com/in/your-link/)  
📧 nishant16405@gmail.com

---

## 📄 License

This project is open-source and free to use.

