package com.panopticode.openapi.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
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

@Schema(name = "Metadata", description = "Metadata record")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-31T15:08:48.518121375Z[Europe/London]", comments = "Generator version: 7.18.0")
public class Metadata {

  private UUID id;

  private JsonNullable<UUID> parentId = JsonNullable.<UUID>undefined();

  /**
   * Gets or Sets kind
   */
  public enum KindEnum {
    FILE("file"),
    
    DIRECTORY("directory");

    private final String value;

    KindEnum(String value) {
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
    public static KindEnum fromValue(String value) {
      for (KindEnum b : KindEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private KindEnum kind;

  private JsonNullable<String> blobType = JsonNullable.<String>undefined();

  private JsonNullable<String> blobRef = JsonNullable.<String>undefined();

  private String name;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime created;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime lastModified;

  private JsonNullable<Long> sizeB = JsonNullable.<Long>undefined();

  private JsonNullable<String> mediaType = JsonNullable.<String>undefined();

  @Valid
  private JsonNullable<Map<String, Object>> metadata = JsonNullable.<Map<String, Object>>undefined();

  private JsonNullable<String> hashSha3256 = JsonNullable.<String>undefined();

  private JsonNullable<String> thumbnail = JsonNullable.<String>undefined();

  private JsonNullable<String> consolidateV = JsonNullable.<String>undefined();

  private Boolean rawAccess = false;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    UNAVAILABLE("unavailable"),
    
    DELETED("deleted"),
    
    STAGED("staged"),
    
    READY("ready");

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
  public Metadata(UUID id, KindEnum kind, String name, OffsetDateTime created, OffsetDateTime lastModified, Boolean rawAccess, StatusEnum status) {
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

  public Metadata kind(KindEnum kind) {
    this.kind = kind;
    return this;
  }

  /**
   * Get kind
   * @return kind
   */
  @NotNull 
  @Schema(name = "kind", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("kind")
  public KindEnum getKind() {
    return kind;
  }

  public void setKind(KindEnum kind) {
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

  public Metadata created(OffsetDateTime created) {
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

  public Metadata lastModified(OffsetDateTime lastModified) {
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

  public Metadata sizeB(Long sizeB) {
    this.sizeB = JsonNullable.of(sizeB);
    return this;
  }

  /**
   * Get sizeB
   * @return sizeB
   */
  
  @Schema(name = "size_b", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("size_b")
  public JsonNullable<Long> getSizeB() {
    return sizeB;
  }

  public void setSizeB(JsonNullable<Long> sizeB) {
    this.sizeB = sizeB;
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

  public Metadata hashSha3256(String hashSha3256) {
    this.hashSha3256 = JsonNullable.of(hashSha3256);
    return this;
  }

  /**
   * Get hashSha3256
   * @return hashSha3256
   */
  
  @Schema(name = "hash_sha3_256", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("hash_sha3_256")
  public JsonNullable<String> getHashSha3256() {
    return hashSha3256;
  }

  public void setHashSha3256(JsonNullable<String> hashSha3256) {
    this.hashSha3256 = hashSha3256;
  }

  public Metadata thumbnail(String thumbnail) {
    this.thumbnail = JsonNullable.of(thumbnail);
    return this;
  }

  /**
   * Get thumbnail
   * @return thumbnail
   */
  
  @Schema(name = "thumbnail", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("thumbnail")
  public JsonNullable<String> getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(JsonNullable<String> thumbnail) {
    this.thumbnail = thumbnail;
  }

  public Metadata consolidateV(String consolidateV) {
    this.consolidateV = JsonNullable.of(consolidateV);
    return this;
  }

  /**
   * Get consolidateV
   * @return consolidateV
   */
  
  @Schema(name = "consolidate_v", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("consolidate_v")
  public JsonNullable<String> getConsolidateV() {
    return consolidateV;
  }

  public void setConsolidateV(JsonNullable<String> consolidateV) {
    this.consolidateV = consolidateV;
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
        equalsNullable(this.sizeB, metadata.sizeB) &&
        equalsNullable(this.mediaType, metadata.mediaType) &&
        equalsNullable(this.metadata, metadata.metadata) &&
        equalsNullable(this.hashSha3256, metadata.hashSha3256) &&
        equalsNullable(this.thumbnail, metadata.thumbnail) &&
        equalsNullable(this.consolidateV, metadata.consolidateV) &&
        Objects.equals(this.rawAccess, metadata.rawAccess) &&
        Objects.equals(this.status, metadata.status);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, hashCodeNullable(parentId), kind, hashCodeNullable(blobType), hashCodeNullable(blobRef), name, created, lastModified, hashCodeNullable(sizeB), hashCodeNullable(mediaType), hashCodeNullable(metadata), hashCodeNullable(hashSha3256), hashCodeNullable(thumbnail), hashCodeNullable(consolidateV), rawAccess, status);
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
    sb.append("    sizeB: ").append(toIndentedString(sizeB)).append("\n");
    sb.append("    mediaType: ").append(toIndentedString(mediaType)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    hashSha3256: ").append(toIndentedString(hashSha3256)).append("\n");
    sb.append("    thumbnail: ").append(toIndentedString(thumbnail)).append("\n");
    sb.append("    consolidateV: ").append(toIndentedString(consolidateV)).append("\n");
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

