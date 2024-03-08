package br.com.elwgomes.application.animal.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AnimalControllerResponse<D>(String status, String code, String message, D data) {
    public AnimalControllerResponse(String status, String code, String message) {
        this(status, code, message, null);
    }

    public AnimalControllerResponse(String status, String code, D data) {
        this(status, code, null, data);
    }
}
