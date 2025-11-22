# TicketingSystem - CineDWS

Sistema de reservas de entradas para cine desarrollado en Spring Boot y Thymeleaf.

## Descripción
Este proyecto permite a los usuarios reservar entradas para sesiones de cine, seleccionando película, sala, sesión, número de entradas y butacas, y finalizando la compra con registro y generación de códigos QR para cada ticket. El sistema gestiona la persistencia de datos, validaciones y navegación robusta entre pasos.

## Características principales
- Selección de película, sala y sesión desde la base de datos
- Formulario de datos personales y número de entradas
- Selección de butacas con validación backend
- Resumen de compra y cálculo de total
- Registro de compra y tickets en la base de datos
- Generación simulada de códigos QR para cada ticket
- Navegación entre pasos y reinicio de sesión con botón Home
- Restricción de acceso: no se puede acceder a pasos intermedios sin pasar por el inicio
- Gestión de datos de tarjeta mediante modelo y sesión

## Estructura del proyecto
```
CineDWS/
├── src/
│   ├── main/
│   │   ├── java/es/dsw/controllers/   # Controladores (Step1, Step2, Step3, Step4, End, Home)
│   │   ├── java/es/dsw/models/        # Modelos (FormularioReserva, TarjetaDatos, etc.)
│   │   ├── java/es/dsw/dao/           # DAOs para acceso a BD
│   │   └── resources/templates/       # Vistas Thymeleaf
│   └── test/
├── pom.xml                           # Configuración Maven
└── db_filmcinema_AP4.sql              # Script de base de datos
```

## Instalación y ejecución
1. Clona el repositorio:
   ```bash
   git clone https://github.com/FerCueA/CineDWS.git
   ```
2. Configura la base de datos MySQL y ejecuta el script `db_filmcinema_AP4.sql`.
3. Modifica `src/main/resources/application.properties` con tus credenciales de BD.
4. Compila y ejecuta el proyecto:
   ```bash
   ./mvnw spring-boot:run
   ```
5. Accede a la aplicación en `http://localhost:8080/index`.

## Uso
- Sigue el flujo de reserva desde el index.
- Elige película, sala y sesión.
- Introduce tus datos y selecciona el número de entradas.
- Selecciona las butacas y confirma la compra.
- Finaliza el proceso y descarga los códigos QR generados.
- Usa el botón Home para reiniciar la sesión y limpiar los datos.

## Tecnologías utilizadas
- Java 17
- Spring Boot
- Thymeleaf
- MySQL
- Maven

## Autor
- FerCueA

## Licencia
Este proyecto está bajo la licencia MIT.
