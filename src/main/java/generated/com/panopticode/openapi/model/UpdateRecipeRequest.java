package com.panopticode.openapi.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.panopticode.openapi.model.OptionalRecipeBody;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * UpdateRecipeRequest
 */

@JsonTypeName("updateRecipe_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-28T03:09:42.850562636Z[Europe/London]", comments = "Generator version: 7.18.0")
public class UpdateRecipeRequest {

  private @Nullable OptionalRecipeBody toUpdate;

  private @Nullable OptionalRecipeBody toDelete;

  public UpdateRecipeRequest toUpdate(@Nullable OptionalRecipeBody toUpdate) {
    this.toUpdate = toUpdate;
    return this;
  }

  /**
   * Get toUpdate
   * @return toUpdate
   */
  @Valid 
  @Schema(name = "toUpdate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("toUpdate")
  public @Nullable OptionalRecipeBody getToUpdate() {
    return toUpdate;
  }

  public void setToUpdate(@Nullable OptionalRecipeBody toUpdate) {
    this.toUpdate = toUpdate;
  }

  public UpdateRecipeRequest toDelete(@Nullable OptionalRecipeBody toDelete) {
    this.toDelete = toDelete;
    return this;
  }

  /**
   * Get toDelete
   * @return toDelete
   */
  @Valid 
  @Schema(name = "toDelete", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("toDelete")
  public @Nullable OptionalRecipeBody getToDelete() {
    return toDelete;
  }

  public void setToDelete(@Nullable OptionalRecipeBody toDelete) {
    this.toDelete = toDelete;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateRecipeRequest updateRecipeRequest = (UpdateRecipeRequest) o;
    return Objects.equals(this.toUpdate, updateRecipeRequest.toUpdate) &&
        Objects.equals(this.toDelete, updateRecipeRequest.toDelete);
  }

  @Override
  public int hashCode() {
    return Objects.hash(toUpdate, toDelete);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateRecipeRequest {\n");
    sb.append("    toUpdate: ").append(toIndentedString(toUpdate)).append("\n");
    sb.append("    toDelete: ").append(toIndentedString(toDelete)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

