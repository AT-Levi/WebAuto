package uz.pdp.WebAuto.dtos.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDataDTO {
        Long id;
        String username;
        String fullName;
        String password;
        String role;
        String status;
        String phoneNumber;
        String email;
}
