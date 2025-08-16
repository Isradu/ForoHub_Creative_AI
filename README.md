# 🗣️ ForoHub - API REST para Foros de IA Creativa

<img width="830" height="732" alt="Captura de Pantalla 2025-08-13 a la(s) 7 33 45 p m" src="https://github.com/user-attachments/assets/94a4aaee-3b06-46df-94ff-d1c64fd3cb0a" />

*Imagen 1: Ejemplo de la API en funcionamiento.*

---

## 🚀 Insignias
![Estado del Proyecto](https://img.shields.io/badge/STATUS-EN%20DESARROLLO-green)  ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=springboot)  ![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)  ![MySQL](https://img.shields.io/badge/MySQL-8-blue?logo=mysql) ![Maven](https://img.shields.io/badge/Maven-3.9+-blue?logo=apachemaven)   
![Spring Security](https://img.shields.io/badge/Spring%20Security-Enabled-darkgreen?logo=springsecurity)
![JWT](https://img.shields.io/badge/JWT-Seguridad%20Token-yellow?logo=jsonwebtokens)
![Flyway](https://img.shields.io/badge/Flyway-Migraciones-red?logo=flyway)
![Lombok](https://img.shields.io/badge/Lombok-Annotations-lightgrey?logo=java)
![Insomnia](https://img.shields.io/badge/Tested%20with-Insomnia-purple?logo=insomnia)
![Postman](https://img.shields.io/badge/Tested%20with-Postman-orange?logo=postman)
---

## 📖 Índice
- [Descripción del Proyecto](#-descripción-del-proyecto)
- [Estado del Proyecto](#-estado-del-proyecto)
- [Arquitectura del Proyecto](#️-arquitectura-del-proyecto)
- [Funcionalidades de la API](#-funcionalidades-de-la-api)
- [Acceso y Configuración Local](#️-acceso-y-configuración-local)
- [Prueba de la API con Insomnia](#-prueba-de-la-api-con-insomnia)
- [Futuras Funcionalidades](#️-futuras-funcionalidades)
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Guía Visual de la API](#-guía-visual-de-la-api)
- [Desarrolladores del Proyecto](#-desarrolladores-del-proyecto)
- [Licencia](#-licencia)

---

## 📝 Descripción del Proyecto
**ForoHub** es una API RESTful construida con **Spring Boot** que simula la gestión de un foro de discusión sobre cursos y herramientas de **Inteligencia Artificial creativa**.  

La API permite a los usuarios interactuar con tópicos y usuarios, ofreciendo **autenticación segura mediante JSON Web Tokens (JWT)**.  

Diseñada con buenas prácticas:
- **Spring Data JPA** → persistencia con MySQL.  
- **Flyway** → migraciones automáticas de base de datos.  
- **Arquitectura MVC** → controladores, servicios y repositorios bien organizados.  

---

## 🚧 Estado del Proyecto
✅ **En desarrollo (MVP básico funcionando)**  
Actualmente cuenta con:  
- CRUD de **Tópicos**.  
- Sistema de **Autenticación JWT**.  

Próximas fases: añadir **respuestas, usuarios con roles y categorización por cursos**.

---

## 🏛️ Arquitectura del Proyecto
La arquitectura sigue el patrón **MVC (Modelo-Vista-Controlador):**
- **Controladores** → reciben solicitudes HTTP y las delegan a los servicios.  
- **Servicios** → contienen la lógica de negocio.  
- **Entidades y Repositorios** → mapean tablas de la base de datos e interactúan con la persistencia.  
- **Configuración de Seguridad** → gestiona autenticación/autorización mediante JWT.  
- **Migraciones con Flyway** → crean y actualizan automáticamente el esquema de base de datos.

---

## 💡 Funcionalidades de la API
### 🔐 Autenticación y Seguridad
- **Login de Usuarios** → `POST /login` devuelve token JWT.  
- **Protección de Endpoints** → requieren `Authorization: Bearer <TOKEN>`.  

### 📚 CRUD de Tópicos
- `POST /topicos` → crear nuevo tópico.  
- `GET /topicos` → listar todos los tópicos.  
- `GET /topicos/{id}` → obtener un tópico por ID.  
- `PUT /topicos/{id}` → actualizar tópico existente.  
- `DELETE /topicos/{id}` → eliminar tópico.  

---

## ⚙️ Acceso y Configuración Local
### Requisitos Previos
Tener instalado:
- **Java JDK 17+**  
- **Maven**  
- **MySQL Server 8.0+**

### 📂 Instalación
```bash
# Clonar el repositorio
git clone https://github.com/Isradu/forohub.git
cd forohub
```

### 🗄️ Configuración de la Base de Datos

1. Crear una base de datos en MySQL (ej: `forohub_db`).

```sql
-- sql

CREATE DATABASE forohub_db;
```
   
3. Copiar el archivo:
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```
3. Configurar credenciales de MySQL y clave secreta para JWT en `application.properties`.

4. Flyway ejecutará migraciones automáticamente al iniciar (`V1__crear_tabla_topicos.sql`, `V2__crear_tabla_usuarios.sql`).

### ▶️ Ejecución

Desde IDE: ejecutar `ForohubApplication.java`.

Desde terminal:
```bash
./mvnw spring-boot:run
```

## 🧪 Prueba de la API con Insomnia

### Paso 1: Generar contraseña encriptada

```java
// java

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "123456";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }
}
```
Insertar en base de datos:
```sql
--sql

INSERT INTO usuarios (nombre, email, contrasena) 
VALUES ('Test User', 'test@example.com', 'PEGA_AQUI_EL_HASH');
```
### Paso 2: Obtener Token JWT
POST → `http://localhost:8080/login`

```json
json

{
  "email": "test@example.com",
  "contrasena": "123456"
}
```
### Paso 3: Acceso a Endpoint Protegido

GET → `http://localhost:8080/topicos`

Encabezado:

```makefile
Authorization: Bearer <TOKEN_JWT>
```
### Paso 4: Probar CRUD

Con el mismo token probar `POST`, `PUT`, `DELETE` en `/topicos`.

## ➡️ Futuras Funcionalidades

- CRUD de Respuestas a tópicos.

- Perfiles de Usuario públicos.

- Categorización por Cursos.

- Roles de Usuario (`ROLE_ADMIN`, `ROLE_USER`).

## 🛠️ Tecnologías Utilizadas

- Java 17

- Spring Boot 3.x

- Spring Data JPA

- Spring Security

- JWT (Auth0 Java JWT)

- Maven

- MySQL

- Flyway

- Lombok

- Insomnia (Para pruebas)

## 📸 Guía Visual de la API

A continuación se incluyen ejemplos de imágenes (próximamente).


`Imagen 2:` Ejemplo de autenticación exitosa en Insomnia.


`Imagen 3:` Ejemplo de respuesta del endpoint GET /topicos.


`Imagen 4:` Ejemplo de creación de un nuevo tópico.

## 👥 Desarrolladores del Proyecto

- **[Israel Durán](https://github.com/Isradu)**

## 📜 Licencia
Este proyecto está bajo la Licencia MIT. Para más detalles, consulta el archivo [LICENSE.md](LICENSE.md).
