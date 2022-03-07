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

- POST -> `http://localhost:8080/api/users`
![image text](https://github.com/Art-byte/QuarkusDeveloper/blob/main/quarkus-simple-mongo/src/main/images/Post.jpg)


- GET -> `http://localhost:8080/api/users`
![image text](https://github.com/Art-byte/QuarkusDeveloper/blob/main/quarkus-simple-mongo/src/main/images/GetAll.jpg)


- GET -> `http://localhost:8080/api/users/{IdUser}`
![image text](https://github.com/Art-byte/QuarkusDeveloper/blob/main/quarkus-simple-mongo/src/main/images/GetById.jpg)


- PUT -> `http://localhost:8080/api/users/{IdUser}`
![image text](https://github.com/Art-byte/QuarkusDeveloper/blob/main/quarkus-simple-mongo/src/main/images/Update.jpg)


- DELETE -> `http://localhost:8080/api/users/{IdUser}`
![image text](https://github.com/Art-byte/QuarkusDeveloper/blob/main/quarkus-simple-mongo/src/main/images/Update.jpg)





