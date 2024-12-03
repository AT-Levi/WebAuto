package uz.pdp.WebAuto.security;

import uz.pdp.WebAuto.entity.user.AuthUser;
import uz.pdp.WebAuto.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@SuppressWarnings("unused")
public class SessionUser {

    private final UserService authUserService;

    public SessionUser(UserService authUserService) {
        this.authUserService = authUserService;
    }

    public AuthUser user() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof AuthUser authUser) {
            return authUser;
        } else if (principal instanceof org.springframework.security.core.userdetails.User springUser) {
            return fetchAuthUserFromUsername(springUser.getUsername());
        }
        return null;
    }

    private AuthUser fetchAuthUserFromUsername(String username) {
        return authUserService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public Long id() {
        AuthUser user = user();
        if (Objects.isNull(user))
            return -1L;
        return user.getId();
    }
}