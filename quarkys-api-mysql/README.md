# quarkys-api-mysql Project

Proyecto de prueba para presentar el desarrollo de un crud básico haciendo uso de una base de datos relacional (Mysql), nos estamos conectando de manera local a una tabla de datos llamada customer. 

El proyecto cumple con el objetivo inicial de presentar los cuatro verbos iniciales: GET, POST, PUT y DELETE.

Como forma de poder hacer un testeo simple sobre nuestro crud, se está haciendo uso de swagger.
<br>

---
# Requisito de sistema
- Maven 3.8.4
- Java Open jdk 11
- GraalVM

---
# Extensiones

- quarkus-resteasy
- quarkus-hibernate-validator
- quarkus-resteasy-jsonb
- quarkus-smallrye-openapi
- quarkus-hibernate-orm-panache
- quarkus-jdbc-mysql
- org.mapstruct

---
# Ejecutar proyecto

- Windows cmd

    `mvnw compile quarkus:dev`

- Linux bash

    `./mvnw compile quarkus:dev`

---
# Ejecutando Swagger UI

Para acceder a la interfaz de swagger, basta solo con dirigirnos a la siguiente ruta: http://localhost:8080/q/swaggerui/

<br>

- GET -> `http://localhost:8080/api/customer`
- GET_ID -> `http://localhost:8080/api/customer/{id}`
- POST -> `http://localhost:8080/api/customer`
- PUT -> `http://localhost:8080/api/customer/{id}`
- DELETE -> `http://localhost:8080/api/customer/{id}`
