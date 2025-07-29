# 📝 JournalApp

A full-stack journal management application designed to help users log their daily thoughts and emotions securely and intelligently. The backend is built with **Spring Boot**, **MongoDB**, **Kafka**, **Redis**, and integrated with **Google OAuth2.0** for authentication and **OpenWeather API** for contextual logging.

---

## 🚀 Features

- ✅ Secure user registration & login with Google OAuth 2.0
- 🗒️ Create, update, and delete journal entries
- 📅 Weekly sentiment analysis using Kafka and consumer groups
- 🌦️ Weather integration using OpenWeather API
- 📧 Email notifications via SMTP (Gmail)
- 💾 Persistent storage with MongoDB Atlas
- ⚡ Caching and session handling with Redis
- 🛡️ JWT-based token security

---

## 🛠️ Tech Stack

| Layer        | Technology                                      |
|--------------|--------------------------------------------------|
| Backend      | Spring Boot, Spring Security, Spring Data MongoDB |
| Auth         | Google OAuth 2.0, JWT                           |
| Messaging    | Apache Kafka (Confluent Cloud)                  |
| Caching      | Redis                                           |
| DB           | MongoDB Atlas                                   |
| External API | OpenWeather                                     |
| Email        | SMTP (Gmail)                                    |

---

## 📂 Project Structure

journalApp/
├── src/
│ ├── main/
│ │ ├── java/net/engineeringdigest/journalApp/
│ │ └── resources/
│ │ ├── application.yml # ❗️Ignored in .gitignore
│ │ └── application-example.yml # 🔓 Use this as a template
├── .gitignore
├── pom.xml
└── README.md



## ⚙️ Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/journalApp.git
   cd journalApp

2. **Set up config**

- Copy application-example.yml to application.yml
- Add your own:
          -- MongoDB URI
          -- Google Client ID/Secret
          -- Redis credentials
          -- Kafka Confluent credentials
          -- OpenWeather API key

3.**Build the project**  - mvn clean install
4.**Run the app** - Run the App
