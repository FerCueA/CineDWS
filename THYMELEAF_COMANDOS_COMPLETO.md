# 🌐 Thymeleaf - Comandos y Sintaxis Completa

## 🎯 ÍNDICE
1. [Introducción a Thymeleaf](#introducción-a-thymeleaf)
2. [Mostrar datos (th:text)](#mostrar-datos-thtext)
3. [Condicionales (th:if, th:unless)](#condicionales-thif-thunless)
4. [Bucles (th:each)](#bucles-theach)  
5. [Enlaces y URLs (th:href)](#enlaces-y-urls-thhref)
6. [Formularios (th:action, th:method)](#formularios-thaction-thmethod)
7. [Campos de formulario (th:value, th:selected)](#campos-de-formulario-thvalue-thselected)
8. [Estilos y clases (th:style, th:class)](#estilos-y-clases-thstyle-thclass)
9. [Atributos dinámicos (th:attr)](#atributos-dinámicos-thattr)
10. [Incluir fragmentos (th:insert, th:replace)](#incluir-fragmentos-thinsert-threplace)
11. [Expresiones y operadores](#expresiones-y-operadores)
12. [Funciones útiles](#funciones-útiles)

---

## 🌟 INTRODUCCIÓN A THYMELEAF

### ¿Qué es Thymeleaf?

**Thymeleaf** es un motor de plantillas que permite **mezclar HTML estático con datos dinámicos** de Java. Te permite escribir HTML normal que funciona sin servidor, pero que se "llena" con datos cuando se ejecuta con Spring Boot.

### Sintaxis básica:

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">  <!-- ← OBLIGATORIO para usar Thymeleaf -->
<head>
    <title>Mi página</title>
</head>
<body>
    <!-- Contenido con comandos Thymeleaf -->
    <p th:text="${mensaje}">Texto por defecto</p>
</body>
</html>
```

### ¿Cómo funciona?

1. **Sin servidor**: El HTML se ve como texto normal
2. **Con servidor**: Thymeleaf reemplaza los `th:*` con datos reales
3. **Resultado**: HTML limpio enviado al navegador

---

## 📝 MOSTRAR DATOS (th:text)

### ¿Qué hace th:text?

**Reemplaza completamente** el contenido de un elemento HTML con el valor de una variable.

### Sintaxis básica:
```html
<elemento th:text="${variable}">contenido por defecto</elemento>
```

### Ejemplos básicos:

```html
<!-- En el controlador: model.addAttribute("nombre", "Juan Pérez"); -->
<h1 th:text="${nombre}">Nombre del usuario</h1>
<!-- Resultado: <h1>Juan Pérez</h1> -->

<p th:text="${edad}">0</p>
<!-- Si edad = 25, resultado: <p>25</p> -->

<span th:text="${precio}">0.00</span>€
<!-- Si precio = 19.99, resultado: <span>19.99</span>€ -->
```

### Con objetos complejos:

```html
<!-- En el controlador: model.addAttribute("usuario", objetoUsuario); -->
<h2 th:text="${usuario.nombre}">Nombre</h2>
<p th:text="${usuario.email}">email@ejemplo.com</p>
<p th:text="${usuario.edad}">0</p> años

<!-- Si el objeto tiene: nombre="Ana", email="ana@email.com", edad=28 -->
<!-- Resultado: -->
<!-- <h2>Ana</h2> -->
<!-- <p>ana@email.com</p> -->
<!-- <p>28</p> años -->
```

### th:text vs contenido normal:

```html
<!-- th:text REEMPLAZA completamente el contenido -->
<p th:text="${mensaje}">Este texto desaparece</p>
<!-- Resultado: <p>Contenido de la variable mensaje</p> -->

<!-- Sin th:text, se mantiene el contenido original -->
<p>Este texto se mantiene</p>
<!-- Resultado: <p>Este texto se mantiene</p> -->
```

### Concatenar texto:

```html
<!-- Opción 1: Con + -->
<p th:text="'Hola ' + ${nombre} + ', tienes ' + ${edad} + ' años'">Saludo</p>

<!-- Opción 2: Con || (más legible) -->
<p th:text="|Hola ${nombre}, tienes ${edad} años|">Saludo</p>

<!-- Opción 3: Inline (mezclar texto fijo con variables) -->
<p>Hola [[${nombre}]], tienes [[${edad}]] años</p>
```

### Formatear números y fechas:

```html
<!-- Formatear decimales -->
<p th:text="${#numbers.formatDecimal(precio, 1, 2)}">0.00</p>
<!-- Si precio = 19.999, resultado: 19.99 -->

<!-- Formatear fechas -->
<p th:text="${#dates.format(fecha, 'dd/MM/yyyy')}">01/01/2000</p>
<!-- Si fecha = 2025-10-20, resultado: 20/10/2025 -->

<!-- Formatear con moneda -->
<p th:text="${#numbers.formatCurrency(precio)}">€0.00</p>
```

---

## ❓ CONDICIONALES (th:if, th:unless)

### th:if - Mostrar SI la condición es verdadera

#### Sintaxis:
```html
<elemento th:if="${condicion}">contenido</elemento>
```

#### Ejemplos básicos:

```html
<!-- Mostrar solo si el usuario está logueado -->
<div th:if="${usuario != null}">
    <p>Bienvenido, <span th:text="${usuario.nombre}">Usuario</span></p>
</div>

<!-- Mostrar solo si la edad es mayor a 18 -->
<p th:if="${edad >= 18}">Eres mayor de edad</p>

<!-- Mostrar solo si hay productos -->
<div th:if="${not #lists.isEmpty(productos)}">
    <h3>Productos disponibles:</h3>
</div>
```

#### Condiciones complejas:

```html
<!-- AND lógico -->
<p th:if="${usuario != null and usuario.activo}">Usuario activo</p>

<!-- OR lógico -->
<p th:if="${edad >= 18 or tienePermiso}">Puede acceder</p>

<!-- Comparaciones -->
<p th:if="${precio > 100}">Producto caro</p>
<p th:if="${categoria == 'premium'}">Categoría premium</p>
<p th:if="${stock <= 5}">¡Pocas unidades!</p>

<!-- Verificar strings -->
<p th:if="${not #strings.isEmpty(descripcion)}">
    <span th:text="${descripcion}">Descripción</span>
</p>
```

### th:unless - Mostrar SI la condición es FALSA

#### Sintaxis:
```html
<elemento th:unless="${condicion}">contenido</elemento>
```

#### Ejemplos:

```html
<!-- Mostrar si NO está logueado -->
<div th:unless="${usuario != null}">
    <a href="/login">Iniciar sesión</a>
</div>

<!-- Equivalente con th:if -->
<div th:if="${usuario == null}">
    <a href="/login">Iniciar sesión</a>
</div>

<!-- Mostrar si NO hay productos -->
<p th:unless="${not #lists.isEmpty(productos)}">No hay productos disponibles</p>

<!-- Más simple: -->
<p th:if="${#lists.isEmpty(productos)}">No hay productos disponibles</p>
```

### Condiciones con múltiples elementos:

```html
<!-- Contenedor completo condicional -->
<div th:if="${mostrarSeccion}">
    <h2>Sección especial</h2>
    <p>Esta sección solo aparece si mostrarSeccion es true</p>
    <ul>
        <li>Elemento 1</li>
        <li>Elemento 2</li>
    </ul>
</div>

<!-- Lista condicional -->
<ul th:if="${not #lists.isEmpty(tareas)}">
    <li th:each="tarea : ${tareas}" th:text="${tarea}">Tarea</li>
</ul>
<p th:if="${#lists.isEmpty(tareas)}">No hay tareas pendientes</p>
```

---

## 🔄 BUCLES (th:each)

### ¿Qué hace th:each?

**Repite un elemento HTML** por cada item de una colección (lista, array, mapa).

### Sintaxis básica:
```html
<elemento th:each="item : ${coleccion}" th:text="${item}">contenido por defecto</elemento>
```

### Ejemplo básico con lista:

```html
<!-- En el controlador: model.addAttribute("frutas", Arrays.asList("Manzana", "Pera", "Plátano")); -->

<ul>
    <li th:each="fruta : ${frutas}" th:text="${fruta}">Fruta</li>
</ul>

<!-- Resultado: -->
<!-- <ul> -->
<!--     <li>Manzana</li> -->
<!--     <li>Pera</li> -->
<!--     <li>Plátano</li> -->
<!-- </ul> -->
```

### Con objetos complejos:

```html
<!-- En el controlador: model.addAttribute("productos", listaProductos); -->

<table>
    <thead>
        <tr>
            <th>Nombre</th>
            <th>Precio</th>
            <th>Stock</th>
        </tr>
    </thead>
    <tbody>
        <tr th:each="producto : ${productos}">
            <td th:text="${producto.nombre}">Nombre</td>
            <td th:text="${producto.precio}">0.00</td>
            <td th:text="${producto.stock}">0</td>
        </tr>
    </tbody>
</table>
```

### Variable de estado del bucle:

```html
<div th:each="item, estado : ${items}">
    <p>
        Elemento #<span th:text="${estado.index}">0</span>: 
        <span th:text="${item}">Item</span>
    </p>
</div>
```

#### Propiedades del estado:

| Propiedad | Descripción | Ejemplo |
|-----------|-------------|---------|
| `index` | Índice actual (empieza en 0) | 0, 1, 2, 3... |
| `count` | Contador actual (empieza en 1) | 1, 2, 3, 4... |
| `size` | Tamaño total de la colección | 5 |
| `current` | Elemento actual | mismo que `item` |
| `even` | ¿Es posición par? | true/false |
| `odd` | ¿Es posición impar? | true/false |
| `first` | ¿Es el primero? | true/false |
| `last` | ¿Es el último? | true/false |

#### Ejemplos con estado:

```html
<ul>
    <li th:each="producto, estado : ${productos}" 
        th:class="${estado.odd} ? 'fila-impar' : 'fila-par'">
        
        <span th:text="${estado.count}">1</span>. 
        <span th:text="${producto.nombre}">Product</span>
        
        <span th:if="${estado.first}">(Primero)</span>
        <span th:if="${estado.last}">(Último)</span>
    </li>
</ul>
```

### Bucles con condicionales:

```html
<!-- Solo productos disponibles -->
<div th:each="producto : ${productos}" th:if="${producto.stock > 0}">
    <h3 th:text="${producto.nombre}">Producto</h3>
    <p th:text="|Stock: ${producto.stock} unidades|">Stock</p>
</div>

<!-- Con clases CSS condicionales -->
<tr th:each="pedido : ${pedidos}" 
    th:class="${pedido.urgente} ? 'urgente' : 'normal'">
    <td th:text="${pedido.numero}">123</td>
    <td th:text="${pedido.cliente}">Cliente</td>
</tr>
```

### Bucles anidados:

```html
<!-- Lista de categorías con productos -->
<div th:each="categoria : ${categorias}">
    <h2 th:text="${categoria.nombre}">Categoría</h2>
    
    <ul>
        <li th:each="producto : ${categoria.productos}" 
            th:text="${producto.nombre}">Producto</li>
    </ul>
</div>
```

### Iterar sobre mapas:

```html
<!-- En el controlador: Map<String, Integer> precios = ... -->
<table>
    <tr th:each="entrada : ${precios}">
        <td th:text="${entrada.key}">Producto</td>     <!-- Clave -->
        <td th:text="${entrada.value}">0</td>€          <!-- Valor -->
    </tr>
</table>
```

---

## 🔗 ENLACES Y URLs (th:href)

### ¿Qué hace th:href?

**Genera URLs dinámicas** para enlaces, considerando la ruta base de la aplicación y permitiendo pasar parámetros.

### Sintaxis básica:
```html
<a th:href="@{/ruta}">Texto del enlace</a>
```

### Enlaces simples:

```html
<!-- Enlaces internos -->
<a th:href="@{/}">Inicio</a>
<a th:href="@{/productos}">Productos</a>
<a th:href="@{/contacto}">Contacto</a>

<!-- Enlaces externos -->
<a th:href="@{https://www.google.com}">Google</a>
<a href="https://www.google.com">Google</a>  <!-- También válido para URLs externas -->
```

### Enlaces con parámetros:

```html
<!-- Un parámetro -->
<a th:href="@{/producto(id=${producto.id})}">Ver producto</a>
<!-- Resultado: /producto?id=123 -->

<!-- Múltiples parámetros -->
<a th:href="@{/buscar(categoria=${cat},precio=${precio})}">Buscar</a>
<!-- Resultado: /buscar?categoria=electronica&precio=100 -->

<!-- Parámetros en la ruta (PathVariable) -->
<a th:href="@{/usuario/{id}(id=${usuario.id})}">Perfil</a>
<!-- Resultado: /usuario/123 -->
```

### En bucles (muy común):

```html
<table>
    <tr th:each="producto : ${productos}">
        <td th:text="${producto.nombre}">Producto</td>
        <td>
            <a th:href="@{/producto/{id}(id=${producto.id})}">Ver</a>
            <a th:href="@{/editar(id=${producto.id})}">Editar</a>
            <a th:href="@{/eliminar(id=${producto.id})}" 
               onclick="return confirm('¿Estás seguro?')">Eliminar</a>
        </td>
    </tr>
</table>
```

### URLs condicionales:

```html
<!-- Enlace diferente según condición -->
<a th:href="${usuario.admin} ? @{/admin/panel} : @{/usuario/panel}">
    Panel de control
</a>

<!-- Enlace solo si cumple condición -->
<a th:if="${producto.disponible}" 
   th:href="@{/comprar(id=${producto.id})}">Comprar</a>
```

### Enlaces a recursos estáticos:

```html
<!-- CSS -->
<link rel="stylesheet" th:href="@{/styles/estilos.css}" />

<!-- JavaScript -->
<script th:src="@{/js/script.js}"></script>

<!-- Imágenes -->
<img th:src="@{/img/logo.png}" alt="Logo" />

<!-- Con parámetros dinámicos -->
<img th:src="@{/img/productos/{nombre}(nombre=${producto.imagen})}" alt="Producto" />
<!-- Resultado: /img/productos/laptop.jpg -->
```

---

## 📋 FORMULARIOS (th:action, th:method)

### ¿Qué hacen th:action y th:method?

- **th:action**: Define dinámicamente **dónde se envía** el formulario
- **th:method**: Define el **método HTTP** (GET o POST)

### Sintaxis básica:
```html
<form th:action="@{/ruta}" th:method="post">
    <!-- campos del formulario -->
</form>
```

### Formulario básico:

```html
<form th:action="@{/registro}" th:method="post">
    <input type="text" name="nombre" placeholder="Nombre" required />
    <input type="email" name="email" placeholder="Email" required />
    <input type="password" name="password" placeholder="Contraseña" required />
    <input type="submit" value="Registrarse" />
</form>
```

### Formularios con parámetros en la acción:

```html
<!-- Editar producto específico -->
<form th:action="@{/producto/{id}/editar(id=${producto.id})}" th:method="post">
    <input type="text" name="nombre" th:value="${producto.nombre}" />
    <input type="number" name="precio" th:value="${producto.precio}" />
    <input type="submit" value="Actualizar" />
</form>

<!-- Con parámetros de query -->
<form th:action="@{/buscar(categoria=${categoriaActual})}" th:method="get">
    <input type="text" name="termino" placeholder="¿Qué buscas?" />
    <input type="submit" value="Buscar" />
</form>
```

### Formularios condicionales:

```html
<!-- Acción diferente según modo -->
<form th:action="${modo == 'editar'} ? @{/producto/{id}/actualizar(id=${producto.id})} : @{/producto/crear}" 
      th:method="post">
    
    <input type="text" name="nombre" th:value="${producto?.nombre}" />
    <input type="submit" th:value="${modo == 'editar'} ? 'Actualizar' : 'Crear'" />
</form>
```

### Formularios con objeto Model:

```html
<!-- El controlador pasa: model.addAttribute("usuario", usuario); -->
<form th:action="@{/usuario/guardar}" th:method="post" th:object="${usuario}">
    <!-- th:field combina name, id, y value automáticamente -->
    <input type="text" th:field="*{nombre}" placeholder="Nombre" />
    <input type="email" th:field="*{email}" placeholder="Email" />
    <input type="number" th:field="*{edad}" placeholder="Edad" />
    <input type="submit" value="Guardar" />
</form>

<!-- Equivale a: -->
<form th:action="@{/usuario/guardar}" th:method="post">
    <input type="text" name="nombre" id="nombre" th:value="${usuario.nombre}" placeholder="Nombre" />
    <input type="email" name="email" id="email" th:value="${usuario.email}" placeholder="Email" />
    <input type="number" name="edad" id="edad" th:value="${usuario.edad}" placeholder="Edad" />
    <input type="submit" value="Guardar" />
</form>
```

---

## 📝 CAMPOS DE FORMULARIO (th:value, th:selected, th:checked)

### th:value - Valor de campos input

#### Para mantener datos en formularios con errores:

```html
<!-- Si hay errores, mantener lo que escribió el usuario -->
<form th:action="@{/registro}" th:method="post">
    <input type="text" name="nombre" th:value="${usuario?.nombre}" placeholder="Nombre" />
    <input type="email" name="email" th:value="${usuario?.email}" placeholder="Email" />
    <input type="number" name="edad" th:value="${usuario?.edad}" placeholder="Edad" />
</form>
```

#### Valores dinámicos:

```html
<!-- Campo con valor calculado -->
<input type="number" name="total" th:value="${precio * cantidad}" readonly />

<!-- Campo con valor por defecto -->
<input type="date" name="fecha" th:value="${#dates.format(#dates.createNow(), 'yyyy-MM-dd')}" />
```

### th:selected - Opciones seleccionadas en select

```html
<select name="categoria">
    <option value="">Selecciona categoría</option>
    <option value="electronica" th:selected="${categoria == 'electronica'}">Electrónica</option>
    <option value="ropa" th:selected="${categoria == 'ropa'}">Ropa</option>
    <option value="hogar" th:selected="${categoria == 'hogar'}">Hogar</option>
</select>

<!-- Con bucle -->
<select name="ciudad">
    <option value="">Selecciona ciudad</option>
    <option th:each="ciudad : ${ciudades}" 
            th:value="${ciudad.id}" 
            th:text="${ciudad.nombre}"
            th:selected="${ciudad.id == usuarioCiudad}">Ciudad</option>
</select>
```

### th:checked - Checkboxes y radio buttons

```html
<!-- Checkboxes -->
<label>
    <input type="checkbox" name="newsletter" value="true" th:checked="${usuario.recibirNewsletter}" />
    Recibir newsletter
</label>

<label>
    <input type="checkbox" name="terminos" value="true" th:checked="${aceptaTerminos}" />
    Acepto los términos y condiciones
</label>

<!-- Radio buttons -->
<fieldset>
    <legend>Género</legend>
    <label>
        <input type="radio" name="genero" value="M" th:checked="${usuario.genero == 'M'}" />
        Masculino
    </label>
    <label>
        <input type="radio" name="genero" value="F" th:checked="${usuario.genero == 'F'}" />
        Femenino
    </label>
    <label>
        <input type="radio" name="genero" value="O" th:checked="${usuario.genero == 'O'}" />
        Otro
    </label>
</fieldset>
```

### Campos complejos con validación:

```html
<!-- Campo con clase CSS condicional para errores -->
<input type="email" name="email" 
       th:value="${usuario?.email}"
       th:class="${#fields.hasErrors('email')} ? 'campo-error' : 'campo-normal'"
       placeholder="Email" />
```

---

## 🎨 ESTILOS Y CLASES (th:style, th:class)

### th:style - Estilos CSS dinámicos

#### Sintaxis básica:
```html
<elemento th:style="'propiedad: ' + ${valor}">contenido</elemento>
```

#### Ejemplos:

```html
<!-- Color dinámico -->
<p th:style="'color: ' + ${colorTexto}">Texto coloreado</p>

<!-- Múltiples propiedades -->
<div th:style="'background-color: ' + ${colorFondo} + '; padding: 10px; border: 1px solid ' + ${colorBorde}">
    Contenido con estilo dinámico
</div>

<!-- Con sintaxis de pipe (más legible) -->
<div th:style="|background-color: ${colorFondo}; color: ${colorTexto}; font-size: ${tamaño}px|">
    Texto estilizado
</div>
```

#### Estilos condicionales:

```html
<!-- Mostrar en rojo si está agotado -->
<p th:style="${producto.stock == 0} ? 'color: red; font-weight: bold' : 'color: green'">
    Stock: <span th:text="${producto.stock}">0</span>
</p>

<!-- Barra de progreso -->
<div class="barra-contenedor">
    <div class="barra-progreso" th:style="|width: ${porcentaje}%|"></div>
</div>
```

### th:class - Clases CSS dinámicas

#### Sintaxis básica:
```html
<elemento th:class="${expresion}">contenido</elemento>
```

#### Clase única condicional:

```html
<!-- Clase según condición -->
<div th:class="${usuario.activo} ? 'usuario-activo' : 'usuario-inactivo'">
    Estado del usuario
</div>

<!-- Solo añadir clase si se cumple condición -->
<p th:class="${producto.destacado} ? 'destacado' : ''">
    Producto
</p>
```

#### Múltiples clases:

```html
<!-- Combinar clases fijas con dinámicas -->
<div th:class="'producto ' + ${producto.categoria} + (${producto.oferta} ? ' en-oferta' : '')">
    Información del producto
</div>

<!-- Con operador ternario -->
<tr th:class="'fila ' + (${estado.odd} ? 'impar' : 'par') + (${item.urgente} ? ' urgente' : '')">
    Contenido de la fila
</tr>
```

#### th:classappend - Añadir clases a las existentes:

```html
<!-- Mantener clases existentes y añadir dinámicas -->
<div class="producto destacado" th:classappend="${producto.agotado} ? 'agotado' : ''">
    <!-- Resultado si agotado=true: class="producto destacado agotado" -->
    Producto
</div>
```

### Ejemplos prácticos:

#### Sistema de colores por estado:

```html
<table>
    <tr th:each="pedido : ${pedidos}">
        <td th:text="${pedido.numero}">123</td>
        <td th:text="${pedido.cliente}">Cliente</td>
        <td th:class="${pedido.estado}" th:text="${pedido.estado}">Estado</td>
    </tr>
</table>

<!-- CSS correspondiente: -->
<!-- .pendiente { color: orange; } -->
<!-- .completado { color: green; } -->
<!-- .cancelado { color: red; } -->
```

#### Indicador de stock:

```html
<div th:each="producto : ${productos}" class="producto">
    <h3 th:text="${producto.nombre}">Producto</h3>
    
    <p th:class="${producto.stock > 10} ? 'stock-alto' : 
                 (${producto.stock > 0} ? 'stock-medio' : 'stock-agotado')"
       th:text="|Stock: ${producto.stock}|">Stock</p>
</div>
```

---

## ⚙️ ATRIBUTOS DINÁMICOS (th:attr)

### ¿Qué hace th:attr?

Permite **establecer cualquier atributo HTML** de forma dinámica.

### Sintaxis:
```html
<elemento th:attr="atributo=${valor}">contenido</elemento>
```

### Ejemplos básicos:

```html
<!-- Título dinámico -->
<p th:attr="title=${producto.descripcion}">
    <span th:text="${producto.nombre}">Producto</span>
</p>

<!-- ID dinámico -->
<div th:attr="id='producto-' + ${producto.id}">
    Producto con ID dinámico
</div>

<!-- Múltiples atributos -->
<img th:attr="src=@{'/img/' + ${producto.imagen}}, alt=${producto.nombre}, title=${producto.descripcion}" />
```

### Casos específicos comunes:

#### Imágenes dinámicas:
```html
<!-- Con th:src (más específico) -->
<img th:src="@{'/img/productos/' + ${producto.imagen}}" 
     th:alt="${producto.nombre}" />

<!-- Con th:attr (más genérico) -->
<img th:attr="src=@{'/img/productos/' + ${producto.imagen}}, alt=${producto.nombre}" />
```

#### Enlaces con target dinámico:
```html
<a th:href="@{${enlace.url}}" 
   th:attr="target=${enlace.externo} ? '_blank' : '_self'"
   th:text="${enlace.texto}">Enlace</a>
```

#### Data attributes para JavaScript:
```html
<button th:attr="data-id=${producto.id}, data-precio=${producto.precio}"
        onclick="añadirCarrito(this)">
    Añadir al carrito
</button>
```

#### Atributos condicionales:
```html
<!-- Disabled condicional -->
<input type="submit" value="Enviar"
       th:attr="disabled=${not formularioValido} ? 'disabled' : null" />

<!-- Placeholder dinámico -->
<input type="text" name="busqueda"
       th:attr="placeholder='Buscar en ' + ${categoria}" />
```

---

## 🧩 INCLUIR FRAGMENTOS (th:insert, th:replace)

### ¿Qué son los fragmentos?

Los **fragmentos** permiten **reutilizar código HTML** en múltiples páginas, como headers, footers, menús, etc.

### 1. Definir fragmentos:

```html
<!-- templates/fragmentos.html -->
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>
    <!-- Fragmento de header -->
    <header th:fragment="header">
        <nav>
            <a th:href="@{/}">Inicio</a>
            <a th:href="@{/productos}">Productos</a>
            <a th:href="@{/contacto}">Contacto</a>
        </nav>
    </header>
    
    <!-- Fragmento de footer -->
    <footer th:fragment="footer">
        <p>&copy; 2025 Mi Empresa. Todos los derechos reservados.</p>
    </footer>
    
    <!-- Fragmento con parámetros -->
    <div th:fragment="alerta(tipo, mensaje)">
        <div th:class="'alert alert-' + ${tipo}">
            <p th:text="${mensaje}">Mensaje</p>
        </div>
    </div>
</body>
</html>
```

### 2. Usar fragmentos con th:insert:

```html
<!-- templates/pagina.html -->
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Mi Página</title>
</head>
<body>
    <!-- Insertar header -->
    <div th:insert="~{fragmentos :: header}"></div>
    
    <!-- Contenido específico de la página -->
    <main>
        <h1>Contenido principal</h1>
        <p>Esta es una página específica.</p>
    </main>
    
    <!-- Insertar footer -->
    <div th:insert="~{fragmentos :: footer}"></div>
</body>
</html>
```

### 3. th:insert vs th:replace:

#### th:insert - Inserta DENTRO del elemento:
```html
<div th:insert="~{fragmentos :: header}"></div>
<!-- Resultado: -->
<!-- <div> -->
<!--     <header>contenido del header</header> -->
<!-- </div> -->
```

#### th:replace - REEMPLAZA completamente el elemento:
```html
<div th:replace="~{fragmentos :: header}"></div>
<!-- Resultado: -->
<!-- <header>contenido del header</header> -->
```

### 4. Fragmentos con parámetros:

```html
<!-- Usar el fragmento alerta con parámetros -->
<div th:insert="~{fragmentos :: alerta('success', 'Usuario creado correctamente')}"></div>
<div th:insert="~{fragmentos :: alerta('error', 'Error al procesar la solicitud')}"></div>
```

### 5. Fragmentos condicionales:

```html
<!-- Solo mostrar si hay mensajes -->
<div th:if="${mensaje != null}" 
     th:insert="~{fragmentos :: alerta(${tipoMensaje}, ${mensaje})}"></div>
```

---

## 🔤 EXPRESIONES Y OPERADORES

### Variables y expresiones básicas:

```html
<!-- Variable simple -->
${variable}

<!-- Propiedad de objeto -->
${usuario.nombre}

<!-- Elemento de lista -->
${productos[0].precio}

<!-- Mapa -->
${precios['laptop']}
```

### Operadores aritméticos:

```html
<!-- Suma -->
<p th:text="${precio + descuento}">Total</p>

<!-- Resta -->
<p th:text="${precio - descuento}">Precio final</p>

<!-- Multiplicación -->
<p th:text="${precio * cantidad}">Subtotal</p>

<!-- División -->
<p th:text="${total / cantidad}">Precio unitario</p>

<!-- Módulo -->
<p th:text="${numero % 2 == 0} ? 'Par' : 'Impar'">Paridad</p>
```

### Operadores de comparación:

```html
<!-- Igualdad -->
<p th:if="${edad == 18}">Recién mayor de edad</p>

<!-- Desigualdad -->
<p th:if="${edad != 0}">Edad válida</p>

<!-- Mayor/menor -->
<p th:if="${precio > 100}">Producto caro</p>
<p th:if="${stock <= 5}">Pocas unidades</p>

<!-- Mayor/menor o igual -->
<p th:if="${edad >= 18}">Mayor de edad</p>
```

### Operadores lógicos:

```html
<!-- AND -->
<p th:if="${usuario != null and usuario.activo}">Usuario válido</p>

<!-- OR -->
<p th:if="${esAdmin or esEditor}">Puede editar</p>

<!-- NOT -->
<p th:if="${not usuario.bloqueado}">Usuario activo</p>
```

### Operador ternario:

```html
<!-- Sintaxis: condicion ? valorSiTrue : valorSiFalse -->
<p th:text="${edad >= 18} ? 'Adulto' : 'Menor'">Edad</p>

<div th:class="${producto.destacado} ? 'destacado' : 'normal'">
    Producto
</div>

<!-- Ternario anidado -->
<p th:text="${nota >= 9} ? 'Excelente' : (${nota >= 7} ? 'Bueno' : 'Necesita mejorar')">
    Calificación
</p>
```

### Concatenación de strings:

```html
<!-- Con + -->
<p th:text="'Hola ' + ${nombre} + ', bienvenido'">Saludo</p>

<!-- Con || (más legible) -->
<p th:text="|Hola ${nombre}, bienvenido|">Saludo</p>

<!-- Mezclando estilos -->
<p th:text="'Usuario: ' + ${usuario.nombre} + |, Edad: ${usuario.edad} años|">Info</p>
```

---

## 🛠️ FUNCIONES ÚTILES

### Funciones de strings (#strings):

```html
<!-- Verificar si está vacío -->
<p th:if="${not #strings.isEmpty(descripcion)}" th:text="${descripcion}">Descripción</p>

<!-- Longitud -->
<p th:text="|El nombre tiene ${#strings.length(nombre)} caracteres|">Longitud</p>

<!-- Convertir a mayúsculas/minúsculas -->
<p th:text="${#strings.toUpperCase(nombre)}">NOMBRE</p>
<p th:text="${#strings.toLowerCase(email)}">email</p>

<!-- Contiene substring -->
<p th:if="${#strings.contains(email, '@gmail.com')}">Email de Gmail</p>

<!-- Empezar/terminar con -->
<p th:if="${#strings.startsWith(telefono, '6')}">Móvil</p>
<p th:if="${#strings.endsWith(archivo, '.pdf')}">Archivo PDF</p>

<!-- Substring -->
<p th:text="${#strings.substring(descripcion, 0, 50)}...">Descripción corta</p>

<!-- Reemplazar -->
<p th:text="${#strings.replace(texto, 'viejo', 'nuevo')}">Texto modificado</p>
```

### Funciones de números (#numbers):

```html
<!-- Formatear decimales -->
<p th:text="${#numbers.formatDecimal(precio, 1, 2)}">19.99</p>

<!-- Formatear con separadores de miles -->
<p th:text="${#numbers.formatInteger(cantidad, 3)}">1,000</p>

<!-- Formatear como moneda -->
<p th:text="${#numbers.formatCurrency(precio)}">€19.99</p>

<!-- Formatear porcentaje -->
<p th:text="${#numbers.formatPercent(descuento, 1, 2)}">15.00%</p>
```

### Funciones de fechas (#dates):

```html
<!-- Formatear fecha -->
<p th:text="${#dates.format(fecha, 'dd/MM/yyyy')}">20/10/2025</p>

<!-- Diferentes formatos -->
<p th:text="${#dates.format(fecha, 'dd MMMM yyyy')}">20 octubre 2025</p>
<p th:text="${#dates.format(fecha, 'EEEE, dd/MM/yyyy')}">domingo, 20/10/2025</p>

<!-- Fecha actual -->
<p th:text="${#dates.format(#dates.createNow(), 'dd/MM/yyyy HH:mm')}">Ahora</p>

<!-- Componentes de fecha -->
<p th:text="${#dates.year(fecha)}">2025</p>
<p th:text="${#dates.month(fecha)}">10</p>
<p th:text="${#dates.day(fecha)}">20</p>
```

### Funciones de listas (#lists):

```html
<!-- Verificar si está vacía -->
<p th:if="${#lists.isEmpty(productos)}">No hay productos</p>

<!-- Tamaño -->
<p th:text="|Hay ${#lists.size(productos)} productos|">Cantidad</p>

<!-- Contiene elemento -->
<p th:if="${#lists.contains(categorias, 'electronica')}">Incluye electrónica</p>

<!-- Primer/último elemento -->
<p th:text="${#lists.first(productos).nombre}">Primer producto</p>
<p th:text="${#lists.last(productos).nombre}">Último producto</p>
```

### Funciones de mapas (#maps):

```html
<!-- Verificar si está vacío -->
<p th:if="${not #maps.isEmpty(configuracion)}">Hay configuración</p>

<!-- Tamaño -->
<p th:text="|${#maps.size(precios)} productos con precio|">Cantidad</p>

<!-- Contiene clave -->
<p th:if="${#maps.containsKey(precios, 'laptop')}">Precio de laptop disponible</p>
```

### Funciones de campos de formulario (#fields):

```html
<!-- Verificar errores -->
<input type="email" name="email" 
       th:class="${#fields.hasErrors('email')} ? 'error' : 'normal'" />

<!-- Mostrar errores -->
<div th:if="${#fields.hasErrors('email')}" class="errores">
    <p th:each="error : ${#fields.errors('email')}" th:text="${error}">Error</p>
</div>
```

---

## 🎯 RESUMEN DE SINTAXIS RÁPIDA

### Comandos básicos:
```html
th:text="${variable}"              <!-- Mostrar texto -->
th:if="${condicion}"               <!-- Mostrar si true -->
th:unless="${condicion}"           <!-- Mostrar si false -->
th:each="item : ${lista}"          <!-- Bucle -->
th:href="@{/ruta}"                 <!-- Enlaces -->
th:src="@{/imagen.jpg}"            <!-- Imágenes -->
th:action="@{/formulario}"         <!-- Acción formulario -->
th:method="post"                   <!-- Método HTTP -->
th:value="${valor}"                <!-- Valor de input -->
th:class="${clase}"                <!-- Clase CSS -->
th:style="|color: ${color}|"       <!-- Estilo CSS -->
```

### Expresiones comunes:
```html
${variable}                        <!-- Variable simple -->
${objeto.propiedad}                <!-- Propiedad de objeto -->
${lista[indice]}                   <!-- Elemento de lista -->
${condicion} ? 'si' : 'no'         <!-- Operador ternario -->
|Texto ${variable} más texto|      <!-- Concatenación con pipe -->
${#strings.isEmpty(texto)}         <!-- Función de utilidad -->
```

### URLs y enlaces:
```html
@{/ruta}                          <!-- URL simple -->
@{/ruta(param=${valor})}          <!-- URL con parámetros -->
@{/ruta/{id}(id=${valor})}        <!-- URL con path variable -->
@{~/recurso.css}                  <!-- Recurso estático -->
```

¡Con estos comandos puedes crear páginas web dinámicas completas! 🚀