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

package com.panopticode.example.utils;

import com.panopticode.example.exception.ValidationException;
import com.panopticode.example.exception.utils.ExceptionUtils;

import org.apache.commons.lang3.StringUtils;

/**
 * Generic validators for various types.
 */
public final class Validator
{
    private static final String NOT_NULL_MSG = "'{}' must not be null";
    private static final String NOT_EMPTY_MSG = "'{}' cannot be blank or empty";
    private static final String POSITIVE_NUM_MSG = "'{}' must be defined and a strictly positive number";
    private static final String NULL_OR_POSITIVE_NUM_MSG = "'{}' must be a strictly positive number";
    private static final String UNEXPECTED_NUMBER = "Unexpected number subtype {} for variable '{}'";

    private Validator()
    {
        ExceptionUtils.throwUnsupportedOperationInUtilityClass(Validator.class);
    }

    /**
     * Shorten version of {@link Validator#validateNonNull} for better readability.
     *
     * @param object to validate
     * @param <T> object type (any)
     * @return the object if not null
     */
    public static <T> T nonNull(T object)
    {
        return validateNonNull(object, "<runtime variable>");
    }

    public static <T> T validateNonNull(T object, String fieldName)
    {
        if (object != null)
        {
            return object;
        } else
        {
            throw new ValidationException(NOT_NULL_MSG, fieldName);
        }

    }

    public static String validateNotEmpty(String string, String fieldName)
    {
        if (StringUtils.isNotBlank(string))
        {
            return string;
        } else
        {
            throw new ValidationException(NOT_EMPTY_MSG, fieldName);
        }
    }

    public static int validateStrictlyPositive(Integer integer, String fieldName)
    {
        if (integer != null && integer > 0)
        {
            return integer;
        } else
        {
            throw new ValidationException(POSITIVE_NUM_MSG, fieldName);
        }
    }

    public static <N extends Number> N validateNullOrStrictlyPositive(N number, String fieldName)
    {
        if (number == null)
        {
            return null;
        }

        final var negative = switch (number)
        {
            case Integer i -> i <= 0;
            case Float f -> f <= 0F;
            case Double d -> d <= 0D;
            case Long l -> l <= 0L;
            case Short s -> s <= 0;
            default -> throw new ValidationException(UNEXPECTED_NUMBER, number.getClass().getName(), fieldName);
        };

        if (negative)
        {
            throw new ValidationException(NULL_OR_POSITIVE_NUM_MSG, fieldName);
        }

        return number;
    }
}
