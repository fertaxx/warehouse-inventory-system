# 📊 Configuración de Base de Datos MySQL

## 🔧 Requisitos Previos

1. **MySQL Server** instalado (XAMPP, WAMP, o MySQL standalone)
2. **MySQL Workbench** o **phpMyAdmin** para ejecutar scripts

---

## 📝 Pasos para Configurar la Base de Datos

### Opción 1: Usando XAMPP (Recomendado)

1. **Iniciar XAMPP**
   - Abre el Panel de Control de XAMPP
   - Inicia **Apache** y **MySQL**

2. **Abrir phpMyAdmin**
   - Ve a: `http://localhost/phpmyadmin`
   - Usuario: `root`
   - Contraseña: (dejar en blanco)

3. **Ejecutar el Script**
   - Click en la pestaña **SQL**
   - Copia y pega el contenido del archivo `database_script.sql`
   - Click en **Continuar** o **Go**

4. **Verificar**
   - Deberías ver la base de datos `inventario_db` en el panel izquierdo
   - Con 3 tablas: `clientes`, `productos`, `usuarios`

---

### Opción 2: Usando MySQL Workbench

1. **Abrir MySQL Workbench**
2. **Conectar al servidor local**
   - Host: `localhost`
   - Port: `3306`
   - Usuario: `root`
   - Contraseña: (tu contraseña de MySQL)

3. **Ejecutar el Script**
   - File → Open SQL Script
   - Selecciona `database_script.sql`
   - Click en el icono del rayo ⚡ para ejecutar

4. **Verificar**
   - Actualiza la lista de bases de datos
   - Deberías ver `inventario_db`

---

## ⚙️ Configurar la Conexión en el Proyecto

Abre el archivo: `src/main/java/com/empresa/sistema_de_almacen_inventario/database/Conexion.java`

Modifica estas líneas según tu configuración:

```java
private static final String URL = "jdbc:mysql://localhost:3306/inventario_db";
private static final String USUARIO = "root";
private static final String PASSWORD = "";  // Pon tu contraseña aquí si tienes una
```

### Configuraciones Comunes:

**XAMPP (por defecto):**
```java
private static final String USUARIO = "root";
private static final String PASSWORD = "";
```

**MySQL Standalone:**
```java
private static final String USUARIO = "root";
private static final String PASSWORD = "tu_contraseña";
```

**Otro puerto:**
```java
private static final String URL = "jdbc:mysql://localhost:3307/inventario_db";
```

---

## 🧪 Probar la Conexión

1. **En NetBeans:**
   - Click derecho en el proyecto
   - **Clean and Build**
   - **Run**

2. **Al iniciar la aplicación:**
   - Si la conexión es exitosa, verás en la consola: `✅ Conexión exitosa a la base de datos`
   - Si hay error, aparecerá un mensaje indicando el problema

---

## 📊 Estructura de la Base de Datos

### Tabla: `clientes`
- `id` - ID autoincremental
- `identificador` - Código único del cliente
- `nombres` - Nombre completo
- `dni` - Documento de identidad
- `ruc` - RUC del cliente
- `direccion` - Dirección fiscal
- `localidad` - Ciudad/distrito
- `fecha_registro` - Fecha de registro automática

### Tabla: `productos`
- `id` - ID autoincremental
- `no_serie` - Número de serie único
- `nombre` - Nombre del producto
- `marca` - Marca del producto
- `descripcion` - Descripción detallada
- `precio` - Precio unitario
- `cantidad` - Cantidad en inventario
- `fecha_compra` - Fecha de compra
- `fecha_caducidad` - Fecha de vencimiento
- `stock` - Si está en stock (1=Sí, 0=No)
- `fecha_registro` - Fecha de registro automática

### Tabla: `usuarios`
- `id` - ID autoincremental
- `email` - Email/usuario para login
- `contrasena` - Contraseña
- `tipo_usuario` - Administrador o Usuario
- `activo` - Si el usuario está activo
- `fecha_registro` - Fecha de registro automática

---

## 🔐 Usuario por Defecto

**Email:** `admin`  
**Contraseña:** `admin`

---

## ❓ Solución de Problemas

### Error: "Driver de MySQL no encontrado"
- Verifica que el archivo `pom.xml` tenga la dependencia de MySQL
- En NetBeans: Click derecho en el proyecto → **Clean and Build**

### Error: "Access denied for user"
- Verifica el usuario y contraseña en `Conexion.java`
- Asegúrate de que MySQL esté ejecutándose

### Error: "Unknown database 'inventario_db'"
- Ejecuta el script `database_script.sql` primero
- Verifica que la base de datos se haya creado correctamente

### Error: "Communications link failure"
- Verifica que MySQL esté ejecutándose
- Verifica el puerto (por defecto 3306)
- En XAMPP: Asegúrate de que el servicio MySQL esté iniciado

---

## 📚 Próximos Pasos

Una vez configurada la base de datos, el sistema podrá:
- ✅ Guardar clientes permanentemente
- ✅ Guardar productos permanentemente
- ✅ Validar usuarios contra la base de datos
- ✅ Consultar y actualizar información en tiempo real

