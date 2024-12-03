package uz.pdp.WebAuto.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.WebAuto.dtos.UserDataDTO;
import uz.pdp.WebAuto.entity.AuthUser;
import uz.pdp.WebAuto.service.UserService;

import java.util.List;

@RestController

public class UserManageController {

    private final UserService userService;

    public UserManageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public UserDataDTO getUserById(@PathVariable(name = "id") Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/user/all")
    public List<AuthUser> getAllUsers() {
        return null;
    }
}
