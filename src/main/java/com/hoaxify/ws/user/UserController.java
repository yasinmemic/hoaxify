package com.hoaxify.ws.user;

import com.hoaxify.ws.error.ApiError;
import com.hoaxify.ws.utils.ApiPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By Yasin Memic on Mar, 2020
 */
@RestController
@RequestMapping(ApiPaths.UserCtrl.CTRL)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        String username = user.getUsername();
        if (username == null || username.isEmpty()) {
            Map<String, String> validationErrors = new HashMap<>();
            validationErrors.put("username","usename cannot be null!");
            ApiError error = new ApiError(400,"Validation Error","/api/1.0/users");
            error.setValidationErrors(validationErrors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        return ResponseEntity.ok(userService.save(user));
    }


/*    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(MethodArgumentNotValidException exception){
        ApiError error = new ApiError(400,"Validation Error","/api/1.0/users");
        Map<String, String> validationErrors = new HashMap<>();

        for(FieldError fieldError : exception.getBindingResult().getFieldErrors()){
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        error.setValidationErrors(validationErrors);
        return error;
    }*/
}
