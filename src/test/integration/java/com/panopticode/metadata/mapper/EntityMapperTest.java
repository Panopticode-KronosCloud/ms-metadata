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

package com.panopticode.metadata.mapper;

import com.panopticode.jooq.enums.KindType;
import com.panopticode.jooq.enums.StatusType;
import com.panopticode.jooq.tables.pojos.Entity;
import com.panopticode.metadata.model.EntityMetadata;
import com.panopticode.metadata.model.Kind;
import com.panopticode.metadata.model.Status;
import com.panopticode.metadata.view.StagedEntityView;
import com.panopticode.openapi.model.CreateMetadataRequest;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Import(EntityMapperImpl.class)
public class EntityMapperTest
{
    @Autowired
    private EntityMapperImpl _entityMapper;

    @ParameterizedTest(name = "testToEntityPojo_status_{0}")
    @ValueSource(strings = {"STAGED", "ACTIVE", "DELETED", "UNAVAILABLE"})
    void testToEntityPojo(String status)
    {
        // Given
        final var entityMetadata = _makeEntityMetadata(UUID.randomUUID());
        final var statusEnum = Status.valueOf(status);

        // When
        final var entity = _entityMapper.toEntityPojo(entityMetadata, statusEnum);

        // Then
        assertThat(entity).isNotNull();
        assertThat(entity.id()).isNotNull();
        assertThat(entity.parentId()).isEqualTo(entityMetadata.parentId());
        assertThat(entity.name()).isEqualTo(entityMetadata.name());
        assertThat(entity.extension()).isEqualTo(entityMetadata.extension());
        assertThat(entity.kind()).isEqualTo(KindType.file);
        assertThat(entity.created()).isEqualTo(entityMetadata.created());
        assertThat(entity.lastModified()).isEqualTo(entityMetadata.lastModified());
        assertThat(entity.sizeBytes()).isEqualTo(entityMetadata.size());
        assertThat(entity.mediaType()).isEqualTo(entityMetadata.mimeType());
        assertThat(entity.customMetadata()).isEqualTo(entityMetadata.customMetadata());
        assertThat(entity.rawAccess()).isEqualTo(entityMetadata.rawAccess());
        assertThat(entity.status().name()).isEqualToIgnoringCase(statusEnum.name());
    }

    @Test
    void testToEntityPojo_nullStatus()
    {
        // Given
        final var entityMetadata = _makeEntityMetadata(UUID.randomUUID());

        // When
        final var entity = _entityMapper.toEntityPojo(entityMetadata, null);

        // Then
        assertThat(entity).isNotNull();
        assertThat(entity.status()).isNull();
    }

    @Test
    void testToEntityPojo_nullEntityNonNullStatus()
    {
        // Given
        final var statusEnum = Status.ACTIVE;

        // When
        final var entity = _entityMapper.toEntityPojo(null, statusEnum);

        // Then
        assertThat(entity).isNotNull();
        assertThat(entity.status().name()).isEqualToIgnoringCase(statusEnum.name());
        assertThat(entity.id()).isNull();
        assertThat(entity.parentId()).isNull();
        assertThat(entity.kind()).isNull();
        assertThat(entity.name()).isNull();
        assertThat(entity.extension()).isNull();
        assertThat(entity.created()).isNull();
        assertThat(entity.lastModified()).isNull();
        assertThat(entity.sizeBytes()).isNull();
        assertThat(entity.mediaType()).isNull();
        assertThat(entity.customMetadata()).isNull();
        assertThat(entity.rawAccess()).isNull();
    }

    @Test
    void testToEntityPojo_allNull()
    {
        // All null arguments: pass null for both entityMetadata and status, expect null returned
        final var entity = _entityMapper.toEntityPojo(null, null);

        // Then
        assertThat(entity).isNull();
    }

    @Test
    void testToStagedView_entityNullNewIdNull()
    {
        // Scenario: both Entity and newId are null, should return null
        final var stagedEntityView = _entityMapper.toStagedView(null, null);
        assertThat(stagedEntityView).isNull();
    }

