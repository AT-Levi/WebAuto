package uz.pdp.WebAuto.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.WebAuto.entity.User;
import uz.pdp.WebAuto.service.UserService;

import java.util.List;

@RestController

public class UserManageController {

    private final UserService userService;

    public UserManageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/all")
    public List<User> getAllUsers() {
        return null;
    }
}
