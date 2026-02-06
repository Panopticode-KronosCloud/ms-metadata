/*
 * metadata-microservice
 * Copyright (c) 2024-2026 Panopticode.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.panopticode.metadata.controller;

import com.panopticode.metadata.exception.BaseAppRuntimeException;
import com.panopticode.metadata.exception.DataLayerException;
import com.panopticode.metadata.exception.DuplicateEntryException;
import com.panopticode.metadata.exception.EntityNotFoundException;
import com.panopticode.openapi.model.ErrorModel;

import io.github.aglibs.validcheck.ValidationError;
import io.github.aglibs.validcheck.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

/**
 * Manage exception handling to map errors to REST responses.
 */
@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler
    extends ResponseEntityExceptionHandler
{
    private static final String X_APPLICATION_NAME = "X-Application-Name";
    private static final String APPLICATION_NAME = "metadata-service";

    public RestResponseEntityExceptionHandler()
    { }

    @ExceptionHandler(value = DataLayerException.class)
    protected ResponseEntity<Object> handleDataLayerException(RuntimeException ex, WebRequest request)
    {
        final var bodyOfResponse = new ErrorModel(HttpStatus.SERVICE_UNAVAILABLE.value(), ex.getMessage());
        log.warn("Handling DataLayerException exception back to client {}", request, ex);
        return handleExceptionInternal(ex, bodyOfResponse,
                _httpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
    }

    @ExceptionHandler(value = DuplicateEntryException.class)
    protected ResponseEntity<Object> handleDuplicateEntryException(RuntimeException ex, WebRequest request)
    {
        final var bodyOfResponse = new ErrorModel(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        log.warn("Handling DuplicateEntryException exception back to client {}", request, ex);
        return handleExceptionInternal(ex, bodyOfResponse,
                _httpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(RuntimeException ex, WebRequest request)
    {
        final var bodyOfResponse = new ErrorModel(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        log.warn("Handling EntityNotFoundException exception back to client {}", request, ex);
        return handleExceptionInternal(ex, bodyOfResponse,
                _httpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = ValidationException.class)
    protected ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request)
    {
        // Access structured error information
        final var errMessage = ex.getErrors().stream()
                .map(ValidationError::toString)
                .collect(Collectors.joining(", ", "Errors: [", "]"));
        final var bodyOfResponse = new ErrorModel(HttpStatus.BAD_REQUEST.value(), errMessage);
        log.warn("Handling ValidationException exception back to client {}", request, ex);
        return handleExceptionInternal(ex, bodyOfResponse,
                _httpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Catch-all handler for any uncaught exception thrown in this application.
     */
    @ExceptionHandler(value = BaseAppRuntimeException.class)
    protected ResponseEntity<Object> handleAppException(RuntimeException ex, WebRequest request)
    {
        final var bodyOfResponse = new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        log.warn("Handling BaseAppRuntimeException exception back to client {}", request, ex);
        return handleExceptionInternal(ex, bodyOfResponse,
                _httpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private static HttpHeaders _httpHeaders()
    {
        final var httpHeaders = new HttpHeaders();
        httpHeaders.add(X_APPLICATION_NAME, APPLICATION_NAME);
        return httpHeaders;
    }
}
