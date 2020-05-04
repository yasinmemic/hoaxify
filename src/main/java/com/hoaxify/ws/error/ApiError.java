package com.hoaxify.ws.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * Created By Yasin Memic on Mar, 2020
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Api model returns every errors.")
public class ApiError {

    @ApiModelProperty(required = true, value = "Error Status Code")
    private int status;

    @ApiModelProperty(required = true, value = "Error Message")
    private String message;

    @ApiModelProperty(required = true, value = "Error Path")
    private String path;

    @ApiModelProperty(required = true, value = "Error Time")
    private Long timestamp = new Date().getTime();

    @ApiModelProperty(required = true, value = "Validation Errors")
    private Map<String, String> validationErrors;

    public ApiError(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
    }
}
