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

import com.panopticode.jooq.enums.KindType;
import com.panopticode.jooq.enums.StatusType;
import com.panopticode.jooq.tables.daos.EntityDao;
import com.panopticode.jooq.tables.pojos.Entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.SqlMergeMode.MergeMode.MERGE;

@Sql(value = "/sql/clear_database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class DatabaseIntegrationTest
    extends AbstractIntegrationTestBase
{
    @Autowired
    private EntityDao _entityDao;

    @Test
    @Sql(value = "/sql/create_metadata.sql")
    @SqlMergeMode(MERGE)
    public void testGetAllMetadata_happyPath()
    {
        // When
        final var entities = _entityDao.findAll();

        // Then
        // just checking the titles
        assertThat(entities.stream().map(Entity::name).toList())
                .hasSameElementsAs(List.of(
                        "test directory",
                        "test file.png"));
    }

    @Test
    @Sql(value = "/sql/create_metadata.sql")
    @SqlMergeMode(MERGE)
    public void testGetMetadataById_happyPath()
    {
        // Given
        final var existingId = _entityDao.findAll().getFirst().id();

        // When
        final var entity = _entityDao.findById(existingId);

        // Then
        assertThat(entity)
                .usingRecursiveComparison()
                .ignoringFieldsOfTypes(UUID.class)
                .isEqualTo(new Entity(
                        existingId,
                        null,
                        KindType.directory,
                        null,
                        null,
                        "test directory",
                        LocalDateTime.of(2004, 10, 19, 10, 23, 54),
                        LocalDateTime.of(2012, 1, 23, 16, 11, 3),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        false,
                        StatusType.ready));
    }
}
