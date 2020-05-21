package com.hoaxify.ws.user;

import com.hoaxify.ws.shared.CurrentUser;
import com.hoaxify.ws.user.vm.UserUpdateVM;
import com.hoaxify.ws.user.vm.UserVM;
import com.hoaxify.ws.utils.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "User Controller APIs")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Create User", response = ResponseEntity.class)
    @PostMapping(ApiPaths.UserCtrl.CTRL)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @ApiOperation(value = "Get All Users", response = Page.class)
    @GetMapping(ApiPaths.UserCtrl.CTRL)
    Page<UserVM> getUsers(Pageable page, @CurrentUser User user) {
        return userService.getUsers(page, user).map(UserVM::new);
    }

    @ApiOperation(value = "Get User by Username", response = UserVM.class)
    @GetMapping(ApiPaths.UserCtrl.UserCtrlWithUsernameCtrl.CTRL)
    UserVM getUser(@PathVariable("username") String username) {
        return new UserVM(userService.getByUsername(username));
    }

    @ApiOperation(value = "Update User", response = UserVM.class)
    @PutMapping(ApiPaths.UserCtrl.UserCtrlWithUsernameCtrl.CTRL)
    @PreAuthorize("#username == principal.username")
        //Spring Expression Language (SpEL)
    UserVM updateUser(@Valid @RequestBody UserUpdateVM updatedUser, @PathVariable String username) {
        User user = userService.updateUser(username, updatedUser);
        return new UserVM(user);
    }

    @ApiOperation(value = "Delete User after checking principal", response = String.class)
    @DeleteMapping(ApiPaths.UserCtrl.UserCtrlWithUsernameCtrl.CTRL)
    @PreAuthorize("#username == principal.username")
    String deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return "User is removed";
    }
}
