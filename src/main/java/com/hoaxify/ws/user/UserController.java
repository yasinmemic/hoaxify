package com.hoaxify.ws.user;

import com.hoaxify.ws.shared.CurrentUser;
import com.hoaxify.ws.user.vm.UserUpdateVM;
import com.hoaxify.ws.user.vm.UserVM;
import com.hoaxify.ws.utils.ApiPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created By Yasin Memic on Mar, 2020
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(ApiPaths.UserCtrl.CTRL)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping(ApiPaths.UserCtrl.CTRL)
    Page<UserVM> getUsers(Pageable page, @CurrentUser User user) {
        return userService.getUsers(page, user).map(UserVM::new);
    }

    @GetMapping(ApiPaths.UserCtrl.UserCtrlWithUsernameCtrl.CTRL)
    UserVM getUser(@PathVariable("username") String username) {
        return new UserVM(userService.getByUsername(username));
    }

    @PutMapping(ApiPaths.UserCtrl.UserCtrlWithUsernameCtrl.CTRL)
    @PreAuthorize("#username == principal.username")
        //Spring Expression Language (SpEL)
    UserVM updateUser(@Valid @RequestBody UserUpdateVM updatedUser, @PathVariable String username) {
        User user = userService.updateUser(username, updatedUser);
        return new UserVM(user);
    }
}
