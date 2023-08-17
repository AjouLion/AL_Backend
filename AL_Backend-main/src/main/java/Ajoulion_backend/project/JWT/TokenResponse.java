package Ajoulion_backend.project.JWT;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class TokenResponse {
    private String token;
    private String tokenType;
    private String issuedTime;
    private String expiredTime;
    private String name;
    private String profileUrl;
}