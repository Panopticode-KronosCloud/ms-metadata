/*
 * java-spring-boot-ms-template
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

package com.panopticode.example.exception.utils;

import com.panopticode.example.utils.AbstractBaseUtilsTest;

import org.junit.jupiter.api.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ExceptionUtilsTest
    extends AbstractBaseUtilsTest<ExceptionUtils>
{
    ExceptionUtilsTest()
    {
        super(ExceptionUtils.class);
    }

    @Test
    void testThrowUnsupportedOperationInUtilityClass_throws()
    {
        assertThatThrownBy(() -> ExceptionUtils.throwUnsupportedOperationInUtilityClass(ExceptionUtils.class))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Utility class ExceptionUtils cannot be instantiated");
    }

    @Test
    void testFormat_noArgs()
    {
        final var args = new Object[]{};

        assertThat(ExceptionUtils.format("final string", args))
                .isEqualTo("final string");

        assertThat(ExceptionUtils.cause(args)).isNull();
    }

    @Test
    void testFormat_normalArgs()
    {
        final var date = ZonedDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        final var args = new Object[]
            {
                123,
                12.345e3,
                "test string",
                new ComplexType(date, "inner string", 123.456D, null),
                List.of("1", "2", "3", "4", "5"),
            };

        assertThat(ExceptionUtils.format("integer {} float {} string {} object {} collection {}", args))
                .isEqualTo("integer 123 float 12345.0 string test string object ComplexType[date=2000-01-01T00:00Z, "
                        + "string=inner string, aDouble=123.456, nullable=null] collection [1, 2, 3, 4, 5]");

        assertThat(ExceptionUtils.cause(args)).isNull();
    }

    @Test
    void testFormat_argsAndThrowable()
    {
        final var date = ZonedDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        final var args = new Object[]
            {
                123,
                12.345e3,
                "test string",
                new ComplexType(date, "inner string", 123.456D, null),
                List.of("1", "2", "3", "4", "5"),
                new TestException("test error message"),
            };

        assertThat(ExceptionUtils.format("integer {} float {} string {} object {} collection {}", args))
                .isEqualTo("integer 123 float 12345.0 string test string object ComplexType[date=2000-01-01T00:00Z, "
                        + "string=inner string, aDouble=123.456, nullable=null] collection [1, 2, 3, 4, 5]");

        assertThat(ExceptionUtils.cause(args))
                .isInstanceOf(TestException.class)
                .hasMessage("test error message");
    }

    private record ComplexType(
            ZonedDateTime date,
            String string,
            Double aDouble,
            Object nullable)
    { }

    private static class TestException
            extends Exception
    {
        TestException(String message)
        {
            super(message);
        }

    }
}
