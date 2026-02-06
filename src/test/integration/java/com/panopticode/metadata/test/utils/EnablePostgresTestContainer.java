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

package com.panopticode.metadata.test.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * When a test class is annotated with <code>@EnablePostgresTestContainer</code>, a Spring ContextCustomizer will be generated.
 * This ContextCustomizer will start a test container running Postgres and add a property source with the database properties.
 * <p/>
 * By using a ContextCustomizerFactory we achieved the following:
 * <ol>
 *     <li/> Only one application context is started and reused between test classes.
 *     <li/> Only one test container is started and reused between test classes.
 *     <li/>
 * </ol>
 * This should significantly speed up integration tests for any application using Testcontainers.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EnablePostgresTestContainer
{ }
