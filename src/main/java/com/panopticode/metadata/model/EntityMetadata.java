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

package com.panopticode.metadata.model;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.aglibs.validcheck.ValidCheck;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * DTO representing file metadata.
 *
 * @param id unique identifier of the stored entity.
 * @param parentId unique identifier of the parent node, null meaning 'root'.
 * @param kind eg 'file', 'folder'.
 * @param name name of the entity, not including path and extension. It cannot be null or empty.
 * @param extension optional extension without leasing dot, e.g. 'png', 'mp3'.
 * @param created ISO 8601 date and time, e.g. '2020-08-30T18:43.568'.
 * @param lastModified ISO 8601 date and time, e.g. '2020-08-30T18:43.568'.
 * @param size size in bytes.
 * @param mimeType type of the media, e.g. 'text/plain', 'inode/directory' etc.
 * @param customMetadata optional, additional metadata in JSON format.
 * @param rawAccess whether the entity is accessible from Speculum.
 */
public record EntityMetadata(
        UUID id,
        UUID parentId,
        Kind kind,
        String name,
        String extension,
        OffsetDateTime created,
        OffsetDateTime lastModified,
        Long size,
        String mimeType,
        JsonNode customMetadata,
        boolean rawAccess)
{
    public EntityMetadata
    {
        ValidCheck.check()
                .notNull(kind, "kind")
                .notNull(created, "created date")
                .notNull(lastModified, "last modified date")
                .nullOrIsPositive(size, "size")
                .when(kind == Kind.DIRECTORY, v -> v
                        .isNull(extension, "extension")
                        .isNull(size, "size")
                        .nullOrMatches(mimeType, "inode/directory", "media type")
                        .assertFalse(rawAccess, "raw access cannot be set at folder-level"))
                .validate();
    }
}
