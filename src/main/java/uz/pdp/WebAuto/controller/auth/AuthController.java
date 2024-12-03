package uz.pdp.WebAuto.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.dtos.AuthUserDTO;
import uz.pdp.WebAuto.dtos.LoginDTOWithRole;
import uz.pdp.WebAuto.dtos.TokensDTO;
import uz.pdp.WebAuto.dtos.UserPassDTO;
import uz.pdp.WebAuto.enums.UserRoleName;
import uz.pdp.WebAuto.service.RefreshTokenService;
import uz.pdp.WebAuto.service.RoleService;
import uz.pdp.WebAuto.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final RoleService roleService;

    public AuthController(AuthenticationManager authenticationManager,
                          RefreshTokenService refreshTokenService,
                          UserService userService,
                          RoleService roleService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.refreshTokenService = refreshTokenService;
        this.roleService = roleService;
    }

    public TokensDTO getToken(@RequestBody AuthUserDTO user) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.username(), user.password());
        authenticationManager.authenticate(authentication);
        userService.isDeleted(user);
        return refreshTokenService.getTokens(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTOWithRole> getTokens(UserPassDTO user) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.username(), user.password());
        authenticationManager.authenticate(authenticationToken);

        AuthUserDTO authUserDTO = new AuthUserDTO(user.username(), user.password(), null);
        userService.isDeleted(authUserDTO);
        TokensDTO tokens = refreshTokenService.getTokens(authUserDTO);
        List<String> roles = roleService.getRoles(user.username());
        String role = roles.size() > 1 ? UserRoleName.ADMIN.name() : UserRoleName.USER.name();

        return ResponseEntity.ok(new LoginDTOWithRole(tokens, role));
    }


    @PostMapping("/signup")
    public ResponseEntity<LoginDTOWithRole> register(@RequestBody AuthUserDTO user) {
        userService.save(user);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.username(), user.password());
        authenticationManager.authenticate(authenticationToken);
        AuthUserDTO authUserDTO = new AuthUserDTO(user.username(), user.password(), null);
        userService.isDeleted(authUserDTO);
        TokensDTO tokens = refreshTokenService.getTokens(authUserDTO);
        return ResponseEntity.ok(new LoginDTOWithRole(tokens, UserRoleName.USER.name()));
    }


}
