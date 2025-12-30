package com.panopticode.openapi.api;

import com.panopticode.openapi.model.ErrorModel;
import com.panopticode.openapi.model.Ingredient;
import com.panopticode.openapi.model.Recipe;
import com.panopticode.openapi.model.RecipeRef;
import com.panopticode.openapi.model.RequiredRecipeBody;
import java.util.UUID;
import com.panopticode.openapi.model.UpdateRecipeRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

/**
 * A delegate to be called by the {@link CookbookApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-28T03:09:42.850562636Z[Europe/London]", comments = "Generator version: 7.18.0")
public interface CookbookApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /recipes/{recipeId}/ingredients : Add new ingredient
     * Add a new ingredient to a recipe
     *
     * @param recipeId Recipe id (required)
     * @param ingredient Data object to save a new ingredient (required)
     * @return successful operation (status code 201)
     *         or Invalid input (status code 405)
     *         or Unexpected error (status code 200)
     * @see CookbookApi#addIngredient
     */
    default ResponseEntity<Ingredient> addIngredient(UUID recipeId,
        Ingredient ingredient) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"unit\" : \"unit\", \"quantity\" : 6.0274563, \"name\" : \"name\", \"index\" : 0, \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 1, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 1, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /recipes : Add new recipe
     * Add a new recipe to the book
     *
     * @param requiredRecipeBody Data object to save a new recipe (required)
     * @return successful operation (status code 201)
     *         or Invalid input (status code 405)
     *         or Unexpected error (status code 200)
     * @see CookbookApi#addRecipe
     */
    default ResponseEntity<Recipe> addRecipe(RequiredRecipeBody requiredRecipeBody) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"textIntro\" : \"textIntro\", \"difficulty\" : \"easy\", \"servings\" : 0, \"thumbnail\" : \"thumbnail\", \"name\" : \"name\", \"prepTimeM\" : 6, \"textOutro\" : \"textOutro\", \"ingredients\" : [ { \"unit\" : \"unit\", \"quantity\" : 6.0274563, \"name\" : \"name\", \"index\" : 0, \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"unit\" : \"unit\", \"quantity\" : 6.0274563, \"name\" : \"name\", \"index\" : 0, \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"cookTimeM\" : 1, \"picture\" : \"picture\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 1, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 1, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /recipes/{recipeId}/ingredients : Deletes all ingredients
     * Deletes all ingredients off a recipe
     *
     * @param recipeId Recipe id (required)
     * @return Deletion completed successfully (status code 204)
     *         or Invalid recipe id (status code 404)
     *         or Unexpected error (status code 200)
     * @see CookbookApi#deleteAllIngredients
     */
    default ResponseEntity<Void> deleteAllIngredients(UUID recipeId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 1, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 1, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /recipes/{recipeId}/ingredients/{ingredientId} : Deletes a ingredient
     * Deletes an existing ingredient off a recipe
     *
     * @param recipeId Recipe id (required)
     * @param ingredientId Ingredient id to delete (required)
     * @return Deletion completed successfully (status code 204)
     *         or Invalid recipe or ingredient id (status code 404)
     *         or Unexpected error (status code 200)
     * @see CookbookApi#deleteIngredient
     */
    default ResponseEntity<Void> deleteIngredient(UUID recipeId,
        UUID ingredientId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 1, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 1, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /recipes/{recipeId} : Deletes a recipe
     * Deletes an existing recipe from the cookbook
     *
     * @param recipeId Recipe id to delete (required)
     * @return Deletion completed successfully (status code 204)
     *         or Invalid recipe id (status code 404)
     *         or Unexpected error (status code 200)
     * @see CookbookApi#deleteRecipe
     */
    default ResponseEntity<Void> deleteRecipe(UUID recipeId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 1, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 1, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /recipes/{recipeId}/ingredients : Get all ingredients
     * Returns all ingredients related to a recipe
     *
     * @param recipeId Recipe id (required)
     * @return List of ingredients (status code 200)
     *         or Unexpected error (status code 200)
     * @see CookbookApi#getAllRecipeIngredients
     */
    default ResponseEntity<List<Ingredient>> getAllRecipeIngredients(UUID recipeId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"unit\" : \"unit\", \"quantity\" : 6.0274563, \"name\" : \"name\", \"index\" : 0, \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"unit\" : \"unit\", \"quantity\" : 6.0274563, \"name\" : \"name\", \"index\" : 0, \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 1, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /recipes : Get all recipes
     * Returns all recipes in the cookbook
     *
     * @return List of recipes (status code 200)
     *         or Unexpected error (status code 200)
     * @see CookbookApi#getAllRecipes
     */
    default ResponseEntity<List<RecipeRef>> getAllRecipes() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"title\" : \"title\" }, { \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"title\" : \"title\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 1, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /recipes/{recipeId} : Get a recipe
     * Returns a recipe by ID
     *
     * @param recipeId ID of the recipe to return (required)
     * @return Recipe (status code 200)
     *         or Invalid ID supplied (status code 400)
     *         or Recipe not found (status code 404)
     *         or Unexpected error (status code 200)
     * @see CookbookApi#getRecipeById
     */
    default ResponseEntity<Recipe> getRecipeById(UUID recipeId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"textIntro\" : \"textIntro\", \"difficulty\" : \"easy\", \"servings\" : 0, \"thumbnail\" : \"thumbnail\", \"name\" : \"name\", \"prepTimeM\" : 6, \"textOutro\" : \"textOutro\", \"ingredients\" : [ { \"unit\" : \"unit\", \"quantity\" : 6.0274563, \"name\" : \"name\", \"index\" : 0, \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"unit\" : \"unit\", \"quantity\" : 6.0274563, \"name\" : \"name\", \"index\" : 0, \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"cookTimeM\" : 1, \"picture\" : \"picture\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 1, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 1, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 1, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /recipes/{recipeId}/ingredients/{ingredientId} : Update ingredient
     * Update an existing ingredient
     *
     * @param recipeId Recipe id (required)
     * @param ingredientId Ingredient id to update (required)
     * @return successful operation (status code 200)
     *         or Invalid ID supplied (status code 400)
     *         or Recipe or ingredient not found (status code 404)
     *         or Validation exception (status code 405)
     *         or Unexpected error (status code 200)
     * @see CookbookApi#updateIngredient
     */
    default ResponseEntity<Ingredient> updateIngredient(UUID recipeId,
        UUID ingredientId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"unit\" : \"unit\", \"quantity\" : 6.0274563, \"name\" : \"name\", \"index\" : 0, \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 1, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 1, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 1, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 1, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /recipes/{recipeId} : Update recipe
     * Update an existing recipe
     *
     * @param recipeId Recipe id to update (required)
     * @param updateRecipeRequest Data object to update an existing recipe (required)
     * @return successful operation (status code 200)
     *         or Invalid ID supplied (status code 400)
     *         or Recipe not found (status code 404)
     *         or Validation exception (status code 405)
     *         or Unexpected error (status code 200)
     * @see CookbookApi#updateRecipe
     */
    default ResponseEntity<RecipeRef> updateRecipe(UUID recipeId,
        UpdateRecipeRequest updateRecipeRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"title\" : \"title\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 1, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 1, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 1, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 1, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
