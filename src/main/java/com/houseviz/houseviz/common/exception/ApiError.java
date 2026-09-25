package com.houseviz.houseviz.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiError(
        Instant timestamp,
        int status,
        String error,          // "Not Found"
        String code,           // "MATERIAL_NOT_FOUND"
        String message,        // человекочитаемое
        String path,           // /api/catalog/materials/xxx
        List<FieldError> fieldErrors  // для ошибок валидации, иначе null
) {
    public record FieldError(String field, String message, Object rejectedValue) {}

    public static ApiError of(int status, String error, String code, String message, String path) {
        return new ApiError(Instant.now(), status, error, code, message, path, null);
    }

    public static ApiError withFields(int status, String error, String code, String message,
                                      String path, List<FieldError> fieldErrors) {
        return new ApiError(Instant.now(), status, error, code, message, path, fieldErrors);
    }
}
