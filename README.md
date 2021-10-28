# Biblioteca

## Pre-requisitos

Levantar la DB 

`docker-compose up`

## Corriendo la app

Para correr el programa, ejecutar:

`./gradlew appRun`

## Implementacion

- Existe una clase para cada objeto del Modelo, esto es: Empleado, Location, Trip, Truck y  User
- Las clases de DAO usan un Singlenton DB que contiene los DAOs de cada objeto, esta clase al crear el Singleton crea el objeto conexión
- De la misma forma existe un DAO para cada una, todos extienden de CRUD. EmpleadoDAO, etc.
- Cada una de estas clases tienen una implementacion usando Jdbc: JdbcDriver, etc.
- Existen una serie de servlets:
    - HomeServlet: que llama al LoginServlet en caso de que no haya una session activa y sino muestra la pagina de inicio index.jsp
    - LoginServlet y LogoutServlet, para las funciones de Login/Logout
    - Servlets por Tipo de operación: Empleados, Truck, etc
- Java server pages (jsp) para login y index y por tipo de operación, ejemplo: Empleado

## Base de datos

- Se esta usando 'mysql' adentro de un docker
- al inicializar el docker se corren los sql's adentro de resource/db
    - 00-biblioteca.sql para crear la base de datos
    - 99-init.sql para llenar con datos basicos

