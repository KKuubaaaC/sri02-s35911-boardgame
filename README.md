# BoardGames REST API

Project created for the **Distributed Systems and Service Integration (SRI)** course — exercise 2.

**Author:** s35911

## Description

A REST API application built with **Java Spring Boot**, providing CRUD operations on a **BoardGame** data model. Data is stored in an in-memory **H2** database.

## Data Model

| Field        | Type     | Description               |
|--------------|----------|---------------------------|
| `id`         | Long     | Primary key (auto-generated) |
| `title`      | String   | Game title                |
| `publisher`  | String   | Publisher name            |
| `releaseYear`| Integer  | Year of release           |
| `maxPlayers` | Integer  | Maximum number of players |

## Tech Stack

- Java 21
- Spring Boot 3.5
- Spring Data JPA
- H2 Database (in-memory)
- Lombok
- ModelMapper 3.2.0
- Maven

## Getting Started

```bash
./mvnw spring-boot:run
```

The application starts on port **8080** by default.

H2 console is available at: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:sri-hr`
- Username: `sa`
- Password: *(empty)*

## API Endpoints

| Method   | URL                        | Description             | Success Code   | Error Code |
|----------|----------------------------|-------------------------|----------------|------------|
| `GET`    | `/api/boardgames`          | Get all board games     | 200 OK         | —          |
| `GET`    | `/api/boardgames/{id}`     | Get board game by ID    | 200 OK         | 404        |
| `POST`   | `/api/boardgames`          | Create a new board game | 201 Created    | —          |
| `PUT`    | `/api/boardgames/{id}`     | Update a board game     | 204 No Content | 404        |
| `DELETE` | `/api/boardgames/{id}`     | Delete a board game     | 204 No Content | 404        |

## Example Request (POST)

```json
{
  "title": "Catan",
  "publisher": "Galakta",
  "releaseYear": 1995,
  "maxPlayers": 4
}
```

## Testing

A Postman collection is included for testing:

**`SRI02-Cisek-s35911_postman_collection.json`**

To use it:
1. Open Postman
2. Go to *File → Import*
3. Import the collection file
4. Run the requests in order (1–6)

## Project Structure

```
src/main/java/s35911/mojeapi/
├── MojeApiApplication.java          # Main class + ModelMapper bean
├── ServletInitializer.java
├── dto/
│   └── BoardGameDto.java            # Data Transfer Object
├── model/
│   └── BoardGame.java               # JPA Entity
├── repo/
│   └── BoardGameRepository.java     # Spring Data Repository
└── rest/
    └── BoardGameController.java     # REST Controller (CRUD)
```
