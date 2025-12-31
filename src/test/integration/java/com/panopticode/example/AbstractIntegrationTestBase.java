/*
 * metadata-microservice
 * Copyright (c) 2024-2026 Panopticode.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.panopticode.example;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
public abstract class AbstractIntegrationTestBase
{
    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER =
            new PostgreSQLContainer<>("postgres:17.4");

    @BeforeAll
    public static void testFlyway_migration()
    {
        // Create the Flyway instance and point it to the database
        final var flyway = Flyway.configure().dataSource(
                POSTGRE_SQL_CONTAINER.getJdbcUrl(),
                POSTGRE_SQL_CONTAINER.getUsername(),
                POSTGRE_SQL_CONTAINER.getPassword()).load();

        // Start the migration
        flyway.migrate();
    }
}
