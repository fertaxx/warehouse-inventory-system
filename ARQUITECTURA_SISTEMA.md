# 🏗️ ARQUITECTURA DEL SISTEMA

## 📐 Diagrama de Capas

```
┌─────────────────────────────────────────────────────────────┐
│                    CAPA DE PRESENTACIÓN                      │
│                         (Vistas)                             │
├─────────────────────────────────────────────────────────────┤
│  Frm_Login.java          │  Valida credenciales             │
│  Frm_MenuPrincipal.java  │  Menú principal del sistema      │
│  Frm_Clientes.java       │  Gestión de clientes             │
│  Frm_Productos.java      │  Gestión de productos            │
└─────────────────────────────────────────────────────────────┘
                            ↕
┌─────────────────────────────────────────────────────────────┐
│                    CAPA DE LÓGICA                            │
│                    (Modelos y DAOs)                          │
├─────────────────────────────────────────────────────────────┤
│  Cliente.java            │  Modelo de datos de Cliente      │
│  Producto.java           │  Modelo de datos de Producto     │
│                          │                                   │
│  ClienteDAO.java         │  Operaciones BD para Clientes    │
│  ProductoDAO.java        │  Operaciones BD para Productos   │
│  UsuarioDAO.java         │  Validación de usuarios          │
└─────────────────────────────────────────────────────────────┘
                            ↕
┌─────────────────────────────────────────────────────────────┐
│                    CAPA DE DATOS                             │
│                    (Base de Datos)                           │
├─────────────────────────────────────────────────────────────┤
│  Conexion.java           │  Gestiona conexión a MySQL       │
│                          │                                   │
│  MySQL Database          │  inventario_db                   │
│    ├─ clientes           │  Tabla de clientes               │
│    ├─ productos          │  Tabla de productos              │
│    └─ usuarios           │  Tabla de usuarios               │
└─────────────────────────────────────────────────────────────┘
```

---

## 🔄 FLUJO DE DATOS

### 1. Login (Autenticación)
```
Usuario ingresa credenciales
         ↓
Frm_Login captura datos
         ↓
UsuarioDAO.validarCredenciales(email, password)
         ↓
Conexion.getConexion() → MySQL
         ↓
SELECT * FROM usuarios WHERE email=? AND contrasena=?
         ↓
¿Existe? → SÍ → Abrir Frm_MenuPrincipal
         → NO → Mostrar error
```

### 2. Guardar Cliente
```
Usuario llena formulario y hace clic en "Guardar"
         ↓
Frm_Clientes.guardarCliente()
         ↓
Validar campos requeridos
         ↓
Crear objeto Cliente con los datos
         ↓
ClienteDAO.guardar(cliente)
         ↓
Conexion.getConexion() → MySQL
         ↓
INSERT INTO clientes (identificador, nombres, dni, ruc, direccion, localidad) VALUES (?, ?, ?, ?, ?, ?)
         ↓
¿Éxito? → SÍ → Recargar tabla desde BD
         → NO → Mostrar error
```

### 3. Editar Cliente
```
Usuario selecciona fila de la tabla
         ↓
Frm_Clientes.cargarClienteSeleccionado()
         ↓
Campos se llenan con datos del cliente
         ↓
Usuario modifica datos y hace clic en "Editar"
         ↓
Frm_Clientes.editarCliente()
         ↓
ClienteDAO.actualizar(cliente, identificadorOriginal)
         ↓
Conexion.getConexion() → MySQL
         ↓
UPDATE clientes SET identificador=?, nombres=?, dni=?, ruc=?, direccion=?, localidad=? WHERE identificador=?
         ↓
¿Éxito? → SÍ → Recargar tabla desde BD
         → NO → Mostrar error
```

### 4. Eliminar Cliente
```
Usuario selecciona fila y hace clic en "Eliminar"
         ↓
Mostrar confirmación
         ↓
Usuario confirma
         ↓
Frm_Clientes.eliminarCliente()
         ↓
ClienteDAO.eliminar(identificador)
         ↓
Conexion.getConexion() → MySQL
         ↓
DELETE FROM clientes WHERE identificador=?
         ↓
¿Éxito? → SÍ → Recargar tabla desde BD
         → NO → Mostrar error
```

### 5. Cargar Clientes al Abrir Formulario
```
Usuario abre Frm_Clientes
         ↓
Constructor: cargarClientesDesdeDB()
         ↓
ClienteDAO.obtenerTodos()
         ↓
Conexion.getConexion() → MySQL
         ↓
SELECT * FROM clientes ORDER BY fecha_registro DESC
         ↓
Crear ArrayList<Cliente> con los resultados
         ↓
Llenar tabla con los datos
```

---

## 🗂️ ESTRUCTURA DE PAQUETES

```
com.empresa.sistema_de_almacen_inventario
│
├── Sistema_de_almacen_inventario.java (Main)
│
├── database/
│   ├── Conexion.java          (Gestión de conexión)
│   ├── ClienteDAO.java         (CRUD Clientes)
│   ├── ProductoDAO.java        (CRUD Productos)
│   └── UsuarioDAO.java         (Validación usuarios)
│
├── modelos/
│   ├── Cliente.java            (Clase modelo)
│   └── Producto.java           (Clase modelo)
│
└── vistas/
    ├── Frm_Login.java          (Pantalla de login)
    ├── Frm_MenuPrincipal.java  (Menú principal)
    ├── Frm_Clientes.java       (Gestión clientes)
    └── Frm_Productos.java      (Gestión productos)
```

---

## 🔐 PATRÓN DAO (Data Access Object)

### ¿Qué es DAO?
DAO es un patrón de diseño que separa la lógica de acceso a datos de la lógica de negocio.

