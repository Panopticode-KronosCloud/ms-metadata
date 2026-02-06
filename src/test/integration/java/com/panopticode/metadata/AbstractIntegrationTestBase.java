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

package com.panopticode.metadata;

import com.panopticode.metadata.test.utils.EnablePostgresTestContainer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@EnablePostgresTestContainer
public abstract class AbstractIntegrationTestBase
    implements ApplicationContextAware
{
    private static volatile ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(final ApplicationContext appContext)
            throws BeansException
    {
        if (AbstractIntegrationTestBase.applicationContext == null)
        {
            AbstractIntegrationTestBase.applicationContext = appContext;
        }
    }

    @AfterAll
    public static void shutdownPostgres()
            throws Exception
    {
        try
        {
            final var postgresTestContainer = applicationContext.getBean("postgresTestContainer", AutoCloseable.class);
            log.info("Shutting down Postgres test containers...");
            postgresTestContainer.close();
        } catch (BeansException e)
        {
            log.warn("Could not gracefully shutdown Postgres test containers", e);
        }
    }
}
