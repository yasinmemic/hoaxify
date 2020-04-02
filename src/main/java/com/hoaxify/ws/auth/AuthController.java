package com.hoaxify.ws.auth;

import com.fasterxml.jackson.annotation.JsonView;
import com.hoaxify.ws.shared.CurrentUser;
import com.hoaxify.ws.shared.Views;
import com.hoaxify.ws.user.User;
import com.hoaxify.ws.user.UserRepository;
import com.hoaxify.ws.utils.ApiPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Yasin Memic on Apr, 2020
 */
@RestController
@RequestMapping(ApiPaths.AuthCtrl.CTRL)
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @PostMapping
    @JsonView(Views.Base.class)
    ResponseEntity<?> handleAuthentication(@CurrentUser User user) {
        return ResponseEntity.ok(user);
    }
}
