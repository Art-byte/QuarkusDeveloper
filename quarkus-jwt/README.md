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

![image text](https://github.com/Art-byte/QuarkusDeveloper/blob/main/quarkus-jwt/src/main/img/login.png)

<br>

![image text](https://github.com/Art-byte/QuarkusDeveloper/blob/main/quarkus-jwt/src/main/img/adminLogin.png)

Dependiendo del usuario con el que este logeado, nuestro token nos dejará tener acceso sólo a las rutas correspondientes a nuestro rol.

<br>

- `http://locallhost:8080/resource/user`

<br>

![image text](https://github.com/Art-byte/QuarkusDeveloper/blob/main/quarkus-jwt/src/main/img/user.png)

<br>

- `http://locallhost:8080/resource/admin`

<br>

![image text](https://github.com/Art-byte/QuarkusDeveloper/blob/main/quarkus-jwt/src/main/img/admin.png)

<br>

- `http://locallhost:8080/resource/user-or-admin`

<br>

![image text](https://github.com/Art-byte/QuarkusDeveloper/blob/main/quarkus-jwt/src/main/img/userOrAdmin.png)

<br>

