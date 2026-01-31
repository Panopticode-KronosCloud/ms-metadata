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

package com.panopticode.metadata.dao;

import com.panopticode.jooq.enums.KindType;
import com.panopticode.jooq.tables.daos.EntityDao;
import com.panopticode.jooq.tables.pojos.Entity;
import com.panopticode.metadata.exception.ParentNotFoundException;

import lombok.extern.slf4j.Slf4j;
import org.jooq.Configuration;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.panopticode.jooq.tables.Entity.ENTITY;
import static com.panopticode.metadata.dao.Utils.safelyInsertAndFetchId;

/**
 * Extension to the jOOQ-generated base {@link EntityDao}.
 */
@Slf4j
@Repository
public class EntityExtDao
        extends EntityDao
{
    @Autowired
    public EntityExtDao(final Configuration configuration)
    {
        super(configuration);
    }

    @Transactional
    public UUID create(final Entity entity)
    {
        _checkParentExist(entity.parentId());

        return safelyInsertAndFetchId(ctx().insertInto(ENTITY)
                .columns(ENTITY.PARENT_ID, ENTITY.KIND, ENTITY.NAME, ENTITY.EXTENSION, ENTITY.CREATED, ENTITY.LAST_MODIFIED,
                        ENTITY.SIZE_BYTES, ENTITY.MEDIA_TYPE, ENTITY.CUSTOM_METADATA, ENTITY.RAW_ACCESS, ENTITY.STATUS)
                .values(entity.parentId(), entity.kind(), entity.name(), entity.extension(), entity.created(), entity.lastModified(),
                        entity.sizeBytes(), entity.mediaType(), entity.customMetadata(), entity.rawAccess(), entity.status())
                .returning(ENTITY.ID));
    }

    private void _checkParentExist(final UUID id)
    {
        if (id != null)
        {
            final var count = ctx().fetchCount(
                    DSL.select()
                            .from(ENTITY)
                            .where(ENTITY.ID.eq(id))
                            .and(ENTITY.KIND.eq(KindType.directory)));

            if (count < 1)
            {
                throw new ParentNotFoundException(id);
            }
        }
    }
}
