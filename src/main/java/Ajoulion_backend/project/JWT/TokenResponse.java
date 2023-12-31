package Ajoulion_backend.project.JWT;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenResponse {
    private String token;
    private String tokenType;
    private String issuedTime;
    private String expiredTime;
    private String name;
    private String profile;
    private Integer category;
}