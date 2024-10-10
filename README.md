# Shopping Cart Backend

Backend for a shopping cart application made in Java using Spring Boot, Spring Security, and JWT.

## Features

- User authentication and authorization using JWT.
- Product management (CRUD operations).
- Shopping cart management.
- Order processing.
- Secure endpoints with role-based access control.


## Endpoints

### Authentication

- **POST** `/api/v1/auth/login` - Authenticate user and get JWT token.

### Products

- **GET** `/api/v1/products` - Get all products.
- **GET** `/api/v1/products/{id}` - Get product by ID.
- **POST** `/api/v1/products` - Add a new product (Admin only).
- **PUT** `/api/v1/products/{id}` - Update product (Admin only).
- **DELETE** `/api/v1/products/{id}` - Delete product (Admin only).

### Cart

- **GET** `/api/v1/carts/{cartId}/my-cart` - Get cart by ID.
- **DELETE** `/api/v1/carts/{cartId}/clear` - Clear cart by ID.
- **GET** `/api/v1/carts/{cartId}/total-price` - Get total price of cart.

### Orders

- **POST** `/api/v1/orders/order` - Create a new order.
- **GET** `/api/v1/orders/{orderId}/get` - Get order by ID.
- **GET** `/api/v1/orders/{userId}/get-orders` - Get all orders for a user.
