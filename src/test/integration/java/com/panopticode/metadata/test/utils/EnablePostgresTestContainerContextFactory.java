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

import lombok.EqualsAndHashCode;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfigurationAttributes;
import org.springframework.test.context.ContextCustomizer;
import org.springframework.test.context.ContextCustomizerFactory;
import org.springframework.test.context.MergedContextConfiguration;
import org.springframework.test.context.TestContextAnnotationUtils;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.List;
import java.util.Map;

/**
 * When a test class is annotated with @EnablePostgresTestContainer the EnablePostgresTestContainerContextFactory
 * will produce a PostgresTestContainerContextCustomizer. This ContextCustomizer will start a test container
 * and add a property source with the database properties.
 */
public class EnablePostgresTestContainerContextFactory
        implements ContextCustomizerFactory
{
    @Override
    public ContextCustomizer createContextCustomizer(final Class<?> testClass,
                                                     final List<ContextConfigurationAttributes> configAttributes)
    {
        if (!(TestContextAnnotationUtils.hasAnnotation(testClass, EnablePostgresTestContainer.class)))
        {
            return null;
        }
        return new PostgresTestContainerContextCustomizer();
    }

    @EqualsAndHashCode
    private static final class PostgresTestContainerContextCustomizer
            implements ContextCustomizer, AutoCloseable
    {
        private static final DockerImageName POSTGRES_IMAGE = DockerImageName
                .parse("postgres")
                .withTag("17.4");
        private PostgreSQLContainer<?> _postgresContainer;

        @Override
        public void customizeContext(final ConfigurableApplicationContext context,
                                     final MergedContextConfiguration mergedConfig)
        {
            _postgresContainer = new PostgreSQLContainer<>(POSTGRES_IMAGE);
            _postgresContainer.start();
            final var propertySource = _makePropertySource();
            context.getEnvironment().getPropertySources().addFirst(propertySource);

            // register itself as a bean to allow shutdown
            final var beanFactory = context.getBeanFactory();
            beanFactory.registerSingleton("postgresTestContainer", this);
        }

        @Override
        public void close()
                throws Exception
        {
            _postgresContainer.close();
        }

        private MapPropertySource _makePropertySource()
        {
            final var properties = Map.<String, Object>of(
                    "spring.datasource.url", _postgresContainer.getJdbcUrl(),
                    "spring.datasource.username", _postgresContainer.getUsername(),
                    "spring.datasource.password", _postgresContainer.getPassword(),
                    // Prevent any in memory db from replacing the data source
                    // See @AutoConfigureTestDatabase
                    "spring.test.database.replace", "NONE");
            return new MapPropertySource("PostgresContainer Test Properties", properties);
        }
    }
}
