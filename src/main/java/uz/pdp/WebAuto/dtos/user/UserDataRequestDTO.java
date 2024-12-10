package uz.pdp.WebAuto.dtos.user;

import org.springframework.web.multipart.MultipartFile;
import uz.pdp.WebAuto.entity.Address;

public class UserDataRequestDTO {
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private MultipartFile profileImage;

    private Address address;

    private String email;

    private String phoneNumber;
}
