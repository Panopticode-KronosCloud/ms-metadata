# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project overview

Spring Boot 3 microservice (Java 21) that stores and serves structured metadata about files and directories, independent of binary storage. Uses PostgreSQL, jOOQ for type-safe SQL, Flyway for migrations, and an OpenAPI-first approach for the REST API.

## Common commands

```bash
# Build (runs unit + integration tests + static analysis + OpenAPI contract check)
./gradlew build

# Run only unit tests
./gradlew test

# Run only integration tests (spins up Postgres via Testcontainers)
./gradlew integrationTest

# Run a single test
./gradlew test --tests "com.panopticode.metadata.utils.ValidatorTest.testSomeMethod_variant"

# Static analysis only
./gradlew checkstyleMain spotbugsMain

# Run locally (requires DB env vars set, or use docker compose)
./gradlew bootRun
docker compose up   # runs app + postgres + adminer (DB browser on :8080)
```

### Code generation (run after schema or API changes)

```bash
# Regenerate jOOQ DB entities (requires running Postgres first)
rm -rf ./src/main/java/generated/com/panopticode/jooq
./gradlew jooqCodegen

# Regenerate OpenAPI Spring controllers/models
rm api/dist/*
rm -rf src/main/java/generated/com/panopticode/openapi
./gradlew openApiGenerate   # also bundles the API spec

# Bundle multi-file OpenAPI spec into dist/ (must be committed)
./gradlew bundleApiSpecs

# Generate HTML API docs
./gradlew generateDocs
```

### Required environment variables

| Variable | Used for |
|---|---|
| `DB_URL` | App datasource (e.g. `jdbc:postgresql://localhost:5432/metadata_microservice_db`) |
| `DB_APP_USERNAME` / `DB_APP_PASSWORD` | App DB user |
| `DB_OWNER_USERNAME` / `DB_OWNER_PASSWORD` | Flyway migrations and jOOQ codegen |
| `GITHUB_ACTOR` / `GITHUB_TOKEN` | Resolving the version catalog from GitHub Packages |

`GITHUB_ACTOR`/`GITHUB_TOKEN` can be replaced with `gpr_user`/`gpr_key` in `gradle.properties` for local dev.

## Architecture

### Request flow

```
HTTP request
  → OpenAPI-generated MetadataApiController
  → MetadataController (implements MetadataApiDelegate)  [controller/]
  → MetadataService / MetadataServiceImpl                [service/impl/]
  → EntityExtDao (extends jOOQ-generated EntityDao)      [dao/]
  → PostgreSQL (metadata schema)
```

`EntityMapper` (MapStruct) handles all conversions between layers:
- `CreateMetadataRequest` (OpenAPI model) → `EntityMetadata` (internal record) → `Entity` (jOOQ POJO)
- `Entity`/`StagedEntityView`/`ActiveEntityView` → `Metadata` (OpenAPI model)

### Generated code

All generated code lives under `src/main/java/generated/` and is committed:

- `com.panopticode.jooq.*` — jOOQ tables, records, DAOs reverse-engineered from the live DB
- `com.panopticode.openapi.*` — Spring controllers and model classes from the OpenAPI spec

Never edit generated files directly. Regenerate them from their sources.

### Entity lifecycle

Entities progress through statuses: `staged` → `active` | `deleted` | `unavailable`.

A freshly created entity is always `staged`. Consolidation (adding storage reference, hashes, thumbnails) transitions it to `active`.

### Database schema

Tables all live in the `metadata` PostgreSQL schema:

- `entity` — files and directories as a self-referential tree (`parent_id` → `entity.id`)
- `entity_hash` — checksum records per entity
- `entity_storage` — blob storage reference (B2, S3, disk, etc.)
- `entity_thumbnail` — thumbnail dimensions per entity
- `entity_consolidation` — consolidation version and timestamp

### OpenAPI spec

The spec is split across files in `api/src/` and bundled into `api/dist/openapi.yaml`. **The bundled file must always be committed and kept in sync with sources** — CI enforces this with a diff check. Always run `./gradlew bundleApiSpecs` (or `openApiGenerate`, which calls it) before committing API changes.

### Multi-module Gradle

- Root project: main app
- `custom-checks`: custom Checkstyle rule (`TestMethodName`) used by the root project
- `test-common`: shared test utilities (e.g. `AssertUtils`, `EnablePostgresTestContainer`)

### DB migrations

Add a new Flyway migration file to `src/main/resources/db/migration/` using the naming pattern `V<major>.<minor>__description.sql`. Flyway applies migrations automatically on startup.

## Coding conventions (enforced by Checkstyle)

### Naming

- Instance fields: `_camelCase` (leading underscore)
- Private methods: `_camelCase` (leading underscore)
- Public/protected methods: `camelCase` (no underscore)
- Test methods: `testSubject_variant` — e.g. `testFetchById_notFound`

### Style rules worth knowing

- Opening brace (`{`) goes on a **new line** for all block statements and class/method declarations
- Use `var` for all local variable declarations where the type is visible from the right-hand side
- Private methods must be declared **after** public methods in the same class
- No `System.out`/`System.err` — use SLF4J (`log.info(...)`)
- Max line length 160 chars, spaces (no tabs)
- All Java files must start with the GPL license header (see `config/checkstyle/java.header`)

### Import order (CustomImportOrder)

1. `com.panopticode.*` (project-internal)
2. Third-party packages
3. `java.*` / `javax.*`
4. Static imports

Each group separated by a blank line.

### Exception hierarchy

All application exceptions extend `BaseAppRuntimeException`. Subclass for specific domains (`DataLayerException`, `DuplicateEntryException`, `EntityNotFoundException`, `ParentNotFoundException`, `ValidationException`). `RestResponseEntityExceptionHandler` maps each to an HTTP status code.

## Testing

- **Unit tests**: `src/test/unit/java/` — standard JUnit 5 + Mockito, no DB
- **Integration tests**: `src/test/integration/java/` — extend `AbstractIntegrationTestBase`, which uses Testcontainers to spin up a real Postgres instance
- Use `@Sql("/sql/setup.sql")` to load fixtures and `@Sql(value="/sql/clear_database.sql", executionPhase=AFTER_TEST_METHOD)` to clean up (already applied at class level in `AbstractIntegrationTestBase` subclasses)
- JaCoCo enforces a minimum **85% coverage** threshold (excluding generated code)

## Debugging locally

```bash
# Enable JVM remote debug on port 8000 (exposed in compose.yaml)
gradle bootRun -Dagentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000

# Enable verbose SQL logging — uncomment in application.properties:
# logging.level.root=TRACE
```
