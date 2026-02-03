package com.panopticode.openapi.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.panopticode.openapi.model.Kind;
import com.panopticode.openapi.model.MetadataConsolidationInfo;
import com.panopticode.openapi.model.MetadataHashesInner;
import com.panopticode.openapi.model.MetadataThumbnailsInner;
import java.time.OffsetDateTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

@Schema(name = "Metadata", description = "Metadata record")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-03T01:21:35.129907262Z[Europe/London]", comments = "Generator version: 7.18.0")
public class Metadata {

  private UUID id;

  private JsonNullable<UUID> parentId = JsonNullable.<UUID>undefined();

  private Kind kind;

  private JsonNullable<String> blobType = JsonNullable.<String>undefined();

  private JsonNullable<String> blobRef = JsonNullable.<String>undefined();

  private String name;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime created;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime lastModified;

  private JsonNullable<Long> sizeBytes = JsonNullable.<Long>undefined();

  private JsonNullable<String> mediaType = JsonNullable.<String>undefined();

  @Valid
  private JsonNullable<Map<String, Object>> metadata = JsonNullable.<Map<String, Object>>undefined();

  @Valid
  private JsonNullable<List<@Valid MetadataHashesInner>> hashes = JsonNullable.<List<@Valid MetadataHashesInner>>undefined();

  @Valid
  private JsonNullable<List<@Valid MetadataThumbnailsInner>> thumbnails = JsonNullable.<List<@Valid MetadataThumbnailsInner>>undefined();

  private JsonNullable<MetadataConsolidationInfo> consolidationInfo = JsonNullable.<MetadataConsolidationInfo>undefined();

  private Boolean rawAccess = false;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    UNAVAILABLE("unavailable"),
    
    DELETED("deleted"),
    
    STAGED("staged"),
    
    ACTIVE("active");

    private final String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private StatusEnum status;

  public Metadata() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Metadata(UUID id, Kind kind, String name, LocalDateTime created, LocalDateTime lastModified, Boolean rawAccess, StatusEnum status) {
    this.id = id;
    this.kind = kind;
    this.name = name;
    this.created = created;
    this.lastModified = lastModified;
    this.rawAccess = rawAccess;
    this.status = status;
  }

