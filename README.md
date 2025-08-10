# CRUD Spring + Angular

Sistema de gestión de usuarios con backend en Spring Boot y frontend en Angular.

## 🏗️ Arquitectura

### Backend (Spring Boot)
- **Arquitectura Hexagonal** con puertos y adaptadores
- **Base de datos**: PostgreSQL
- **Mapeo**: MapStruct con ObjectMapper
- **Validaciones**: Bean Validation
- **Documentación**: Swagger UI (`/documentacion`)

### Frontend (Angular 20)
- **Framework**: Angular con standalone components
- **Estilos**: TailwindCSS
- **Alertas**: SweetAlert2
- **HTTP Client**: HttpClient

## 📁 Estructura del Proyecto

```
app/
├── back/                 # Spring Boot Backend
│   ├── application/      # Casos de uso y DTOs
│   ├── domain/          # Modelos de dominio
│   └── infrastructure/  # Controladores, persistencia
└── front/               # Angular Frontend
    └── src/app/         # Componentes y servicios
```

## 🚀 Funcionalidades

- ✅ **Crear** usuarios con validaciones
- 📋 **Listar** todos los usuarios
- ✏️ **Actualizar** información de usuarios
- 🗑️ **Eliminar** usuarios con confirmación

## 🛠️ Tecnologías

| Backend | Frontend |
|---------|----------|
| Spring Boot 3.x | Angular 20 |
| PostgreSQL | TailwindCSS |
| Lombok | SweetAlert2 |
| JPA/Hibernate | TypeScript |

## 📝 Modelo de Datos

```typescript
Usuario {
  id: Long
  cedula: Long
  nombre: String
  correo: String
  fechaNacimiento: LocalDate
  edad: Integer (calculada)
}
```

## 🔧 Configuración

### Base de Datos
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/usuarios_bd
spring.datasource.username=postgres
spring.datasource.password=123
```

### API Endpoints
- `GET /usuarios` - Obtener todos los usuarios
- `POST /usuarios` - Crear usuario
- `PUT /usuarios/{cedula}` - Actualizar usuario
- `DELETE /usuarios/{cedula}` - Eliminar usuario

## 🧪 Testing

- **Backend**: JUnit 5 + Mockito
- **Cobertura**: Servicios, adaptadores y manejo de excepciones