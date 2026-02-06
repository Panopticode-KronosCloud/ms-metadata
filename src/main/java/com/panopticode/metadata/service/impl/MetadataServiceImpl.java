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

package com.panopticode.metadata.service.impl;

import com.panopticode.metadata.dao.EntityExtDao;
import com.panopticode.metadata.exception.DuplicateEntryException;
import com.panopticode.metadata.mapper.EntityMapper;
import com.panopticode.metadata.model.EntityMetadata;
import com.panopticode.metadata.model.Status;
import com.panopticode.metadata.service.MetadataService;
import com.panopticode.metadata.view.StagedEntityView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Implementation of the metadata service.
 */
@Service
public class MetadataServiceImpl
    implements MetadataService
{
    private final EntityMapper _entityMapper;
    private final EntityExtDao _entityDao;

    @Autowired
    public MetadataServiceImpl(final EntityMapper entityMapper,
                               final EntityExtDao entityDao)
    {
        _entityMapper = entityMapper;
        _entityDao = entityDao;
    }

    @Override
    public StagedEntityView createEntityMetadata(final EntityMetadata entityMetadata)
    {
        final var entity = _entityMapper.toEntityPojo(entityMetadata, Status.STAGED);

        final UUID newId;
        try
        {
            newId = _entityDao.create(entity);
        } catch (DuplicateKeyException e)
        {
            throw new DuplicateEntryException("Entry named '{}' already exists in parent '{}'",
                    entityMetadata.name(), entityMetadata.parentId() == null ? "root" : entityMetadata.parentId(), e);
        }

        return _entityMapper.toStagedView(entity, newId);
    }
}
