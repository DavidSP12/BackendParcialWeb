# Sistema de Gestión de Clínicas - Backend

API REST desarrollada con Spring Boot para la gestión de clínicas y doctores.

## 📋 Descripción

Sistema backend que permite gestionar clínicas y doctores mediante una API REST, implementando operaciones CRUD básicas siguiendo las mejores prácticas de desarrollo con arquitectura en capas.

## 🏗️ Arquitectura

El proyecto sigue una arquitectura en capas bien definida:

```
com.example.parcialwebback.demo
├── config/           # Configuraciones (CORS, ModelMapper)
├── controller/       # Controladores REST
├── dto/             # Data Transfer Objects
├── exception/       # Manejo de excepciones
├── init/            # Inicializador de datos
├── mapper/          # Mappers Entity ↔ DTO
├── model/           # Entidades JPA
├── repository/      # Repositorios JPA
└── service/         # Capa de servicios
    └── impl/        # Implementaciones de servicios
```

## 🛠️ Tecnologías

- **Java 17**
- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **H2 Database** (en memoria)
- **Lombok** (reducción de código boilerplate)
- **ModelMapper** (mapeo Entity ↔ DTO)
- **Spring Validation** (validaciones)
- **Swagger/OpenAPI** (documentación API)

## 📦 Dependencias principales

```xml
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-validation
- h2database
- lombok
- modelmapper
- springdoc-openapi-starter-webmvc-ui
```

## 🚀 Instalación y ejecución

### Prerrequisitos
- Java 17 o superior
- Maven 3.6+

### Pasos

1. **Clonar el repositorio**
```bash
cd demo
```

2. **Compilar el proyecto**
```bash
./mvnw clean install
```

3. **Ejecutar la aplicación**
```bash
./mvnw spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

## 📊 Base de datos H2

- **URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:clinicadb`
- **Usuario**: `sa`
- **Contraseña**: _(vacía)_

## 📖 Documentación API (Swagger)

Una vez iniciada la aplicación, puedes acceder a la documentación interactiva:

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **API Docs (JSON)**: `http://localhost:8080/api-docs`

## 🔌 Endpoints disponibles

### Clínicas

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/clinicas` | Obtener todas las clínicas |
| GET | `/api/clinicas/{id}` | Obtener clínica por ID |
| POST | `/api/clinicas` | Crear nueva clínica |
| PUT | `/api/clinicas/{id}` | Actualizar clínica |
| DELETE | `/api/clinicas/{id}` | Eliminar clínica |
| GET | `/api/clinicas/ciudad/{ciudad}` | Buscar por ciudad |
| GET | `/api/clinicas/buscar?nombre={nombre}` | Buscar por nombre |

### Doctores

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/doctores` | Obtener todos los doctores |
| GET | `/api/doctores/{id}` | Obtener doctor por ID |
| POST | `/api/doctores` | Crear nuevo doctor |
| PUT | `/api/doctores/{id}` | Actualizar doctor |
| DELETE | `/api/doctores/{id}` | Eliminar doctor |
| GET | `/api/doctores/clinica/{clinicaId}` | Buscar por clínica |
| GET | `/api/doctores/especialidad/{especialidad}` | Buscar por especialidad |
| GET | `/api/doctores/buscar?nombre={nombre}` | Buscar por nombre |

## 📝 Ejemplos de uso

### Crear una clínica

```bash
curl -X POST http://localhost:8080/api/clinicas \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Clínica Ejemplo",
    "direccion": "Calle 123",
    "cantidadCamas": 25,
    "telefono": "555-0000",
    "ciudad": "Bogotá"
  }'
```

### Crear un doctor

```bash
curl -X POST http://localhost:8080/api/doctores \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Dr. Ejemplo",
    "especialidad": "Medicina General",
    "clinicaId": 1,
    "email": "doctor@ejemplo.com",
    "telefono": "555-1000",
    "fechaContratacion": "2024-01-15"
  }'
```

## 🗄️ Modelo de datos

### Clínica
- `id` (Long, PK)
- `nombre` (String, obligatorio)
- `direccion` (String, obligatorio)
- `cantidadCamas` (Integer, obligatorio, > 0)
- `telefono` (String, obligatorio)
- `ciudad` (String, obligatorio)
- `fechaCreacion` (LocalDateTime, automático)

### Doctor
- `id` (Long, PK)
- `nombre` (String, obligatorio)
- `especialidad` (String, obligatorio)
- `clinicaId` (Long, FK, obligatorio)
- `email` (String, obligatorio, formato email)
- `telefono` (String, obligatorio)
- `fechaContratacion` (LocalDate, obligatorio)

## 📂 Datos de ejemplo

Al iniciar la aplicación, se cargan automáticamente:
- 3 clínicas de ejemplo
- 5 doctores de ejemplo

## ✅ Validaciones

El sistema incluye validaciones automáticas:
- Campos obligatorios (@NotBlank, @NotNull)
- Formato de email (@Email)
- Valores mínimos (@Min)
- Relaciones entre entidades

## 🔍 Manejo de errores

Respuestas de error estandarizadas con:
- Código de estado HTTP apropiado
- Timestamp
- Mensaje descriptivo
- Detalles de validación (cuando aplique)

Ejemplo:
```json
{
  "timestamp": "2024-11-18T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Clínica no encontrada con id: 999"
}
```

## 👨‍💻 Autor

Proyecto de ejemplo para Parcial Web Backend

## 📄 Licencia

Este proyecto es de uso académico.
