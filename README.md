# Warehouse & Inventory System — Desktop Management App

A complete desktop application for warehouse and inventory management built with Java Swing. Handles client registration, product control, stock management, and full CRUD operations with a modern UI.

## Features

| Module | Features |
|---|---|
| Authentication | Login system with credential validation, modern UI |
| Clients | Register clients with DNI/RUC, search, edit, full CRUD |
| Products | Inventory control, pricing, stock management, full CRUD |
| Dashboard | Real-time clock, quick access to all modules |

## Tech Stack

![Java](https://img.shields.io/badge/Java_8+-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Java_Swing-GUI-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat&logo=apache-maven&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=flat&logo=mysql&logoColor=white)

- **Language:** Java 8+
- **GUI:** Java Swing with custom modern design
- **Build tool:** Apache Maven
- **Database:** MySQL (integration ready — see docs)
- **Architecture:** MVC pattern

## Quick Start

**Requirements:** JDK 8+ · Maven 3.6+ · NetBeans or any Java IDE

```bash
git clone https://github.com/fertaxx/warehouse-inventory-system.git
cd warehouse-inventory-system
mvn clean compile
mvn exec:java
```

Or open in NetBeans → right click project → Run

**Test credentials:**

Username: admin

Password: admin

## Database Setup

Full SQL scripts included:
- `database_script.sql` — main structure
- `inventario_db.sql` — initial data

See `GUIA_INTEGRACION_BASE_DE_DATOS.md` for full setup instructions.

## Highlights

- Custom draggable windows with hover effects
- Interactive data tables with inline editing
- Field validation on all forms
- Corporate color scheme with modern flat design
- Ready for MySQL database integration

## Author

**Fernando Dionicio** — [github.com/fertaxx](https://github.com/fertaxx)
