package uz.pdp.WebAuto.enums;

import lombok.Getter;

@Getter
public enum UserRoleName {

    USER("Regular user role with access to standard functionalities"),
    ADMIN("Administrator role with full access to all system functionalities"),
    DEALER("Dealer role responsible for managing product or service offerings"),
    SUPER_ADMIN("Super Administrator role with oversight and advanced privileges");

    private final String description;

    UserRoleName(String description) {
        this.description = description;
    }

}
