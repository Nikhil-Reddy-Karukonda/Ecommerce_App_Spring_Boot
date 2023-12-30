# 🛒 Ecommerce_App_Spring_Boot

This Spring Boot application is designed for e-commerce solutions, integrating Spring Data JPA and a PostgreSQL database. It provides a robust backend system for managing e-commerce operations.

## 🚀 Quick Start

### 📋 Prerequisites
- 🐳 Docker
- ☕ Java JDK 11 or later
- 🧱 Maven

### 🗄️ Setting Up the PostgreSQL Database with Docker
- Run `docker ps` to list all running containers.
- Find the name or ID of the PostgreSQL container in the output.
- Run `docker exec -it <container_name_or_id> bash`, replacing `<container_name_or_id>` with the actual name or ID of your PostgreSQL container.
- Inside the container's bash session, run `psql -U postgres -d ecommerce_db` to connect to the PostgreSQL server and then DB.
- Run `\l` to list all available databases.
- `\dt` to see a list of relations.
- `\d table_name` to see the schema of a specific table.
- Run `\q` to exit the psql prompt.
- Press `ctrl+D` to exit the bash session inside the container.

### 🌐 Running the Spring Boot Application
- Clone the repository: `git clone [repository-link]`.
- Navigate to the project directory and run:
  - `mvn clean install`
  - `mvn spring-boot:run`

## 📐 Database Models and Relationships

1. **🏠 Address**
   - Related to `👤 Customer` (One-to-One or One-to-Many): Each customer may have multiple addresses (billing, shipping).

2. **🏷️ Category**
   - Related to `📦 Product` (One-to-Many): Each category encompasses multiple products.

3. **👤 Customer**
   - Related to `🏠 Address` (One-to-One or One-to-Many): Customers can have multiple addresses.
   - Related to `🛒 Order` (One-to-Many): A customer can place multiple orders.

4. **🛒 Order**
   - Related to `👤 Customer` (Many-to-One): Each order is linked to a single customer.
   - Related to `📦 OrderItem` (One-to-Many): An order can contain multiple items.

5. **📦 OrderItem**
   - Related to `🛒 Order` (Many-to-One): Each item is part of an order.
   - Related to `📦 Product` (Many-to-One): Each item corresponds to a product.

6. **📦 Product**
   - Related to `🏷️ Category` (Many-to-One): Each product belongs to a category.
   - Related to `📦 OrderItem` (One-to-Many): A product can be part of multiple order items.
