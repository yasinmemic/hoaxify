package com.hoaxify.ws.error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created By Yasin Memic on Apr, 2020
 */
@RestController
@Api(value = "Errors return with ApiError model")
public class ErrorHandler implements ErrorController {

    @Autowired
    private ErrorAttributes errorAttributes;

    @ApiOperation(value = "Error api return an error with ApiError model")
    @RequestMapping("/error")
    ApiError handleError(WebRequest webRequest) {
        Map<String, Object> attributes = this.errorAttributes.getErrorAttributes(webRequest, true);
        String message = (String) attributes.get("message");
        String path = (String) attributes.get("path");
        int status = (int) attributes.get("status");
        ApiError apiError = new ApiError(status, message, path);

        if (attributes.containsKey("errors")) {
            List<FieldError> fieldErrors = (List<FieldError>) attributes.get("errors");
            Map<String, String> validationError = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                validationError.put(fieldError.getField(), fieldError.getDefaultMessage());
                apiError.setValidationErrors(validationError);
            }
        }

        return apiError;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
