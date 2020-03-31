package com.hoaxify.ws.error;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * Created By Yasin Memic on Mar, 2020
 */
@Data
public class ApiError {

    private int status;

    private String message;

    private String path;

    private Long timestamp= new Date().getTime();

    private Map<String, String> validationErrors;

    public ApiError(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
    }
}
