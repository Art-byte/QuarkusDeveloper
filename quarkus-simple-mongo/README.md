# code-with-quarkus Project

Proyecto de prueba para presentar el desarrollo de un crud básico haciendo uso de una base de datos NoSQL, en este caso es Mongodb y no estamos conectando de manera local a una colección de datos llamada usuarios. 

El proyecto cumple con el objetivo inicial de presentar los cuatro verbos iniciales: GET, POST, PUT y DELETE.
<br>

---
# Requisito de sistema

- Java Open jdk 11
- GraalVM

---
# Extensiones

- quarkus-resteasy
- quarkus-resteasy-reactive-jsonb
- quarkus-mongodb-panache
- quarkus-resteasy-reactive
- io.rest-assured

---
# Ejecutar proyecto

- Windows cmd

    `mvnw compile quarkus:dev`

- Linux bash

    `./mvnw compile quarkus:dev`

---
# Pruebas  

- AddUser POST -> `http://localhost:8080/api/users`
- GetAllUser GET -> `http://localhost:8080/api/users`
- FindUserById GET -> `http://localhost:8080/api/users/{IdUser}`
- UpdateUser PUT -> `http://localhost:8080/api/users/{IdUser}`
- DeleteUser DELETE -> `http://localhost:8080/api/users/{IdUser}`





