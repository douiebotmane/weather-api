# Weather API
A Spring Boot backend application that retrieves and analyzes current and forecasted weather data using the WeatherBit API.

## Features
- Fetch current weather for a given city
- Fetch 7-day weather forecast summary for a city
- CI/CD configured via GitHub Actions

## Endpoints
| HTTP Method  |                 URL                 |             Description              |
|:------------:|:-----------------------------------:|:------------------------------------:|
|     GET      |  /weather/current?location={city}   |    Get current weather of a city     |
|     GET      |  /weather/forecast?location={city}  | Get 7-day forecast summary for city  |

## Technologies Used
- Java 21
- Spring Boot
- MapStruct
- Spring AOP
- JUnit 5, Mockito
- Maven
- Swagger / OpenAPI
- GitHub Actions (CI/CD)
