# where-to-live

REST API that compares cities by cost-of-living and salary, so you can make a data-driven call on where to relocate.

> **Portfolio project, 2017–2020.** Kept on GitHub as a snapshot of earlier backend work. Not actively maintained.
> Frontend counterpart: [vaspoz/where-to-live-ui](https://github.com/vaspoz/where-to-live-ui).

---

## What it does

Given a base city (where you live now) and one or more target cities, the service returns a normalized comparison across ~53 cost-of-living categories plus average net salary. The frontend uses this to render side-by-side city scorecards and "should I move?" rankings.

The dataset is a per-city snapshot of consumer-price and salary indicators (Numbeo-style schema: rent, groceries, transport, utilities, restaurants, average net salary, etc.), seeded into the database via Flyway migrations on first boot.

## Tech stack

- **Java 8**, **Spring Boot 1.5** (web, data-jpa, data-rest, jersey, security, websocket)
- **Flyway** for schema migrations and dataset seeding
- **H2** (file-based) for dev, **PostgreSQL** for the `prod` Maven profile
- **JWT** auth (jjwt) + Spring Security; optional Facebook social login
- **JSoup** retained for HTML scraping of source datasets
- **Maven** build, packaged as WAR (`aliyah.war`), deployable to Tomcat
- **Jenkins** pipeline (`Jenkinsfile`) — `mvn clean install -Pprod` then `mvn tomcat7:deploy`

## API

Public endpoints exposed by `CompareController`:

| Method | Path | Purpose |
| --- | --- | --- |
| GET | `/countries` | List all countries in the dataset |
| GET | `/cities/by/{country}` | List cities for a given country |
| GET | `/compare/{baseCountry}/{baseCity}/with/{countryToCompare}` | Compare base city against every city in one country |
| GET | `/compare/{baseCountry}/{baseCity}/with-all/{countriesToCompare}` | Compare against multiple countries |
| GET | `/ping` | Health check |

Auth (`JwtAuthenticationController`):

| Method | Path | Purpose |
| --- | --- | --- |
| POST | `/login` | Issue JWT |
| POST | `/signup` | Register user |

Admin (`AdministratorController`, JWT-protected, under `/admin`): user count and log tail endpoints.

Each comparison response carries, per target city, relative ratios for **overall expenses**, **rent**, and **average net salary**, expressed as a percentage of the base city. Numbers above 100 mean "more expensive / higher salary than your current city."

## Engineering notes

- **Stateless JWT auth** behind a `JwtRequestFilter`, so the API stays horizontally scalable. CORS is opened explicitly for the React UI on `localhost:3000`.
- **Profile-driven persistence**: same code path runs on H2 locally and Postgres in `prod`. Schema is owned by Flyway, not Hibernate (`ddl-auto=false`), to keep prod migrations deterministic.
- **Dataset bootstrap as a side-effect of `mvn test`**: the test run populates the local H2 file (`~/compare_countries.mv.db`) so a fresh checkout has working data without a separate ETL step. Convenient for a portfolio repo; in a current project I would split this into an explicit seeding command.
- **Comparison service** normalizes ~53 raw indicators into three roll-ups (expenses, rent, salary) and computes relative ratios against the base city, so the frontend stays dumb.

## Running locally

Requires JDK 8 and Maven 3.

```bash
# 1. Build and populate the local H2 dataset
mvn test

# 2. Run the API (default dev profile, H2)
mvn spring-boot:run

# Optional: attach a remote debugger on port 5005
mvn spring-boot:run -Drun.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
```

API is then on `http://localhost:8080`. The H2 console is at `/h2`.

Build a deployable WAR against the prod (PostgreSQL) profile:

```bash
mvn clean install -Pprod
```

Smoke test:

```bash
curl http://localhost:8080/countries
curl http://localhost:8080/compare/Poland/Gdansk/with/Netherlands
```

## Project layout

```
src/main/java/ru/vaspoz/relo/
  WhereToLiveApplication.java      Spring Boot entrypoint
  WebSecurityConfig.java           Spring Security + JWT filter wiring
  controllers/                     CompareController, Jwt..., Administrator...
  services/                        Comparison + response assembly + logging
  repository/                      Spring Data JPA repositories
  model/                           JPA entities + response DTOs
  auth/                            JWT utilities, filter, user details
src/main/resources/db/migration/h2/   Flyway: schema + seed data
```

## Status

Last meaningful work was in **2020**. Spring Boot 1.5 and Java 8 are both well past end-of-life. If I were starting this today I would be on Spring Boot 3.x / Java 21, swap the WAR + Tomcat deploy for a containerized JAR, replace the reflection-heavy `VALUE_01..53` columns with a normalized schema or a typed JSON column, and front the whole thing with a real CI/CD pipeline on AWS or Azure instead of a single Jenkins job.

I keep the repo public because the bones are still useful as a portfolio sample: clean REST contract, stateless JWT auth, Flyway-owned schema, profile-driven dev/prod split, and an end-to-end Maven to Jenkins to Tomcat path.

## Author

**Vasilii (Basil) Pozdeev** — Senior Cloud Architect / DevOps freelancer, Breda, Netherlands.
Current focus: Kubernetes, AWS/Azure, and internal developer platforms.
