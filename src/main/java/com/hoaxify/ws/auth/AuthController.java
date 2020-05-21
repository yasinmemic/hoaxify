package com.hoaxify.ws.auth;

import com.hoaxify.ws.shared.CurrentUser;
import com.hoaxify.ws.user.User;
import com.hoaxify.ws.user.vm.UserVM;
import com.hoaxify.ws.utils.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Yasin Memic on Apr, 2020
 */
@RestController
@RequestMapping(ApiPaths.AuthCtrl.CTRL)
@CrossOrigin
@Api(value = "Authentication Controller")
public class AuthController {

    @ApiOperation(value = "Authentication Handler", response = UserVM.class)
    @PostMapping
    UserVM handleAuthentication(@CurrentUser User user) {
        return new UserVM(user);
    }
}
