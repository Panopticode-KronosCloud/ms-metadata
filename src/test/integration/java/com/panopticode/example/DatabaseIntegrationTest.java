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

package com.panopticode.example;

import com.panopticode.jooq.tables.daos.RecipeDao;
import com.panopticode.jooq.tables.pojos.Recipe;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.SqlMergeMode.MergeMode.MERGE;

@Sql(value = "/sql/clear_database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class DatabaseIntegrationTest
    extends AbstractIntegrationTestBase
{
    @Autowired
    private RecipeDao _recipeDao;

    @Test
    @Sql(value = "/sql/create_recipes.sql")
    @SqlMergeMode(MERGE)
    public void testGetAllRecipes_happyPath()
    {
        // When
        final var recipes = _recipeDao.findAll();

        // Then
        // just checking the titles
        assertThat(recipes.stream().map(Recipe::name).toList())
                .hasSameElementsAs(List.of(
                        "Recipe 1",
                        "Another fantastic recipe",
                        "A disgusting soup"));
    }

    @Test
    @Sql(value = "/sql/create_recipes.sql")
    @SqlMergeMode(MERGE)
    public void testGetRecipeById_happyPath()
    {
        // When
        final var recipe = _recipeDao.findById(_recipeDao.findAll().getFirst().id());

        // Then
        assertThat(recipe)
                .usingRecursiveComparison()
                .ignoringFieldsOfTypes(UUID.class)
                .isEqualTo(new Recipe(
                        null,
                        "Recipe 1",
                        4,
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer eget ullamcorper enim. "
                                + "Nulla pharetra neque arcu, vitae sagittis augue auctor non. Nulla bibendum risus eget diam "
                                + "bibendum maximus.",
                        null,
                        null,
                        "medium",
                        20,
                        null,
                        "Vestibulum lectus nisl, convallis placerat massa at, lacinia vestibulum mi. "
                                + "Mauris consectetur ipsum eu lobortis feugiat."));
    }
}
