# Módulo de Usuarios – Jobify (Java Servlets)

Este módulo hace parte de las pruebas del sistema **Jobify**. El objetivo del módulo es gestionar el CRUD de usuarios (crear, listar, editar y eliminar) usando **Java, Servlets, JSP y MySQL** desplegado en **Apache Tomcat**.

## Tecnologías utilizadas

- **Java 21**
- **Servlets (Jakarta EE)**
- **JSP (JavaServer Pages)**
- **MySQL**
- **Tomcat 9.0**
- **Maven**
- **HTML5, CSS3, Bootstrap**
- **.env (manejo de credenciales seguras)**

---

## Configuración Inicial

1. **Base de datos:**

Crea una base de datos en MySQL con el nombre:

```sql
CREATE DATABASE jobify_db;
```

Luego importa el archivo database.sql incluido en el repositorio o crea las tablas manualmente.

2. Archivo .env:

Crea un archivo .env en la raíz y en la carpeta resources, con las siguientes variables:

DB_HOST=localhost
DB_PORT=3306
DB_NAME=jobify_db
DB_USER=root
DB_PASSWORD=TU_CONTRASEÑA


---

## Instrucciones de uso

1. Clona este repositorio:

```bash
git clone "https://github.com/iGabe23/users-module-java.git"
```

2. Compila el proyecto con Maven:

```bash
mvn clean package
```

3. Copia el archivo .war generado en target/ hacia el directorio webapps/ de tu instalación de Tomcat 9.

4. Inicia Tomcat doble click en el startup.bat dentro del directorio bin de Tomcat.

5. Accede en tu navegador:
"http://localhost:8080/users-java-servlet/"

---


## Funcionalidades del módulo

- Ver listado de usuarios
- Crear nuevos usuarios
- Editar usuarios existentes
- Eliminar usuarios
- Validación de rutas y parámetros