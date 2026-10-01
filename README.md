# Min Journal – Backend

## Projektets innehåll:

Backend är byggd med Java och Quarkus och ansvarar för användare, journaler och statistik. 

Backend kan:

- Registrera användare
- Logga in användare
- Hasha lösenord med BCrypt
- Spara journalanteckningar
- Hämta journaler för en användare
- Koppla journaler till rätt användare
- Beräkna statistik för ett valt datumintervall

## Teknik

- Java
- Quarkus
- Hibernate ORM / Panache
- H2 Database
- BCrypt
- REST API

### Lösenord och BCrypt

För att lösenord inte ska sparas i klartext används BCrypt.
När en användare registrerar sig hashashas lösenordet innan det sparas i databasen. Vid inloggning jämförs det inskrivna lösenordet med den sparade BCrypt-hashen och på så sätt behöver det riktiga lösenordet aldrig sparas direkt i databasen.

## Databas

Projektet använder H2 som databas.

Databasen innehåller bland annat:

- `user_details` – sparar användare
- `journal_entries` – sparar journalanteckningar

## API

| Metod | Endpoint | Beskrivning |
|---|---|---|
| POST | `/users/register` | Registrerar en användare |
| POST | `/users/login` | Loggar in en användare |
| GET | `/journals` | Hämtar användarens journaler |
| POST | `/journals` | Skapar en ny journal |
| GET | `/statistics` | Hämtar statistik för ett datumintervall |

## Köra lokalt

### 1. Starta backend

```
bash
./mvnw quarkus:dev
````
### 2. Öppna backend

Backend körs på:
````
http://localhost:8080
````

### 3. Databaskonfiguration

Databasen konfigureras i:
````
src/main/resources/application.properties

````

### Relaterad Guide för använda extentioner

- Hibernate ORM ([guide](https://quarkus.io/guides/hibernate-orm)): Object-relational mapping with JPA/Hibernate for relational database access
- Hibernate Validator ([guide](https://quarkus.io/guides/validation)): Bean validation using Hibernate Validator and Jakarta Validation annotations
- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Generate OpenAPI schemas and serve Swagger UI for REST API documentation
- REST Jackson ([guide](https://quarkus.io/guides/rest#json-serialisation)): Jackson serialization support for Quarkus REST. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it
- Hibernate ORM with Panache ([guide](https://quarkus.io/guides/hibernate-orm-panache)): Simplified JPA/Hibernate data access layer with active record and repository patterns
- JDBC Driver - PostgreSQL ([guide](https://quarkus.io/guides/datasource)): Connect to the PostgreSQL database via JDBC

