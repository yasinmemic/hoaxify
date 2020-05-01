package com.hoaxify.ws.hoax;

import com.hoaxify.ws.error.NotFoundException;
import com.hoaxify.ws.hoax.vm.HoaxVM;
import com.hoaxify.ws.shared.CurrentUser;
import com.hoaxify.ws.user.User;
import com.hoaxify.ws.user.UserService;
import com.hoaxify.ws.utils.ApiPaths;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created By Yasin Memic on Apr, 2020
 */
@RestController
public class HoaxController {

    HoaxService hoaxService;
    UserService userService;

    public HoaxController(HoaxService hoaxService, UserService userService) {
        this.hoaxService = hoaxService;
        this.userService = userService;
    }

    @PostMapping(ApiPaths.HoaxCtrl.CTRL)
    public ResponseEntity<?> saveHoax(@Valid @RequestBody Hoax hoax, @CurrentUser User user) {
        hoaxService.save(hoax, user);
        return ResponseEntity.ok(new HoaxVM(hoax));
    }

    @GetMapping(ApiPaths.HoaxCtrl.CTRL)
    public Page<HoaxVM> getAllHoaxes(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page) {
        return hoaxService.getAll(page).map(HoaxVM::new);
    }

    @GetMapping(ApiPaths.UserCtrl.CTRL + "/{username}/hoaxes")
    public Page<HoaxVM> getAllHoaxesByUsername(@PathVariable("username") String username,
                                               @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return hoaxService.getAllByUsername(username, pageable).map(HoaxVM::new);
    }

}
