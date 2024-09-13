👨🏻‍💻 INTEGRANTES
- Oriana Valentina Guerrero Torrado
- Claudio Van Anghelo Flores Uceda

Proyecto de Registro de Turnos - Clínica Odontológica
Este proyecto es un sistema de gestión de turnos para una clínica odontológica. Permite registrar, listar, modificar y eliminar turnos, así como gestionar la información de pacientes y odontólogos.

Características
- Registro de turnos: Permite seleccionar una fecha y hora para el turno dentro de un rango permitido (08:00 a 18:00 horas).
- Validaciones: El sistema incluye validaciones para evitar que los usuarios agenden turnos fuera de las horas de atención de la clínica.
- Gestión de pacientes: Crear, modificar, listar y eliminar pacientes.
- Gestión de odontólogos: Crear, modificar, listar y eliminar odontólogos.
- Interfaz amigable: Diseño limpio y fácil de usar, adaptado para una clínica odontológica.
- Registro de logs: Utilización de Log4j para gestionar los logs del sistema.
- Pruebas unitarias: Pruebas con JUnit para garantizar la calidad del código.

Tecnologías utilizadas
- Java: Lenguaje de programación principal utilizado para desarrollar la aplicación backend.

- Spring Boot Starter Web: Para desarrollar aplicaciones web y APIs REST.
- Spring Boot Starter Data JPA: Para la integración con bases de datos usando Hibernate.
- Spring Boot Starter Test: Incluye JUnit y otras dependencias necesarias para pruebas unitarias e integración.
- JPA (Java Persistence API): Para la gestión de persistencia y operaciones CRUD con la base de datos.

- Hibernate: Mapeo Objeto-Relacional (ORM) para interactuar con la base de datos.

- Maven: Para la gestión de dependencias y automatización del proceso de construcción del proyecto.

- JavaScript (JS): Utilizado en el frontend para agregar interactividad en la aplicación.

- HTML/CSS: Lenguajes de marcado y estilo utilizados para estructurar y estilizar el frontend.

- Bootstrap: Framework de CSS utilizado para crear un diseño moderno y responsivo.

- Lombok: Para simplificar la creación de getters, setters y otros métodos repetitivos en las clases de entidades.

- Log4j: Manejo de logs, versión 1.2.17, para registrar eventos importantes en el sistema.

- JUnit: Utilizado para pruebas unitarias e integración dentro del proyecto.

- Base de datos H2: Base de datos en memoria utilizada para pruebas y desarrollo.

Nota: La aplicación se ejecuta en el puerto 8080 por defecto. Si deseas cambiar el puerto, puedes modificar el archivo application.properties.
