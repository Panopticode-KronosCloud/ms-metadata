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

package com.panopticode.metadata.exception;

import com.panopticode.metadata.exception.utils.ExceptionUtils;

/**
 * Base class for all <i>unchecked</i> exceptions thrown in this application. It cannot be
 * instantiated itself, but it can be conveniently caught to filter out
 * all the microservice domain-specific errors.
 */
public class BaseAppRuntimeException
    extends RuntimeException
{
    protected BaseAppRuntimeException(String message, Object... args)
    {
        super(ExceptionUtils.format(message, args), ExceptionUtils.cause(args));
    }
}