### Ventajas:
- ✅ **Separación de responsabilidades**: Las vistas no conocen los detalles de la base de datos
- ✅ **Reutilización**: Los métodos DAO se pueden usar desde cualquier parte
- ✅ **Mantenibilidad**: Cambios en la BD solo afectan a las clases DAO
- ✅ **Testabilidad**: Fácil de probar cada componente por separado

### Métodos Implementados en cada DAO:

#### ClienteDAO
```java
+ guardar(Cliente cliente): boolean
+ actualizar(Cliente cliente, String identificadorOriginal): boolean
+ eliminar(String identificador): boolean
+ obtenerTodos(): ArrayList<Cliente>
+ buscarPorIdentificador(String identificador): Cliente
+ existe(String identificador): boolean
```

#### ProductoDAO
```java
+ guardar(Producto producto): boolean
+ actualizar(Producto producto, String noSerieOriginal): boolean
+ eliminar(String noSerie): boolean
+ obtenerTodos(): ArrayList<Producto>
+ buscarPorNoSerie(String noSerie): Producto
+ existe(String noSerie): boolean
```

#### UsuarioDAO
```java
+ validarCredenciales(String email, String contrasena): boolean
+ obtenerTipoUsuario(String email): String
+ existe(String email): boolean
```

---

## 🛡️ SEGURIDAD IMPLEMENTADA

### 1. PreparedStatement
```java
// ❌ MAL - Vulnerable a SQL Injection
String sql = "SELECT * FROM usuarios WHERE email='" + email + "'";

// ✅ BIEN - Protegido contra SQL Injection
String sql = "SELECT * FROM usuarios WHERE email=?";
PreparedStatement pst = conn.prepareStatement(sql);
pst.setString(1, email);
```

### 2. Try-with-resources
```java
// Cierra automáticamente la conexión, incluso si hay error
try (Connection conn = Conexion.getConexion();
     PreparedStatement pst = conn.prepareStatement(sql)) {
    // código
}
```

### 3. Manejo de Errores
```java
try {
    // operación de base de datos
} catch (SQLException e) {
    System.err.println("Error: " + e.getMessage());
    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    return false;
}
```

---

## 📊 RELACIÓN ENTRE COMPONENTES

```
┌──────────────┐
│  Frm_Login   │
└──────┬───────┘
       │ usa
       ↓
┌──────────────┐      ┌──────────────┐
│ UsuarioDAO   │─────→│  Conexion    │
└──────────────┘      └──────┬───────┘
                             │
┌──────────────┐             │
│Frm_Clientes  │             │
└──────┬───────┘             │
       │ usa                 │ conecta
       ↓                     ↓
┌──────────────┐      ┌──────────────┐
│ ClienteDAO   │─────→│    MySQL     │
└──────────────┘      │ inventario_db│
       ↑              └──────────────┘
       │ usa                 ↑
┌──────┴───────┐             │
│   Cliente    │             │
│   (Modelo)   │             │
└──────────────┘             │
                             │
┌──────────────┐             │
│Frm_Productos │             │
└──────┬───────┘             │
       │ usa                 │
       ↓                     │
┌──────────────┐             │
│ ProductoDAO  │─────────────┘
└──────────────┘
       ↑
       │ usa
┌──────┴───────┐
│   Producto   │
│   (Modelo)   │
└──────────────┘
```

---

## 🎯 PRINCIPIOS DE DISEÑO APLICADOS

### 1. Separación de Capas (Layered Architecture)
- **Presentación**: Vistas (JFrame)
- **Lógica**: Modelos y DAOs
- **Datos**: Base de datos MySQL

### 2. Single Responsibility Principle (SRP)
- Cada clase tiene una única responsabilidad
- `Conexion`: Solo gestiona la conexión
- `ClienteDAO`: Solo operaciones de clientes
- `Frm_Clientes`: Solo interfaz de clientes

### 3. DRY (Don't Repeat Yourself)
- Métodos reutilizables en DAOs
- Conexión centralizada en una clase

### 4. Encapsulación
- Atributos privados en modelos
- Getters y setters para acceso controlado

---

## 📈 ESCALABILIDAD

El sistema está diseñado para crecer fácilmente:

### Agregar nueva entidad (ej: Proveedores)
1. Crear tabla en MySQL
2. Crear clase `Proveedor.java` en `modelos/`
3. Crear clase `ProveedorDAO.java` en `database/`
4. Crear clase `Frm_Proveedores.java` en `vistas/`
5. Agregar botón en `Frm_MenuPrincipal.java`

### Agregar nueva funcionalidad
- Búsqueda: Agregar método `buscarPorNombre()` en DAO
- Reportes: Crear nueva clase `ReporteDAO.java`
- Exportar: Agregar método `exportarAExcel()` en DAO

---

## 🔧 TECNOLOGÍAS UTILIZADAS

- **Lenguaje**: Java 8+
- **GUI**: Java Swing
- **Base de Datos**: MySQL 8.0
- **Driver JDBC**: MySQL Connector/J 8.0.33
- **Build Tool**: Maven
- **IDE**: NetBeans

---

## 📝 CONVENCIONES DE CÓDIGO

### Nomenclatura
- **Clases**: PascalCase (ej: `ClienteDAO`)
- **Métodos**: camelCase (ej: `guardarCliente()`)
- **Variables**: camelCase (ej: `listaClientes`)
- **Constantes**: UPPER_SNAKE_CASE (ej: `URL`, `USUARIO`)

### Comentarios
```java
/**
 * Descripción del método
 * @param parametro Descripción del parámetro
 * @return Descripción del retorno
 */
```

---

¡Sistema completamente documentado y listo para usar! 🚀
