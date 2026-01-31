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
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-26T19:03:14.882484979Z[Europe/London]", comments = "Generator version: 7.18.0")
public interface MetadataApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /nodes : Create new metadata record
     * Create new metadata record
     *
     * @param createMetadataRequest Data object to save a new metadata entry (required)
     * @return successful operation (status code 201)
     *         or Invalid input (status code 405)
     *         or Unexpected error (status code 200)
     * @see MetadataApi#createMetadata
     */
    default ResponseEntity<Metadata> createMetadata(CreateMetadataRequest createMetadataRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"blob_ref\" : \"blob_ref\", \"metadata\" : { \"key\" : \"\" }, \"size_bytes\" : 0, \"kind\" : \"file\", \"created\" : \"2000-01-23T04:56:07.000+00:00\", \"blob_type\" : \"blob_type\", \"media_type\" : \"media_type\", \"parent_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"name\" : \"name\", \"hashes\" : [ { \"encoding\" : \"encoding\", \"value\" : \"value\", \"algorithm\" : \"algorithm\" }, { \"encoding\" : \"encoding\", \"value\" : \"value\", \"algorithm\" : \"algorithm\" } ], \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"consolidation_info\" : { \"consolidated_at\" : \"2000-01-23T04:56:07.000+00:00\", \"version\" : \"version\" }, \"thumbnails\" : [ { \"width\" : 0, \"height\" : 0 }, { \"width\" : 0, \"height\" : 0 } ], \"last_modified\" : \"2000-01-23T04:56:07.000+00:00\", \"raw_access\" : false, \"status\" : \"unavailable\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 5, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 5, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
