# 🛒 Mini E-Commerce Order Management API

![Java](https://img.shields.io/badge/Java-17-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-green.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange.svg)
![JWT](https://img.shields.io/badge/JWT-Security-red.svg)
![Maven](https://img.shields.io/badge/Maven-3.10-yellow.svg)

> A complete RESTful API for managing an e-commerce platform with user authentication, product management, and order processing.

---

## 📖 Table of Contents

- [About The Project](#-about-the-project)
- [Features](#-features)
- [Technologies Used](#-technologies-used)
- [System Architecture](#-system-architecture)
- [Getting Started](#-getting-started)
    - [Prerequisites](#prerequisites)
    - [Database Setup](#database-setup)
    - [Running the Application](#running-the-application)
- [API Documentation](#-api-documentation)
- [Business Logic](#-business-logic)
- [Security](#-security)
- [Testing](#-testing)
- [Screenshots](#-screenshots)
- [Project Structure](#-project-structure)
- [Author](#-author)

---

## 🎯 About The Project

This project is a **Mini E-Commerce Order Management API** built with **Spring Boot 3.2** and **MySQL**. It provides a complete backend solution for an online store, allowing:

- **Customers** to browse products, create orders, and track their purchases.
- **Admins** to manage products, view all orders, and update order statuses.
- **Security** through JWT-based authentication and role-based authorization.

---

## ✨ Features

### 🔐 Authentication & Authorization
- User registration with email validation
- Login with JWT token generation
- Role-based access control (CUSTOMER / ADMIN)
- Password encryption using BCrypt

### 📦 Product Management
- Full CRUD operations (Create, Read, Update, Delete)
- Advanced search & filtering (by name, category, price range)
- Pagination and sorting
- Admin-only access for Create, Update, Delete

### 🛍️ Order Management
- Create orders with multiple products
- Automatic stock validation
- Real-time stock deduction
- Automatic price calculation
- Order history per customer
- Admin view of all orders
- Order status management (PENDING → CONFIRMED → SHIPPED → DELIVERED / CANCELLED)

### 🛡️ Validation & Error Handling
- Input validation using Spring Validation
- Global exception handling
- Clear error messages
- Proper HTTP status codes

---

## 🛠 Technologies Used

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 17 | Programming Language |
| **Spring Boot** | 3.2.0 | Main Framework |
| **Spring Web** | 6.1.1 | REST APIs |
| **Spring Data JPA** | 3.2.0 | Database Access |
| **Spring Security** | 6.2.0 | Authentication & Authorization |
| **JWT (JJWT)** | 0.11.5 | Token-based Auth |
| **MySQL** | 8.0 | Relational Database |
| **Lombok** | 1.18.42 | Reduce Boilerplate Code |
| **Maven** | 3.10.0 | Build Tool |
| **XAMPP** | - | Local Development Environment |

---

## 🏗 System Architecture

```
┌─────────────────────────────────────────────────────┐
│                    Client (Postman)                 │
└──────────────────────┬──────────────────────────────┘
                       │ HTTP Request
                       ▼
┌─────────────────────────────────────────────────────┐
│                  Spring Security                    │
│              (JWT Authentication Filter)            │
└──────────────────────┬──────────────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────────────┐
│                    Controllers                      │
│    (AuthController, ProductController, OrderCtrl)   │
└──────────────────────┬──────────────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────────────┐
│                     Services                        │
│      (AuthService, ProductService, OrderService)    │
└──────────────────────┬──────────────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────────────┐
│                   Repositories                      │
│    (UserRepo, ProductRepo, OrderRepo, CategoryRepo) │
└──────────────────────┬──────────────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────────────┐
│                    MySQL Database                   │
│                  (ecommerce_db)                     │
└─────────────────────────────────────────────────────┘
```

---

## 🚀 Getting Started

### Prerequisites

Before running the project, make sure you have:

- ✅ **Java 17** or higher ([Download](https://adoptium.net/))
- ✅ **Maven** ([Download](https://maven.apache.org/download.cgi))
- ✅ **XAMPP** ([Download](https://www.apachefriends.org/))
- ✅ **IntelliJ IDEA** (Community or Ultimate)
- ✅ **Git** ([Download](https://git-scm.com/))

---

### Database Setup

#### 1. Start XAMPP

Open **XAMPP Control Panel** and start:
- ✅ Apache
- ✅ MySQL

#### 2. Create Database

1. Open browser: `http://localhost/phpmyadmin`
2. Click **New** on the left
3. Database name: `ecommerce_db`
4. Collation: `utf8mb4_general_ci`
5. Click **Create**

#### 3. Import SQL Script

1. Select `ecommerce_db`
2. Click **SQL** tab
3. Paste the content of `database/ecommerce_db.sql`
4. Click **Go**

#### 4. Test Users

The database includes test users (password: `password123`):

| Email | Role |
|-------|------|
| `admin@shop.com` | ADMIN |
| `ahmed@example.com` | CUSTOMER |

---

### Running the Application

#### 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/ecommerce-api.git
cd ecommerce-api
```

#### 2. Configure Database

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=
```

**Note:** If your MySQL has a password, add it after `=`.

#### 3. Build the Project

```bash
mvn clean install -DskipTests
```

#### 4. Run the Application

**Option A: From IntelliJ**
- Open `EcommerceApiApplication.java`
- Click the green ▶️ button

**Option B: From Terminal**
```bash
mvn spring-boot:run
```

#### 5. Verify

Open browser: `http://localhost:8080/api/products`

You should see a JSON response with the list of products.

---

## 📚 API Documentation

Full API documentation is available in [API_DOCUMENTATION.md](API_DOCUMENTATION.md)

### Quick Endpoints Reference

| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| `POST` | `/api/auth/register` | ❌ | Register new user |
| `POST` | `/api/auth/login` | ❌ | Login |
| `GET` | `/api/products` | ❌ | Get all products |
| `GET` | `/api/products/{id}` | ❌ | Get product by ID |
| `POST` | `/api/products` | ADMIN | Create product |
| `PUT` | `/api/products/{id}` | ADMIN | Update product |
| `DELETE` | `/api/products/{id}` | ADMIN | Delete product |
| `POST` | `/api/orders` | ✅ | Create order |
| `GET` | `/api/orders/my-orders` | ✅ | Get my orders |
| `GET` | `/api/admin/orders` | ADMIN | Get all orders |
| `PATCH` | `/api/admin/orders/{id}/status` | ADMIN | Update order status |

---

## 💼 Business Logic

### Order Creation Flow

```
1. Client sends POST /api/orders with items
         ↓
2. Validate customer exists in database
         ↓
3. For each item:
   - Check product exists
   - Check stock availability
   - Deduct from stock
   - Add to order items
         ↓
4. Calculate total price
         ↓
5. Save order with status = PENDING
         ↓
6. Return order details with items
```

### Stock Management

- **Automatic deduction:** When an order is created, product stock is deducted.
- **Validation:** If requested quantity > available stock → throws `InsufficientStockException`.
- **Transaction:** The entire operation is wrapped in `@Transactional`.

---

## 🔐 Security

### JWT Authentication Flow

```
┌──────────────┐
│   Register   │───► Create user account
└──────────────┘
       │
       ▼
┌──────────────┐
│    Login     │───► Get JWT token
└──────────────┘
       │
       ▼
┌──────────────────────────┐
│  Include Token in Header │
│  Authorization:          │
│  Bearer {token}          │
└──────────────────────────┘
       │
       ▼
┌──────────────────────────┐
│  Access Protected APIs   │
└──────────────────────────┘
```

### Role-Based Access

| Role | Permissions |
|------|-------------|
| **CUSTOMER** | View products, Create orders, View own orders |
| **ADMIN** | Everything + Manage products, View all orders, Update order status |

---

## 🧪 Testing

### Using Postman

1. **Register** a new user.
2. **Login** and copy the JWT token.
3. Include the token in `Authorization: Bearer {token}` header for protected endpoints.

### Test Scenarios Covered

- ✅ User registration & login
- ✅ JWT token validation
- ✅ Product CRUD operations (Admin)
- ✅ Product search & filtering
- ✅ Order creation with stock validation
- ✅ Insufficient stock handling
- ✅ Role-based access control
- ✅ Validation errors
- ✅ Resource not found handling

---

## 📸 Screenshots

*(Add screenshots of your Postman tests here)*

- `screenshots/01-register.png`
- `screenshots/02-login.png`
- `screenshots/03-get-products.png`
- `screenshots/04-create-order.png`
- `screenshots/05-my-orders.png`
- `screenshots/06-admin-orders.png`
- `screenshots/07-update-status.png`

---

## 📁 Project Structure

```
ecommerce-api/
├── 📁 src/
│   ├── 📁 main/
│   │   ├── 📁 java/
│   │   │   └── 📁 com/oceanfirst/ecommerce/
│   │   │       ├── 📄 EcommerceApiApplication.java
│   │   │       ├── 📁 config/          ← Security, JWT Filter
│   │   │       ├── 📁 controller/      ← REST APIs
│   │   │       ├── 📁 dto/             ← Request/Response DTOs
│   │   │       ├── 📁 entity/          ← Database Entities
│   │   │       ├── 📁 exception/       ← Custom Exceptions
│   │   │       ├── 📁 repository/      ← JPA Repositories
│   │   │       ├── 📁 service/         ← Business Logic
│   │   │       └── 📁 util/            ← JWT Utility
│   │   └── 📁 resources/
│   │       └── 📄 application.properties
│   └── 📁 test/
├── 📁 database/
│   └── 📄 ecommerce_db.sql
├── 📄 API_DOCUMENTATION.md
├── 📄 README.md
├── 📄 pom.xml
└── 📄 .gitignore
```

---

## 🎯 Evaluation Criteria

| Criterion | Weight |
|-----------|--------|
| Requirements Analysis & Logic | 10 |
| Database Design & Relations | 10 |
| Authentication & Authorization | 10 |
| CRUD & REST APIs | 10 |
| Orders Business Logic | 10 |
| Validation & Error Handling | 10 |
| Code & Project Organization | 10 |
| API Documentation & README | 10 |
| GitHub Quality & Delivery | 10 |
| **Total** | **100** |

---

## 👨‍💻 Author

**Mai Sobeih**  
Backend Development Assessment  
**Ocean First – Technology & Training**

- 📧 Email: maiooosh49@gmail.com
- 🐙 GitHub: [@MaiSobeih-coder](https://github.com/MaiSobeih-coder)

---

## 📝 License

This project was built as part of a technical assessment for Ocean First.

---

## 🙏 Acknowledgments

- **Ocean First** for the opportunity
- **Spring Boot Team** for the excellent framework
- **Open Source Community** for the amazing tools

---

⭐ If you found this project helpful, please give it a star!