# 🎬 Movie Booking Application 

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

- **Java 21**
- **Spring Boot 3.4.6**
- **MongoDB Atlas**
- **Spring Security + JWT**
- **Spring Mail**
- **Swagger/OpenAPI**
- **Lombok**, **Maven**

---

## 🔐 Authentication

This project uses **JWT token-based authentication**. Once you log in, you’ll receive a token to authorize future requests.

---

## 📬 Email Service

Uses Spring Mail to send automated emails for:
- Successful registration
- Booking confirmation
- Booking cancellation

---

## 📄 API Documentation

Swagger UI is enabled for all endpoints.

```bash
http://localhost:8080/swagger-ui/index.html
📦 How to Run
Clone the repo:

bash
Copy
Edit
git clone https://github.com/your-username/movie-booking-app.git
Navigate to the project folder:

bash
Copy
Edit
cd movie-booking-app
Configure application.properties:

MongoDB URI

JWT secret

Email credentials

Build and run:

bash
Copy
Edit
./mvnw spring-boot:run
📂 Project Structure
arduino
Copy
Edit
com.tnahsin.bookMovies
│
├── controller/
├── service/
├── entity/
├── repository/
├── dto/
├── config/
└── utils/
✅ Future Enhancements
🔗 Integrate TMDB API for real-time movie data

💳 Add payment gateway integration

🤖 Add AI-powered movie recommendations

☁️ Deploy on Heroku or Render

---

### 🙋‍♂️ Author

Nishant Singh
📧 Email
🔗 LinkedIn
🌐 Portfolio (if available)

📄 License
This project is open-source and free to use.

yaml
Copy
Edit

---

### ✅ What to Do:
- Replace the GitHub URL, email, LinkedIn with your actual links.
- Add any additional features if you've done more (like role-based access).

Want a **more advanced README with screenshots, endpoints table, or badges** too? I can generate that if you want to showcase it better!








Ask ChatGPT
