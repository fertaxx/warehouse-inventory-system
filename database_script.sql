-- Script para crear la base de datos del Sistema de Inventario
-- Ejecuta este script en MySQL Workbench o phpMyAdmin

-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS inventario_db;
USE inventario_db;

-- Tabla de Clientes
CREATE TABLE IF NOT EXISTS clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    identificador VARCHAR(50) NOT NULL UNIQUE,
    nombres VARCHAR(200) NOT NULL,
    dni VARCHAR(8),
    ruc VARCHAR(11),
    direccion VARCHAR(300),
    localidad VARCHAR(100),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_identificador (identificador),
    INDEX idx_dni (dni)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Tabla de Productos
CREATE TABLE IF NOT EXISTS productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    no_serie VARCHAR(100) NOT NULL UNIQUE,
    nombre VARCHAR(200) NOT NULL,
    marca VARCHAR(100),
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    cantidad INT NOT NULL DEFAULT 0,
    fecha_compra DATE,
    fecha_caducidad DATE,
    stock TINYINT(1) DEFAULT 1,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_no_serie (no_serie),
    INDEX idx_nombre (nombre)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Tabla de Inventario
CREATE TABLE IF NOT EXISTS inventario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo_sku VARCHAR(100) NOT NULL UNIQUE,
    nombre_producto VARCHAR(200) NOT NULL,
    categoria VARCHAR(100),
    proveedor VARCHAR(200),
    stock_inicial INT DEFAULT 0,
    entradas INT DEFAULT 0,
    salidas INT DEFAULT 0,
    devoluciones INT DEFAULT 0,
    stock_final INT DEFAULT 0,
    costo_unitario DECIMAL(10,2) DEFAULT 0.00,
    precio_venta DECIMAL(10,2) DEFAULT 0.00,
    valor_inventario DECIMAL(12,2) DEFAULT 0.00,
    punto_reorden INT DEFAULT 0,
    estado VARCHAR(50) DEFAULT 'Disponible',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_codigo (codigo_sku),
    INDEX idx_estado (estado)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Tabla de Ventas
CREATE TABLE IF NOT EXISTS ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_identificador VARCHAR(50),
    cliente_nombre VARCHAR(200),
    producto_no_serie VARCHAR(100),
    producto_nombre VARCHAR(200),
    cantidad INT NOT NULL DEFAULT 1,
    precio_unitario DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    subtotal DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    igv DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    total DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    fecha_venta DATE,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_cliente (cliente_identificador),
    INDEX idx_producto (producto_no_serie),
    INDEX idx_fecha (fecha_venta)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Tabla de Usuarios (opcional para login)
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    tipo_usuario ENUM('Administrador', 'Usuario') DEFAULT 'Usuario',
    activo TINYINT(1) DEFAULT 1,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insertar usuario administrador por defecto
INSERT INTO usuarios (email, contrasena, tipo_usuario) 
VALUES ('admin', 'admin', 'Administrador')
ON DUPLICATE KEY UPDATE email=email;

-- Datos de ejemplo para Clientes
INSERT INTO clientes (identificador, nombres, dni, ruc, direccion, localidad) VALUES
('CLI001', 'Juan Pérez García', '12345678', '10123456789', 'Av. Principal 123', 'Lima'),
('CLI002', 'María López Sánchez', '87654321', '10876543210', 'Jr. Los Olivos 456', 'Callao'),
('CLI003', 'Carlos Rodríguez Díaz', '11223344', '10112233445', 'Calle Las Flores 789', 'Comas')
ON DUPLICATE KEY UPDATE identificador=identificador;

-- Datos de ejemplo para Productos
INSERT INTO productos (no_serie, nombre, marca, descripcion, precio, cantidad, stock) VALUES
('PROD001', 'Laptop HP Pavilion', 'HP', 'Laptop 15.6 pulgadas, Intel i5, 8GB RAM', 2500.00, 10, 1),
('PROD002', 'Mouse Inalámbrico', 'Logitech', 'Mouse óptico inalámbrico', 45.00, 50, 1),
('PROD003', 'Teclado Mecánico', 'Razer', 'Teclado gaming RGB', 350.00, 15, 1),
('PROD004', 'Monitor Samsung 24"', 'Samsung', 'Monitor Full HD 24 pulgadas', 650.00, 8, 1),
('PROD005', 'Impresora Epson L3150', 'Epson', 'Impresora multifuncional con sistema continuo', 850.00, 5, 1)
ON DUPLICATE KEY UPDATE no_serie=no_serie;

-- Mostrar resumen
SELECT 'Base de datos creada exitosamente' AS Mensaje;
SELECT COUNT(*) AS Total_Clientes FROM clientes;
SELECT COUNT(*) AS Total_Productos FROM productos;
SELECT COUNT(*) AS Total_Ventas FROM ventas;
SELECT COUNT(*) AS Total_Usuarios FROM usuarios;
