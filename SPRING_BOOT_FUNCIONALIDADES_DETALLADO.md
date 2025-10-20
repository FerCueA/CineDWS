# 📘 Spring Boot MVC - Funcionalidades Detalladas

## 🎯 ÍNDICE
1. [Controladores (@Controller)](#controladores-controller)
2. [Mapeos de URLs (@GetMapping y @PostMapping)](#mapeos-de-urls)
3. [Recibir datos (@RequestParam)](#recibir-datos-requestparam)
4. [Formularios completos (@ModelAttribute)](#formularios-completos-modelattribute)
5. [Enviar datos a vistas (Model)](#enviar-datos-a-vistas-model)
6. [Mantener datos entre páginas (HttpSession)](#mantener-datos-entre-páginas-httpsession)
7. [Redirecciones (redirect:)](#redirecciones-redirect)
8. [Validaciones](#validaciones)

---

## 🎛️ CONTROLADORES (@Controller)

### ¿Qué es un Controlador?

Un **controlador** es una clase Java que actúa como **intermediario** entre las peticiones HTTP del navegador y las respuestas que devuelve la aplicación. Es el **"cerebro"** que decide qué hacer cuando alguien visita una URL específica.

### Sintaxis básica:

```java
@Controller  // ← OBLIGATORIO: Marca la clase como controlador
public class MiControlador {
    
    // Métodos que manejan URLs específicas
}
```

### Explicación detallada:

- **`@Controller`**: Es una **anotación** (annotation) que le dice a Spring Boot:
  - *"Esta clase maneja peticiones web"*
  - *"Busca dentro de esta clase métodos que respondan a URLs"*
  - *"Registra esta clase automáticamente en el sistema"*

- **Spring Boot automáticamente**:
  1. **Encuentra** todas las clases marcadas con `@Controller`
  2. **Las registra** en su sistema interno
  3. **Las usa** para responder a peticiones web

### Ejemplo completo:

```java
package es.miapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("mensaje", "¡Bienvenido!");
        return "index";  // ← Archivo: templates/index.html
    }
}
```

### ¿Cómo funciona internamente?

1. **Aplicación arranca** → Spring escanea todas las clases
2. **Encuentra `@Controller`** → Registra la clase
3. **Usuario visita `/`** → Spring busca método con `@GetMapping("/")`
4. **Encuentra el método** → Lo ejecuta
5. **Método devuelve "index"** → Spring busca `templates/index.html`
6. **Renderiza HTML** → Envía respuesta al navegador

---

## 🗺️ MAPEOS DE URLs (@GetMapping y @PostMapping)

### ¿Qué son los Mapeos?

Los **mapeos** son anotaciones que **conectan URLs específicas con métodos Java**. Le dicen a Spring: *"Cuando alguien visite esta URL, ejecuta este método"*.

### @GetMapping - Para MOSTRAR páginas

#### Sintaxis:
```java
@GetMapping("/ruta")
public String nombreMetodo(parámetros...) {
    // Lógica aquí
    return "nombre-vista";
}
```

#### Explicación detallada:
- **`@GetMapping("/ruta")`**: 
  - Escucha peticiones **HTTP GET** a la URL `/ruta`
  - GET se usa para **obtener/mostrar** información
  - Es lo que pasa cuando escribes una URL en el navegador
  - Es lo que pasa cuando haces clic en un enlace `<a href="...">`

#### Ejemplo:
```java
@GetMapping("/productos")
public String mostrarProductos(Model model) {
    // Este método se ejecuta cuando alguien visita: http://localhost:8080/productos
    
    List<String> productos = Arrays.asList("Laptop", "Mouse", "Teclado");
    model.addAttribute("listaProductos", productos);
    
    return "productos";  // ← Muestra templates/productos.html
}
```

#### ¿Cuándo usar @GetMapping?
- ✅ Mostrar páginas web
- ✅ Mostrar listas de datos
- ✅ Navegar entre páginas
- ✅ Búsquedas y filtros
- ✅ Enlaces normales

### @PostMapping - Para PROCESAR formularios

#### Sintaxis:
```java
@PostMapping("/ruta")
public String nombreMetodo(parámetros...) {
    // Procesar datos recibidos
    return "vista-resultado";
}
```

#### Explicación detallada:
- **`@PostMapping("/ruta")`**:
  - Escucha peticiones **HTTP POST** a la URL `/ruta`
  - POST se usa para **enviar/procesar** información
  - Es lo que pasa cuando envías un formulario con `method="POST"`
  - Los datos viajan "ocultos" en el cuerpo de la petición

#### Ejemplo:
```java
@PostMapping("/crear-producto")
public String crearProducto(@ModelAttribute Producto producto) {
    // Este método se ejecuta cuando un formulario con action="/crear-producto" es enviado
    
    System.out.println("Producto recibido: " + producto.getNombre());
    // Guardar en base de datos...
    
    return "producto-creado";  // ← Muestra página de confirmación
}
```

#### ¿Cuándo usar @PostMapping?
- ✅ Procesar formularios
- ✅ Crear nuevos registros
- ✅ Modificar datos existentes
- ✅ Operaciones que cambian información

### Diferencias clave GET vs POST:

| Aspecto | GET | POST |
|---------|-----|------|
| **Propósito** | Obtener/Mostrar | Enviar/Procesar |
| **Datos** | En la URL (visibles) | En el cuerpo (ocultos) |
| **Límite** | ~2000 caracteres | Sin límite práctico |
| **Cacheable** | Sí | No |
| **Historial** | Se guarda en historial | No se guarda |
| **Botón atrás** | Funciona normal | Puede dar problemas |

---

## 📥 RECIBIR DATOS (@RequestParam)

### ¿Qué es @RequestParam?

`@RequestParam` es una anotación que **extrae datos específicos** que vienen en la URL de la petición. Es como decirle a Spring: *"Dame el valor del parámetro X que viene en la URL"*.

### Sintaxis básica:
```java
@GetMapping("/ruta")
public String metodo(@RequestParam String nombreParametro) {
    // nombreParametro contiene el valor del parámetro de la URL
    return "vista";
}
```

### Ejemplo simple:
```java
@GetMapping("/buscar")
public String buscar(@RequestParam String termino, Model model) {
    // URL: http://localhost:8080/buscar?termino=laptop
    // termino = "laptop"
    
    System.out.println("Buscando: " + termino);
    model.addAttribute("resultado", "Encontrado: " + termino);
    return "resultados";
}
```

### ¿Cómo funciona internamente?

1. **Usuario visita**: `http://localhost:8080/buscar?termino=laptop`
2. **Spring analiza la URL**: 
   - Ruta: `/buscar`
   - Parámetros: `termino=laptop`
3. **Spring busca**: Método con `@GetMapping("/buscar")`
4. **Spring encuentra**: `@RequestParam String termino`
5. **Spring automáticamente**: `String termino = "laptop"`
6. **Ejecuta método**: Con `termino` ya listo para usar

### Parámetros opcionales:

```java
@GetMapping("/pagina")
public String pagina(@RequestParam(value = "numero", required = false, defaultValue = "1") String numero) {
    // URL: /pagina?numero=5  → numero = "5"
    // URL: /pagina           → numero = "1" (valor por defecto)
    // URL: /pagina?numero=   → numero = "1" (valor por defecto)
    
    return "pagina";
}
```

#### Explicación de parámetros:
- **`value = "numero"`**: El nombre del parámetro en la URL
- **`required = false`**: No es obligatorio (por defecto es `true`)
- **`defaultValue = "1"`**: Valor si no viene o está vacío

### Múltiples parámetros:

```java
@GetMapping("/filtrar")
public String filtrar(@RequestParam String categoria, 
                     @RequestParam String precio,
                     @RequestParam(required = false) String color,
                     Model model) {
    // URL: /filtrar?categoria=electronica&precio=100&color=rojo
    // categoria = "electronica"
    // precio = "100" 
    // color = "rojo"
    
    System.out.println("Filtrando por:");
    System.out.println("- Categoría: " + categoria);
    System.out.println("- Precio: " + precio);
    System.out.println("- Color: " + color);
    
    return "productos-filtrados";
}
```

### Tipos de datos automáticos:

Spring Boot convierte automáticamente los tipos:

```java
@GetMapping("/producto")
public String producto(@RequestParam String nombre,          // String
                      @RequestParam int precio,             // int
                      @RequestParam boolean disponible,     // boolean
                      @RequestParam double descuento) {     // double
    // URL: /producto?nombre=Laptop&precio=500&disponible=true&descuento=0.15
    // nombre = "Laptop"
    // precio = 500
    // disponible = true
    // descuento = 0.15
    
    return "detalle-producto";
}
```

### ¿Cuándo usar @RequestParam?
- ✅ Filtros de búsqueda
- ✅ Paginación (página, tamaño)
- ✅ IDs simples
- ✅ Parámetros de configuración
- ✅ Enlaces con datos simples

---

## 📝 FORMULARIOS COMPLETOS (@ModelAttribute)

### ¿Qué es @ModelAttribute?

`@ModelAttribute` es la forma **más potente** de recibir datos en Spring Boot. Automáticamente **convierte todos los campos de un formulario HTML en un objeto Java**. Es como decirle a Spring: *"Toma todos estos datos del formulario y crea un objeto completo"*.

### ¿Por qué usar @ModelAttribute en lugar de @RequestParam?

#### Con @RequestParam (tedioso para formularios grandes):
```java
@PostMapping("/registro")
public String registro(@RequestParam String nombre,
                      @RequestParam String apellidos,
                      @RequestParam String email,
                      @RequestParam String telefono,
                      @RequestParam String direccion,
                      @RequestParam String ciudad,
                      @RequestParam String codigoPostal,
                      @RequestParam String pais) {
    // ¡8 parámetros individuales! 😱
}
```

#### Con @ModelAttribute (elegante):
```java
@PostMapping("/registro")
public String registro(@ModelAttribute Usuario usuario) {
    // ¡1 objeto con todo organizado! 😊
    String nombre = usuario.getNombre();
    String email = usuario.getEmail();
    // ...
}
```

### Proceso completo paso a paso:

#### 1. Crear la clase de datos (DTO/Model):

```java
// models/Usuario.java
public class Usuario {
    // Propiedades (DEBEN coincidir con los "name" del HTML)
    private String nombre;      // ← Coincide con name="nombre"
    private String apellidos;   // ← Coincide con name="apellidos"
    private String email;       // ← Coincide con name="email"
    private String telefono;    // ← Coincide con name="telefono"
    private int edad;          // ← Coincide con name="edad"
    
    // Constructor vacío (OBLIGATORIO para Spring)
    public Usuario() {
        // Spring necesita poder hacer: new Usuario()
    }
    
    // Getters (OBLIGATORIOS - Spring los usa para leer datos)
    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public int getEdad() { return edad; }
    
    // Setters (OBLIGATORIOS - Spring los usa para escribir datos)
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setEdad(int edad) { this.edad = edad; }
}
```

#### 2. Crear el formulario HTML:

```html
<!-- templates/formulario-usuario.html -->
<form method="POST" action="/guardar-usuario">
    <!-- Los "name" deben coincidir EXACTAMENTE con las propiedades Java -->
    <input type="text" name="nombre" placeholder="Nombre" required />           <!-- ← name="nombre" → setNombre() -->
    <input type="text" name="apellidos" placeholder="Apellidos" required />     <!-- ← name="apellidos" → setApellidos() -->
    <input type="email" name="email" placeholder="Email" required />            <!-- ← name="email" → setEmail() -->
    <input type="tel" name="telefono" placeholder="Teléfono" />                 <!-- ← name="telefono" → setTelefono() -->
    <input type="number" name="edad" placeholder="Edad" min="18" required />    <!-- ← name="edad" → setEdad() -->
    
    <input type="submit" value="Registrar Usuario" />
</form>
```

#### 3. Crear el controlador que recibe el formulario:

```java
@PostMapping("/guardar-usuario")
public String guardarUsuario(@ModelAttribute Usuario usuario, Model model) {
    // Spring hace AUTOMÁTICAMENTE esto por detrás:
    // 1. Usuario usuario = new Usuario();
    // 2. usuario.setNombre("Juan");         ← del campo name="nombre"
    // 3. usuario.setApellidos("Pérez");     ← del campo name="apellidos"
    // 4. usuario.setEmail("juan@...");      ← del campo name="email"
    // 5. usuario.setTelefono("123456");     ← del campo name="telefono"
    // 6. usuario.setEdad(25);               ← del campo name="edad"
    
    // Ahora puedes usar el objeto completo:
    System.out.println("Usuario registrado:");
    System.out.println("- Nombre completo: " + usuario.getNombre() + " " + usuario.getApellidos());
    System.out.println("- Email: " + usuario.getEmail());
    System.out.println("- Teléfono: " + usuario.getTelefono());
    System.out.println("- Edad: " + usuario.getEdad());
    
    // Guardar en base de datos, enviar email, etc.
    
    model.addAttribute("usuarioRegistrado", usuario);
    return "registro-exitoso";
}
```

### ¿Cómo funciona la "magia" internamente?

Cuando el usuario envía el formulario:

1. **Navegador envía**: 
   ```
   POST /guardar-usuario
   Content-Type: application/x-www-form-urlencoded
   
   nombre=Juan&apellidos=Pérez&email=juan@email.com&telefono=123456&edad=25
   ```

2. **Spring recibe los datos** y ve `@ModelAttribute Usuario usuario`

3. **Spring automáticamente**:
   ```java
   // Paso 1: Crear objeto vacío
   Usuario usuario = new Usuario();
   
   // Paso 2: Por cada campo del formulario, buscar setter correspondiente
   if (hay campo "nombre") usuario.setNombre(valor_nombre);
   if (hay campo "apellidos") usuario.setApellidos(valor_apellidos);
   if (hay campo "email") usuario.setEmail(valor_email);
   if (hay campo "telefono") usuario.setTelefono(valor_telefono);
   if (hay campo "edad") usuario.setEdad(valor_edad);
   ```

4. **Spring pasa el objeto lleno** al método del controlador

### Conversiones automáticas de tipos:

Spring automáticamente convierte strings a otros tipos:

```java
public class Producto {
    private String nombre;        // String (texto)
    private double precio;        // double (decimal)
    private int stock;           // int (entero)
    private boolean disponible;   // boolean (true/false)
    private LocalDate fecha;     // LocalDate (fecha)
    
    // getters y setters...
}
```

```html
<form method="POST" action="/producto">
    <input type="text" name="nombre" value="Laptop" />           <!-- → String -->
    <input type="number" name="precio" step="0.01" value="799.99" /> <!-- → double -->
    <input type="number" name="stock" value="15" />              <!-- → int -->
    <input type="checkbox" name="disponible" value="true" />     <!-- → boolean -->
    <input type="date" name="fecha" value="2025-10-20" />       <!-- → LocalDate -->
</form>
```

### Validación integrada con @ModelAttribute:

```java
@PostMapping("/producto")
public String crearProducto(@ModelAttribute Producto producto, Model model) {
    
    // Validar datos recibidos
    List<String> errores = new ArrayList<>();
    
    if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
        errores.add("El nombre es obligatorio");
    }
    
    if (producto.getPrecio() <= 0) {
        errores.add("El precio debe ser mayor a 0");
    }
    
    if (producto.getStock() < 0) {
        errores.add("El stock no puede ser negativo");
    }
    
    // Si hay errores, volver al formulario
    if (!errores.isEmpty()) {
        model.addAttribute("errores", errores);
        model.addAttribute("producto", producto);  // Mantener datos escritos
        return "formulario-producto";  // Volver al formulario con errores
    }
    
    // Si no hay errores, procesar
    // Guardar producto...
    return "producto-creado";
}
```

---

## 🎒 ENVIAR DATOS A VISTAS (Model)

### ¿Qué es el Model?

El **Model** es como una **"mochila"** o **"contenedor"** donde guardas todos los datos que quieres enviar desde el controlador a la vista HTML. Es el mecanismo que Spring Boot usa para **pasar información** del backend (Java) al frontend (HTML).

### Sintaxis básica:

```java
@GetMapping("/ruta")
public String metodo(Model model) {
    model.addAttribute("clave", valor);  // ← Agregar datos al "contenedor"
    return "vista";  // ← La vista puede usar los datos con ${clave}
}
```

### ¿Cómo funciona internamente?

1. **Spring crea un Model vacío** automáticamente
2. **Tu método recibe** este Model como parámetro
3. **Tú añades datos** con `addAttribute()`
4. **Tu método devuelve** el nombre de la vista
5. **Spring toma el Model lleno** y lo envía a la vista
6. **La vista HTML** puede usar los datos con Thymeleaf

### Ejemplo básico:

```java
@GetMapping("/perfil")
public String perfil(Model model) {
    // Agregar datos simples
    model.addAttribute("nombreUsuario", "Juan Pérez");
    model.addAttribute("edad", 28);
    model.addAttribute("activo", true);
    
    return "perfil";  // ← templates/perfil.html puede usar: ${nombreUsuario}, ${edad}, ${activo}
}
```

En el HTML:
```html
<!-- templates/perfil.html -->
<h1>Perfil de <span th:text="${nombreUsuario}">Usuario</span></h1>
<p>Edad: <span th:text="${edad}">0</span> años</p>
<p th:if="${activo}">✅ Usuario activo</p>
```

### Tipos de datos que puedes enviar:

#### Datos simples:
```java
@GetMapping("/datos")
public String datos(Model model) {
    model.addAttribute("texto", "Hola mundo");
    model.addAttribute("numero", 42);
    model.addAttribute("decimal", 19.99);
    model.addAttribute("booleano", true);
    model.addAttribute("fecha", LocalDate.now());
    
    return "datos";
}
```

#### Listas y colecciones:
```java
@GetMapping("/productos")
public String productos(Model model) {
    List<String> productos = Arrays.asList("Laptop", "Mouse", "Teclado", "Monitor");
    model.addAttribute("listaProductos", productos);
    
    Map<String, Integer> precios = new HashMap<>();
    precios.put("Laptop", 799);
    precios.put("Mouse", 25);
    precios.put("Teclado", 60);
    model.addAttribute("precios", precios);
    
    return "productos";
}
```

En el HTML:
```html
<ul>
    <li th:each="producto : ${listaProductos}">
        <span th:text="${producto}">Producto</span> - 
        <span th:text="${precios[producto]}">0</span>€
    </li>
</ul>
```

#### Objetos complejos:
```java
@GetMapping("/usuario")
public String usuario(Model model) {
    Usuario usuario = new Usuario();
    usuario.setNombre("Ana García");
    usuario.setEmail("ana@email.com");
    usuario.setEdad(30);
    
    model.addAttribute("datosUsuario", usuario);
    return "usuario";
}
```

En el HTML:
```html
<h2 th:text="${datosUsuario.nombre}">Nombre</h2>
<p>Email: <span th:text="${datosUsuario.email}">email</span></p>
<p>Edad: <span th:text="${datosUsuario.edad}">0</span> años</p>
```

### Model vs @ModelAttribute - Diferencia importante:

#### Model = Para ENVIAR datos (Java → HTML):
```java
@GetMapping("/mostrar")
public String mostrar(Model model) {
    model.addAttribute("mensaje", "Datos para mostrar");  // ← Envío datos
    return "vista";
}
```

#### @ModelAttribute = Para RECIBIR datos (HTML → Java):
```java
@PostMapping("/procesar")
public String procesar(@ModelAttribute FormularioData datos) {  // ← Recibo datos
    System.out.println("Datos recibidos: " + datos.getNombre());
    return "resultado";
}
```

### Combinar ambos (patrón común):

```java
@PostMapping("/procesar-y-mostrar")
public String procesarYMostrar(@ModelAttribute FormularioData datosRecibidos, Model model) {
    // 1. RECIBIR datos del formulario
    System.out.println("Procesando: " + datosRecibidos.getNombre());
    
    // 2. Procesar datos (validar, guardar, calcular, etc.)
    String resultado = "Procesado: " + datosRecibidos.getNombre();
    
    // 3. ENVIAR datos a la vista
    model.addAttribute("mensajeResultado", resultado);
    model.addAttribute("datosOriginales", datosRecibidos);
    
    return "resultado";
}
```

---

## 🔄 MANTENER DATOS ENTRE PÁGINAS (HttpSession)

### ¿Qué es HttpSession?

**HttpSession** es un mecanismo que permite **mantener datos específicos de un usuario** mientras navega por diferentes páginas de tu aplicación. Es como darle a cada usuario una **"caja personal"** donde puedes guardar información que se mantiene durante toda su visita.

### ¿Por qué necesitas sesión?

Sin sesión:
```
Usuario visita /pagina1 → Datos: [nombre: "Juan"]
Usuario va a /pagina2    → Datos: [] (se perdieron)
Usuario va a /pagina3    → Datos: [] (no sabe quién es)
```

Con sesión:
```
Usuario visita /pagina1 → Guarda en sesión: [nombre: "Juan"]
Usuario va a /pagina2   → Lee de sesión: [nombre: "Juan"] ✅
Usuario va a /pagina3   → Lee de sesión: [nombre: "Juan"] ✅
```

### Sintaxis básica:

```java
import jakarta.servlet.http.HttpSession;

@GetMapping("/ruta")
public String metodo(HttpSession session) {
    // Guardar datos en sesión
    session.setAttribute("clave", valor);
    
    // Leer datos de sesión
    TipoDato dato = (TipoDato) session.getAttribute("clave");
    
    return "vista";
}
```

### Ejemplo práctico - Sistema de login:

#### 1. Mostrar formulario de login:
```java
@GetMapping("/login")
public String mostrarLogin() {
    return "login";  // ← templates/login.html
}
```

#### 2. Procesar login y guardar en sesión:
```java
@PostMapping("/login")
public String procesarLogin(@RequestParam String usuario, 
                           @RequestParam String password,
                           HttpSession session,
                           Model model) {
    
    // Validar credenciales (simplificado)
    if ("admin".equals(usuario) && "123".equals(password)) {
        // Login exitoso - GUARDAR en sesión
        session.setAttribute("usuarioLogueado", usuario);
        session.setAttribute("tiempoLogin", LocalDateTime.now());
        
        return "redirect:/dashboard";  // ← Redirigir al dashboard
    } else {
        // Login fallido
        model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "login";  // ← Volver al formulario con error
    }
}
```

#### 3. Dashboard que requiere estar logueado:
```java
@GetMapping("/dashboard")
public String dashboard(HttpSession session, Model model) {
    // LEER datos de sesión
    String usuario = (String) session.getAttribute("usuarioLogueado");
    LocalDateTime tiempoLogin = (LocalDateTime) session.getAttribute("tiempoLogin");
    
    // Verificar si está logueado
    if (usuario == null) {
        return "redirect:/login";  // ← No logueado, volver a login
    }
    
    // Está logueado - mostrar dashboard
    model.addAttribute("nombreUsuario", usuario);
    model.addAttribute("horaLogin", tiempoLogin);
    
    return "dashboard";
}
```

#### 4. Logout - limpiar sesión:
```java
@GetMapping("/logout")
public String logout(HttpSession session) {
    session.invalidate();  // ← Elimina TODA la sesión
    return "redirect:/";   // ← Volver a la página principal
}
```

### Ejemplo práctico - Carrito de compras:

#### 1. Añadir producto al carrito:
```java
@PostMapping("/añadir-carrito")
public String añadirCarrito(@RequestParam String producto,
                           @RequestParam int cantidad,
                           HttpSession session) {
    
    // Obtener carrito actual de la sesión (o crear uno nuevo)
    List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
    if (carrito == null) {
        carrito = new ArrayList<>();
    }
    
    // Añadir producto
    ItemCarrito item = new ItemCarrito(producto, cantidad);
    carrito.add(item);
    
    // GUARDAR carrito actualizado en sesión
    session.setAttribute("carrito", carrito);
    
    return "redirect:/productos";
}
```

#### 2. Ver carrito:
```java
@GetMapping("/carrito")
public String verCarrito(HttpSession session, Model model) {
    // LEER carrito de sesión
    List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
    
    if (carrito == null || carrito.isEmpty()) {
        model.addAttribute("carritoVacio", true);
    } else {
        model.addAttribute("itemsCarrito", carrito);
        
        // Calcular total
        double total = carrito.stream()
                             .mapToDouble(item -> item.getPrecio() * item.getCantidad())
                             .sum();
        model.addAttribute("totalCarrito", total);
    }
    
    return "carrito";
}
```

### Operaciones comunes con sesión:

#### Guardar datos:
```java
session.setAttribute("clave", valor);

// Ejemplos:
session.setAttribute("usuario", "juan123");
session.setAttribute("carrito", listaProductos);
session.setAttribute("configuracion", objetoConfig);
```

#### Leer datos:
```java
TipoDato valor = (TipoDato) session.getAttribute("clave");

// Ejemplos:
String usuario = (String) session.getAttribute("usuario");
List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
Config config = (Config) session.getAttribute("configuracion");
```

#### Verificar si existe un dato:
```java
if (session.getAttribute("usuario") != null) {
    // El usuario está logueado
} else {
    // No está logueado
}
```

#### Eliminar dato específico:
```java
session.removeAttribute("clave");

// Ejemplo:
session.removeAttribute("carrito");  // ← Solo elimina el carrito
```

#### Eliminar toda la sesión:
```java
session.invalidate();  // ← Elimina TODO de la sesión
```

### ¿Cuándo usar HttpSession?

✅ **Usar sesión para:**
- Login/autenticación de usuarios
- Carrito de compras
- Configuraciones temporales del usuario
- Wizards/formularios multi-paso
- Datos que necesitas entre múltiples páginas

❌ **NO usar sesión para:**
- Datos que pueden ir por URL (usar @RequestParam)
- Datos que solo necesitas en una página (usar Model)
- Información que debe persistir para siempre (usar base de datos)

---

## 🔀 REDIRECCIONES (redirect:)

### ¿Qué son las redirecciones?

Una **redirección** es cuando le dices al navegador: *"No muestres esta página, ve a otra URL diferente"*. Es como poner un cartel que dice *"La oficina se mudó a la dirección X"*.

### Sintaxis básica:

```java
return "redirect:/nueva-url";  // ← Redirige a /nueva-url
```

### Diferencia: Vista normal vs Redirección

#### Vista normal:
```java
@PostMapping("/procesar")
public String procesar() {
    // Procesar datos...
    return "resultado";  // ← Muestra templates/resultado.html en la URL /procesar
}
```
**Resultado**: URL sigue siendo `/procesar` pero muestra el contenido de `resultado.html`

#### Redirección:
```java
@PostMapping("/procesar")
public String procesar() {
    // Procesar datos...
    return "redirect:/exito";  // ← El navegador va a la URL /exito
}
```
**Resultado**: URL cambia a `/exito` y ejecuta el método que maneja esa URL

### ¿Por qué usar redirecciones?

#### Problema sin redirección:
```java
@PostMapping("/crear-producto")
public String crearProducto(@ModelAttribute Producto producto) {
    // Guardar producto en BD...
    return "producto-creado";  // ← URL sigue siendo /crear-producto
}
```

**Problema**: Si el usuario recarga la página (F5), **vuelve a enviar el formulario** y crea el producto duplicado ❌

#### Solución con redirección:
```java
@PostMapping("/crear-producto")
public String crearProducto(@ModelAttribute Producto producto) {
    // Guardar producto en BD...
    return "redirect:/productos";  // ← Navegador va a /productos
}

@GetMapping("/productos")
public String productos(Model model) {
    // Cargar lista actualizada...
    return "lista-productos";
}
```

**Solución**: Si el usuario recarga, solo recarga la lista de productos ✅

### Patrón POST-Redirect-GET:

Es una **buena práctica** seguir este patrón:

1. **POST**: Procesar formulario (crear/modificar datos)
2. **Redirect**: Redirigir a una página GET
3. **GET**: Mostrar resultado

```java
// 1. POST - Procesar
@PostMapping("/registrar")
public String registrar(@ModelAttribute Usuario usuario) {
    guardarUsuario(usuario);  // ← Modificar datos
    return "redirect:/usuario/" + usuario.getId();  // ← Redirigir
}

// 3. GET - Mostrar
@GetMapping("/usuario/{id}")
public String mostrarUsuario(@PathVariable String id, Model model) {
    Usuario usuario = buscarUsuario(id);
    model.addAttribute("usuario", usuario);
    return "perfil-usuario";  // ← Mostrar resultado
}
```

### Redirecciones con parámetros:

#### Pasar parámetros en la URL:
```java
@PostMapping("/buscar")
public String buscar(@RequestParam String termino) {
    return "redirect:/resultados?q=" + termino;
}
```

#### Usar RedirectAttributes (mejor opción):
```java
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@PostMapping("/crear-usuario")
public String crearUsuario(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) {
    guardarUsuario(usuario);
    
    // Añadir mensaje que aparecerá en la página destino
    redirectAttributes.addFlashAttribute("mensaje", "Usuario creado exitosamente");
    redirectAttributes.addFlashAttribute("tipoMensaje", "exito");
    
    return "redirect:/usuarios";
}

@GetMapping("/usuarios")
public String usuarios(Model model) {
    // Los datos de redirectAttributes aparecen automáticamente en el modelo
    // En el HTML puedes usar: ${mensaje} y ${tipoMensaje}
    
    List<Usuario> usuarios = obtenerUsuarios();
    model.addAttribute("usuarios", usuarios);
    return "lista-usuarios";
}
```

### Tipos de redirecciones:

#### Redirección interna (misma aplicación):
```java
return "redirect:/otra-pagina";           // ← A /otra-pagina de tu app
return "redirect:/usuarios/123";          // ← Con parámetros
return "redirect:/admin/dashboard";       // ← A otra sección
```

#### Redirección externa:
```java
return "redirect:https://www.google.com"; // ← A otro sitio web
return "redirect:https://mi-otro-sitio.com/api/callback";
```

### Casos comunes de uso:

#### Después de login exitoso:
```java
@PostMapping("/login")
public String login(@RequestParam String usuario, HttpSession session) {
    if (validarCredenciales(usuario)) {
        session.setAttribute("usuarioLogueado", usuario);
        return "redirect:/dashboard";  // ← Ir al dashboard
    } else {
        return "login";  // ← Volver al formulario de login
    }
}
```

#### Después de logout:
```java
@GetMapping("/logout")
public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";  // ← Volver a la página principal
}
```

#### Después de operaciones CRUD:
```java
@PostMapping("/productos")
public String crear(@ModelAttribute Producto producto) {
    guardar(producto);
    return "redirect:/productos";  // ← Ver lista actualizada
}

@PostMapping("/productos/{id}/eliminar")
public String eliminar(@PathVariable String id) {
    eliminarProducto(id);
    return "redirect:/productos";  // ← Ver lista actualizada
}
```

---

## ✅ VALIDACIONES

### ¿Qué son las validaciones?

Las **validaciones** son verificaciones que haces en el servidor para asegurarte de que los datos que envía el usuario son **correctos, completos y seguros** antes de procesarlos.

### ¿Por qué validar en el servidor?

#### Validaciones del navegador (NO son suficientes):
```html
<input type="email" required />  <!-- ← Se puede desactivar con JavaScript -->
```

#### Validaciones del servidor (SEGURAS):
```java
if (email == null || !email.contains("@")) {
    // ← Siempre se ejecuta, el usuario no puede evitarla
}
```

### Validación básica manual:

```java
@PostMapping("/registro")
public String registro(@ModelAttribute Usuario usuario, Model model) {
    
    // Lista para recopilar errores
    List<String> errores = new ArrayList<>();
    
    // Validar cada campo
    if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
        errores.add("El nombre es obligatorio");
    }
    
    if (usuario.getNombre() != null && usuario.getNombre().length() < 2) {
        errores.add("El nombre debe tener al menos 2 caracteres");
    }
    
    if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
        errores.add("El email debe ser válido");
    }
    
    if (usuario.getEdad() < 18) {
        errores.add("Debes ser mayor de edad");
    }
    
    if (usuario.getPassword() == null || usuario.getPassword().length() < 6) {
        errores.add("La contraseña debe tener al menos 6 caracteres");
    }
    
    // Si hay errores, volver al formulario
    if (!errores.isEmpty()) {
        model.addAttribute("errores", errores);
        model.addAttribute("usuario", usuario);  // ← Mantener datos escritos
        return "formulario-registro";  // ← Volver al formulario
    }
    
    // Si no hay errores, procesar
    guardarUsuario(usuario);
    return "redirect:/login";
}
```

### Mostrar errores en HTML:

```html
<!-- templates/formulario-registro.html -->
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Registro</title>
    <style>
        .errores { background: #ffe6e6; border: 1px solid #ff6666; padding: 10px; margin: 10px 0; }
        .error { color: red; font-size: 0.9em; }
        .campo-error { border: 2px solid red; }
    </style>
</head>
<body>
    <!-- Mostrar lista de errores -->
    <div th:if="${errores}" class="errores">
        <h4>Por favor, corrige los siguientes errores:</h4>
        <ul>
            <li th:each="error : ${errores}" th:text="${error}" class="error"></li>
        </ul>
    </div>
    
    <!-- Formulario con datos mantenidos -->
    <form method="POST" action="/registro">
        <div>
            <label>Nombre:</label>
            <input type="text" name="nombre" th:value="${usuario?.nombre}" 
                   th:class="${#lists.contains(errores, 'El nombre es obligatorio') ? 'campo-error' : ''}" />
        </div>
        
        <div>
            <label>Email:</label>
            <input type="email" name="email" th:value="${usuario?.email}"
                   th:class="${#strings.contains(errores.toString(), 'email') ? 'campo-error' : ''}" />
        </div>
        
        <div>
            <label>Edad:</label>
            <input type="number" name="edad" th:value="${usuario?.edad}" />
        </div>
        
        <div>
            <label>Contraseña:</label>
            <input type="password" name="password" />
        </div>
        
        <input type="submit" value="Registrar" />
    </form>
</body>
</html>
```

### Validación con métodos auxiliares:

```java
@PostMapping("/producto")
public String crearProducto(@ModelAttribute Producto producto, Model model) {
    
    List<String> errores = validarProducto(producto);
    
    if (!errores.isEmpty()) {
        model.addAttribute("errores", errores);
        model.addAttribute("producto", producto);
        return "formulario-producto";
    }
    
    guardarProducto(producto);
    return "redirect:/productos";
}

// Método auxiliar para validaciones
private List<String> validarProducto(Producto producto) {
    List<String> errores = new ArrayList<>();
    
    // Validar nombre
    if (esVacio(producto.getNombre())) {
        errores.add("El nombre del producto es obligatorio");
    } else if (producto.getNombre().length() < 3) {
        errores.add("El nombre debe tener al menos 3 caracteres");
    }
    
    // Validar precio
    if (producto.getPrecio() == null || producto.getPrecio() <= 0) {
        errores.add("El precio debe ser mayor a 0");
    }
    
    // Validar categoría
    if (esVacio(producto.getCategoria())) {
        errores.add("La categoría es obligatoria");
    }
    
    // Validar stock
    if (producto.getStock() < 0) {
        errores.add("El stock no puede ser negativo");
    }
    
    return errores;
}

// Método auxiliar para verificar strings vacíos
private boolean esVacio(String texto) {
    return texto == null || texto.trim().isEmpty();
}
```

### Validación con diferentes tipos de retorno:

#### Volver al mismo formulario con errores:
```java
@PostMapping("/editar-perfil")
public String editarPerfil(@ModelAttribute Usuario usuario, Model model) {
    List<String> errores = validarUsuario(usuario);
    
    if (!errores.isEmpty()) {
        model.addAttribute("errores", errores);
        model.addAttribute("usuario", usuario);
        return "editar-perfil";  // ← Misma página con errores
    }
    
    actualizarUsuario(usuario);
    return "redirect:/perfil";  // ← Redirigir al perfil actualizado
}
```

#### Redirigir con mensaje de error:
```java
@PostMapping("/cambiar-password")
public String cambiarPassword(@RequestParam String passwordActual,
                             @RequestParam String passwordNueva,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
    
    String usuario = (String) session.getAttribute("usuarioLogueado");
    
    if (!validarPasswordActual(usuario, passwordActual)) {
        redirectAttributes.addFlashAttribute("error", "La contraseña actual es incorrecta");
        return "redirect:/configuracion";
    }
    
    if (passwordNueva.length() < 8) {
        redirectAttributes.addFlashAttribute("error", "La nueva contraseña debe tener al menos 8 caracteres");
        return "redirect:/configuracion";
    }
    
    actualizarPassword(usuario, passwordNueva);
    redirectAttributes.addFlashAttribute("exito", "Contraseña actualizada correctamente");
    return "redirect:/configuracion";
}
```

### Validaciones complejas:

```java
@PostMapping("/reserva")
public String crearReserva(@ModelAttribute Reserva reserva, Model model) {
    List<String> errores = new ArrayList<>();
    
    // Validaciones básicas
    if (esVacio(reserva.getNombre())) {
        errores.add("El nombre es obligatorio");
    }
    
    // Validaciones de fechas
    if (reserva.getFecha() == null) {
        errores.add("La fecha es obligatoria");
    } else {
        LocalDate fechaReserva = LocalDate.parse(reserva.getFecha());
        LocalDate hoy = LocalDate.now();
        
        if (fechaReserva.isBefore(hoy)) {
            errores.add("No puedes reservar en el pasado");
        }
        
        if (fechaReserva.isAfter(hoy.plusMonths(3))) {
            errores.add("No puedes reservar con más de 3 meses de anticipación");
        }
    }
    
    // Validaciones de negocio
    if (reserva.getNumeroPersonas() > getCapacidadMaxima()) {
        errores.add("El número máximo de personas es " + getCapacidadMaxima());
    }
    
    if (!hayDisponibilidad(reserva.getFecha(), reserva.getHora())) {
        errores.add("No hay disponibilidad para esa fecha y hora");
    }
    
    // Validaciones condicionales
    if (reserva.getNumeroPersonas() > 10 && esVacio(reserva.getTelefono())) {
        errores.add("Para grupos grandes es obligatorio proporcionar teléfono");
    }
    
    if (!errores.isEmpty()) {
        model.addAttribute("errores", errores);
        model.addAttribute("reserva", reserva);
        prepararDatosFormulario(model);  // ← Preparar listas desplegables, etc.
        return "formulario-reserva";
    }
    
    guardarReserva(reserva);
    return "redirect:/reserva-confirmada";
}
```

---

## 🎯 RESUMEN DE SINTAXIS

### Controlador básico:
```java
@Controller
public class MiControlador {
    
    @GetMapping("/ruta")                           // ← Mostrar página
    @PostMapping("/ruta")                          // ← Procesar formulario
    public String metodo(parametros...) {
        // lógica
        return "vista";                            // ← templates/vista.html
        return "redirect:/otra-ruta";              // ← Redirección
    }
}
```

### Parámetros comunes:
```java
public String metodo(
    @RequestParam String param,                    // ← Dato de URL
    @ModelAttribute ClaseData datos,               // ← Datos de formulario
    Model model,                                   // ← Para enviar datos a vista
    HttpSession session                            // ← Para mantener datos entre páginas
) { }
```

### Operaciones típicas:
```java
// Enviar datos a vista
model.addAttribute("clave", valor);

// Guardar en sesión
session.setAttribute("clave", valor);

// Leer de sesión
TipoDato dato = (TipoDato) session.getAttribute("clave");

// Validar datos
if (dato == null || dato.isEmpty()) {
    errores.add("Campo obligatorio");
}
```