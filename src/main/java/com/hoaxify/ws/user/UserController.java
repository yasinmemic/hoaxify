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
@RequestMapping(ApiPaths.UserCtrl.CTRL)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping
    Page<UserVM> getUsers(Pageable page, @CurrentUser User user) {
        return userService.getUsers(page, user).map(UserVM::new);
    }

    @GetMapping("/{username}")
    UserVM getUser(@PathVariable("username") String username) {
        return new UserVM(userService.getByUsername(username));
    }

    @PutMapping("/{username}")
    @PreAuthorize("#username == principal.username")
        //Spring Expression Language (SpEL)
    UserVM updateUser(@RequestBody UserUpdateVM updatedUser, @PathVariable("username") String username) {
        return new UserVM(userService.updateUser(username, updatedUser));
    }
}
