package uz.pdp.WebAuto.dtos.auth;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokensDTO {
    String accessToken;
    String refreshToken;
}
