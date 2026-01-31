package com.panopticode.openapi.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * MetadataConsolidationInfo
 */

@JsonTypeName("Metadata_consolidation_info")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-26T19:03:14.882484979Z[Europe/London]", comments = "Generator version: 7.18.0")
public class MetadataConsolidationInfo {

  private String version;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime consolidatedAt;

  public MetadataConsolidationInfo() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public MetadataConsolidationInfo(String version, OffsetDateTime consolidatedAt) {
    this.version = version;
    this.consolidatedAt = consolidatedAt;
  }

  public MetadataConsolidationInfo version(String version) {
    this.version = version;
    return this;
  }

  /**
   * Get version
   * @return version
   */
  @NotNull 
  @Schema(name = "version", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("version")
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public MetadataConsolidationInfo consolidatedAt(OffsetDateTime consolidatedAt) {
    this.consolidatedAt = consolidatedAt;
    return this;
  }

  /**
   * Get consolidatedAt
   * @return consolidatedAt
   */
  @NotNull @Valid 
  @Schema(name = "consolidated_at", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("consolidated_at")
  public OffsetDateTime getConsolidatedAt() {
    return consolidatedAt;
  }

  public void setConsolidatedAt(OffsetDateTime consolidatedAt) {
    this.consolidatedAt = consolidatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataConsolidationInfo metadataConsolidationInfo = (MetadataConsolidationInfo) o;
    return Objects.equals(this.version, metadataConsolidationInfo.version) &&
        Objects.equals(this.consolidatedAt, metadataConsolidationInfo.consolidatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(version, consolidatedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MetadataConsolidationInfo {\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    consolidatedAt: ").append(toIndentedString(consolidatedAt)).append("\n");
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

