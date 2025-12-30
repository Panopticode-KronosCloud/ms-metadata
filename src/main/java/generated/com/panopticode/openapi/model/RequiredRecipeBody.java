package com.panopticode.openapi.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.panopticode.openapi.model.Difficulty;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Lighter recipe object, required fields
 */

@Schema(name = "RequiredRecipeBody", description = "Lighter recipe object, required fields")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-28T03:09:42.850562636Z[Europe/London]", comments = "Generator version: 7.18.0")
public class RequiredRecipeBody {

  private String name;

  private Integer servings;

  private @Nullable String textIntro;

  private @Nullable String picture;

  private @Nullable String thumbnail;

  private Difficulty difficulty;

  private Integer prepTimeM;

  private @Nullable Integer cookTimeM;

  private @Nullable String textOutro;

  public RequiredRecipeBody() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RequiredRecipeBody(String name, Integer servings, Difficulty difficulty, Integer prepTimeM) {
    this.name = name;
    this.servings = servings;
    this.difficulty = difficulty;
    this.prepTimeM = prepTimeM;
  }

  public RequiredRecipeBody name(String name) {
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

  public RequiredRecipeBody servings(Integer servings) {
    this.servings = servings;
    return this;
  }

  /**
   * Get servings
   * @return servings
   */
  @NotNull 
  @Schema(name = "servings", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("servings")
  public Integer getServings() {
    return servings;
  }

  public void setServings(Integer servings) {
    this.servings = servings;
  }

  public RequiredRecipeBody textIntro(@Nullable String textIntro) {
    this.textIntro = textIntro;
    return this;
  }

  /**
   * Get textIntro
   * @return textIntro
   */
  
  @Schema(name = "textIntro", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("textIntro")
  public @Nullable String getTextIntro() {
    return textIntro;
  }

  public void setTextIntro(@Nullable String textIntro) {
    this.textIntro = textIntro;
  }

  public RequiredRecipeBody picture(@Nullable String picture) {
    this.picture = picture;
    return this;
  }

  /**
   * Get picture
   * @return picture
   */
  
  @Schema(name = "picture", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("picture")
  public @Nullable String getPicture() {
    return picture;
  }

  public void setPicture(@Nullable String picture) {
    this.picture = picture;
  }

  public RequiredRecipeBody thumbnail(@Nullable String thumbnail) {
    this.thumbnail = thumbnail;
    return this;
  }

  /**
   * Get thumbnail
   * @return thumbnail
   */
  
  @Schema(name = "thumbnail", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("thumbnail")
  public @Nullable String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(@Nullable String thumbnail) {
    this.thumbnail = thumbnail;
  }

  public RequiredRecipeBody difficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
    return this;
  }

  /**
   * Get difficulty
   * @return difficulty
   */
  @NotNull @Valid 
  @Schema(name = "difficulty", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("difficulty")
  public Difficulty getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
  }

  public RequiredRecipeBody prepTimeM(Integer prepTimeM) {
    this.prepTimeM = prepTimeM;
    return this;
  }

  /**
   * Get prepTimeM
   * @return prepTimeM
   */
  @NotNull 
  @Schema(name = "prepTimeM", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("prepTimeM")
  public Integer getPrepTimeM() {
    return prepTimeM;
  }

  public void setPrepTimeM(Integer prepTimeM) {
    this.prepTimeM = prepTimeM;
  }

  public RequiredRecipeBody cookTimeM(@Nullable Integer cookTimeM) {
    this.cookTimeM = cookTimeM;
    return this;
  }

  /**
   * Get cookTimeM
   * @return cookTimeM
   */
  
  @Schema(name = "cookTimeM", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cookTimeM")
  public @Nullable Integer getCookTimeM() {
    return cookTimeM;
  }

  public void setCookTimeM(@Nullable Integer cookTimeM) {
    this.cookTimeM = cookTimeM;
  }

  public RequiredRecipeBody textOutro(@Nullable String textOutro) {
    this.textOutro = textOutro;
    return this;
  }

  /**
   * Get textOutro
   * @return textOutro
   */
  
  @Schema(name = "textOutro", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("textOutro")
  public @Nullable String getTextOutro() {
    return textOutro;
  }

  public void setTextOutro(@Nullable String textOutro) {
    this.textOutro = textOutro;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequiredRecipeBody requiredRecipeBody = (RequiredRecipeBody) o;
    return Objects.equals(this.name, requiredRecipeBody.name) &&
        Objects.equals(this.servings, requiredRecipeBody.servings) &&
        Objects.equals(this.textIntro, requiredRecipeBody.textIntro) &&
        Objects.equals(this.picture, requiredRecipeBody.picture) &&
        Objects.equals(this.thumbnail, requiredRecipeBody.thumbnail) &&
        Objects.equals(this.difficulty, requiredRecipeBody.difficulty) &&
        Objects.equals(this.prepTimeM, requiredRecipeBody.prepTimeM) &&
        Objects.equals(this.cookTimeM, requiredRecipeBody.cookTimeM) &&
        Objects.equals(this.textOutro, requiredRecipeBody.textOutro);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, servings, textIntro, picture, thumbnail, difficulty, prepTimeM, cookTimeM, textOutro);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RequiredRecipeBody {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    servings: ").append(toIndentedString(servings)).append("\n");
    sb.append("    textIntro: ").append(toIndentedString(textIntro)).append("\n");
    sb.append("    picture: ").append(toIndentedString(picture)).append("\n");
    sb.append("    thumbnail: ").append(toIndentedString(thumbnail)).append("\n");
    sb.append("    difficulty: ").append(toIndentedString(difficulty)).append("\n");
    sb.append("    prepTimeM: ").append(toIndentedString(prepTimeM)).append("\n");
    sb.append("    cookTimeM: ").append(toIndentedString(cookTimeM)).append("\n");
    sb.append("    textOutro: ").append(toIndentedString(textOutro)).append("\n");
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