    @Test
    void testToStagedView_entityNullNewIdNonNull()
    {
        // Scenario: Entity is null, newId is provided, returns StagedEntityView with only id set
        final var newId = UUID.randomUUID();
        final var stagedEntityView = _entityMapper.toStagedView(null, newId);

        assertThat(stagedEntityView).isNotNull();
        assertThat(stagedEntityView.id()).isEqualTo(newId);
        assertThat(stagedEntityView.parentId()).isNull();
        assertThat(stagedEntityView.kind()).isNull();
        assertThat(stagedEntityView.name()).isNull();
        assertThat(stagedEntityView.extension()).isNull();
        assertThat(stagedEntityView.created()).isNull();
        assertThat(stagedEntityView.lastModified()).isNull();
        assertThat(stagedEntityView.customMetadata()).isNull();
        // default for rawAccess is false
        assertThat(stagedEntityView.rawAccess()).isFalse();
        assertThat(stagedEntityView.status()).isNull();
    }

    @Test
    void testToStagedView_entityNonNullNewIdNull()
    {
        // Scenario: Entity is fully filled, newId is null
        final var entity = _makeEntity(UUID.randomUUID());
        final var stagedEntityView = _entityMapper.toStagedView(entity, null);

        assertThat(stagedEntityView).isNotNull();
        assertThat(stagedEntityView.id()).isNull();
        assertThat(stagedEntityView.parentId()).isEqualTo(entity.parentId());
        assertThat(stagedEntityView.kind()).isEqualTo(_entityMapper.toKind(entity.kind()));
        assertThat(stagedEntityView.name()).isEqualTo(entity.name());
        assertThat(stagedEntityView.extension()).isEqualTo(entity.extension());
        assertThat(stagedEntityView.created()).isEqualTo(entity.created());
        assertThat(stagedEntityView.lastModified()).isEqualTo(entity.lastModified());
        assertThat(stagedEntityView.customMetadata()).isEqualTo(entity.customMetadata());
        assertThat(stagedEntityView.rawAccess()).isEqualTo(Boolean.TRUE.equals(entity.rawAccess()));
        // Status mapping will depend on the implementation
        if (entity.status() != null)
        {
            assertThat(stagedEntityView.status()).isNotNull();
            assertThat(stagedEntityView.status().name()).isEqualToIgnoringCase(entity.status().name());
        } else
        {
            assertThat(stagedEntityView.status()).isNull();
        }
    }

    @Test
    void testToStagedView_entityNonNullNewIdNonNull()
    {
        // Scenario: Both entity and newId are provided
        final var newId = UUID.randomUUID();
        final var entity = _makeEntity(UUID.randomUUID());
        final var stagedEntityView = _entityMapper.toStagedView(entity, newId);

        assertThat(stagedEntityView).isNotNull();
        assertThat(stagedEntityView.id()).isEqualTo(newId);
        assertThat(stagedEntityView.parentId()).isEqualTo(entity.parentId());
        assertThat(stagedEntityView.kind()).isEqualTo(_entityMapper.toKind(entity.kind()));
        assertThat(stagedEntityView.name()).isEqualTo(entity.name());
        assertThat(stagedEntityView.extension()).isEqualTo(entity.extension());
        assertThat(stagedEntityView.created()).isEqualTo(entity.created());
        assertThat(stagedEntityView.lastModified()).isEqualTo(entity.lastModified());
        assertThat(stagedEntityView.customMetadata()).isEqualTo(entity.customMetadata());
        assertThat(stagedEntityView.rawAccess()).isEqualTo(Boolean.TRUE.equals(entity.rawAccess()));
        if (entity.status() != null)
        {
            assertThat(stagedEntityView.status()).isNotNull();
            assertThat(stagedEntityView.status().name()).isEqualToIgnoringCase(entity.status().name());
        } else
        {
            assertThat(stagedEntityView.status()).isNull();
        }
    }

