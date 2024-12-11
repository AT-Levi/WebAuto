package uz.pdp.WebAuto.dtos.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserDataDTO {
        Long id;
        String username;
        String firstName;
        String lastName;
        String password;
        List<String> roles;
        String status;
        String phoneNumber;
        String email;
}
