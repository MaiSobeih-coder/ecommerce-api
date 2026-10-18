# 📚 API Documentation

## Mini E-Commerce Order Management API

**Base URL:** `http://localhost:8080`

**Content-Type:** `application/json`

**Authentication:** Bearer Token (JWT)

---

## 📑 Table of Contents

1. [Authentication APIs](#1-authentication-apis)
2. [Product APIs](#2-product-apis)
3. [Order APIs](#3-order-apis)
4. [Admin Order APIs](#4-admin-order-apis)
5. [Error Responses](#5-error-responses)

---

## 1. Authentication APIs

### 1.1 Register New User

Creates a new customer account.

| Property | Value |
|----------|-------|
| **Endpoint** | `/api/auth/register` |
| **Method** | `POST` |
| **Auth Required** | ❌ No |

**Request Body:**

```json
{
  "name": "Mai Sobeih",
  "email": "mai@example.com",
  "password": "password123"
}
```

**Validation Rules:**

| Field | Rules |
|-------|-------|
| `name` | Required, 2-100 characters |
| `email` | Required, valid email format, unique |
| `password` | Required, minimum 6 characters |

**Success Response (200 OK):**

```json
{
  "success": true,
  "message": "Registration successful",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "type": "Bearer",
    "id": 3,
    "name": "Mai Sobeih",
    "email": "mai@example.com",
    "role": "CUSTOMER"
  }
}
```

**Error Response (400 Bad Request):**

```json
{
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "email": "Invalid email format",
    "password": "Password must be at least 6 characters"
  }
}
```

---

### 1.2 Login

Authenticates a user and returns a JWT token.

| Property | Value |
|----------|-------|
| **Endpoint** | `/api/auth/login` |
| **Method** | `POST` |
| **Auth Required** | ❌ No |

**Request Body:**

```json
{
  "email": "admin@shop.com",
  "password": "password123"
}
```

**Success Response (200 OK):**

```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "type": "Bearer",
    "id": 1,
    "name": "Admin User",
    "email": "admin@shop.com",
    "role": "ADMIN"
  }
}
```

**Error Response (401 Unauthorized):**

```json
{
  "status": 401,
  "message": "Invalid email or password"
}
```

---

## 2. Product APIs

### 2.1 Get All Products

Retrieves a paginated list of products with optional filters.

| Property | Value |
|----------|-------|
| **Endpoint** | `/api/products` |
| **Method** | `GET` |
| **Auth Required** | ❌ No |

**Query Parameters:**

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `name` | String | - | Filter by product name (partial match) |
| `categoryId` | Long | - | Filter by category |
| `minPrice` | Double | - | Minimum price |
| `maxPrice` | Double | - | Maximum price |
| `page` | Integer | 0 | Page number (0-indexed) |
| `size` | Integer | 10 | Page size |
| `sortBy` | String | id | Sort field |
| `sortDir` | String | asc | Sort direction (asc/desc) |

**Example Request:**

```
GET http://localhost:8080/api/products?name=laptop&minPrice=100&page=0&size=5
```

**Success Response (200 OK):**

```json
{
  "success": true,
  "message": "Products retrieved",
  "data": {
    "content": [
      {
        "id": 1,
        "name": "Laptop HP",
        "description": "HP 15-inch laptop, 16GB RAM, 512GB SSD",
        "price": 1200.0,
        "stockQuantity": 15,
        "imageUrl": null,
        "categoryId": 1,
        "categoryName": "Electronics"
      }
    ],
    "totalElements": 1,
    "totalPages": 1,
    "size": 5,
    "number": 0
  }
}
```

---

### 2.2 Get Product by ID

Retrieves a single product by its ID.

| Property | Value |
|----------|-------|
| **Endpoint** | `/api/products/{id}` |
| **Method** | `GET` |
| **Auth Required** | ❌ No |

**Example Request:**

```
GET http://localhost:8080/api/products/1
```

**Success Response (200 OK):**

```json
{
  "success": true,
  "message": "Product retrieved",
  "data": {
    "id": 1,
    "name": "Laptop HP",
    "description": "HP 15-inch laptop, 16GB RAM, 512GB SSD",
    "price": 1200.0,
    "stockQuantity": 15,
    "imageUrl": null,
    "categoryId": 1,
    "categoryName": "Electronics"
  }
}
```

**Error Response (404 Not Found):**

```json
{
  "timestamp": "2026-10-15T15:30:00",
  "status": 404,
  "message": "Product not found with id: 999"
}
```

---

### 2.3 Create Product (Admin Only)

Creates a new product.

| Property | Value |
|----------|-------|
| **Endpoint** | `/api/products` |
| **Method** | `POST` |
| **Auth Required** | ✅ Yes (ADMIN) |

**Headers:**

```
Authorization: Bearer {admin_token}
```

**Request Body:**

```json
{
  "name": "Gaming Mouse",
  "description": "RGB gaming mouse with 8 buttons",
  "price": 75.0,
  "stockQuantity": 50,
  "imageUrl": "https://example.com/mouse.jpg",
  "categoryId": 1
}
```

**Success Response (200 OK):**

```json
{
  "success": true,
  "message": "Product created",
  "data": {
    "id": 8,
    "name": "Gaming Mouse",
    "description": "RGB gaming mouse with 8 buttons",
    "price": 75.0,
    "stockQuantity": 50,
    "imageUrl": "https://example.com/mouse.jpg",
    "categoryId": 1,
    "categoryName": "Electronics"
  }
}
```

**Error Response (403 Forbidden):**

```json
{
  "status": 403,
  "message": "Access denied"
}
```

---

### 2.4 Update Product (Admin Only)

Updates an existing product.

| Property | Value |
|----------|-------|
| **Endpoint** | `/api/products/{id}` |
| **Method** | `PUT` |
| **Auth Required** | ✅ Yes (ADMIN) |

**Request Body:**

```json
{
  "name": "Gaming Mouse Pro",
  "description": "RGB gaming mouse with 12 buttons",
  "price": 95.0,
  "stockQuantity": 40,
  "imageUrl": "https://example.com/mouse-pro.jpg",
  "categoryId": 1
}
```

**Success Response (200 OK):**

```json
{
  "success": true,
  "message": "Product updated",
  "data": {
    "id": 8,
    "name": "Gaming Mouse Pro",
    "price": 95.0,
    "stockQuantity": 40
  }
}
```

---

### 2.5 Delete Product (Admin Only)

Deletes a product by its ID.

| Property | Value |
|----------|-------|
| **Endpoint** | `/api/products/{id}` |
| **Method** | `DELETE` |
| **Auth Required** | ✅ Yes (ADMIN) |

**Example Request:**

```
DELETE http://localhost:8080/api/products/8
Authorization: Bearer {admin_token}
```

**Success Response (200 OK):**

```json
{
  "success": true,
  "message": "Product deleted"
}
```

---

## 3. Order APIs

### 3.1 Create Order

Creates a new order for the authenticated customer.

| Property | Value |
|----------|-------|
| **Endpoint** | `/api/orders` |
| **Method** | `POST` |
| **Auth Required** | ✅ Yes (Any authenticated user) |

**Headers:**

```
Authorization: Bearer {customer_token}
```

**Request Body:**

```json
{
  "shippingAddress": "Gaza, Palestine",
  "items": [
    {
      "productId": 1,
      "quantity": 2
    },
    {
      "productId": 3,
      "quantity": 1
    }
  ]
}
```

**Business Logic:**

1. Validates customer exists.
2. Validates all products exist.
3. Checks stock availability for each product.
4. Deducts quantities from stock.
5. Calculates total price.
6. Saves order with status `PENDING`.

**Success Response (200 OK):**

```json
{
  "success": true,
  "message": "Order created",
  "data": {
    "id": 1,
    "userId": 3,
    "userName": "Mai Sobeih",
    "totalPrice": 2435.0,
    "status": "PENDING",
    "shippingAddress": "Gaza, Palestine",
    "createdAt": "2026-10-15T15:35:00",
    "items": [
      {
        "productId": 1,
        "productName": "Laptop HP",
        "quantity": 2,
        "price": 1200.0,
        "subtotal": 2400.0
      },
      {
        "productId": 3,
        "productName": "Wireless Mouse",
        "quantity": 1,
        "price": 35.0,
        "subtotal": 35.0
      }
    ]
  }
}
```

**Error Response - Insufficient Stock (400 Bad Request):**

```json
{
  "status": 400,
  "message": "Insufficient stock for product: Laptop HP. Available: 13, Requested: 1000"
}
```

**Error Response - Product Not Found (404 Not Found):**

```json
{
  "status": 404,
  "message": "Product not found with id: 999"
}
```

---

### 3.2 Get My Orders

Retrieves all orders for the authenticated customer.

| Property | Value |
|----------|-------|
| **Endpoint** | `/api/orders/my-orders` |
| **Method** | `GET` |
| **Auth Required** | ✅ Yes (Any authenticated user) |

**Headers:**

```
Authorization: Bearer {customer_token}
```

**Success Response (200 OK):**

```json
{
  "success": true,
  "message": "Orders retrieved",
  "data": [
    {
      "id": 1,
      "userId": 3,
      "userName": "Mai Sobeih",
      "totalPrice": 2435.0,
      "status": "PENDING",
      "shippingAddress": "Gaza, Palestine",
      "createdAt": "2026-10-15T15:35:00",
      "items": [...]
    }
  ]
}
```

---

## 4. Admin Order APIs

### 4.1 Get All Orders (Admin Only)

Retrieves all orders in the system.

| Property | Value |
|----------|-------|
| **Endpoint** | `/api/admin/orders` |
| **Method** | `GET` |
| **Auth Required** | ✅ Yes (ADMIN) |

**Headers:**

```
Authorization: Bearer {admin_token}
```

**Success Response (200 OK):**

```json
{
  "success": true,
  "message": "All orders retrieved",
  "data": [
    {
      "id": 1,
      "userId": 3,
      "userName": "Mai Sobeih",
      "totalPrice": 2435.0,
      "status": "PENDING"
    },
    {
      "id": 2,
      "userId": 4,
      "userName": "Ahmed Ali",
      "totalPrice": 500.0,
      "status": "SHIPPED"
    }
  ]
}
```

---

### 4.2 Update Order Status (Admin Only)

Updates the status of an order.

| Property | Value |
|----------|-------|
| **Endpoint** | `/api/admin/orders/{id}/status` |
| **Method** | `PATCH` |
| **Auth Required** | ✅ Yes (ADMIN) |

**Valid Statuses:**

- `PENDING`
- `CONFIRMED`
- `SHIPPED`
- `DELIVERED`
- `CANCELLED`

**Headers:**

```
Authorization: Bearer {admin_token}
```

**Request Body:**

```json
{
  "status": "SHIPPED"
}
```

**Success Response (200 OK):**

```json
{
  "success": true,
  "message": "Order status updated",
  "data": {
    "id": 1,
    "userId": 3,
    "userName": "Mai Sobeih",
    "totalPrice": 2435.0,
    "status": "SHIPPED",
    "shippingAddress": "Gaza, Palestine"
  }
}
```

---

## 5. Error Responses

### Common HTTP Status Codes

| Code | Meaning | When It Happens |
|------|---------|-----------------|
| **200** | OK | Request succeeded |
| **400** | Bad Request | Validation failed, insufficient stock |
| **401** | Unauthorized | Missing or invalid token |
| **403** | Forbidden | Authenticated but not authorized (e.g., customer trying admin action) |
| **404** | Not Found | Resource doesn't exist |
| **500** | Internal Server Error | Server-side error |

---

### Error Response Format

All errors follow this format:

```json
{
  "timestamp": "2026-10-15T15:35:00",
  "status": 400,
  "message": "Description of the error"
}
```

### Validation Errors

```json
{
  "timestamp": "2026-10-15T15:35:00",
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "field1": "Error message 1",
    "field2": "Error message 2"
  }
}
```

---

### Common Error Scenarios

#### 1. Missing Required Field

```json
{
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "name": "Name is required"
  }
}
```

#### 2. Invalid Email

```json
{
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "email": "Invalid email format"
  }
}
```

#### 3. Email Already Exists

```json
{
  "status": 400,
  "message": "Email already in use: mai@example.com"
}
```

#### 4. Invalid Credentials

```json
{
  "status": 401,
  "message": "Invalid email or password"
}
```

#### 5. Missing Token

```json
{
  "status": 401,
  "message": "Unauthorized"
}
```

#### 6. Insufficient Permissions

```json
{
  "status": 403,
  "message": "Access denied"
}
```

#### 7. Resource Not Found

```json
{
  "status": 404,
  "message": "Product not found with id: 999"
}
```

---

## 🔐 Authentication Flow

```
┌──────────────┐
│   Register   │───► Get user account created
└──────────────┘
       │
       ▼
┌──────────────┐
│    Login     │───► Get JWT token
└──────────────┘
       │
       ▼
┌──────────────────────┐
│  Use Token in Header │───► Access protected APIs
│  Authorization:       │
│  Bearer {token}       │
└──────────────────────┘
```

---

## 📋 Summary of Endpoints

| # | Method | Endpoint | Auth | Description |
|---|--------|----------|------|-------------|
| 1 | POST | `/api/auth/register` | ❌ | Register new user |
| 2 | POST | `/api/auth/login` | ❌ | Login |
| 3 | GET | `/api/products` | ❌ | Get all products |
| 4 | GET | `/api/products/{id}` | ❌ | Get product by ID |
| 5 | POST | `/api/products` | ADMIN | Create product |
| 6 | PUT | `/api/products/{id}` | ADMIN | Update product |
| 7 | DELETE | `/api/products/{id}` | ADMIN | Delete product |
| 8 | POST | `/api/orders` | ✅ | Create order |
| 9 | GET | `/api/orders/my-orders` | ✅ | Get my orders |
| 10 | GET | `/api/admin/orders` | ADMIN | Get all orders |
| 11 | PATCH | `/api/admin/orders/{id}/status` | ADMIN | Update order status |

---

## 👨‍💻 Author

**Mai Sobeih**  
Backend Developer Assessment  
Ocean First – Technology & Training

**Date:** October 2026