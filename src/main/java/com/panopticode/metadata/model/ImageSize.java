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
 * Generic record holding the height and width of an image.
 *
 * @param height height, in pixels
 * @param width width, in pixels
 */
public record ImageSize(
        int height,
        int width)
{
    public ImageSize
    {
        ValidCheck.check()
                .isPositive(height, "height")
                .isPositive(width, "width")
                .validate();
    }
}
