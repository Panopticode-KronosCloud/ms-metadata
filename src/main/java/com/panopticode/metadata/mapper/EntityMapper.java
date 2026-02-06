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
import com.panopticode.openapi.model.Metadata;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import org.openapitools.jackson.nullable.JsonNullable;
import org.openapitools.jackson.nullable.JsonNullableModule;

import java.util.Map;
import java.util.UUID;

/**
 * Mapper to convert various entities.
 *
 * <p>Its implementation is automatically generated with MapStruct.</p>
 *
 * <p>It supports mapping to/from
 * DB entities, API Entities and internal representations.</p>
 */
@Mapper(componentModel = "spring")
public interface EntityMapper
{
    ObjectMapper OBJECT_MAPPER = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .addModule(new JsonNullableModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .build();

    @Mapping(target = "sizeBytes", source = "entityMetadata.size")
    @Mapping(target = "mediaType", source = "entityMetadata.mimeType")
    @Mapping(target = "status", source = "status")
    Entity toEntityPojo(EntityMetadata entityMetadata, Status status);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "extension", source = "request")
    @Mapping(target = "size", ignore = true)
    @Mapping(target = "mimeType", source = "request.suggestedMediaType")
    @Mapping(target = "customMetadata", source = "request.metadata")
    EntityMetadata toEntityMetadata(CreateMetadataRequest request);

    @Mapping(target = "sizeBytes", ignore = true)
    @Mapping(target = "mediaType", ignore = true)
    @Mapping(target = "blobType", ignore = true)
    @Mapping(target = "blobRef", ignore = true)
    @Mapping(target = "hashes", ignore = true)
    @Mapping(target = "thumbnails", ignore = true)
    @Mapping(target = "consolidationInfo", ignore = true)
    @Mapping(target = "metadata", source = "customMetadata")
    Metadata toMetadataResponse(StagedEntityView view);

    @Mapping(target = "id", source = "newId")
    StagedEntityView toStagedView(Entity entity, UUID newId);

    @ValueMapping(target = "file", source = "FILE")
    @ValueMapping(target = "directory", source = "DIRECTORY")
    KindType toKindType(Kind kind);

    @InheritInverseConfiguration
    Kind toKind(KindType kind);

    @ValueMapping(target = "unavailable", source = "UNAVAILABLE")
    @ValueMapping(target = "deleted", source = "DELETED")
    @ValueMapping(target = "staged", source = "STAGED")
    @ValueMapping(target = "active", source = "ACTIVE")
    StatusType toStatusType(Status status);

    @InheritInverseConfiguration
    Status toStatus(StatusType status);

    default <T> JsonNullable<T> wrapJsonNullable(T object)
    {
        return JsonNullable.of(object);
    }

    default <T> T unwrapJsonNullable(JsonNullable<T> jsonNullable)
    {
        return jsonNullable.orElse(null);
    }

    default String extractExtension(CreateMetadataRequest request)
    {
        if (com.panopticode.openapi.model.Kind.FILE == request.getKind())
        {
            final var ext = FilenameUtils.getExtension(request.getName());

            return StringUtils.isEmpty(ext) ? null : ext;
        } else
        {
            return null;
        }
    }

    default JsonNode toJsonNode(Map<String, Object> map)
    {
        return OBJECT_MAPPER.valueToTree(map);
    }

    default Map<String, Object> toJavaMap(JsonNode jsonNode)
    {
        return OBJECT_MAPPER.convertValue(jsonNode, new TypeReference<>()
        { });
    }
}
