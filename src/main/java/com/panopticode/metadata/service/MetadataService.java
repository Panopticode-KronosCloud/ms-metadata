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

package com.panopticode.metadata.service;

import com.panopticode.metadata.model.EntityMetadata;
import com.panopticode.metadata.view.StagedEntityView;

/**
 * Metadata service.
 */
public interface MetadataService
{
    /**
     * Create a new metadata entry for an entity.
     *
     * @param entityMetadata convenient object wrapping all attributes
     * @return a view of the staged entity
     */
    StagedEntityView createEntityMetadata(EntityMetadata entityMetadata);
}