  public Metadata id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @NotNull @Valid 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Metadata parentId(UUID parentId) {
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

  public Metadata kind(Kind kind) {
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

  public Metadata blobType(String blobType) {
    this.blobType = JsonNullable.of(blobType);
    return this;
  }

  /**
   * Get blobType
   * @return blobType
   */
  
  @Schema(name = "blob_type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("blob_type")
  public JsonNullable<String> getBlobType() {
    return blobType;
  }

  public void setBlobType(JsonNullable<String> blobType) {
    this.blobType = blobType;
  }

  public Metadata blobRef(String blobRef) {
    this.blobRef = JsonNullable.of(blobRef);
    return this;
  }

  /**
   * Get blobRef
   * @return blobRef
   */
  
  @Schema(name = "blob_ref", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("blob_ref")
  public JsonNullable<String> getBlobRef() {
    return blobRef;
  }

  public void setBlobRef(JsonNullable<String> blobRef) {
    this.blobRef = blobRef;
  }

  public Metadata name(String name) {
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

  public Metadata created(LocalDateTime created) {
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
  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public Metadata lastModified(LocalDateTime lastModified) {
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
  public LocalDateTime getLastModified() {
    return lastModified;
  }

  public void setLastModified(LocalDateTime lastModified) {
    this.lastModified = lastModified;
  }

  public Metadata sizeBytes(Long sizeBytes) {
    this.sizeBytes = JsonNullable.of(sizeBytes);
    return this;
  }

  /**
   * Get sizeBytes
   * @return sizeBytes
   */
  
  @Schema(name = "size_bytes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("size_bytes")
  public JsonNullable<Long> getSizeBytes() {
    return sizeBytes;
  }

  public void setSizeBytes(JsonNullable<Long> sizeBytes) {
    this.sizeBytes = sizeBytes;
  }

  public Metadata mediaType(String mediaType) {
    this.mediaType = JsonNullable.of(mediaType);
    return this;
  }

  /**
   * Get mediaType
   * @return mediaType
   */
  
  @Schema(name = "media_type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("media_type")
  public JsonNullable<String> getMediaType() {
    return mediaType;
  }

  public void setMediaType(JsonNullable<String> mediaType) {
    this.mediaType = mediaType;
  }

  public Metadata metadata(Map<String, Object> metadata) {
    this.metadata = JsonNullable.of(metadata);
    return this;
  }

  public Metadata putMetadataItem(String key, Object metadataItem) {
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

  public Metadata hashes(List<@Valid MetadataHashesInner> hashes) {
    this.hashes = JsonNullable.of(hashes);
    return this;
  }

  public Metadata addHashesItem(MetadataHashesInner hashesItem) {
    if (this.hashes == null || !this.hashes.isPresent()) {
      this.hashes = JsonNullable.of(new ArrayList<>());
    }
    this.hashes.get().add(hashesItem);
    return this;
  }

  /**
   * Get hashes
   * @return hashes
   */
  @Valid 
  @Schema(name = "hashes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("hashes")
  public JsonNullable<List<@Valid MetadataHashesInner>> getHashes() {
    return hashes;
  }

  public void setHashes(JsonNullable<List<@Valid MetadataHashesInner>> hashes) {
    this.hashes = hashes;
  }

  public Metadata thumbnails(List<@Valid MetadataThumbnailsInner> thumbnails) {
    this.thumbnails = JsonNullable.of(thumbnails);
    return this;
  }

  public Metadata addThumbnailsItem(MetadataThumbnailsInner thumbnailsItem) {
    if (this.thumbnails == null || !this.thumbnails.isPresent()) {
      this.thumbnails = JsonNullable.of(new ArrayList<>());
    }
    this.thumbnails.get().add(thumbnailsItem);
    return this;
  }

  /**
   * Get thumbnails
   * @return thumbnails
   */
  @Valid 
  @Schema(name = "thumbnails", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("thumbnails")
  public JsonNullable<List<@Valid MetadataThumbnailsInner>> getThumbnails() {
    return thumbnails;
  }

  public void setThumbnails(JsonNullable<List<@Valid MetadataThumbnailsInner>> thumbnails) {
    this.thumbnails = thumbnails;
  }

  public Metadata consolidationInfo(MetadataConsolidationInfo consolidationInfo) {
    this.consolidationInfo = JsonNullable.of(consolidationInfo);
    return this;
  }

  /**
   * Get consolidationInfo
   * @return consolidationInfo
   */
  @Valid 
  @Schema(name = "consolidation_info", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("consolidation_info")
  public JsonNullable<MetadataConsolidationInfo> getConsolidationInfo() {
    return consolidationInfo;
  }

  public void setConsolidationInfo(JsonNullable<MetadataConsolidationInfo> consolidationInfo) {
    this.consolidationInfo = consolidationInfo;
  }

  public Metadata rawAccess(Boolean rawAccess) {
    this.rawAccess = rawAccess;
    return this;
  }

  /**
   * Get rawAccess
   * @return rawAccess
   */
  @NotNull 
  @Schema(name = "raw_access", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("raw_access")
  public Boolean isRawAccess() {
    return rawAccess;
  }

  public void setRawAccess(Boolean rawAccess) {
    this.rawAccess = rawAccess;
  }

  public Metadata status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   */
  @NotNull 
  @Schema(name = "status", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Metadata metadata = (Metadata) o;
    return Objects.equals(this.id, metadata.id) &&
        equalsNullable(this.parentId, metadata.parentId) &&
        Objects.equals(this.kind, metadata.kind) &&
        equalsNullable(this.blobType, metadata.blobType) &&
        equalsNullable(this.blobRef, metadata.blobRef) &&
        Objects.equals(this.name, metadata.name) &&
        Objects.equals(this.created, metadata.created) &&
        Objects.equals(this.lastModified, metadata.lastModified) &&
        equalsNullable(this.sizeBytes, metadata.sizeBytes) &&
        equalsNullable(this.mediaType, metadata.mediaType) &&
        equalsNullable(this.metadata, metadata.metadata) &&
        equalsNullable(this.hashes, metadata.hashes) &&
        equalsNullable(this.thumbnails, metadata.thumbnails) &&
        equalsNullable(this.consolidationInfo, metadata.consolidationInfo) &&
        Objects.equals(this.rawAccess, metadata.rawAccess) &&
        Objects.equals(this.status, metadata.status);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, hashCodeNullable(parentId), kind, hashCodeNullable(blobType), hashCodeNullable(blobRef), name, created, lastModified, hashCodeNullable(sizeBytes), hashCodeNullable(mediaType), hashCodeNullable(metadata), hashCodeNullable(hashes), hashCodeNullable(thumbnails), hashCodeNullable(consolidationInfo), rawAccess, status);
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
    sb.append("class Metadata {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    parentId: ").append(toIndentedString(parentId)).append("\n");
    sb.append("    kind: ").append(toIndentedString(kind)).append("\n");
    sb.append("    blobType: ").append(toIndentedString(blobType)).append("\n");
    sb.append("    blobRef: ").append(toIndentedString(blobRef)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    lastModified: ").append(toIndentedString(lastModified)).append("\n");
    sb.append("    sizeBytes: ").append(toIndentedString(sizeBytes)).append("\n");
    sb.append("    mediaType: ").append(toIndentedString(mediaType)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    hashes: ").append(toIndentedString(hashes)).append("\n");
    sb.append("    thumbnails: ").append(toIndentedString(thumbnails)).append("\n");
    sb.append("    consolidationInfo: ").append(toIndentedString(consolidationInfo)).append("\n");
    sb.append("    rawAccess: ").append(toIndentedString(rawAccess)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

