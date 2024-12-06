package uz.pdp.WebAuto.config;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
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

    public UserDetails getCurrentUser() {
        UserDetails userDetails = getCurrentUserDetails();
        if (userDetails != null) {
            return service.loadUserByUsername(userDetails.getUsername());
        }
        return null;
    }


    public String getCurrentUsername() {
        UserDetails userDetails = getCurrentUserDetails();
        return userDetails != null ? userDetails.getUsername() : null;
    }

}