# 🌴 tourism-api-2026

A **lightweight, scalable backend API** built with **Spring Boot** for a modern tourism platform.
This API enables seamless interaction between **tour guides** and **tourists**, powering a smooth booking and tour management experience.

---

## ✨ Features

* 🚀 High-performance **Spring Boot backend**
* 🧭 Designed for **tour guide & tourist interactions**
* 🐳 Fully **Dockerized environment**
* ⚡ Fast **setup and deployment**
* 📦 Production-ready architecture

---

# 🛠 Tech Stack

| Technology       | Version    |
| ---------------- | ---------- |
| Java             | **21**     |
| Spring Boot      | **4.1.0**  |
| Build Tool       | **Maven**  |
| Packaging        | **JAR**    |
| Containerization | **Docker** |

---

# 📦 Prerequisites

Make sure the following tools are installed before running the project.

### Required Software

* 🐳 **Docker**
* 🐙 **Docker Compose**

Install Docker Compose if needed:

```bash
sudo apt install docker-compose
```

---

# 🚀 Getting Started

Follow these steps to run the project locally.

---

## 📥 Clone the Repository

```bash
git clone https://github.com/2000sans/tourism-api-2026.git
cd tourism-api-2026
```

---

## 🐳 Running the Application

The project uses **Docker Compose** to simplify setup.

---

### 🔹 First Time Run (Build + Start)

Use this when running the project for the **first time**.

```bash
docker compose up --build
```

---

### 🔹 After Code Changes (Rebuild + Restart)

Use this command when you **modify the code**.

```bash
docker compose up --build
```

---

### 🔹 Production Mode (Detached)

Runs containers **in the background**.

```bash
docker compose up -d --build
```

---

### 🔹 Stop All Services

Stops and removes containers.

```bash
docker compose down
```

---

# 📂 Project Structure

```text
tourism-api-2026
│
├── src/                        # Main Spring Boot application source code
│   └── main/
│       ├── java/               # Java classes (controllers, services, models, etc.)
│       └── resources/          # Configuration files (application.yml / properties)
│
├── .mvn/wrapper/               # Maven wrapper configuration files
│
├── Dockerfile                  # Docker image build instructions for the Spring Boot app
├── docker-compose.yaml         # Multi-container Docker configuration
│
├── mvnw                        # Maven wrapper script (Linux / macOS)
├── mvnw.cmd                    # Maven wrapper script (Windows)
│
├── pom.xml                     # Maven dependencies and project configuration
│
├── README.md                   # Project documentation
├── info.txt                    # Additional project notes or metadata
│
├── .gitignore                  # Files and directories ignored by Git
└── .gitattributes              # Git repository configuration
```

---

### 🧩 Structure Overview

* **src/** → Contains the core **Spring Boot application code**
* **Dockerfile** → Builds the application into a **Docker container**
* **docker-compose.yaml** → Manages multi-container setup and service orchestration
* **.mvn + mvnw** → Enables running Maven **without installing Maven globally**
* **pom.xml** → Defines **project dependencies, plugins, and build configuration**

---

# 🐳 Docker Workflow

This project uses Docker to ensure:

* ✔ Consistent development environments
* ✔ Easy deployment
* ✔ Faster onboarding for developers

Simply run:

```bash
docker compose up --build
```

And the entire system will be ready.

---