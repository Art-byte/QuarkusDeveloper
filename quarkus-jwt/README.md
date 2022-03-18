# quarkus-jwt Project

Proyecto de prueba para presentar el desarrollo de un acceso a recursos por medio de una autenticación por JWT.
<br>

---
# Requisito de sistema
- Maven 3.8.4
- Java Open jdk 11
- GraalVM

---
# Extensiones

- quarkus-resteasy
- quarkus-smallrye-jwt
- quarkus-resteasy-jsonb
---
# Ejecutar proyecto

- Windows cmd

    `mvnw compile quarkus:dev`

- Linux bash

    `./mvnw compile quarkus:dev`

---

# Preview

Para probar la funcionalidad de nuestro proyecto deberemos ingresar primero a la ruta: `http://localhost:8080/auth/login` donde nos autenticaremos con uno de los roles disponibles (user, admin) y así obtendremos nuestro token de acceso.

<br>

Dependiendo del usuario con el que este logeado, nuestro token nos dejará tener acceso sólo a las rutas correspondientes a nuestro rol.

- `http://locallhost:8080/resource/user`

- `http://locallhost:8080/resource/admin`

- `http://locallhost:8080/resource/user-or-admin`