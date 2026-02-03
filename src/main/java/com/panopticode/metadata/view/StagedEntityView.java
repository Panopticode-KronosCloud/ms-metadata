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

package com.panopticode.metadata.view;

import com.panopticode.metadata.model.Kind;
import com.panopticode.metadata.model.Status;

import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * View of an unconsolidated entity (fewer fields are available).
 *
 * @param id entity ID
 * @param parentId optional ID of the parent entity
 * @param kind file, folder...
 * @param name name
 * @param extension extrapolated extension (txt, png etc)
 * @param created original date the entity was created
 * @param lastModified date the entity was last modified
 * @param customMetadata user-added json block
 * @param rawAccess whether the entity is visible in Speculum
 * @param status current status of the entity
 */
public record StagedEntityView(
        UUID id,
        UUID parentId,
        Kind kind,
        String name,
        String extension,
        LocalDateTime created,
        LocalDateTime lastModified,
        JsonNode customMetadata,
        boolean rawAccess,
        Status status)
{ }
