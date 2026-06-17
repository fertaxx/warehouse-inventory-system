# Sistema de Almacén e Inventario - InnoTech

Sistema de gestión de almacén e inventario desarrollado en Java con Swing para la Actividad Nro11 de Desarrollo de Software.

## 📋 Descripción

Aplicación de escritorio que permite gestionar:
- **Clientes**: Registro y gestión de clientes
- **Productos**: Control de inventario de productos

## 🚀 Características

### Sistema de Login
- Autenticación de usuarios
- Validación de credenciales
- Interfaz moderna con diseño personalizado

### Menú Principal
- Acceso rápido a los 2 módulos principales
- Reloj en tiempo real
- Navegación intuitiva

### Gestión de Clientes
- Registro de clientes con DNI y RUC
- Búsqueda y edición de información
- Tabla de visualización de datos
- Operaciones CRUD completas

### Gestión de Productos
- Control de inventario
- Registro de precios y cantidades
- Gestión de stock
- Operaciones CRUD completas

## 🛠️ Tecnologías

- **Java 8+**
- **Swing** (GUI)
- **Maven** (Gestión de dependencias)

## 📦 Estructura del Proyecto

```
src/main/java/com/empresa/sistema_de_almacen_inventario/
├── modelos/
│   ├── Cliente.java
│   └── Producto.java
├── vistas/
│   ├── Frm_Login.java
│   ├── Frm_MenuPrincipal.java
│   ├── Frm_Clientes.java
│   └── Frm_Productos.java
└── Sistema_de_almacen_inventario.java
```

## 🔧 Instalación y Ejecución

### Requisitos Previos
- JDK 8 o superior
- Maven 3.6+
- NetBeans IDE (recomendado) o cualquier IDE Java

### Pasos para ejecutar

1. **Clonar o descargar el proyecto**

2. **Compilar el proyecto con Maven:**
   ```bash
   mvn clean compile
   ```

3. **Ejecutar la aplicación:**
   ```bash
   mvn exec:java
   ```

   O desde NetBeans:
   - Abrir el proyecto
   - Click derecho en el proyecto → Run

## 👤 Credenciales de Prueba

Para acceder al sistema, use las siguientes credenciales:

- **Usuario:** admin
- **Contraseña:** admin

## 📝 Uso del Sistema

### 1. Login
- Ingresar email y contraseña
- Click en "Acceder"

### 2. Menú Principal
- Seleccionar el módulo deseado desde el menú lateral o los iconos centrales

### 3. Gestión de Clientes
- Click en "Clientes" desde el menú o tarjeta central
- Click en "Nuevo Cliente" para limpiar el formulario
- Completar los datos del cliente
- Click en "Guardar" para registrar
- Seleccionar un cliente de la tabla para editar

### 4. Gestión de Productos
- Click en "Productos" desde el menú o tarjeta central
- Ingresar datos del producto
- Seleccionar marca del combo box
- Marcar "Stock" si está disponible
- Click en "Guardar"

## 🎨 Características de la Interfaz

- Diseño moderno con colores corporativos
- Ventanas arrastrables
- Botones con efectos hover
- Tablas interactivas
- Validación de campos

## 📌 Notas Importantes

- Los datos se almacenan en memoria (no hay persistencia en base de datos)
- Al cerrar la aplicación, los datos se pierden
- Para implementar persistencia, se puede integrar con MySQL, PostgreSQL o archivos

## 🔜 Mejoras Futuras

- [ ] Integración con base de datos
- [ ] Reportes en PDF
- [ ] Gráficos estadísticos
- [ ] Sistema de respaldo
- [ ] Búsqueda avanzada con filtros
- [ ] Módulo de ventas
- [ ] Gestión de proveedores
- [ ] Sistema de usuarios y permisos

## 👨‍💻 Autor

**alu_torre1**  
Actividad Nro11 - Desarrollo de Software  
SENATI

## 📄 Licencia

Este proyecto es parte de una actividad académica para SENATI.

---

**InnoTech** - Servicio de Gestión e Inventario  
Autorizado por: Sistema de Gestión Empresarial SAC  
Distribuido por: Consac Peru
