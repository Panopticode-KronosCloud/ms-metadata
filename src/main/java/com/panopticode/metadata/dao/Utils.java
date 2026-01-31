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

import com.panopticode.metadata.exception.DataLayerException;
import com.panopticode.metadata.exception.utils.ExceptionUtils;

import org.jooq.InsertResultStep;
import org.jooq.Record;
import org.jooq.exception.DataTypeException;
import org.jooq.exception.TooManyRowsException;

import java.util.UUID;

/**
 * Utility functions for Data Access Objects.
 */
public final class Utils
{
    private Utils()
    {
        ExceptionUtils.throwUnsupportedOperationInUtilityClass(Utils.class);
    }

    public static <T extends Record> UUID safelyInsertAndFetchId(InsertResultStep<T> query)
            throws DataLayerException
    {
        final T result;

        try
        {
            result = query.fetchOne();
        } catch (TooManyRowsException e)
        {
            throw new DataLayerException("Too many entity metadata records found when only one was expected!", e);
        }

        if (result == null)
        {
            throw new DataLayerException("Entity metadata record not found!");
        }

        final UUID id;

        try
        {
            id = result.get("id", UUID.class);
        } catch (IllegalArgumentException | DataTypeException e)
        {
            throw new DataLayerException("Could not fetch field 'id' of expected type 'java.util.UUID'", e);
        }

        return id;
    }
}
