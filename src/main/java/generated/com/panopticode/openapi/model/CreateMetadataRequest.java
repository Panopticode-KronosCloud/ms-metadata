package com.panopticode.openapi.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.panopticode.openapi.model.Kind;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import java.util.NoSuchElementException;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Metadata record
 */

@Schema(name = "createMetadata_request", description = "Metadata record")
@JsonTypeName("createMetadata_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-26T19:03:14.882484979Z[Europe/London]", comments = "Generator version: 7.18.0")
public class CreateMetadataRequest {

  private JsonNullable<UUID> parentId = JsonNullable.<UUID>undefined();

  private Kind kind;

  private String name;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime created;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime lastModified;

  private JsonNullable<String> suggestedMediaType = JsonNullable.<String>undefined();

  @Valid
  private JsonNullable<Map<String, Object>> metadata = JsonNullable.<Map<String, Object>>undefined();

  private Boolean rawAccess = false;

  public CreateMetadataRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateMetadataRequest(Kind kind, String name, OffsetDateTime created, OffsetDateTime lastModified) {
    this.kind = kind;
    this.name = name;
    this.created = created;
    this.lastModified = lastModified;
  }

  public CreateMetadataRequest parentId(UUID parentId) {
    this.parentId = JsonNullable.of(parentId);
    return this;
  }

  /**
   * Get parentId
   * @return parentId
   */
  @Valid 
  @Schema(name = "parent_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("parent_id")
  public JsonNullable<UUID> getParentId() {
    return parentId;
  }

  public void setParentId(JsonNullable<UUID> parentId) {
    this.parentId = parentId;
  }

  public CreateMetadataRequest kind(Kind kind) {
    this.kind = kind;
    return this;
  }

  /**
   * Get kind
   * @return kind
   */
  @NotNull @Valid 
  @Schema(name = "kind", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("kind")
  public Kind getKind() {
    return kind;
  }

  public void setKind(Kind kind) {
    this.kind = kind;
  }

  public CreateMetadataRequest name(String name) {
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

  public CreateMetadataRequest created(OffsetDateTime created) {
    this.created = created;
    return this;
  }

  /**
   * Get created
   * @return created
   */
  @NotNull @Valid 
  @Schema(name = "created", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("created")
  public OffsetDateTime getCreated() {
    return created;
  }

  public void setCreated(OffsetDateTime created) {
    this.created = created;
  }

  public CreateMetadataRequest lastModified(OffsetDateTime lastModified) {
    this.lastModified = lastModified;
    return this;
  }

  /**
   * Get lastModified
   * @return lastModified
   */
  @NotNull @Valid 
  @Schema(name = "last_modified", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("last_modified")
  public OffsetDateTime getLastModified() {
    return lastModified;
  }

  public void setLastModified(OffsetDateTime lastModified) {
    this.lastModified = lastModified;
  }

  public CreateMetadataRequest suggestedMediaType(String suggestedMediaType) {
    this.suggestedMediaType = JsonNullable.of(suggestedMediaType);
    return this;
  }

  /**
   * Suggested media type. Might be refined automatically.
   * @return suggestedMediaType
   */
  
  @Schema(name = "suggested_media_type", description = "Suggested media type. Might be refined automatically.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("suggested_media_type")
  public JsonNullable<String> getSuggestedMediaType() {
    return suggestedMediaType;
  }

  public void setSuggestedMediaType(JsonNullable<String> suggestedMediaType) {
    this.suggestedMediaType = suggestedMediaType;
  }

  public CreateMetadataRequest metadata(Map<String, Object> metadata) {
    this.metadata = JsonNullable.of(metadata);
    return this;
  }

  public CreateMetadataRequest putMetadataItem(String key, Object metadataItem) {
    if (this.metadata == null || !this.metadata.isPresent()) {
      this.metadata = JsonNullable.of(new HashMap<>());
    }
    this.metadata.get().put(key, metadataItem);
    return this;
  }

  /**
   * Get metadata
   * @return metadata
   */
  
  @Schema(name = "metadata", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("metadata")
  public JsonNullable<Map<String, Object>> getMetadata() {
    return metadata;
  }

  public void setMetadata(JsonNullable<Map<String, Object>> metadata) {
    this.metadata = metadata;
  }

  public CreateMetadataRequest rawAccess(Boolean rawAccess) {
    this.rawAccess = rawAccess;
    return this;
  }

  /**
   * Get rawAccess
   * @return rawAccess
   */
  
  @Schema(name = "raw_access", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("raw_access")
  public Boolean isRawAccess() {
    return rawAccess;
  }

  public void setRawAccess(Boolean rawAccess) {
    this.rawAccess = rawAccess;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateMetadataRequest createMetadataRequest = (CreateMetadataRequest) o;
    return equalsNullable(this.parentId, createMetadataRequest.parentId) &&
        Objects.equals(this.kind, createMetadataRequest.kind) &&
        Objects.equals(this.name, createMetadataRequest.name) &&
        Objects.equals(this.created, createMetadataRequest.created) &&
        Objects.equals(this.lastModified, createMetadataRequest.lastModified) &&
        equalsNullable(this.suggestedMediaType, createMetadataRequest.suggestedMediaType) &&
        equalsNullable(this.metadata, createMetadataRequest.metadata) &&
        Objects.equals(this.rawAccess, createMetadataRequest.rawAccess);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(hashCodeNullable(parentId), kind, name, created, lastModified, hashCodeNullable(suggestedMediaType), hashCodeNullable(metadata), rawAccess);
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateMetadataRequest {\n");
    sb.append("    parentId: ").append(toIndentedString(parentId)).append("\n");
    sb.append("    kind: ").append(toIndentedString(kind)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    lastModified: ").append(toIndentedString(lastModified)).append("\n");
    sb.append("    suggestedMediaType: ").append(toIndentedString(suggestedMediaType)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    rawAccess: ").append(toIndentedString(rawAccess)).append("\n");
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

