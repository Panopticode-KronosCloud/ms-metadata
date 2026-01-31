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
 * MetadataHashesInner
 */

@JsonTypeName("Metadata_hashes_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-26T19:03:14.882484979Z[Europe/London]", comments = "Generator version: 7.18.0")
public class MetadataHashesInner {

  private @Nullable String algorithm;

  private @Nullable String encoding;

  private @Nullable String value;

  public MetadataHashesInner algorithm(@Nullable String algorithm) {
    this.algorithm = algorithm;
    return this;
  }

  /**
   * Get algorithm
   * @return algorithm
   */
  
  @Schema(name = "algorithm", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("algorithm")
  public @Nullable String getAlgorithm() {
    return algorithm;
  }

  public void setAlgorithm(@Nullable String algorithm) {
    this.algorithm = algorithm;
  }

  public MetadataHashesInner encoding(@Nullable String encoding) {
    this.encoding = encoding;
    return this;
  }

  /**
   * Get encoding
   * @return encoding
   */
  
  @Schema(name = "encoding", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("encoding")
  public @Nullable String getEncoding() {
    return encoding;
  }

  public void setEncoding(@Nullable String encoding) {
    this.encoding = encoding;
  }

  public MetadataHashesInner value(@Nullable String value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
   */
  
  @Schema(name = "value", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("value")
  public @Nullable String getValue() {
    return value;
  }

  public void setValue(@Nullable String value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataHashesInner metadataHashesInner = (MetadataHashesInner) o;
    return Objects.equals(this.algorithm, metadataHashesInner.algorithm) &&
        Objects.equals(this.encoding, metadataHashesInner.encoding) &&
        Objects.equals(this.value, metadataHashesInner.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(algorithm, encoding, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MetadataHashesInner {\n");
    sb.append("    algorithm: ").append(toIndentedString(algorithm)).append("\n");
    sb.append("    encoding: ").append(toIndentedString(encoding)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

