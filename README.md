# AlumnosCurso-many-to-many

Aplicación desarrollada con Spring Boot para gestionar la relación muchos a muchos (Many-to-Many) entre alumnos y cursos utilizando JPA y Hibernate.
El proyecto sirve como ejemplo práctico de modelado de entidades y persistencia de datos en una base de datos relacional.

## Tecnologías utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven

## Funcionalidades
- Crear alumnos y cursos
- Asociar alumnos a múltiples cursos
- Gestionar relaciones Many-to-Many
- Persistencia de datos con JPA/Hibernate
- Operaciones CRUD básicas

## Objetivo del proyecto

Practicar el uso de relaciones Many-to-Many en aplicaciones Java con Spring Boot, aplicando buenas prácticas de persistencia y modelado de datos.

## Lo importante: 
Un lado “manda”

En JPA, aunque la relación sea en ambos sentidos, uno de los dos lados debe ser el propietario de la relación.

Eso significa:

uno define la tabla intermedia y sus columnas
el otro solo dice “yo estoy relacionado con eso”
