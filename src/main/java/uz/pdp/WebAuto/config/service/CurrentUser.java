package uz.pdp.WebAuto.config.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import uz.pdp.WebAuto.entity.User;
import uz.pdp.WebAuto.service.impl.UserServiceImp;

@Component
public class CurrentUser {
    private final UserServiceImp service;

    public CurrentUser(@Lazy UserServiceImp service) {
        this.service = service;
    }

    public UserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

    public String getCurrentUsername() {
        UserDetails userDetails = getCurrentUserDetails();
        System.out.println(userDetails.getUsername());
        return userDetails != null ? userDetails.getUsername() : null;
    }

    public User getCurrentUser() {
        String username = getCurrentUsername();
        return service.findByUsername(username);
    }
}