---

## 🆘 ¿Necesitas Ayuda?

Si tienes problemas con la configuración, verifica:
1. Que MySQL esté ejecutándose
2. Que la base de datos `inventario_db` exista
3. Que el usuario y contraseña sean correctos
4. Que el puerto sea el correcto (3306 por defecto)


---

## 🎉 INTEGRACIÓN COMPLETADA

**¡EL SISTEMA YA ESTÁ COMPLETAMENTE INTEGRADO CON LA BASE DE DATOS!**

### ✅ Archivos DAO Creados

Se han creado las siguientes clases para manejar las operaciones de base de datos:

1. **ClienteDAO.java** - Operaciones CRUD para clientes
   - `guardar()` - Inserta nuevo cliente
   - `actualizar()` - Modifica cliente existente
   - `eliminar()` - Borra cliente
   - `obtenerTodos()` - Carga todos los clientes
   - `buscarPorIdentificador()` - Busca cliente específico
   - `existe()` - Verifica si existe un cliente

2. **ProductoDAO.java** - Operaciones CRUD para productos
   - `guardar()` - Inserta nuevo producto
   - `actualizar()` - Modifica producto existente
   - `eliminar()` - Borra producto
   - `obtenerTodos()` - Carga todos los productos
   - `buscarPorNoSerie()` - Busca producto específico
   - `existe()` - Verifica si existe un producto

3. **UsuarioDAO.java** - Validación de usuarios
   - `validarCredenciales()` - Valida login
   - `obtenerTipoUsuario()` - Obtiene rol del usuario
   - `existe()` - Verifica si existe un usuario

### ✅ Formularios Integrados

Todos los formularios ya están conectados a la base de datos:

#### Frm_Login.java
- ✅ Valida usuario y contraseña contra la tabla `usuarios`
- ✅ Usuario por defecto: **admin** / **admin**
- ✅ Muestra mensajes de error si las credenciales son incorrectas

#### Frm_Clientes.java
- ✅ **Al abrir**: Carga automáticamente todos los clientes desde la BD
- ✅ **Botón "Guardar"**: Inserta nuevo cliente en la BD
- ✅ **Botón "Editar"**: Actualiza cliente seleccionado en la BD
- ✅ **Botón "Eliminar"**: Borra cliente seleccionado de la BD
- ✅ **Botón "Nuevo"**: Limpia campos para nuevo registro

#### Frm_Productos.java
- ✅ **Al abrir**: Carga automáticamente todos los productos desde la BD
- ✅ **Botón "Guardar"**: Inserta nuevo producto en la BD
- ✅ **Botón "Editar"**: Actualiza producto seleccionado en la BD
- ✅ **Botón "Eliminar"**: Borra producto seleccionado de la BD
- ✅ **Botón "Nuevo Producto"**: Limpia campos para nuevo registro

---

## 🧪 CÓMO PROBAR EL SISTEMA

### 1. Preparar la Base de Datos
```
1. Inicia MySQL en XAMPP
2. Abre phpMyAdmin (http://localhost/phpmyadmin)
3. Ejecuta el script database_script.sql
4. Verifica que se creó inventario_db con 3 tablas
```

### 2. Compilar el Proyecto
```
1. Abre el proyecto en NetBeans
2. Click derecho → Clean and Build
3. Espera a que Maven descargue las dependencias
```

### 3. Ejecutar y Probar
```
1. Click derecho → Run
2. Login: admin / admin
3. Prueba agregar, editar y eliminar clientes
4. Prueba agregar, editar y eliminar productos
5. Verifica en phpMyAdmin que los cambios se guardaron
```

---

## 📚 DOCUMENTACIÓN ADICIONAL

Para más información detallada, consulta:

- **GUIA_INTEGRACION_BASE_DE_DATOS.md** - Guía completa de uso y pruebas paso a paso
- **ARQUITECTURA_SISTEMA.md** - Diagrama de arquitectura, flujo de datos y patrones de diseño

---

## 🎯 CARACTERÍSTICAS IMPLEMENTADAS

✅ **Operaciones CRUD Completas**
- Create (Crear nuevos registros)
- Read (Leer/Cargar registros)
- Update (Actualizar registros)
- Delete (Eliminar registros)

✅ **Seguridad**
- PreparedStatement (previene SQL Injection)
- Try-with-resources (cierra conexiones automáticamente)
- Validación de credenciales contra BD

✅ **Experiencia de Usuario**
- Carga automática de datos al abrir formularios
- Recarga automática después de operaciones
- Mensajes informativos de éxito/error
- Confirmación antes de eliminar

✅ **Manejo de Errores**
- Captura de excepciones SQL
- Mensajes descriptivos al usuario
- Logs en consola para debugging

---

## 🚀 EL SISTEMA ESTÁ LISTO PARA USAR

**¡Todo está funcionando!** Solo necesitas:
1. ✅ MySQL ejecutándose
2. ✅ Base de datos creada con el script
3. ✅ Compilar y ejecutar el proyecto

**¡Disfruta tu sistema de inventario!** 🎉
