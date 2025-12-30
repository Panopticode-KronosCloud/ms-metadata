package com.panopticode.openapi.api;

import com.panopticode.openapi.model.ErrorModel;
import com.panopticode.openapi.model.Ingredient;
import com.panopticode.openapi.model.Recipe;
import com.panopticode.openapi.model.RecipeRef;
import com.panopticode.openapi.model.RequiredRecipeBody;
import java.util.UUID;
import com.panopticode.openapi.model.UpdateRecipeRequest;


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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-28T03:09:42.850562636Z[Europe/London]", comments = "Generator version: 7.18.0")
@Controller
@RequestMapping("${openapi.java-spring-boot-ms-template_OpenAPI.base-path:/api}")
public class CookbookApiController implements CookbookApi {

    private final CookbookApiDelegate delegate;

    public CookbookApiController(@Autowired(required = false) CookbookApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new CookbookApiDelegate() {});
    }

    @Override
    public CookbookApiDelegate getDelegate() {
        return delegate;
    }

}