    @Test
    void testToStagedView_entityWithRawAccessNull()
    {
        // Scenario: Entity with rawAccess == null; should default to false
        final var entity = _makeEntity(UUID.randomUUID(), null);

        final var stagedEntityView = _entityMapper.toStagedView(entity, null);
        assertThat(stagedEntityView).isNotNull();
        // rawAccess should be false (default)
        assertThat(stagedEntityView.rawAccess()).isFalse();
    }

    @Test
    void testToStagedView_entityWithAllNullFields()
    {
        // Scenario: All fields in Entity (except required id) are null/absent
        final var entity = _makeEntityWithAllFieldsNull();
        final var newId = UUID.randomUUID();
        final var stagedEntityView = _entityMapper.toStagedView(entity, newId);
        assertThat(stagedEntityView).isNotNull();
        assertThat(stagedEntityView.id()).isEqualTo(newId);
        assertThat(stagedEntityView.parentId()).isNull();
        assertThat(stagedEntityView.kind()).isNull();
        assertThat(stagedEntityView.name()).isNull();
        assertThat(stagedEntityView.extension()).isNull();
        assertThat(stagedEntityView.created()).isNull();
        assertThat(stagedEntityView.lastModified()).isNull();
        assertThat(stagedEntityView.customMetadata()).isNull();
        assertThat(stagedEntityView.rawAccess()).isFalse();
        assertThat(stagedEntityView.status()).isNull();
    }

    @ParameterizedTest(name = "testToEntityMetadata_file_{0}")
    @ValueSource(strings = {"test.txt", "nofileextension"})
    void testToEntityMetadata_file(String name)
    {
        // Given
        final var parentId = UUID.randomUUID();
        final var suggestedMediaType = "text/plain";
        final var metadata = new HashMap<String, Object>();
        metadata.put("key", "value");
        final var rawAccess = false;
        final var created = OffsetDateTime.of(2020, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        final var lastModified = OffsetDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);

        final var metadataRequest = new CreateMetadataRequest()
                .parentId(parentId)
                .name(name)
                .suggestedMediaType(suggestedMediaType)
                .metadata(metadata)
                .rawAccess(rawAccess)
                .created(created)
                .lastModified(lastModified)
                .kind(com.panopticode.openapi.model.Kind.FILE);

        // When
        final var entityMetadata = _entityMapper.toEntityMetadata(metadataRequest);

        // Then
        assertThat(entityMetadata).isNotNull();
        assertThat(entityMetadata.parentId()).isEqualTo(parentId);
        assertThat(entityMetadata.name()).isEqualTo(name);
        assertThat(entityMetadata.extension()).isEqualTo(name.endsWith(".txt") ? "txt" : null);
        assertThat(entityMetadata.mimeType()).isEqualTo(suggestedMediaType);
        assertThat(entityMetadata.customMetadata()).isEqualTo(_toJsonNode(metadata));
        assertThat(entityMetadata.rawAccess()).isEqualTo(rawAccess);
        assertThat(entityMetadata.created()).isEqualTo(created);
        assertThat(entityMetadata.lastModified()).isEqualTo(lastModified);
        assertThat(entityMetadata.size()).isNull();
        assertThat(entityMetadata.rawAccess()).isEqualTo(rawAccess);
        assertThat(entityMetadata.kind()).isEqualTo(Kind.FILE);
    }

