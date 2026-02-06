package com.panopticode.openapi.api;

import com.panopticode.openapi.model.CreateMetadataRequest;
import com.panopticode.openapi.model.ErrorModel;
import com.panopticode.openapi.model.Metadata;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-03T01:21:35.129907262Z[Europe/London]", comments = "Generator version: 7.18.0")
@Controller
@RequestMapping("${openapi.metadata-microservice_OpenAPI.base-path:/api/v1/metadata}")
public class MetadataApiController implements MetadataApi {

    private final MetadataApiDelegate delegate;

    public MetadataApiController(@Autowired(required = false) MetadataApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new MetadataApiDelegate() {});
    }

    @Override
    public MetadataApiDelegate getDelegate() {
        return delegate;
    }

}
