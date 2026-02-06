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

package com.panopticode.metadata;

import com.panopticode.jooq.enums.KindType;
import com.panopticode.metadata.dao.EntityExtDao;
import com.panopticode.metadata.exception.DuplicateEntryException;
import com.panopticode.metadata.exception.ParentNotFoundException;
import com.panopticode.metadata.model.EntityMetadata;
import com.panopticode.metadata.model.Kind;
import com.panopticode.metadata.model.Status;
import com.panopticode.metadata.service.MetadataService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.TestInstantiationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.panopticode.metadata.test.utils.AssertUtils.assertThrowsWithMessage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.SqlMergeMode.MergeMode.MERGE;

@Sql(value = "/sql/clear_database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MetadataServiceIntegrationTest
        extends AbstractIntegrationTestBase
{
    @Autowired
    private MetadataService _metadataService;

    @Autowired
    private EntityExtDao _entityDao;

    @Test
    void testCreateEntityMetadata_createFile()
    {
        // Given
        final var entity = _makeFile(null);

        // When
        final var staged = _metadataService.createEntityMetadata(entity);

        // Then
        assertThat(staged).isNotNull();
        assertThat(staged.id()).isNotNull();
        assertThat(staged.parentId()).isNull();
        assertThat(staged.name()).isEqualTo("test.txt");
        assertThat(staged.extension()).isEqualTo("txt");
        assertThat(staged.kind()).isEqualTo(Kind.FILE);
        assertThat(staged.created()).isEqualTo(entity.created());
        assertThat(staged.lastModified()).isEqualTo(entity.lastModified());
        assertThat(staged.customMetadata()).isNull();
        assertThat(staged.rawAccess()).isEqualTo(false);
        assertThat(staged.status()).isEqualTo(Status.STAGED);
    }

    @Test
    void testCreateEntityMetadata_createFolder()
    {
        // Given
        final var entity = _makeFolder(null);

        // When
        final var staged = _metadataService.createEntityMetadata(entity);

        // Then
        assertThat(staged).isNotNull();
        assertThat(staged.id()).isNotNull();
        assertThat(staged.parentId()).isNull();
        assertThat(staged.name()).isEqualTo("test");
        assertThat(staged.extension()).isEqualTo(null);
        assertThat(staged.kind()).isEqualTo(Kind.DIRECTORY);
        assertThat(staged.created()).isEqualTo(entity.created());
        assertThat(staged.lastModified()).isEqualTo(entity.lastModified());
        assertThat(staged.customMetadata()).isNull();
        assertThat(staged.rawAccess()).isEqualTo(false);
        assertThat(staged.status()).isEqualTo(Status.STAGED);
    }

    @Test
    @Sql(value = "/sql/create_metadata.sql")
    @SqlMergeMode(MERGE)
    public void testCreateEntityMetadata_createFileInFolder()
    {
        // Given
        final var parentId = _getFirstDirectoryId();
        final var entity = _makeFile(parentId);

        // When
        final var staged = _metadataService.createEntityMetadata(entity);

        // Then
        assertThat(staged).isNotNull();
        assertThat(staged.id()).isNotNull();
        assertThat(staged.parentId()).isEqualTo(parentId);
        assertThat(staged.name()).isEqualTo("test.txt");
        assertThat(staged.extension()).isEqualTo("txt");
        assertThat(staged.kind()).isEqualTo(Kind.FILE);
        assertThat(staged.created()).isEqualTo(entity.created());
        assertThat(staged.lastModified()).isEqualTo(entity.lastModified());
        assertThat(staged.customMetadata()).isNull();
        assertThat(staged.rawAccess()).isEqualTo(false);
        assertThat(staged.status()).isEqualTo(Status.STAGED);
    }

    @Test
    @Sql(value = "/sql/create_metadata.sql")
    @SqlMergeMode(MERGE)
    public void testCreateEntityMetadata_createSubFolder()
    {
        // Given
        final var parentId = _getFirstDirectoryId();
        final var entity = _makeFolder(parentId);

        // When
        final var staged = _metadataService.createEntityMetadata(entity);

        // Then
        assertThat(staged).isNotNull();
        assertThat(staged.id()).isNotNull();
        assertThat(staged.parentId()).isEqualTo(parentId);
        assertThat(staged.name()).isEqualTo("test");
        assertThat(staged.extension()).isEqualTo(null);
        assertThat(staged.kind()).isEqualTo(Kind.DIRECTORY);
        assertThat(staged.created()).isEqualTo(entity.created());
        assertThat(staged.lastModified()).isEqualTo(entity.lastModified());
        assertThat(staged.customMetadata()).isNull();
        assertThat(staged.rawAccess()).isEqualTo(false);
        assertThat(staged.status()).isEqualTo(Status.STAGED);
    }

    @Test
    public void testCreateEntityMetadata_createFileInFolderFolderNotFound()
    {
        // Given
        final var parentId = UUID.randomUUID();
        final var entity = _makeFile(parentId);

        // Then
        assertThrowsWithMessage(ParentNotFoundException.class,
                () -> _metadataService.createEntityMetadata(entity),
                String.format("Parent node with id %s was not found, or is not a folder", parentId));
    }

    @Test
    @Sql(value = "/sql/create_simple_folder_structure.sql")
    @SqlMergeMode(MERGE)
    public void testCreateEntityMetadata_createFileInFolderParentIsNotFolder()
    {
        // Given
        final var parentId = "11111111-1111-1111-1111-111111111111";
        final var entity = _makeFile(UUID.fromString(parentId));

        // Then
        assertThrowsWithMessage(
                ParentNotFoundException.class,
                () -> _metadataService.createEntityMetadata(entity),
                String.format("Parent node with id %s was not found, or is not a folder", parentId));
    }

    @Test
    @Sql(value = "/sql/create_simple_folder_structure.sql")
    @SqlMergeMode(MERGE)
    public void testCreateEntityMetadata_createFileInFolderNameAlreadyExists()
    {
        // Given
        final var parentId = UUID.fromString("22222222-2222-2222-2222-222222222222");
        final var entity = _makeEntity(parentId, Kind.FILE, "panorama.tiff", "tiff");

        // Then
        assertThrowsWithMessage(DuplicateEntryException.class,
                () -> _metadataService.createEntityMetadata(entity),
                String.format("Entry named '%s' already exists in parent '%s'",
                        entity.name(),
                        entity.parentId() == null ? "root" : entity.parentId()));
    }

    @Test
    @Sql(value = "/sql/create_simple_folder_structure.sql")
    @SqlMergeMode(MERGE)
    public void testCreateEntityMetadata_createFolderInFolderNameAlreadyExists()
    {
        // Given
        final var parentId = UUID.fromString("eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee");
        final var entity = _makeEntity(parentId, Kind.DIRECTORY, "Cici", null);

        // Then
        assertThrowsWithMessage(DuplicateEntryException.class,
                () -> _metadataService.createEntityMetadata(entity),
                String.format("Entry named '%s' already exists in parent '%s'",
                        entity.name(),
                        entity.parentId() == null ? "root" : entity.parentId()));
    }

    @Test
    @Sql(value = "/sql/create_simple_folder_structure.sql")
    @SqlMergeMode(MERGE)
    public void testCreateEntityMetadata_createFileInRootFolderNameAlreadyExists()
    {
        // Given
        final var entity = _makeEntity(null, Kind.FILE, "hello.txt", "txt");

        // Then
        assertThrowsWithMessage(DuplicateEntryException.class,
                () -> _metadataService.createEntityMetadata(entity),
                String.format("Entry named '%s' already exists in parent '%s'",
                        entity.name(),
                        entity.parentId() == null ? "root" : entity.parentId()));
    }

    @Test
    @Sql(value = "/sql/create_simple_folder_structure.sql")
    @SqlMergeMode(MERGE)
    public void testCreateEntityMetadata_createFolderInRootFolderNameAlreadyExists()
    {
        // Given
        final var entity = _makeEntity(null, Kind.DIRECTORY, "docs", null);

        // Then
        assertThrowsWithMessage(DuplicateEntryException.class,
                () -> _metadataService.createEntityMetadata(entity),
                String.format("Entry named '%s' already exists in parent '%s'",
                        entity.name(),
                        entity.parentId() == null ? "root" : entity.parentId()));
    }

    private EntityMetadata _makeFile(final UUID parentId)
    {
        return _makeEntity(parentId, Kind.FILE, "test.txt", "txt");
    }

    private EntityMetadata _makeFolder(final UUID parentId)
    {
        return _makeEntity(parentId, Kind.DIRECTORY, "test", null);
    }

    private EntityMetadata _makeEntity(final UUID parentId,
                                       final Kind kind,
                                       final String name,
                                       final String extension)
    {
        final var createdDate = LocalDateTime.now();
        final var modifiedDate = createdDate.plusDays(3L);

        return new EntityMetadata(
                null,
                parentId,
                kind,
                name,
                extension,
                createdDate,
                modifiedDate,
                null,
                null,
                null,
                false);
    }

    private UUID _getFirstDirectoryId()
    {
        return _entityDao.fetchByKind(KindType.directory).stream()
                .findFirst()
                .orElseThrow(() -> new TestInstantiationException("No directory found"))
                .id();
    }
}
