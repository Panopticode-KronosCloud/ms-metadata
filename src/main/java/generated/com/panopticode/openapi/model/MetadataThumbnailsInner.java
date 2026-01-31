package com.panopticode.openapi.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * MetadataThumbnailsInner
 */

@JsonTypeName("Metadata_thumbnails_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-26T19:03:14.882484979Z[Europe/London]", comments = "Generator version: 7.18.0")
public class MetadataThumbnailsInner {

  private Integer width;

  private Integer height;

  public MetadataThumbnailsInner() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public MetadataThumbnailsInner(Integer width, Integer height) {
    this.width = width;
    this.height = height;
  }

  public MetadataThumbnailsInner width(Integer width) {
    this.width = width;
    return this;
  }

  /**
   * Get width
   * minimum: 0
   * @return width
   */
  @NotNull @Min(value = 0) 
  @Schema(name = "width", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("width")
  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public MetadataThumbnailsInner height(Integer height) {
    this.height = height;
    return this;
  }

  /**
   * Get height
   * minimum: 0
   * @return height
   */
  @NotNull @Min(value = 0) 
  @Schema(name = "height", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("height")
  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataThumbnailsInner metadataThumbnailsInner = (MetadataThumbnailsInner) o;
    return Objects.equals(this.width, metadataThumbnailsInner.width) &&
        Objects.equals(this.height, metadataThumbnailsInner.height);
  }

  @Override
  public int hashCode() {
    return Objects.hash(width, height);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MetadataThumbnailsInner {\n");
    sb.append("    width: ").append(toIndentedString(width)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
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