    @Test
    void testToEntityMetadata_folder()
    {
        // Given
        final var parentId = UUID.randomUUID();
        final var name = "test";
        final var suggestedMediaType = "inode/directory";
        final var metadata = new HashMap<String, Object>();
        final var rawAccess = false;
        final var created = OffsetDateTime.of(2020, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        final var lastModified = OffsetDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);

        final var metadataRequest = new CreateMetadataRequest()
                .parentId(parentId)
                .name(name)
                .suggestedMediaType(suggestedMediaType)
                .metadata(metadata)
                .rawAccess(rawAccess)
                .created(created)
                .lastModified(lastModified)
                .kind(com.panopticode.openapi.model.Kind.DIRECTORY);

        // When
        final var entityMetadata = _entityMapper.toEntityMetadata(metadataRequest);

        // Then
        assertThat(entityMetadata).isNotNull();
        assertThat(entityMetadata.parentId()).isEqualTo(parentId);
        assertThat(entityMetadata.name()).isEqualTo(name);
        assertThat(entityMetadata.extension()).isNull();
        assertThat(entityMetadata.mimeType()).isEqualTo(suggestedMediaType);
        assertThat(entityMetadata.customMetadata()).isEqualTo(_toJsonNode(metadata));
        assertThat(entityMetadata.rawAccess()).isEqualTo(rawAccess);
        assertThat(entityMetadata.created()).isEqualTo(created);
        assertThat(entityMetadata.lastModified()).isEqualTo(lastModified);
        assertThat(entityMetadata.size()).isNull();
        assertThat(entityMetadata.rawAccess()).isEqualTo(rawAccess);
        assertThat(entityMetadata.kind()).isEqualTo(Kind.DIRECTORY);
    }

    @Test
    void testToMetadataResponse_happyPath()
    {
        // Given
        final var staged = _makeStagedEntityView(UUID.randomUUID());

        // When
        final var metadata = _entityMapper.toMetadataResponse(staged);

        // Then
        assertThat(metadata).isNotNull();
        assertThat(metadata.getId()).isEqualTo(staged.id());
        assertThat(_entityMapper.unwrapJsonNullable(metadata.getParentId())).isEqualTo(staged.parentId());
        assertThat(metadata.getKind().toString()).isEqualToIgnoringCase(staged.kind().name());
        assertThat(metadata.getName()).isEqualTo(staged.name());
        assertThat(metadata.getCreated()).isEqualTo(staged.created());
        assertThat(metadata.getLastModified()).isEqualTo(staged.lastModified());
        assertThat(metadata.isRawAccess()).isEqualTo(staged.rawAccess());
        assertThat(metadata.getStatus().toString()).isEqualToIgnoringCase(staged.status().name());
        assertThat(metadata.getMetadata()).isEqualTo(_entityMapper.wrapJsonNullable(_entityMapper.toJavaMap(staged.customMetadata())));
    }

    private EntityMetadata _makeEntityMetadata(final UUID parentId)
    {
        return new EntityMetadata(
                UUID.randomUUID(),
                parentId,
                Kind.FILE,
                "test.txt",
                "txt",
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                null,
                null,
                null,
                false);
    }

    private StagedEntityView _makeStagedEntityView(final UUID id)
    {
        return new StagedEntityView(
                id,
                null,
                Kind.FILE,
                "test.txt",
                "txt",
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                null,
                false,
                Status.STAGED);
    }

    private JsonNode _toJsonNode(final Map<String, Object> map)
    {
        return _entityMapper.toJsonNode(map);
    }

    private static Entity _makeEntity(final UUID id)
    {
        return _makeEntity(id, true);
    }

    // Utility: simulate a fully filled Entity
    private static Entity _makeEntity(final UUID id,
                                      final Boolean rawAccess)
    {
        return new Entity(
                id,
                UUID.randomUUID(),
                KindType.file,
                "testName.txt",
                "txt",
                OffsetDateTime.now(),
                OffsetDateTime.now().plusDays(1),
                12345L,
                "text/plain",
                com.fasterxml.jackson.databind.node.JsonNodeFactory.instance.objectNode().put("foo", "bar"),
                rawAccess,
                StatusType.active);
    }

    // Utility: simulate an Entity with all nullable fields null (except id)
    @SuppressWarnings("checkstyle:TrailingComment")
    private static Entity _makeEntityWithAllFieldsNull()
    {
        return new Entity(
                null,  // id
                null,  // parentId
                null,  // kind
                null,  // name
                null,  // extension
                null,  // created
                null,  // lastModified
                null,  // sizeBytes
                null,  // mediaType
                null,  // customMetadata
                null,  // rawAccess
                null); // status)
    }
}
