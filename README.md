**Franchise API**

Requisitos

Java 26

Maven 3.9+

Docker

Docker Compose


**Endpoints**

Crear franquicia

POST /franchises
Request
{
  "name": "McDonalds"
}

Crear sucursal

POST /branches?franchiseId=1
Request
{
  "name": "Sucursal Norte"
}

Crear producto

POST /products?branchId=1
Request
{
  "name": "Hamburguesa",
  "stock": 100
}

Eliminar producto

DELETE /products/1

Actualizar stock

PUT /products/1/stock
Request
{
  "stock": 50
}


Obtener producto con mayor stock por sucursal

GET /franchises/1/top-stock-products

Response

[
  {
    "branchId": 1,
    "branchName": "Sucursal Norte",
    "productId": 10,
    "productName": "Hamburguesa",
    "stock": 100
  }
]



**Microservicio reactivo desarrollado con:**

Java 26

Spring Boot 4

Spring WebFlux

Spring Data R2DBC

MySQL

Docker

Docker Compose


El sistema permite administrar:

Franquicias

Sucursales

Productos

Stock de productos



**Arquitectura** 

Router
→ Handler
→ Service
→ Repository
→ Database

Implementada usando programación reactiva con:

Mono
Flux

**Tecnologías utilizadas**

| Tecnología        | Descripción                 |
| ----------------- | --------------------------- |
| Java 26           | Lenguaje principal          |
| Spring Boot 4     | Framework backend           |
| Spring WebFlux    | Programación reactiva       |
| Spring Data R2DBC | Acceso reactivo a datos     |
| MySQL 8           | Base de datos               |
| Docker            | Contenedores                |
| Docker Compose    | Infraestructura como código |
| Maven             | Gestión de dependencias     |
| JUnit 5           | Testing                     |
| Mockito           | Mocking                     |
| WebTestClient     | Testing reactivo            |



**Configuración por perfiles**

El proyecto utiliza perfiles de Spring Boot:
| Perfil | Descripción          |
| ------ | -------------------- |
| local  | Desarrollo local     |
| docker | Ejecución con Docker |


**Ejecutar localmente**

1. Clonar proyecto:
   
git clone 

2. Compilar:
   
mvn clean package

3. Ejecutar aplicación
   
mvn spring-boot:run


**Ejecutar con Docker Compose**

1. Compilar aplicación
   
mvn clean package


2. Levantar infraestructura
   
docker compose up --build


**Servicios levantados**

| Servicio | Puerto |
| -------- | ------ |
| API      | 8080   |
| MySQL    | 3306   |



**Testing**

Ejecutar todos los tests

mvn test

Ejecutar una clase específica

mvn -Dtest=ProductServiceImplTest test



**Características implementadas**


* Arquitectura reactiva con WebFlux

* CRUD reactivo

* Persistencia reactiva con R2DBC

* Queries complejas usando DatabaseClient

* Router Functions

* Handlers reactivos

* Tests unitarios

* Router tests

* Docker Compose

* Infraestructura como código

* Persistencia de datos con volúmenes Docker










