# Lolorest - Restaurant Management System

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15+-4169E1?style=for-the-badge&logo=postgresql)
![JavaScript](https://img.shields.io/badge/Vanilla_JS-ES6+-F7DF1E?style=for-the-badge&logo=javascript)

A full-stack, lightweight restaurant automation platform designed to streamline food ordering workflows and menu administration. Built with a high-performance Spring Boot REST backend, PostgreSQL persistence, and a responsive Vanilla JavaScript client interface.

---

## Key Features

* **Role-Based Access Control (RBAC):**
  * **ROLE_ADMIN**: Complete menu administration (Create, Read, Update, Soft-Delete dishes) and full order visibility.
  * **ROLE_WAITER**: Access restricted strictly to order processing (CREATED, IN_PROGRESS). Automatic 403 Forbidden protection on administrative routes.
* **Smart Menu Grouping:** Frontend dynamically groups dishes by custom categories (e.g., Drinks, Mains, Desserts) with real-time status indicators.
* **Data Integrity & Soft-Delete:** Utilizes an ARCHIVED status pattern combined with PostgreSQL CHECK constraints to preserve historical transaction records without deleting active entities.
* **Asynchronous Client Interface:** Single Page Application (SPA) feel engineered with pure Vanilla JS and Fetch API without external heavy JS framework overhead.

---

## Tech Stack

| Domain | Technology |
| :--- | :--- |
| **Backend Framework** | Java 17, Spring Boot 3 |
| **Security** | Spring Security (Session-based RBAC) |
| **Persistence** | Spring Data JPA / Hibernate |
| **Database** | PostgreSQL |
| **Frontend** | Vanilla JS (ES6+), HTML5, CSS3 |
| **Build System** | Maven |

---

## API Overview

### Dish Management (ROLE_ADMIN)
* `GET /lolorest/dish` - Fetch all non-archived menu items grouped by category.
* `POST /lolorest/dish` - Create a new dish entry.
* `PUT /lolorest/dish/{id}` - Update price, category, or status.
* `DELETE /lolorest/dish/{id}` - Soft-delete (sets status to ARCHIVED).

### Order Management (ROLE_ADMIN, ROLE_WAITER)
* `GET /lolorest/order` - Fetch current active orders.
* `POST /lolorest/order` - Place a new order with linked dish items.
* `PATCH /lolorest/order/{id}` - Update order processing status.

---

## Getting Started

### Prerequisites
* **JDK 17** or higher
* **PostgreSQL** database instance
* **Maven** package manager

### Installation & Local Setup

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/yernatsanatuly/lolorest.git](https://github.com/yernatsanatuly/lolorest.git)
   cd lolorest



