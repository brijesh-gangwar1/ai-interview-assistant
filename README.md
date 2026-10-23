# AI Interview Assistant

## Description

AI Interview Assistant is a Java/Spring Boot backend for asking technical interview questions and receiving AI-generated answers from the Google Gemini API. The current implementation exposes a validated REST endpoint, calls Gemini through a configured `WebClient`, and returns the generated answer as JSON.

The project is being developed phase by phase as a backend learning project. The current code is the initial Gemini integration; user accounts, saved conversations, database persistence, authentication, caching, and deployment are future roadmap work, not implemented features.

### Current endpoint

| Method | Endpoint | Description |
| --- | --- | --- |
| `POST` | `/api/v1/ai/ask` | Accepts a question and returns a Gemini-generated answer. |

Example request:

```json
{
  "question": "Explain dependency injection in Spring."
}
```

Example response:

```json
{
  "answer": "..."
}
```

Blank questions are rejected by request validation. The Gemini API key, model, and connection/read timeouts are configured through the `app.gemini` properties in `application.properties`; provide your own API key through configuration and do not commit secrets.

## Technology

- Java 25
- Spring Boot 4.1.1
- Maven
- Spring MVC for the REST API
- Spring WebFlux `WebClient` for the Gemini HTTP call
- Jakarta Bean Validation
- Google Gemini API
- PostgreSQL and Spring Data JPA dependencies are present for planned persistence work
- Spring Boot Actuator for health and metrics endpoints

## Project structure

```text
ai-interview-assistant/
├── src/
│   ├── main/
│   │   ├── java/com/gangwar/ai_interview_assistant/
│   │   │   ├── AiInterviewAssistantApplication.java  # Spring Boot application entry point.
│   │   │   ├── client/
│   │   │   │   ├── GeminiClient.java                 # Sends questions to Gemini and extracts generated text.
│   │   │   │   └── WebClientConfig.java              # Configures the HTTP client used for Gemini requests.
│   │   │   ├── config/
│   │   │   │   ├── GeminiConfig.java                 # Enables scanning for Gemini configuration properties.
│   │   │   │   └── GeminiProperties.java             # Binds Gemini API key, model, and timeout settings.
│   │   │   ├── controller/
│   │   │   │   └── AiController.java                 # Exposes the POST /api/v1/ai/ask endpoint.
│   │   │   ├── dto/
│   │   │   │   ├── AskRequest.java                   # Validated request body containing a question.
│   │   │   │   ├── AskResponse.java                  # API response containing the generated answer.
│   │   │   │   └── GeminiApiResponse.java            # Java records representing Gemini response data.
│   │   │   ├── exception/
│   │   │   │   └── GlobalExceptionHandler.java       # Converts validation and application errors to HTTP responses.
│   │   │   └── service/
│   │   │       └── GeminiService.java                # Coordinates the Gemini client and API response.
│   │   ├── resources/
│   │   │   └── application.properties                # Spring application and integration configuration.
│   │   └── test/
│   │       └── java/com/gangwar/ai_interview_assistant/
│   │           └── AiInterviewAssistantApplicationTests.java # Checks that the Spring application context loads.
├── .gitattributes                 # Git text-file and line-ending settings.
├── .gitignore                     # Excludes generated and local-only files from Git.
├── HELP.md                        # Spring Initializr help/reference file.
├── mvnw                           # Maven Wrapper launcher for Unix-like shells.
├── mvnw.cmd                       # Maven Wrapper launcher for Windows.
├── pom.xml                        # Maven project metadata, dependencies, and build configuration.
├── README.md                      # Project overview, structure, and implementation timeline.
├── README_Spring_Boot_Backend_Roadmap.md # Detailed Spring Boot learning and feature roadmap.
├── SpringBoot_Backend_Roadmap_AI_Interview_Preparation_Assistant.txt # Phase-by-phase project roadmap notes.
└── target/                        # Generated Maven build output (not source code).
```

`.idea/` contains local IDE metadata.

## Implementation timeline

The dates below reflect the Git history available in this repository. Phase 0 was implemented between the initial project work on **October 19, 2026** and the Phase 0 completion commit on **October 23, 2026**. Future phases are ordered below; target dates have not yet been assigned, so they are intentionally not estimated here.

| Phase | Status / when | Implementation |
| --- | --- | --- |
| 0 — Backend foundation and Gemini integration | **COMPLETED** | Set up the Spring Boot application, validated ask endpoint, Gemini configuration and HTTP client, service layer, response DTO, and global error handling. |
| 1 — Spring Core | Planned — after Phase 0 | Study and strengthen IoC, dependency injection, bean lifecycle, and component wiring. |
| 2 — Spring Boot fundamentals and configuration | Planned — after Phase 1 | Add profiles, externalized configuration, and a clearer understanding of auto-configuration and startup. |
| 3 — REST API design, validation, and exception handling | Planned — after Phase 2 | Standardize API contracts, validation errors, and REST error responses. |
| 4 — Spring Data JPA and PostgreSQL persistence | Planned — after Phase 3 | Add persistent users, conversations, and messages using PostgreSQL and JPA. |
| 5 — Advanced JPA and query optimization | Planned — after Phase 4 | Add history search/pagination and address fetch strategies and query performance. |
| 6 — Transactions and data consistency | Planned — after Phase 5 | Define and test transaction boundaries and consistency behavior. |
| 7 — Spring Security and JWT | Planned — after Phase 6 | Add registration, authentication, authorization, and user ownership checks. |
| 8 — AOP and cross-cutting concerns | Planned — after Phase 7 | Apply AOP concepts to suitable cross-cutting behavior such as auditing or logging. |
| 9 — Redis caching | Planned — after Phase 8 | Add cache-aside behavior for selected reusable answers, including expiry and invalidation. |
| 10 — Async processing and scheduling | Planned — after Phase 9 | Introduce asynchronous work or scheduled tasks where they fit the application. |
| 11 — Monitoring and observability | Planned — after Phase 10 | Expand health, metrics, and operational visibility for the application. |
| 12 — Production readiness and resilience | Planned — after Phase 11 | Add appropriate timeouts, bounded retries, resilience, deployment configuration, and final operational checks. |

Each phase should leave the application runnable and include relevant tests before the next phase begins. See [README_Spring_Boot_Backend_Roadmap.md](README_Spring_Boot_Backend_Roadmap.md) for the detailed learning objectives and phase checklist.

## Running locally

1. Install JDK 25.
2. Configure a valid Gemini API key and the required application settings.
3. From the repository root, start the application with the Maven Wrapper:

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

4. Send a `POST` request to `http://localhost:8080/api/v1/ai/ask` with a JSON body containing a non-blank `question`.

Run the test suite with:

```powershell
.\mvnw.cmd test
```

## API documentation

Start the application locally, then open either documentation link in your browser:

- [Swagger UI](http://localhost:8080/swagger-ui/index.html) — browse and try the available endpoints.
- [OpenAPI JSON](http://localhost:8080/v3/api-docs) — view or download the generated API specification.

These links are available while the application is running on the default port, `8080`.
