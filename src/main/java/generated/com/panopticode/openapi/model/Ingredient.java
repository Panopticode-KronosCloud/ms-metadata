package com.panopticode.openapi.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.UUID;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Recipe ingredient
 */

@Schema(name = "Ingredient", description = "Recipe ingredient")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-28T03:09:42.850562636Z[Europe/London]", comments = "Generator version: 7.18.0")
public class Ingredient {

  private @Nullable UUID id;

  private String name;

  private Integer index;

  private @Nullable Float quantity;

  private @Nullable String unit;

  public Ingredient() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Ingredient(String name, Integer index) {
    this.name = name;
    this.index = index;
  }

  public Ingredient id(@Nullable UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @Valid 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public @Nullable UUID getId() {
    return id;
  }

  public void setId(@Nullable UUID id) {
    this.id = id;
  }

  public Ingredient name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @NotNull 
  @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Ingredient index(Integer index) {
    this.index = index;
    return this;
  }

  /**
   * Get index
   * @return index
   */
  @NotNull 
  @Schema(name = "index", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("index")
  public Integer getIndex() {
    return index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }

  public Ingredient quantity(@Nullable Float quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Get quantity
   * @return quantity
   */
  
  @Schema(name = "quantity", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("quantity")
  public @Nullable Float getQuantity() {
    return quantity;
  }

  public void setQuantity(@Nullable Float quantity) {
    this.quantity = quantity;
  }

  public Ingredient unit(@Nullable String unit) {
    this.unit = unit;
    return this;
  }

  /**
   * Get unit
   * @return unit
   */
  
  @Schema(name = "unit", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("unit")
  public @Nullable String getUnit() {
    return unit;
  }

  public void setUnit(@Nullable String unit) {
    this.unit = unit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ingredient ingredient = (Ingredient) o;
    return Objects.equals(this.id, ingredient.id) &&
        Objects.equals(this.name, ingredient.name) &&
        Objects.equals(this.index, ingredient.index) &&
        Objects.equals(this.quantity, ingredient.quantity) &&
        Objects.equals(this.unit, ingredient.unit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, index, quantity, unit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ingredient {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    index: ").append(toIndentedString(index)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    unit: ").append(toIndentedString(unit)).append("\n");
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

