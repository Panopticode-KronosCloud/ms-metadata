# Java Spring Boot micro-service template

A template code base for a java Spring Boot micro-service, with database versioning, a RESTful API defined and documented
using OpenAPI and a compelling test suite.

## Development resources

### DB migration

This project uses Flyway to handle DB migration, just add a versioned SQL file (e.g. `V2.3__add_my_table.sql`)
and Flyway will take care of applying the changes during startup.

### Re/generate DB entities with jOOQ

The jOOQ generator can be hooked up with Flyway and generate entities based on the diff, but I haven't
investigated that. Right now jOOQ needs to connect to a running DB instance to reverse engineer the
tables. First thing then is to run the Postgres DB on Docker

```shell
./gradlew bootRun
# or
docker-compose up
```

then, in a different terminal,

```shell
rm -rf ./src/main/java/generated/com/panopticode/jooq
./gradlew jooqCodegen
```

### API

The API specification is in the `api` folder and follows the OpenAPI v3 model. Controllers are generate with
the OpenAPI engine. When changing the API spec, regenerate the API entities with

```shell
rm api/dist/*
rm -rf src/main/java/generated/com/panopticode/openapi
./gradlew openApiGenerate
```

#### OpenAPI Contract

- `src/` contains the human-authored OpenAPI source (multi-file).
- `dist/openapi.yaml` is the bundled, published contract.
- The bundled contract is versioned and immutable once released.
- Consumers must depend only on `dist/openapi.yaml`

A consumer of this API is supposed to generate a client using the manifest
`https://raw.githubusercontent.com/Phortran/java-spring-boot-ms-template/refs/tags/vx.y.z/api/dist/openapi.yaml`, taking care
of replacing `x.y.z` with an actual version. **IMPORTANT! Only refer to version tags for stability and reproducibility**.

#### API Docs

They are in the `docs` directory, in HTML format. They can be generated from the API specs with

```shell
rm -rf docs/*
./gradlew generateDocs
```

## Debug

Running the app locally on docker accepts command line arguments passed to the JVM

```shell
gradle bootRun -Dagentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000
```

Be careful to actually use port 8000 as it is exposed in the docker compose file.
You can also uncomment the logging level in `application.properties` to further
investigate into the app runtime.

## Licence

> Copyright (c) 2024-2026 Panopticode.
>
> This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
>
> This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
>
> You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.