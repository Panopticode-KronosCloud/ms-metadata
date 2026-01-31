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

import io.github.aglibs.validcheck.ValidCheck;

/**
 * Hash of an object.
 *
 * @param value hashes value, in lowercase
 * @param algorithm algorithm used to compute the hashes
 * @param encoding e.g. 'base64', 'utf-8', 'hex' etc.
 */
public record ObjectHash(
        String value,
        String algorithm,
        String encoding)
{
    public ObjectHash
    {
        ValidCheck.check()
                .notBlank(value, "value")
                .notBlank(algorithm, "algorithm")
                .notBlank(encoding, "encoding")
                .validate();
    }
}
