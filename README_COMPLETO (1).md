Este proyecto implementa un sistema profesional de comprobación automática de entregas de ejercicios en GitHub para alumnado de Desarrollo de Aplicaciones Web.

Incluye:
- MVC con Spring Boot 3.x
- Plantillas Thymeleaf
- API REST para consultar estados de ramas de GitHub
- Integración con PostgreSQL
- Cacheo de resultados para eficiencia
- Despliegue automático en Render.com mediante `render.yaml`
- Arquitectura modular: controller, service, repository, model, config, util
- Validación de datos (Jakarta Validation)

El objetivo es ofrecer una herramienta escalable, segura y mantenible para docentes que necesiten automatizar la corrección de proyectos basados en Git y GitHub.

# Instalación y Configuración de PostgreSQL en Linux  
Guía rápida para instalar PostgreSQL en Ubuntu/Debian, crear usuarios y bases de datos, y conectarlo con Spring Boot y DBeaver.

---

## ✅ 1. Instalar PostgreSQL

En distribuciones basadas en Debian/Ubuntu:

```bash
sudo apt update
sudo apt install postgresql postgresql-contrib
```

Esto instalará:
- El servidor PostgreSQL  
- Herramientas administrativas  
- Extensiones útiles  

PostgreSQL se inicia automáticamente tras la instalación.

---

## ✅ 2. Verificar el servicio

```bash
sudo systemctl status postgresql
```

Para arrancarlo manualmente:

```bash
sudo systemctl start postgresql
```

Para habilitarlo en cada arranque:

```bash
sudo systemctl enable postgresql
```

---

## ✅ 3. Acceder a PostgreSQL

PostgreSQL crea un usuario del sistema llamado `postgres`.

Entrar en la consola SQL:

```bash
sudo -u postgres psql
```

Verás el prompt:

```
postgres=#
```

---

## ✅ 4. Crear usuario y base de datos

Dentro de `psql`:

### Crear usuario:
```sql
CREATE USER miusuario WITH PASSWORD '1234';
```

### Dar permisos:
```sql
ALTER USER miusuario CREATEDB;
```

### Crear base de datos:
```sql
CREATE DATABASE springbootapp OWNER miusuario;
```

Salir:
```sql
\q
```

---

## ✅ 5. Cambiar contraseña del usuario postgres (opcional)

```bash
sudo -u postgres psql
```

```sql
ALTER USER postgres PASSWORD 'mi_clave_segura';
\q
```

---

## ✅ 6. Comprobar conexión desde terminal

```bash
psql -h localhost -U miusuario -d springbootapp
```

---

## 🧩 7. Configurar Spring Boot (application.properties)

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/springbootapp
spring.datasource.username=miusuario
spring.datasource.password=1234

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

---

## 🔍 8. Conectar PostgreSQL con DBeaver

1. Abrir DBeaver  
2. Seleccionar **New Database Connection**  
3. Elegir **PostgreSQL**  
4. Completar:

   - Host: `localhost`  
   - Port: `5432`  
   - Database: `springbootapp`  
   - User: `miusuario`  
   - Password: `1234`

5. Test Connection → Finish

---
