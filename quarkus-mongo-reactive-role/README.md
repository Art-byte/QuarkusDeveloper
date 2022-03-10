# code-with-quarkus Project

Proyecto de prueba para presentar el desarrollo de un servicio rest que permita manejar el control de acceso a ciertas rutas basandose en roles.  Se hace uso de Mongodb y nos estamos conectando de manera local a una colección de datos llamada companys. 

El proyecto cumple con el objetivo inicial de presentar los cuatro verbos iniciales: GET, POST, PUT y DELETE así como también cuenta con una llave publica o privada para el manejo de la autenticación mediante jwt.
<br>

---
# Requisito de sistema

- Maven 3.8.4
- Java Open jdk 11
- GraalVM
- Openssl (Para generar llave pública o privada)

#### Nota: `Puedes usar el perfil de autenticación para generar uno automático`

<br>

---
# Extensiones

- quarkus-resteasy
- quarkus-hibernate-validator
- quarkus-resteasy-jsonb
- quarkus-resteasy-mutiny
- quarkus-smallrye-openapi
- quarkus-smallrye-jwt
- quarkus-elytron-security-properties-file
- de.flapdoodle.embed.mongo
- quarkus-junit5-mockito
- smallrye-mutiny-vertx-web-client
- mapstruct
- mapstruct-processor
- quarkus-junit5-mockito

<br>

---
# Ejecutar proyecto

- Windows cmd

    `mvnw compile quarkus:dev`

    ### Para poder ejecutar el proyecto, es necesario que tengas la llave pública que se encuentra en la ruta: `src/main/resources/META-INF/resources/publicKey.pem`

    <br>

- Linux bash

    `./mvnw compile quarkus:dev`

---

# Ejecutar proyecto con un perfil de autenticación


    QUARKUS_PROFILE=auth mvn compile quarkus:dev

<br>

#### Debe contarse con llave pública y privada para poder manda a llamar el api

<br>

---
# Generar llave pública y privada
Para generar la llave pública y privada haremos uso de la herramienta openssl la cual pueden descargar desde su [página principal](https://slproweb.com/products/Win32OpenSSL.html). Una vez instalado debemos agregar a nuestro path openssl y posterior a ello debemos situarnos en la ruta de nuestro proyecto donde necesitamos que se guarden las llaves. 

Para generarlas ejecutaremos los siguientes comandos 

- Generar la llave: `openssl genrsa -des3 -out private.pem 2048`

- Extraer la llave publica: `openssl rsa -in private.pem -outform PEM -pubout -out public.pem`

#### Recordar que la clave que estamos agregando es 2048, pero puede variar

<br>

---
# Swagger UI

Ruta de acceso: `http://localhost:8080/q/swaggerui/`

Para acceder a la interfaz de usuario de Swagger y generar un JWT válido, use /api/auth cuando el perfil de autenticación esté activado.


Pueden usarse los siguientes roles:

- ROLE_ADMIN -> `Access for all endpoints`
- ROLE_COMPANY_READ -> `Read Access to GET - /api/companies and GET - /api/companies/{id}.`
- ROLE_COMPANY_CREATE -> `Create Access to POST - /api/companies`
- ROLE_COMPANY_SAVE -> `Update Access to PUT - /api/companies`
- ROLE_COMPANY_DELETE -> `Delete Access to DELETE - /api/companies`

### Para generar un JWT, primero debe cerrar sesión en el botón Autorizar.