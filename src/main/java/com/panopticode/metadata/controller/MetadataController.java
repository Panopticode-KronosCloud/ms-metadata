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

package com.panopticode.metadata.controller;

import com.panopticode.metadata.mapper.EntityMapper;
import com.panopticode.metadata.service.MetadataService;
import com.panopticode.openapi.api.MetadataApiDelegate;
import com.panopticode.openapi.model.CreateMetadataRequest;
import com.panopticode.openapi.model.Metadata;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Controller for the metadata API.
 */
@Service
@Slf4j
public class MetadataController
    implements MetadataApiDelegate
{
    private final EntityMapper _entityMapper;
    private final MetadataService _metadataService;

    @Autowired
    public MetadataController(final EntityMapper entityMapper,
                              final MetadataService metadataService)
    {
        _entityMapper = entityMapper;
        _metadataService = metadataService;
    }

    @Override
    public ResponseEntity<Metadata> createMetadata(final CreateMetadataRequest createMetadataRequest)
    {
        final var entityMetadata = _entityMapper.toEntityMetadata(createMetadataRequest);

        final var staged = _metadataService.createEntityMetadata(entityMetadata);

        final var responseBody = _entityMapper.toMetadataResponse(staged);

        return ResponseEntity.ok(responseBody);
    }
}
