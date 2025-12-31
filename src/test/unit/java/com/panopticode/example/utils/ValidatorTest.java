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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ValidatorTest
    extends AbstractBaseUtilsTest<Validator>
{
    ValidatorTest()
    {
        super(Validator.class);
    }

    @ParameterizedTest
    @NullSource
    void testNonNull_nullOrEmpty(Object o)
    {
        assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> Validator.nonNull(o))
                .withMessage("'<runtime variable>' must not be null");
    }

    @Test
    void testValidateNonNull_happyPath()
    {
        final var o = new Object();
        assertThat(Validator.validateNonNull(o, "test")).isEqualTo(o);
        assertThat(Validator.nonNull(o)).isEqualTo(o);
    }

    @ParameterizedTest
    @NullSource
    void testValidateNonNull_nullOrEmpty(Object o)
    {
        assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> Validator.validateNonNull(o, "test"))
                .withMessage("'test' must not be null");
    }

    @Test
    void testValidateNotEmpty_happyPath()
    {
        assertThat(Validator.validateNotEmpty("hi", "test")).isEqualTo("hi");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testValidateNotEmpty_nullOrEmpty(String s)
    {
        assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> Validator.validateNotEmpty(s, "test"))
                .withMessage("'test' cannot be blank or empty");
    }

    @Test
    void testValidateStrictlyPositive_happyPath()
    {
        assertThat(Validator.validateStrictlyPositive(10, "test")).isEqualTo(10);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = {0, -4})
    void testValidateStrictlyPositive_nullOrNegative(Integer i)
    {
        assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> Validator.validateStrictlyPositive(i, "test"))
                .withMessage("'test' must be defined and a strictly positive number");
    }

    @ParameterizedTest
    @NullSource
    @MethodSource("_nullOrStrictlyPositive")
    void testValidateNullOrStrictlyPositive_happyPath(Number n)
    {
        assertThat(Validator.validateNullOrStrictlyPositive(n, "test")).isEqualTo(n);
    }

    @ParameterizedTest
    @MethodSource("_zeroOrNegative")
    void testValidateNullOrStrictlyPositive_negative(Number n)
    {
        assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> Validator.validateNullOrStrictlyPositive(n, "test"))
                .withMessage("'test' must be a strictly positive number");
    }

    @Test
    void testValidateNullOrStrictlyPositive_unknownType()
    {
        assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> Validator.validateNullOrStrictlyPositive(BigInteger.TEN, "test"))
                .withMessage("Unexpected number subtype java.math.BigInteger for variable 'test'");
    }

    private static Stream<Number> _nullOrStrictlyPositive()
    {
        return Stream.of(
                1,
                2L,
                3.5F,
                null,
                4.2e2,
                (short) 5);
    }

    private static Stream<Number> _zeroOrNegative()
    {
        return Stream.of(
                0,
                -1,
                -2L,
                -3.5F,
                -4.2e2,
                (short) -5);
    }
}
