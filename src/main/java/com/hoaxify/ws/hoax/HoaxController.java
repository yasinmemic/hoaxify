package com.hoaxify.ws.hoax;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @GetMapping(ApiPaths.HoaxCtrl.CTRL + "/{id:[0-9]+}")
    public ResponseEntity<?> getAllHoaxesRelative(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page,
                                                  @PathVariable("id") Long id,
                                                  @RequestParam(name = "count", required = false, defaultValue = "false") Boolean count,
                                                  @RequestParam(value = "direction", required = false, defaultValue = "false") String direction) {
        if (count) {
            Long newHoaxCount = hoaxService.getNewHoaxesCount(id);
            Map<String, Long> response = new HashMap<>();
            response.put("count", newHoaxCount);
            return ResponseEntity.ok(response);
        }
        if(direction.equals("after")){
            List<HoaxVM> newHoaxes = hoaxService.getNewHoaxes(id, page.getSort()).stream().map(HoaxVM::new).collect(Collectors.toList());
            return ResponseEntity.ok(newHoaxes);
        }
        return ResponseEntity.ok(hoaxService.getOldHoaxes(id, page).map(HoaxVM::new));
    }

    @GetMapping(ApiPaths.UserCtrl.CTRL + "/{username}/hoaxes")
    public Page<HoaxVM> getUserHoaxes(@PathVariable("username") String username,
                                      @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return hoaxService.getAllByUsername(username, pageable).map(HoaxVM::new);
    }

    @GetMapping(ApiPaths.UserCtrl.CTRL + "/{username}/hoaxes/{id:[0-9]+}")
    public ResponseEntity<?> getUserHoaxesRelative(@PathVariable("username") String username, @PathVariable("id") Long id,
                                                   @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page,
                                                   @RequestParam(name = "count", defaultValue = "false", required = false) Boolean count,
                                                   @RequestParam(value = "direction", required = false, defaultValue = "false") String direction) {
        if (count) {
            Long newHoaxCountByUsername = hoaxService.getNewHoaxesCountByUsername(username, id);
            Map<String, Long> response = new HashMap<>();
            response.put("count", newHoaxCountByUsername);
            return ResponseEntity.ok(response);
        }
        if(direction.equals("after")){
            List<HoaxVM> newHoaxes = hoaxService.getNewHoaxesByUsername(id, username, page.getSort()).stream().map(HoaxVM::new).collect(Collectors.toList());
            return ResponseEntity.ok(newHoaxes);
        }
        return ResponseEntity.ok(hoaxService.getAllOldHoaxesByUsername(id, username, page).map(HoaxVM::new));
    }
}
