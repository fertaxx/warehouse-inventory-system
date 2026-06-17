-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-05-2026 a las 15:16:30
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `inventario_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL,
  `identificador` varchar(50) NOT NULL,
  `nombres` varchar(200) NOT NULL,
  `dni` varchar(8) DEFAULT NULL,
  `ruc` varchar(11) DEFAULT NULL,
  `direccion` varchar(300) DEFAULT NULL,
  `localidad` varchar(100) DEFAULT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id`, `identificador`, `nombres`, `dni`, `ruc`, `direccion`, `localidad`, `fecha_registro`) VALUES
(1, 'CLI001', 'Juan Pérez García', '12345678', '10123456789', 'Av. Principal 123', 'Lima', '2026-05-04 14:09:01'),
(2, 'CLI002', 'María López Sánchez', '87654321', '10876543210', 'Jr. Los Olivos 456', 'Callao', '2026-05-04 14:09:01'),
(3, 'CLI003', 'Carlos Rodríguez Díaz', '11223344', '10112233445', 'Calle Las Flores 789', 'Comas', '2026-05-04 14:09:01');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `no_serie` varchar(100) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `marca` varchar(100) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `precio` decimal(10,2) NOT NULL DEFAULT 0.00,
  `cantidad` int(11) NOT NULL DEFAULT 0,
  `fecha_compra` date DEFAULT NULL,
  `fecha_caducidad` date DEFAULT NULL,
  `stock` tinyint(1) DEFAULT 1,
  `fecha_registro` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `no_serie`, `nombre`, `marca`, `descripcion`, `precio`, `cantidad`, `fecha_compra`, `fecha_caducidad`, `stock`, `fecha_registro`) VALUES
(1, 'PROD001', 'Laptop HP Pavilion', 'HP', 'Laptop 15.6 pulgadas, Intel i5, 8GB RAM', 2500.00, 10, NULL, NULL, 1, '2026-05-04 14:09:01'),
(2, 'PROD002', 'Mouse Inalámbrico', 'Logitech', 'Mouse óptico inalámbrico', 45.00, 50, NULL, NULL, 1, '2026-05-04 14:09:01'),
(3, 'PROD003', 'Teclado Mecánico', 'Razer', 'Teclado gaming RGB', 350.00, 15, NULL, NULL, 1, '2026-05-04 14:09:01'),
(4, 'PROD004', 'Monitor Samsung 24\"', 'Samsung', 'Monitor Full HD 24 pulgadas', 650.00, 8, NULL, NULL, 1, '2026-05-04 14:09:01'),
(5, 'PROD005', 'Impresora Epson L3150', 'Epson', 'Impresora multifuncional con sistema continuo', 850.00, 5, NULL, NULL, 1, '2026-05-04 14:09:01'),
(6, 'PROD006', 'Television', 'Sony', 'de 60 pulgadas', 1500.00, 10, '2026-05-04', '2026-05-04', 1, '2026-05-04 16:03:13');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `tipo_usuario` enum('Administrador','Usuario') DEFAULT 'Usuario',
  `activo` tinyint(1) DEFAULT 1,
  `fecha_registro` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `email`, `contrasena`, `tipo_usuario`, `activo`, `fecha_registro`) VALUES
(1, 'admin', 'admin', 'Administrador', 1, '2026-05-04 14:09:01');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `identificador` (`identificador`),
  ADD KEY `idx_identificador` (`identificador`),
  ADD KEY `idx_dni` (`dni`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `no_serie` (`no_serie`),
  ADD KEY `idx_no_serie` (`no_serie`),
  ADD KEY `idx_nombre` (`nombre`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `idx_email` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
