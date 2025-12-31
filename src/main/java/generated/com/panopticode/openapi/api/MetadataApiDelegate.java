package com.panopticode.openapi.api;

import com.panopticode.openapi.model.CreateMetadataRequest;
import com.panopticode.openapi.model.ErrorModel;
import com.panopticode.openapi.model.Metadata;
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
 * A delegate to be called by the {@link MetadataApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-31T15:08:48.518121375Z[Europe/London]", comments = "Generator version: 7.18.0")
public interface MetadataApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /nodes : Create new metadata record
     * Create new metadata record
     *
     * @param createMetadataRequest Data object to save a new recipe (required)
     * @return successful operation (status code 201)
     *         or Invalid input (status code 405)
     *         or Unexpected error (status code 200)
     * @see MetadataApi#createMetadata
     */
    default ResponseEntity<Metadata> createMetadata(CreateMetadataRequest createMetadataRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"blob_ref\" : \"blob_ref\", \"metadata\" : { \"key\" : \"\" }, \"thumbnail\" : \"thumbnail\", \"kind\" : \"file\", \"created\" : \"2000-01-23T04:56:07.000+00:00\", \"blob_type\" : \"blob_type\", \"media_type\" : \"media_type\", \"parent_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"name\" : \"name\", \"size_b\" : 0, \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"last_modified\" : \"2000-01-23T04:56:07.000+00:00\", \"hash_sha3_256\" : \"hash_sha3_256\", \"raw_access\" : false, \"consolidate_v\" : \"consolidate_v\", \"status\" : \"unavailable\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 6, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 6, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
